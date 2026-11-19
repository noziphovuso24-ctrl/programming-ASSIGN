package com.mycompany.programmingassignment;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MessageManager {

    private List<MessageDetails> sentMessages = new ArrayList<>();
    private List<MessageDetails> disregardedMessages = new ArrayList<>();
    private List<MessageDetails> storedMessages = new ArrayList<>();
    private List<String> messageHashes = new ArrayList<>();

    // Inner class representing an individual message record
    public static class MessageDetails {
        public String messageID;
        public String messageHash;
        public String recipient;
        public String messageBody;
        public String flag; // "Sent", "Stored", "Disregarded"

        public MessageDetails(String messageID, String messageHash, String recipient, String messageBody, String flag) {
            this.messageID = messageID;
            this.messageHash = messageHash;
            this.recipient = recipient;
            this.messageBody = messageBody;
            this.flag = flag;
        }
    }

    public void addMessage(String messageID, String messageHash, String recipient, String messageBody, String flag) {
        MessageDetails msg = new MessageDetails(messageID, messageHash, recipient, messageBody, flag);
        messageHashes.add(messageHash);

        if ("Sent".equalsIgnoreCase(flag)) {
            sentMessages.add(msg);
        } else if ("Stored".equalsIgnoreCase(flag)) {
            storedMessages.add(msg);
            saveStoredMessagesToJson();
        } else if ("Disregarded".equalsIgnoreCase(flag)) {
            disregardedMessages.add(msg);
        }
    }

    public String searchByMessageID(String targetID) {
        for (MessageDetails msg : storedMessages) {
            if (msg.messageID.equals(targetID)) {
                return "ID: " + msg.messageID + " | Recipient: " + msg.recipient + " | Message: " + msg.messageBody;
            }
        }
        for (MessageDetails msg : sentMessages) {
            if (msg.messageID.equals(targetID)) {
                return "ID: " + msg.messageID + " | Recipient: " + msg.recipient + " | Message: " + msg.messageBody;
            }
        }
        return "Message ID not found.";
    }

    public String searchByRecipient(String targetRecipient) {
        StringBuilder sb = new StringBuilder();
        for (MessageDetails msg : storedMessages) {
            if (msg.recipient.equals(targetRecipient)) {
                sb.append("ID: ").append(msg.messageID).append(" | Message: ").append(msg.messageBody).append("\n");
            }
        }
        return sb.length() > 0 ? sb.toString() : "No messages found for recipient.";
    }

    public boolean deleteMessageByHash(String targetHash) {
        Iterator<MessageDetails> iterator = storedMessages.iterator();
        while (iterator.hasNext()) {
            MessageDetails msg = iterator.next();
            if (msg.messageHash.equals(targetHash)) {
                iterator.remove();
                messageHashes.remove(targetHash);
                saveStoredMessagesToJson();
                return true;
            }
        }
        return false;
    }

    public String displayLongestStoredMessage() {
        if (storedMessages.isEmpty()) {
            return "No stored messages available.";
        }
        MessageDetails longest = storedMessages.get(0);
        for (MessageDetails msg : storedMessages) {
            if (msg.messageBody.length() > longest.messageBody.length()) {
                longest = msg;
            }
        }
        return "Longest Message: " + longest.messageBody;
    }

    public String displayStoredReport() {
        if (storedMessages.isEmpty()) {
            return "No stored messages to display.";
        }
        StringBuilder report = new StringBuilder("=== Stored Messages Report ===\n");
        for (MessageDetails msg : storedMessages) {
            report.append("Hash: ").append(msg.messageHash)
                  .append(" | ID: ").append(msg.messageID)
                  .append(" | Recipient: ").append(msg.recipient)
                  .append(" | Body: ").append(msg.messageBody)
                  .append("\n");
        }
        return report.toString();
    }

    public String displayStoredSendersAndRecipients() {
        StringBuilder sb = new StringBuilder();
        for (MessageDetails msg : storedMessages) {
            sb.append("Recipient: ").append(msg.recipient).append(" | Hash: ").append(msg.messageHash).append("\n");
        }
        return sb.length() > 0 ? sb.toString() : "No records found.";
    }

    public void saveStoredMessagesToJson() {
        StringBuilder json = new StringBuilder("[\n");
        for (int i = 0; i < storedMessages.size(); i++) {
            MessageDetails m = storedMessages.get(i);
            json.append("  {\n")
                .append("    \"messageID\": \"").append(m.messageID).append("\",\n")
                .append("    \"messageHash\": \"").append(m.messageHash).append("\",\n")
                .append("    \"recipient\": \"").append(m.recipient).append("\",\n")
                .append("    \"messageBody\": \"").append(m.messageBody).append("\"\n")
                .append("  }");
            if (i < storedMessages.size() - 1) {
                json.append(",");
            }
            json.append("\n");
        }
        json.append("]");

        try (FileWriter writer = new FileWriter("stored_messages.json")) {
            writer.write(json.toString());
                    }catch(IOException e){
                        System.out.println("Error saving JSON file:"+e.getMessage());
                    }
    }
}