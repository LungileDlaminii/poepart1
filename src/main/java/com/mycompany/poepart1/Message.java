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
import java.util.Random;

public class Message {
    private final String messageID;
    private final String messageHash;
    private final String recipient;
    private String messageContent;
    private static int messageCounter = 0; 
    private static String allSentMessagesLog = "";

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
            return "Press 0 to delete the message";
        } else if (choice.equalsIgnoreCase("store")) {
            storeMessage();
            return "Message successfully stored";
        } else {
            messageCounter++;
            String details = "Message ID: " + messageID + "\n" +
                             "Message Hash: " + messageHash + "\n" +
                             "Recipient: " + recipient + "\n" +
                             "Message: " + messageContent + "\n";
            allSentMessagesLog += details + "\n";
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

    // Returns all sent messages
    public static String printMessages() {
        if (allSentMessagesLog.isEmpty()) {
            return "No messages sent yet.";
        }
        return allSentMessagesLog;
    }

    // Returns the total number of messages sent
    public static int returnTotalMessages() {
        return messageCounter;
    }

    // Getters
    public String getMessageID() { return messageID; }
    public String getMessageHash() { return messageHash; }
    public String getRecipient() { return recipient; }
    public String getMessageContent() { return messageContent; }
}