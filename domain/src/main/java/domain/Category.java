package domain;

import products.Product;
import store.Comparator.MultiFieldComparator;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private final String categoryName;
    private List<Product> productList;

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void addProduct(Product product) {
        if (productList == null) {
            productList = new ArrayList<>();
        }
        productList.add(product);
    }

    public int getProductSize() {
        return productList.size();
    }

    public List<Product> getProductList(String sortKey) {
        if (sortKey == null || sortKey.isBlank()) {
            sortKey = "price"; // use "price" as default sort key
        }
        return MultiFieldComparator.sortProductList(productList, sortKey);
    }

    public void sort(String field) {
        StringBuilder info = new StringBuilder();
        info.append(String.format("%s category:%n", getCategoryName()));
        List<Product> plist = MultiFieldComparator.sortProductList(productList, "");
        for (Product product : plist) {
            info.append(product);
        }
        info.append(String.format("Number of products: %s%n", getProductSize()));
        System.out.println(info);
    }
}
