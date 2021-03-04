import com.github.javafaker.Faker;
import org.reflections.Reflections;
import product.Category;
import product.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RandomStorePopulator {

    Reflections reflections = new Reflections("categories");
    List<String> namesOfCategory = new ArrayList<>();
    Set<Class<? extends Category>> subClassesForCategory = reflections.getSubTypesOf(Category.class);
    Faker faker = new Faker();

    public List<String> getNamesOfCategory() {
        for (int j = 0; j < subClassesForCategory.size(); j++) {
            namesOfCategory.add(subClassesForCategory.stream().collect(Collectors.toList())
                    .get(j).getName());
        }
        return namesOfCategory;
    }

    public String getProductNameByCategoryName(String categoryNameField) {
        String productName = null;
        if (categoryNameField.equals("categories.Fruit")) {
            productName = faker.food().fruit();
        } else if (categoryNameField.equals("categories.Ingredient")) {
            productName = faker.food().ingredient();
        } else if (categoryNameField.equals("categories.Spice")) {
            productName = faker.food().spice();
        } else if (categoryNameField.equals("categories.Vegetable")) {
            productName = faker.food().vegetable();
        }
        return productName;
    }

    public List<Product> populateTheStore() {
        List<Product> products = new ArrayList<>();
        String productName;
        String className;

        for (int i = 0; i < 20; i++) {
            int randomInt = (int) (Math.random() * subClassesForCategory.size());
            className = getNamesOfCategory().get(randomInt);
            productName = getProductNameByCategoryName(className);
            Product newProduct = null;
            try {
                newProduct = new Product(productName, faker.number().numberBetween(0, 11),
                        Double.valueOf(faker.commerce().price()), (Category) Class.forName(getNamesOfCategory()
                        .get(randomInt)).newInstance());
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            products.add(newProduct);
        }
        return products;
    }
}


