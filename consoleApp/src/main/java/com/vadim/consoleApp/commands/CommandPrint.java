package com.vadim.consoleApp.commands;

import com.vadim.domain.product.Product;
import com.vadim.store.Store;

import java.util.List;

public class CommandPrint extends Command {
    Store store;
    int n;

    public CommandPrint(Store store) {
        this.store = store;
    }

    public CommandPrint(Store store, int n) {
        this.store = store;
        this.n = n;
    }

    public void print(List<Product> list) {
        list.stream().limit(n).forEach(System.out::println);
    }

}
