
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalToObject;

public class test {
    @DataProvider(name = "circuitIdsAnsCountries")
    public Object[][] circuitIdsAnsCountries() {

        String firstcircuitId =
                given()
                        .when()
                        .get("http://ergast.com/api/f1/2017/circuits.json")
                        .then()
                        .extract().path("MRData.CircuitTable.Circuits.circuitId[1]");
        String fifthcircuitId =
                given()
                        .when()
                        .get("http://ergast.com/api/f1/2017/circuits.json")
                        .then()
                        .extract().path("MRData.CircuitTable.Circuits.circuitId[5]");
        System.out.println(firstcircuitId);
        System.out.println(fifthcircuitId);

        return new Object[][]{
                {firstcircuitId, "USA"},
                {fifthcircuitId, "Hungary"}
        };
    }

    @Test(dataProvider = "circuitIdsAnsCountries")
    public void test_NumberOfCircuits_ShouldBe_DataDriven(String circuitId, String country) {
        given().
                pathParam("circuitId", circuitId).
                when().
                get("http://ergast.com/api/f1/circuits/{circuitId}.json").
                then().
                assertThat().
                body("MRData.CircuitTable.Circuits.Location[0].country", equalToObject(country));
    }
}
