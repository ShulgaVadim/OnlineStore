package com.vadim.domain.product;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    String name;
    int rate;
    double price;
    int categoryId;
    int id;

    public Product(String name, int rate, double price) {
        this.name = name;
        this.rate = rate;
        this.price = price;
    }

    @Override
    public String toString() {
        return  name +
                " - rate: " + rate +
                ", price: " + price;
    }
}

