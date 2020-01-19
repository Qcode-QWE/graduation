package cn.hwsoft.admin.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: court
 * @description: controller错误日志记录
 * @author: QEcode
 * @create: 2019-08-13 10:44
 **/
@Aspect
@Component
public class AdminLoggerAspect {

    private  static  final Logger logger = LoggerFactory.getLogger(AdminLoggerAspect.class);
    // 切入点表达式按需配置
    @Pointcut("execution(* cn.hwsoft.admin..*.*(..)))")
    private void myPointcut() {
    }

    @AfterThrowing(value = "myPointcut()", throwing = "e")
    @ResponseBody
    public void  handleThrowing(JoinPoint joinPoint, Throwable e) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        //开始打log
        StringBuffer buffer = new StringBuffer();
        buffer.append(System.getProperty("line.separator"));
        buffer.append("异常:" + e.getMessage());
        buffer.append(System.getProperty("line.separator"));
        buffer.append("异常所在类：" + className);
        buffer.append(System.getProperty("line.separator"));
        buffer.append("异常所在方法：" + methodName);
        buffer.append(System.getProperty("line.separator"));
        buffer.append("异常中的参数：");
        for (int i = 0; i < args.length; i++) {
            buffer.append(System.getProperty("line.separator"));
            buffer.append("参数"+i+" : "+ args[i].toString());
        }
        e.printStackTrace();
        logger.error(buffer.toString());
    }
}
