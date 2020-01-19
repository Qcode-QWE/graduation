package cn.hwsoft.wisdom.core.serviceimpl;

import cn.hwsoft.wisdom.core.domain.Admin_authoritys;
import cn.hwsoft.wisdom.core.domain.Admin_authoritysExample;
import cn.hwsoft.wisdom.core.domain.Authority;
import cn.hwsoft.wisdom.core.domain.AuthorityExample;
import cn.hwsoft.wisdom.core.mapper.Admin_authoritysMapper;
import cn.hwsoft.wisdom.core.mapper.AuthorityMapper;
import cn.hwsoft.wisdom.core.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: court
 * @description:
 * @author: QEcode
 * @create: 2019-08-03 09:07
 **/
@Service
public class AuthorityServiceImpl implements AuthorityService{

    @Autowired
    private AuthorityMapper authorityMapper;
    @Autowired
    private Admin_authoritysMapper adminAuthoritysMapper;

    /**
     * 获取权限列表
     * @return
     */
    @Override
    public List<Authority> list() {
        AuthorityExample example = new AuthorityExample();
        AuthorityExample.Criteria criteria = example.createCriteria();
        //criteria.andStatusEqualTo((byte)1);
        example.setOrderByClause("id");
        List<Authority> list = authorityMapper.selectByExample(example);
        return list;
    }

    /**
     * 获取admin的权限
     * @param id
     * @return
     */
    @Override
    public List<Admin_authoritys> getByAdminId(int id) {
        Admin_authoritysExample example = new Admin_authoritysExample();
        Admin_authoritysExample.Criteria criteria = example.createCriteria();
        criteria.andAdmin_idEqualTo(id);
        example.setOrderByClause("authoruth_id");
        List<Admin_authoritys> list = adminAuthoritysMapper.selectByExample(example);
        return list;
    }

    /**
     * 更新权限
     * @param authorities
     */
    @Override
    public void updateByAdminId(List<Admin_authoritys> authorities) {
        for(Admin_authoritys authoritys:authorities){
            adminAuthoritysMapper.updateByPrimaryKeySelective(authoritys);
        }
    }

    /**
     * 插入权限
     * @param authorities
     */
    @Override
    public void insert(List<Admin_authoritys> authorities) {
        for(Admin_authoritys authoritys:authorities){
            adminAuthoritysMapper.insertSelective(authoritys);
        }
    }
}
