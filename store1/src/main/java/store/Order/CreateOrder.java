package store.Order;

import products.Product;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class CreateOrder implements Runnable {

    private final Product product;
    private final ProductAddedCallback callback;

    public CreateOrder(Product product, ProductAddedCallback callback) {
        this.product = product;
        this.callback = callback;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.printf("Thread name: %s%n", threadName);
        Product purchasedProduct = product;
        System.out.println("Ordered product: " + purchasedProduct);
        callback.onProductAdded(purchasedProduct);

        try {
            TimeUnit.SECONDS.sleep(new Random().nextInt(30) + 1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(threadName + " finished execution.");
    }
}