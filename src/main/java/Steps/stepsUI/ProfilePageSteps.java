package Steps.stepsUI;

import Pages.ProfilePage;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.Alert;

import static com.codeborne.selenide.Selenide.switchTo;

public class ProfilePageSteps {
    ProfilePage profilePage = new ProfilePage();

    @Step("delete user")
    public ProfilePageSteps clickOnDeleteBtn (){
        profilePage.deleteAccountBtn.scrollTo().click();
        profilePage.deleteAccountWindowOkBtn.click();
        return this;
    }

    @Step("validate alert text")
    public ProfilePageSteps validatePopupWindowText (String text){
        Alert simpleAlert = switchTo().alert();
        Assert.assertTrue(simpleAlert.getText().contains(text));
        simpleAlert.accept();
        return this;
    }

}
