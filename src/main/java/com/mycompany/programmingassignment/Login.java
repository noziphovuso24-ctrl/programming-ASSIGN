package com.mycompany.programmingassignment;

import java.util.regex.Pattern;

public class Login {
    private String savedUsername;
    private String savedPassword;
    private String savedCell;
    private String savedFirstName;
    private String savedLastName;

    public boolean checkUserName(String username) {
        return username != null && username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity(String password) {
        if (password == null || password.length() < 8) return false;
        boolean hasUpper = !password.equals(password.toLowerCase());
        boolean hasNum = password.chars().anyMatch(Character::isDigit);
        boolean hasSpecial = !password.matches("[A-Za-z0-9]*");
        return hasUpper && hasNum && hasSpecial;
    }

    public boolean checkCellPhoneNumber(String cell) {
        if (cell == null || !cell.startsWith("+") || cell.length() > 10) return false;
        String localNumber = cell.substring(1);
        return Pattern.matches("\\d+", localNumber);
    }

    public String registerUser(String username, String password, String cell, String firstName, String lastName) {
        if (!checkUserName(username)) {
            return "UserName is not correctly formatted; please ensure that your UserName contains an underscore and is no more than 5 characters in length.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least 8 characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber(cell)) {
            return "Cell number is incorrectly formatted or does not contain an international code; please ensure it starts with '+' and is no more than 10 characters long.";
        }

        this.savedUsername = username;
        this.savedPassword = password;
        this.savedCell = cell;
        this.savedFirstName = firstName;
        this.savedLastName = lastName;

        return "User successfully registered.";
    }

    public boolean loginUser(String username, String password) {
        return username != null && password != null && username.equals(savedUsername) && password.equals(savedPassword);
    }

    public String returnLoginStatus(boolean isLoggedIn, String firstName, String lastName) {
        if (isLoggedIn) {
            return "Welcome " + firstName + " " + lastName + ", it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}