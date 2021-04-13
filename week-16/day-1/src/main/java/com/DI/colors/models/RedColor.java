package com.DI.colors.models;

import com.DI.colors.services.MyColor;
import org.springframework.stereotype.Service;

@Service
public class RedColor implements MyColor {

    @Override
    public void printColor() {
        System.out.println("It is red in color...");
    }
}
