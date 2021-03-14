import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import product.Product;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
public class Store {

    List<Product> products;

    public void printProducts() {
        for (Product p : products) {
            System.out.println(p);
        }
    }

    public void multipleSort(List<Product> products, Map<String, String> map) {
        System.out.println("******* Sorting by " + map.entrySet() + " ********");
        List<String> sortingKey = new ArrayList<>();
        List<String> sortingValue = new ArrayList<>();
        for (Map.Entry<String, String> item : map.entrySet()) {
            sortingKey.add(item.getKey());
            sortingValue.add(item.getValue());
        }
        Comparator<Product> comparator = Comparator.comparing(getFunction(sortingKey.get(0)), getComparator(sortingValue.get(0)));
        for (int i = 1; i < map.size(); i++) {
            comparator = comparator.thenComparing(getFunction(sortingKey.get(i)), getComparator(sortingValue.get(i)));
        }
        products.stream().sorted(comparator).collect(Collectors.toList()).forEach(System.out::println);

    }

    public Function getFunction(String name) {
        if (name.equals("name")) {
            Function<? super Product, ? extends String> f = Product::getName;
            return f;
        }
        if (name.equals("price")) {
            Function<? super Product, ? extends Double> f = Product::getPrice;
            return f;
        }
        if (name.equals("rate")) {
            Function<? super Product, ? extends Integer> f = Product::getRate;
            return f;
        }
        return null;
    }

    public Comparator getComparator(String order) {
        Comparator comparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return 0;
            }
        };
        if (order.equals("asc")) {
            comparator = Comparator.naturalOrder();
        }
        if (order.equals("desc")) {
            comparator = Comparator.reverseOrder();
        }
        return comparator;
    }
}
