import Data.request.Authorization;
import Data.request.CreateToken;
import Data.request.CreateUserRequest;
import Data.response.AuthorizeResponse;
import Data.response.CreatedUserResponse;
import Data.response.Token;
import Steps.StepClass;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.Assert;

public class Test {
    StepClass step = new StepClass();
    ObjectMapper mapper = new ObjectMapper();

    @org.testng.annotations.Test
    public void createUserTest() throws JsonProcessingException {
        CreateUserRequest createUserRequest = new CreateUserRequest("maia", "Maia@1245125");
        String body = mapper.writeValueAsString(createUserRequest);
        Response response = step.createUserStep(body);
        response.prettyPrint();
        CreatedUserResponse e = response.body().as(CreatedUserResponse.class);
        Assert.assertEquals(e.books().size(), 0);


    }

    @org.testng.annotations.Test
    public void createTokenTest() throws JsonProcessingException {
        CreateToken createToken = new CreateToken("maia", "Maia@1234");
        String body = mapper.writeValueAsString(createToken);
        Response response = step.createTokentep(body);
        Token token = response.body().as(Token.class);
        Assert.assertEquals(token.status(), "Success");
        Assert.assertEquals(token.result(), "User authorized successfully.");


    }

    @org.testng.annotations.Test
    public void authTest() throws JsonProcessingException {
        Authorization auth = new Authorization("TOOLSQA-Test", "Test@@123");
        String body = mapper.writeValueAsString(auth);
        Response response = step.Authorize(body);
  //      response.then().assertThat().body(is(String.valueOf(true)));

        AuthorizeResponse authorizeResponse = new AuthorizeResponse(true);
        Assert.assertEquals(String.valueOf(authorizeResponse.auth()), response.asString());


    }

}
