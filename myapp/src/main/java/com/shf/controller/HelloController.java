package com.shf.controller;

import com.shf.log.MyLog;
import com.shf.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {
//    当前实例已经由我们自定义的starter完成了创建
    @Autowired
    private HelloService helloService;

    @MyLog(desc = "sayHello方法")
    @GetMapping("/say")
    public String sayHello() {
        return helloService.sayHello();
    }
}
