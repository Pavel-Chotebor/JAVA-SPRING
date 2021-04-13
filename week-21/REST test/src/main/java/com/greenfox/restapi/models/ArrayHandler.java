package com.greenfox.restapi.models;

import org.springframework.stereotype.Service;

@Service
public class ArrayHandler {

    private String what;
    private int[] numbers;



    public ArrayHandler(String what, int[] numbers) {
        this.numbers = numbers;
    }


    public ArrayHandler() {
    }

    public ArrayHandler(int[] numbers) {
        this.numbers = numbers;
    }

    public String getWhat() {
        return what;
    }

    public void setWhat(String what) {
        this.what = what;
    }

    public int[] getNumbers() {
        return numbers;
    }

    public void setNumbers(int[] numbers) {
        this.numbers = numbers;

    }

}
