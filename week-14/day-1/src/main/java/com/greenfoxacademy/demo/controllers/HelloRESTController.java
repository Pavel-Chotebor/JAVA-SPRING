package com.greenfoxacademy.demo.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController  // Returning body for all these methods. Single method does't have to be marked with @ResponseBody
public class HelloRESTController {


    // RETURNING TEXT: SOME ID AND CONTENT CREATED BY CONSTRUCTOR IN GREETING CLASS
    @RequestMapping (value = "/greeting")    //setting a path for method (http://localhost:8080/greeting)
    public Greeting greeting () {
        return new Greeting (110,"Hello, World!!");  // creating a new object by constructor in Greeting class
    }


    // RETURNING TEXT: SOME ID AND NAME CREATED BY CONSTRUCTOR, AND WITH GIVING PARAMETERS
    @RequestMapping (value = "/greeting2")
    public Greeting greeting2 (@RequestParam long yourID, @RequestParam String name) {   // parameters setting: http://localhost:8080/greeting?yourID=1&name=Pavel
        return new Greeting(yourID, name);
    }


    // RETURNING TEXT: SOME CONTENT WITH NAME PARAMETER AND WITH SOME COUNTER WHICH CAN COUNT PAGE REFRESHING
    AtomicLong atomicLong = new AtomicLong(0);    // creating a AtomicLong variable - it can by changed dynamically

    @RequestMapping (value = "/greetingCounter")
    public Greeting greetingCounter (@RequestParam String name) {
        return new Greeting (atomicLong.incrementAndGet(), "Hello, my name is " + name);  //atomicLong is increased by one, when reload the page
    }
}