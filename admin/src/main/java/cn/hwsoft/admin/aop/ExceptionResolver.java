package cn.hwsoft.admin.aop;

import cn.hwsoft.wisdom.core.query.JSONResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: court
 * @description: 对异常进行处理,返回JsonResult
 * @author: QEcode
 * @create: 2019-08-15 10:05
 **/

@ControllerAdvice
public class ExceptionResolver{
    @ExceptionHandler(Exception.class)    //对那些异常进行处理
    @ResponseBody                         //定义返回类型，
    public JSONResult response(Exception e){
        JSONResult result = new JSONResult();
        result.mark("服务器发生了异常");
        e.printStackTrace();
        return result;
    }

}