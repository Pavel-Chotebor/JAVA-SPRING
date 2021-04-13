package com.DI.colors.models;

import com.DI.colors.services.MyColor;


public class BlueColor implements MyColor {
    @Override
    public void printColor() {
        System.out.println("It is blue in color...");
    }
}
