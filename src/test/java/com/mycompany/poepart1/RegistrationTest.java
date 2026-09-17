/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
  
   package com.mycompany.poepart1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RegistrationTest {

    private Registration validUser;
    private Registration invalidUser;

    @BeforeEach
    public void setUp() {
        // Valid user details: username with '_', <= 5 length, password meets complexity, valid SA cell (+27 + 9 digits)
        validUser = new Registration("John", "Doe", "j_doe", "Ch@nge123", "+27821234567");

        // Invalid user details: long username, simple password, invalid phone format
        invalidUser = new Registration("Jane", "Smith", "janesmith", "12345", "0821234567");
    }

    @Test
    public void testCheckUserName_Valid() {
        assertTrue(validUser.checkUserName(), "Username should be valid (contains '_' and length <= 5)");
    }

    @Test
    public void testCheckUserName_Invalid() {
        assertFalse(invalidUser.checkUserName(), "Username should be invalid (missing '_' and > 5 chars)");
    }

    @Test
    public void testCheckCellPhoneNumber_Valid() {
        assertTrue(validUser.checkCellPhoneNumber(), "Cell phone number should match +27 followed by 9 digits");
    }

    @Test
    public void testCheckCellPhoneNumber_Invalid() {
        assertFalse(invalidUser.checkCellPhoneNumber(), "Cell phone number missing +27 format should fail");
    }

    @Test
    public void testRegisterUser_Success() {
        String expectedMessage = "userName successfuly captured.\n"
                + "Password successfully captured.\n"
                + "Cellphone number successfully captured.\n"
                + "User registered successfully.";
        
        assertEquals(expectedMessage, validUser.registerUser());
    }

    @Test
    public void testRegisterUser_InvalidUsername() {
        Registration user = new Registration("John", "Doe", "john_doe_long", "Ch@nge123", "+27821234567");
        String message = user.registerUser();
        assertTrue(message.contains("UserName is not correctly formatted"));
    }

    @Test
    public void testLoginUser_Success() {
        assertTrue(validUser.loginUser("j_doe", "Ch@nge123"), "Login should succeed with correct credentials");
    }

    @Test
    public void testLoginUser_Failure() {
        assertFalse(validUser.loginUser("j_doe", "wrongPassword"), "Login should fail with wrong password");
        assertFalse(validUser.loginUser("wrongUser", "Ch@nge123"), "Login should fail with wrong username");
    }

    @Test
    public void testReturnLoginStatus_Success() {
        String expectedMessage = "WelcomeJohn Doe, you are welcome.";
        assertEquals(expectedMessage, validUser.returnLoginStatus(true));
    }

    @Test
    public void testReturnLoginStatus_Failure() {
        String expectedMessage = "Username or paasword incorrect,please try again.";
        assertEquals(expectedMessage, validUser.returnLoginStatus(false));
    }
}
