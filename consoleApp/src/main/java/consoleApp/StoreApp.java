package consoleApp;

import store.Order.OrderProcessor;
import store.Store;
import store.StoreInteraction;
import store.database.ConnectionManager;
import store.database.DBHelper;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class StoreApp {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, SQLException {
        Store store = Store.getInstance();

        DBHelper dbHelper = new DBHelper();
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
