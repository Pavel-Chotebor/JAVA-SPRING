package com.greenfoxacademy.demo.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.concurrent.atomic.AtomicLong;


@Controller
public class HelloWebController {

/*
    GREETING 4:
    Write a program that prints out numbers. But for multiples of three print “Fizz” instead of the number,
    for multiples of five print “Buzz” and for the multiples of seven print “Woof”.
    For numbers which are multiples of both numbers use two keywords and for numbers which are multiples of the three numbers use three keywords.

    If one keyword is printed out, use 24px sized font.
    If two keyword is printed out, use 48px sized font.
    If three keyword is printed out, use 72px sized font.
    Example when the site was loaded 3 times, your output should look like:
    Hello! This site was loaded Fizz times since last server start

 */


    private AtomicLong counter = new AtomicLong(0);
    private int fontSize = 0;

    @RequestMapping("/web/greeting4")
    public String greeting4(Model model) {

        counter.incrementAndGet();

        model.addAttribute("fizzBuzzWoofIndex",  valueFilteringByDividing((int) counter.get()));
        model.addAttribute("fontSize", fontSize);
        return "greeting2";
    }


    public String valueFilteringByDividing (int number) {

        String fizz = "Fizz";
        String buzz = "Buzz";
        String woof = "Woof";
        String numberToString = Integer.toString(number);

        if (number % 3 == 0 && number % 5 == 0 && number % 7 == 0) {
            fontSize = 72;
            return fizz + buzz + woof;
        }

        if ((number % 3 == 0) && (number % 5 == 0)) {
            fontSize = 48;
            return fizz + buzz;

        } else if ((number % 7 == 0) && (number % 5 == 0)) {
            fontSize = 48;
            return buzz + woof;

        } else if ((number % 7 == 0) && (number % 3 == 0)) {
            fontSize = 48;
            return fizz + woof;

        } else if (number % 3 == 0) {
            fontSize = 24;
            return fizz;

        } else if (number % 5 == 0) {
            fontSize = 24;
            return buzz;

        } else if (number % 7 == 0) {
            fontSize = 24;
            return woof;

        } else fontSize = 15;         // Some default value for readable text. So for another numbers which are not divided by 3, 5, 7 print this font size.
            return numberToString;
    }
}
