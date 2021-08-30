package com.jr.gitdemo.config;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * Created by 武江伟 on 2018/9/12.
 */
@Aspect
@Component
public class RequestAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(public * com.jr.gitdemo.controller.*.*(..))")
    public void log(){}

    @Before("log()")
    public void exBefore(JoinPoint joinPoint){
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        logger.info("url:"+request.getRequestURL());
        logger.info("method:"+request.getMethod());
        logger.info("class method:"+joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
        String params = "";
        Enumeration<String> enu = request.getParameterNames();
        while(enu.hasMoreElements()){
            String paraName = enu.nextElement();
            params += paraName + ": " + request.getParameter(paraName) + ";  ";
        }
        logger.info("params is {}",params);
    }

    @After("log()")
    public void exAfter(JoinPoint joinPoint){
        logger.info("class method:"+joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName()
                +"方法执行完毕！");
    }

    @AfterReturning(returning="result",pointcut="log()")
    public void exAfterReturning(Object result){
        logger.info("执行返回值："+ JSON.toJSONString(result));
    }
}
