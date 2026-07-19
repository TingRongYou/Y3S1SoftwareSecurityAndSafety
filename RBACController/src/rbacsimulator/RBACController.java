/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package rbacsimulator;

/**
 * Proof of Concept: Mitigating privilege Escalation
 * This program simulates a secure Role-Based Access Control (RBAC) system.
 * It enforces the Principle of Least Privilege (PoLP) by ensuring that
 * third-party accounts cannot execute critical system modifications.
 */
public class RBACController {
    
    // Step 1: Define strict system roles using Enum
    public enum Role {
        DOMAIN_ADMIN,
        STANDARD_EMPLOYEE,
        THIRD_PARTY_VENDOR // e.g., Swissport account
    }
    
    // Step 2: Define the User object
    public static class User {
        private final String username;
        private final Role role;
        
        public User(String username, Role role) {
            this.username = username;
            this.role = role;
        }
        
        public String getUsername() { return this.username; }
        public Role getRole() { return this.role; }
    }
    
    public static void main(String[] args) {
        System.out.println("--- BA Network: RBAC Authorization Controller ---\n");
        
        // Simulating the compromised third-party account and a legitimate BA admin
        User compromisedVendor = new User("swissport_user_99", Role.THIRD_PARTY_VENDOR);
        User authorizedAdmin = new User("ba_sysadmin_01", Role.DOMAIN_ADMIN);
        
        String targetResource = "modenizr-2.6.2.min.js";
        
        // Scenario A: The attacker attempts to modify the file using the compromised vendor account
        attemptFileModification(compromisedVendor, targetResource);
        
        System.out.println("---------------------------------------------------");
        
        // Scenario B: A legitimate internal admin modifies the file
        attemptFileModification(authorizedAdmin, targetResource);
    }
    
    /**
     * Centralized authorization method enforcing the Principe of Least Privilege (PoLP).
     */
    public static void attemptFileModification(User user, String filename) {
        System.out.println("[AUTH REQUEST] User: '" + user.getUsername() + "'");
        System.out.println("[AUTH REQUEST] Role: [" + user.getRole() + "]");
        System.out.println("[AUTH REQUEST] Action: WRITE/MODIFY access to " + filename + "\n");
        
        // Step 3: Strict privilege validation
        if (user.getRole() == Role.DOMAIN_ADMIN) {
            System.out.println("[SUCCESS] Authorization Granted.");
            System.out.println("-> Action executed: " + filename + " updated securely.");
        } else {
            System.out.println("[DENIED] Security Exception: Privilege Escalation Prevented.");
            System.out.println("-> Principle of Least Privilege (PoLP) enforced.");
            System.out.println("-> Account flagged for suspicious lateral movement attempt.");
        }
        System.out.println("");
    }
    
}
