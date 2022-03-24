package com.example.linguspring;

public class Entry {
    private final String original;
    private String translation;

    public Entry(String original, String translation) {
        this.original = original;
        this.translation = translation;
    }

    public String getOriginal() {
        return original;
    }

    public String getTranslation() {
        return translation;
    }

    @Override
    public String toString() {
        return original + ";" + translation;
    }
}
