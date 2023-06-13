package store.http;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import store.Order.ProductStorage;
import store.database.DBException;
import store.database.DBHelper;

import java.io.IOException;
import java.io.OutputStream;

public class OrderHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if ("POST".equals(exchange.getRequestMethod())) {
            try {
                DBHelper dbHelper = new DBHelper(System.out::println);
                dbHelper.addOrder(1, 2);
                String response = "Order added successfully";
                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } catch (DBException e) {
                String response = "Failed to add order: " + e.getMessage();
                exchange.sendResponseHeaders(500, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        } else {
            exchange.sendResponseHeaders(405, -1); // Method Not Allowed
        }
    }
}
