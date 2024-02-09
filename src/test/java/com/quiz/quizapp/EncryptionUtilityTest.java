package com.quiz.quizapp;

import com.quiz.quizapp.util.EncryptionUtility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

class EncryptionUtilityTest {
    /**
     * The function tests the encryption and decryption of a text using a secret key.
     */
    @Test
    void encryptAndDecryptText() {
        String encryptedText = EncryptionUtility.encrypt("joy", new SecretKeySpec("passwordpassword".getBytes(StandardCharsets.UTF_8), "AES"));
        String decryptedText = EncryptionUtility.decrypt(encryptedText, new SecretKeySpec("passwordpassword".getBytes(StandardCharsets.UTF_8), "AES"));
        Assertions.assertEquals("joy", decryptedText, "Encryption/decryption working properly.");
    }
}
