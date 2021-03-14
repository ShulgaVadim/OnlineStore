import product.Product;
import java.util.List;

public class StoreApp {

    public static void main(String[] args) {

        RandomStorePopulator populator = new RandomStorePopulator();
        List<Product> products = populator.populateTheStore();
        Store evroOpt = new Store(products);
        evroOpt.printProducts();

        Command clientCommand = new Command();
        clientCommand.getCommandForStore(evroOpt, 5);
    }
}


