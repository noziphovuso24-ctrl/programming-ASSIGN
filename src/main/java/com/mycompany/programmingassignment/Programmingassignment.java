 package com.mycompany.programmingassignment;

import java.util.Scanner;

public class Programmingassignment {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login loginSystem = new Login();
        MessageManager messageManager = new MessageManager();

        System.out.println("=== QuickChat Registration ===");
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        System.out.print("Enter Cell Number (e.g., +27831234): ");
        String cell = scanner.nextLine();
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();

        String regResult = loginSystem.registerUser(username, password, cell, firstName, lastName);
        System.out.println("\n" + regResult + "\n");

        boolean registered = regResult.contains("successfully registered");

        if (registered) {
            // 2. Login Loop (Part 1)
            System.out.println("=== QuickChat Login ===");
            boolean loggedIn = false;
            while (!loggedIn) {
                System.out.print("Enter Username: ");
                String loginUser = scanner.nextLine();
                System.out.print("Enter Password: ");
                String loginPass = scanner.nextLine();

                loggedIn = loginSystem.loginUser(loginUser, loginPass);
                System.out.println(loginSystem.returnLoginStatus(loggedIn, firstName, lastName) + "\n");
            }

            // 3. Main Application Menu (Parts 2 & 3)
            boolean running = true;
            while (running) {
                System.out.println("=== QuickChat Main Menu ===");
                System.out.println("1) Send Messages (Part 2)");
                System.out.println("2) Show recently sent messages");
                System.out.println("3) Quit");
                System.out.println("4) Stored Messages");
                System.out.print("Choice: ");

                String choice = scanner.nextLine();

                switch (choice) {
                    case "1": {
                        int numMessages = 0;
                        System.out.print("How many messages do you wish to enter? ");
                        try {
                            numMessages = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid number entered.");
                            numMessages = 0;
                        }
                        System.out.println("Processing " + numMessages + " messages.");
                        break;
                    }
                    case "2":
                        System.out.println("Showing recently sent messages.");
                        break;
                    case "3":
                        System.out.println("Exiting QuickChat. Goodbye!");
                        running = false;
                        break;
                    case "4":
                        handleStoredMessagesMenu(scanner, messageManager);
                        break;
                    default:
                        System.out.println("Invalid option.");
                }
            }
        }
        scanner.close();
    }

    private static void handleStoredMessagesMenu(Scanner scanner, MessageManager messageManager) {
        System.out.println("=== Stored Messages Menu ===");
        System.out.println("1. Display Stored Report");
        System.out.println("2. Display Longest Message");
        System.out.print("Enter choice: ");
        String opt = scanner.nextLine();
        if (opt.equals("1")) {
            System.out.println(messageManager.displayStoredReport());
        } else if (opt.equals("2")) {
            System.out.println(messageManager.displayLongestStoredMessage());
        } else {
            System.out.println("Returning to main menu.");
        }
    }
}