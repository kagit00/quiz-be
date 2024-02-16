package com.quiz.quizapp.encryption;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 * The type Encryption util.
 */
@Component
public class EncryptionUtil {
    @Value("${app.security.secret-key}")
    private String secretKey;
    private static final Logger logger = LoggerFactory.getLogger(EncryptionUtil.class);

    /**
     * Encrypt string.
     *
     * @param data the data
     * @return the string
     */
    public String encrypt(String data) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CFB/NoPadding");
            byte[] iv = generateRandomIV();
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] encrypted = cipher.doFinal(data.getBytes());
            byte[] combined = new byte[iv.length + encrypted.length];
            System.arraycopy(iv, 0, combined, 0, iv.length);
            System.arraycopy(encrypted, 0, combined, iv.length, encrypted.length);
            return Base64.encodeBase64String(combined);
        } catch (Exception e) {
            logger.error("Error encrypting data: {}", e.getMessage());
            return "";
        }
    }

    /**
     * Decrypt string.
     *
     * @param encryptedData the encrypted data
     * @return the string
     */
    public String decrypt(String encryptedData) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CFB/NoPadding");
            byte[] combined = Base64.decodeBase64(encryptedData);
            byte[] iv = new byte[16]; // Assuming a 16-byte IV
            byte[] encrypted = new byte[combined.length - iv.length];
            System.arraycopy(combined, 0, iv, 0, iv.length);
            System.arraycopy(combined, iv.length, encrypted, 0, encrypted.length);
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] decrypted = cipher.doFinal(encrypted);
            return new String(decrypted);
        } catch (Exception e) {
            logger.error("Error decrypting data: {}", e.getMessage());
            return "";
        }
    }

    private byte[] generateRandomIV() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] iv = new byte[16];
        secureRandom.nextBytes(iv);
        return iv;
    }
}
