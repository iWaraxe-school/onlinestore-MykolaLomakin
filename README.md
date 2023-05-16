# 8. HTTP
----
## Materials
[HTTP](https://en.wikipedia.org/wiki/Hypertext_Transfer_Protocol)
[HTTP simple servers (plain java/lib)](https://syntaxcorrect.com/Java/5_Ultra_Lightweight_Http_Server_Implementations_in_Java_for_Blazing_Fast_Microservices_APIs_or_Even_Websites)
[Basic auth](https://en.wikipedia.org/wiki/Basic_access_authentication)
[RestAssured](https://rest-assured.io/)
## Task #8: Implementing an HTTP Server for Store Management

In this task, you will build upon the previous task by implementing an HTTP server to manage your store's categories and products. You will also add the functionality to handle adding products to the cart, and secure the server using basic authentication.

### Objective

1. Implement an HTTP server that handles category and product management using the HTTP protocol.
2. Implement 'add product to cart' functionality in the server.
3. Secure the HTTP service with basic authentication (hardcoded credentials are acceptable).
4. Use either Java's built-in HTTP client or the RestAssured library for the client-side implementation.

### Subtasks

1. **HTTP Server Setup**: Implement an HTTP server using Java's built-in HTTP server or an external library. The server should be capable of handling requests for adding, updating, retrieving, and deleting categories and products in your store's database.
2. **Add Product to Cart**: Implement a feature that allows users to add products to their cart using an HTTP request. The server should process this request, update the cart, and return a confirmation message.
3. **Basic Authentication**: Secure your HTTP server using basic authentication. Hardcoded credentials are acceptable for this task. Ensure that only authenticated users can access the server's features.
4. **HTTP Client**: Implement the client-side part of your application using either Java's built-in HTTP client or the RestAssured library. The client should be capable of sending requests to the server and processing the server's responses.

By breaking down the task into these clear and concise subtasks, you will have a better understanding of the requirements and be able to tackle each part of the challenge more effectively. Good luck!
