package com.vadim.consoleApp.commands;

import com.vadim.domain.product.Product;
import com.vadim.store.Store;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CommandTop extends CommandSort {

    public CommandTop(Store store) {
        super(store);
    }

    public List<Product> top(String sortKey, String sortValue) {
        System.out.printf("\n ********* sorting top 5 products by %s/%s *********\n", sortKey, sortValue);
        Map<String, String> map = createSingletonMap(sortKey, sortValue);
        return sort(map);
    }

    public  Map<String, String> createSingletonMap(String sortKey, String sortValue) {
        return Collections.singletonMap(sortKey, sortValue);
    }

}
