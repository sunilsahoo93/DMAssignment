package Pages;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;

public class CommonPage {
    public Response response;
    public static String responseBody = "";
    public RequestSpecification request;
    public static int responseCode = 0;
    private Logger log = LogManager.getLogger(CommonPage.class);
    String baseUrl = "https://petstore.swagger.io/v2/";

    public void waitForClickability(WebDriver driver, WebElement element) throws Error{
        new WebDriverWait(driver, Duration.ofSeconds(60))
                .until(ExpectedConditions.elementToBeClickable(element));
    }
    public void callPostApi(String body, String endPoint) {
        request = RestAssured.given().log().all().urlEncodingEnabled(false);
        request.contentType("application/json");
        request.body(body);
        response = request.when().post(baseUrl + endPoint);
        responseCode = response.getStatusCode();
        responseBody = response.asString();
        System.out.println(response.asString());
    }

    public void callGetApi(String endPoint) {
        request = RestAssured.given().log().all().urlEncodingEnabled(false);
        request.contentType("application/json");
        response = request.when().get(baseUrl + endPoint);
        responseCode = response.getStatusCode();
        responseBody = response.asString();
        System.out.println(response.asString());
    }

    public String readJsonContent(String fileName) throws IOException {
        String filePath = System.getProperty("user.dir") + "//src//test//Resources//inputJSONs//" + fileName;
        String content = new String(Files.readAllBytes((Paths.get(filePath))));
        return content;
    }

    public void validateStatusCode(int expectedStatusCode) {
        if (expectedStatusCode == responseCode) {
            System.out.println("Expected and Actual status codes match. Expected Status Code =" + expectedStatusCode + " and Actual Status Code = " + responseCode);
        } else {
            System.out.println("Expected and Actual status codes do not match. Expected Status Code =" + expectedStatusCode + " and Actual Status Code = " + responseCode);
            Assert.fail();
        }
    }

    public void validateResponse(String expectedResponse) throws JSONException {
        JSONObject expectedJSONResponse = new JSONObject(expectedResponse);
        JSONObject actualJSONResponse = new JSONObject(responseBody);
        JSONAssert.assertEquals(expectedJSONResponse, actualJSONResponse, JSONCompareMode.LENIENT);
        System.out.println("Expected and Actual Responses match. Expected Response =" + expectedResponse + " and Actual Response = " + responseBody);
    }


}
