package Steps.stepsUI;

import Pages.HomePage;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

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
        homePage.errorTextMessage.shouldHave(Condition.text(errorText));
    //    Assert.assertTrue(homePage.errorTextMessage.getText().contains(errorText));
        return this;
    }

}
