package com.vadim.consoleApp;

import com.vadim.store.Populator;
import com.vadim.store.Store;
import com.vadim.store.StorePopulator;

import java.sql.SQLException;
import java.util.Scanner;


public class StoreApp {

    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);
        StorePopulator sp = new StorePopulator();
        Store evroOpt = new Store(sp.populateStore(Populator.DB));

        CommandsManager commandsManager = new CommandsManager(scanner, evroOpt);
        commandsManager.init();
    }
}


