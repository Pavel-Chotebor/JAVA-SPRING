package com.DI.colors.models;

import com.DI.colors.services.MyColor;

public class YellowColor implements MyColor {
    @Override
    public void printColor() {
        System.out.println("It is yellow in color...");
    }
}
