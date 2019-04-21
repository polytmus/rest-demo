package me.qebs.restdemo.interceptor;

import lombok.extern.slf4j.Slf4j;
import me.qebs.restdemo.concurrent.Constants;
import me.qebs.restdemo.concurrent.TraceContext;
import me.qebs.restdemo.utils.IPUtils;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class LogInterceptor extends HandlerInterceptorAdapter {
    public LogInterceptor() {
        super();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("remote ip is : {}", IPUtils.getRemoteRealIp());
        TraceContext.getContext().genAndSetTraceId();
        MDC.put(Constants.TRACE_ID, TraceContext.getContext().getTraceId());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        TraceContext.removeContext();
        MDC.remove(Constants.TRACE_ID);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }
}
