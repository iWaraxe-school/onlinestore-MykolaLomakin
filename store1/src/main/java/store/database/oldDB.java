//package store.helpers;
//
//import domain.Category;
//import org.reflections.Reflections;
//import products.Product;
//import store.Comparator.MultiFieldComparator;
//import store.populator.RandomStorePopulator;
//
//import java.lang.reflect.Constructor;
//import java.lang.reflect.InvocationTargetException;
//import java.sql.*;
//import java.util.*;
//
//
//class DBHelper {
//    static Connection CONNECTION = null;
//    static Statement STATEMENT = null;
//    static Statement STATEMENT_ENCLOSED = null;
//    static ResultSet RESULTSET = null;
//
//    static final String URL = "jdbc:mysql://localhost:3306/newdb";
//    static final String USERNAME = "root";
//    static final String PASSWORD = "root";
//
//    public void connectToDb() {
//        try {
//            CONNECTION = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//            System.out.println("\nDatabase connection successfull!\n");
//            STATEMENT = CONNECTION.createStatement();
//            STATEMENT_ENCLOSED = CONNECTION.createStatement();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public void clearDB() {
//        try {
//            // Disable foreign key checks
//            STATEMENT.executeUpdate("SET FOREIGN_KEY_CHECKS = 0");
//
//            // Drop foreign key constraint
//            STATEMENT.executeUpdate("ALTER TABLE products DROP FOREIGN KEY products_ibfk_1");
//
//            // Drop tables
//            STATEMENT.executeUpdate("DROP TABLE IF EXISTS categories");
//            STATEMENT.executeUpdate("DROP TABLE IF EXISTS products");
//            STATEMENT.executeUpdate("DROP TABLE IF EXISTS orders");
//
//            // Enable foreign key checks
//            STATEMENT.executeUpdate("SET FOREIGN_KEY_CHECKS = 1");
//
//            System.out.println("Tables dropped successfully.");
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public void createCategoryTable() {
//        String query = "CREATE TABLE IF NOT EXISTS CATEGORIES(ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL, NAME VARCHAR(255) NOT NULL);";
//        try {
//            STATEMENT.executeUpdate(query);
//            System.out.println("category table created");
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public void createProductTable() {
//        String query = "CREATE TABLE IF NOT EXISTS PRODUCTS(ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL," +
//                "CATEGORY_ID INT NOT NULL, NAME VARCHAR(255) NOT NULL, RATE DECIMAL (10, 1) NOT NULL, " +
//                "PRICE DECIMAL (10, 1) NOT NULL, FOREIGN KEY(CATEGORY_ID) REFERENCES CATEGORIES(ID));";
//        try {
//            STATEMENT.executeUpdate(query);
//            System.out.println("product table created");
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public void createOrdersTable() {
//        String query = "CREATE TABLE IF NOT EXISTS ORDERS ("
//                + "ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL, "
//                + "PRODUCT_ID INT NOT NULL, "
//                + "QUANTITY INT NOT NULL, "
//                + "FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCTS(ID)"
//                + ");";
//        try {
//            STATEMENT.executeUpdate(query);
//            System.out.println("orders table created");
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public void fillStoreRandomly() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, SQLException {
//        RandomStorePopulator populator = new RandomStorePopulator();
//        Map<Category, Integer> categoryMap = createCategoryMap();
//        int categoryID = 1;
//        int productID = 1;
//        int quantity = 1;
//
//        for (Map.Entry<Category, Integer> entry : categoryMap.entrySet()) {
//            addCategoryToDB(entry.getKey());
//            addProductToDB(entry, populator, categoryID, productID, quantity);
//            categoryID++;
//            productID++;
//            quantity++;
//        }
//    }
//
//    private void addCategoryToDB(Category category) {
//        try {
//            PreparedStatement insertCategories = CONNECTION.prepareStatement("INSERT INTO CATEGORIES(NAME)"
//                    + " VALUES (?)");
//            insertCategories.setString(1, category.getCategoryName());
//            System.out.println(insertCategories);
//            insertCategories.execute();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private void addProductToDB(Map.Entry<Category, Integer> entry, RandomStorePopulator populator, int categoryID, int productID, int quantity) throws SQLException {
//        try {
//            PreparedStatement insertProducts = CONNECTION.prepareStatement("INSERT INTO PRODUCTS (CATEGORY_ID, NAME, RATE, PRICE)"
//                    + " VALUES (?, ?, ?, ?)");
//            insertProducts.setInt(1, categoryID);
//            insertProducts.setString(2, populator.getProductName(entry.getKey().getCategoryName()));
//            insertProducts.setDouble(3, populator.getProductRate());
//            insertProducts.setDouble(4, populator.getProductPrice());
//            System.out.println(insertProducts);
//            insertProducts.execute();
//
//            PreparedStatement insertOrders = CONNECTION.prepareStatement("INSERT INTO ORDERS (PRODUCT_ID, QUANTITY)"
//                    + " VALUES (?, ?)");
//            insertOrders.setInt(1, productID);
//            insertOrders.setInt(2, quantity);
//            System.out.println(insertOrders);
//            insertOrders.execute();
//        } catch (SQLException e) {
//            throw new SQLException("Failed to add product and order to the database.", e);
//        }
//    }
//
//    private static Map<Category, Integer> createCategoryMap() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
//        Map<Category, Integer> categoryToPut = new HashMap<>();
//
//        Reflections reflections = new Reflections("domain.categories");
//        Set<Class<? extends Category>> subTypes = reflections.getSubTypesOf(Category.class);
//
//        for (Class<? extends Category> type : subTypes) {
//            Random random = new Random();
//            Constructor<? extends Category> constructor = type.getConstructor();
//            Category category = constructor.newInstance();
//            categoryToPut.put(category, random.nextInt(10) + 1);
//        }
//
//        return categoryToPut;
//    }
//
//    public void printFilledStore() throws SQLException {
//        try {
//            System.out.println("\nPrint Store from DataBase\n");
//            RESULTSET = STATEMENT.executeQuery("SELECT * FROM CATEGORIES");
//            System.out.println("List of Categories");
//            while (RESULTSET.next()) {
//                System.out.println(
//                        RESULTSET.getInt("ID") + ", " +
//                                RESULTSET.getString("NAME"));
//
//            }
//            RESULTSET = STATEMENT.executeQuery("SELECT * FROM PRODUCTS");
//            System.out.println("List of Products");
//            while (RESULTSET.next()) {
//                System.out.println(
//                        RESULTSET.getInt("CATEGORY_ID") + ", " +
//                                RESULTSET.getString("NAME") + ", " +
//                                RESULTSET.getDouble("RATE") + ", " +
//                                RESULTSET.getDouble("PRICE"));
//            }
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            RESULTSET.close();
//        }
//    }
//
//    public void sortCategories() {
//        try {
//            ResultSet resultSet = STATEMENT.executeQuery("SELECT * FROM CATEGORIES");
//            while (resultSet.next()) {
//                int categoryID = resultSet.getInt("ID");
//                String categoryName = resultSet.getString("NAME");
//                System.out.println("\nCategory: " + categoryName);
//
//                List<Product> productList = getProductsByCategory(categoryID);
//                productList.sort(new MultiFieldComparator(new HashMap<>()));
//                for (Product product : productList) {
//                    System.out.println(product);
//                }
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private List<Product> getProductsByCategory(int categoryID) {
//        List<Product> productList = new ArrayList<>();
//        try {
//            PreparedStatement statement = CONNECTION.prepareStatement("SELECT * FROM PRODUCTS WHERE CATEGORY_ID = ?");
//            statement.setInt(1, categoryID);
//            ResultSet resultSet = statement.executeQuery();
//            while (resultSet.next()) {
//                String name = resultSet.getString("NAME");
//                double rate = resultSet.getDouble("RATE");
//                double price = resultSet.getDouble("PRICE");
//                Product product = Product.newProductBuilder()
//                        .setName(name)
//                        .setRate(rate)
//                        .setPrice(price)
//                        .build();
//
//                productList.add(product);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return productList;
//    }
//
//    public void printTopProductsByPrice(int count) {
//        try {
//            List<Product> productList = new ArrayList<>();
//            ResultSet resultSet = STATEMENT.executeQuery("SELECT * FROM PRODUCTS ORDER BY PRICE DESC");
//            while (resultSet.next()) {
//                String name = resultSet.getString("NAME");
//                double rate = resultSet.getDouble("RATE");
//                double price = resultSet.getDouble("PRICE");
//                Product product = Product.newProductBuilder()
//                        .setName(name)
//                        .setRate(rate)
//                        .setPrice(price)
//                        .build();
//                productList.add(product);
//            }
//            productList.sort(new MultiFieldComparator(Collections.singletonMap("price", "asc")));
//            System.out.println("Top " + count + " products by price:");
//            for (int i = 0; i < count && i < productList.size(); i++) {
//                System.out.println(productList.get(i));
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}