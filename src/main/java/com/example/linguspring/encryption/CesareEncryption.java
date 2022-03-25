package com.example.linguspring.encryption;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("cesar")
public class CesareEncryption implements Encryption {
    private static final int ENCRYPTING_KEY = 3;

    @Override
    public String encrypt(String originalText) {
        char[] chars = originalText.toCharArray();
        for (int i = 0; i < chars.length; i++){
            chars[i] += ENCRYPTING_KEY;
        }
        return String.valueOf(chars);
    }

    @Override
    public String decrypt(String encryptedText) {
        char[] chars = encryptedText.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] -= ENCRYPTING_KEY;
        }
        return String.valueOf(chars);
    }
}
