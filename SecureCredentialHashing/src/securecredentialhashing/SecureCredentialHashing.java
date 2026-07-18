/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package securecredentialhashing;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * Proof of Concept: Mitigating Plain text Credential Storage
 * This program demonstrates the secure hashing of an administrator password
 * using PBK2WithHmacSHA256, strictly adhering to NIST guidelines.
 */
public class SecureCredentialHashing {
    
    // Define security parameters recommended by OWASP and NIST 
    private static final int ITERATIONS = 310000; // High iteration count slows down brute-force attacks
    public static final int KEY_LENGTH = 256; // 256-bit hash length
    public static final int SALT_LENGTH = 16; // 16-byte cryptographic salt

    public static void main(String[] args) {
        System.out.println("--- BA E-Commerce: Secure Credential Hashing Module ---\\n");
        
        // Simulated plaintext administrator password discovered during the 2018 breach
        String plaintextAdminPassword = "AdminPassword123";
        System.out.println("[WARNING] Legacy System State: Password stored as plaintext: " + plaintextAdminPassword);
        
        try {
            
            // Step 1: Generate a cryptographically secure random salt
            byte[] salt = generateSalt();
            String encodedSalt = Base64.getEncoder().encodeToString(salt);
            System.out.println("\n[Secure] Step 1: Generated 16-byte Cryptographic Salt: " + encodedSalt);
            
            // Step 2: Hash the password using PBKDF2
            byte[] hashedPassword = hashPassword(plaintextAdminPassword.toCharArray(), salt);
            String encodedHash = Base64.getEncoder().encodeToString(hashedPassword);
            System.out.println("[SECURE] Step 2: Generated PBKDF2 Hash: " + encodedHash);
            
            // Step 3: Database Storage Simulation
            System.out.println("\n[Success] The database will now store the salt and the hash");
            System.out.println("Even if the database is breached, the attackers cannot reverse the hash to find original password");
            
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            System.err.println("Cryptographic error occured: " + e.getMessage());
        }
    }
    
    /**
     * Generates a cryptographically secure salt to defeat rainbow tables.
     */
    public static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);
        return salt;
    }
    
    /**
     * Hashes the plain text password using PBKDF2 with HMAC-SHA256.
     */
    private static byte[] hashPassword(char[] password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
        // Using PBKDF2WithHmacSHA256 as recommended by standard security frameworks
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        return factory.generateSecret(spec).getEncoded();
    }
    
}
