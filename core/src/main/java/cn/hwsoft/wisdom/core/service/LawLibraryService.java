package cn.hwsoft.wisdom.core.service;

import cn.hwsoft.wisdom.core.domain.Law_library;
import cn.hwsoft.wisdom.core.query.LawJsonResult;
import cn.hwsoft.wisdom.core.query.LawQuery;
import cn.hwsoft.wisdom.core.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @program: court
 * @description: 法务学习--
 * @author:
 * @create: 2019-07-13 10:13
 **/
public interface LawLibraryService {
    public List<Law_library> selectList(int nums);
    public PageInfo<Law_library> selectPageByType(QueryObject qo, Integer typeId);
    public Law_library selectById(Integer id);
    public void read(Integer id);

    /*-----------------后台界面-------------------*/

    LawJsonResult listByPage(LawQuery query);

    boolean add(Law_library lawLibrary);

    boolean delete(Integer id);

    LawJsonResult search(LawQuery query);

    Law_library searchById(Integer id);

    boolean update(Law_library lawLibrary);

    boolean changeStatus(Law_library lawLibrary);
}
