package com.example.mobile_banking.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Component
public class KeyUtil {

    @Value("${access-token.private}")
    private String accessTokenPrivateKeyPath;

    @Value("${access-token.public}")
    private String accessTokenPublicKeyPath;

    @Value("${refresh-token.private}")
    private String refreshTokenPrivateKeyPath;

    @Value("${refresh-token.public}")
    private String refreshTokenPublicKeyPath;

    /**
     * Generates a new RSA key pair and saves them to specified file paths.
     *
     * @param privateKeyPath Path to save the private key.
     * @param publicKeyPath  Path to save the public key.
     */
    public void generateAndSaveKeys(String privateKeyPath, String publicKeyPath) throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048); // Key size
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        // Save private key
        saveKeyToFile(privateKeyPath, keyPair.getPrivate().getEncoded());

        // Save public key
        saveKeyToFile(publicKeyPath, keyPair.getPublic().getEncoded());
    }

    /**
     * Loads a private key from a file.
     */
    public PrivateKey loadPrivateKey(String privateKeyPath) throws Exception {
        byte[] keyBytes = Files.readAllBytes(Paths.get(privateKeyPath));
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(spec);
    }

    /**
     * Loads a public key from a file.
     */
    public PublicKey loadPublicKey(String publicKeyPath) throws Exception {
        byte[] keyBytes = Files.readAllBytes(Paths.get(publicKeyPath));
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(spec);
    }

    /**
     * Saves a key to a file in Base64 format.
     */
    private void saveKeyToFile(String path, byte[] keyBytes) throws IOException {
        String encodedKey = Base64.getEncoder().encodeToString(keyBytes);
        Files.write(Paths.get(path), encodedKey.getBytes());
    }
}
