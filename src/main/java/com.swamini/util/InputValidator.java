package com.swamini.util;

public class InputValidator {

    // Name must be letters and spaces only
    public static boolean isValidName(String name) {
        return name != null &&
                !name.trim().isEmpty() &&
                name.matches("[a-zA-Z ]+");
    }

    // Age must be between 15 and 30
    public static boolean isValidAge(int age) {
        return age >= 15 && age <= 30;
    }

    // Basic email format check
    public static boolean isValidEmail(String email) {
        return email != null &&
                email.contains("@") &&
                email.contains(".");
    }

    // Marks must be between 0 and 100
    public static boolean isValidMarks(int marks) {
        return marks >= 0 && marks <= 100;
    }

    // Date format YYYY-MM-DD
    public static boolean isValidDate(String date) {
        return date != null &&
                date.matches("\\d{4}-\\d{2}-\\d{2}");
    }

    // Department cannot be empty
    public static boolean isValidDepartment(String dept) {
        return dept != null && !dept.trim().isEmpty();
    }
}