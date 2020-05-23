package cn.hwsoft.wisdom.core.service;

import cn.hwsoft.wisdom.core.domain.Law_help;
import cn.hwsoft.wisdom.core.query.LawQuery;
import cn.hwsoft.wisdom.core.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @program: court
 * @description: 法律咨询service-
 * @author:
 * @create: 2019-07-13 12:14
 **/
public interface LawHelpService {
    public PageInfo<Law_help> selectList(int id, byte tag, QueryObject qo);
    public boolean saveUserLawHelp(Law_help lawHelp);

    /*--------------后台管理界面------------------*/

//    LawJsonResult listByTag(LawQuery query);

    void updateById(Law_help lawHelp);

    void saveAdminLawHelp(Law_help help);

    boolean delete(Integer id);

//    Law_help searchByPid(Integer pid);
//
//    Law_help searchById(Integer pid);

    Law_help searchByUid(LawQuery query,int uid);

    int count(byte reply_mark, Byte tag);

    PageInfo<Law_help> searchByUids(Integer uid, byte reply_mark,byte tag,QueryObject qo);

    List<Integer> getUids(LawQuery query);

    Law_help getLawHelpById(Integer id);

    boolean createLawHelpLog(Law_help lawHelp);
}
