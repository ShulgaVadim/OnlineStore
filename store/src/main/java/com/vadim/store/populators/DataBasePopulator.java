package com.vadim.store.populators;

import com.vadim.dao.CategoryDao;
import com.vadim.dao.ProductDao;
import com.vadim.domain.product.Category;
import com.vadim.domain.product.Product;
import com.vadim.exceptions.DbException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBasePopulator implements Populator {

    CategoryDao categoryDao = new CategoryDao();
    ProductDao productDao = new ProductDao();

    @Override
    public List<Category> getListOfCategories() {
        List<Category> categoryList = categoryDao.getAll();
        List<Category> categories = new ArrayList<>();
        for (Category c : categoryList) {
            c.setProducts(getProductListFromCategory(c));
            categories.add(c);
        }
        try {
            categoryDao.getConnection().close();
        } catch (SQLException e) {
            throw new DbException("Error: Connection has not been closed", e);
        }
        return categories;
    }

    public List<Product> getProductListFromCategory(Category category) {
        List<Product> productList = new ArrayList<>();
        try {
            productList = productDao.getByCategoryId(category.getCategoryId());
        } catch (SQLException e) {
            throw new DbException("Error to call getProductListFromCategory()", e);
        }
        return productList;
    }
}
