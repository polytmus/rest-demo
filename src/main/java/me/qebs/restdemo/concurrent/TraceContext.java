package me.qebs.restdemo.concurrent;


import me.qebs.restdemo.utils.UUIDUtils;

public class TraceContext {

    private static ThreadLocal<String> context = ThreadLocal.withInitial(() -> UUIDUtils.uuid());

    private TraceContext() {
    }

    public static TraceContext getContext() {
        return new TraceContext();
    }

    public String getTraceId() {
        return context.get();
    }

    public void genAndSetTraceId() {
        context.set(UUIDUtils.uuid());
    }

    public void setTraceId(String traceId) {
        context.set(traceId);
    }

    public static void removeContext() {
        context.remove();
    }

}
