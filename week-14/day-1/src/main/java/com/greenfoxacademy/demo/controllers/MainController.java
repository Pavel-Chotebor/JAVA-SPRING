package com.greenfoxacademy.demo.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller   // class is set as Controller
public class MainController {

    @ResponseBody     // returning body of the method (not any file, like html...)
    @RequestMapping(value = "/hello")
    public String hello() {
        return "hello world";
    }
}
