package consoleApp;

import store.Order.OrderProcessor;
import store.Store;
import store.StoreInteraction;
import store.database.ConnectionManager;
import store.database.DBException;
import store.database.DBHelper;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.function.Consumer;

public class StoreApp {
    public static void main(String[] args) throws DBException {
        Store store = Store.getInstance();

        Consumer<String> printCallback = System.out::println;
        DBHelper dbHelper = new DBHelper(printCallback);
        dbHelper.clearDB();
        dbHelper.initializeDatabase();
        dbHelper.fillStoreRandomly();
        dbHelper.printFilledStore();
        dbHelper.sortCategories();
        dbHelper.printTopProductsByPrice(5);

        // Start the periodic cleanup task
        OrderProcessor.getInstance().startPeriodicCleanup();

        // Start the store interaction loop
        StoreInteraction.execStoreInteraction(store);

    }
}
