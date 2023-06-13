package store.database;

import products.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private static Connection connection;

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

    public void addProduct(int categoryId, Product product) throws SQLException {
        String productQuery = "INSERT INTO PRODUCTS (CATEGORY_ID, NAME, RATE, PRICE) VALUES (?, ?, ?, ?)";

        try (PreparedStatement productStatement = connection.prepareStatement(productQuery)) {
            productStatement.setInt(1, categoryId);
            productStatement.setString(2, product.getName());
            productStatement.setDouble(3, product.getRate());
            productStatement.setDouble(4, product.getPrice());
            productStatement.executeUpdate();
            System.out.println("Product added: " + product.getName());
        } catch (SQLException e) {
            throw new SQLException("Failed to add product to the database.", e);
        }
    }


    public List<Product> getAllProducts(Connection connection) throws SQLException {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM PRODUCTS";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
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

    public List<Product> getTopProductsByPrice(int count, Connection connection) throws SQLException {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM PRODUCTS ORDER BY PRICE DESC LIMIT ?";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, count);
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
            throw new SQLException("Failed to fetch top products by price from the database.", e);
        }
        return products;
    }

    public List<Product> getProductsByCategoryName(String categoryName, Connection connection) throws SQLException {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM PRODUCTS WHERE CATEGORY_ID = (SELECT ID FROM CATEGORIES WHERE NAME = ?)";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
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

    public void deleteAllProducts() throws SQLException {
        String query = "DELETE FROM PRODUCTS";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Failed to delete products from the database.", e);
        }
    }

    public static int getProductId(String productName) throws SQLException {
        String query = "SELECT ID FROM PRODUCTS WHERE NAME = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, productName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("ID");
            }
        } catch (SQLException e) {
            throw new SQLException("Failed to retrieve the product ID from the database.", e);
        }
        throw new IllegalArgumentException("Product not found: " + productName);
    }
}

