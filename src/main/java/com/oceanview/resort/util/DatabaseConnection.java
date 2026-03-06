package com.oceanview.resort.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private static volatile DatabaseConnection instance;

    private final String url;
    private final boolean integratedSecurity;
    private final String user;
    private final String password;

    private DatabaseConnection() {
        Properties props = new Properties();
        try (InputStream is = DatabaseConnection.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (is == null) {
                throw new IllegalStateException("db.properties not found in classpath");
            }
            props.load(is);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load db.properties", e);
        }

        this.url = getRequired(props, "db.url");
        this.integratedSecurity = Boolean.parseBoolean(props.getProperty("db.integratedSecurity", "false").trim());
        this.user = props.getProperty("db.user", "").trim();
        this.password = props.getProperty("db.password", "");

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("SQL Server JDBC driver not found", e);
        }
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnection.class) {
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        if (integratedSecurity) {
            String effectiveUrl = url;
            if (!effectiveUrl.toLowerCase().contains("integratedsecurity=")) {
                effectiveUrl = effectiveUrl + ";integratedSecurity=true";
            }
            return DriverManager.getConnection(effectiveUrl);
        }

        if (user.isEmpty()) {
            throw new SQLException("Database credentials not set. Set db.user and db.password in db.properties (or set db.integratedSecurity=true).",
                    "08001");
        }
        return DriverManager.getConnection(url, user, password);
    }

    private static String getRequired(Properties props, String key) {
        String value = props.getProperty(key);
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalStateException("Missing required property: " + key);
        }
        return value.trim();
    }
}
