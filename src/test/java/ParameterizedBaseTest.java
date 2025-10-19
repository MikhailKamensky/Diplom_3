import config.BrowserConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

@RunWith(Parameterized.class)
public class ParameterizedBaseTest {

    @Parameterized.Parameter
    public String browser;

    @Parameterized.Parameters(name = "Browser: {0}")
    public static Collection<Object[]> browsers() {
        return Arrays.asList(new Object[][]{
                {"chrome"},
                {"yandex"}
        });
    }

    @Before
    public void setUp() {
        BrowserConfig.setupBrowser(browser);
        open("https://stellarburgers.nomoreparties.site/");
    }

    @After
    public void tearDown() {
        closeWebDriver();
    }

}
