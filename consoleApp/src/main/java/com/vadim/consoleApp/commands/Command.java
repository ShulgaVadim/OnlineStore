package com.vadim.consoleApp.commands;

import com.vadim.store.Store;
import lombok.Data;

@Data
public abstract class Command {

    protected Store store;

    public Command(Store store) {
        this.store = store;
    }

    public abstract void execute();
}
