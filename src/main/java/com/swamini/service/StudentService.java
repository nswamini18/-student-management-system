package com.swamini.service;

import com.swamini.dao.StudentDAO;
import com.swamini.exception.StudentNotFound;
import com.swamini.model.Student;
import com.swamini.util.InputValidator;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentService {

    private StudentDAO studentDAO = new StudentDAO();

    public void addStudent(Student student) {
        if (!InputValidator.isValidName(student.getName())) {
            System.out.println("❌ Invalid name.");
            return;
        }
        if (!InputValidator.isValidAge(student.getAge())) {
            System.out.println("❌ Invalid age.");
            return;
        }
        if (!InputValidator.isValidEmail(student.getEmail())) {
            System.out.println("❌ Invalid email format.");
            return;
        }

        try {
            boolean success = studentDAO.insertStudent(student);
            if (success) {
                System.out.println("✅ Student added successfully!");
            }
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                System.out.println("❌ Student ID or email already exists!");
            } else {
                System.out.println("❌ Error: " + e.getMessage());
            }
        }
    }

    public void viewAllStudents() {
        try {
            ArrayList<Student> list = studentDAO.getAllStudents();

            if (list.isEmpty()) {
                System.out.println("⚠️  No students found.");
                return;
            }

            System.out.println("\n--- Student List ---");
            System.out.println("| ID\t| Name\t\t| Age\t| Dept\t\t| Email\t\t|");
            System.out.println("-".repeat(70));

            for (Student s : list) {
                System.out.println(s);
            }

            System.out.println("-".repeat(70));

        } catch (SQLException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    public void searchById(int id) {
        try {
            Student s = studentDAO.getStudentById(id);

            if (s == null) {
                throw new StudentNotFound(id);
            }

            System.out.println("\nStudent Found:");
            System.out.println(s);

        } catch (SQLException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    public void deleteStudent(int id) {
        try {
            boolean success = studentDAO.deleteStudent(id);

            if (success) {
                System.out.println("✅ Student deleted successfully!");
            } else {
                throw new StudentNotFound(id);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    public void updateStudent(int id, String name, int age, String dept, String email) {
        if (!InputValidator.isValidName(name)) {
            System.out.println("❌ Invalid name.");
            return;
        }
        if (!InputValidator.isValidAge(age)) {
            System.out.println("❌ Invalid age.");
            return;
        }

        try {
            Student student = new Student(id, name, age, dept, email);
            boolean success = studentDAO.updateStudent(student);

            if (success) {
                System.out.println("✅ Student updated successfully!");
            } else {
                throw new StudentNotFound(id);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}
