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

   
    