package Frontend.Pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ProfilePage {
    public SelenideElement deleteAccountBtn = $(byText("Delete Account")),
            deleteAccountWindowOkBtn = $("#closeSmallModal-ok");

}
