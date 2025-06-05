package test.api;

import config.ConfigReader;
import dataprovider.CreateOperationData;
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

public class OperationsManagementTests {
    private static final Logger logger = LogManager.getLogger(OperationsManagementTests.class);
    private static String token;
    private static RequestSpecification requestSpec;

    @BeforeClass
    public void setup() {
        logger.info("Initializing OperationsManagementTests setup");

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
    public void getOperationWithValidId() {
        int operationId = 2;
        logger.info("Fetching operation details for ID: {}", operationId);

        Response response = given()
                .spec(requestSpec)
                .when()
                .get("/api/operations/{id}", operationId);

        logger.info("Response received, Status Code: {}", response.getStatusCode());
        response.then().statusCode(200).header("pragma", equalTo("no-cache"));
    }

    @Test
    public void getOperationWithInvalidId() {
        int invalidOperationId = 100;
        logger.warn("Attempting to fetch operation details for invalid ID: {}", invalidOperationId);

        Response response = given()
                .spec(requestSpec)
                .when()
                .get("/api/operations/{id}", invalidOperationId);

        logger.info("Response received, Status Code: {}", response.getStatusCode());
        response.then().statusCode(404).header("pragma", equalTo("no-cache"));
    }

    @Test(dataProvider = "createOperationData", dataProviderClass = CreateOperationData.class)
    public void testOperationsPost(String date, String description, double amount, int accountId, String accountName, double balance, int labelId, String label) {
        logger.info("Creating operation for description: {}", description);

        String requestBody = String.format("{\n" +
                "  \"date\": \"%s\",\n" +
                "  \"description\": \"%s\",\n" +
                "  \"amount\": %.2f,\n" +
                "  \"bankAccount\": {\n" +
                "    \"id\": %d,\n" +
                "    \"name\": \"%s\",\n" +
                "    \"balance\": %.2f\n" +
                "  },\n" +
                "  \"labels\": [\n" +
                "    {\n" +
                "      \"id\": %d,\n" +
                "      \"label\": \"%s\"\n" +
                "    }\n" +
                "  ]\n" +
                "}", date, description, amount, accountId, accountName, balance, labelId, label);

        Response response = given()
                .spec(requestSpec)
                .body(requestBody)
                .when()
                .post("/api/operations");

        logger.info("Response received, Status Code: {}", response.getStatusCode());
        response.then().statusCode(201);
    }
}
