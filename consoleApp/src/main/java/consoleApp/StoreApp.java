package consoleApp;

import store.Order.ClearOrder;
import store.Store;
import store.StoreInteraction;
public class StoreApp {
    public static void main(String[] args) {

        Store store = Store.getInstance();
        new ClearOrder().start();
        StoreInteraction.execStoreInteraction(store);

    }
}
