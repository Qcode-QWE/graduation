package cn.hwsoft.wisdom.core.service;

import cn.hwsoft.wisdom.core.domain.Inspect_sue;
import cn.hwsoft.wisdom.core.query.QueryObject;
import com.github.pagehelper.PageInfo;

/**
 * @program: parent
 * @description:  军检控告
 * @author: QEcode
 * @create: 2019-07-12 11:28
 **/
public interface InspectSueService {
    public Integer save(Inspect_sue inspectSue);
    public Inspect_sue selectByReceipt(int receipt);
    public PageInfo<Inspect_sue> listByUserId(QueryObject qo,Integer userId);
    /*--------------管理界面------------------------*/
    public PageInfo<Inspect_sue> list(QueryObject qo);
    public Inspect_sue selectById(int id);
    public void updateProcessStatus(Inspect_sue sue);

}
