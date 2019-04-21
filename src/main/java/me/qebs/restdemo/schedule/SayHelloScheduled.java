package me.qebs.restdemo.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SayHelloScheduled {

    @Scheduled(cron = "0/10 * * * * ?")
    public void sayHello() {
        log.info("Hello,I'm scheduled...");
    }
}
