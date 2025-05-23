package config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

public class BrowserConfig {

    public static WebDriver setupBrowser(String browserName) {
        WebDriver driver;

        switch (browserName.toLowerCase()) {
            case "yandex":
                WebDriverManager.chromedriver().setup();
                ChromeOptions yandexOptions = new ChromeOptions();
                yandexOptions.setBinary(System.getenv("YANDEX_BROWSER_PATH"));
                driver = new ChromeDriver(yandexOptions);
                break;

            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
        }

        setWebDriver(driver);
        return driver;
    }

}
