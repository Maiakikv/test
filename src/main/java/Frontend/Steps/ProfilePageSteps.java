package Frontend.Steps;

import Frontend.Pages.ProfilePage;
import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.testng.Assert;


import static com.codeborne.selenide.Selenide.switchTo;

public class ProfilePageSteps {
    ProfilePage profilePage = new ProfilePage();

    @Step("Delete user")
    public ProfilePageSteps clickOnDeleteBtn (){
        profilePage.deleteAccountBtn.scrollTo().click();
                return this;
    }
    @Step("Confirm delete")
    public ProfilePageSteps confirmDelete (){
        profilePage.deleteAccountWindowOkBtn.click();
        return this;
    }

    @Step("Validate alert text")
    public ProfilePageSteps validatePopupWindowText (String text){
        Alert simpleAlert = switchTo().alert();
        Assert.assertEquals(simpleAlert.getText(), text);
        simpleAlert.accept();
        return this;
    }

}
