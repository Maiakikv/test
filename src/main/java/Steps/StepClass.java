package Steps;

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



    @Step("create token")
    public Response createTokentep(String body) {
        Response response = RestAssured.given()
                .filter(new AllureRestAssured())
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .post(EndPoint.BASEURL + "/Account/v1/GenerateToken");
        return response;
    }

    @Step("Auth")
    public Response Authorize(String body) {
        Response response = RestAssured.given()
                .filter(new AllureRestAssured())
                .header("Content-Type", "application/json")
                .body(body)
                .post(EndPoint.BASEURL + "/Account/v1/Authorized");
        return response;
    }

}
