package com.love.aspect;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author admin
 */
@Slf4j
@Aspect
@Component
public class InvitationAspect {

    @Resource
    private HttpServletRequest request;
    //@Around("execution(* com.love.controller.*.*(..))")
//    public Object around(JoinPoint joinPoint) throws Throwable {
//        final Object proceed = ((ProceedingJoinPoint) joinPoint).proceed();
//        log.info("返回值信息是：{}", JSON.toJSONString(proceed));
//        return proceed;
//    }

    @Before("execution(* com.love.controller.*.*(..))")
    public void before() {
        log.info("有用户访问啦 IP是：{}", getIp2(request));
    }


    /**
     * 多次反向代理后会有多个ip值，第一个ip才是真实ip
     *
     * @param request
     * @return
     */
    public static String getIp2(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (!StringUtils.isEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {

            int index = ip.indexOf(",");
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if (!StringUtils.isEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            return ip;
        }
        return request.getRemoteAddr();
    }
}
