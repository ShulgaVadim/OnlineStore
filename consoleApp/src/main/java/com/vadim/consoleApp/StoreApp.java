package com.vadim.consoleApp;

import com.vadim.consoleApp.commands.Command;
import com.vadim.domain.product.Product;
import com.vadim.store.RandomStorePopulator;
import com.vadim.store.Store;
import java.util.List;
import java.util.Scanner;


public class StoreApp {

    public static void main(String[] args) {

        RandomStorePopulator populator = new RandomStorePopulator();
        Scanner scanner = new Scanner (System.in);
        List<Product> products = populator.populateTheStore();
        Store evroOpt = new Store(products);

        CommandsManager commandsManager = new CommandsManager(scanner,evroOpt);
        commandsManager.init();
    }
}


