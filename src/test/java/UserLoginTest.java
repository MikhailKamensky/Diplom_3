import User.User;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import io.qameta.allure.Epic;
import io.qameta.allure.junit4.DisplayName;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.Condition;
import stellarBurgersPages.ObjHomePage;
import utils.GenerateUser;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.*;

@Epic("Login user")
public class UserLoginTest {

    private User user;
    private ObjHomePage objHomePage;

    @Before
    public void setUp() {
        user = GenerateUser.getRandomUser();
        objHomePage = open(ObjHomePage.URL, ObjHomePage.class);
        objHomePage.clickLoginButton()
                .clickRegisterLink()
                .fillRegisterForm(user.getName(), user.getEmail(), user.getPassword())
                .clickRegisterButton(Condition.hidden);
        objHomePage = null;
    }

    @After
    public void clearState() {
        user = null;
        Selenide.clearBrowserLocalStorage();
    }

    @Test
    @DisplayName("Login user by login button")
    public void loginUserByLoginButton() {
        objHomePage = open(ObjHomePage.URL, ObjHomePage.class);
        boolean isDisplayed = objHomePage.clickLoginButton()
                .fillLoginForm(user.getEmail(), user.getPassword())
                .clickLoginButton(Condition.hidden);

        assertFalse(isDisplayed);
    }

    @Test
    @DisplayName("Login user by account button")
    public void loginUserByAccountButton() {
        objHomePage = open(ObjHomePage.URL, ObjHomePage.class);
        boolean isDisplayed = objHomePage.clickAccountButton()
                .fillLoginForm(user.getEmail(), user.getPassword())
                .clickLoginButton(Condition.hidden);

        assertFalse(isDisplayed);
    }

    @Test
    @DisplayName("Login user by register page")
    public void loginUserByRegisterPage() {
        objHomePage = open(ObjHomePage.URL, ObjHomePage.class);
        boolean isDisplayed = objHomePage.clickLoginButton()
                .clickRegisterLink()
                .clickLoginLink()
                .fillLoginForm(user.getEmail(), user.getPassword())
                .clickLoginButton(Condition.hidden);

        assertFalse(isDisplayed);
    }

    @Test
    @DisplayName("Login user by forgot password page")
    public void loginUserByForgotPasswordPage() {
        objHomePage = open(ObjHomePage.URL, ObjHomePage.class);
        boolean isDisplayed = objHomePage.clickLoginButton()
                .clickForgotPasswordLink()
                .clickLoginLink()
                .fillLoginForm(user.getEmail(), user.getPassword())
                .clickLoginButton(Condition.hidden);

        assertFalse(isDisplayed);
    }

}
