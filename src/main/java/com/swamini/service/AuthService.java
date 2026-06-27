// AuthService.java
package com.swamini.service;

import com.swamini.dao.UserDAO;
import com.swamini.exception.InvalidCredentials;
import com.swamini.model.User;
import java.sql.SQLException;

public class AuthService {

    private UserDAO userDAO = new UserDAO();

    public User login(String username, String password) {
        try {
            User u = userDAO.findByUsernameAndPassword(username, password);
            if (u == null) {
                throw new InvalidCredentials();
            }
            return u;
        } catch (SQLException e) {
            System.out.println("❌ Login error: " + e.getMessage());
            return null;
        }
    }
}
