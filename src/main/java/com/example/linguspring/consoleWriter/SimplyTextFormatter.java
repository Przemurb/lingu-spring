package com.example.linguspring.consoleWriter;

import org.springframework.stereotype.Service;

@Service
//@Profile("SIMPLY")
public class SimplyTextFormatter implements TextFormatter{
    @Override
    public String format(String message) {
        return message;
    }
}
