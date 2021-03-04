import product.Product;
import utility.DomXmlReader;

import java.net.URL;
import java.util.List;
import java.util.Map;

public class StoreApp {

    public static void main(String[] args) {
        RandomStorePopulator populator = new RandomStorePopulator();
        List<Product> products = populator.populateTheStore();
        Store evroOpt = new Store(products);
        evroOpt.printProducts(); //print all products from myStore

        URL resource = StoreApp.class.getResource("sort.xml");
        DomXmlReader reader = new DomXmlReader();
        Map<String, String> sortCondition = reader.XmlReader(resource.getPath());
        evroOpt.multipleSort(sortCondition);

        Command clientCommand = new Command();
        clientCommand.getCommandForStore(evroOpt);
    }
}


