package com.vadim.store;

import com.github.javafaker.Faker;
import org.reflections.Reflections;
import com.vadim.domain.product.Category;
import com.vadim.domain.product.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RandomStorePopulator {

    Faker faker = new Faker();


    public String getProductNameByCategoryName(String categoryNameField) {
        String productName = null;

        if (categoryNameField.equals("Fruits")) {
            productName = faker.food().fruit();
        } else if (categoryNameField.equals("Ingredients")) {
            productName = faker.food().ingredient();
        } else if (categoryNameField.equals("Spices")) {
            productName = faker.food().spice();
        } else if (categoryNameField.equals("Vegetables")) {
            productName = faker.food().vegetable();
        }
        return productName;
    }

    private List<Product> populateCategories(String categoryNameField) {
        List<Product> productList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            productList.add(new Product(getProductNameByCategoryName(categoryNameField),
                    faker.number().numberBetween(0, 11),
                    Double.valueOf(faker.commerce().price())));
        }
        return productList;
    }

    public List<Category> populateTheStore() {
        List<Category> categories = getNamesOfCategory();
        for (Category c : categories) {
            c.setProducts(populateCategories(c.getCategoryName()));
        }
        return categories;
    }

    public Set<Class<? extends Category>> getSubClassesForCategory() {
        Reflections reflections = new Reflections("com.vadim.domain.categories");
        Set<Class<? extends Category>> subClassesForCategory = reflections.getSubTypesOf(Category.class);
        return subClassesForCategory;
    }

    public List<Category> getNamesOfCategory() {
        List<Category> namesOfCategory = new ArrayList<>();
        for (Class<? extends Category> cla : this.getSubClassesForCategory()) {
            try {
                namesOfCategory.add(cla.newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return namesOfCategory;
    }
}


