package com.example.linguspring.consoleWriter;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("upperText")
public class UpperCaseTextFormatter implements TextFormatter{
    @Override
    public String format(String message) {
        return message.toUpperCase();
    }


}
