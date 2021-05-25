package com.vadim.consoleApp;

import com.vadim.store.populators.HttpPopulator;
import com.vadim.store.http.LocalHttpServer;
import com.vadim.store.populators.Populator;
import com.vadim.store.Store;

import java.util.Scanner;


public class StoreApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Populator populator = new HttpPopulator();
        new LocalHttpServer().startServer();
        Store evroOpt = new Store(populator.getListOfCategories());

        CommandsManager commandsManager = new CommandsManager(scanner, evroOpt);
        commandsManager.init();
    }
}


