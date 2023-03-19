package Backend.Utils;

import io.restassured.response.Response;
import org.testng.Assert;

public class Utils {

    public static class StatusCode{

        public int statusCode;

        public boolean equals;

        private StatusCode(int _code, boolean equals){
            this.statusCode = _code;
            this.equals = equals;
        }

        public static StatusCode equals(int _code){
            return new StatusCode(_code, true);
        }

        public static StatusCode notEquals(int _code){
            return new StatusCode(_code, false);
        }

        public static void assertStatusCode(Response response, StatusCode statusCode){
            if(statusCode.equals) {
                response.then().assertThat().statusCode(statusCode.statusCode);
            } else {
                Assert.assertNotEquals(statusCode.statusCode, response.getStatusCode(), "Status code equals to " + statusCode.statusCode);
            }
        }
    }
}
