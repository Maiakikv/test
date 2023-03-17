package Steps.stepsAPI;

import Enums.EndPoint;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class StepClass {
    @Step("create user")
    public Response createUserStep(String body) {
        Response response = RestAssured.given()
                .filter(new AllureRestAssured())
                .header("Content-Type", "application/json")
                .body(body)
                .post(EndPoint.BASEURL + "/Account/v1/User");
        return response;
    }


    @Step("Authorize with deleted credentials")
    public Response Authorize(String body) {
        Response response = RestAssured.given()
                .filter(new AllureRestAssured())
                .header("Content-Type", "application/json")
                .body(body)
                .post(EndPoint.BASEURL + "/Account/v1/Authorized");
        return response;
    }
//
//    @Step("Authorize with deleted credentials")
//    public void validateResponseMessage(String responseText) {
//        ErrorAuthorizeResponse errorAuthorizeResponse = response.body().as(ErrorAuthorizeResponse.class);
//        Assert.assertEquals(errorAuthorizeResponse.message(), responseText );
//
//    }

}
