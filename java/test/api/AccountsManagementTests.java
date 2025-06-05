package test.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.User;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AccountsManagementTests {
    private static final Logger logger = LogManager.getLogger(AccountsManagementTests.class);
    private static String token;
    private static RequestSpecification requestSpec;
    WebDriver driver;

    @BeforeClass
    public void setup() {
        logger.info("Setting up API base URI");
        RestAssured.baseURI = "http://localhost:9000";

        logger.info("Authenticating and retrieving token");
        Response response = given()
                .contentType(ContentType.JSON)
                .body("{\"username\": \"admin\", \"password\": \"admin\"}")
                .when()
                .post("/api/authenticate")
                .then()
                .extract().response();

        token = response.jsonPath().getString("token");
        logger.info("Authentication successful, token received: {}", token);

        requestSpec = RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON);
    }

    @Test(dataProvider = "userDataProvider")
    public void testRegisterUser(User user) {
        String endpoint = "/api/register";
        logger.info("Registering user: {}", user);

        Response response = given()
                .spec(requestSpec)
                .body(user)
                .when()
                .post(endpoint);

        response.then().statusCode(200);
        logger.info("User registration response received, Status Code: {}", response.getStatusCode());
    }

    @Test
    public void testResetPassword() {
        String endpoint = "/api/account/reset-password/init";
        String requestBody = "\"tanuja\"";  // This should be the email or username
        logger.info("Sending password reset request for {}", requestBody);

        Response response = given()
                .spec(requestSpec)
                .body(requestBody)
                .when()
                .post(endpoint);

        response.then().statusCode(200);
        logger.info("Password reset request response received, Status Code: {}", response.getStatusCode());
    }
}
