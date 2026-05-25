/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.prog5121_assignment_part2;
import java.util.Random;
import javax.swing.JOptionPane;
import java.io.IOException;
import java.io.FileWriter;

/**
 *
 * @author Student
 */

public class PROG5121_Assignment_part2 {

    // --- Message Storage Arrays ---
    static String[] messageIDs = new String[100];
    static String[] messages = new String[100];
    static String[] phoneNumbers = new String[100];
    static String[] hashes = new String[100];
    static int count = 0;
    static int totalMessagesSent = 0; 
    static int maxAllowedMessages = 0; 

    // --- User Storage Arrays ---
    static String[] registeredUsers = new String[50];
    static String[] registeredPasswords = new String[50];
    static String[] registeredPhones = new String[50];
    static int userCount = 0;

    // Generate 10-digit ID
    public static String generateMessageID() {
        Random rand = new Random();
        long number = (long)(rand.nextDouble() * 10_000_000_000L);
        return String.format("%010d", number);
    }

    // Validate South African phone number format
    public static boolean isValidPhoneNumber(String number) {
        if (number == null) return false;
        return number.matches("^0[6-8][0-9]{8}$");
    }

    /**
     * Example Expected Output Format: 00:0:HITHANKS
     * @param messageID
     * @param message
     * @param currentMessageIndex
     * @return 
     */
    public static String createHash(String messageID, String message, int currentMessageIndex) {
        if (message == null || message.trim().isEmpty()) {
            String shortID = (messageID != null && messageID.length() >= 2) ? messageID.substring(0, 2) : "00";
            return (shortID + ":" + currentMessageIndex + ":EMPTY").toUpperCase();
        }

        // 1. Extract the first two numbers of the message ID
        String firstTwoDigits = messageID.substring(0, 2);

        // 2. Clear out extra spaces and pull words
        String[] words = message.trim().split("\\s+");
        String firstWord = words[0];
        String lastWord = words[words.length - 1];

        // 3. Assemble exactly as requested: ID_DIGITS:INDEX:WORDS (Enforced All Caps)
        String rawHash = firstTwoDigits + ":" + currentMessageIndex + ":" + firstWord + lastWord;
        
        return rawHash.toUpperCase();
    }

    // Helper method to check if a registration username is already taken
    public static boolean isUsernameTaken(String username) {
        for (int i = 0; i < userCount; i++) {
            if (registeredUsers[i].equalsIgnoreCase(username)) {
                return true;
            }
        }
        return false;
    }

    // User Registration Phase
    public static void registerUser() {
        JOptionPane.showMessageDialog(null, "Welcome! Please register your account credentials first.", "Registration", JOptionPane.INFORMATION_MESSAGE);
        
        String username;
        while (true) {
            username = JOptionPane.showInputDialog("Enter Username:");
            if (username == null) {
                JOptionPane.showMessageDialog(null, "Registration cancelled. Exiting application.");
                System.exit(0);
            }
            if (username.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Error: Username cannot be empty!");
                continue;
            }
            if (isUsernameTaken(username)) {
                JOptionPane.showMessageDialog(null, "Error: Username already exists!");
            } else {
                break; 
            }
        }

        String password;
        String passwordRequirements = """
                                      Password Requirements:
                                      - Minimum 8 characters
                                      - At least one Uppercase and Lowercase letter
                                      - At least one number and one special character (@$!%*?&)""";
        
        while (true) {
            password = JOptionPane.showInputDialog(null, passwordRequirements + "\n\nEnter Password:");
            if (password == null) {
                JOptionPane.showMessageDialog(null, "Registration cancelled. Exiting application.");
                System.exit(0);
            }
            
            if (password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")) {
                break; 
            } else {
                JOptionPane.showMessageDialog(null, "[ERROR] Password is too weak!", "Registration Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        String phone;
        while (true) {
            phone = JOptionPane.showInputDialog("Enter South African Cellphone (e.g., 0821234567):");
            if (phone == null) {
                JOptionPane.showMessageDialog(null, "Registration cancelled. Exiting application.");
                System.exit(0);
            }
            
            if (isValidPhoneNumber(phone)) {
                break; 
            } else {
                JOptionPane.showMessageDialog(null, "[ERROR] Invalid format. Must be 10 digits starting with 06, 07, or 08.", "Registration Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        registeredUsers[userCount] = username;
        registeredPasswords[userCount] = password;
        registeredPhones[userCount] = phone;
        userCount++;

        JOptionPane.showMessageDialog(null, "Registration successful! You can now log in.");
    }

    // User Login Phase
    public static boolean loginUser() {
        JOptionPane.showMessageDialog(null, "Please login to access QuickChat.", "Authentication", JOptionPane.INFORMATION_MESSAGE);
        
        String username = JOptionPane.showInputDialog("Enter Username:");
        if (username == null) return false;
        
        String password = JOptionPane.showInputDialog("Enter Password:");
        if (password == null) return false;

        for (int i = 0; i < userCount; i++) {
            if (username.equals(registeredUsers[i]) && password.equals(registeredPasswords[i])) {
                JOptionPane.showMessageDialog(null, "Login Successful! Welcome back, " + username + ".");
                return true;
            }
        }
        
        JOptionPane.showMessageDialog(null, "Invalid Credentials. Access Denied.", "Login Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    // Option 1: Send messages matching system limits
    public static void sendMessages() {
        if (totalMessagesSent >= maxAllowedMessages) {
            JOptionPane.showMessageDialog(null, "You have reached your limit of " + maxAllowedMessages + " messages for this session.");
            return;
        }

        String messageID = generateMessageID();

        String phoneNumber;
        do {
            phoneNumber = JOptionPane.showInputDialog("Enter recipient SA cellphone number:");
            if (phoneNumber == null) return; 
            if (!isValidPhoneNumber(phoneNumber)) {
                JOptionPane.showMessageDialog(null, "Invalid phone number! Must be 10 digits starting with 06, 07, or 08.");
            }
        } while (!isValidPhoneNumber(phoneNumber));

        String message;
        do {
            message = JOptionPane.showInputDialog("Enter message (max 250 characters):");
            if (message == null) return; 
            if (message.length() > 250) {
                JOptionPane.showMessageDialog(null, "Message too long!");
            }
        } while (message.length() > 250);
        
        // Pass 'count' variable to safely track the index position for requirement #3
        String hash = createHash(messageID, message, count);
        
        String[] actions = {"Send", "Disregard", "Store"};
        int action = JOptionPane.showOptionDialog(null, "What do you want to do?", "Message Options",
              JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, actions, actions[0]);
        
        boolean shouldRecord = false;

        switch (action) {
            case 0 -> {
                totalMessagesSent++; 
                displayMessageDetails(messageID, hash, phoneNumber, message);
                JOptionPane.showMessageDialog(null, "Message successfully sent.");
                shouldRecord = true;
            }
            case 1 -> JOptionPane.showMessageDialog(null, "Message Disregarded.");
            case 2 -> {
                 writeMessageToJSON(messageID, hash, phoneNumber, message);
                 JOptionPane.showMessageDialog(null, "Message Stored");
                 shouldRecord = true;
            }
        }
        
        if (shouldRecord && count < 100) {
            messageIDs[count] = messageID;
            messages[count] = message;
            phoneNumbers[count] = phoneNumber;
            hashes[count] = hash;
            count++;
        }
    }

    public static void main(String[] args) {
        
        registerUser();

        boolean loggedIn = false;
        while (!loggedIn) {
            loggedIn = loginUser();
            if (!loggedIn) {
                int retry = JOptionPane.showConfirmDialog(null, "Would you like to try logging in again?", "Login Failed", JOptionPane.YES_NO_OPTION);
                if (retry != JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "Application shutting down.");
                    System.exit(0);
                }
            }
        }

        String limitInput = JOptionPane.showInputDialog("Welcome to QuickChat\nHow many messages do you wish to enter for this session?");
        if (limitInput == null || limitInput.trim().isEmpty()) {
            maxAllowedMessages = 5; 
        } else {
            try {
                maxAllowedMessages = Integer.parseInt(limitInput);
            } catch (NumberFormatException e) {
                maxAllowedMessages = 5;
            }
        }

        int choice = 3;

        do {
            String input = JOptionPane.showInputDialog("""
                                                       Welcome to QuickChat
                                                       
                                                       1.Send Messages
                                                       2.Show recently sent messages
                                                       3.Quit""");
            
            if (input == null) {
                choice = 3; 
            } else {
                try {
                    choice = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    choice = 0; 
                }
            }

            switch (choice) {
                case 1 -> sendMessages();
                case 2 -> JOptionPane.showMessageDialog(null, "Coming Soon."); 
                case 3 -> JOptionPane.showMessageDialog(null, "Goodbye!");
                default -> JOptionPane.showMessageDialog(null, "Invalid option!");
            }

        } while (choice != 3);
    }

    private static void displayMessageDetails(String messageID, String hash, String phoneNumber, String message) {
        String details = """
                         --- MESSAGE DETAILS ---
                         ID: """ + messageID + "\n" +
                         "To: " + phoneNumber + "\n" +
                         "Hash: " + hash + "\n" +
                         "Content: " + message;
        JOptionPane.showMessageDialog(null, details, "Message Sent", JOptionPane.PLAIN_MESSAGE);
    }

    private static void writeMessageToJSON(String messageID, String hash, String phoneNumber, String message) {
        try (FileWriter writer = new FileWriter("messages.json", true)) {
            String jsonFormat = String.format(
                "{\n  \"id\": \"%s\",\n  \"phone\": \"%s\",\n  \"hash\": \"%s\",\n  \"message\": \"%s\"\n}\n",
                messageID, phoneNumber, hash, message.replace("\"", "\\\"")
            );
            writer.write(jsonFormat);
        } catch (IOException e) {
            System.out.println("Error saving JSON log file: " + e.getMessage());
        }
    }
}
