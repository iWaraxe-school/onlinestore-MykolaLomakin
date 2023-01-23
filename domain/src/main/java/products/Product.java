package products;

public class Product {

    private final String name;
    private final double rate;
    private final double price;


    public Product(String name, double rate, double price) {
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
