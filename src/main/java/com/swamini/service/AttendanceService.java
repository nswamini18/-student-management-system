// AttendanceService.java
package com.swamini.service;

import com.swamini.dao.AttendanceDAO;
import com.swamini.model.Attendance;
import com.swamini.util.InputValidator;
import java.sql.SQLException;
import java.util.ArrayList;

public class AttendanceService {

    private AttendanceDAO attendanceDAO = new AttendanceDAO();

    public void markAttendance(int studentId, String date, String status) {
        if (!InputValidator.isValidDate(date)) {
            System.out.println("❌ Invalid date. Use YYYY-MM-DD format.");
            return;
        }
        if (!status.equalsIgnoreCase("PRESENT") && !status.equalsIgnoreCase("ABSENT")) {
            System.out.println("❌ Status must be PRESENT or ABSENT.");
            return;
        }

        try {
            boolean success = attendanceDAO.insertAttendance(studentId, date, status.toUpperCase());
            if (success) {
                System.out.println("✅ Attendance marked successfully!");
            }
        } catch (SQLException e) {
            if (e.getErrorCode() == 1452) {
                System.out.println("❌ Student ID does not exist!");
            } else {
                System.out.println("❌ Error: " + e.getMessage());
            }
        }
    }

    public void viewAttendance(int studentId) {
        try {
            ArrayList<Attendance> list = attendanceDAO.getAttendanceByStudent(studentId);

            System.out.println("\n--- Attendance Record ---");
            System.out.println("| Date\t\t| Status\t|");
            System.out.println("-".repeat(35));

            if (list.isEmpty()) {
                System.out.println("⚠️  No attendance records found.");
                return;
            }

            for (Attendance a : list) {
                System.out.println("| " + a.getDate() + "\t| " + a.getStatus() + "\t|");
            }

        } catch (SQLException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    public void viewAttendancePercentage(int studentId) {
        try {
            int[] counts = attendanceDAO.getAttendanceCounts(studentId);
            int total = counts[0];
            int present = counts[1];

            if (total == 0) {
                System.out.println("⚠️  No attendance records found.");
                return;
            }

            double percentage = (present * 100.0) / total;
            System.out.println("\n--- Attendance Summary ---");
            System.out.println("Total Days  : " + total);
            System.out.println("Present     : " + present);
            System.out.println("Absent      : " + (total - present));
            System.out.printf("Percentage  : %.2f%%%n", percentage);

            if (percentage < 75) {
                System.out.println("⚠️  WARNING: Below 75% attendance!");
            } else {
                System.out.println("✅ Attendance is satisfactory.");
            }

        } catch (SQLException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}
