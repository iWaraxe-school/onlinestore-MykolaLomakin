package store.database;

import domain.Category;
import org.reflections.Reflections;
import products.Product;
import store.Comparator.MultiFieldComparator;
import store.Order.ProductStorage;
import store.populator.RandomStorePopulator;

import java.lang.reflect.Constructor;
import java.sql.*;
import java.util.*;
import java.util.function.Consumer;

public class DBHelper {
    private static ConnectionManager connectionManager;
    private static CategoryDAO categoryDAO;
    private static ProductDAO productDAO;
    private OrderDAO orderDAO;
    private final Consumer<String> printCallback;

    public DBHelper(Consumer<String> printCallback) throws DBException {
        this.printCallback = printCallback;
        connectionManager = new ConnectionManager();
        Connection connection = null;
        try {
            connection = connectionManager.getConnection();
            categoryDAO = new CategoryDAO(connection);
            productDAO = new ProductDAO(connection);
            orderDAO = new OrderDAO(connection);
        } catch (SQLException e) {
            throw new DBException("Failed to establish a database connection.", e);
        }
    }

    public void clearDB() throws DBException {
        try {
            orderDAO.deleteAllOrders();
            productDAO.deleteAllProducts();
            categoryDAO.deleteAllCategories();

            printCallback.accept("Database cleared successfully!");
        } catch (SQLException e) {
            throw new DBException("Failed to clear the database.", e);
        }
    }

    public void initializeDatabase() throws DBException {
        try {
            categoryDAO.createCategoryTable();
            productDAO.createProductTable();
            orderDAO.createOrdersTable();

            printCallback.accept("Database initialized successfully!");
        } catch (SQLException e) {
            throw new DBException("Failed to initialize the database.", e);
        }
    }

    public void fillStoreRandomly() throws DBException {
        try {
            RandomStorePopulator populator = new RandomStorePopulator();
            Map<Category, Integer> categoryMap = createCategoryMap();
            int quantity = 1;

            for (Map.Entry<Category, Integer> entry : categoryMap.entrySet()) {
                Category category = entry.getKey();
                categoryDAO.addCategory(category);
                int categoryId = categoryDAO.getCategoryId(category.getCategoryName());

                for (int i = 0; i < entry.getValue(); i++) {
                    Product product = populator.generateRandomProduct(category.getCategoryName());
                    productDAO.addProduct(categoryId, product);
                    int productId = productDAO.getProductId(product.getName());
                    orderDAO.addOrder(productId, quantity);

                    quantity++;
                }
            }

            printCallback.accept("Store filled randomly successfully!");
        } catch (SQLException | ReflectiveOperationException e) {
            throw new DBException("Failed to fill the store randomly.", e);
        }
    }

    public void printFilledStore() throws DBException {
        try {
            List<Category> categories;
            List<Product> products;

            try (Connection connection = connectionManager.getConnection()) {
                categories = categoryDAO.getAllCategories(connection);
                products = productDAO.getAllProducts(connection);
            }

            printCallback.accept("\nPrint Store from Database\n");
            printCallback.accept("List of Categories");
            for (Category category : categories) {
                printCallback.accept(category.getCategoryName());
            }

            printCallback.accept("List of Products");
            for (Product product : products) {
                printCallback.accept(product.getName() + ", " + product.getRate() + ", " + product.getPrice());
            }
        } catch (SQLException e) {
            throw new DBException("Failed to print the filled store.", e);
        }
    }

    public void sortCategories() throws DBException {
        try {
            List<Category> categories;

            try (Connection connection = connectionManager.getConnection()) {
                categories = categoryDAO.getAllCategories(connection);
            }

            for (Category category : categories) {
                printCallback.accept("\nCategory: " + category.getCategoryName());

                List<Product> productList;
                try (Connection connection = connectionManager.getConnection()) {
                    productList = productDAO.getProductsByCategoryName(category.getCategoryName(), connection);
                }
                productList.sort(new MultiFieldComparator(new HashMap<>()));
                for (Product product : productList) {
                    printCallback.accept(product.toString());
                }
            }
        } catch (SQLException e) {
            throw new DBException("Failed to sort categories.", e);
        }
    }

    public void printTopProductsByPrice(int count) throws DBException {
        try {
            List<Product> products;

            try (Connection connection = connectionManager.getConnection()) {
                products = productDAO.getTopProductsByPrice(count, connection);
            }

            printCallback.accept("Top " + count + " products by price:");
            for (Product product : products) {
                printCallback.accept(product.toString());
            }
        } catch (SQLException e) {
            throw new DBException("Failed to print top products by price.", e);
        }
    }

    private Map<Category, Integer> createCategoryMap() throws ReflectiveOperationException {
        Map<Category, Integer> categoryToPut = new HashMap<>();

        Reflections reflections = new Reflections("domain.categories");
        Set<Class<? extends Category>> subTypes = reflections.getSubTypesOf(Category.class);

        for (Class<? extends Category> type : subTypes) {
            Random random = new Random();
            Constructor<? extends Category> constructor = type.getDeclaredConstructor();
            Category category = constructor.newInstance();
            categoryToPut.put(category, random.nextInt(10) + 1);
        }

        return categoryToPut;
    }

    public static String getFilledStore() throws DBException {
        try {
            List<Category> categories;
            List<Product> products;

            try (Connection connection = connectionManager.getConnection()) {
                categories = categoryDAO.getAllCategories(connection);
                products = productDAO.getAllProducts(connection);
            }

            StringBuilder response = new StringBuilder();
            response.append("\nPrint Store from Database\n");
            response.append("List of Categories\n");
            for (Category category : categories) {
                response.append(category.getCategoryName()).append('\n');
            }

            response.append("List of Products\n");
            for (Product product : products) {
                response.append(product.getName())
                        .append(", ")
                        .append(product.getRate())
                        .append(", ")
                        .append(product.getPrice())
                        .append('\n');
            }

            return response.toString();
        } catch (SQLException e) {
            throw new DBException("Failed to print the filled store.", e);
        }
    }

    public static String getSortedCategories() throws DBException {
        try {
            List<Category> categories;

            try (Connection connection = connectionManager.getConnection()) {
                categories = categoryDAO.getAllCategories(connection);
            }

            StringBuilder response = new StringBuilder();
            for (Category category : categories) {
                response.append("\nCategory: ").append(category.getCategoryName()).append('\n');

                List<Product> productList;
                try (Connection connection = connectionManager.getConnection()) {
                    productList = productDAO.getProductsByCategoryName(category.getCategoryName(), connection);
                }
                productList.sort(new MultiFieldComparator(new HashMap<>()));
                for (Product product : productList) {
                    response.append(product.toString()).append('\n');
                }
            }

            return response.toString();
        } catch (SQLException e) {
            throw new DBException("Failed to sort categories.", e);
        }
    }

    public void addOrder(int productId, int quantity) throws DBException {
        try (Connection connection = connectionManager.getConnection()) {
            OrderDAO orderDAO = new OrderDAO(connection);
            orderDAO.addOrder(productId, quantity);
        } catch (SQLException e) {
            throw new DBException("Failed to add order to the database.", e);
        }
    }
}
