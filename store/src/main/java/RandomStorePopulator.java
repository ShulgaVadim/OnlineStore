import com.github.javafaker.Faker;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class RandomStorePopulator {

    public ArrayList<Product> populateTheStore() {

        Faker faker = new Faker();
        ArrayList<Product> products = new ArrayList<>();
        for (int i = 0; i <= 15; i++) {

            Category myCategory = null;
            try {
                Class categoryClass = Class.forName(Category.class.getName());
                Class[] params = {String.class};
                myCategory = (Category) categoryClass.getConstructor(params).newInstance(faker.food().dish());
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            }

            Product newProduct = Product.builder()
                    .name(faker.food().fruit())
                    .rate(faker.number().numberBetween(0, 11))
                    .price(Double.valueOf(faker.commerce().price()))
                    .category(myCategory)
                    .build();
            products.add(newProduct);
        }
        return products;
    }
}
