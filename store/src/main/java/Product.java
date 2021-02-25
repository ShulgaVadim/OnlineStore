import lombok.*;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@Builder
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

