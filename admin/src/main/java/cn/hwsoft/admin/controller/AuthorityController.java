package cn.hwsoft.admin.controller;

import cn.hwsoft.wisdom.core.domain.Admin;
import cn.hwsoft.wisdom.core.domain.Admin_authoritys;
import cn.hwsoft.wisdom.core.domain.Authority;
import cn.hwsoft.wisdom.core.query.JSONResult;
import cn.hwsoft.wisdom.core.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @program: court
 * @description: 权限管理
 * @author: QEcode
 * @create: 2019-08-02 12:00
 **/
@Controller
@RequestMapping("/authority")
public class AuthorityController {

    @Autowired
    private AuthorityService authorityService;

    /**
     * 获取权限列表
     * @return
     */
    @GetMapping("/list")
    @ResponseBody
    public JSONResult list(HttpSession session){
        List<Authority> list = authorityService.list();
        JSONResult result = new JSONResult();
        session.setAttribute("authorityList",list);
        result.setData(list);
        return result;
    }

    /**
     * 获取登陆管理员的权限
     * @param session
     * @return
     */
    @GetMapping("/mine")
    @ResponseBody
    public JSONResult getByAdminId(HttpSession session){
        Admin admin = (Admin) session.getAttribute("admin");
        JSONResult result = new JSONResult();
        if(admin==null){
            result.mark("");
            return  result;
        }
        List<Admin_authoritys> authorities = authorityService.getByAdminId(admin.getId());
        session.setAttribute("authorities",authorities);
        result.setData(authorities);
        return result;
    }
}
