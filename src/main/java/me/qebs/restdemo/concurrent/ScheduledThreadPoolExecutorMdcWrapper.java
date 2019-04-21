package me.qebs.restdemo.concurrent;

import java.util.concurrent.*;

public class ScheduledThreadPoolExecutorMdcWrapper extends ScheduledThreadPoolExecutor {
    public ScheduledThreadPoolExecutorMdcWrapper(int corePoolSize) {
        super(corePoolSize);
    }

    public ScheduledThreadPoolExecutorMdcWrapper(int corePoolSize, ThreadFactory threadFactory) {
        super(corePoolSize, threadFactory);
    }

    public ScheduledThreadPoolExecutorMdcWrapper(int corePoolSize, RejectedExecutionHandler handler) {
        super(corePoolSize, handler);
    }

    public ScheduledThreadPoolExecutorMdcWrapper(int corePoolSize, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, threadFactory, handler);
    }

    @Override
    public void execute(Runnable command) {
        super.execute(TraceRunnable.wrapOf(command));
    }

    @Override
    public Future<?> submit(Runnable task) {
        return super.submit(TraceRunnable.wrapOf(task));
    }

    @Override
    public <T> Future<T> submit(Runnable task, T result) {
        return super.submit(TraceRunnable.wrapOf(task), result);
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        return super.submit(TraceCallable.wrapOf(task));
    }
}
