package com.vadim.consoleApp;

import com.vadim.store.populators.DataBasePopulator;
import com.vadim.store.populators.Populator;
import com.vadim.store.Store;

import java.util.Scanner;


public class StoreApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Populator populator = new DataBasePopulator();
        Store evroOpt = new Store(populator.getListOfCategories());

        CommandsManager commandsManager = new CommandsManager(scanner, evroOpt);
        commandsManager.init();
    }
}


