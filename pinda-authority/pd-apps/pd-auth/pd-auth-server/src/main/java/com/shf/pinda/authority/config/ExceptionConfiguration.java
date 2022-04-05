package com.shf.pinda.authority.config;

import com.shf.pinda.common.handler.DefaultGlobalExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 全局异常处理的配置类：
 */
@Configuration
@ControllerAdvice(annotations = {RestController.class, Controller.class}) // 对那些类生效
@ResponseBody
public class ExceptionConfiguration extends DefaultGlobalExceptionHandler {
}
