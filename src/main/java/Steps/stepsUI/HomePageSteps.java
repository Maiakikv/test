package Steps.stepsUI;

import Pages.HomePage;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

public class HomePageSteps {
    HomePage homePage = new HomePage();

    @Step("Set username")
    public HomePageSteps setUserName(String userName){
        homePage.userName.sendKeys(userName);
    return this;
    }
    @Step("Set password")
    public HomePageSteps setPassword (String password){
        homePage.password.sendKeys(password);
        return this;
    }
    @Step("Click on login")
    public HomePageSteps clickOnLoginBtn (){
        homePage.login.click();
        return this;
    }
    @Step("Validate error message")
    public HomePageSteps errorTextValidation (String errorText){
        homePage.errorOutput.shouldHave(Condition.text(errorText));
        return this;
    }

}
