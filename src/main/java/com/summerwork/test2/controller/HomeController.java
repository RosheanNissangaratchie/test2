package com.summerwork.test2.controller;

import com.summerwork.test2.services.MyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);
    private final MyService myService;

    public HomeController(MyService myService) {
        this.myService = myService;
    }


    @GetMapping("/")
    public String index(){
        return "index.html";
    }

    @GetMapping("/greet")
    public String greet(){
        String greeting = myService.getHello();
        log.info("Generated greeting: {}", greeting);
        return greeting;
    }
}
