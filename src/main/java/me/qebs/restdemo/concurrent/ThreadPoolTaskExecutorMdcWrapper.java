package me.qebs.restdemo.concurrent;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class ThreadPoolTaskExecutorMdcWrapper extends ThreadPoolTaskExecutor {
    @Override
    public void execute(Runnable task) {
        super.execute(TraceRunnable.wrapOf(task));
    }

    @Override
    public void execute(Runnable task, long startTimeout) {
        super.execute(TraceRunnable.wrapOf(task), startTimeout);
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        return super.submit(TraceCallable.wrapOf(task));
    }

    @Override
    public Future<?> submit(Runnable task) {
        return super.submit(TraceRunnable.wrapOf(task));
    }

    @Override
    public ListenableFuture<?> submitListenable(Runnable task) {
        return super.submitListenable(TraceRunnable.wrapOf(task));
    }

    @Override
    public <T> ListenableFuture<T> submitListenable(Callable<T> task) {
        return super.submitListenable(TraceCallable.wrapOf(task));
    }
}
