package test.api;

import config.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AuthenticationTests {

    private static final Logger logger = LogManager.getLogger(AuthenticationTests.class);
    private static String token;
    private static RequestSpecification requestSpec;

    @BeforeClass
    public void setup() {
        logger.info("Initializing API authentication setup");

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
    public void testAuthenticate() {
        logger.info("Starting authentication validation test");

        // Send GET request to authenticate endpoint
        Response response = requestSpec.get("/api/authenticate");
        logger.info("Response received, Status Code: {}", response.getStatusCode());
        logger.info("Response Body: {}", response.getBody().asString());

        response.then().statusCode(200);
        response.then().body(equalTo("admin"));

        logger.info("Authentication test passed successfully");
    }
}
