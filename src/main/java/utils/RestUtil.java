package utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;

import java.util.Map;

public class RestUtil {

    private static RequestSpecification getRequestSpecification(String endpoint, Object payload, Map<String, String> headers){
        return RestAssured.given()
                .baseUri(endpoint)
                .headers(headers)
                .contentType(ContentType.JSON)
                .body(payload);
    }
    public static Response performPost(String endpoint, String payload, Map<String, String> headers) {
        RequestSpecification requestSpecification = getRequestSpecification(endpoint, payload, headers);
        Response response = requestSpecification.post();
        logRequestDetailsInReport(requestSpecification);
        logResponseDetailsInReport(response);
        return response;
    }

    public static Response performPost(String endpoint, Map<String, Object> payload, Map<String, String> headers) {
        RequestSpecification requestSpecification = getRequestSpecification(endpoint, payload, headers);
        Response response = requestSpecification.post();
        logRequestDetailsInReport(requestSpecification);
        logResponseDetailsInReport(response);
        return response;
    }
    private static void logRequestDetailsInReport(RequestSpecification requestSpecification){
        QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(requestSpecification);
//        ExtentReportsManager.logInfoDetails("End point : "+queryableRequestSpecification.getBaseUri());
//        ExtentReportsManager.logInfoDetails("Method : "+queryableRequestSpecification.getMethod());
//        ExtentReportsManager.logInfoDetails("Headers : ");
//        ExtentReportsManager.logHeaders(queryableRequestSpecification.getHeaders().asList());
//        ExtentReportsManager.logInfoDetails("Request body : ");
//        ExtentReportsManager.logJsonDetails(queryableRequestSpecification.getBody());
    }

    private static void logResponseDetailsInReport(Response response){
//        ExtentReportsManager.logInfoDetails("Response status : "+response.getStatusCode());
//        ExtentReportsManager.logInfoDetails("Response headers : ");
//        ExtentReportsManager.logHeaders(response.getHeaders().asList());
//        ExtentReportsManager.logInfoDetails("Response body : ");
//        ExtentReportsManager.logJsonDetails(response.getBody().prettyPrint());
    }

    public static Response performPost(String endpoint, Object pojoPayload, Map<String, String> headers) {
        RequestSpecification requestSpecification = getRequestSpecification(endpoint, pojoPayload, headers);
        Response response = requestSpecification.post();
        logRequestDetailsInReport(requestSpecification);
        logResponseDetailsInReport(response);
        return response;
    }
}
