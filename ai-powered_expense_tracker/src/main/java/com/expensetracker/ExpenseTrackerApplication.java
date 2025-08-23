package com.expensetracker;

import com.expensetracker.controller.ControllerOfAllApp;

public class ExpenseTrackerApplication {
    public static void main(String[] args) {
        ControllerOfAllApp controllerOfAllApp = new ControllerOfAllApp();
        controllerOfAllApp.start();
    }
}
