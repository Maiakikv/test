package Steps.stepsAPI;

import Data.Constants.ConstantData;
import Data.request.AuthorizationRequest;
import Data.request.CreateUserRequest;
import Data.response.ErrorAuthorizeResponse;
import Enums.EndPoint;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;

public class APIsteps {
    ObjectMapper mapper = new ObjectMapper();

    @Step("Create user")
    public Response createUserStep(String username, String password) throws JsonProcessingException {
        CreateUserRequest createUserRequest = new CreateUserRequest(username, password);
        String body = mapper.writeValueAsString(createUserRequest);
        Response response = RestAssured.given()
                .filter(new AllureRestAssured())
                .header("Content-Type", "application/json")
                .body(body)
                .post(EndPoint.BASEURL + "/Account/v1/User");
        return response;
    }


    @Step("Authorize with deleted credentials")
    public Response Authorize(String username, String password) throws JsonProcessingException {
        AuthorizationRequest auth = new AuthorizationRequest(username, password);
        String body = mapper.writeValueAsString(auth);
        Response response = RestAssured.given()
                .filter(new AllureRestAssured())
                .header("Content-Type", "application/json")
                .body(body)
                .post(EndPoint.BASEURL + "/Account/v1/Authorized");
        return response;
    }


    @Step("Validate authorize response")
    public void validateAuthorizeMessage(Response response) {
        ErrorAuthorizeResponse errorAuthorizeResponse = response.body().as(ErrorAuthorizeResponse.class);
        Assert.assertEquals(errorAuthorizeResponse.message(), ConstantData.authorizedApiUserNotFound);

    }

}
