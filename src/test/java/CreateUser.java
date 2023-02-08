import Data.CreateUserData;
import RequestResponse.CreateUserRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class CreateUser {
    @Test
    public void createUser (){
        CreateUserData createUserData = new CreateUserData();
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setJob(createUserData.getJob());
        createUserRequest.setName(createUserData.getName());

        RestAssured
                .given()
                .baseUri("https://reqres.in/")
                .contentType(ContentType.JSON)
                .body(createUserRequest)
                .when()
                .post("/api/users/")
                .then()
                .assertThat()
                .statusCode(201);
    }
}
