package cn.hwsoft.app.controller;

import cn.hwsoft.app.utils.UploadUtil;
import cn.hwsoft.wisdom.core.domain.User;
import cn.hwsoft.wisdom.core.domain.User_login_record;
import cn.hwsoft.wisdom.core.query.JSONResult;
import cn.hwsoft.wisdom.core.service.UserService;
import cn.hwsoft.wisdom.core.utils.IpUtils;
import cn.hwsoft.wisdom.core.utils.MD5Utils;
import cn.hwsoft.wisdom.core.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by Lenovo on 2019/7/10.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")  //用户注册
    @ResponseBody
    public JSONResult register(User user){
        JSONResult result = new JSONResult();
        User user1 = userService.findByIdentity(user.getIdentity());
        if(user1!=null){
            result.mark("用户已存在");
            return result;
        }
        //将日期转换成时间戳
        Date date =new Date();
        String dateToTimeStamp = TimeUtils.DateToTimeStamp(date);
        //设置创建时间
        user.setCreate_time(Integer.valueOf(dateToTimeStamp));
        //设置更新时间
        user.setUpdate_time(Integer.valueOf(dateToTimeStamp));
        user.setPhone(0L);
        user.setCover("");
        user.setAddress("");
        user.setStatus((byte) 1);
        userService.register(user);
        return result;
    }

    /**
     * 根据身份证号判断用户是否已经存在
     * @param identity
     * @return
     */
    @GetMapping("/exist/{identity}")
    @ResponseBody
    public JSONResult isRegistered(@PathVariable("identity") String identity){
        User user = userService.findByIdentity(identity);
        JSONResult result = new JSONResult();
        if(user!=null){
            result.mark("用户已存在");
        }
        return result;
    }



    @RequestMapping("/login")  //用户登陆
    @ResponseBody
    public JSONResult login(HttpServletRequest request, User user){
        JSONResult result = new JSONResult();
        //根据用户的身份证号和密码查找user
        User user1 = userService.selectById(user);
        request.getSession().setAttribute("user",user);
        //获取用户客户端登陆的ip地址
        String ip = IpUtils.getIp(request);
        if(user1!=null){
            //将日期转换成时间戳
            Date date =new Date();
            String dateToTimeStamp = TimeUtils.DateToTimeStamp(date);
            //记录用户登陆信息
            User_login_record record = new User_login_record();
            record.setUser_id(user1.getId());
            record.setIp(ip);
            record.setCreate_time(Integer.valueOf(dateToTimeStamp));
            userService.addRecord(record);
            user1.setPassword(null);
            result.setData(user1);
        }else {
            result.mark("没有该用户");
        }
        return result;
    }

    //退出登陆
    @GetMapping("/exit")
    @ResponseBody
    public JSONResult exit(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getSession().setAttribute("user", null);
        return  new JSONResult();
    }

    //显示个人信息
    @GetMapping("/mine/{id}")
    @ResponseBody
    public JSONResult showInfo(@PathVariable("id") int id) {
        User user = userService.findUserById(id);
        JSONResult result = new JSONResult();
        if (user != null) {
            result.setData(result);
        }else {
            result.mark("没有该用户");
        }
        return result;
    }

    //修改个人密码
    @PostMapping("/chagePwd")
    @ResponseBody
    public JSONResult chagePwd(HttpServletRequest request, User user, String newPwd) {
        JSONResult result = new JSONResult();
        User user1 = userService.findUserById(user.getId());
        if(user1!=null){
            if(user1.getPassword().equals(MD5Utils.md5(user.getPassword()))){
                user.setPassword(MD5Utils.md5(newPwd));
                String dateToTimeStamp = TimeUtils.DateToTimeStamp(new Date());
                user.setUpdate_time(Integer.valueOf(dateToTimeStamp));
                userService.updatePsw(user);
            }else {
                result.mark("旧密码不正确");
            }
        }else{
            result.mark("不存在该用户");
        }
        return result;
    }


    /**
     * 根据身份证号更新密码
     * @param identity
     * @param password
     * @return
     */
    @PostMapping("/forgetPwd")
    @ResponseBody
    public JSONResult forgetPwd(String identity,String password){
        JSONResult result = new JSONResult();
        User user = userService.findByIdentity(identity);
        if(user==null){
            result.mark("没有该用户");
            return result;
        }
        //修改密码
        user.setPassword(MD5Utils.md5(password));
        String date = TimeUtils.DateToTimeStamp(new Date());
        user.setUpdate_time(Integer.valueOf(date));
        userService.forgetPassword(user);
        return result;
    }

    //文件上传接口
    @PostMapping("/img/upload")
    @ResponseBody
    public JSONResult uploadImage(@RequestParam("file")MultipartFile file, HttpServletRequest request) {
        String url= UploadUtil.upload(file,UploadUtil.getCoverPath());
        JSONResult result = new JSONResult();
        if(url!=null){
            result.setData(url);
        }else{
            result.mark("上传失败");
        }
        return  result;
    }




}
