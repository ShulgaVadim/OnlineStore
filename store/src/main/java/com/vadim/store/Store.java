package com.vadim.store;

import com.vadim.domain.product.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
public class Store {
    List<Category> categories;
}
