package cn.hwsoft.admin.controller;

import cn.hwsoft.admin.annotation.AuthorityCheck;
import cn.hwsoft.wisdom.core.domain.Admin;
import cn.hwsoft.wisdom.core.domain.Admin_authoritys;
import cn.hwsoft.wisdom.core.domain.Admin_login_record;
import cn.hwsoft.wisdom.core.query.JSONResult;
import cn.hwsoft.wisdom.core.query.QueryObject;
import cn.hwsoft.wisdom.core.service.AdminService;
import cn.hwsoft.wisdom.core.service.AuthorityService;
import cn.hwsoft.wisdom.core.utils.IpUtils;
import cn.hwsoft.wisdom.core.utils.MD5Utils;
import cn.hwsoft.wisdom.core.utils.TimeUtils;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @program: court
 * @description: 后台管理人员controller
 * @author: QEcode
 * @create: 2019-07-16 17:45
 **/

@RequestMapping("/admin")
@Controller
public class AdminController {


    @Autowired
    private AdminService adminService;
    @Autowired
    private AuthorityService authorityService;
    /**
     * 根据id查询管理员(和对应的权限)
     * @param id
     * @return
     */
    @AuthorityCheck("admin_deal")
    @GetMapping("/{id}")
    @ResponseBody
    public JSONResult getAdmin(@PathVariable("id") int id){
        Admin admin = adminService.selectAdmin(id);
        List<Admin_authoritys> authorities = authorityService.getByAdminId(id);
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("admin",admin);
        map.put("authorities",authorities);
        JSONResult result = new JSONResult();
        result.setData(map);
        return result;
    }

    /**
     * 获取管理员列表,分页,带条件(只获取普通管理人员)
     * @param qo
     * @return
     */
    @AuthorityCheck("admin_deal")
    @PostMapping("/list")
    @ResponseBody
    public JSONResult list(QueryObject qo){
        Map<String,Object> map = adminService.selectList(qo);
        JSONResult result = new JSONResult();
        result.setData(map);
        return result;
    }

    /**
     * 删除指定管理员
     * @param ids
     * @return
     */
    @AuthorityCheck("admin_deal")
    @PostMapping("/del")
    @ResponseBody
    public JSONResult delete(@RequestParam("ids")int[] ids){
        JSONResult result = new JSONResult();
        if(ids.length>0){
            ArrayList<Integer> list = new ArrayList();
            for(int id:ids){
                list.add(id);
            }
            adminService.deleteByids(list);
        }else {
            result.mark("");
        }
        return  result;
    }

    /**
     * 修改管理员
     * @param admin
     * @param authorities
     * @return
     */
    @AuthorityCheck("admin_deal")
    @PostMapping("/edit")
    @ResponseBody
    public JSONResult update(String admin,String authorities){
        JSONResult result = new JSONResult();
        Admin admin1 = JSON.parseObject(admin,Admin.class);
        List<Admin_authoritys> list = JSON.parseArray(authorities,Admin_authoritys.class);
        adminService.update(admin1);
        authorityService.updateByAdminId(list);
        result.setData(admin);
        return result;
    }

    /**
     * 修改个人信息
     * @param admin
     * @param session
     * @return
     */
    @PostMapping("/edit/mine")
    @ResponseBody
    public JSONResult update(Admin admin,HttpSession session){
        JSONResult result = new JSONResult();
        adminService.update(admin);
        admin = adminService.selectAdmin(admin.getId());
        session.setAttribute("admin",admin);
        result.setData(admin);
        return result;
    }

    /**
     * 新增管理员
     * @param admin
     * @param authorities
     * @return
     */
    @AuthorityCheck("admin_deal")
    @PostMapping("/add")
    @ResponseBody
    public JSONResult add(String admin,String authorities){
        JSONResult result = new JSONResult();
        Admin admin1 = JSON.parseObject(admin,Admin.class);
        //创建时间
        int time = Integer.valueOf(TimeUtils.DateToTimeStamp(new Date()));
        admin1.setCreate_time(time);
        //身份:普通管理员
        admin1.setTag((byte)2);
        //对密码加密
        admin1.setPassword(MD5Utils.md5(admin1.getPassword()));
        //权限管理
        List<Admin_authoritys> list = JSON.parseArray(authorities,Admin_authoritys.class);
        adminService.insertAdmin(admin1,list);
        return result;
    }

    /**
     * 判断用户名是否存在
     * @param name
     * @return
     */
    @GetMapping("/add/{name}")
    @ResponseBody
    public JSONResult isExist(@PathVariable("name") String name){
        Admin admin = adminService.isExist(name);
        JSONResult result = new JSONResult();
        if(admin != null){
            result.mark("用户名已经存在");
        }
        return result;
    }

    /**
     * 登陆
     * @param nickname
     * @param password
     * @param session
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public JSONResult login(String nickname,String password, HttpSession session,HttpServletRequest request){
        Admin admin = new Admin();
        admin.setNickname(nickname);
        admin.setPassword(password);
        admin = adminService.login(admin);
        JSONResult result = new JSONResult();
        if(admin!=null){
            //如果账户存在并且状态正常
            if(admin.getStatus()==1){
                result.setData(admin);
                session.setAttribute("admin",admin);
                //记录登陆信息
                String ip = IpUtils.getIp(request);
                Admin_login_record record = new Admin_login_record();
                record.setIp(ip);
                record.setUser_id(admin.getId());
                int time = Integer.valueOf(TimeUtils.DateToTimeStamp(new Date()));
                record.setCreate_time(time);
                adminService.saveRecord(record);
            }else {
                result.mark("该账户已被限制");
            }
        }else {
            result.mark("用户名或密码错误");
        }
        return result;
    }

    @GetMapping("/mine")
    @ResponseBody
    public JSONResult getMine(HttpSession session){
        Admin admin = (Admin) session.getAttribute("admin");
        admin = adminService.selectAdmin(admin.getId());
        JSONResult result = new JSONResult();
        result.setData(admin);
        return result;
    }

    /**
     * 判断是否已经登录
     */
    @GetMapping("/isLogin")
    @ResponseBody
    public JSONResult logined(HttpSession session){
        JSONResult result = new JSONResult();
        Admin admin = (Admin) session.getAttribute("admin");
        if(admin!=null){
            result.mark("已经登录");
            result.setData(admin);
            return result;
        }
        return  result;
    }


    /**
     * 退出登陆处理
     */
    @GetMapping("/exit")
    @ResponseBody
    public JSONResult exit(HttpSession session){
        session.invalidate();
        return  new JSONResult();
    }

    /**
     * 初始化密码
     * @param id
     * @return
     */
    @AuthorityCheck("admin_deal")
    @PostMapping("/reset")
    @ResponseBody
    public JSONResult resetPassword(int id){
        adminService.resetPassword(id,"123456");
        return new JSONResult();
    }

    /**
     * 修改自己的密码
     * @param oldpass
     * @param newpass
     */
    @PostMapping("/mine/reset")
    @ResponseBody
    public JSONResult resetPassword(String oldpass,String newpass,HttpSession session){
        Admin admin = (Admin) session.getAttribute("admin");
        JSONResult result = new JSONResult();
        if(MD5Utils.md5(oldpass).equals(admin.getPassword())){
            adminService.resetPassword(admin.getId(),newpass);
            admin = adminService.selectAdmin(admin.getId());
            session.setAttribute("admin",admin);
            return  result;
        }else {
            result.mark("旧密码不正确");
            return result;
        }
    }

}
