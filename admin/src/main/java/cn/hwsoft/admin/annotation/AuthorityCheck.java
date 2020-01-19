package cn.hwsoft.admin.annotation;

import java.lang.annotation.*;

/**
 * @program: court
 * @description: 权限认证注解,
 * @author: QEcode
 * @create: 2019-08-13 09:34
 **/
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthorityCheck {
    String value() default "";
}
