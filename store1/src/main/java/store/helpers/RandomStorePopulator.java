package store.helpers;

import com.github.javafaker.Faker;

public class RandomStorePopulator {
    private final Faker faker = new Faker();
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

    public Double getProductPrice() {
        return faker.number().randomDouble(1, 1, 100000);
    }

    public Double getProductRate() {
        return faker.number().randomDouble(1,1,100);
    }

}
