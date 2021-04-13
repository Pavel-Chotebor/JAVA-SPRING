package com.DI.utilities.controllers;

import com.DI.utilities.services.UtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@Controller
public class WebController {

    private UtilityService utilityService;

    public WebController(UtilityService utilityService) {
        this.utilityService = utilityService;
    }

    @RequestMapping(value="useful")
    public String showHomePage () {
        return "template1";
    }

    @RequestMapping(value="useful/random")
    public String showRandomColor (Model model) {
        model.addAttribute("backGroundColor", utilityService.randomColor());
        return "template2";
    }

    @RequestMapping(value="useful/email")
    public String checkEmail (Model model, @RequestParam String email) {
        model.addAttribute("email", utilityService.validateEmail(email));
        return "form";
    }



    @RequestMapping(value="useful/caesar-encoder")
    public String caesarEncoder (Model model, @RequestParam String text, Integer number) {
        model.addAttribute("index", utilityService.caesar(text, number));
        return "template3";
    }


    @RequestMapping(value="useful/caesar-decoder")
    public String caesarDecoder (Model model, @RequestParam String text, Integer number) {
        model.addAttribute("index", utilityService.caesar(text, (number * (-1))) ) ;
        return "template3";
    }

}