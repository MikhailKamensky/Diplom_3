import clients.UserClient;
import models.LoginUserRequest;
import models.User;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import io.qameta.allure.Epic;
import io.qameta.allure.junit4.DisplayName;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.Condition;
import stellarBurgersPages.ObjHomePage;
import utils.GenerateUser;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.*;


@Epic("Register user")
public class UserRegisterTest {

    private User user;
    private ObjHomePage objHomePage;
    private UserClient userClient;

    @Before
    public void setUp() {
        user = GenerateUser.getRandomUser();
        userClient = new UserClient();
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
    @DisplayName("Register user by valid credentials")
    public void registerUserByValidCredentials() {
        boolean isDisplayed = objHomePage.clickLoginButton()
                .clickRegisterLink()
                .fillRegisterForm(user.getName(), user.getEmail(), user.getPassword())
                .clickRegisterButton(Condition.hidden);

        assertFalse(isDisplayed);
    }

    @Test
    @DisplayName("Register user by invalid password")
    public void registerUserByInvalidPassword() {
        boolean isDisplayed = objHomePage.clickLoginButton()
                .clickRegisterLink()
                .fillRegisterForm(user.getName(), user.getEmail(), "888")
                .clickRegisterButton(Condition.visible);

        assertTrue(isDisplayed);
    }

    @Test
    @DisplayName("Register user is displayed password error")
    public void registerUserIsDisplayedPasswordError() {
        boolean isDisplayed = objHomePage.clickLoginButton()
                .clickRegisterLink()
                .fillRegisterForm(user.getName(), user.getEmail(), "888")
                .isDisplayedPasswordError();

        assertTrue(isDisplayed);
    }

}
