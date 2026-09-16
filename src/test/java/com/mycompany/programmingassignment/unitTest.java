package com.mycompany.programmingassignment;
import java.util.regex.Pattern;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class unitTest {

    private Login login;

    @BeforeEach
    public void setUp() {
        login = new Login();
    }

    @Test
    public void testUserNameCorrectlyFormatted_Boolean() {
        login.setUserName("kyl_1");
        assertTrue(login.checkUserName());
    }

    @Test
    public void testUserNameIncorrectlyFormatted_Boolean() {
        login.setUserName("kyle!!!!!!");
        assertFalse(login.checkUserName());
    }

    @Test
    public void testPasswordMeetsComplexity_Boolean() {
        login.setPassword("Ch&&sec@ke99!");
        assertTrue(login.checkPasswordComplexity());
    }

    @Test
    public void testPasswordDoesNotMeetComplexity_Boolean() {
        login.setPassword("password");
        assertFalse(login.checkPasswordComplexity());
    }

    @Test
    public void testCellPhoneNumberCorrectlyFormatted_Boolean() {
        login.setCellPhoneNumber("+27838968976");
        assertTrue(login.checkCellPhoneNumber());
    }

    @Test
    public void testCellPhoneNumberIncorrectlyFormatted_Boolean() {
        login.setCellPhoneNumber("08966553");
        assertFalse(login.checkCellPhoneNumber());
    }

    @Test
    public void testLoginSuccessful_Boolean() {
        login.setUserName("kyl_1");
        login.setPassword("Ch&&sec@ke99!");
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }
}

