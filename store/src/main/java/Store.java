import lombok.AllArgsConstructor;
import product.Product;

import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
public class Store {

    List<Product> products;

    public void printProducts() {
        for (Product p:products) {
            System.out.println(p);
        }
    }

    public List<Product>multipleSort(Map<String, String> map){
        List<Product> sortedListOfProducts = new ArrayList<>();
        for(Map.Entry<String, String> item : map.entrySet()){
            String key = item.getKey();
            String value = item.getValue();
            switch (key){
                case "name":
                    if (value.equals("asc")) {
                        sortedListOfProducts = products.stream().sorted(Comparator.comparing(Product::getName)).collect(Collectors.toList());
                    }else if (value.equals("desc")){
                        Comparator<Product> productNameComparatorDesc = Comparator.comparing(Product::getName, Comparator.reverseOrder());
                        sortedListOfProducts = products.stream().sorted(productNameComparatorDesc).collect(Collectors.toList());
                    }
                    System.out.println(String.format("*** Sorting by %s %s ***", key, value));
                    sortedListOfProducts.stream().forEach(System.out::println);
                    break;
                case "price":
                    if (value.equals("asc")) {
                        sortedListOfProducts = products.stream().sorted(Comparator.comparing(Product::getPrice)).collect(Collectors.toList());
                    }else if (value.equals("desc")){
                        Comparator<Product> productPriceComparatorDesc = Comparator.comparing(Product::getPrice, Comparator.reverseOrder());
                        sortedListOfProducts = products.stream().sorted(productPriceComparatorDesc).collect(Collectors.toList());
                    }
                    System.out.println(String.format("*** Sorting by %s %s ***", key, value));
                    sortedListOfProducts.stream().forEach(System.out::println);
                    break;
                case "rate":
                    if (value.equals("asc")) {
                        sortedListOfProducts = products.stream().sorted(Comparator.comparing(Product::getRate)).collect(Collectors.toList());
                    }else if (value.equals("desc")){
                        Comparator<Product> productRatingComparatorDesc = Comparator.comparing(Product::getRate, Comparator.reverseOrder());
                        sortedListOfProducts = products.stream().sorted(productRatingComparatorDesc).collect(Collectors.toList());
                    }
                    System.out.println(String.format("*** Sorting by %s %s ***", key, value));
                    sortedListOfProducts.stream().forEach(System.out::println);
                    break;
            }
        }
        return sortedListOfProducts;
    }

}