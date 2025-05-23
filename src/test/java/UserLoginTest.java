import clients.UserClient;
import models.LoginUserRequest;
import  models.User;
import models.UserCreateRequest;
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
public class UserLoginTest extends ParameterizedBaseTest {

    private User user;
    private ObjHomePage objHomePage;
    private UserClient userClient;

    @Before
    public void setUp() {
        userClient = new UserClient();
        user = GenerateUser.getRandomUser();
        UserCreateRequest userCreateRequest = new UserCreateRequest(
                user.getEmail(),
                user.getPassword(),
                user.getName()
        );
        userClient.createUser(userCreateRequest).statusCode(200);

        objHomePage = open(ObjHomePage.URL, ObjHomePage.class);
    }

    @After
    public void clearState() {
        try {
            LoginUserRequest loginUserRequest = new LoginUserRequest(
                    user.getEmail(),
                    user.getPassword()
            );
            String accessToken = userClient.getAccessToken(loginUserRequest);
            if (accessToken != null) {
                userClient.deleteUser(accessToken).statusCode(202);
            }
        } catch (Exception e) {
            System.out.println("Удаление пользователя не удалось: " + e.getMessage());
        }

        user = null;
        Selenide.clearBrowserLocalStorage();
    }

    @Test
    @DisplayName("Login user by login button")
    public void loginUserByLoginButtonTest() {
        objHomePage = open(ObjHomePage.URL, ObjHomePage.class);
        boolean isDisplayed = objHomePage.clickLoginButton()
                .fillLoginForm(user.getEmail(), user.getPassword())
                .clickLoginButton(Condition.hidden);

        assertFalse(isDisplayed);
    }

    @Test
    @DisplayName("Login user by account button")
    public void loginUserByAccountButtonTest() {
        objHomePage = open(ObjHomePage.URL, ObjHomePage.class);
        boolean isDisplayed = objHomePage.clickAccountButton()
                .fillLoginForm(user.getEmail(), user.getPassword())
                .clickLoginButton(Condition.hidden);

        assertFalse(isDisplayed);
    }

    @Test
    @DisplayName("Login user by register page")
    public void loginUserByRegisterPageTest() {
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
    public void loginUserByForgotPasswordPageTest() {
        objHomePage = open(ObjHomePage.URL, ObjHomePage.class);
        boolean isDisplayed = objHomePage.clickLoginButton()
                .clickForgotPasswordLink()
                .clickLoginLink()
                .fillLoginForm(user.getEmail(), user.getPassword())
                .clickLoginButton(Condition.hidden);

        assertFalse(isDisplayed);
    }

}
