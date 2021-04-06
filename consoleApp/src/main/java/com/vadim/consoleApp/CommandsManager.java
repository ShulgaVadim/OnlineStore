package com.vadim.consoleApp;

import com.vadim.consoleApp.commands.*;
import com.vadim.store.Store;
import com.vadim.store.utility.MapHandler;

import java.util.*;

public class CommandsManager {
    Store store;
    Scanner scanner;
    List<Command> commands;

    public CommandsManager(Scanner scanner, Store store) {
        this.store = store;
        this.scanner = scanner;
        commands = Arrays.asList(
                new CommandPrint(store),
                new CommandSort(store, new MapHandler().getMapFromXml("sort.xml")),
                new CommandTop(store, new MapHandler().createSingletonMap("price", "desc"), 5),
                new CommandCreateOrder(store, scanner),
                new CommandQuit(store)
        );
    }

    public void init() {
        showMenu();
        int command = scanner.nextInt();
        if (command > commands.size() || command <= 0) {
            System.out.println("Incorrect command.");
            init();
        } else {
            commands.get(command - 1).execute();
        }
        scanner.close();
    }

    public void showMenu() {
        System.out.println("\nEnter command:");
        System.out.println("1 - print all store products");
        System.out.println("2 - sort products");
        System.out.println("3 - print top products sorted via price by desc");
        System.out.println("4 - create your order");
        System.out.println("5 - exit app");

    }
}

