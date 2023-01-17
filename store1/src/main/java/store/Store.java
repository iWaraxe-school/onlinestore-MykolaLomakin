package store;

import domain.Category;

import java.util.ArrayList;
import java.util.List;
public class Store {
    private List<Category> categoryList;
    public Store() {
        categoryList = new ArrayList<>();
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }
}
