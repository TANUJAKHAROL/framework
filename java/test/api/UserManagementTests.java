package test.api;

import config.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.User;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

public class UserManagementTests {
    private static final Logger logger = LogManager.getLogger(UserManagementTests.class);
    private static String token;
    private static RequestSpecification requestSpec;

    @BeforeClass
    public void setup() {
        logger.info("Initializing UserManagementTests setup");

        ConfigReader configReader = new ConfigReader();
        RestAssured.baseURI = "http://localhost:9000";
        logger.info("Base URI set to: {}", RestAssured.baseURI);

        // Authenticate and retrieve token
        logger.info("Sending authentication request...");
        Response response = given()
                .contentType(ContentType.JSON)
                .body("{\"username\": \"admin\", \"password\": \"admin\"}")
                .when()
                .post("/api/authenticate")
                .then()
                .extract().response();

        token = response.jsonPath().getString("token");
        logger.info("Authentication successful, received token: {}", token);

        // Define common request specification
        requestSpec = RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON);

        logger.info("Request specification setup complete");
    }

    @Test
    public void viewUser() {
        logger.info("Fetching list of users...");

        Response response = given()
                .spec(requestSpec)
                .when()
                .get("/api/admin/users");

        logger.info("Response received, Status Code: {}", response.getStatusCode());
        response.then().statusCode(200).body("size()", greaterThan(0)); // Ensures list isn't empty
    }

    @Test
    public void createUser() {
        User user = new User.UserBuilder()
                .id(12)
                .createdBy("tanuja")
                .build();

        logger.info("Creating new user: {}", user.getCreatedBy());

        Response response = given()
                .spec(requestSpec)
                .body(user)
                .when()
                .post("/api/admin/users");

        logger.info("Response received, Status Code: {}", response.getStatusCode());
        response.then().statusCode(201).body("login", equalTo("tanuja"));
    }

    @Test
    public void updateUserByLogin() {
        User updatedUser = new User.UserBuilder()
                .id(12)
                .createdBy("tanu")
                .email("tanuja@gmail.com")
                .build();

        logger.info("Updating user with login: tanuja");

        Response response = given()
                .spec(requestSpec)
                .body(updatedUser)
                .when()
                .put("/api/admin/users/{login}", "tanuja");

        logger.info("Response received, Status Code: {}", response.getStatusCode());
        response.then().statusCode(400).body("login", equalTo("tanuja"));
    }

    @Test
    public void deleteUserByLogin() {
        logger.warn("Attempting to delete user with login: tanuja");

        Response response = given()
                .spec(requestSpec)
                .when()
                .delete("/api/admin/users/{login}", "tanuja");

        logger.info("Response received, Status Code: {}", response.getStatusCode());
        response.then().statusCode(204);
    }
}
