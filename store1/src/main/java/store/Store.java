package store;

import domain.Category;

import java.util.ArrayList;
import java.util.List;
public class Store {
    private final List<Category> categoryList;

    public Store() {
        categoryList = new ArrayList<>();
    }

    public void addCategoryToStore(Category category) {
        categoryList.add(category);
    }

    public void setStore() {
        System.out.println("My online store");
        for (Category category : categoryList) {
            category.printCategory();
        }
    }
}
