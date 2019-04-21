package me.qebs.restdemo.concurrent;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

/**
 * 包装Runnable，支持traceId 透传
 */
public class TraceRunnable implements Runnable {
    private Runnable runnable;
    private String copyTraceId;

    public TraceRunnable(Runnable runnable) {
        this.runnable = runnable;
        copyTraceId = TraceContext.getContext().getTraceId();
    }

    @Override
    public void run() {
        try {
            TraceContext.getContext().setTraceId(copyTraceId);
            if (StringUtils.isBlank(TraceContext.getContext().getTraceId())) {
                TraceContext.getContext().genAndSetTraceId();
            }
            MDC.put(Constants.TRACE_ID, TraceContext.getContext().getTraceId());
            runnable.run();
        } finally {
            TraceContext.removeContext();
            MDC.remove(Constants.TRACE_ID);
        }
    }

    public static TraceRunnable wrapOf(Runnable runnable) {
        return new TraceRunnable(runnable);
    }
}
