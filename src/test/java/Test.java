import Data.Constants.ConstantData;
import Data.request.AuthorizationRequest;
import Data.request.CreateUserRequest;
import Data.response.ErrorAuthorizeResponse;
import Steps.stepsAPI.APIsteps;
import Steps.stepsUI.HomePageSteps;
import Steps.stepsUI.ProfilePageSteps;
import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

import static com.codeborne.selenide.Selenide.open;
@Epic("User register and login scenarios")
@Feature("Register new user, login with new user credentials, login and authorize with deleted credentials")
@Story("Create new user, Login with newly added user, Delete user account and validate popup message, " +
        "Login with deleted user and validate error text, Authorize with deleted user credentials, validate message")
public class Test {

    APIsteps step = new APIsteps();
    HomePageSteps homePageSteps = new HomePageSteps();
    ProfilePageSteps profilePageSteps =new ProfilePageSteps();
    ObjectMapper mapper = new ObjectMapper();
    @BeforeClass
    public static void setUp() {
        Configuration.browser = Browsers.CHROME;
        Configuration.startMaximized = true;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
    }


    @org.testng.annotations.Test (priority = 1)
    @Description("Create new user")
    public void createUserTest() throws JsonProcessingException {
        CreateUserRequest createUserRequest = new CreateUserRequest(ConstantData.name, ConstantData.password);
        String body = mapper.writeValueAsString(createUserRequest);
        Response response = step.createUserStep(body);
        response.prettyPrint();
//        CreatedUserResponse e = response.body().as(CreatedUserResponse.class);
//        Assert.assertEquals(e.books().size(), 0);


    }

    @org.testng.annotations.Test(priority = 2)
    @Description("Login with newly added user (successful scenario)")
    public void loginWithNewlyAddedUser()  {
        open("https://demoqa.com/login");
        homePageSteps.setUsertName(ConstantData.name)
                        .setPassword(ConstantData.password)
                        .clickOnLoginBtn();
    }


    @org.testng.annotations.Test(priority = 3)
    @Description("Delete user account")
    public void deleteUserAccount()  {
       profilePageSteps
               .clickOnDeleteBtn()
               .validatePopupWindowText(ConstantData.popupMessageUserDeleted);
    }



    @org.testng.annotations.Test(priority = 4)
    @Description("Login with deleted user credentials")
    public void loginWithDeletedCredentials()  {
        loginWithNewlyAddedUser();
        homePageSteps.errorTextValidation(ConstantData.errorText);
    }



    @org.testng.annotations.Test(priority = 5)
    @Description("Authorize with deleted user credentials")
    public void authorizeWithDeletedCredentials() throws JsonProcessingException {
        AuthorizationRequest auth = new AuthorizationRequest(ConstantData.name, ConstantData.password);
        String body = mapper.writeValueAsString(auth);
        Response response = step.Authorize(body);
  //      step.validateResponseMessage(ConstantData.userNotFound);

        ErrorAuthorizeResponse errorAuthorizeResponse = response.body().as(ErrorAuthorizeResponse.class);
        Assert.assertEquals(errorAuthorizeResponse.message(), ConstantData.userNotFound);


    }

}
