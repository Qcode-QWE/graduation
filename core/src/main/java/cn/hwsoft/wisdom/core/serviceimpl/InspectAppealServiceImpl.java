package cn.hwsoft.wisdom.core.serviceimpl;

import cn.hwsoft.wisdom.core.domain.Inspect_appeal;
import cn.hwsoft.wisdom.core.domain.Inspect_appealExample;
import cn.hwsoft.wisdom.core.mapper.Inspect_appealMapper;
import cn.hwsoft.wisdom.core.query.QueryObject;
import cn.hwsoft.wisdom.core.service.InspectAppealService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;


/**
 * @program: court
 * @description: 军检申述
 * @author: QEcode
 * @create: 2019-07-19 10:11
 **/
@Service
public class InspectAppealServiceImpl implements InspectAppealService {

    @Autowired
    private Inspect_appealMapper appealMapper;

    @Override //保存申诉，且返回 回执单号
    public String save(Inspect_appeal appeal) {
        appeal.setAdmin_id(0);  //后台工作人员id
        appeal.setProcess_status((byte)1); //后台工作人员处理状态
        appeal.setStatus((byte) 1); //数据状况
        //处理状态
        appeal.setProcess_status((byte)1);
        //保存到数据库
        appealMapper.insert(appeal);
        return "";
    }


    @Override  //通过回执单号查询申诉
    public Inspect_appeal selectByReceipt(Integer receipt) {
        Inspect_appealExample example = new Inspect_appealExample();
        Inspect_appealExample.Criteria criteria = example.createCriteria();
        criteria.andReceiptEqualTo(receipt);
        List<Inspect_appeal> list = appealMapper.selectByExample(example);
        if(list.size()>0){
            return (Inspect_appeal) list.toArray()[0];
        }
        return null;
    }

    /**
     * 查询用户的申述列表
     * @param qo
     * @param userId
     * @return
     */
    @Override
    public PageInfo<Inspect_appeal> listByUserId(QueryObject qo, Integer userId) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        Inspect_appealExample example = new Inspect_appealExample();
        Inspect_appealExample.Criteria criteria = example.createCriteria();
        //按时间顺序
        example.setOrderByClause("create_time desc");
        //userId
        criteria.andUser_idEqualTo(userId);
        //状态正常
        criteria.andStatusEqualTo((byte)1);
        if(!StringUtils.isEmpty(qo.getKeyword())){
            int num = Integer.valueOf(qo.getKeyword());
            criteria.andReceiptEqualTo(num);
        }
        List<Inspect_appeal> list = appealMapper.selectByExample(example);
        return new PageInfo<>(list);
    }

    /**
     * 查询所有类型的军检
     * @param qo
     * @return
     */
    @Override
    public PageInfo<Inspect_appeal> allList(QueryObject qo,Integer userId) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        Inspect_appealExample example = new Inspect_appealExample();
        Inspect_appealExample.Criteria criteria = example.createCriteria();
        //用户id
        criteria.andUser_idEqualTo(userId);
        //按时间顺序
        example.setOrderByClause("create_time desc");
        //状态为正常的
        criteria.andStatusEqualTo((byte)1);
        if(!StringUtils.isEmpty(qo.getKeyword())){
            int num = Integer.valueOf(qo.getKeyword());
            criteria.andReceiptEqualTo(num);
        }
        List<Inspect_appeal> list = appealMapper.selectAllInspect(example);
        return new PageInfo<>(list);
    }

    /*-----------------管理界面-------------------------*/


    /**
     * 查询军检申述
     * @param qo
     * @return
     */
    @Override
    public PageInfo<Inspect_appeal> list(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        Inspect_appealExample example = new Inspect_appealExample();
        Inspect_appealExample.Criteria criteria = example.createCriteria();
        //按时间顺序
        example.setOrderByClause("create_time desc");
        if(!StringUtils.isEmpty(qo.getKeyword())){
            int num = Integer.valueOf(qo.getKeyword());
            criteria.andReceiptEqualTo(num);
        }
        List<Inspect_appeal> list = appealMapper.selectByExample(example);
        return new PageInfo<>(list);
    }



    /**
     * 根据id查询控告
     * @param id
     * @return
     */
    @Override
    public Inspect_appeal selectById(int id) {
        Inspect_appeal appeal = appealMapper.selectByPrimaryKey(id);
        return appeal;
    }

    /**
     *
     * @param ids
     * @return
     */
    @Override
    public List<Inspect_appeal> selectByIds(List<Integer> ids) {
        Inspect_appealExample example = new Inspect_appealExample();
        Inspect_appealExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        List<Inspect_appeal> list = appealMapper.selectByExample(example);
        return list;
    }

    /**
     * 修改处理状态
     * @param appeal
     */
    @Override
    public void updateProcessStatus(Inspect_appeal appeal) {
        appealMapper.updateByPrimaryKeySelective(appeal);
    }


}
