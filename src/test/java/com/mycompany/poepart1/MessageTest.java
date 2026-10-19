/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.poepart1;


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
}