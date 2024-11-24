package member2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class SwaggerPetStoreApiTest {

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    @Test
    public void testGetPet() {
        Response resp = RestAssured.get("/pet/9223372036854580974");
        
        System.out.println("Get PET Testing");
        System.out.println("Status Code: " + resp.getStatusCode());
        int code = resp.getStatusCode();
        System.out.println("Response Body: " + resp.getBody().asString());
        Assertions.assertEquals(code, 200);
        Assertions.assertNotNull(resp.jsonPath().getInt("id"));
        Assertions.assertNotNull(resp.jsonPath().getString("name"));
    }

    @Test
    public void testPostPet() {
        String newPetJson = "{ \"id\": 0, \"name\": \"doggie\", \"photoUrls\": [\"string\"], \"status\": \"available\" }";

        Response resp = RestAssured.given()
            .contentType(ContentType.JSON)
            .body(newPetJson)
            .post("/pet");
        
        System.out.println("Create PET Testing");
        System.out.println("Status Code: " + resp.getStatusCode());
        int code = resp.getStatusCode();
        System.out.println("Response Body: " + resp.getBody().asString());
        Assertions.assertEquals(code, 200);
        Assertions.assertTrue(resp.getBody().asString().contains("doggie"));
    }

    @Test
    public void testPutPet() {
        String updatePetJson = "{ \"id\": 0, \"name\": \"updated-doggie\", \"photoUrls\": [\"string\"], \"status\": \"available\" }";

        Response resp = RestAssured.given()
            .contentType(ContentType.JSON)
            .body(updatePetJson)
            .put("/pet");

        System.out.println("Update PET Testing");
        
        System.out.println("Status Code: " + resp.getStatusCode());
        int code = resp.getStatusCode();
        System.out.println("Response Body: " + resp.getBody().asString());
        Assertions.assertEquals(code, 200);
        Assertions.assertTrue(resp.getBody().asString().contains("updated-doggie"));
    }

    @Test
    public void testDeletePet() {
        Response resp = RestAssured.delete("/pet/9223372036854580974");
        
        System.out.println("Delete PET Testing");
        System.out.println("Status Code: " + resp.getStatusCode());
        int code = resp.getStatusCode();
        System.out.println("Response Body: " + resp.getBody().asString());
        Assertions.assertEquals(code, 200);
    }
}
