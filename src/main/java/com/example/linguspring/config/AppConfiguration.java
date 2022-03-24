package com.example.linguspring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
    private final String filename;

    public AppConfiguration(@Value("${app.filename}") String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }
}
