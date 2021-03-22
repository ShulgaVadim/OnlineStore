package com.vadim.consoleApp.commands;

import com.vadim.domain.product.Product;
import com.vadim.store.Store;

import java.util.List;

public class CommandPrint extends Command {

    Integer n;
    List<Product> sortedProducts;

    //for print
    public CommandPrint(Store store) {
        super(store);
    }

    //for sort
    public CommandPrint(Store store, List<Product> sortedProducts) {
        super(store);
        this.sortedProducts = sortedProducts;
    }

    //for top
    public CommandPrint(Store store, int n, List<Product> sortedProducts) {
        super(store);
        this.n = n;
        this.sortedProducts = sortedProducts;
    }

    @Override
    public void execute() {
        if (n != null) {                                    //top
            sortedProducts.stream()
                    .limit(n).forEach(System.out::println);
        } else {
            if (sortedProducts != null) {                   //sort
                sortedProducts.stream()
                        .forEach(System.out::println);
            } else {                                        //print
                store.getProducts().stream()
                        .forEach(System.out::println);
            }
        }
    }
}
