package store.Order;

import products.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductStorage {
    private final List<Product> purchasedProductList = new ArrayList<>();

    private ProductStorage() {
    }

    private static class SingletoneHelper {
        private static final ProductStorage PRODUCT_STORAGE = new ProductStorage();
    }

    public static ProductStorage getInstance() {
        return SingletoneHelper.PRODUCT_STORAGE;
    }

    public synchronized void addPurchasedProduct(Product product) {
        purchasedProductList.add(product);
    }

    public synchronized void clearPurchasedProductList() {
        if (!purchasedProductList.isEmpty()) {
            purchasedProductList.remove(0);
        }
    }

    public void printPurchasedProducts() {
        System.out.println("-----------------------------");
        System.out.println("Products are added to List:");
        int index = 0;
        for (Product product : purchasedProductList) {
            index++;
            System.out.println(index + ": " + product);
        }
        System.out.println("-----------------------------");
        System.out.println(index + " products are in the List");
        System.out.println("-----------------------------");
    }

    public void printClearedList() {
        System.out.println("-----------------------------");
        System.out.println("Product was removed from the List:");
        if (!purchasedProductList.isEmpty()) {
            System.out.println(purchasedProductList.remove(0));
        } else {
            System.out.println("List is empty");
        }
        System.out.println("-----------------------------");
        System.out.println(purchasedProductList.size() + " products are in the List");
        System.out.println("-----------------------------");
    }
}