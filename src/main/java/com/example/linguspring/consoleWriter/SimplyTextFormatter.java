package com.example.linguspring.consoleWriter;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
//@Profile("simplyText")
public class SimplyTextFormatter implements TextFormatter{
    @Override
    public String format(String message) {
        return message;
    }
}
