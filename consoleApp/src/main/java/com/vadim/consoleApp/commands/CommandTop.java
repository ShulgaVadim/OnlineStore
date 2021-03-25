package com.vadim.consoleApp.commands;

import com.vadim.store.Store;

import java.util.Map;

public class CommandTop extends CommandSort {
    int n;

    public CommandTop(Store store, Map<String, String> sortOptions, int n) {
        super(store, sortOptions);
        this.n = n;
    }

    @Override
    public void execute() {
        print(sortCategory(), n);
    }
}
