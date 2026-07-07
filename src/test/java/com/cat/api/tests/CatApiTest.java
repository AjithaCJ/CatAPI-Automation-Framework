package com.cat.api.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.cat.api.utils.ConfigReader;

import java.io.IOException;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CatApiTest {

    @BeforeClass
    public void setup() throws IOException {
        RestAssured.baseURI = ConfigReader.getGlobalValue("baseUrl");
        System.out.println("📡 Target API Environment URL initialized to: " + RestAssured.baseURI);
    }

    @Test(priority = 1)
    public void validatePublicBreedsEndpoint() {
        given()
            .queryParam("limit", 3)
            .contentType(ContentType.JSON)
        .when()
            .get("/breeds")
        .then()
            .statusCode(200)
            .body("size()", equalTo(3))
            .body("[0].id", notNullValue())
            .log().body();
    }

    @Test(priority = 2)
    public void validateApiJsonSchemaStructure() {
        given()
            .queryParam("limit", 2)
            .contentType(ContentType.JSON)
        .when()
            .get("/breeds")
        .then()
            .statusCode(200)
            // This single line matches the server response payload against your resource blueprint schema file!
            .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema.json"));
        
        System.out.println("✅ Structural Validation Success: The API schema strictly matches the target blueprint layout.");
    }
}