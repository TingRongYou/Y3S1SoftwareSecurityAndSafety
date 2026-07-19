/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package mfasimulator;

import java.security.SecureRandom;
import java.util.Scanner;

/**
 * Proof of Concept: Mitigating Remote Access Vulnerabilities
 * This program simulates a secure Multi-Factor Authentication (MFA) backend.
 * It generates a cryptographically secure 6-digit OTP, enforces time expiration, 
 * and limits verification attempts to prevent brute-force attacks.
 */
public class MFASimulator {
    
    // Security Configuration
    private static final int OTP_LENGTH = 6;
    private static final int MAX_ATTEMPTS = 3;
    private static final long EXPIRATION_TIME_MS = 60000; // 60 second Time-to-Live

    public static void main(String[] args) {
        System.out.println("--- BA Citrix Gateway: MFA Enforcement ---\n");
        System.out.println("[LOG] Primary Authentication (Username/Password) successful");
        System.out.println("[LOG] Initiating secondary authentication factor...\n");
        
        // Step 1: Generate Cryptographically Secure OTP
        String generateOtp = generateSecureOTP();
        long creationTime = System.currentTimeMillis();
        
        // Simulate sending OTP to employee's registered device
        System.out.println(">> [SIMULATION] SMS sent to registered device: ******5629");
        System.out.println(">> [SIMULATION] Message: Your BA secure access code is " + generateOtp + ". It expires in 60 seconds.\n");
        
        // Step 2: Verification Process
        verifyOTP(generateOtp, creationTime);
    }
    
    /**
     * Generates a 6-digit OTP using SecureRandom to ensure high entropy.
     */
    public static String generateSecureOTP() {
        SecureRandom random = new SecureRandom();
        StringBuilder otp = new StringBuilder();
        
        for (int i = 0; i < OTP_LENGTH; i++) {
            otp.append(random.nextInt(10));
        }
        
        return otp.toString();
    }
    
    /**
     * Handles user input and applies security checks (matching, expiration, brute-force limits).
     */
    public static void verifyOTP(String actualOtp, long creationTime) {
        Scanner scanner = new Scanner(System.in);
        int attempts = 0;
        
        while (attempts < MAX_ATTEMPTS) {
            System.out.println("Enter your 6-digit MFA code: ");
            String userInput = scanner.nextLine();
            long currentTime = System.currentTimeMillis();
            
            // Security Check 1: Time Expiration (Prevents Replay Attacks)
            if (currentTime - creationTime > EXPIRATION_TIME_MS) {
                System.out.println("\n[DENIED] Security Exception: OTP has expired. Please request a new code.");
                return; // Terminate access
            }
            
            // Security Check 2: Value Matching
            if (userInput.equals(actualOtp)) {
                System.out.println("\n[SUCCESS] MFA Verification passed. Remote access granted to internal network.");
                return; // Grant access
            } else {
                attempts++;
                System.out.println("[ERROR] Invalid code. Attempts remaining: " + (MAX_ATTEMPTS - attempts) + "\n");
            }
            
        }

        // Security Check 3: Brute-Force Prevention
        System.out.println("[DENIED] Security Exception: Maximum MFA attempts exceeded. Account temporarily locked.");
    }
}
