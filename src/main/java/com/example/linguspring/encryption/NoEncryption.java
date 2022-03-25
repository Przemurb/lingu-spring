package com.example.linguspring.encryption;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class NoEncryption implements Encryption{
    @Override
    public String encrypt(String originalText) {
        return originalText;
    }

    @Override
    public String decrypt(String encryptedText) {
        return encryptedText;
    }
}
