package selenium.test;

import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeDriver;
import selenium.test.pageobject.LoginPage;
import selenium.test.pageobject.SignUpPage;

import java.util.Properties;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SignUpTest {
    private static ChromeDriver driver;
    private static SignUpPage signUpPage;
    private static LoginPage loginPage;

    @BeforeAll
    static void build() {
        Properties properties = PropertiesReader.getProperties();
        System.setProperty("webdriver.chrome.driver", properties.getProperty("webdriver.chrome.driver"));
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        signUpPage = new SignUpPage(driver, properties);
        loginPage = new LoginPage(driver, properties);
    }

    @AfterAll
    static void destroy() {
        driver.close();
    }

    @Test
    @Order(1)
    void wrongLogin() {
        signUpPage.open();
        signUpPage.enterUsername("s asf   asf ");
        signUpPage.submit();
        Assertions.assertTrue(signUpPage.atPage());
        Assertions.assertFalse(signUpPage.isLoginValid());
    }

    @Test
    @Order(2)
    void wrongPassword() {
        signUpPage.open();
        signUpPage.enterPassword("ssd");
        signUpPage.submit();
        Assertions.assertTrue(signUpPage.atPage());
        Assertions.assertFalse(signUpPage.isPasswordValid());
    }

    @Test
    @Order(3)
    void userExists() {
        signUpPage.open();
        signUpPage.enterUsername("u");
        signUpPage.enterUsername("p");
        signUpPage.submit();
        Assertions.assertTrue(signUpPage.atPage());
        Assertions.assertFalse(signUpPage.isLoginValid());
    }

    @Test
    @Order(4)
    void successSignUp() {
        signUpPage.open();
        signUpPage.enterUsername("sirojiddin");
        signUpPage.enterPassword("mySecretPassword");
        signUpPage.submit();
        Assertions.assertTrue(loginPage.atPage());
    }
}
