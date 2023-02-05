import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasValue;
import static org.hamcrest.number.OrderingComparison.greaterThanOrEqualTo;
import static org.hamcrest.number.OrderingComparison.lessThanOrEqualTo;

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
                .body("MRData.CircuitTable.Circuits.Location[-1].long",
                        anyOf(greaterThan("1"), equalTo("10")))
                .body("MRData.CircuitTable.Circuits.Location[1, -1].country", hasItems("USA", "UAE"));


    }
}
