package cn.hwsoft.admin.interceptor;

import cn.hwsoft.admin.annotation.AuthorityCheck;
import cn.hwsoft.wisdom.core.domain.Admin_authoritys;
import cn.hwsoft.wisdom.core.domain.Authority;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @program: court
 * @description: 权限认证拦截器
 * @author: QEcode
 * @create: 2019-08-09 17:18
 **/

public class AuthorityInterceptor implements HandlerInterceptor {

    /**
     * 拦截url,进行权限验证
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if(handler instanceof HandlerMethod){
            AuthorityCheck authorityCheck = ((HandlerMethod) handler).getMethodAnnotation(AuthorityCheck.class);
            //没有声明需要权限,或者声明不验证权限
            if(authorityCheck == null || StringUtils.isEmpty(authorityCheck.value()))
                return true;
            else{
                List<Admin_authoritys> authorityses = (List<Admin_authoritys>) session.getAttribute("authorities");
                List<Authority> authorityList = (List<Authority>) session.getAttribute("authorityList");
                boolean flage = false;
                if(authorityses==null||authorityList==null||authorityses.size()==0||authorityList.size()==0){
                    return false;
                }
                String value = authorityCheck.value();
                for(int i=0;i<authorityList.size();i++){
                    if(authorityList.get(i).getName().equals(value)){
                        if(authorityses.get(i).getStatus()==1){
                            flage = true;
                        }
                        break;
                    }
                }
                return flage;
            }
        }else {
            return  true;
        }
    }
}
