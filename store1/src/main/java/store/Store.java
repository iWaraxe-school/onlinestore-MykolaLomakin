package store;

import domain.Category;
import products.Product;
import store.Comparator.ProductComparator;

import java.util.ArrayList;
import java.util.List;
public class Store {
    public final List<Category> categoryList;

    private static Store storeInstance;

    public Store() {
        categoryList = new ArrayList<>();
    }

    public void addCategoryToStore(Category category) {
        categoryList.add(category);
    }

    public static Store getInstance() {
        if (storeInstance != null) {
            return storeInstance;
        }
        synchronized (Store.class) {
            if (storeInstance == null) {
                storeInstance = new Store();
            }
            return storeInstance;
        }
    }

    public void sort() {
        for (Category category : categoryList) {
            category.sort();
        }
    }

    public void top() {
        List<Product> productList = new ArrayList<>();
        for (Category category : categoryList) {
            productList.addAll(category.getProductList());
        }
        ProductComparator.sortProductReversed(productList, "price");
        System.out.println("Top 5 products by price:");
        for (int i = 0; i < 5; i++) {
            System.out.println(productList.get(i));
        }
    }
}
