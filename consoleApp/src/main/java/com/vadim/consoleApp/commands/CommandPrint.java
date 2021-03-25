package com.vadim.consoleApp.commands;

import com.vadim.domain.product.Category;
import com.vadim.store.Store;

import java.util.List;

public class CommandPrint extends Command {

    Integer n;
    List<Category> categoryList;

    public CommandPrint(Store store) {
        super(store);
    }

    @Override
    public void execute() {
        if (n != null) {                                    //top
            print(categoryList, n);
        } else {                                            //sort
            if (categoryList != null) {
                print(categoryList);
            } else {                                        //print
                print(store.getCategories());
            }
        }
    }

    void print(List<Category> listForPrint, int... size) {
        int sizeList;
        for (Category c : listForPrint) {
            System.out.println("\n********* " + c.getCategoryName() + " *********");
            int productPosition = 1;
            if (size.length > 0) {
                sizeList = size[0];
            } else {
                sizeList = c.getProducts().size();
            }
            for (int i = 0; i < sizeList; i++) {
                System.out.println("\t" + productPosition + ". " + c.getProducts().get(i));
                productPosition++;
            }
        }
    }
}













