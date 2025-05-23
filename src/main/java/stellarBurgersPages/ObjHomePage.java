package stellarBurgersPages;

import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ElementsCollection;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class ObjHomePage {

    public static final String URL = "https://stellarburgers.nomoreparties.site/";
    @FindBy(how = How.XPATH, using = ".//p[text()='Личный Кабинет']")
    private SelenideElement accountButton;
    @FindBy(how = How.XPATH, using = ".//button[text()='Войти в аккаунт']")
    private SelenideElement loginButton;
    @FindBy(how = How.CLASS_NAME, using = "BurgerIngredients_ingredients__list__2A-mT")
    private ElementsCollection menuIngredients;

    @FindBy(how = How.XPATH, using = ".//div[contains(@class, 'tab_tab__1SPyG')][1]")
    private SelenideElement bunTab;
    @FindBy(how = How.XPATH, using = ".//div[contains(@class, 'tab_tab__1SPyG')][2]")
    private SelenideElement sauceTab;
    @FindBy(how = How.XPATH, using = ".//div[contains(@class, 'tab_tab__1SPyG')][3]")
    private SelenideElement fillingTab;

    @Step("Click account button")
    public ObjLoginPage clickAccountButton() {
        accountButton.click();
        return page(ObjLoginPage.class);
    }

    @Step("Click account button, go account page")
    public ObjAccountPage clickAccountButtonGoAccountPage() {
        accountButton.click();
        return page(ObjAccountPage.class);
    }

    @Step("Click login button")
    public ObjLoginPage clickLoginButton() {
        loginButton.click();
        return page(ObjLoginPage.class);
    }

    @Step("Find last bun ingredient")
    public boolean findBunIngredient() {
        SelenideElement bun = menuIngredients.get(0).lastChild();
        bun.scrollIntoView(true);
        bun.click();
        return bunTab.has(cssClass("tab_tab_type_current__2BEPc"));
    }

    @Step("Find last sauce ingredient")
    public boolean findSauceIngredient() {
        SelenideElement sauce = menuIngredients.get(1).lastChild();
        sauce.scrollIntoView(true);
        sauce.click();
        return sauceTab.has(cssClass("tab_tab_type_current__2BEPc"));
    }

    @Step("Find last filling ingredient")
    public boolean findFillingIngredient() {
        SelenideElement filling = menuIngredients.get(2).lastChild();
        filling.scrollIntoView(true);
        filling.click();
        return fillingTab.has(cssClass("tab_tab_type_current__2BEPc"));
    }

}
