package com.vadim.domain.product;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class Product {

    String name;
    int rate;
    double price;

    @Override
    public String toString() {
        return  name +
                " - rate: " + rate +
                ", price: " + price;
    }
}

