package com.example.linguspring.encryption;

public interface Encryption {
    String encrypt(String originalText);
    String decrypt(String encryptedText);
}
