package store.helpers;


import domain.Category;
import org.reflections.Reflections;
import products.Product;
import store.Store;
import store.populator.Populator;
import store.populator.RandomStorePopulator;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class StoreHelper {
    private Store store;
    private Populator populator;

    public StoreHelper(Store store) {
        this(store, new RandomStorePopulator());
    }

    public StoreHelper(Store store, Populator populator) {
        this.store = store;
        this.populator = populator;
    }

    public void fillStoreRandomly() {
        Map<Category, Integer> categoryProductsMapToAdd = createProductListToAdd();

        for (Map.Entry<Category, Integer> entry : categoryProductsMapToAdd.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                Product product = Product.newProductBuilder()
                        .setName(populator.getProductName(entry.getKey().getCategoryName()))
                        .setRate(populator.getProductRate())
                        .setPrice(populator.getProductPrice())
                        .build();
                entry.getKey().addProduct(product);
            }
            store.addCategoryToStore(entry.getKey());
        }
    }

    private static Map<Category, Integer> createProductListToAdd() {
        Map<Category, Integer> productToAdd = new HashMap<>();

        Reflections reflections = new Reflections("domain.categories");
        Set<Class<? extends Category>> subTypes = reflections.getSubTypesOf(Category.class);

        for (Class<? extends Category> type : subTypes) {
            try {
                Random random = new Random();
                productToAdd.put(type.getConstructor().newInstance(), random.nextInt(10) + 1);
            } catch (InstantiationException | NoSuchMethodException | InvocationTargetException |
                     IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return productToAdd;
    }
}
