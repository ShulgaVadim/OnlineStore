import java.util.ArrayList;
import java.util.Map;

public class StoreApp {

    public static void main(String[] args) {

        RandomStorePopulator populator = new RandomStorePopulator();
        ArrayList<Product> products = populator.populateTheStore();
        Store evroOpt = new Store(products);
        evroOpt.printProducts(products); //print all products from myStore

        String path = System.getProperty("user.dir") + "/store/src/main/resources/sort.xml";
        DOMxmlReader reader = new DOMxmlReader();
        Map<String, String> sortCondition = reader.XMLreader(path);

        ArrayList<Product> sortedProducts = evroOpt.sortProduct(sortCondition, "price");
        System.out.println("\n ********* sorting *********\n");
        for (int i = 0; i < sortedProducts.size(); i++) {
            System.out.println(sortedProducts.get(i)); //print all products sorted via price desc
        }

        evroOpt.showTopProductsByPriceDesc(5); //print top 5 products sorted via price desc
    }
}


