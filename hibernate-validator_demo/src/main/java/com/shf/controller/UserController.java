package com.shf.controller;

import com.shf.config.EnableFormValidator;
import com.shf.entity.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/user")
@Validated // 开启校验
public class UserController {
//    简单类型的校验
    @GetMapping("/delete")
    public String delete(@NotNull(message = "用户id不能为空") Integer id){
        System.out.println("delete...");
        return "delete success";
    }

//    对象属性校验
    @GetMapping("/save")
    public String save(@Validated User user){
        System.out.println("save...");
        return "save success";
    }
}
