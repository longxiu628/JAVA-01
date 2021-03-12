package pers.peixinyi.work.tcc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.peixinyi.work.tcc.service.HelloService;

/**
 * @author: peixinyi
 * @version: V1.0.0.0
 * @date: 2021-03-11 13:40
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    HelloService helloService;

    @GetMapping
    public void Hello(String hello, int value) {
        System.out.println(String.format("%s - %d", hello, value));
        helloService.say(hello, value);
    }
}
