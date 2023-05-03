package store;

import domain.Category;
import products.Product;
import store.Comparator.MultiFieldComparator;
import store.Order.OrderProcessor;
import store.populator.RandomStorePopulator;

import java.util.ArrayList;
import java.util.List;
public class Store {
    private final List<Category> categoryList;

    private static Store storeInstance;

    private Store() {
        categoryList = new ArrayList<>();
    }

    public void addCategoryToStore(Category category) {
        categoryList.add(category);
    }

    //singleton pattern
    public static Store getInstance() {
        if (storeInstance == null) {
            synchronized (Store.class) {
                if (storeInstance == null) {
                    storeInstance = new Store();
                }
            }
        }
        return storeInstance;
    }

    public void sort() {
        for (Category category : categoryList) {
            category.sort();
        }
    }


    public void top() {
        List<Product> productList = new ArrayList<>();
        for (Category category : categoryList) {
            productList.addAll(category.getProductList(""));
        }
        MultiFieldComparator.sortProductList(productList, "price");
        System.out.println("Top 5 products by price:");
        for (int i = 0; i < 5; i++) {
            System.out.println(productList.get(i));
        }
    }

    public void order() {
        Product product = getRandomProductFromStore();
        if (product != null) {
            OrderProcessor.getInstance().processOrder(product);
        } else {
            System.out.println("No products available for order.");
        }
    }

    public Product getRandomProductFromStore() {
        RandomStorePopulator products = new RandomStorePopulator();
        return new Product.ProductBuilder()
                .setName(products.getProductName("Bike"))
                .setRate(products.getProductRate())
                .setPrice(products.getProductPrice())
                .build();
    }
}
