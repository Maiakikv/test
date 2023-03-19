
import Backend.Data.BackEndConstants;
import Backend.Steps.Case1ApiSsteps;
import Backend.Utils.Utils;
import Frontend.Data.FrontEndConstants;
import Frontend.Steps.HomePageSteps;
import Frontend.Steps.ProfilePageSteps;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;



import static com.codeborne.selenide.Selenide.open;

@Epic("User register and login scenarios")
@Feature("Register new user, login with new user credentials, login and authorize with deleted credentials")
@Story("Create new user, Login with newly added user, Delete user account and validate popup message, " +
        "Login with deleted user and validate error text, Authorize with deleted user credentials, validate message")
public class CaseOneTest extends BaseTest {

    Case1ApiSsteps step = new Case1ApiSsteps();
    HomePageSteps homePageSteps = new HomePageSteps();
    ProfilePageSteps profilePageSteps = new ProfilePageSteps();


    @Test(priority = 1)
    @Description("Create new user")
    public void createUserTest() throws JsonProcessingException {
        Response response = step.createUserStep(BackEndConstants.userName, BackEndConstants.password, Utils.StatusCode.equals(HttpStatus.SC_CREATED));
    }


    @Test(priority = 2)
    @Description("Login with newly added user (successful scenario)")
    public void loginWithNewlyAddedUser() {
        open("https://demoqa.com/login");
        homePageSteps
                .setUserName(BackEndConstants.userName)
                .setPassword(BackEndConstants.password)
                .clickOnLoginBtn();
    }


    @Test(priority = 3)
    @Description("Delete user account")
    public void deleteUserAccount() {
        profilePageSteps
                .clickOnDeleteBtn()
                .confirmDelete()
                .validatePopupWindowText(FrontEndConstants.popupMessageUserDeleted);
    }


    @Test(priority = 4)
    @Description("Login with deleted user credentials")
    public void loginWithDeletedCredentials() {
        loginWithNewlyAddedUser();
        homePageSteps.errorTextValidation(FrontEndConstants.uiLoginErrorMessage);
    }


    @Test(priority = 5)
    @Description("Authorize with deleted user credentials")
    public void authorizeWithDeletedCredentials() throws JsonProcessingException {
        Response response = step.Authorize(BackEndConstants.userName, BackEndConstants.password,Utils.StatusCode.equals(HttpStatus.SC_NOT_FOUND));
        step.validateAuthorizeMessage(response);
    }

}
