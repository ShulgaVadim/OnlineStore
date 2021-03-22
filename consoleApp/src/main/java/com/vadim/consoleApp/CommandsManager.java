package com.vadim.consoleApp;

import com.vadim.consoleApp.commands.CommandPrint;
import com.vadim.consoleApp.commands.CommandQuit;
import com.vadim.consoleApp.commands.CommandSort;
import com.vadim.consoleApp.commands.CommandTop;
import com.vadim.store.Store;
import java.util.Scanner;

public class CommandsManager {
    Store store;
    Scanner scanner;
    CommandPrint print;
    CommandSort sort;
    CommandTop top;
    CommandQuit quit;

    public CommandsManager(Scanner scanner, Store store) {
        this.store = store;
        this.scanner = scanner;
    }

    public void init() {
        sort = new CommandSort(store);
        top = new CommandTop(store);
        print = new CommandPrint(store);
        quit = new CommandQuit(store);
        scanner = new Scanner(System.in);
        showMenu();
        int command = scanner.nextInt();

        switch (command) {
            case 1:
                sort.sort(sort.getMapFromXml("sort.xml"));
                break;
            case 2:
                top.top("price", "desc");
                break;
            case 3:
                print.print(store.getProducts());
                break;
            case 4:
                quit.quit();
                break;
            default:
                System.out.println("Incorrect command.");
                init();
        }
        scanner.close();
    }

    public void showMenu() {
        System.out.println("\nEnter command:");
        System.out.println("1 - sort products");
        System.out.println("2 - print top products sorted via price by desc");
        System.out.println("3 - print products");
        System.out.println("4 - exit app");
    }

}

