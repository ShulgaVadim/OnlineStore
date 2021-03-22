package com.vadim.consoleApp.commands;

import com.vadim.domain.product.Product;
import com.vadim.store.Store;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CommandSort extends Command {

    Map<String, String> sortOptions;
    CommandPrint print;

    public CommandSort(Store store, Map<String, String> sortOptions) {
        super(store);
        this.sortOptions = sortOptions;
    }

    @Override
    public void execute() {
        print = new CommandPrint(store, multipleSort());
        print.execute();
    }

    public List<Product> multipleSort() {
        Comparator<Product> comparator = null;
        for (Map.Entry<String, String> item : sortOptions.entrySet()) {
            if (comparator == null) {
                comparator = Comparator.comparing(getFunction(item.getKey()), getComparator(item.getValue()));

            } else {
                comparator = comparator.thenComparing(getFunction(item.getKey()), getComparator(item.getValue()));
            }
        }
        return store.getProducts().stream().sorted(comparator).collect(Collectors.toList());
    }


    public Function getFunction(String name) {
        if (name.equals("name")) {
            Function<? super Product, ? extends String> f = Product::getName;
            return f;
        }
        if (name.equals("price")) {
            Function<? super Product, ? extends Double> f = Product::getPrice;
            return f;
        }
        if (name.equals("rate")) {
            Function<? super Product, ? extends Integer> f = Product::getRate;
            return f;
        }
        return null;
    }

    public Comparator getComparator(String order) {
        Comparator comparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return 0;
            }
        };
        if (order.equals("asc")) {
            comparator = Comparator.naturalOrder();
        }
        if (order.equals("desc")) {
            comparator = Comparator.reverseOrder();
        }
        return comparator;
    }
}

