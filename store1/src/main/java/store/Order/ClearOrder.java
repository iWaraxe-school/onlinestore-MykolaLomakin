package store.Order;

import java.util.concurrent.TimeUnit;

public class ClearOrder implements Runnable {
    private final ProductStorage productStorage = ProductStorage.getInstance();

    @Override
    public void run() {
        System.out.println("Thread name: " + Thread.currentThread().getName());
        productStorage.clearPurchasedProductList();
        System.out.println("List cleared");
        productStorage.printClearedList();
    }
}
