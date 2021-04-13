package com.greenfox.restapi.services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class SithTextServiceImpl implements SithTextService {

    @Override
    public String convector(String sentence) {

        String finalConvertedSentence = "";
        String[] randomYodaSentence = {"Err..err.err.", "Arrgh.","Uhmm.", "Hmmm." };
        List<String> newOrderList = new ArrayList<>();
        List<String> words = Arrays.stream(sentence.split(" ")).map(String::toLowerCase).collect(Collectors.toList());
        Random random = new Random();
        int randomSentence = random.nextInt(randomYodaSentence.length);

        if (words.size() % 2 != 0) {
            words.add("tempWordForMakingSizeEven");
        }
            for (int i = 1; i < words.size(); i += 2) {
                    newOrderList.add(words.get(i));
            }
            for (int i = 0; i < words.size(); i += 2) {
                newOrderList.add(i + 1, words.get(i));
            }

            newOrderList.set(0, newOrderList.get(0).substring(0,1).toUpperCase()  +newOrderList.get(0).substring(1));
            newOrderList.set(newOrderList.size() -1, newOrderList.get(newOrderList.size() -1) + ".");
            newOrderList.remove("tempWordForMakingSizeEven");

            newOrderList.add(randomYodaSentence[randomSentence]);

        for (int i = 0; i< newOrderList.size(); i++) {
            finalConvertedSentence += newOrderList.get(i) + " ";
        }

            return finalConvertedSentence;
    }
}

