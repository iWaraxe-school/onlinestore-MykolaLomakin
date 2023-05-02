package store.Order;

import products.Product;
import store.Store;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class CreateOrder extends Thread {

    private final Store store = Store.getInstance();
    private final ProductStorage productStorage = ProductStorage.getInstance();

    @Override
    public void run(){
        System.out.println("Thread name: " + Thread.currentThread().getName());
        Product purchasedProduct = store.getRandomProductFromStore();
        System.out.println("Ordered product: " + purchasedProduct);
        productStorage.addPurchasedProduct(purchasedProduct);
        productStorage.printPurchasedProducts();

        try {
            TimeUnit.SECONDS.sleep(new Random().nextInt(3));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + " finished execution.");
    }
}
