package consoleApp;

import store.helpers.StoreHelper;
import store.Store;
public class StoreApp {
    public static void main(String[] args) {

        Store store = new Store();
        StoreHelper helper = new StoreHelper(store);
        helper.fillStoreRandomly();
        store.setStore();
    }
}
