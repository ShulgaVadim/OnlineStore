package com.vadim;


import com.vadim.exceptions.DbException;
import com.vadim.exceptions.InitException;
import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

@Getter
public class DataBaseManager {
    String driver = getPropertyValue("DB_DRIVER");
    String url = getPropertyValue("DB_URL");
    String username = getPropertyValue("DB_USERNAME");
    String password = getPropertyValue("DB_PASSWORD");

    public Connection connection;

    public DataBaseManager() {
        this.connection = getDBConnection();
    }

    public Connection getDBConnection() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            throw new DbException("Failed to make connection!", e);
        }
        return connection;
    }

    public String getPropertyValue(String propertyName) {
        String propertyValue = "";
        Properties properties = new Properties();

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            properties.load(inputStream);
            propertyValue = properties.getProperty(propertyName);
        } catch (IOException e) {
            throw new InitException("Loading properties error");
        }
        return propertyValue;
    }

    public void closeConnection(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new DbException("Error: Connection has not been closed");
        }
    }
}
