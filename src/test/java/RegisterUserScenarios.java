import Data.SuccesResponseValues;
import Data.UnsuccessResponseValue;
import RequestResponse.SuccessfulRegisterResponse;
import RequestResponse.UnsuccessfulRegisterResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import RequestResponse.RegisterRequest;
import Data.SuccessfulRequestData;
import Data.UnsuccessfulRequestData;


public class RegisterUserScenarios {

    @Test
    public void registerUserSuccessfulScenario() {
        SuccessfulRequestData succesData = new SuccessfulRequestData();
        RegisterRequest correctRequest = new RegisterRequest();
        correctRequest.setEmail(succesData.getEmail());
        correctRequest.setPassword(succesData.getPassword());

        UnsuccessfulRequestData unsuccesData = new UnsuccessfulRequestData();
        RegisterRequest uncorrectRequest = new RegisterRequest();
        uncorrectRequest.setEmail(unsuccesData.getWrongEmail());

        SuccesResponseValues succesResponseValues = new SuccesResponseValues();
        UnsuccessResponseValue unsuccessResponseValue = new UnsuccessResponseValue();


        Response correct = RestAssured
                .given()
                .baseUri("https://reqres.in/")
                .contentType(ContentType.JSON)
                .body(correctRequest)
                .when()
                .post("/api/register/");

        correct.then()
                .assertThat()
                .statusCode(200);


        Response inCorrect = RestAssured
                .given()
                .baseUri("https://reqres.in/")
                .contentType(ContentType.JSON)
                .body(uncorrectRequest)
                .when()
                .post("/api/register/");
        inCorrect.then()
                .assertThat()
                .statusCode(400);


        if (correct.statusCode() == 200) {
            SuccessfulRegisterResponse successfulRegisterResponse = correct.as(SuccessfulRegisterResponse.class);
            Assert.assertEquals(successfulRegisterResponse.getId(), succesResponseValues.getId());
            Assert.assertEquals(successfulRegisterResponse.getToken(), succesResponseValues.getToken());
        } else if (inCorrect.statusCode() == 400) {
            UnsuccessfulRegisterResponse unsuccessfulRegisterResponse = inCorrect.as(UnsuccessfulRegisterResponse.class);
            Assert.assertEquals(unsuccessfulRegisterResponse.getError(), unsuccessResponseValue.getError());
        }

    }
}
