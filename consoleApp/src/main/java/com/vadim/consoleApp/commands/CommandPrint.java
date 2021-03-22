package com.vadim.consoleApp.commands;

import com.vadim.domain.product.Product;
import com.vadim.store.Store;

import java.util.List;

public class CommandPrint extends Command {

    Integer n;

    public CommandPrint(Store store, int n) {
        super(store);
        this.n = n;
    }

    public CommandPrint(Store store) {
        super(store);
    }

    @Override
    public void execute() {
        if(n!=null) {
            store.getProducts().stream()
                    .limit(n).forEach(System.out::println);
        }else{
            store.getProducts().stream()
                    .forEach(System.out::println);
        }
    }

}
