package com.mycompany.programmingassignment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class junitest {

    private Login loginSystem;
    private MessageManager messageManager;

    @BeforeEach
    public void setUp() {
        loginSystem = new Login();
        messageManager = new MessageManager();
    }

    @Test
    public void testCheckUserNameValid() {
        assertTrue(loginSystem.checkUserName("kyl_1"));
    }

    @Test
    public void testCheckUserNameInvalid() {
        assertFalse(loginSystem.checkUserName("kyle!!!!!!"));
    }

    @Test
    public void testCheckPasswordComplexityValid() {
        assertTrue(loginSystem.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    public void testCheckPasswordComplexityInvalid() {
        assertFalse(loginSystem.checkPasswordComplexity("password"));
    }

    @Test
    public void testRegisterUserSuccess() {
        String result = loginSystem.registerUser("kyl_1", "Ch&&sec@ke99!", "+27831234567", "John", "Doe");
        assertEquals("User successfully registered.", result);
    }

    @Test
    public void testLoginUserSuccess() {
        loginSystem.registerUser("kyl_1", "Ch&&sec@ke99!", "+27831234567", "John", "Doe");
        assertTrue(loginSystem.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testLoginUserFailure() {
        assertFalse(loginSystem.loginUser("wrong", "invalidpass"));
    }

    @Test
    public void testStoreAndSearchMessage() {
        messageManager.addMessage("1234567890", "12:0:HITHANKS", "+27831234", "HI THANKS", "Stored");
        String searchResult = messageManager.searchByMessageID("1234567890");
        assertTrue(searchResult.contains("1234567890"));
    }

    @Test
    public void testDeleteMessageByHash() {
        messageManager.addMessage("1234567890", "12:0:HITHANKS", "+27831234", "HI THANKS", "Stored");
        boolean deleted = messageManager.deleteMessageByHash("12:0:HITHANKS");
        assertTrue(deleted);
    }

    @Test
    public void testLongestStoredMessage() {
        messageManager.addMessage("1111111111", "11:0:SHORT", "+27831234", "Short msg", "Stored");
        messageManager.addMessage("2222222222", "22:1:LONGEST", "+27831234", "This is a much longer message body for testing.", "Stored");
        
        String longest = messageManager.displayLongestStoredMessage();
        assertTrue(longest.contains("This is a much longer message body"));
    }
}