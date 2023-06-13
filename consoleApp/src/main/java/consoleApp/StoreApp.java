package consoleApp;

import store.Order.OrderProcessor;
import store.Store;
import store.StoreInteraction;
import store.database.DBException;
import store.database.DBHelper;
import store.http.Server;
import store.http.StoreHttpClient;
import java.io.IOException;
import java.util.function.Consumer;

public class StoreApp {
    private static final String SERVER_BASE_URL = "http://localhost:8081";

    public static void main(String[] args) throws DBException, IOException, InterruptedException {
        // Initialize the server, database, and store
        Consumer<String> printCallback = System.out::println;
        DBHelper dbHelper = new DBHelper(printCallback);
        Server httpServer = new Server();
        httpServer.start();
        dbHelper.clearDB();
        dbHelper.initializeDatabase();
        dbHelper.fillStoreRandomly();
        dbHelper.printFilledStore();
        dbHelper.sortCategories();
        dbHelper.printTopProductsByPrice(5);
        StoreHttpClient.clientOrder();

        // Start the periodic cleanup task
        OrderProcessor.getInstance().startPeriodicCleanup();

        // Start the store interaction loop
        StoreInteraction.execStoreInteraction(Store.getInstance());

        // Use the client to interact with the server
    }
}