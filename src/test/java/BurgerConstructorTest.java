import io.qameta.allure.Description;
import org.junit.Test;
import org.junit.Before;
import io.qameta.allure.Epic;
import io.qameta.allure.junit4.DisplayName;

import stellarBurgersPages.ObjHomePage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.*;

@Epic("Navigate burger constructor")
public class BurgerConstructorTest extends ParameterizedBaseTest {

    private ObjHomePage objHomePage;

    @Before
    public void setUp() {
        objHomePage = open(ObjHomePage.URL, ObjHomePage.class);
    }

    @Test
    @DisplayName("Navigate to last bun ingredient")
    @Description("Verify that Bun tab becomes active when user clicks on a bun ingredient in the constructor")
    public void navigateToBunIngredientTest() {
        boolean isActive = objHomePage.findBunIngredient();
        assertTrue("Bun tab should be active after clicking bun ingredient", isActive);
    }

    @Test
    @DisplayName("Navigate to last sauce ingredient")
    @Description("Verify that Sauce tab becomes active when user clicks on a sauce ingredient in the constructor")
    public void navigateToSauceIngredientTest() {
        boolean isActive = objHomePage.findSauceIngredient();
        assertTrue("Sauce tab should be active after clicking sauce ingredient", isActive);
    }

    @Test
    @DisplayName("Navigate to last filling ingredient")
    @Description("Verify that Filling tab becomes active when user clicks on a filling ingredient in the constructor")
    public void navigateToFillingIngredientTest() {
        boolean isActive = objHomePage.findFillingIngredient();
        assertTrue("Filling tab should be active after clicking filling ingredient", isActive);
    }

}
