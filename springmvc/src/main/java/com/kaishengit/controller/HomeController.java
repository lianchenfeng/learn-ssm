package com.kaishengit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/helloworld")
    public String helloworld(){
        System.out.println("hello,SpringMVC");
        return "helloworld";
    }

    @RequestMapping("hi")
    public String hi(){
        System.out.println("hi,SpringMVC");
        return "hi";
    }
}
