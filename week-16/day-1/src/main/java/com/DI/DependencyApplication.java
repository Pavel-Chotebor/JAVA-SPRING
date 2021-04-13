package com.DI;

import com.DI.colors.services.MyColor;
import com.DI.helloWorld.Printer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DependencyApplication implements CommandLineRunner {

    private Printer printer;
    private MyColor myColor;


    //@Autowired
    public DependencyApplication(Printer printer) {
        this.printer = printer;
    }

    @Autowired
    public DependencyApplication (MyColor myColor) {
        this.myColor = myColor;
    }

    public static void main(String[] args) {
        SpringApplication.run(DependencyApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
      //  printer.log("hello");

        myColor.printColor();
    }
}