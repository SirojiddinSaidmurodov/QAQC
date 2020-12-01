package selenium.test;

import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SeleniumTestImplicitWaits {
    public static WebDriver driver;
    public static Properties properties;

    @BeforeAll
    public static void init() {
        properties = new Properties();
        try {
            properties.load(new FileReader("src/test/resources/selenium.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.setProperty("webdriver.chrome.driver", properties.getProperty("webdriver.chrome.driver"));
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }
    @AfterAll
    public static void close(){
        driver.quit();
    }

    @Test
    @Order(1)
    public void signupTest() {
        driver.get(properties.getProperty("signUpPage"));
        Assert.assertEquals("Sign Up", driver.getTitle());
        WebElement username = driver.findElement(By.cssSelector("#username"));
        WebElement password = driver.findElement(By.cssSelector("#password"));
        WebElement submitButton = driver.findElement(By.cssSelector("input[name=submitSignUp]"));
        username.sendKeys("sirojiddin2");
        password.sendKeys("mySecretPassword");
        submitButton.click();
        Assert.assertEquals("Login", driver.getTitle());
    }

    @Test
    @Order(2)
    public void loginTest() {
        driver.get(properties.getProperty("logInPage"));
        Assert.assertEquals("Login", driver.getTitle());
        WebElement username = driver.findElement(By.xpath("//input[@name='username']"));
        WebElement password = driver.findElement(By.xpath("html/body/div/div/form/div[2]/label/input"));
        WebElement submitButton = driver.findElement(By.xpath("//input[@type='submit']"));
        username.sendKeys("sirojiddin2");
        password.sendKeys("mySecretPassword");
        submitButton.click();
        Assert.assertEquals("Greeting", driver.getTitle());
    }

}
