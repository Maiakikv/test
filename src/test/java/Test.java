import Data.Constants.ConstantData;
import Steps.stepsAPI.APIsteps;
import Steps.stepsUI.HomePageSteps;
import Steps.stepsUI.ProfilePageSteps;
import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;

import static com.codeborne.selenide.Selenide.open;

@Epic("User register and login scenarios")
@Feature("Register new user, login with new user credentials, login and authorize with deleted credentials")
@Story("Create new user, Login with newly added user, Delete user account and validate popup message, " +
        "Login with deleted user and validate error text, Authorize with deleted user credentials, validate message")
public class Test {

    APIsteps step = new APIsteps();
    HomePageSteps homePageSteps = new HomePageSteps();
    ProfilePageSteps profilePageSteps = new ProfilePageSteps();

    @BeforeClass
    public static void setUp() {
        Configuration.browser = Browsers.CHROME;
        Configuration.startMaximized = true;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
    }


    @org.testng.annotations.Test(priority = 1)
    @Description("Create new user")
    public void createUserTest() throws JsonProcessingException {
        Response response = step.createUserStep(ConstantData.userName, ConstantData.password);
        //      response.prettyPrint();


    }

    @org.testng.annotations.Test(priority = 2)
    @Description("Login with newly added user (successful scenario)")
    public void loginWithNewlyAddedUser() {
        open("https://demoqa.com/login");
        homePageSteps
                .setUserName(ConstantData.userName)
                .setPassword(ConstantData.password)
                .clickOnLoginBtn();
    }


    @org.testng.annotations.Test(priority = 3)
    @Description("Delete user account")
    public void deleteUserAccount() {
        profilePageSteps
                .clickOnDeleteBtn()
                .confirmDelete()
                .validatePopupWindowText(ConstantData.popupMessageUserDeleted);
    }


    @org.testng.annotations.Test(priority = 4)
    @Description("Login with deleted user credentials")
    public void loginWithDeletedCredentials() {
        loginWithNewlyAddedUser();
        homePageSteps.errorTextValidation(ConstantData.uiLoginErrorMessage);
    }


    @org.testng.annotations.Test(priority = 5)
    @Description("Authorize with deleted user credentials")
    public void authorizeWithDeletedCredentials() throws JsonProcessingException {
        Response response = step.Authorize(ConstantData.userName, ConstantData.password);
        step.validateAuthorizeMessage(response);


    }

}
