package me.qebs.restdemo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/get")
    public String get(@RequestBody String bodyContent) {
        return "Server Accept " + bodyContent;
    }

    @GetMapping("id/{id}")
    public String get(@PathVariable("id") Integer id) {
        return "You Get " + id;
    }
}
