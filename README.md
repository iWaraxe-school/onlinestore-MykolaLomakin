# 7. DB

----
## Materials

[H2 Database](https://www.h2database.com/html/main.html)

[Very brief description](https://www.javatpoint.com/steps-to-connect-to-the-database-in-java)

[One more](https://www.baeldung.com/java-jdbc)

## Task
In this task, you will replace the current `Reflection` and in-memory products storage with a database for storing categories and products. We will be using `JDBC` for database connectivity, and you'd better use the MySQL database. But if you have any experience with other databases feel free to choose those that you're familiar with.

### Objective

1. Store categories and products for each category in MySQL database tables using `JDBC`.
2. Implement different levels of difficulty for this task (simple, medium, and advanced).

### Levels of Difficulty

1. **Simple**: Implement basic functionality to store categories and products in the `CATEGORIES` and `PRODUCTS` tables, respectively, and print the store data to the console using a direct JDBC connection.
2. **Medium**: Implement all methods for sorting stores, ordering products, and clearing purchased product storage. Create an additional `ORDERS` table in the database to store order information.
3. **Advanced**: Implement the DAO pattern to provide a clean and modular solution for data access. Implement the `CategoryDao`, `ProductDao`, and `OrderDao` interfaces and their corresponding concrete classes.

Choose the level of difficulty based on your current knowledge and skills.

### DAO Design Pattern
The Data Access Object (DAO) pattern is a structural pattern that allows us to isolate the application's business logic from the underlying data storage. It provides an interface to perform CRUD operations on the data storage, which can be a database, a file system, or any other storage mechanism.

In this task, you'll implement the DAO pattern to create a flexible and maintainable solution for accessing the MySQL database.
