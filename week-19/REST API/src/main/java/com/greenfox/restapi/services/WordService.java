package com.greenfox.restapi.services;

import org.springframework.stereotype.Service;

@Service
public class WordService {

    public String appendA (String word) {
        //String finalWord = word + "a";
        //return finalWord;
        return word + "a";
    }
}
