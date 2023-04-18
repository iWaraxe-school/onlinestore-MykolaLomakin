package store.populator;


// Common interface for all strategies
public interface Populator {
    String getProductName(String categoryName);

    Double getProductPrice();

    Double getProductRate();
}
