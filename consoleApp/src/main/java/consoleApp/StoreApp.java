package consoleApp;

import store.Order.OrderProcessor;
import store.Store;
import store.StoreInteraction;

public class StoreApp {
    public static void main(String[] args) {
        Store store = Store.getInstance();

        // Start the periodic cleanup task
        OrderProcessor.getInstance().startPeriodicCleanup();

        // Start the store interaction loop
        StoreInteraction.execStoreInteraction(store);
    }
}
