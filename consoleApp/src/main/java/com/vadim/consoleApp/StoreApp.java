package com.vadim.consoleApp;

import com.vadim.store.Store;
import com.vadim.store.DataBaseHandler;

import java.sql.SQLException;
import java.util.Scanner;


public class StoreApp {

    public static void main(String[] args) throws SQLException {

//        RandomStorePopulator populator = new RandomStorePopulator();
        Scanner scanner = new Scanner(System.in);
        DataBaseHandler dbh = new DataBaseHandler();
//        dbh.createDB();
        Store evroOpt = new Store(dbh.getListOfCategoriesFromDB());

        CommandsManager commandsManager = new CommandsManager(scanner, evroOpt);
        commandsManager.init();
    }
}


