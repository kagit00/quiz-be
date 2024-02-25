package com.quiz.quizapp.encryption;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * The type Encryption utility test.
 */
class EncryptionUtilityTest {
    private final EncryptionUtil encryptionUtil = new EncryptionUtil();

    /**
     * Test encryption decryption.
     */
    @Test
    void testEncryptionDecryption() {
        String originalText = "demo";
        String encryptedText = encryptionUtil.encrypt(originalText, "uzbukuzbukuzbuku");
        String decryptedText = encryptionUtil.decrypt(encryptedText, "uzbukuzbukuzbuku");
        Assertions.assertEquals(originalText, decryptedText, "Encryption/Decryption works fine");
    }
}
