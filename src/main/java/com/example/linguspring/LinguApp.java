package com.example.linguspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Random;
import java.util.Scanner;

@SpringBootApplication
public class LinguApp {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(LinguApp.class);
        LinguController bean = context.getBean(LinguController.class);
        bean.mainLoop();
    }
    @Bean
    public Random random(){
        return new Random();
    }
    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }
}
