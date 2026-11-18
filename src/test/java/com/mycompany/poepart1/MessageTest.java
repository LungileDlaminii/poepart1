/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.poepart1;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Student
 */
public class MessageTest { 
    private Message validMessage;
    private Message invalidMessage;

    @BeforeEach
    public void setUp() {
        // Reset static arrays/lists before every test so tests do not interfere with each other
        Message.clearAllListsForTesting();
        
        // Valid message and invalid message instances for Part 2 testing
        validMessage = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?");
        invalidMessage = new Message("12345", "A".repeat(255));
    }

    @Test
    public void testMessageLength_Success() {
        assertEquals("Message ready to send.", validMessage.checkMessageLength());
    }

    @Test
    public void testMessageLength_Failure() {
        assertTrue(invalidMessage.checkMessageLength().contains("Message exceeds 250 characters"));
    }

    @Test
    public void testRecipientCell_Success() {
        assertEquals("Cell phone number successfully captured.", validMessage.checkRecipientCell());
    }

    @Test
    public void testRecipientCell_Failure() {
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", invalidMessage.checkRecipientCell());
    }

    @Test
    public void testMessageID_Creation() {
        assertTrue(validMessage.checkMessageID());
        assertNotNull(validMessage.getMessageID());
    }

    @Test
    public void testMessageHash_Format() {
        String hash = validMessage.getMessageHash();
        assertNotNull(hash);
        assertTrue(hash.contains("HITONIGHT"));
    }

    // ==========================================
    // PART 3 UNIT TESTS 
    // ==========================================

    @Test
    public void testSentMessagesArrayPopulated() {
        Message msg1 = new Message("+27834557896", "Did you get the cake?");
        Message msg4 = new Message("0838884567", "It is dinner time !");
        
        msg1.sentMessage("send");
        msg4.sentMessage("send");

        List<String> expected = Arrays.asList("Did you get the cake?", "It is dinner time !");
        assertEquals(expected, Message.getSentMessageContents());
    }

    @Test
    public void testDisplayLongestMessage() {
        Message msg1 = new Message("+27834557896", "Did you get the cake?");
        Message msg2 = new Message("+27838884567", "Where are you? You are late! I have asked you to be on time.");
        Message msg3 = new Message("+27834484567", "Yohoooo, I am at your gate.");
        Message msg4 = new Message("0838884567", "It is dinner time !");
        
        msg1.sentMessage("store");
        msg2.sentMessage("store");
        msg3.sentMessage("0");
        msg4.sentMessage("store");

        assertEquals(
            "Where are you? You are late! I have asked you to be on time.",
            Message.displayLongestStoredMessage()
        );
    }

    @Test
    public void testSearchForMessageID() {
        Message msg4 = new Message("0838884567", "It is dinner time !");
        msg4.sentMessage("send");
        
        assertEquals("It is dinner time !", Message.searchMessageByID(msg4.getMessageID()));
    }

    @Test
    public void testSearchMessagesByRecipient() {
        Message msg2 = new Message("+27838884567", "Where are you? You are late! I have asked you to be on time.");
        Message msg5 = new Message("+27838884567", "Ok, I am leaving without you.");
        
        msg2.sentMessage("store");
        msg5.sentMessage("store");

        String expected = "Where are you? You are late! I have asked you to be on time.\nOk, I am leaving without you.";
        assertEquals(expected, Message.searchMessagesByRecipient("+27838884567"));
    }

    @Test
    public void testDeleteMessageUsingHash() {
        Message msg2 = new Message("+27838884567", "Where are you? You are late! I have asked you to be on time.");
        msg2.sentMessage("store");

        assertEquals(
            "Message \"Where are you? You are late! I have asked you to be on time\" successfully deleted.",
            Message.deleteMessageByHash(msg2.getMessageHash())
        );
    }

    @Test
    public void testDisplayReport() {
        Message msg5 = new Message("+27838884567", "Ok, I am leaving without you.");
        msg5.sentMessage("store");

        String report = Message.displayStoredMessagesReport();
        assertTrue(report.contains(msg5.getMessageID()));
        assertTrue(report.contains(msg5.getMessageHash()));
        assertTrue(report.contains("+27838884567"));
        assertTrue(report.contains("Ok, I am leaving without you."));
    }
}
