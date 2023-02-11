import Data.Constants;
import Data.FilePathString;
import io.restassured.RestAssured;
import io.restassured.internal.path.xml.NodeChildrenImpl;
import io.restassured.response.Response;
import org.testng.Assert;
import java.io.IOException;


import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;


public class Test {
    @org.testng.annotations.Test
    void countriesTest() {
        RestAssured.baseURI = "http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso/ListOfContinentsByName";
        NodeChildrenImpl countryName = RestAssured.given().when()
                .get("")
                .then().extract().path("ArrayOftContinent.tContinent.sName");
        Assert.assertEquals(countryName.size(), Constants.sname.size());
        Assert.assertEquals(countryName, Constants.sname);
        String lastName = String.valueOf(countryName.get(countryName.size() - 1));
        Assert.assertEquals(lastName, Constants.sname.get(Constants.sname.size() - 1));

        String sCodeEqualtoAN = given().when()
                .get("")
                .then().extract().path("**.find { it.sCode == 'AN' }.sName");
        Assert.assertEquals(sCodeEqualtoAN, Constants.antarctica);



    }


    @org.testng.annotations.Test
    public void personTest() throws IOException {
        Response response = RestAssured
                .given()
                .baseUri("https://www.crcind.com/csp/samples/SOAP.Demo.CLS")
                .header("SOAPAction", "http://tempuri.org/SOAP.Demo.FindPerson")
                .header("Content-Type", "text/xml; charset=utf-8")
                .body(FilePathString.myRequest)
                .post();
        response.then().statusCode(200)
                .assertThat()
                .body("Envelope.Body.FindPersonResponse.FindPersonResult.Home.Street", equalTo(Constants.homeStreet));
        response.then()
                .assertThat()
                .body("Envelope.Body.FindPersonResponse.FindPersonResult.Office.Zip", equalTo(Integer.toString(Constants.officeZip)));

        response.prettyPrint();


    }


}
