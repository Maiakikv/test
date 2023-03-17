package Steps.stepsUI;

import Pages.HomePage;
import io.qameta.allure.Step;
import org.testng.Assert;

public class HomePageSteps {
    HomePage homePage = new HomePage();

    @Step("set username")
    public HomePageSteps setUsertName (String userName){
        homePage.userName.sendKeys(userName);
    return this;
    }
    @Step("set password")
    public HomePageSteps setPassword (String password){
        homePage.password.sendKeys(password);
        return this;
    }
    @Step("click on login")
    public HomePageSteps clickOnLoginBtn (){
        homePage.login.click();
        return this;
    }
    @Step("validate error message")
    public HomePageSteps errorTextValidation (String errorText){
        Assert.assertTrue(homePage.errorTextMessage.getText().contains(errorText));
        return this;
    }

}
