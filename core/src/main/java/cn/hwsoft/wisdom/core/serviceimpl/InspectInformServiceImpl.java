package cn.hwsoft.wisdom.core.serviceimpl;


import cn.hwsoft.wisdom.core.domain.Inspect_inform;
import cn.hwsoft.wisdom.core.domain.Inspect_informExample;
import cn.hwsoft.wisdom.core.mapper.Inspect_informMapper;
import cn.hwsoft.wisdom.core.mapper.Inspect_typeMapper;
import cn.hwsoft.wisdom.core.query.QueryObject;
import cn.hwsoft.wisdom.core.service.InspectInformService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @program: court
 * @description: 军检控告
 * @author: QEcode
 * @create: 2019-07-22 18:15
 **/
@Service
public class InspectInformServiceImpl implements InspectInformService {
    @Autowired
    private Inspect_informMapper informMapper;
    @Autowired
    private Inspect_typeMapper typeMapper;

    /**
     * 查询举报列表(分页,带条件)
     * @param qo
     * @return
     */
    @Override
    public PageInfo<Inspect_inform> list(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        Inspect_informExample example = new Inspect_informExample();
        Inspect_informExample.Criteria criteria = example.createCriteria();
        //按时间顺序
        example.setOrderByClause("create_time desc");
        if(!StringUtils.isEmpty(qo.getKeyword())){
            int num = Integer.valueOf(qo.getKeyword());
            criteria.andReceiptEqualTo(num);
        }
        List<Inspect_inform> list = informMapper.selectByExample(example);
        return new PageInfo<>(list);
    }

    /**
     * 根据id查询举报
     * @param id
     * @return
     */
    @Override
    public Inspect_inform selectById(int id) {
        Inspect_inform inform = informMapper.selectByPrimaryKey(id);
        return inform;
    }
    /**
     * 修改处理状态
     * @param inform
     */
    @Override
    public void updateProcessStatus(Inspect_inform inform) {
        informMapper.updateByPrimaryKeySelective(inform);
    }

    /*---------------用户界面----------------------------*/

    @Override
    public Integer saveInspectInform(Inspect_inform inspectInform) {
        //设置后台工作人员Id，默认为0
        inspectInform.setAdmin_id(0);
        //设置进展情况：1:已提交，2：已受理，3：已完结
        inspectInform.setProcess_status((byte) 1);
        //设置状态:1:数据正常，2：异常
        inspectInform.setStatus((byte) 1);
        //插入数据
        informMapper.insert(inspectInform);
        //返回唯一回执单号
        return 0;
    }


    @Override
    public Inspect_inform findInspectInformByReceipt(Integer receipt) {
        //创建查询条件
        Inspect_informExample example=new Inspect_informExample();
        Inspect_informExample.Criteria criteria = example.createCriteria();
        //根据用户查询信息
        criteria.andReceiptEqualTo(receipt);
        List<Inspect_inform> inspect_informs = informMapper.selectByExample(example);
        if(inspect_informs!=null && inspect_informs.size()>0){
            return  inspect_informs.get(0);
        }
        return null;
    }

    /**
     * 查询用户的举报列表
     * @param qo
     * @param userId
     * @return
     */
    @Override
    public PageInfo<Inspect_inform> listByUserId(QueryObject qo, Integer userId) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        Inspect_informExample example = new Inspect_informExample();
        Inspect_informExample.Criteria criteria = example.createCriteria();
        //按时间顺序
        example.setOrderByClause("create_time desc");
        criteria.andUser_idEqualTo(userId);
        //状态正常
        criteria.andStatusEqualTo((byte)1);
        if(!StringUtils.isEmpty(qo.getKeyword())){
            int num = Integer.valueOf(qo.getKeyword());
            criteria.andReceiptEqualTo(num);
        }
        List<Inspect_inform> list = informMapper.selectByExample(example);
        return new PageInfo<>(list);
    }

}
