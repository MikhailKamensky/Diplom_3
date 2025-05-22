package utils;

import io.qameta.allure.Step;
import models.LoginUserRequest;
import models.User;
import io.qameta.allure.Allure;
import org.apache.commons.lang3.RandomStringUtils;
import models.UserCreateRequest;
import clients.UserClient;

public class GenerateUser {

    public static User getRandomUser() {
        String name = RandomStringUtils.randomAlphabetic(8);
        String email = name.toLowerCase() + "@yandex.ru";
        String password = RandomStringUtils.randomAlphabetic(8);

        Allure.addAttachment("Email : ", email);
        Allure.addAttachment("Password : ", password);
        Allure.addAttachment("Name : ", name);

        return new User(email, password, name);
    }

    @Step("Create user via API")
    public static User createUserViaApi() {
        User user = getRandomUser();
        UserCreateRequest userCreateRequest = new UserCreateRequest(
                user.getEmail(),
                user.getPassword(),
                user.getName()
        );

        new UserClient().createUser(userCreateRequest)
                .statusCode(200);

        return user;
    }

    @Step("Delete user via API")
    public static void deleteUserViaApi(User user) {
        LoginUserRequest loginUserRequest = new LoginUserRequest(
                user.getEmail(),
                user.getPassword()
        );

        UserClient userClient = new UserClient();
        String accessToken = userClient.getAccessToken(loginUserRequest);
        userClient.deleteUser(accessToken)
                .statusCode(202);
    }
}
