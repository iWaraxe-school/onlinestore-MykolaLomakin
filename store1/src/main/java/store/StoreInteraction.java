package store;

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
                    System.out.println("The store interacts with using next commands: sort, top, quit: ");
                    String input = reader.readLine();
                    if (input.equals("sort")) {
                        store.sort();
                    }
                    if (input.equals("top")) {
                        store.top();
                    }
                    else if (input.equals("quit")) {
                        System.out.println("Goodbye!");
                        return;
                    } else {
                        System.out.println("Unknown command: " + input);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }