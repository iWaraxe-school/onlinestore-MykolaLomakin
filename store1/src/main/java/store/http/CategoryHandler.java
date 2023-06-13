package store.http;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import store.database.DBException;
import store.database.DBHelper;

import java.io.IOException;
import java.io.OutputStream;

public class CategoryHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if ("GET".equals(exchange.getRequestMethod())) {
            String response;
            try {
                response = DBHelper.getSortedCategories();
                exchange.sendResponseHeaders(200, response.getBytes().length);
            } catch (DBException e) {
                response = "Failed to retrieve categories: " + e.getMessage();
                exchange.sendResponseHeaders(500, response.getBytes().length);
            }

            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        } else {
            exchange.sendResponseHeaders(405, -1); // Method Not Allowed
        }
    }
}
