package com.springcoreadvanced.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringAdvancedController {

    @RequestMapping("/hello")
    public void helloWorld(){
        System.out.println("Hello World");
    }


}
