package stellarBurgersPages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.page;

public class ObjLoginPage {

    @FindBy(how = How.XPATH, using = ".//a[text()='Зарегистрироваться']")
    private SelenideElement registerLink;
    @FindBy(how = How.XPATH, using = ".//a[text()='Восстановить пароль']")
    private SelenideElement forgotPasswordLink;
    @FindBy(how = How.XPATH, using = ".//input[@name='name']")
    public SelenideElement emailField;
    @FindBy(how = How.XPATH, using = ".//input[@name='Пароль']")
    public SelenideElement passwordField;
    @FindBy(how = How.XPATH, using = ".//button[text()='Войти']")
    private SelenideElement loginButton;

    @Step("Click register link")
    public ObjRegisterPage clickRegisterLink() {
        registerLink.click();
        return page(ObjRegisterPage.class);
    }

    @Step("Click forgot password link")
    public ObjForgotPasswordPage clickForgotPasswordLink() {
        forgotPasswordLink.click();
        return page(ObjForgotPasswordPage.class);
    }

    @Step("Fill email field")
    public void setEmailField(String email) {
        emailField.setValue(email);
    }

    @Step("Fill password field")
    public void setPasswordField(String password) {
        passwordField.setValue(password);
    }

    @Step("Fill login form")
    public ObjLoginPage fillLoginForm(String email, String password) {
        setEmailField(email);
        setPasswordField(password);
        return page(ObjLoginPage.class);
    }

    @Step("Click login button, go account page")
    public ObjAccountPage clickLoginButton() {
        loginButton.click();
        return page(ObjAccountPage.class);
    }

    @Step("Click login button")
    public boolean clickLoginButton(Condition condition) {
        loginButton.click();
        return loginButton.shouldBe(condition).isDisplayed();
    }

}
