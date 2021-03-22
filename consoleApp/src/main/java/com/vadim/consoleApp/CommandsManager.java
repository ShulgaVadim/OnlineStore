package com.vadim.consoleApp;

import com.vadim.consoleApp.commands.Command;
import com.vadim.consoleApp.commands.CommandPrint;
import com.vadim.consoleApp.commands.CommandQuit;
import com.vadim.consoleApp.commands.CommandSort;
import com.vadim.consoleApp.commands.CommandTop;
import com.vadim.store.Store;
import com.vadim.store.utility.XmlReader;

import java.net.URL;
import java.util.*;

public class CommandsManager {
    Store store;
    Scanner scanner;

    List<Command> commands;

//    CommandPrint print;
//    CommandSort sort;
//    CommandTop top;
//    CommandQuit quit;

    public CommandsManager(Scanner scanner, Store store) {
        this.store = store;
        this.scanner = scanner;
        commands = Arrays.asList(
                new CommandSort(store, getMapFromXml("sort.xml")),
                new CommandSort(store, createSingletonMap("price", "desc")),
                new CommandPrint(store),
                new CommandQuit(store)
        );

    }

    public void init() {

        showMenu();
        int command = scanner.nextInt();

        if (command > commands.size() + 1) {
            System.out.println("Incorrect command.");
            init();
        } else {
            commands.get(command - 1).execute();
        }

//        switch (command) {
//            case 1:
//                sort.sort(sort.getMapFromXml("sort.xml"));
//                break;
//            case 2:
//                top.top("price", "desc");
//                break;
//            case 3:
//                print.print(store.getProducts());
//                break;
//            case 4:
//                quit.quit();
//                break;
//            default:
//                System.out.println("Incorrect command.");
//                init();
//        }
        scanner.close();
    }

    public void showMenu() {
        System.out.println("\nEnter command:");
        System.out.println("1 - sort products");
        System.out.println("2 - print top products sorted via price by desc");
        System.out.println("3 - print products");
        System.out.println("4 - exit app");
    }

    public Map<String, String> getMapFromXml(String fileName) {
        URL resource = CommandSort.class.getClassLoader().getResource(fileName);
        return new XmlReader().getSortConditions(resource.getPath());

    }

    public Map<String, String> createSingletonMap(String sortKey, String sortValue) {
        return Collections.singletonMap(sortKey, sortValue);
    }
}

