package com.hf.mybootDbJdbc.rest;

 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;
 import springfox.documentation.builders.ApiInfoBuilder;
 import springfox.documentation.builders.PathSelectors;
 import springfox.documentation.builders.RequestHandlerSelectors;
 import springfox.documentation.service.ApiInfo;
 import springfox.documentation.spi.DocumentationType;
 import springfox.documentation.spring.web.plugins.Docket;
 import springfox.documentation.swagger2.annotations.EnableSwagger2;

 @Configuration
 @EnableSwagger2
 public class Swagger2 {
 @Bean
 public Docket createRestApi() {
 return new Docket(DocumentationType.SWAGGER_2)
 .apiInfo(apiInfo())
 .select()
 .apis(RequestHandlerSelectors.basePackage("com.hf"))
 .paths(PathSelectors.any())
 .build();
 }

 private ApiInfo apiInfo() {
 return new ApiInfoBuilder()
 .title("使用jdbcTemplate的增删该查")
 .description("第一个jdbcTemplate")
 .termsOfServiceUrl("http://blog.didispace.com/")
 .contact("LevineHua")
 .version("1.0")
 .build();
 }
 }
