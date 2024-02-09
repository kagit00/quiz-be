package com.quiz.quizapp.util;

import com.quiz.quizapp.exception.InternalServerErrorException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public final class EncryptionUtility {
    // The `private EncryptionUtility()` is a private constructor in the `EncryptionUtility` class. It
    // is used to prevent the instantiation of the `EncryptionUtility` class. By throwing an
    // `UnsupportedOperationException`, it ensures that no instance of the `EncryptionUtility` class
    // can be created. This is commonly done in utility classes where all methods are static and there
    // is no need to create an instance of the class.
    private EncryptionUtility() {
        throw new UnsupportedOperationException("Not supported");
    }

    /**
     * The encrypt function takes a plain text and a secret key as input, and uses the
     * AES/GCM/NoPadding cipher to encrypt the plain text using the secret key, returning the encrypted
     * text.
     * 
     * @param plainText The `plainText` parameter is the text that you want to encrypt. It is the
     * original message or data that you want to protect and keep confidential.
     * @param secretKey The `secretKey` parameter is an instance of the `SecretKey` class, which
     * represents a secret key used for encryption and decryption. It is typically generated using a
     * key generation algorithm, such as AES.
     * @return The encrypt method returns a string that consists of the Base64 encoded IV
     * (Initialization Vector) concatenated with a colon ":" and the Base64 encoded encrypted bytes.
     */
    public static String encrypt(String plainText, SecretKey secretKey) {
        try {
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            byte[] iv = generateIV();
            GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(128, iv);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, gcmParameterSpec);

            byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(iv) + ":" + Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    /**
     * The function decrypts an encrypted text using a secret key and returns the decrypted text.
     * 
     * @param encryptedText The encrypted text that needs to be decrypted. It is in the format
     * "IV:encryptedBytes", where IV is the initialization vector used for encryption and
     * encryptedBytes is the actual encrypted data.
     * @param secretKey The `secretKey` parameter is an instance of the `SecretKey` class, which
     * represents a secret key used for encryption and decryption. It is typically generated using a
     * key generation algorithm, such as AES or DES. The `SecretKey` class is part of the Java
     * Cryptography Architecture (J
     * @return The method is returning a decrypted string.
     */
    public static String decrypt(String encryptedText, SecretKey secretKey) {
        try {
            String[] parts = encryptedText.split(":");
            byte[] iv = Base64.getDecoder().decode(parts[0]);
            byte[] encryptedBytes = Base64.getDecoder().decode(parts[1]);

            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(128, iv);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, gcmParameterSpec);

            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            return new String(decryptedBytes, StandardCharsets.UTF_8);
        }  catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    /**
     * The function generates a 12-byte array to be used as an initialization vector (IV).
     * 
     * @return A byte array of size 12 is being returned.
     */
    private static byte[] generateIV() {
        return new byte[12];
    }
}
