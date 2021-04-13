package com.greenfox.restapi.services;


import com.greenfox.restapi.models.InputNumber;
import org.springframework.stereotype.Service;

@Service
public class DoUntilService {

    public Integer recognizeMathOperationAndCount (String action, InputNumber inputNumber) {

        if (action.equals("sum")) {
            return sum(inputNumber);

        } else if (action.equals("factor")) {
            return factor(inputNumber);
        } return null;
    }


    // dodelat - nefunguje!
    public Integer factor (InputNumber inputNumber) {

        Integer result = 1;
        for (int i = 1; i <= inputNumber.getUntil(); i++) {
            result = result * i;
        }
        return result;
    }





    public Integer sum (InputNumber inputNumber) {

        Integer result = 0;
            for (int i = 0; i <= inputNumber.getUntil(); i++ ) {
                result += i;
            }
         return result;
    }
}
