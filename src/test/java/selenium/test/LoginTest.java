package selenium.test;

import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeDriver;
import selenium.test.pageobject.LoginPage;
import selenium.test.pageobject.MainPage;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginTest {
    private static ChromeDriver driver;
    private static LoginPage loginPage;
    private static MainPage mainPage;

    @BeforeAll
    static void build() {
        Properties properties = PropertiesReader.getProperties();
        System.setProperty("webdriver.chrome.driver", properties.getProperty("webdriver.chrome.driver"));
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        loginPage = new LoginPage(driver, properties);
        mainPage = new MainPage(driver, properties);
        mainPage.open();
    }

    @AfterAll
    static void destroy() {
        driver.close();
    }

    @Test
    @Order(1)
    void wrongLogin() {
        Assertions.assertTrue(loginPage.atPage());
        loginPage.enterUsername("username");
        loginPage.enterPassword("pas");
        loginPage.submit();
        Assertions.assertFalse(loginPage.atPage());
        Assertions.assertFalse(mainPage.atPage());
    }

    @Test
    @Order(2)
    void success() {
        mainPage.open();
        Assertions.assertTrue(loginPage.atPage());
        loginPage.enterUsername("u");
        loginPage.enterPassword("p");
        loginPage.submit();
        Assertions.assertFalse(loginPage.atPage());
        Assertions.assertTrue(mainPage.atPage());
    }

}
