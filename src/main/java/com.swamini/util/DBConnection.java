package com.swamini.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    private static final String URL;
    private static final String USER;
    private static final String PASSWORD;

    static {
        Properties props = new Properties();
        try (InputStream input = DBConnection.class
                .getClassLoader()
                .getResourceAsStream("db.properties")) {

            if (input == null) {
                throw new RuntimeException(
                        "db.properties not found! Copy db.properties.example " +
                                "to db.properties and fill in your real password."
                );
            }

            props.load(input);

        } catch (IOException e) {
            throw new RuntimeException("Could not load db.properties", e);
        }

        URL      = props.getProperty("db.url");
        USER     = props.getProperty("db.user");
        PASSWORD = props.getProperty("db.password");
    }

    private static Connection connection = null;

    private DBConnection() {}

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Database connected!");
        }
        return connection;
    }
}
