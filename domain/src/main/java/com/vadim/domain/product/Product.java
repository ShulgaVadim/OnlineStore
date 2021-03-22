package com.vadim.domain.product;

import com.vadim.domain.product.Category;
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

