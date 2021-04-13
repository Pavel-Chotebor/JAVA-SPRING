package com.greenfox.groot.controllers;

import com.greenfox.groot.models.Error;
import com.greenfox.groot.models.Groot;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class MainController {


    @GetMapping("/groot")
    public ResponseEntity<?> grootMethod (@RequestParam(required = false) String text) {
        if (text == null) {
            return new ResponseEntity<>(new Error("I am groot"), HttpStatus.BAD_REQUEST);
        } else  {
            return new ResponseEntity<>(new Groot(text, "I am Groot"), HttpStatus.OK);
        }

    }

}
