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
        boolean isActive = objHomePage.findBunIngredient();
        assertTrue("Bun tab should be active after clicking bun ingredient", isActive);
    }

    @Test
    @DisplayName("Navigate to last sauce ingredient")
    public void navigateToSauceIngredient() {
        boolean isActive = objHomePage.findSauceIngredient();
        assertTrue("Sauce tab should be active after clicking sauce ingredient", isActive);
    }

    @Test
    @DisplayName("Navigate to last filling ingredient")
    public void navigateToFillingIngredient() {
        boolean isActive = objHomePage.findFillingIngredient();
        assertTrue("Filling tab should be active after clicking filling ingredient", isActive);
    }

}
