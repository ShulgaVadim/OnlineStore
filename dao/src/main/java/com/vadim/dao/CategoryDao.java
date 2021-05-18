package com.vadim.dao;

import com.vadim.DataBaseManager;
import com.vadim.domain.product.Category;
import com.vadim.exceptions.DbException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CategoryDao extends DataBaseManager implements Dao<Category> {

    @Override
    public void add(Category category) {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO CATEGORIES (ID, name) VALUES(?, ?)";
        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, category.getCategoryId());
            preparedStatement.setString(2, category.getCategoryName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Error to call add() method for Category", e);
        } finally {
            closeConnection(preparedStatement);
        }
    }

    @Override
    public List<Category> getAll() {
        List<Category> categoryList = new ArrayList<>();
        String sql = "SELECT * FROM CATEGORIES";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Category category = new Category();
                category.setCategoryId(resultSet.getInt("ID"));
                category.setCategoryName(resultSet.getString("name"));
                categoryList.add(category);
            }
        } catch (SQLException e) {
            throw new DbException("Error to call getAll() method for Category", e);
        } finally {
            closeConnection(statement);
        }
        return categoryList;
    }

    @Override
    public Category getById(Long id) {
        PreparedStatement preparedStatement = null;
        String sql = "SELECT ID, name FROM CATEGORIES WHERE ID=?";
        Category category = new Category();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            category.setCategoryId(resultSet.getInt("ID"));
            category.setCategoryName(resultSet.getString("name"));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Error to call getById() method for Category", e);
        } finally {
            closeConnection(preparedStatement);
        }
        return category;
    }

    @Override
    public void update(Category category) {
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE CATEGORIES SET name=? WHERE ID=?";
        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, category.getCategoryName());
            preparedStatement.setLong(2, category.getCategoryId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Error to call update() method for Category", e);
        } finally {
            closeConnection(preparedStatement);
        }
    }

    @Override
    public void remove(Category category) {
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM CATEGORIES WHERE ID=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, category.getCategoryId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Error to call remove() method for Category", e);
        } finally {
            closeConnection(preparedStatement);
        }
    }
}

