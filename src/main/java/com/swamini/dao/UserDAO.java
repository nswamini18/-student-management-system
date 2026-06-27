package com.swamini.dao;

import com.swamini.model.User;
import com.swamini.util.DBConnection;
import java.sql.*;

public class UserDAO {

    public User findByUsernameAndPassword(String username, String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

        Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, username);
        stmt.setString(2, password);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            User u = new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("role")
            );
            rs.close();
            stmt.close();
            return u;
        }

        rs.close();
        stmt.close();
        return null;  // no matching row → Service throws InvalidCredentials
    }
}
