package cn.hwsoft.wisdom.core.service;

import cn.hwsoft.wisdom.core.domain.Inspect_type;
import cn.hwsoft.wisdom.core.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @program: court
 * @description: 军检类型
 * @author: QEcode
 * @create: 2019-07-19 10:41
 **/
public interface InspectTypeService {

    public List<Inspect_type> typeList(int type);
    public Inspect_type selectById(int id);
    public PageInfo<Inspect_type> list(QueryObject qo, int tag);
    public void save(Inspect_type type);
    public void edit(Inspect_type type);


}
