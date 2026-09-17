/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.poepart1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */
public class RegistrationTest {
  
  
    // USERNAME TESTS

    @Test
    public void testCheckUserName_ValidFormat() {
        // Contains underscore and length <= 5
        Registration reg = new Registration("John", "Doe", "kyl_1", "Password123!", "+27821234567");
        assertTrue(reg.checkUserName());
    }

    @Test
    public void testCheckUserName_InvalidFormat_NoUnderscore() {
        // Length <= 5 but missing underscore
        Registration reg = new Registration("John", "Doe", "kyll1", "Password123!", "+27821234567");
        assertFalse(reg.checkUserName());
    }

    @Test
    public void testCheckUserName_InvalidFormat_TooLong() {
        // Contains underscore but length > 5
        Registration reg = new Registration("John", "Doe", "kyle_123", "Password123!", "+27821234567");
        assertFalse(reg.checkUserName());
    }
  //PASSWORD COMPLEXITY TESTS
   
    @Test
    public void testCheckPasswordComplexity_Valid() {
        // >= 8 chars, includes uppercase, digit, and special char
        Registration reg = new Registration("John", "Doe", "kyl_1", "Ch3&sec@ke", "+27821234567");
        assertTrue(reg.checkPasswordComplexibility());
    }

    @Test
    public void testCheckPasswordComplexity_Invalid_TooShort() {
        // Less than 8 characters
        Registration reg = new Registration("John", "Doe", "kyl_1", "Ch3&s", "+27821234567");
        assertFalse(reg.checkPasswordComplexibility());
    }

    @Test
    public void testCheckPasswordComplexity_Invalid_NoCapital() {
        // Missing capital letter
        Registration reg = new Registration("John", "Doe", "kyl_1", "ch3&sec@ke", "+27821234567");
        assertFalse(reg.checkPasswordComplexibility());
    }

    @Test
    public void testCheckPasswordComplexity_Invalid_NoNumber() {
        // Missing digit
        Registration reg = new Registration("John", "Doe", "kyl_1", "Chee&sec@ke", "+27821234567");
        assertFalse(reg.checkPasswordComplexibility());
    }

    @Test
    public void testCheckPasswordComplexity_Invalid_NoSpecialChar() {
        // Missing special character
        Registration reg = new Registration("John", "Doe", "kyl_1", "Ch33secake", "+27821234567");
        assertFalse(reg.checkPasswordComplexibility());
    }
  
    // CELL PHONE NUMBER TESTS
   
    @Test
    public void testCheckCellPhoneNumber_Valid() {
        // Valid SA number format (+27 followed by 821234567)
        Registration reg = new Registration("John", "Doe", "kyl_1", "Ch3&sec@ke", "+27821234567");
        assertTrue(reg.checkCellPhoneNumber());
    }

    @Test
    public void testCheckCellPhoneNumber_Invalid_IncorrectCountryCode() {
        // Wrong country code prefix
        Registration reg = new Registration("John", "Doe", "kyl_1", "Ch3&sec@ke", "0821234567");
        assertFalse(reg.checkCellPhoneNumber());
    }

    @Test
    public void testCheckCellPhoneNumber_Invalid_WrongDigitLength() {
        // Invalid number of digits after prefix
        Registration reg = new Registration("John", "Doe", "kyl_1", "Ch3&sec@ke", "+278212345");
        assertFalse(reg.checkCellPhoneNumber());
    }
    
    // USER REGISTRATION PROCESS TESTS
    

    @Test
    public void testRegisterUser_Success() {
        Registration reg = new Registration("John", "Doe", "kyl_1", "Ch3&sec@ke", "+27821234567");
        String expected = "Username successfully captured.\n"
                + "Password successfully captured.\n"
                + "Cellphone number successfully captured.\n"
                + "User registered successfully.";
        
        assertEquals(expected, reg.registerUser());
    }

    @Test
    public void testRegisterUser_Failure_Username() {
        Registration reg = new Registration("John", "Doe", "kyle_123", "Ch3&sec@ke", "+27821234567");
        String expected = "Username is not correctly formatted, please ensure that username contains an underscore and is no more than 5 characters in length.";
        
        assertEquals(expected, reg.registerUser());
    }

    @Test
    public void testRegisterUser_Failure_Password() {
        Registration reg = new Registration("John", "Doe", "kyl_1", "password", "+27821234567");
        String expected = "Password is not correctly formatted, please correct the password and try again.";
        
        assertEquals(expected, reg.registerUser());
    }

    @Test
    public void testRegisterUser_Failure_CellPhone() {
        Registration reg = new Registration("John", "Doe", "kyl_1", "Ch3&sec@ke", "0821234567");
        String expected = "Cellphone number is not correctly formatted, please correct the number and try again.";
        
        assertEquals(expected, reg.registerUser());
    }
    
    // LOGIN TESTS
  
    @Test
    public void testLoginUser_Successful() {
        Registration reg = new Registration("John", "Doe", "kyl_1", "Ch3&sec@ke", "+27821234567");
        assertTrue(reg.loginUser("kyl_1", "Ch3&sec@ke"));
    }

    @Test
    public void testLoginUser_Failed_WrongPassword() {
        Registration reg = new Registration("John", "Doe", "kyl_1", "Ch3&sec@ke", "+27821234567");
        assertFalse(reg.loginUser("kyl_1", "WrongPass123!"));
    }

    @Test
    public void testReturnLoginStatus_Successful() {
        Registration reg = new Registration("John", "Doe", "kyl_1", "Ch3&sec@ke", "+27821234567");
        String expected = "Welcome John Doe, it is great to see you again.";
        assertEquals(expected, reg.returnLoginStatus(true));
    }

    @Test
    public void testReturnLoginStatus_Failed() {
        Registration reg = new Registration("John", "Doe", "kyl_1", "Ch3&sec@ke", "+27821234567");
        String expected = "Username or password incorrect, please try again.";
        assertEquals(expected, reg.returnLoginStatus(false));
    }
}
