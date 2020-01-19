package cn.hwsoft.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: court
 * @description: 页面跳转controller
 * @author: Boom
 * @create: 2019-07-20 09:59
 **/
@Controller
public class pageController {

    @RequestMapping("/")
    public String showPage(){
        return "login.html";
    }

}
