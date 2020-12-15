package selenium.pageObjectTest.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Properties;

public class LoginPage {

    private final WebDriver driver;
    private final Properties properties;

    @FindBy(xpath = "//input[@name='username']")
    private WebElement loginField;
    @FindBy(xpath = "html/body/div/div/form/div[2]/label/input")
    private WebElement passwordField;
    private WebElement submitButton;

    public LoginPage(WebDriver driver, Properties properties) {
        PageFactory.initElements(driver, this);

        this.driver = driver;
        this.properties = properties;
    }

    public void enterUsername(String username) {
        loginField.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void submit() {
        submitButton.click();
    }

    public void open() {
        driver.get(properties.getProperty("logInPage"));
    }

    public boolean atPage() {
        return driver.getCurrentUrl().equals(properties.getProperty("logInPage"));
    }

}
