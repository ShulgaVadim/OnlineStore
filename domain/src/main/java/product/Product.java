package product;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class Product {

    String name;
    int rate;
    double price;
    Category category;


    @Override
    public String toString() {
        return "Product{" +
                "name: " + name +
                ", rating: " + rate +
                ", price: " + price +
                ", category: " + getCategory().getCategoryName() + "}";
    }

}

