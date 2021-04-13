package com.greenfoxacademy.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Random;

@Controller
public class WebController1 {

    // GREETING 3:  create a web app which says hello in different languages. Use the @RequestParam annotation to have different font sizes and colors.

    String[] hellos = {"Mirëdita", "Ahalan", "Parev", "Zdravei", "Nei Ho", "Dobrý den", "Ahoj", "Goddag",
            "Goede dag, Hallo", "Hello", "Saluton", "Hei", "Bonjour", "Guten Tag", "Gia'sou", "Aloha", "Shalom",
            "Namaste", "Namaste", "Jó napot", "Halló", "Helló", "Góðan daginn", "Halo", "Aksunai", "Qanuipit",
            "Dia dhuit", "Salve", "Ciao", "Kon-nichiwa", "An-nyong Ha-se-yo", "Salvëte", "Ni hao", "Dzien' dobry",
            "Olá", "Bunã ziua", "Zdravstvuyte", "Hola", "Jambo", "Hujambo", "Hej", "Sa-wat-dee", "Merhaba", "Selam",
            "Vitayu", "Xin chào", "Hylo", "Sut Mae", "Sholem Aleychem", "Sawubona"};

    Random r = new Random();
    int randomHello = r.nextInt(hellos.length);

    @RequestMapping("/web/greeting3")
    public String greeting3(Model model, @RequestParam String fontSize, @RequestParam String fontColor) {
        model.addAttribute("fontSize", fontSize);  // adding a "variable" which will be change in html document by attributeName, and value will be se in parameter
        model.addAttribute("fontColor", fontColor);
        model.addAttribute("content", hellos[randomHello]);
        return "greeting3";    // Returning edited greeting.html in src/main/resources/templates. This file already exist.
    }
}