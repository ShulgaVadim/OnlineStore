package com.vadim.consoleApp.commands;

import com.vadim.store.Store;

public class CommandQuit extends Command {

    public CommandQuit(Store store) {
        super(store);
    }

    @Override
    public void execute() {
        System.out.println("Thanks for visiting our store");
        System.exit(0);
    }
}
