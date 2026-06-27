package com.swamini;
import com.swamini.exception.DuplicateIDException;
import com.swamini.exception.InvalidCredentials;
import com.swamini.exception.StudentNotFound;
import com.swamini.model.Student;
import com.swamini.model.User;
import com.swamini.service.*;
import com.swamini.util.DBConnection;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.sql.*;
public class Main {
    static Scanner           scanner           = new Scanner(System.in);
    static StudentService    studentService    = new StudentService();
    static AttendanceService attendanceService = new AttendanceService();
    static MarksService      marksService      = new MarksService();
    static AuthService       authService       = new AuthService();

    public static void main(String[] args) {
        try {
            Connection conn = DBConnection.getConnection();
            System.out.println("Connection successful: " + conn);
        } catch (SQLException e) {
            System.out.println("❌ Failed: " + e.getMessage());
        }
        System.out.println("==============================");
        System.out.println("  Student Management System  ");
        System.out.println("==============================");

        // Login
        User loggedInUser = handleLogin();
        if (loggedInUser == null) {
            System.out.println("❌ Too many failed attempts. Exiting.");
            return;
        }

        System.out.println("✅ Welcome, " + loggedInUser.getUsername()
                + "! Role: " + loggedInUser.getRole());

        // Route based on role
        if (loggedInUser.getRole().equals("ADMIN")) {
            adminMenu();
        } else {
            studentMenu();
        }

        scanner.close();
    }

    // --- LOGIN ---
    static User handleLogin() {
        int attempts = 0;
        while (attempts < 3) {
            System.out.print("\nUsername: ");
            String username = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();
            try {
                return authService.login(username, password);
            } catch (InvalidCredentials e) {
                attempts++;
                System.out.println(e.getMessage());
                System.out.println("Attempts left: " + (3 - attempts));
            }
        }
        return null;
    }

    // --- ADMIN MENU ---
    static void adminMenu() {
        int choice = 0;
        do {
            printAdminMenu();
            try {
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:  addStudent();                       break;
                    case 2:  studentService.viewAllStudents();   break;
                    case 3:  searchStudent();                    break;
                    case 4:  deleteStudent();                    break;
                    case 5:  updateStudent();                    break;
                    case 6:  markAttendance();                   break;
                    case 7:  viewAttendance();                   break;
                    case 8:  viewAttendancePercentage();         break;
                    case 9:  addMarks();                         break;
                    case 10: viewMarks();                        break;
                    case 11: marksService.viewMarksAnalytics();  break;
                    case 12: System.out.println("👋 Goodbye!"); break;
                    default: System.out.println("❌ Invalid choice.");
                }

            } catch (InputMismatchException e) {
                System.out.println("❌ Enter numbers only!");
                scanner.nextLine();
                choice = 0;
            } catch (StudentNotFound | DuplicateIDException e) {
                System.out.println(e.getMessage());
                choice = 0;
            }

        } while (choice != 12);
    }

    // --- STUDENT MENU ---
    static void studentMenu() {
        int choice = 0;
        do {
            printStudentMenu();
            try {
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Enter your Student ID: ");
                        int id = scanner.nextInt();
                        studentService.searchById(id);
                        break;
                    case 2:
                        System.out.print("Enter your Student ID: ");
                        int sid = scanner.nextInt();
                        attendanceService.viewAttendance(sid);
                        break;
                    case 3:
                        System.out.print("Enter your Student ID: ");
                        int mid = scanner.nextInt();
                        marksService.viewMarksByStudent(mid);
                        break;
                    case 4:
                        System.out.println("👋 Goodbye!");
                        break;
                    default:
                        System.out.println("❌ Invalid choice.");
                }

            } catch (InputMismatchException e) {
                System.out.println("❌ Enter numbers only!");
                scanner.nextLine();
                choice = 0;
            } catch (StudentNotFound e) {
                System.out.println(e.getMessage());
                choice = 0;
            }

        } while (choice != 4);
    }

    // --- HELPER METHODS ---

    static void addStudent() {
        try {
            System.out.print("Enter ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Age: ");
            int age = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter Department: ");
            String dept = scanner.nextLine();
            System.out.print("Enter Email: ");
            String email = scanner.nextLine();
            studentService.addStudent(
                    new Student(id, name, age, dept, email));
        } catch (InputMismatchException e) {
            System.out.println("❌ Invalid input!");
            scanner.nextLine();
        }
    }

    static void searchStudent() {
        try {
            System.out.print("Enter ID to search: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            studentService.searchById(id);
        } catch (InputMismatchException e) {
            System.out.println("❌ Invalid input!");
            scanner.nextLine();
        }
    }

    static void deleteStudent() {
        try {
            System.out.print("Enter ID to delete: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            studentService.deleteStudent(id);
        } catch (InputMismatchException e) {
            System.out.println("❌ Invalid input!");
            scanner.nextLine();
        }
    }

    static void updateStudent() {
        try {
            System.out.print("Enter ID to update: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter new Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter new Age: ");
            int age = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter new Department: ");
            String dept = scanner.nextLine();
            System.out.print("Enter new Email: ");
            String email = scanner.nextLine();
            studentService.updateStudent(id, name, age, dept, email);
        } catch (InputMismatchException e) {
            System.out.println("❌ Invalid input!");
            scanner.nextLine();
        }
    }
    static void markAttendance() {
        try {
            System.out.print("Enter Student ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter Date (YYYY-MM-DD): ");
            String date = scanner.nextLine();
            System.out.print("Enter Status (PRESENT/ABSENT): ");
            String status = scanner.nextLine();
            attendanceService.markAttendance(id, date, status);
        } catch (InputMismatchException e) {
            System.out.println("❌ Invalid input!");
            scanner.nextLine();
        }
    }

    static void viewAttendance() {
        try {
            System.out.print("Enter Student ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            attendanceService.viewAttendance(id);
        } catch (InputMismatchException e) {
            System.out.println("❌ Invalid input!");
            scanner.nextLine();
        }
    }

    static void viewAttendancePercentage() {
        try {
            System.out.print("Enter Student ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            attendanceService.viewAttendancePercentage(id);
        } catch (InputMismatchException e) {
            System.out.println("❌ Invalid input!");
            scanner.nextLine();
        }
    }

    static void addMarks() {
        try {
            System.out.print("Enter Student ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter Subject: ");
            String subject = scanner.nextLine();
            System.out.print("Enter Marks (0-100): ");
            int marks = scanner.nextInt();
            scanner.nextLine();
            marksService.addMarks(id, subject, marks);
        } catch (InputMismatchException e) {
            System.out.println("❌ Invalid input!");
            scanner.nextLine();
        }
    }

    static void viewMarks() {
        try {
            System.out.print("Enter Student ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            marksService.viewMarksByStudent(id);
        } catch (InputMismatchException e) {
            System.out.println("❌ Invalid input!");
            scanner.nextLine();
        }
    }

    // --- MENU PRINTERS ---

    static void printAdminMenu() {
        System.out.println("\n======= ADMIN MENU =======");
        System.out.println("--- Student Operations ---");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Search Student");
        System.out.println("4. Delete Student");
        System.out.println("5. Update Student");
        System.out.println("--- Attendance ---");
        System.out.println("6. Mark Attendance");
        System.out.println("7. View Attendance");
        System.out.println("8. Attendance Percentage");
        System.out.println("--- Marks ---");
        System.out.println("9.  Add Marks");
        System.out.println("10. View Marks");
        System.out.println("11. Marks Analytics");
        System.out.println("--------------------------");
        System.out.println("12. Exit");
        System.out.print("Enter choice: ");
    }

    static void printStudentMenu() {
        System.out.println("\n====== STUDENT MENU ======");
        System.out.println("1. View My Profile");
        System.out.println("2. View My Attendance");
        System.out.println("3. View My Marks");
        System.out.println("4. Exit");
        System.out.print("Enter choice: ");
    }
}
