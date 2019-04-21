package me.qebs.restdemo.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class ForkJoinPoolMdcWrapper extends ForkJoinPool {
    public ForkJoinPoolMdcWrapper() {
        super();
    }

    public ForkJoinPoolMdcWrapper(int parallelism) {
        super(parallelism);
    }

    public ForkJoinPoolMdcWrapper(int parallelism, ForkJoinWorkerThreadFactory factory,
                                  Thread.UncaughtExceptionHandler handler, boolean asyncMode) {
        super(parallelism, factory, handler, asyncMode);
    }

    @Override
    public void execute(Runnable task) {
        super.execute(TraceRunnable.wrapOf(task));
    }

    @Override
    public <T> ForkJoinTask<T> submit(Runnable task, T result) {
        return super.submit(TraceRunnable.wrapOf(task), result);
    }

    @Override
    public <T> ForkJoinTask<T> submit(Callable<T> task) {
        return super.submit(TraceCallable.wrapOf(task));
    }
}
