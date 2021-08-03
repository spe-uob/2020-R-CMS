package com.leon.dynamiccolumn.handler;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author leon
 */
@Slf4j
@Aspect
@Component
public class LogAspect {

    public LogAspect() {
        log.info("web log inited");
    }

    @Pointcut("execution(* com.leon..*.controller..*.*(..))")
    public void pointCutMethod() {
    }

    @Around("pointCutMethod()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long reqId = IdUtil.getSnowflake(1, 1).nextId();
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String uri = request.getRequestURI();
        String method = request.getMethod();
        String args = Arrays.toString(pjp.getArgs());
        log.info(reqId + "    请求：{}-----{}----请求参数:{}", uri, method, args);
        Object ret = pjp.proceed();
        log.info(reqId + "    响应：{}-----{}----响应数据:{}", uri, method, JSONObject.toJSONString(ret));
        return ret;
    }
}
