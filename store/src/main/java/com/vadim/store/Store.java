package com.vadim.store;

import com.vadim.domain.product.Category;
import com.vadim.domain.product.Product;
import lombok.*;
import java.util.*;

@Getter
@Setter
@RequiredArgsConstructor
public class Store {

    @NonNull
    private List<Category> categories;
    private List<Product> purchasedGoods;
}
