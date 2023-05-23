package store.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDAO {
    private Connection connection;

    public OrderDAO(Connection connection) {
        this.connection = connection;
    }

    public void createOrdersTable() throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS ORDERS (" +
                "ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL, " +
                "PRODUCT_ID INT NOT NULL, " +
                "QUANTITY INT NOT NULL, " +
                "FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCTS(ID)" +
                ");";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
            System.out.println("Orders table created");
        } catch (SQLException e) {
            throw new SQLException("Failed to create the orders table.", e);
        }
    }

    public void addOrder(int productId, int quantity) throws SQLException {
        String query = "INSERT INTO ORDERS (PRODUCT_ID, QUANTITY) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, productId);
            statement.setInt(2, quantity);
            statement.executeUpdate();
            System.out.println("Order added");
        } catch (SQLException e) {
            throw new SQLException("Failed to add order to the database.", e);
        }
    }
}

