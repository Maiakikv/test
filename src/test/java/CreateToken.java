import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CreateToken {
    @Test
    public void deleteBookingTest (){
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        RequestSpecification request = given();
        String payLoad = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";
        request.header("Content-Type", "application/json");
        Response responseFromGeneratedToken = request.body(payLoad).post("/auth");
        responseFromGeneratedToken.prettyPrint();
        String jsonString = responseFromGeneratedToken.getBody().asString();
        String tokenGenerated = JsonPath.from(jsonString).get("token");



        RequestSpecification req = given()
                .header("Cookie", "token=" + tokenGenerated)
                .header("Content-Type", "application/json");

        Response response =
                given()
                        .spec(req)
                        .contentType(ContentType.JSON)
                        .delete("/booking/" + 1);
        response.then()
                    .log().ifStatusCodeIsEqualTo(201);
        Assert.assertEquals(response.statusCode(),201);

        given().get("/booking/" + 1).then().statusCode(404);

    }

}
