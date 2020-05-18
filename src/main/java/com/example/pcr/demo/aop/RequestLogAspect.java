package com.example.pcr.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author chengri.piao
 * @Title:
 * @Package
 * @Description:
 * @date 2020/4/29 15:15
 */
@Aspect
@Component
public class RequestLogAspect {
    private static final Logger logger = LoggerFactory.getLogger(RequestLogAspect.class);

    @Pointcut("execution (public * com.example.pcr.demo.log..*.*(..))")
    public void webLog() {

    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        //接受到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //记录下请求内容
        logger.info("URL:" + request.getRequestURL().toString());
        logger.info("IP:" + request.getRemoteAddr());
        logger.info("URI:" + request.getRequestURI());
        logger.info("method:" + joinPoint.getSignature().getName());
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            System.out.println("arg=====" + arg);
        }

        System.out.println("classPath" + System.getProperty("java.class.path"));
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) {
        //处理完请求，返回内容
        logger.info("RESPONSE:" + ret);
    }
}
