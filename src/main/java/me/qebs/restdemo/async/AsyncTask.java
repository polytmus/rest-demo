package me.qebs.restdemo.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AsyncTask {

    @Async
    public void asyncSayHello() {
        log.info("进入异步线程方法");
        System.out.println("Hello");
    }
}
