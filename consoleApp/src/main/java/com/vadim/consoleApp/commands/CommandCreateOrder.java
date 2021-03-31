package com.vadim.consoleApp.commands;

import com.vadim.domain.product.Product;
import com.vadim.store.Store;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CommandCreateOrder extends Command {

    private int categoryNumber;
    private int productNumber;
    Scanner scanner;

    public CommandCreateOrder(Store store, Scanner scanner) {
        super(store);
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        selectCategory();
        createOrder();
    }

    public void createOrder() {
        int enteredNum = scanner.nextInt();
        productNumber = enteredNum - 1;
        List<Product> productList = store.getCategories().get(categoryNumber).getProducts();
        if (productNumber >= 0 && productNumber < productList.size()) {
            ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(2);
            int timeOfProcess = 1 + (int) (Math.random() * 29);

            Runnable removeToPurchasedGoods = () -> {
                store.getPurchasedGoods().add(productList.get(productNumber));
            };
            scheduledExecutor.schedule(removeToPurchasedGoods, timeOfProcess, TimeUnit.SECONDS);
            System.out.println(productList.get(productNumber).getName() + " is purchased");

            Runnable cartCleaner = () -> {
                store.getPurchasedGoods().clear();
            };
            scheduledExecutor.scheduleAtFixedRate(cartCleaner, 2, 2, TimeUnit.MINUTES);

        } else {
            System.out.println("Enter please correct product number");
            createOrder();
        }
    }

    public void selectCategory() {
        showCategoryNumbers();
        int enteredNum = scanner.nextInt();
        if (enteredNum > 0 && enteredNum <= store.getCategories().size()) {
            categoryNumber = enteredNum - 1;
            System.out.println("Select product from category: " + store.getCategories().get(categoryNumber).getCategoryName());
            showProductsNumbers();
        } else {
            System.out.println("Incorrect category index. Enter correct number");
            selectCategory();
        }
    }

    public void showCategoryNumbers() {
        System.out.println("Please enter category number");
        int size = store.getCategories().size();
        for (int i = 1; i <= size; i++) {
            System.out.println(i + " - " + store.getCategories().get(i - 1).getCategoryName());
        }
    }

    public void showProductsNumbers() {
        int size = store.getCategories().get(categoryNumber).getProducts().size();
        for (int i = 1; i <= size; i++) {
            System.out.println(i + " - " + store.getCategories().get(categoryNumber).getProducts().get(i - 1));
        }
    }
}
