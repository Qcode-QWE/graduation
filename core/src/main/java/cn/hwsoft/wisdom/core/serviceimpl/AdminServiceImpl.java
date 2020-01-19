package cn.hwsoft.wisdom.core.serviceimpl;

import cn.hwsoft.wisdom.core.domain.*;
import cn.hwsoft.wisdom.core.mapper.AdminMapper;
import cn.hwsoft.wisdom.core.mapper.Admin_login_recordMapper;
import cn.hwsoft.wisdom.core.query.QueryObject;
import cn.hwsoft.wisdom.core.service.AdminService;
import cn.hwsoft.wisdom.core.service.AuthorityService;
import cn.hwsoft.wisdom.core.utils.MD5Utils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: court
 * @description: 管理员业务层
 * @author: QEcode
 * @create: 2019-07-16 17:47
 **/
@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private Admin_login_recordMapper loginRecordMapper;
    @Autowired
    private AuthorityService authorityService;

    /**
     * 根据id查询普通管理员
     * @param id
     * @return
     */
    @Override
    public Admin selectAdmin(Integer id) {
        Admin admin = adminMapper.selectByPrimaryKey(id);
        return admin;
    }

    /**
     * 查询管理员列表(只获取普通管理员)
     * @param qo
     * @return
     */
    @Override
    public Map<String,Object> selectList(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        AdminExample example = new AdminExample();
        AdminExample.Criteria criteria = example.createCriteria();
        //普通管理员
        criteria.andTagEqualTo((byte)2);
        //条件查询
        if(!StringUtils.isEmpty(qo.getKeyword())){
            criteria.andNicknameLike('%'+qo.getKeyword()+'%');
        }
        example.setOrderByClause("create_time desc");
        List<Admin> list = adminMapper.selectByExample(example);
        //获取管理员的登陆信息
        Map<Integer,Admin_login_record> ipMap = new HashMap<>();
        Admin_login_recordExample example1 = new Admin_login_recordExample();
        Admin_login_recordExample.Criteria criteria1;
        for(Admin admin : list){
            example1.clear();
            criteria1 = example1.createCriteria();
            //管理员id
            criteria1.andUser_idEqualTo(admin.getId());
            //最近一次登陆记录
            example1.setOrderByClause("create_time desc");
            List<Admin_login_record> recordlist = loginRecordMapper.selectByExample(example1);
            if(recordlist.size()>0){
                ipMap.put(admin.getId(),recordlist.get(0));
            }
        }
        Map<String,Object> map = new HashMap<>();
        map.put("admins",new PageInfo<>(list));
        map.put("ipMap",ipMap);
        return map;
    }

    /**
     * 删除普通管理员
     * @param ids
     */
    @Override
    public void deleteByids(ArrayList<Integer> ids) {
        for(int id:ids){
            Admin admin = new Admin();
            admin.setId(id);
            admin.setStatus((byte)2);
            adminMapper.updateByPrimaryKeySelective(admin);

        }
    }

    /**
     * 修改普通管理员
     * @param admin
     */
    @Override
    public void update(Admin admin) {
        adminMapper.updateByPrimaryKeySelective(admin);
    }


    /**
     * 新增普通管理员
     * @param admin
     */
    @Override
    public void insertAdmin(Admin admin,List<Admin_authoritys> authorityses) {
        adminMapper.insertSelective(admin);
        for(Admin_authoritys authoritys:authorityses){
            authoritys.setAdmin_id(admin.getId());
        }
        authorityService.insert(authorityses);
    }

    /**
     * 判断name的用户是否存在
     * @param name
     * @return
     */
    @Override
    public Admin isExist(String name) {
        AdminExample example = new AdminExample();
        AdminExample.Criteria criteria = example.createCriteria();
        criteria.andNicknameEqualTo(name);
        List<Admin> list = adminMapper.selectByExample(example);
        if(list.size()>0){
            return list.get(0);
        }
        return null;
    }

    /**
     * 登陆验证
     * @param admin
     * @return
     */
    @Override
    public Admin login(Admin admin) {
        AdminExample example = new AdminExample();
        AdminExample.Criteria criteria = example.createCriteria();
        criteria.andNicknameEqualTo(admin.getNickname());
        criteria.andPasswordEqualTo(MD5Utils.md5(admin.getPassword()));
        List<Admin> list = adminMapper.selectByExample(example);
        if(list.size()>0){
            return list.get(0);
        }
        return null;
    }

    /**
     * 保存用户登陆信息
     * @param record
     */
    @Override
    public void saveRecord(Admin_login_record record) {
        loginRecordMapper.insertSelective(record);
    }

    /**
     * 初始化密码
     * @param id
     */
    @Override
    public void resetPassword(int id,String newpass) {
        Admin admin = new Admin();
        admin.setId(id);
        admin.setPassword(MD5Utils.md5(newpass));
        adminMapper.updateByPrimaryKeySelective(admin);
    }




}
