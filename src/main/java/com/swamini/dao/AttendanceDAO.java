package com.swamini.dao;

import com.swamini.model.Attendance;
import com.swamini.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;

public class AttendanceDAO {

    public boolean insertAttendance(int studentId, String date, String status) throws SQLException {
        String sql = "INSERT INTO attendance (student_id, date, status) VALUES (?, ?, ?)";

        Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, studentId);
        stmt.setString(2, date);
        stmt.setString(3, status);

        int rows = stmt.executeUpdate();
        stmt.close();
        return rows > 0;
    }

    public ArrayList<Attendance> getAttendanceByStudent(int studentId) throws SQLException {
        String sql = "SELECT * FROM attendance WHERE student_id = ? ORDER BY date";

        ArrayList<Attendance> list = new ArrayList<>();

        Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, studentId);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Attendance a = new Attendance(
                    rs.getInt("id"),
                    rs.getInt("student_id"),
                    rs.getString("date"),
                    rs.getString("status")
            );
            list.add(a);
        }

        rs.close();
        stmt.close();
        return list;
    }

    // ⚠️ NEW PATTERN — aggregate query (COUNT + SUM), only ONE row comes back,
    // but the columns are CALCULATED, not direct table columns.
    public int[] getAttendanceCounts(int studentId) throws SQLException {
        String sql = "SELECT " +
                "COUNT(*) AS total, " +
                "SUM(CASE WHEN status='PRESENT' THEN 1 ELSE 0 END) AS present " +
                "FROM attendance WHERE student_id = ?";

        Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, studentId);
        ResultSet rs = stmt.executeQuery();

        int total = 0, present = 0;
        if (rs.next()) {
            // notice: rs.getInt("total") — "total" is the ALIAS we wrote
            // after AS in the SQL, NOT a real column name in the table!
            total = rs.getInt("total");
            present = rs.getInt("present");
        }

        rs.close();
        stmt.close();
        return new int[]{total, present};
    }
}
