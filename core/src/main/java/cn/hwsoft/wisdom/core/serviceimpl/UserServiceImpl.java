package cn.hwsoft.wisdom.core.serviceimpl;

import cn.hwsoft.wisdom.core.domain.*;
import cn.hwsoft.wisdom.core.mapper.Inspect_informMapper;
import cn.hwsoft.wisdom.core.mapper.UserMapper;
import cn.hwsoft.wisdom.core.mapper.User_login_recordMapper;
import cn.hwsoft.wisdom.core.query.QueryObject;
import cn.hwsoft.wisdom.core.service.UserService;
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
 * Created by Lenovo on 2019/7/10.--
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper; //相当于注入User的dao层
    @Autowired
    private User_login_recordMapper recordMapper;

    @Autowired
    private Inspect_informMapper informapper;

    @Override   //用户注册
    public void register(User user) {
        //密码用md5加密
        user.setPassword( MD5Utils.md5(user.getPassword()));
        //用户状态默认为1，即为正常
        user.setStatus((byte) 1);
        userMapper.insertSelective(user);
    }

    @Override  //根据用户身份证和密码查找user
    public User selectById(User user) {
        user.setPassword(MD5Utils.md5(user.getPassword()));
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andIdentityEqualTo(user.getIdentity());
        criteria.andPasswordEqualTo(user.getPassword());
        List<User> list = userMapper.selectByExample(userExample);
        if(list.size()>0){
            System.out.println((User) list.toArray()[0]);
            return (User) list.toArray()[0];
        }
        return null;
    }


    @Override  //记录用户登陆状态
    public void addRecord(User_login_record record) {
        recordMapper.insert(record);
    }

    @Override
    public User findUserById(int id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override //更新用户密码
    public void updatePsw(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override//显示用户举报记录
    public List<Inspect_inform> showReport(Integer id) {

        Inspect_informExample informExample = new Inspect_informExample();
        Inspect_informExample.Criteria criteria = informExample.createCriteria();
        criteria.andUser_idEqualTo(id);
        List<Inspect_inform> list = informapper.selectByExample(informExample);
        return list;
    }

    /**
     * 根据身份证号查询
     * @param identity
     * @return
     */
    @Override
    public User findByIdentity(String identity) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andIdentityEqualTo(identity);
        List<User> list = userMapper.selectByExample(example);
        if(list.size()>0){
            return list.get(0);
        }
        return null;
    }

    /**
     * 修改密码
     */
    @Override
    public void forgetPassword(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    /*--------------------------管理界面---------------------------*/

    /**
     * 根据用户id查询用户
     * @param id
     * @return
     */
    @Override
    public User selectUser(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        return user;
    }

    /**
     * 查询用户
     * @param qo
     * @return
     */
    @Override
    public Map<String,Object> selectList(QueryObject qo,int searchType,int status) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        if(searchType!=0) {
            if (!StringUtils.isEmpty(qo.getKeyword())) {
                if(searchType==1){
                    criteria.andNicknameLike('%' + qo.getKeyword() + '%');
                }else if(searchType==2){
                    criteria.andIdentityEqualTo(qo.getKeyword());
                }
            }
        }
        if(status!=0){
            criteria.andStatusEqualTo((byte)status);
        }
        example.setOrderByClause("create_time desc");
        List<User> list = userMapper.selectByExample(example);
        //获取用户的登陆信息
        Map<Integer,User_login_record> ipMap = new HashMap<>();
        User_login_recordExample recordExample = new User_login_recordExample();
        User_login_recordExample.Criteria recordCriteria;
        for(User user:list){
            recordExample.clear();
            recordCriteria = recordExample.createCriteria();
            //用户id
            recordCriteria.andUser_idEqualTo(user.getId());
            //最近一次登陆记录
            recordExample.setOrderByClause("create_time desc");
            List<User_login_record> recordlist = recordMapper.selectByExample(recordExample);
            if(recordlist.size()>0){
                ipMap.put(user.getId(),recordlist.get(0));
            }
        }
        Map<String,Object> map = new HashMap<>();
        map.put("users",new PageInfo<>(list));
        map.put("ipMap",ipMap);
        return map;
    }

    /**
     * 删除用户
     * @param ids
     */
    @Override
    public void deleteByids(ArrayList<Integer> ids) {
       for(int id:ids){
           User user = new User();
           user.setId(id);
           user.setStatus((byte)2);
           userMapper.updateByPrimaryKeySelective(user);
       }
    }

    /**
     * 更新用户
     * @param user
     */
    @Override
    public void update(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }


    /**
     * 初始化密码
     * @param id
     */
    @Override
    public void resetPassword(int id) {
        User user = new User();
        user.setId(id);
        user.setPassword(MD5Utils.md5("123456"));
        userMapper.updateByPrimaryKeySelective(user);
    }

}
