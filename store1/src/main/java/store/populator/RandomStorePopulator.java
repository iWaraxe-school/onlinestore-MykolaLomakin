package store.populator;

import com.github.javafaker.Faker;
import products.Product;

public class RandomStorePopulator implements Populator {
    private final Faker faker = new Faker();

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

    public Product generateRandomProduct(String categoryName) {
        switch (categoryName) {
            case "Bike":
                return Product.newProductBuilder()
                        .setName(faker.ancient().god())
                        .setRate(faker.number().randomDouble(1, 1, 100))
                        .setPrice(faker.number().randomDouble(1, 1, 100000))
                        .build();
            case "Milk":
                return Product.newProductBuilder()
                        .setName(faker.food().ingredient())
                        .setRate(faker.number().randomDouble(1, 1, 100))
                        .setPrice(faker.number().randomDouble(1, 1, 100000))
                        .build();
            case "Phone":
                return Product.newProductBuilder()
                        .setName(faker.ancient().primordial())
                        .setRate(faker.number().randomDouble(1, 1, 100))
                        .setPrice(faker.number().randomDouble(1, 1, 100000))
                        .build();
            default:
                throw new IllegalArgumentException("Unknown category: " + categoryName);
        }
    }
}
