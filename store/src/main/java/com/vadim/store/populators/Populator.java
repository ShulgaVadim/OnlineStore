package com.vadim.store.populators;

import com.vadim.domain.product.Category;

import java.util.List;

public interface Populator {

    List<Category> getListOfCategories();
}
