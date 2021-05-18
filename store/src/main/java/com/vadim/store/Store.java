package com.vadim.store;

import com.vadim.domain.product.Category;
import com.vadim.domain.product.Product;
import lombok.*;
import java.util.*;

@Getter
@Setter
public class Store {

    private List<Category> categories;
    private List<Product> purchasedGoods;

    public Store(List<Category> categories) {
        this.categories = categories;
    }

}
