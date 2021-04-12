package com.vadim.store;

import com.vadim.domain.categories.Fruit;
import com.vadim.domain.categories.Ingredient;
import com.vadim.domain.categories.Spice;
import com.vadim.domain.categories.Vegetable;
import com.vadim.domain.product.Category;
import com.vadim.domain.product.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseHandler {

    private String driver = "org.h2.Driver";
    private String url = "jdbc:h2:~/store";
    private String username = "user";
    private String password = "password";
    private String sql;
    private Connection connection;
    private Statement statement;

    private void createTables() {
        sql = "CREATE TABLE CATEGORIES " + "(name VARCHAR(255) not NULL)";
        execUpdate(sql);
        sql = "CREATE TABLE PRODUCTS " +
                "(id INTEGER AUTO_INCREMENT, " +
                " productName VARCHAR(255) not NULL, " +
                " categoryName VARCHAR(255) not NULL, " +
                " rate INTEGER not NULL, " +
                " price DOUBLE not NULL, " +
                " PRIMARY KEY ( id ), " +
                " FOREIGN KEY ( categoryName ) REFERENCES CATEGORIES (name))";
        execUpdate(sql);
        closeConnection(connection, statement);
    }

    private void fillTables() {
        sql = "INSERT INTO CATEGORIES " + "VALUES ('Fruits'),('Ingredients'),('Spices'),('Vegetables')";
        execUpdate(sql);
        createDataForTables("Fruits");
        createDataForTables("Ingredients");
        createDataForTables("Spices");
        createDataForTables("Vegetables");
        closeConnection(connection, statement);
    }

    private void createDataForTables(String categoryName) {
        RandomStorePopulator r = new RandomStorePopulator();
        for (int i = 0; i < 10; i++) {
            sql = String.format("INSERT INTO PRODUCTS (productName, categoryName, rate, price) " + "VALUES ('%s', '%s', %d, %f)",
                    r.getProductNameByCategoryName(categoryName), categoryName,
                    r.faker.number().numberBetween(0, 11), Double.valueOf(r.faker.commerce().price()));
            execUpdate(sql);
        }
        closeConnection(connection, statement);
    }

    public void createDB() {
        createTables();
        fillTables();
    }

    public List<Category> getListOfCategoriesFromDB() throws SQLException {
        List<Category> categories = new ArrayList<>();
        sql = "SELECT * FROM CATEGORIES";
        ResultSet rs = execQuery(sql);
        try {
            while (rs.next()) {
                String categoryName = rs.getString("name");
                Category category = null;
                switch (categoryName) {
                    case ("Fruits"):
                        category = new Fruit();
                        break;
                    case ("Ingredients"):
                        category = new Ingredient();
                        break;
                    case ("Spices"):
                        category = new Spice();
                        break;
                    case ("Vegetables"):
                        category = new Vegetable();
                        break;
                }
                populateCategories(category);
                categories.add(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        closeConnection(connection, statement);
        return categories;
    }

    private void populateCategories(Category category) throws SQLException {
        List<Product> productList = new ArrayList<>();
        sql = String.format("SELECT * FROM PRODUCTS WHERE categoryName = '%s' ", category.getCategoryName());
        ResultSet rs = execQuery(sql);
        while (rs.next()) {
            productList.add(new Product(rs.getString("productName"), rs.getInt("rate"), rs.getDouble("price")));
        }
        category.setProducts(productList);
        closeConnection(connection, statement);
    }

    private ResultSet execQuery(String sql) {
        try {
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void execUpdate(String sql) {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void closeConnection(Connection c, Statement s) {
        try {
            s.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
