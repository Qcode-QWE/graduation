package cn.hwsoft.wisdom.core.service;

import cn.hwsoft.wisdom.core.domain.Admin;
import cn.hwsoft.wisdom.core.domain.Admin_authoritys;
import cn.hwsoft.wisdom.core.domain.Admin_login_record;
import cn.hwsoft.wisdom.core.query.QueryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @program: court
 * @description: 管理员业务层
 * @author: QEcode
 * @create: 2019-07-16 17:47
 **/
public interface AdminService {
    public Admin selectAdmin(Integer id);

    public Map<String,Object> selectList(QueryObject qo);

    public void deleteByids(ArrayList<Integer> list);

    public void update(Admin admin);

    public void insertAdmin(Admin admin,List<Admin_authoritys> authorityses);

    public Admin isExist(String name);

    public Admin login(Admin admin);

    public void saveRecord(Admin_login_record record);

    public void resetPassword(int id,String newpass);

}
