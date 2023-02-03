import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static io.restassured.http.ContentType.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.given;

public class restAssuredBasicsTwo {
    @Test
    public void updateTest() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com/auth";
        RequestSpecification request = RestAssured.given();
        String payLoad = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";
        request.header("Content-Type", "application/json");
        Response responseFromGeneratedToken = request.body(payLoad).post("https://restful-booker.herokuapp.com/auth");
        responseFromGeneratedToken.prettyPrint();
        String jsonString = responseFromGeneratedToken.getBody().asString();
        String tokenGenerated = JsonPath.from(jsonString).get("token");
        String authToken= "Bearer " + tokenGenerated;

        JSONObject requestParams = new JSONObject();
        requestParams.put("firstname", "Nino");
        requestParams.put("lastname", "Dolidze");
        requestParams.put("totalprice", 300);
        requestParams.put("depositpaid", false);
        requestParams.put("checkin", "2017-10-10");
        requestParams.put("checkout", "2016-04-04");
        requestParams.put("additionalneeds", "Dinner");


        RequestSpecification req = given()
        .header("Authorization", authToken)
                .header("Content-Type", "application/json")
                   .body(requestParams);

//        Response response =
//                given()
//                        .spec(request)
//                        .contentType(ContentType.JSON)
//                        .put("https://restful-booker.herokuapp.com/booking/id[0]");
//        response.then()
//                    .log().ifStatusCodeIsEqualTo(201);
//        Assert.assertEquals(response.statusCode(),201);


        given().spec(req)
                .contentType(ContentType.JSON)
                .body(requestParams.toJSONString())
                .log()
                .all()
                .when()
                .put("https://restful-booker.herokuapp.com/booking/1")
                .then()
                .log()
                .ifStatusCodeIsEqualTo(201)
                .assertThat()
                .statusCode(201)
                .extract()
                .response();
    }



    @Test
    public void validateExtractedResponsesTest() {
        RequestSpecification request = RestAssured.given();
        JsonPath jsonPath = request
                .get("https://chercher.tech/sample/api/product/read")
                .then()
                .extract()
                .jsonPath();


        Assert.assertEquals(jsonPath.getString("records.name[-1]"), "test");


        List<String> created = given().contentType(JSON)
                .get("https://chercher.tech/sample/api/product/read")
                .then()
                .extract().path("records.created");

        for (String s : created
        ) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.parse(s, formatter);

            assertThat(dateTime.isBefore(LocalDateTime.now()), is(true));

        }


    }
}
