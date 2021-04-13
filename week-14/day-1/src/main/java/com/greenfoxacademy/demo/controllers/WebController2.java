package com.greenfoxacademy.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.atomic.AtomicLong;

@Controller
public class WebController2 {

    //GREETING 2:      Counting page loading  ->  output: Hello, Your name! This site was loaded 3 times since last server start.

   AtomicLong htmlwebCounter = new AtomicLong(0);

   @RequestMapping("/web/greeting2")
   public String greeting2(Model model, @RequestParam String name) {
        model.addAttribute("name", name);
        model.addAttribute("counter", htmlwebCounter.incrementAndGet());
       return "greeting";
   }
}
