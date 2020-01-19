package cn.hwsoft.wisdom.core.serviceimpl;


import cn.hwsoft.wisdom.core.domain.Inspect_sue;
import cn.hwsoft.wisdom.core.domain.Inspect_sueExample;
import cn.hwsoft.wisdom.core.mapper.Inspect_sueMapper;
import cn.hwsoft.wisdom.core.mapper.Inspect_typeMapper;
import cn.hwsoft.wisdom.core.query.QueryObject;
import cn.hwsoft.wisdom.core.service.InspectSueService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @program: parent
 * @description: 军检控告
 * @author: QEcode
 * @create: 2019-07-12 11:29
 **/
@Service
public class InspectSueServiceImpl implements InspectSueService {
    @Autowired
    private Inspect_sueMapper inspectSueMapper;
    @Autowired
    private Inspect_typeMapper inspectTypeMapper;
    /**
     * 保存军检控告表
     * @param inspectSue
     */
    @Override
    public Integer save(Inspect_sue inspectSue) {
        inspectSue.setAdmin_id(0);  //后台工作人员id
        inspectSue.setProcess_status((byte)1); //后台工作人员处理状态
        inspectSue.setStatus((byte) 1); //数据状况
        //处理状态
        inspectSue.setProcess_status((byte)1);
        inspectSueMapper.insertSelective(inspectSue);
        return  0;
    }

    /**
     * 根据回执单号查询
     * @param receipt
     * @return
     */
    @Override
    public Inspect_sue selectByReceipt(int receipt) {
        Inspect_sueExample example = new Inspect_sueExample();
        Inspect_sueExample.Criteria criteria = example.createCriteria();
        //根据回执单号查询
        criteria.andReceiptEqualTo(receipt);
        //获取创建时间最晚的一个,防止回执单号发生冲突
        example.setOrderByClause("create_time desc");
        List<Inspect_sue> list =  inspectSueMapper.selectByExample(example);
        Inspect_sue inspectSue = null;
        if(list.size()>0){
            inspectSue = list.get(0);
        }
        return inspectSue;
    }

    /**
     * 查询用户的控告列表
     * @param qo
     * @param userId
     * @return
     */
    @Override
    public PageInfo<Inspect_sue> listByUserId(QueryObject qo, Integer userId) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        Inspect_sueExample example = new Inspect_sueExample();
        Inspect_sueExample.Criteria criteria = example.createCriteria();
        //按时间顺序
        example.setOrderByClause("create_time desc");
        criteria.andUser_idEqualTo(userId);
        //状态正常
        criteria.andStatusEqualTo((byte)1);
        if(!StringUtils.isEmpty(qo.getKeyword())){
            int num = Integer.valueOf(qo.getKeyword());
            criteria.andReceiptEqualTo(num);
        }
        List<Inspect_sue> list = inspectSueMapper.selectByExample(example);
        return new PageInfo<>(list);
    }




    /*-----------------------管理界面--------------------------------------*/

    /**
     * 查询列表(分页,带条件)
     * @param qo
     * @return
     */
    @Override
    public PageInfo<Inspect_sue> list(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        Inspect_sueExample example = new Inspect_sueExample();
        Inspect_sueExample.Criteria criteria = example.createCriteria();
        //按时间顺序
        example.setOrderByClause("create_time desc");
        if(!StringUtils.isEmpty(qo.getKeyword())){
            int num = Integer.valueOf(qo.getKeyword());
            criteria.andReceiptEqualTo(num);
        }
        List<Inspect_sue> list = inspectSueMapper.selectByExample(example);
        return new PageInfo<>(list);
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Override
    public Inspect_sue selectById(int id) {
        Inspect_sue sue = inspectSueMapper.selectByPrimaryKey(id);
        return sue;
    }
    /**
     * 修改处理状态
     * @param sue
     */
    @Override
    public void updateProcessStatus(Inspect_sue sue) {
        inspectSueMapper.updateByPrimaryKeySelective(sue);
    }
}
