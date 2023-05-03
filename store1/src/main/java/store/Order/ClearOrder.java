package store.Order;

public class ClearOrder implements Runnable {
    private final ProductStorage productStorage = ProductStorage.getInstance();

    @Override
    public void run() {
        System.out.println("Thread name: " + Thread.currentThread().getName());
        productStorage.clearPurchasedProductList();
        System.out.println("List cleared");
    }
}