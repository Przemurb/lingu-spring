package com.example.linguspring.consoleWriter;

import com.example.linguspring.config.AppConfiguration;
import org.springframework.stereotype.Service;

@Service
public class ConsoleOutputWriter {
    private final TextFormatter textFormatter;
    private final AppConfiguration config;

    public ConsoleOutputWriter(TextFormatter textFormatter, AppConfiguration config) {
        this.textFormatter = textFormatter;
        this.config = config;
    }
    public void print(String message) {
        String text = textFormatter.format(message);
        System.out.println(text);
    }
}
