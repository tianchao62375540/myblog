package com.tc.aspect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: tianchao
 * @Date: 2020/1/1 15:46
 * @Description:
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    @Pointcut("execution(* com.tc.web..*.*(..))")
    public void log(){
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        RequestLog requestLog = new RequestLog(url,ip,classMethod,args);
        log.info("-doBefore-RequestLog:{}",requestLog);
    }

    @After("log()")
    public void doAfter(){
        log.info("-------------------doAfter--------------------");
    }

    @AfterReturning(pointcut = "log()",returning = "result")
    public void doAfterReturn(Object result){
        log.info("Result: {}" , result);
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private class RequestLog{
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;

    }
}
