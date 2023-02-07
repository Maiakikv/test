import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;


public class HamcrestAssertions {
    @Test
    public void hamcrestAssertionsTest() {

        Response response = given()
                .when()
                .get("http://ergast.com/api/f1/2017/circuits.json");

        response.
                then()
                .assertThat()
                .body("MRData.CircuitTable.Circuits.circuitId", hasItem("marina_bay"))
                .body("MRData.CircuitTable.Circuits.Location[1, -1].country", hasItems("USA", "UAE"));

        String locationL = response.then().extract().jsonPath().getString("MRData.CircuitTable.Circuits.Location[-1].long");
        double locationLong = Double.parseDouble(locationL);
        assertThat(locationLong, Matchers.anyOf(greaterThan(1.0),equalTo(10.0)));



    }
}
