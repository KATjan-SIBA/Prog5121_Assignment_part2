/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.prog5121_assignment_part2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */
public class PROG5121_Assignment_part2Test {
    
    public PROG5121_Assignment_part2Test() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of generateMessageID method, of class PROG5121_Assignment_part2.
     */
    @Test
    public void testGenerateMessageID() {
        System.out.println("generateMessageID");
        String expResult = "";
        String result = PROG5121_Assignment_part2.generateMessageID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValidPhoneNumber method, of class PROG5121_Assignment_part2.
     */
    @Test
    public void testIsValidPhoneNumber() {
        System.out.println("isValidPhoneNumber");
        String number = "";
        boolean expResult = false;
        boolean result = PROG5121_Assignment_part2.isValidPhoneNumber(number);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createHash method, of class PROG5121_Assignment_part2.
     */
    @Test
    public void testCreateHash() {
        System.out.println("createHash");
        String messageID = "";
        String message = "";
        int currentMessageIndex = 0;
        String expResult = "";
        String result = PROG5121_Assignment_part2.createHash(messageID, message, currentMessageIndex);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isUsernameTaken method, of class PROG5121_Assignment_part2.
     */
    @Test
    public void testIsUsernameTaken() {
        System.out.println("isUsernameTaken");
        String username = "";
        boolean expResult = false;
        boolean result = PROG5121_Assignment_part2.isUsernameTaken(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registerUser method, of class PROG5121_Assignment_part2.
     */
    @Test
    public void testRegisterUser() {
        System.out.println("registerUser");
        PROG5121_Assignment_part2.registerUser();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loginUser method, of class PROG5121_Assignment_part2.
     */
    @Test
    public void testLoginUser() {
        System.out.println("loginUser");
        boolean expResult = false;
        boolean result = PROG5121_Assignment_part2.loginUser();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sendMessages method, of class PROG5121_Assignment_part2.
     */
    @Test
    public void testSendMessages() {
        System.out.println("sendMessages");
        PROG5121_Assignment_part2.sendMessages();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class PROG5121_Assignment_part2.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        PROG5121_Assignment_part2.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
