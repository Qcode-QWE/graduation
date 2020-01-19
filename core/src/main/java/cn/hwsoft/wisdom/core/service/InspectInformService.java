package cn.hwsoft.wisdom.core.service;

import cn.hwsoft.wisdom.core.domain.Inspect_inform;
import cn.hwsoft.wisdom.core.query.QueryObject;
import com.github.pagehelper.PageInfo;

/**
 * @program: court
 * @description: 军检举报
 * @author: QEcode
 * @create: 2019-07-22 18:14
 **/
public interface InspectInformService {
    public PageInfo<Inspect_inform> list(QueryObject qo);
    public Inspect_inform selectById(int id);
    public void updateProcessStatus(Inspect_inform inform);
    /*--------------------用户界面--------------------------------*/
    public Integer saveInspectInform(Inspect_inform inspectInform);
    public Inspect_inform findInspectInformByReceipt(Integer receipt);
    public PageInfo<Inspect_inform> listByUserId(QueryObject qo,Integer userId);
}
