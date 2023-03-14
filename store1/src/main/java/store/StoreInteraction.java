package store;

import store.helpers.StoreHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StoreInteraction {

    public static void execStoreInteraction(Store store) {

        StoreHelper storeHelper = new StoreHelper(store);
        storeHelper.fillStoreRandomly();

        try {

            boolean console = true;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("The store is created and filled with random products.");

            while (console) {

                System.out.println("The store interacts with using next commands: sort, top, quit:");
                String command = bufferedReader.readLine();

                switch (command) {
                    case "sort" -> store.sort();
                    case "top" -> store.top();
                    case "quit" -> {
                        bufferedReader.close();
                        console = false;
                    }
                    default -> System.out.println("Command is not supported.");
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }
}