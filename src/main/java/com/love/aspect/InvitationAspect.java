package com.love.aspect;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author admin
 */
@Slf4j
@Aspect
@Component
public class InvitationAspect {

//    @Around("execution(* com.love.controller.*.*(..))")
    public Object around(JoinPoint joinPoint) throws Throwable {
        final Object[] args = joinPoint.getArgs();
//        log.info("参数是：{}", JSON.toJSONString(args));
        final Object proceed = ((ProceedingJoinPoint) joinPoint).proceed();
        log.info("返回值信息是：{}", JSON.toJSONString(proceed));
        return proceed;
    }

}
