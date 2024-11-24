package member1;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class JsonPlaceholderApiTest {

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void testGetUsers() {
        Response resp = RestAssured.get("/users");

        System.out.println("Status Code: " + resp.getStatusCode());
        int code = resp.getStatusCode();
        System.out.println("Response Body: " + resp.getBody().asString());
        Assertions.assertEquals(code, 200);
        Assertions.assertNotNull(resp.jsonPath().getList("id"));
        Assertions.assertNotNull(resp.jsonPath().getList("name"));
    }

    @Test
    public void testCreateUser() {
        Response resp = RestAssured.given()
            .contentType("application/json")
            .body("{ \"name\": \"Farhan\", \"username\": \"farhansial\", \"email\": \"farhansial64@gmail.com\" }")
            .post("/users");

        System.out.println("Status Code: " + resp.getStatusCode());
        int code = resp.getStatusCode();
        System.out.println("Response Body: " + resp.getBody().asString());
        Assertions.assertEquals(code, 201);
        Assertions.assertTrue(resp.getBody().asString().contains("John"));
    }

    @Test
    public void testUpdateUser() {
        Response resp = RestAssured.given()
            .contentType("application/json")
            .body("{ \"name\": \"Farhan Sial\", \"username\": \"farhansial0\", \"email\": \"farhansial800@gmail.com\" }")
            .put("/users/1");

        System.out.println("Status Code: " + resp.getStatusCode());
        int code = resp.getStatusCode();
        System.out.println("Response Body: " + resp.getBody().asString());
        Assertions.assertEquals(code, 200);
        Assertions.assertTrue(resp.getBody().asString().contains("John Doe"));
    }

    @Test
    public void testDeleteUser() {
        Response resp = RestAssured.delete("/users/1");

        System.out.println("Status Code: " + resp.getStatusCode());
        int code = resp.getStatusCode();
        System.out.println("Response Body: " + resp.getBody().asString());
        Assertions.assertEquals(code, 200);
        Assertions.assertTrue(resp.getBody().asString().isEmpty() || resp.getBody().asString().contains("{}"));
    }
}
