package com.example.springboot.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.logging.Logger;

/**
 * @Author wusha
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/

@Aspect
@Component
public class AspectAop {

    private Logger logger = Logger.getLogger("aspect");

    //两种方式
    @Pointcut("execution(public * com.example.springboot..*.*(..))")
    //@Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void webLog(){};

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable{
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();

        HttpServletRequest request = attributes.getRequest();

        //记录下请求内容
        logger.info("URL"+request.getRequestURI());
        logger.info("HTTP_METHOD:"+request.getMethod());
        logger.info("IP:"+ request.getRemoteAddr());
        logger.info("CLASS_METHOD:"+joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint);
        logger.info("ARGS:"+ Arrays.toString(joinPoint.getArgs()));

    }


}
