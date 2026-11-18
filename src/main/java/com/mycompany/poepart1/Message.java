/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poepart1;

/**
 *
 * @author Student
 */

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Message {
    private final String messageID;
    private final String messageHash;
    private final String recipient;
    private String messageContent;
    private static int messageCounter = 0; 

    // Part 3 Arrays & Lists for dynamic tracking
    private static List<String> sentMessagesList = new ArrayList<>();
    private static List<String> disregardedMessagesList = new ArrayList<>();
    private static List<Message> storedMessagesList = new ArrayList<>();
    private static List<String> messageIDsList = new ArrayList<>();
    private static List<String> messageHashesList = new ArrayList<>();

    // Constructor
    public Message(String recipient, String messageContent) {
        this.recipient = recipient;
        this.messageContent = messageContent;
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();
    }

    // Generates a random 10-digit message ID
    private String generateMessageID() {
        Random random = new Random();
        long id = 1000000000L + (long)(random.nextDouble() * 9000000000L);
        return String.valueOf(id);
    }

    // Checks that the message ID is not more than 10 characters
    public boolean checkMessageID() {
        return messageID != null && messageID.length() <= 10;
    }

    // Ensures recipient cell number is valid (international format or starts with 0, 10-12 chars)
    public String checkRecipientCell() {
        if (recipient != null && (recipient.startsWith("+") || recipient.startsWith("0")) && recipient.length() >= 10 && recipient.length() <= 12) {
            return "Cell phone number successfully captured.";
        }
        return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }

    // Creates and returns the Message Hash
    public String createMessageHash() {
        String firstTwoDigits = (messageID != null && messageID.length() >= 2) ? messageID.substring(0, 2) : "00";
        
        String firstWord = "";
        String lastWord = "";
        
        if (messageContent != null && !messageContent.trim().isEmpty()) {
            String cleanMsg = messageContent.replaceAll("[^a-zA-Z0-9\\s]", "").trim();
            String[] words = cleanMsg.split("\\s+");
            if (words.length > 0) {
                firstWord = words[0];
                lastWord = words[words.length - 1];
            }
        }

        return (firstTwoDigits + ":" + messageCounter + ":" + firstWord + lastWord).toUpperCase();
    }

    // Checks if message is <= 250 characters
    public String checkMessageLength() {
        if (messageContent != null && messageContent.length() <= 250) {
            return "Message ready to send.";
        } else {
            int excess = messageContent.length() - 250;
            return "Message exceeds 250 characters by " + excess + "; please reduce the size.";
        }
    }

    // Handles sending, storing, or discarding options
    public String sentMessage(String choice) {
        if (choice.equals("0")) {
            disregardedMessagesList.add("Recipient: " + recipient + " | Message: " + messageContent);
            return "Press 0 to delete the message";
        } else if (choice.equalsIgnoreCase("store")) {
            storeMessage();
            storedMessagesList.add(this);
            messageIDsList.add(messageID);
            messageHashesList.add(messageHash);
            return "Message successfully stored";
        } else {
            messageCounter++;
            String details = "Message ID: " + messageID + "\n" +
                             "Message Hash: " + messageHash + "\n" +
                             "Recipient: " + recipient + "\n" +
                             "Message: " + messageContent + "\n";
            sentMessagesList.add(details);
            messageIDsList.add(messageID);
            messageHashesList.add(messageHash);
            return "Message successfully sent\n" + details;
        }
    }

    // Store message in a JSON file format named 'messages.json'
    public void storeMessage() {
        String jsonContent = "{\n" +
                "  \"messageID\": \"" + messageID + "\",\n" +
                "  \"messageHash\": \"" + messageHash + "\",\n" +
                "  \"recipient\": \"" + recipient + "\",\n" +
                "  \"messageContent\": \"" + messageContent + "\"\n" +
                "},\n";
        
        try (FileWriter file = new FileWriter("messages.json", true)) {
            file.write(jsonContent);
        } catch (IOException e) {
            System.out.println("Error storing message to JSON file.");
        }
    }

    // ==========================================
    // PART 3: REQUIRED REPORTING & ARRAY METHODS
    // ==========================================

    // Helper to reset static lists between JUnit tests
    public static void clearAllListsForTesting() {
        sentMessagesList.clear();
        disregardedMessagesList.clear();
        storedMessagesList.clear();
        messageIDsList.clear();
        messageHashesList.clear();
        messageCounter = 0;
    }

    // Helper to extract sent message texts for unit testing
    public static List<String> getSentMessageContents() {
        List<String> contents = new ArrayList<>();
        for (String entry : sentMessagesList) {
            for (String line : entry.split("\n")) {
                if (line.startsWith("Message: ")) {
                    contents.add(line.substring("Message: ".length()));
                }
            }
        }
        return contents;
    }

    // a. Display sender and recipient of all stored messages
    public static String displayStoredSendersAndRecipients() {
        if (storedMessagesList.isEmpty()) {
            return "No stored messages available.";
        }
        StringBuilder sb = new StringBuilder("--- Stored Messages (Senders & Recipients) ---\n");
        for (Message m : storedMessagesList) {
            sb.append("Recipient: ").append(m.getRecipient()).append("\n");
        }
        return sb.toString();
    }

    // b. Display the longest stored message
    public static String displayLongestStoredMessage() {
        if (storedMessagesList.isEmpty()) {
            return "No stored messages available.";
        }
        Message longest = storedMessagesList.get(0);
        for (Message m : storedMessagesList) {
            if (m.getMessageContent().length() > longest.getMessageContent().length()) {
                longest = m;
            }
        }
        return longest.getMessageContent();
    }

    // c. Search for a message ID and display corresponding message content
    public static String searchMessageByID(String targetID) {
        for (Message m : storedMessagesList) {
            if (m.getMessageID().equals(targetID)) {
                return m.getMessageContent();
            }
        }
        for (String entry : sentMessagesList) {
            if (entry.contains("Message ID: " + targetID)) {
                for (String line : entry.split("\n")) {
                    if (line.startsWith("Message: ")) {
                        return line.substring("Message: ".length());
                    }
                }
            }
        }
        return "No message found with ID: " + targetID;
    }

    // d. Search for all messages stored or sent for a particular recipient
    public static String searchMessagesByRecipient(String targetRecipient) {
        StringBuilder sb = new StringBuilder();
        for (Message m : storedMessagesList) {
            if (m.getRecipient().equals(targetRecipient)) {
                if (sb.length() > 0) sb.append("\n");
                sb.append(m.getMessageContent());
            }
        }
        for (String entry : sentMessagesList) {
            if (entry.contains("Recipient: " + targetRecipient)) {
                for (String line : entry.split("\n")) {
                    if (line.startsWith("Message: ")) {
                        if (sb.length() > 0) sb.append("\n");
                        sb.append(line.substring("Message: ".length()));
                    }
                }
            }
        }
        if (sb.length() == 0) {
            return "No messages found for recipient: " + targetRecipient;
        }
        return sb.toString();
    }

    // e. Delete a message using the message hash
    public static String deleteMessageByHash(String targetHash) {
        for (int i = 0; i < storedMessagesList.size(); i++) {
            Message m = storedMessagesList.get(i);
            if (m.getMessageHash().equalsIgnoreCase(targetHash)) {
                String deletedContent = m.getMessageContent();
                String cleanContent = deletedContent.endsWith(".") 
                        ? deletedContent.substring(0, deletedContent.length() - 1) 
                        : deletedContent;
                storedMessagesList.remove(i);
                messageHashesList.remove(targetHash);
                return "Message \"" + cleanContent + "\" successfully deleted.";
            }
        }
        return "Message hash not found.";
    }

    // f. Display a report that lists full details of all stored messages
    public static String displayStoredMessagesReport() {
        if (storedMessagesList.isEmpty()) {
            return "No stored messages in the report.";
        }
        StringBuilder sb = new StringBuilder("========================================\n");
        sb.append("        STORED MESSAGES REPORT          \n");
        sb.append("========================================\n");
        for (Message m : storedMessagesList) {
            sb.append("Message ID   : ").append(m.getMessageID()).append("\n");
            sb.append("Message Hash : ").append(m.getMessageHash()).append("\n");
            sb.append("Recipient    : ").append(m.getRecipient()).append("\n");
            sb.append("Content      : ").append(m.getMessageContent()).append("\n");
            sb.append("----------------------------------------\n");
        }
        return sb.toString();
    }

    public static String printMessages() {
        if (sentMessagesList.isEmpty()) {
            return "No messages sent yet.";
        }
        StringBuilder sb = new StringBuilder();
        for (String msg : sentMessagesList) {
            sb.append(msg).append("\n");
        }
        return sb.toString();
    }

    public static int returnTotalMessages() {
        return messageCounter;
    }

    // Getters
    public String getMessageID() { return messageID; }
    public String getMessageHash() { return messageHash; }
    public String getRecipient() { return recipient; }
    public String getMessageContent() { return messageContent; }
}