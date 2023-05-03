package store;

import products.Product;
import store.Order.OrderProcessor;
import store.helpers.StoreHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StoreInteraction {

    public static void execStoreInteraction(Store store) {

        StoreHelper storeHelper = new StoreHelper(store);
        storeHelper.fillStoreRandomly();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                System.out.println("The store interacts with using next commands: sort, top, order, quit: ");
                String input = reader.readLine();
                switch (input) {
                    case "sort" -> store.sort();
                    case "top" -> store.top();
                    case "order" -> {
                        Product product = store.getRandomProductFromStore();
                        if (product != null) {
                            OrderProcessor.getInstance().processOrder(product);
                        } else {
                            System.out.println("No products available for order.");
                        }
                    }
                    case "quit" -> {
                        System.out.println("Goodbye!");
                        return;
                    }
                    default -> System.out.println("Unknown command: " + input);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}