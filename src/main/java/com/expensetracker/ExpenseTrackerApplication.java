package com.expensetracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class ExpenseTrackerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExpenseTrackerApplication.class, args);
    }
}
