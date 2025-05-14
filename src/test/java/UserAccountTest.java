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

@Epic("Logout user and transitions between pages")
public class UserAccountTest {

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
    @DisplayName("Transition user to constructor")
    public void transitionToConstructor() {
        objHomePage = open(ObjHomePage.URL, ObjHomePage.class);
        String url = objHomePage.clickAccountButton()
                .fillLoginForm(user.getEmail(), user.getPassword())
                .clickLoginButton()
                .clickConstructor();

        assertEquals(ObjHomePage.URL, url);
    }

    @Test
    @DisplayName("Transition user to logo burger")
    public void transitionToLogoBurger() {
        objHomePage = open(ObjHomePage.URL, ObjHomePage.class);
        String url = objHomePage.clickAccountButton()
                .fillLoginForm(user.getEmail(), user.getPassword())
                .clickLoginButton()
                .clickLogoBurger();

        assertEquals(ObjHomePage.URL, url);
    }

    @Test
    @DisplayName("Logout user by logout button")
    public void logoutUserByLogoutButton() {
        objHomePage = open(ObjHomePage.URL, ObjHomePage.class);
        objHomePage.clickAccountButton()
                .fillLoginForm(user.getEmail(), user.getPassword())
                .clickLoginButton(Condition.hidden);

        boolean isDisplayed = objHomePage.clickAccountButtonGoAccountPage()
                .clickLogoutButton(Condition.hidden);

        assertFalse(isDisplayed);
    }

}
