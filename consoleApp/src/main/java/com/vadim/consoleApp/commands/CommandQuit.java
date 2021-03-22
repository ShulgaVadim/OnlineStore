package com.vadim.consoleApp.commands;

import com.vadim.store.Store;

public class CommandQuit extends Command {

    Store store;

    public CommandQuit(Store store) {
        this.store = store;
    }

    public void quit() {
        System.out.println("Thanks for visiting our store");
        System.exit(0);
    }

}
