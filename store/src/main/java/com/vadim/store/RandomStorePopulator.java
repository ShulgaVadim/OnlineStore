package com.vadim.store;

import com.github.javafaker.Faker;
import org.reflections.Reflections;
import com.vadim.domain.product.Category;
import com.vadim.domain.product.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RandomStorePopulator {

    List<String> namesOfCategory;
    Faker faker = new Faker();

    public Set<Class<? extends Category>> getSubClassesForCategory() {
        Reflections reflections = new Reflections("com.vadim.domain.categories");
        Set<Class<? extends Category>> subClassesForCategory = reflections.getSubTypesOf(Category.class);
        return subClassesForCategory;
    }

    public int getSubClassesForCategorySize(Set<Class<? extends Category>> set) {
        return set.size();
    }

    public List<String> getNamesOfCategory() {
        return namesOfCategory = getSubClassesForCategory().stream().map(i -> i.getName())
                .collect(Collectors.toList());
    }

    public String getProductNameByCategoryName (String categoryNameField) {
        String productName = null;
        if (categoryNameField.equals("com.vadim.domain.categories.Fruit")) {
            productName = faker.food().fruit();
        } else if (categoryNameField.equals("com.vadim.domain.categories.Ingredient")) {
            productName = faker.food().ingredient();
        } else if (categoryNameField.equals("com.vadim.domain.categories.Spice")) {
            productName = faker.food().spice();
        } else if (categoryNameField.equals("com.vadim.domain.categories.Vegetable")) {
            productName = faker.food().vegetable();
        }
        return productName;
    }

    public List<Product> populateTheStore() {
        List<Product> products = new ArrayList<>();
        namesOfCategory = getNamesOfCategory();
        Set<Class<? extends Category>> subClassesForCategory = getSubClassesForCategory();
        int length = getSubClassesForCategorySize(subClassesForCategory);
        String productName;
        Product newProduct = null;

        for (int i = 0; i < 20; i++) {
            int randomInt = (int) (Math.random() * length);
            productName = getProductNameByCategoryName(namesOfCategory.get(randomInt));
            try {
                newProduct = new Product(productName, faker.number().numberBetween(0, 11),
                        Double.valueOf(faker.commerce().price()), (Category) Class.forName(namesOfCategory
                        .get(randomInt)).newInstance());
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            products.add(newProduct);
        }
        return products;
    }
}


