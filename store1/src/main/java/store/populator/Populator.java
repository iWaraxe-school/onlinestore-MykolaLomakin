package store.populator;

public interface Populator {
    String getProductName(String categoryName);

    Double getProductPrice();

    Double getProductRate();
}
