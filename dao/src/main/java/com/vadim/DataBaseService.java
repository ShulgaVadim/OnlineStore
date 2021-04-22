package com.vadim;

import com.vadim.dao.CategoryDao;
import com.vadim.dao.ProductDao;
import com.vadim.domain.product.Category;
import com.vadim.domain.product.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseService {

    CategoryDao categoryDao = new CategoryDao();
    ProductDao productDao = new ProductDao();

    public List<Category> getListOfCategoriesFromDB() throws SQLException {
        List<Category> categoryList = categoryDao.getAll();
        List<Category> categories = new ArrayList<>();
        try {
            for (Category c : categoryList) {
                c.setProducts(getProductListFromCategory(c));
                categories.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            categoryDao.getConnection().close();
        }
        return categories;
    }

    public List<Product> getProductListFromCategory(Category category) {
        List<Product> productList = new ArrayList<>();
        try {
            productList = productDao.getByCategoryId(category.getCategoryId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }
}
