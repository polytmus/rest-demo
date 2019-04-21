package me.qebs.restdemo.aspect;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import me.qebs.restdemo.utils.ServletUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LogAscpect {

    @Pointcut("execution(public * me.qebs.restdemo.controller..*.*(..))")
    public void pointCut() {

    }

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        log.info("{} {},请求参数:{}", ServletUtils.getRequest().getMethod(), ServletUtils.getRequest().getServletPath(), JSON.toJSONString(joinPoint.getArgs()));
    }


    @AfterReturning(pointcut = "pointCut()", returning = "returnValue")
    public void afterReturning(JoinPoint joinPoint, Object returnValue) {
        log.info("{} {},响应参数:{}", ServletUtils.getRequest().getMethod(), ServletUtils.getRequest().getServletPath(), returnValue == null ? "null" : JSON.toJSONString(returnValue));
    }


    @Around("pointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {
        long startTime = System.currentTimeMillis();

        Object result = null;

        try {
            result = proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            log.error("执行失败", e);
        }

        long endTime = System.currentTimeMillis();
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        log.info("方法 {} ,执行时间 {} ms", signature.getMethod(), (endTime - startTime));
        return result;
    }
}
