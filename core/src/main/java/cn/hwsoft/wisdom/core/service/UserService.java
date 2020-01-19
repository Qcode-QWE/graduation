package cn.hwsoft.wisdom.core.service;

import cn.hwsoft.wisdom.core.domain.Inspect_inform;
import cn.hwsoft.wisdom.core.domain.User;
import cn.hwsoft.wisdom.core.domain.User_login_record;
import cn.hwsoft.wisdom.core.query.QueryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Lenovo on 2019/7/10.--
 */
public interface UserService {
    //用户注册
    void register(User user);

    void addRecord(User_login_record record);

    User findUserById(int id);

    void updatePsw(User user);

    List<Inspect_inform> showReport(Integer id);

    User findByIdentity(String identity);

    void forgetPassword(User user);

    /*-------------------管理界面----------------------*/

    // 根据id查询用户
    public User selectUser(Integer id);

    public Map<String,Object> selectList(QueryObject qo, int searchType, int status);

    public void deleteByids(ArrayList<Integer> ids);

    public void update(User user);

    public User selectById(User user);

    public void resetPassword(int id);

}
