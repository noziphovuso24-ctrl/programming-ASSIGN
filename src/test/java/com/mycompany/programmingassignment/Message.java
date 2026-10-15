 package com.mycompany.programmingassignment;
import java.util.Random;
/**
 *
 * @author nozipho vuso
 */
public class Message {
    private String messageId;
    private int messageNumber;
    private String recipient;
    private String messageContent;
    private String actionChoice; // "Send", "Disregard", "Store"

    public Message(int messageNumber, String recipient, String messageContent) {
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageContent = messageContent;
        this.messageId = generateMessageId();
    }

    // Helper to generate a random 10-digit number as String to retain precision
    private String generateMessageId() {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(rand.nextInt(10));
        }
        return sb.toString();
    }

    public boolean checkMessageID() {
        return this.messageId != null && this.messageId.length() <= 10;
    }

    public String checkRecipientCell() {
        // Validation: No more than 10 characters long AND starts with an international code (e.g., '+')
        if (this.recipient.length() <= 10 && this.recipient.startsWith("+")) {
            return "Valid Recipient";
        }
        return "Invalid Recipient: Must start with '+' and be 10 characters or less.";
    }

    public String createMessageHash() {
        String firstTwoId = this.messageId.substring(0, 2);
        
        // Extract first and last words
        String trimmed = this.messageContent.trim();
        String[] words = trimmed.split("\\s+");
        String firstWord = words.length > 0 ? words[0] : "";
        String lastWord = words.length > 0 ? words[words.length - 1] : "";
        
        // Clean punctuation for the hash match example (HITHANKS)
        firstWord = firstWord.replaceAll("[^a-zA-Z]", "");
        lastWord = lastWord.replaceAll("[^a-zA-Z]", "");

        String hash = firstTwoId + ":" + this.messageNumber + ":" + firstWord + lastWord;
        return hash.toUpperCase();
    }

    public String validateMessageLength() {
        if (this.messageContent.length() <= 250) {
            return "Message ready to send.";
        } else {
            int exceededBy = this.messageContent.length() - 250;
            return "Message exceeds 250 characters by " + exceededBy + "; please reduce the size.";
        }
    }

    public String sentMessage(int choice) {
        switch (choice) {
            case 1:
                this.actionChoice = "Send";
                return "Message successfully sent";
            case 0:
                this.actionChoice = "Disregard";
                return "Press 0 to delete the message";
            case 2:
                this.actionChoice = "Store";
                return "Message successfully stored";
            default:
                this.actionChoice = "Unknown";
                return "Invalid choice";
        }
    }

    public String printMessageDetails() {
        return "Message ID: " + this.messageId + 
               ", Message Hash: " + createMessageHash() + 
               ", Recipient: " + this.recipient + 
               ", Message: " + this.messageContent;
    }

    // Getters for testing and application tracking
    public String getMessageId() { return messageId; }
    public int getMessageNumber() { return messageNumber; }
    public String getRecipient() { return recipient; }
    public String getMessageContent() { return messageContent; }
    public String getActionChoice() { return actionChoice; }
}
