package com.vadim.consoleApp.commands;


import com.vadim.consoleApp.Sorter;
import com.vadim.store.Store;
import lombok.Data;

@Data
public class Command {
    Store store;

    void execute() {
    }
}
