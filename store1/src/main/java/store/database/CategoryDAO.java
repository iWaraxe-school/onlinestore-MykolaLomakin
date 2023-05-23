package store.database;

import domain.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    private Connection connection;

    public CategoryDAO(Connection connection) {
        this.connection = connection;
    }

    public void createCategoryTable() throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS CATEGORIES(ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL, NAME VARCHAR(255) NOT NULL);";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
            System.out.println("Category table created");
        } catch (SQLException e) {
            throw new SQLException("Failed to create the category table.", e);
        }
    }

    public void addCategory(Category category) throws SQLException {
        String query = "INSERT INTO CATEGORIES (NAME) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, category.getCategoryName());
            statement.executeUpdate();
            System.out.println("Category added: " + category.getCategoryName());
        } catch (SQLException e) {
            throw new SQLException("Failed to add category to the database.", e);
        }
    }

    public List<Category> getAllCategories() throws SQLException {
        List<Category> categories = new ArrayList<>();
        String query = "SELECT * FROM CATEGORIES";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("NAME");
                Category category = new Category(name);
                categories.add(category);
            }
        } catch (SQLException e) {
            throw new SQLException("Failed to fetch categories from the database.", e);
        }
        return categories;
    }
}
