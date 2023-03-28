package consoleApp;

import store.Store;
import store.StoreInteraction;
public class StoreApp {
    public static void main(String[] args) {

        Store store = Store.getInstance();
        StoreInteraction.execStoreInteraction(store);
    }
}
