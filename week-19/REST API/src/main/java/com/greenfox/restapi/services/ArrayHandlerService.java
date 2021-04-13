package com.greenfox.restapi.services;

import com.greenfox.restapi.models.ArrayHandler;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ArrayHandlerService {

    public Integer recognizeOperationReturningSimpleIntAndCount(ArrayHandler arrayHandler) {
        int result = 0;

        if (arrayHandler.getWhat().equals("sum")) {
            return result = sumOfElementsInArray(arrayHandler);

        } else if (arrayHandler.getWhat().equals("multiply")) {
            return result = multiplyingElementsInArray(arrayHandler);
        }  return null;
    }



    public int sumOfElementsInArray(ArrayHandler arrayHandler) {
        int result =0;
        for (int i=0; i< arrayHandler.getNumbers().length; i++) {
            result += arrayHandler.getNumbers()[i];
        } return result;
    }


    public int multiplyingElementsInArray(ArrayHandler arrayHandler) {
        int result =1;
        for (int number : arrayHandler.getNumbers()) {
            result *=number;
        } return result;
    }


    public int[] doublingElementsInArray(int[] arrayInput) {
        return Arrays.stream(arrayInput)
                .map( i -> i*2)
                .toArray();
    }
}
