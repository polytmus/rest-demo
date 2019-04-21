package me.qebs.restdemo.controller;

import lombok.extern.slf4j.Slf4j;
import me.qebs.restdemo.async.AsyncTask;
import me.qebs.restdemo.utils.ServletUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {


    @Resource
    private AsyncTask asyncTask;

    @GetMapping("/get")
    public String get(@RequestBody String bodyContent) {
        return "Server Accept " + bodyContent;
    }

    @GetMapping("id/{id}")
    public String get(@PathVariable("id") Integer id) {
        log.info("{},in sync process .", ServletUtils.getRequest().getServletPath());
        asyncTask.asyncSayHello();
        return "You Get " + id;
    }
}
