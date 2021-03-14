import product.Product;
import utility.DomXmlReader;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class Command {

    URL resource = StoreApp.class.getResource("sort.xml");
    DomXmlReader reader = new DomXmlReader();
    Map<String, String> sortCondition = reader.getMapFromXml(resource.getPath());

    public void quit() {
        System.exit(0);
    }

    public void showTop(Store store, int n) {
        List<Product> products = store.getProducts();
        System.out.println("\n *********sorting top 5 products*********\n");
        Comparator<Product> productPriceComparatorDesc = Comparator.comparing(Product::getPrice, Comparator.reverseOrder());
        products.stream().sorted(productPriceComparatorDesc).limit(n).collect(Collectors.toList()).forEach(System.out::println);
    }

    public void getCommandForStore(Store store, int n) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter command:");
        System.out.println("sort  - sort products");
        System.out.println("top   - print top " + n + "products sorted via price by desc");
        System.out.println("quit  - exit app");
        String command = scanner.nextLine();

        switch (command) {
            case ("sort"):
                store.multipleSort(store.getProducts(), sortCondition);
                break;
            case ("top"):
                showTop(store, n);
                break;
            case ("quit"):
                quit();
                break;
            default:
                System.out.println("Incorrect command.");
                getCommandForStore(store, n);
        }
        scanner.close();
    }
}
