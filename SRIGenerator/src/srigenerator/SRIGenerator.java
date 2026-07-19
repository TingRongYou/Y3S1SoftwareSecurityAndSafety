/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package srigenerator;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Proof of Concept: Mitigating Malicious Script Injection
 * This program generates a Subresource Integrity (SRI) hash for JavaScript files,
 * ensuring that the browsers reject unauthorized code modifications like the MageCart attack.
 */
public class SRIGenerator {

    public static void main(String[] args) {
        System.out.println("--- BA E-Commerce: Subresource Integrity (SRI) Generator ---\n");
        
        // Simulating the original, legitimate Modenizr.js file content
        String originalJSCode = "function modernizr(){ /* Legitimate BA Code */ }";
        System.out.println("[INFO] Processing original script: modenizr-2.6.2.min.js");
        
        try {
            // Step 1: Generate the SRI Hash using SHA-384
            String sriHash = generateSRIHash(originalJSCode);
            System.out.println("\n[SECURE] Generated SRI Hash: " + sriHash);
            
            // Step 2: Output the secure HTML script tag for the frontend team
            System.out.println("\n[SUCCESS] The frontend development team must use this exact tag in the HTML:");
            System.out.println("<script src=\"js/modernizr-2.6.2.min.js\" ");
            System.out.println("        integrity=\"" + sriHash + "\" ");
            System.out.println("        crossorigin=\"anonymous\"></script>");
            
            System.out.println("\n[ANALYSIS] If attackers inject the 22 lines of MageCart code, the browser will calculate a different hash,");
            System.out.println("detect the mismatch with the 'integrity' attribute and completely block the digital skimmer from running.");
            
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Cryptographic error occured: " + e.getMessage());
        }
        
    }
    
    /**
     * Calculates the SHA-384 hash of the provided content and formats it for SRI.
     */
    public static String generateSRIHash(String content) throws NoSuchAlgorithmException {
        // The W3C standard recommend SHA-384 as a "good-baseline" for SRI Implementations
        MessageDigest digest = MessageDigest.getInstance("SHA-384");
        byte[] hashBytes = digest.digest(content.getBytes());
        
        // Base64 encode the resulting cryptographic hash
        String base64Hash = Base64.getEncoder().encodeToString(hashBytes);
        
        // Prepend the hashing algorithm prefix as mandated by the W3C specification 
        return "sha384-" + base64Hash;
    }
    
}
