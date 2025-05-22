package clients;

import endpoint.EndPoint;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import models.LoginUserRequest;
import models.LoginUserResponse;
import models.UserCreateRequest;

import static endpoint.EndPoint.*;
import static io.restassured.RestAssured.given;


public class UserClient {

    public static RequestSpecification requestSpecification() {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(EndPoint.BASE_URL);
    }

    @Step("Create new user")
    public ValidatableResponse createUser(UserCreateRequest userCreateRequest) {
        return requestSpecification()
                .body(userCreateRequest)
                .post(CREATE_USER)
                .then();
    }

    @Step("Login user")
    public ValidatableResponse loginUser(LoginUserRequest loginUserRequest) {
        return requestSpecification()
                .body(loginUserRequest)
                .post(LOGIN_USER)
                .then();
    }

    @Step("Delete user")
    public ValidatableResponse deleteUser(String accessToken) {
        return requestSpecification()
                .header("Authorization", accessToken)
                .delete(DELETE_USER)
                .then();
    }

    @Step("Get access token after login")
    public String getAccessToken(LoginUserRequest loginUserRequest) {
        LoginUserResponse response = loginUser(loginUserRequest)
                .extract()
                .as(LoginUserResponse.class);
        return response.getAccessToken();
    }

}
