package cn.hwsoft.admin.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: court
 * @description: 登陆拦截器,拦截对view目录的请求
 * @author: QEcode
 * @create: 2019-07-18 16:32
 **/
public class LoginInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        Object obj = httpServletRequest.getSession().getAttribute("admin");
        if(obj == null){
            // 表示没有登陆
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/login.html");
            return false;
        }
        return true;
    }

}
