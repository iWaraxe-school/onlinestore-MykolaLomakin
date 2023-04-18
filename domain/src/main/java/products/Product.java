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
        return new Product().new ProductBuilder();
    }

    public class ProductBuilder {
        private String name;
        private double rate;
        private double price;

        public ProductBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ProductBuilder setPrice(double price) {
            this.price = price;
            return this;
        }

        public ProductBuilder setRate(double rate) {
            this.rate = rate;
            return this;
        }

        public Product build() {
            Product.this.name = this.name;
            Product.this.price = this.price;
            Product.this.rate = this.rate;
            return Product.this;
        }
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

    public void setProduct() {
        System.out.println("Name: " + getName() + ";  " +
                "Price: " + getPrice() + ";  " +
                "Rate: " + getRate() + ";");
    }

    @Override
    public String toString() {
        return String.format("Product name: '%s', Product rate: '%s', Product price: '%s'", name, rate, price);
    }
}
