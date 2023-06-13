package store.http;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.io.IOException;


public class Server {

    public void start() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8081), 0);
        HttpContext productContext = server.createContext("/product", new ProductHandler());
        productContext.setAuthenticator(new Authenticator("productRealm"));
        HttpContext orderContext = server.createContext("/order", new OrderHandler());
        orderContext.setAuthenticator(new Authenticator("orderRealm"));
        HttpContext categoryContext = server.createContext("/category", new CategoryHandler());
        categoryContext.setAuthenticator(new Authenticator("categoryRealm"));

        server.start();
    }

}
