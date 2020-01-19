package cn.hwsoft.wisdom.core.service;

import cn.hwsoft.wisdom.core.domain.Admin_authoritys;
import cn.hwsoft.wisdom.core.domain.Authority;

import java.util.List;

/**
 * @program: court
 * @description: 权限管理
 * @author: QEcode
 * @create: 2019-08-03 09:06
 **/
public interface AuthorityService {

    public List<Authority> list();
    public List<Admin_authoritys> getByAdminId(int id);
    public void updateByAdminId(List<Admin_authoritys> authorities);
    public void insert(List<Admin_authoritys> authorities);

}
