package com.swamini.dao;

import com.swamini.model.Marks;
import com.swamini.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;

public class MarksDAO {

    public boolean insertMarks(int studentId, String subject, int marks) throws SQLException {
        String sql = "INSERT INTO marks (student_id, subject, marks) VALUES (?, ?, ?)";

        Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, studentId);
        stmt.setString(2, subject);
        stmt.setInt(3, marks);

        int rows = stmt.executeUpdate();
        stmt.close();
        return rows > 0;
    }

    public ArrayList<Marks> getMarksByStudent(int studentId) throws SQLException {
        String sql = "SELECT * FROM marks WHERE student_id = ? ORDER BY subject";

        ArrayList<Marks> list = new ArrayList<>();

        Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, studentId);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Marks m = new Marks(
                    rs.getInt("id"),
                    rs.getInt("student_id"),
                    rs.getString("subject"),
                    rs.getInt("marks")
            );
            list.add(m);
        }

        rs.close();
        stmt.close();
        return list;
    }

    // ⚠️ NEW PATTERN — GROUP BY means MULTIPLE rows come back,
    // one row PER SUBJECT, each with its own AVG/MAX/MIN.
    // So this needs a while loop, like getAllStudents(), but
    // the columns are calculated per-group, not raw table columns.
    public ArrayList<String[]> getMarksAnalytics() throws SQLException {
        String sql = "SELECT subject, " +
                "ROUND(AVG(marks), 2) AS average, " +
                "MAX(marks) AS highest, " +
                "MIN(marks) AS lowest " +
                "FROM marks GROUP BY subject ORDER BY average DESC";

        ArrayList<String[]> result = new ArrayList<>();

        Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            String subject = rs.getString("subject");
            String average = String.valueOf(rs.getDouble("average"));
            String highest = String.valueOf(rs.getInt("highest"));
            String lowest  = String.valueOf(rs.getInt("lowest"));
            result.add(new String[]{subject, average, highest, lowest});
        }

        rs.close();
        stmt.close();
        return result;
    }
}