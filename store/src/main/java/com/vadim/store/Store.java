package com.vadim.store;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import com.vadim.domain.product.Product;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor

public class Store {
    List<Product> products;

}
