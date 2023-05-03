package store.Order;

import products.Product;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ProductStorage {
    private final ConcurrentLinkedQueue<Product> purchasedProductQueue = new ConcurrentLinkedQueue<>();

    private ProductStorage() {
    }

    private static class SingletonHelper {
        private static final ProductStorage PRODUCT_STORAGE = new ProductStorage();
    }

    public static ProductStorage getInstance() {
        return SingletonHelper.PRODUCT_STORAGE;
    }

    public void addPurchasedProduct(Product product) {
        purchasedProductQueue.add(product);
    }

    public void clearPurchasedProductList() {
        purchasedProductQueue.clear();
    }

    public void printPurchasedProducts() {
        System.out.println("-----------------------------");
        System.out.println("Products are added to Queue:");
        int index = 0;
        for (Product product : purchasedProductQueue) {
            index++;
            System.out.println(index + ": " + product);
        }
        System.out.println("-----------------------------");
        System.out.println(index + " products are in the Queue");
        System.out.println("-----------------------------");
    }

    public void printClearedList() {
        System.out.println("-----------------------------");
        System.out.println("Product was removed from the Queue");
        Product product = purchasedProductQueue.poll();
        if (product != null) {
            System.out.println(product);
        } else {
            System.out.println("Queue is empty");
        }
        System.out.println("-----------------------------");
        System.out.println(purchasedProductQueue.size() + " products are in the Queue");
        System.out.println("-----------------------------");
    }
}