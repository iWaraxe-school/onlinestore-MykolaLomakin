package store.populator;

import com.github.javafaker.Faker;
import products.Product;

public class RandomStorePopulator implements Populator {
    private final Faker faker = new Faker();

    public Product generateProduct(String categoryName) {
        Product product = Product.newProductBuilder()
                .setName(getProductName(categoryName))
                .setPrice(getProductPrice())
                .setRate(getProductRate())
                .build();
        return product;
    }

    @Override
    public String getProductName(String categoryName) {
        switch (categoryName) {
            case "Bike":
                return faker.ancient().god();
            case "Milk":
                return faker.food().ingredient();
            case "Phone":
                return faker.ancient().primordial();
            default:
                return "Unknown category";
        }
    }

    @Override
    public Double getProductPrice() {
        return faker.number().randomDouble(1, 1, 100000);
    }

    @Override
    public Double getProductRate() {
        return faker.number().randomDouble(1, 1, 100);
    }

}
