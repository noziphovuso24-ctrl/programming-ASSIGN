package com.mycompany.programmingassignment;

import java.util.regex.Pattern;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author ST10509430
 */
public class Login {

    // Instance variables to store registered user data
    private String UserName;
    private String password;
    private String firstName;
    private String lastName;
    private String cellPhoneNumber;

    // Default constructor
    public Login() {
        this.UserName = "";
        this.password = "";
        this.firstName = "";
        this.lastName = "";
        this.cellPhoneNumber = "";
    }

    /**
     * Ensures that any username contains an underscore (_) and is no more than
     * 5 characters long.
     */
    public boolean checkUserName() {
        return this.UserName.contains("_") && this.UserName.length() <= 5;
    }

    /**
     * Verifies password complexity requirements: - At least eight characters
     * long - Contains a capital letter - Contains a number - Contains a special
     * character
     */
    public boolean checkPasswordComplexity() {
        if (this.password.length() < 8) {
            return false;
        }
        boolean hasUppercase = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        // Special characters include standard punctuation/symbols
        String specialChars = "~`!@#$%^&*()-_=+\\|[{]};:'\",<.>/?";

        for (char c : this.password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUppercase = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (specialChars.indexOf(c) != -1) {
                hasSpecial = true;
            }
        }
        return hasUppercase && hasDigit && hasSpecial;
    }

    /**
     * Checks if the cell phone number starts with a valid international country
     * code (e.g., +27 for South Africa) followed by a number that is no more
     * than 10 digits long.
     */
    public boolean checkCellPhoneNumber() {
        if (this.cellPhoneNumber == null) {
            return false;
        }
        // Check if it starts with the South African international code +27
        if (!this.cellPhoneNumber.startsWith("+27")) {
            return false;
        }
        // Extract the remaining number digits following '+27'
        String localNumber = this.cellPhoneNumber.substring(3);

        // Check if the remaining part consists only of digits and is no more than 10 characters long
        return java.util.regex.Pattern.matches("\\d+", localNumber) && localNumber.length() <= 9;
    }

    /**
     * Orchestrates registration logic and returns status messages based on
     * formatting criteria.
     */
    public String registerUser() {
        if (!checkUserName()) {
            return "UserName is not correctly formatted; please ensure that your UserName contains an underscore and is no more than five characters in length.";
        }
        if (!checkPasswordComplexity()) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber()) {
            return "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
        }
        return "UserName successfully captured.";
    }

    /**
     * Verifies that the entered login credentials match the registered account
     * details.
     */
    public boolean loginUser(String enteredUserName, String enteredPassword) {
        if (this.UserName == null || this.password == null) {
            return false;
        }
        return this.UserName.equals(enteredUserName) && this.password.equals(enteredPassword);
    }

    /**
     * Returns the appropriate system welcome message or login failure warning
     * message.
     */
    public String returnLoginStatus(boolean loginSuccessful) {
        if (loginSuccessful) {
            return "Welcome " + this.firstName + ", " + this.lastName + " it is great to see you again.";
        } else {
            return "UserName or password incorrect, please try again.";
        }
    }

    // --- Getters and Setters to populate data before running the checks ---
    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCellPhoneNumber(String cellPhoneNumber) {
        this.cellPhoneNumber = cellPhoneNumber;
    }
}
