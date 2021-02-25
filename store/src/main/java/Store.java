import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

@AllArgsConstructor
public class Store {

    ArrayList<Product> products;

    public void printProducts(ArrayList<Product> products) {
        for (int i = 0; i < products.size(); i++) {
            System.out.println(products.get(i));
        }
    }

    public ArrayList<Product> sortByNameAsc() {
        ProductNameComparator nameComparator = new ProductNameComparator();
        ArrayList<Product> sortedByName = (ArrayList<Product>) products.stream()
                .sorted(nameComparator).collect(Collectors.toList());
        return sortedByName;
    }

    public ArrayList<Product> sortByNameDesc() {
        ProductNameComparator nameComparator = new ProductNameComparator();
        ArrayList<Product> sortedByName = (ArrayList<Product>) products.stream()
                .sorted(nameComparator.reversed()).collect(Collectors.toList());
        return sortedByName;
    }


    public ArrayList<Product> sortByPriceAsc() {
        ProductPriceComparator priceComparator = new ProductPriceComparator();
        ArrayList<Product> sortedByPrice = (ArrayList<Product>) products.stream()
                .sorted(priceComparator).collect(Collectors.toList());
        return sortedByPrice;
    }

    public ArrayList<Product> sortByPriceDesc() {
        ProductPriceComparator priceComparator = new ProductPriceComparator();
        ArrayList<Product> sortedByPrice = (ArrayList<Product>) products.stream()
                .sorted(priceComparator.reversed()).collect(Collectors.toList());
        return sortedByPrice;
    }


    public ArrayList<Product> sortByRatingAsc() {
        ProductRatingComparator ratingComparator = new ProductRatingComparator();
        ArrayList<Product> sortedByPrice = (ArrayList<Product>) products.stream()
                .sorted(ratingComparator).collect(Collectors.toList());
        return sortedByPrice;
    }

    public ArrayList<Product> sortByRatingDesc() {
        ProductRatingComparator ratingComparator = new ProductRatingComparator();
        ArrayList<Product> sortedByPrice = (ArrayList<Product>) products.stream()
                .sorted(ratingComparator.reversed()).collect(Collectors.toList());
        return sortedByPrice;
    }


    public ArrayList<Product> sortProduct(Map<String, String> map, String sortingKey) {
        ArrayList<Product> sortedListOfProducts = null;
        String value = map.get(sortingKey);
        switch (sortingKey) {
            case "name":
                if (value.equals("asc")) {
                    sortedListOfProducts = sortByNameAsc();
                } else if (value.equals("desc")) {
                    sortedListOfProducts = sortByNameDesc();
                }
                break;
            case "price":
                if (value.equals("asc")) {
                    sortedListOfProducts = sortByPriceAsc();
                } else if (value.equals("desc")) {
                    sortedListOfProducts = sortByPriceDesc();
                }
                break;
            case "rate":
                if (value.equals("asc")) {
                    sortedListOfProducts = sortByRatingAsc();
                } else if (value.equals("desc")) {
                    sortedListOfProducts = sortByRatingDesc();
                }
                break;
        }

        return sortedListOfProducts;
    }

    public void showTopProductsByPriceDesc(int n) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n Enter 'top' if you want to see 5 products sorted via price desc\n" +
                " or 'quit' to exit app");
        String command = scanner.nextLine();
        if (command.equals("top")) {
            ArrayList<Product> top5Products = sortByPriceDesc();
            System.out.println("\n *********sorting top " + n + " products*********\n");
            for (int i = 0; i < n; i++) {
                System.out.println(top5Products.get(i));
            }
        } else if (command.equals("quit")) {
            System.exit(0);
        } else {
            System.out.println("Incorrect command.");
        }
        scanner.close();
    }
}