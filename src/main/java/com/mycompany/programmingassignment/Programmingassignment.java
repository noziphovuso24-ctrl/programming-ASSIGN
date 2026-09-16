 package com.mycompany.programmingassignment;
import java.util.Scanner;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */



/**
 *
 * @author ST10509430
 */
public class Programmingassignment {

    
       


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login userLogin = new Login();

        System.out.println("=== USER REGISTRATION ===");
        
        // 1. Capture First Name and Last Name
        System.out.print("Enter First Name: ");
        userLogin.setFirstName(scanner.nextLine());

        System.out.print("Enter Last Name: ");
        userLogin.setLastName(scanner.nextLine());

        // 2. Loop until Registration is Successful
        boolean isRegistered = false;
        while (!isRegistered) {
            System.out.print("Enter Username (max 5 chars, must contain '_'): ");
            userLogin.setUserName(scanner.nextLine());

            System.out.print("Enter Password (min 8 chars, 1 uppercase, 1 digit, 1 special): ");
            userLogin.setPassword(scanner.nextLine());

            System.out.print("Enter Cell Phone Number (e.g., +27838968976): ");
            userLogin.setCellPhoneNumber(scanner.nextLine());

            System.out.println("\nProcessing registration...");
            String registrationStatus = userLogin.registerUser();
            System.out.println(registrationStatus);

            // If the status confirms success, break the loop
            if (registrationStatus.equals("Username successfully captured.")) {
                isRegistered = true;
                System.out.println("Registration Complete!\n");
            } else {
                System.out.println("Please try registering again with valid formats.\n");
            }
        }

        System.out.println("=== USER LOGIN ===");
        
        // 3. Prompt for Login Credentials
        System.out.print("Enter Username to Login: ");
        String enteredUsername = scanner.nextLine();

        System.out.print("Enter Password to Login: ");
        String enteredPassword = scanner.nextLine();

        // 4. Verify and Display Login Status
        boolean loginSuccessful = userLogin.loginUser(enteredUsername, enteredPassword);
        String loginMessage = userLogin.returnLoginStatus(loginSuccessful);
        
        System.out.println("\n" + loginMessage);

        scanner.close();
    }
}

 
