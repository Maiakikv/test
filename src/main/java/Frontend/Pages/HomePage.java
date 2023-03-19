package Frontend.Pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class HomePage {
    public SelenideElement userName = $("#userName"),
            password = $("#password"),
            login = $("#login"),
            errorOutput = $("#output");

}
