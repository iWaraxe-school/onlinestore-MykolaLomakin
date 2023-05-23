package store.database;

import domain.Category;
import org.reflections.Reflections;
import products.Product;
import store.Comparator.MultiFieldComparator;
import store.populator.RandomStorePopulator;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.*;

public class DBHelper {
    private ConnectionManager connectionManager;
    private CategoryDAO categoryDAO;
    private ProductDAO productDAO;
    private OrderDAO orderDAO;

    public DBHelper() {
        connectionManager = new ConnectionManager();
        Connection connection = null;
        try {
            connection = connectionManager.getConnection();
            categoryDAO = new CategoryDAO(connection);
            productDAO = new ProductDAO(connection);
            orderDAO = new OrderDAO(connection);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to establish a database connection.", e);
        }
    }

    public void initializeDatabase() {
        try {
            categoryDAO.createCategoryTable();
            productDAO.createProductTable();
            orderDAO.createOrdersTable();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to initialize the database.", e);
        }
    }

    public void fillStoreRandomly() {
        try {
            RandomStorePopulator populator = new RandomStorePopulator();
            Map<Category, Integer> categoryMap = createCategoryMap();
            int categoryID = 1;
            int productID = 1;
            int quantity = 1;

            for (Map.Entry<Category, Integer> entry : categoryMap.entrySet()) {
                categoryDAO.addCategory(entry.getKey());
                Category category = entry.getKey();
                productDAO.addProduct(categoryID, populator.getProductName(category.getCategoryName()),
                        populator.getProductRate(), populator.getProductPrice());
                orderDAO.addOrder(productID, quantity);
                categoryID++;
                productID++;
                quantity++;
            }
        } catch (SQLException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Failed to fill the store randomly.", e);
        }
    }


    public void printFilledStore() {
        try {
            List<Category> categories = categoryDAO.getAllCategories();
            System.out.println("\nPrint Store from Database\n");
            System.out.println("List of Categories");
            for (Category category : categories) {
                System.out.println(category.getCategoryName());
            }

            List<Product> products = productDAO.getAllProducts();
            System.out.println("List of Products");
            for (Product product : products) {
                System.out.println(product.getName() + ", " + product.getRate() + ", " + product.getPrice());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to print the filled store.", e);
        }
    }

    public void sortCategories() {
        try {
            List<Category> categories = categoryDAO.getAllCategories();
            for (Category category : categories) {
                System.out.println("\nCategory: " + category.getCategoryName());

                List<Product> productList = productDAO.getProductsByCategoryName(category.getCategoryName());
                productList.sort(new MultiFieldComparator(new HashMap<>()));
                for (Product product : productList) {
                    System.out.println(product);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to sort categories.", e);
        }
    }

    public void printTopProductsByPrice(int count) {
        try {
            List<Product> products = productDAO.getTopProductsByPrice(count);
            System.out.println("Top " + count + " products by price:");
            for (Product product : products) {
                System.out.println(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to print top products by price.", e);
        }
    }

    private Map<Category, Integer> createCategoryMap() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Map<Category, Integer> categoryToPut = new HashMap<>();

        Reflections reflections = new Reflections("domain.categories");
        Set<Class<? extends Category>> subTypes = reflections.getSubTypesOf(Category.class);

        for (Class<? extends Category> type : subTypes) {
            Random random = new Random();
            Constructor<? extends Category> constructor = type.getConstructor();
            Category category = constructor.newInstance();
            categoryToPut.put(category, random.nextInt(10) + 1);
        }

        return categoryToPut;
    }
}

