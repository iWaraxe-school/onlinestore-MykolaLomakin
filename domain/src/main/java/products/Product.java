package products;

import java.math.BigDecimal;

public class Product {

    private final String name;
    private final double rate;
    private final BigDecimal price;


    public Product(String name, double rate, BigDecimal price) {
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

    public BigDecimal getPrice() {
        return price;
    }
}
