package com.vadim.dao;

import com.vadim.DataBaseManager;
import com.vadim.domain.product.Product;
import com.vadim.exceptions.DbException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao extends DataBaseManager implements Dao<Product> {

    @Override
    public void add(Product product) {
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO PRODUCTS (id, productName, categoryId, rate, price) VALUES(?, ?, ?, ?, ?)";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, product.getId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setLong(3, product.getCategoryId());
            preparedStatement.setInt(4, product.getRate());
            preparedStatement.setDouble(5, product.getPrice());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Error to call add() method for Product", e);
        } finally {
            closeConnection(preparedStatement);
        }
    }


    @Override
    public List<Product> getAll() {
        System.out.println("List<Product> getAll() run");
        List<Product> productList = new ArrayList<>();

        String sql = "SELECT id, productName, categoryId, rate, price FROM PRODUCTS";

        Statement statement = null;
        try {
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("productName"));
                product.setCategoryId(resultSet.getInt("categoryId"));
                product.setRate(resultSet.getInt("rate"));
                product.setPrice(resultSet.getDouble("price"));
                productList.add(product);
            }
        } catch (SQLException e) {
            throw new DbException("Error to call getAll() method for Product", e);
        } finally {
            closeConnection(statement);
        }
        return productList;
    }

    @Override
    public Product getById(Long id) {
        PreparedStatement preparedStatement = null;

        String sql = "SELECT id, productName, categoryId, rate, price FROM PRODUCTS WHERE id=?";

        Product product = new Product();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            product.setId(resultSet.getInt("id"));
            product.setName(resultSet.getString("productName"));
            product.setCategoryId(resultSet.getInt("categoryId"));
            product.setRate(resultSet.getInt("rate"));
            product.setPrice(resultSet.getDouble("price"));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Error to call getById() method for Product", e);
        } finally {
            closeConnection(preparedStatement);
            System.out.println("Product getById is closed");
        }
        return product;
    }

    @Override
    public void update(Product product) {
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE PRODUCTS SET productName=?, categoryId=?, rate=?, price=? WHERE ID=?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getCategoryId());
            preparedStatement.setInt(3, product.getRate());
            preparedStatement.setDouble(4, product.getPrice());
            preparedStatement.setInt(5, product.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Error to call update() method for Product", e);
        } finally {
            closeConnection(preparedStatement);
        }
    }

    @Override
    public void remove(Product product) {
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM PRODUCTS WHERE ID=?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, product.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Error to call remove() method for Product", e);
        } finally {
            closeConnection(preparedStatement);
        }
    }

    public List<Product> getByCategoryId(int id) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "SELECT * FROM PRODUCTS WHERE categoryId=?";

        List<Product> productList = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("productName"));
                product.setCategoryId(resultSet.getInt("categoryId"));
                product.setRate(resultSet.getInt("rate"));
                product.setPrice(resultSet.getDouble("price"));
                productList.add(product);
            }
        } catch (SQLException e) {
            throw new DbException("Error to call getByCategoryId() method for Product", e);
        } finally {
            preparedStatement.close();
        }
        return productList;
    }
}
