package edu.keepeasy.testcode.service;

public class Checker {
    public static boolean isPasswordValid(String password) {
        return password.length() >= 8 && !password.contains(" ");
    }

    public static boolean isUsernameValid(String username) {
        return username.length() > 0 && !username.contains(" ");
    }
}
