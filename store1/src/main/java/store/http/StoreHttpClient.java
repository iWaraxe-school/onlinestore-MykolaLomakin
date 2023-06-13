package store.http;

import products.Product;
import store.Store;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

public class StoreHttpClient {
    public static void clientOrder() {
        RestAssured.baseURI = "https://localhost:8081/";
        RequestSpecification request = RestAssured.given();
        JSONObject requestParams = new JSONObject();
        Product product = Store.getInstance().getRandomProductFromStore();
        String name = product.getName();
        requestParams.put("name", name);
        request.header("Content-Type", "application/json");
        request.auth().basic("admin", "password");
        request.body(requestParams.toJSONString());
        Response response = request.post("/post");
        System.out.println("The status received: " + response.statusLine());
        System.out.println("Response body: " + response.getBody().asString());
    }
}