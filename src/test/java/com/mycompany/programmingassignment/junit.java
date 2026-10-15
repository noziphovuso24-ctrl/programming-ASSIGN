package com.mycompany.programmingassignment;
  import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
/**
 *
 * @author nozipho vuso
 */
public class junit {
  

public class QuickChatTest {

    @Test
    public void testMessageLengthSuccess() {
        Message validMsg = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message ready to send.", validMsg.validateMessageLength());
    }

    @Test
    public void testMessageLengthFailure() {
        // Build an intentionally bloated string string running structural checks over 250 character limitations
        String longText = "A".repeat(255); 
        Message invalidMsg = new Message(2, "08575975889", longText);
        assertEquals("Message exceeds 250 characters by 5; please reduce the size.", invalidMsg.validateMessageLength());
    }

    @Test
    public void testCheckMessageIDLength() {
        Message msg = new Message(1, "+27718693002", "Test text");
        assertTrue(msg.checkMessageID());
        assertEquals(10, msg.getMessageId().length());
    }

    @Test
    public void testRecipientCellValidation() {
        // Test valid international format data block constraint checks
        Message validRecipient = new Message(1, "+277186930", "Valid content.");
        assertEquals("Valid Recipient", validRecipient.checkRecipientCell());

        // Test missing standard matching rules properties codes block
        Message invalidRecipient = new Message(2, "0857597588", "Missing prefix sign code.");
        assertTrue(invalidRecipient.checkRecipientCell().contains("Invalid"));
    }
}


    
}
