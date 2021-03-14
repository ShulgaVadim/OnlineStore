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
    List<String> namesOfCategory;
    Set<Class<? extends Category>> subClassesForCategory = reflections.getSubTypesOf(Category.class);
    Faker faker = new Faker();
    int length = subClassesForCategory.size();

    public List<String> getNamesOfCategory() {
        return namesOfCategory = subClassesForCategory.stream().map(i -> i.getName())
                .collect(Collectors.toList());
    }

    public String getProductNameByCategoryName (String categoryNameField) {
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
        namesOfCategory = getNamesOfCategory();
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


