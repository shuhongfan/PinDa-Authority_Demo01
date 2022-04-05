package com.shf.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 自动配置类
 */
@Configuration
@EnableSwagger2
public class SwaggerAutoConfiguration {
    ApiInfo apiInfo = new ApiInfoBuilder()
            .title("我的接口文档")
            .contact(
                    new Contact(
                            "shuhongfan",
                            "http://www.baidu.com",
                            "shuhongfan@foxmail.com"
                    )
            )
            .version("V1.0")
            .description("接口文档描述")
            .build();

    @Bean
    public Docket createRestApi() {
//        docker对象用于封装接口文档相关信息
        Docket docket =
                new Docket(DocumentationType.SWAGGER_2)
                        .apiInfo(apiInfo)
                        .groupName("用户接口组")
                        .select()
                        .apis(RequestHandlerSelectors.basePackage("com.shf.swagger.controller.user"))//为当前包路径
                        .build();
        return docket;
    }

    @Bean
    public Docket createRestApi2() {
        Docket docket =
                new Docket(DocumentationType.SWAGGER_2)
                        .apiInfo(apiInfo)
                        .groupName("菜单接口组")
                        .select()
                        .apis(RequestHandlerSelectors.basePackage("com.shf.swagger.controller.menu"))//为当前包路径
                        .build();
        return docket;
    }

    @Bean
    public Docket createRestApi3() {
        Docket docket =
                new Docket(DocumentationType.SWAGGER_2)
                        .apiInfo(apiInfo)
                        .select()
                        .apis(RequestHandlerSelectors.basePackage("com.shf.swagger.controller"))//为当前包路径
                        .build();
        return docket;
    }

}
