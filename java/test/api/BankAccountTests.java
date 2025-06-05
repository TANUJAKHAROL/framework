package test.api;

import config.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.BankAccount;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class BankAccountTests {
    private static final Logger logger = LogManager.getLogger(BankAccountTests.class);
    private static String token;
    private static RequestSpecification requestSpec;

    @BeforeClass
    public void setup() {
        logger.info("Initializing BankAccountTests setup");

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

        if (response.statusCode() == 200) {
            token = response.path("id_token");
            logger.info("Authentication successful, received token: {}", token);
        } else {
            logger.error("Failed to authenticate, status code: {}", response.statusCode());
            throw new IllegalStateException("Could not login to API");
        }

        // Define common request specification
        requestSpec = RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON);

        logger.info("Request specification setup complete");
    }

    @Test
    public void getBankAccountWithValidId() {
        int accountId = 3;
        logger.info("Fetching bank account details for ID: {}", accountId);

        Response response = given()
                .spec(requestSpec)
                .when()
                .get("/api/bank-accounts/{id}", accountId);

        logger.info("Response received, Status Code: {}", response.getStatusCode());
        response.then().statusCode(200).header("pragma", equalTo("no-cache"));
    }

    @Test
    public void getBankAccountWithInValidId() {
        int invalidAccountId = 72;
        logger.warn("Attempting to fetch bank account details for invalid ID: {}", invalidAccountId);

        Response response = given()
                .spec(requestSpec)
                .when()
                .get("/api/bank-accounts/{id}", invalidAccountId);

        logger.info("Response received, Status Code: {}", response.getStatusCode());
        response.then().statusCode(404).header("pragma", equalTo("no-cache"));
    }

    @Test(dataProvider = "bankAccountData")
    public void createBankAccount(BankAccount bankAccount) {
        logger.info("Creating a new bank account for: {}", bankAccount.getName());

        Response response = given()
                .spec(requestSpec)
                .body(bankAccount)
                .when()
                .post("/api/bank-accounts");

        logger.info("Response received, Status Code: {}", response.getStatusCode());
        response.then().statusCode(201)
                .body("name", equalTo("Tanuja Kharol"))
                .body("balance", equalTo(1200.0f));
    }

    @Test(dataProvider = "bankAccountData")
    public void updateBankAccount(BankAccount bankAccount) {
        int accountId = 1;
        logger.info("Updating bank account with ID: {}", accountId);

        Response response = given()
                .spec(requestSpec)
                .body(bankAccount)
                .when()
                .put("/api/bank-accounts/{id}", accountId);

        logger.info("Response received, Status Code: {}", response.getStatusCode());
        response.then().statusCode(200);
    }

    @Test
    public void deleteBankAccount() {
        int accountId = 8;
        logger.warn("Deleting bank account with ID: {}", accountId);

        Response response = given()
                .spec(requestSpec)
                .when()
                .delete("/api/bank-accounts/{id}", accountId);

        logger.info("Response received, Status Code: {}", response.getStatusCode());
        response.then().statusCode(500);
    }
}
