package cn.hwsoft.wisdom.core.serviceimpl;

import cn.hwsoft.wisdom.core.domain.Law_help;
import cn.hwsoft.wisdom.core.domain.Law_helpExample;
import cn.hwsoft.wisdom.core.mapper.Law_helpMapper;
import cn.hwsoft.wisdom.core.query.LawQuery;
import cn.hwsoft.wisdom.core.query.QueryObject;
import cn.hwsoft.wisdom.core.service.LawHelpService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * @program: court
 * @description: 法律咨询--
 * @author:
 * @create: 2019-07-13 12:14
 **/
@Service
public class LawHelpServiceImpl implements LawHelpService {
    @Autowired
    private Law_helpMapper lawHelpMapper;

    /**
     * 查询用户的法律咨询记录,按照问答的顺序,即create_time 升序排序
     *
     * @param id
     * @param tag
     * @param qo
     * @return
     */

    @Override
    public PageInfo<Law_help> selectList(int id, byte tag, QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        Law_helpExample example = new Law_helpExample();
        Law_helpExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(id);
        if (tag != 0) {
            criteria.andTagEqualTo(tag);
        }
        example.setOrderByClause("create_time desc");
        List<Law_help> list = lawHelpMapper.selectByExample(example);
        return new PageInfo<>(list);
    }

    /**
     * 保存留言记录
     *
     * @param lawHelp
     */
    @Override
    public boolean saveUserLawHelp(Law_help lawHelp) {
        int i = lawHelpMapper.insertSelective(lawHelp);
        if (i > 0) return true;
        return false;
    }


    /*---------------管理界面------------------------*/


    @Override  //更新reply_mark
    @Transactional
    public void updateById(Law_help lawHelp) {
        //更新当前留言的reply_mark
        lawHelp.setContent(null);
        lawHelp.setReply_mark((byte) 2);
        lawHelpMapper.updateByPrimaryKeySelective(lawHelp);


        //更新一个用户多条咨询语句的reply_mark
        Law_helpExample example = new Law_helpExample();
        Law_helpExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(lawHelp.getUid());
        criteria.andTagEqualTo(lawHelp.getTag());
        criteria.andReply_markEqualTo((byte) 1);
        List<Law_help> list = lawHelpMapper.selectByExample(example);
        for (Law_help help : list) {
            help.setReply_mark((byte) 2);
            lawHelpMapper.updateByPrimaryKeySelective(help);
        }
    }

    @Override  //保存管理人员回复的留言
    public void saveAdminLawHelp(Law_help help) {
        lawHelpMapper.insert(help);
    }

    @Override  //删除留言
    public boolean delete(Integer id) {
        int i = lawHelpMapper.deleteByPrimaryKey(id);
        if (i > 0) {
            return true;
        }
        return false;
    }

    @Override  //通过uid查询留言
    public Law_help searchByUid(LawQuery query, int uid) {
        Law_helpExample example = new Law_helpExample();
        Law_helpExample.Criteria criteria = example.createCriteria();
        if (query.getReply_mark() != (byte) 0) {
            criteria.andReply_markEqualTo(query.getReply_mark());
        }
        if (Objects.nonNull(query.getTag()) && query.getTag() != 0) {
            criteria.andTagEqualTo(query.getTag());
        }
        criteria.andUidEqualTo(uid);
        example.setOrderByClause("create_time desc");
        List<Law_help> list = lawHelpMapper.selectByExample(example);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override  //查询记录的总数
    public int count(byte reply_mark, Byte tag) {
        return lawHelpMapper.selectCount(reply_mark, tag);
    }

    @Override  //通过uid查询留言记录数
    public PageInfo<Law_help> searchByUids(Integer uid, byte reply_mark, byte tag, QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        Law_helpExample example = new Law_helpExample();
        Law_helpExample.Criteria criteria = example.createCriteria();
        if (reply_mark == (byte) 2) {
            criteria.andReply_markEqualTo(reply_mark);
        }
        example.setOrderByClause("create_time desc");
        criteria.andUidEqualTo(uid);
        criteria.andTagEqualTo(tag);
        List<Law_help> list = lawHelpMapper.selectByExample(example);
        return new PageInfo<>(list);
    }

    @Override   //获取相关的uid
    public List<Integer> getUids(LawQuery query) {
        Law_helpExample example = new Law_helpExample();
        Law_helpExample.Criteria criteria = example.createCriteria();
        if (query.getReply_mark() != (byte) 0) {
            criteria.andReply_markEqualTo(query.getReply_mark());
        }
        if(Objects.nonNull(query.getTag())&&query.getTag()!=0){
            criteria.andTagEqualTo(query.getTag());
        }
        //设置查询分页条件
        int start = (query.getPage() - 1) * query.getLimit();
        example.setStartRow(start);
        example.setPageSize(query.getLimit());
        //example.setDistinct(true);
        example.setOrderByClause("create_time desc");
        List<Integer> list = lawHelpMapper.selectUids(example);
        return list;
    }

    @Override
    public Law_help getLawHelpById(Integer id) {
        return lawHelpMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean createLawHelpLog(Law_help lawHelp) {
        Integer lawHelpLog = lawHelpMapper.create(lawHelp);
        if (lawHelpLog > 0) {
            return true;
        }
        return false;
    }

}
