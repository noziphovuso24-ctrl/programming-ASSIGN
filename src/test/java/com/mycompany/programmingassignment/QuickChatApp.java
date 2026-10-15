package com.mycompany.programmingassignment;
 import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author nozipho vuso
 */
public class QuickChatApp {
    private static boolean isLoggedIn = true; // Simulating successful login state
    private static ArrayList<Message> sentMessagesList = new ArrayList<>();
    private static int totalMessagesSentCount = 0;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        if (!isLoggedIn) {
            System.out.println("Error: You must be logged in to send messages.");
            return;
        }

        System.out.println("Welcome to QuickChat.");
        int choice = 0;

        while (choice != 3) {
            System.out.println("\n--- Numeric Menu ---");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.print("Choose an option: ");
            
            if (input.hasNextInt()) {
                choice = input.nextInt();
                input.nextLine(); // Clear buffer
            } else {
                System.out.println("Invalid input. Please enter a number.");
                input.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    processMessageSending(input);
                    break;
                case 2:
                    System.out.println("Coming Soon.");
                    break;
                case 3:
                    System.out.println("Exiting Application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
        input.close();
    }

    private static void processMessageSending(Scanner input) {
        System.out.print("How many messages do you wish to enter? ");
        int totalToEnter = input.nextInt();
        input.nextLine(); // Clear buffer

        for (int i = 0; i < totalToEnter; i++) {
            System.out.println("\n--- Entering Message " + (i + 1) + " of " + totalToEnter + " ---");
            
            System.out.print("Enter Recipient Cell Number: ");
            String recipient = input.nextLine();

            System.out.print("Enter Message text (Max 250 characters): ");
            String messageContent = input.nextLine();

            // Instantiate object (it autogenerates ID internally)
            int currentMsgNumber = totalMessagesSentCount + 1;
            Message msg = new Message(currentMsgNumber, recipient, messageContent);

            // Validation Checks
            String validationMsg = msg.validateMessageLength();
            if (!validationMsg.equals("Message ready to send.")) {
                System.out.println(validationMsg);
                System.out.println("Please enter a message of less than 250 characters.*");
                i--; // Redo this entry index loop iteration safely
                continue;
            }

            // Options Workflow
            System.out.println("Choose action: \n1 - Send Message\n0 - Disregard Message\n2 - Store Message to send later");
            int actionChoice = input.nextInt();
            input.nextLine(); // Clear buffer

            String statusMessage = msg.sentMessage(actionChoice);
            System.out.println(statusMessage);

            if (actionChoice == 1) {
                totalMessagesSentCount++;
                sentMessagesList.add(msg);
                System.out.println("Message sent");
                System.out.println(msg.printMessageDetails());
            } else if (actionChoice == 2) {
                storeMessageInJson(msg);
            } else if (actionChoice == 0) {
                System.out.println("Message tracking purged.");
            }
        }

        // Return metrics accumulation
        System.out.println("\n=================================");
        System.out.println("Total successful messages processed: " + returnTotalMessages());
        System.out.println("All running messages logged summary below:");
        System.out.println(printMessages());
        System.out.println("=================================");
    }

    public static void storeMessageInJson(Message msg) {
        // Research mapping requirement mocked cleanly without heavy bloated dependencies
        String json = "{\n" +
                " \"messageId\": \"" + msg.getMessageId() + "\",\n" +
                " \"hash\": \"" + msg.createMessageHash() + "\",\n" +
                " \"recipient\": \"" + msg.getRecipient() + "\",\n" +
                " \"content\": \"" + msg.getMessageContent() + "\"\n" +
                "}";
        System.out.println("Mock JSON saved to flat storage system cleanly:\n" + json);
        System.out.println("Message successfully stored");
    }

    public static String printMessages() {
        if (sentMessagesList.isEmpty()) return "No messages sent during execution pipeline loops.";
        StringBuilder sb = new StringBuilder();
        for (Message m : sentMessagesList) {
            sb.append(m.printMessageDetails()).append("\n");
        }
        return sb.toString();
    }

    public static int returnTotalMessages() {
        return totalMessagesSentCount;
    }
}
