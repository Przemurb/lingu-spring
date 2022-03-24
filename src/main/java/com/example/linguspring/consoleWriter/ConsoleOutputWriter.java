package com.example.linguspring.consoleWriter;

import org.springframework.stereotype.Service;

@Service
public class ConsoleOutputWriter {
    TextFormatter textFormatter;

    public ConsoleOutputWriter(TextFormatter textFormatter) {
        this.textFormatter = textFormatter;
    }
    public void print(String message) {
        String text = textFormatter.format(message);
        System.out.println(text);
    }
}
