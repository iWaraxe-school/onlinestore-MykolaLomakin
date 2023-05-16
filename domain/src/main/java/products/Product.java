package products;

import store.populator.RandomStorePopulator;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private String name;
    private double rate;
    private double price;

    //add builder
    public static ProductBuilder newProductBuilder() {
        return new ProductBuilder();
    }

    public static class ProductBuilder {
        private String name;
        private double rate;
        private double price;

        public ProductBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ProductBuilder setRate(double rate) {
            this.rate = rate;
            return this;
        }

        public ProductBuilder setPrice(double price) {
            this.price = price;
            return this;
        }

        public Product build() {
            return new Product(name, rate, price);
        }
    }

    private Product(String name, double rate, double price) {
        this.name = name;
        this.rate = rate;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getRate() {
        return rate;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("Product name: '%s', Product rate: '%s', Product price: '%s'", name, rate, price);
    }
}
