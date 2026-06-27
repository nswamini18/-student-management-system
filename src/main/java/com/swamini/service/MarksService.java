// MarksService.java
package com.swamini.service;

import com.swamini.dao.MarksDAO;
import com.swamini.model.Marks;
import com.swamini.util.InputValidator;
import java.sql.SQLException;
import java.util.ArrayList;

public class MarksService {

    private MarksDAO marksDAO = new MarksDAO();

    public void addMarks(int studentId, String subject, int marks) {
        if (!InputValidator.isValidMarks(marks)) {
            System.out.println("❌ Marks must be between 0 and 100.");
            return;
        }

        try {
            boolean success = marksDAO.insertMarks(studentId, subject, marks);
            if (success) {
                System.out.println("✅ Marks added successfully!");
            }
        } catch (SQLException e) {
            if (e.getErrorCode() == 1452) {
                System.out.println("❌ Student ID does not exist!");
            } else {
                System.out.println("❌ Error: " + e.getMessage());
            }
        }
    }

    public void viewMarksByStudent(int studentId) {
        try {
            ArrayList<Marks> list = marksDAO.getMarksByStudent(studentId);

            System.out.println("\n--- Marks Record ---");
            System.out.println("| Subject\t\t| Marks\t|");
            System.out.println("-".repeat(35));

            if (list.isEmpty()) {
                System.out.println("⚠️  No marks found.");
                return;
            }

            int total = 0;
            for (Marks m : list) {
                total += m.getMarks();
                System.out.println("| " + m.getSubject() + "\t\t| " + m.getMarks() + "\t|");
            }

            System.out.println("-".repeat(35));
            System.out.printf("Average Marks : %.2f%n", (double) total / list.size());

        } catch (SQLException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    public void viewMarksAnalytics() {
        try {
            ArrayList<String[]> rows = marksDAO.getMarksAnalytics();

            if (rows.isEmpty()) {
                System.out.println("⚠️  No marks data available.");
                return;
            }

            System.out.println("\n--- Marks Analytics ---");
            System.out.println("| Subject\t| Avg\t| High\t| Low\t|");
            System.out.println("-".repeat(50));

            for (String[] row : rows) {
                System.out.println("| " + row[0] + "\t| " + row[1] + "\t| " + row[2] + "\t| " + row[3] + "\t|");
            }

        } catch (SQLException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}
