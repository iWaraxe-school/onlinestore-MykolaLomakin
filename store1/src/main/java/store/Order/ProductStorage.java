package store.Order;

import products.Product;
import store.database.DBException;
import store.database.OrderDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ProductStorage {
    private final ConcurrentLinkedQueue<Product> purchasedProductQueue = new ConcurrentLinkedQueue<>();

    public ProductStorage() {
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

    public void printPurchasedProducts() {
        System.out.println("-----------------------------");
        System.out.println("Products are added to List:");
        int index = 0;
        for (Product product : purchasedProductQueue) {
            index++;
            System.out.println(index + ": " + product);
        }
        System.out.println("-----------------------------");
        System.out.println(index + " products are in the List");
        System.out.println("-----------------------------");
    }

    public void clearPurchasedProductList() {
        System.out.println("-----------------------------");
        System.out.println("Products removed from the Queue:");
        Product product;
        int removedCount = 0;
        while ((product = purchasedProductQueue.poll()) != null) {
            removedCount++;
            System.out.println(removedCount + ": " + product);
        }
        System.out.println("-----------------------------");
        System.out.println(removedCount + " products removed from the Queue");
        System.out.println("-----------------------------");
    }
}