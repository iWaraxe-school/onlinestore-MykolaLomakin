package store.database;

import domain.Category;
import products.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private Connection connection;

    public ProductDAO(Connection connection) {
        this.connection = connection;
    }

    public void createProductTable() throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS PRODUCTS(ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL," +
                "CATEGORY_ID INT NOT NULL, NAME VARCHAR(255) NOT NULL, RATE DECIMAL (10, 1) NOT NULL, " +
                "PRICE DECIMAL (10, 1) NOT NULL, FOREIGN KEY(CATEGORY_ID) REFERENCES CATEGORIES(ID));";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
            System.out.println("Product table created");
        } catch (SQLException e) {
            throw new SQLException("Failed to create the product table.", e);
        }
    }

    public void addProduct(int categoryId, String name, double rate, double price) throws SQLException {
        String query = "INSERT INTO PRODUCTS (CATEGORY_ID, NAME, RATE, PRICE) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, categoryId);
            statement.setString(2, name);
            statement.setDouble(3, rate);
            statement.setDouble(4, price);
            statement.executeUpdate();
            System.out.println("Product added: " + name);
        } catch (SQLException e) {
            throw new SQLException("Failed to add product to the database.", e);
        }
    }

    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM PRODUCTS";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("NAME");
                double rate = resultSet.getDouble("RATE");
                double price = resultSet.getDouble("PRICE");
                Product product = Product.newProductBuilder()
                        .setName(name)
                        .setRate(rate)
                        .setPrice(price)
                        .build();
                products.add(product);
            }
        } catch (SQLException e) {
            throw new SQLException("Failed to fetch products from the database.", e);
        }
        return products;
    }

    public List<Product> getTopProductsByPrice(int count) throws SQLException {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM PRODUCTS ORDER BY PRICE DESC LIMIT ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, count);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int categoryId = resultSet.getInt("CATEGORY_ID");
                String name = resultSet.getString("NAME");
                double rate = resultSet.getDouble("RATE");
                double price = resultSet.getDouble("PRICE");
                Product product = Product.newProductBuilder()
                        .setName(name)
                        .setRate(rate)
                        .setPrice(price)
                        .build();
                products.add(product);
            }
        } catch (SQLException e) {
            throw new SQLException("Failed to fetch top products by price from the database.", e);
        }
        return products;
    }

    public List<Product> getProductsByCategoryName(String categoryName) throws SQLException {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM PRODUCTS WHERE CATEGORY_NAME = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, categoryName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("NAME");
                double rate = resultSet.getDouble("RATE");
                double price = resultSet.getDouble("PRICE");
                Product product = Product.newProductBuilder()
                        .setName(name)
                        .setRate(rate)
                        .setPrice(price)
                        .build();
                products.add(product);
            }
        } catch (SQLException e) {
            throw new SQLException("Failed to fetch products by category name from the database.", e);
        }
        return products;
    }
}

