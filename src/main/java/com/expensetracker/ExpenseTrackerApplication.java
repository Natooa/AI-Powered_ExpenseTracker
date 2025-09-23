package com.expensetracker;

import com.expensetracker.controller.ControllerOfAllApp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExpenseTrackerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExpenseTrackerApplication.class, args);
//        ControllerOfAllApp controllerOfAllApp = new ControllerOfAllApp();
//        controllerOfAllApp.start();
    }
}
