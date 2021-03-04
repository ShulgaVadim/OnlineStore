import product.Product;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Command {

    public void quit() {
        System.exit(0);
    }

    public void showTop(Store store) {
        List<Product> products = store.products;
        List<Product> topProducts;
        System.out.println("\n *********sorting top 5 products*********\n");
        Comparator<Product> productPriceComparatorDesc = Comparator.comparing(Product::getPrice, Comparator.reverseOrder());
        topProducts = products.stream().sorted(productPriceComparatorDesc).limit(5).collect(Collectors.toList());
        topProducts.forEach(System.out::println);
    }

    public void getCommandForStore (Store store) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n Enter 'top' if you want to see 5 products sorted via price desc\n for "
                + " or 'quit' to exit app");
        String command = scanner.nextLine();
        if (command.equals("top")) {
            showTop(store);
        } else if (command.equals("quit")) {
            quit();
        } else {
            System.out.println("Incorrect command.");
        }
        scanner.close();
    }
}
