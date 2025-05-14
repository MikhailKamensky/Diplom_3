import org.junit.Test;
import org.junit.Before;
import io.qameta.allure.Epic;
import io.qameta.allure.junit4.DisplayName;

import stellarBurgersPages.ObjHomePage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.*;

@Epic("Navigate burger constructor")
public class BurgerConstructorTest {

    private ObjHomePage objHomePage;

    @Before
    public void setUp() {
        objHomePage = open(ObjHomePage.URL, ObjHomePage.class);
    }

    @Test
    @DisplayName("Navigate to last bun ingredient")
    public void navigateToBunIngredient() {
        boolean isDisplayed = objHomePage.findBunIngredient();
        assertTrue(isDisplayed);
    }

    @Test
    @DisplayName("Navigate to last sauce ingredient")
    public void navigateToSauceIngredient() {
        boolean isDisplayed = objHomePage.findSauceIngredient();
        assertTrue(isDisplayed);
    }

    @Test
    @DisplayName("Navigate to last filling ingredient")
    public void navigateToFillingIngredient() {
        boolean isDisplayed = objHomePage.findFillingIngredient();
        assertTrue(isDisplayed);
    }

}
