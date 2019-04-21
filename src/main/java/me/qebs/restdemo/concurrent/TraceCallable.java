package me.qebs.restdemo.concurrent;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import java.util.concurrent.Callable;

/**
 * Callable 的包装类，支持日志的透传
 *
 * @param <V>
 */
public class TraceCallable<V> implements Callable<V> {
    private String copyTraceId;
    private Callable<V> callable;

    public TraceCallable(Callable<V> callable) {
        this.copyTraceId = TraceContext.getContext().getTraceId();
        this.callable = callable;
    }

    @Override
    public V call() throws Exception {
        try {
            TraceContext.getContext().setTraceId(copyTraceId);
            if (StringUtils.isBlank(TraceContext.getContext().getTraceId())) {
                TraceContext.getContext().genAndSetTraceId();
            }
            MDC.put(Constants.TRACE_ID, TraceContext.getContext().getTraceId());
            return callable.call();
        } finally {
            TraceContext.removeContext();
            MDC.remove(Constants.TRACE_ID);
        }
    }

    public static <V> TraceCallable wrapOf(Callable<V> callable) {
        return new TraceCallable(callable);
    }
}
