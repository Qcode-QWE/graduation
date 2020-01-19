package cn.hwsoft.wisdom.core.service;

import cn.hwsoft.wisdom.core.domain.Inspect_appeal;
import cn.hwsoft.wisdom.core.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @program: court
 * @description: 军检控告
 * @author: QEcode
 * @create: 2019-07-19 10:10
 **/
public interface InspectAppealService {
    public PageInfo<Inspect_appeal> list(QueryObject qo);
    public Inspect_appeal selectById(int id);
    public List<Inspect_appeal> selectByIds(List<Integer> ids);
    public void updateProcessStatus(Inspect_appeal appeal);
    public Inspect_appeal selectByReceipt(Integer receipt);
    /*------------用户界面------------------*/
    public String save(Inspect_appeal appeal);
    public PageInfo<Inspect_appeal> allList(QueryObject qo,Integer userId);
    public PageInfo<Inspect_appeal> listByUserId(QueryObject qo,Integer userId);

}
