package selenium.pageObjectTest.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Properties;

public class MainPage {
    private final WebDriver driver;
    private final Properties properties;

    public MainPage(WebDriver driver, Properties properties) {
        this.driver = driver;
        this.properties = properties;
    }

    public void open() {
        driver.get(properties.getProperty("mainPage"));
    }

    public void logOut() {
        WebElement logoutButton = new WebDriverWait(driver, 3)
                .until(driver -> driver.findElement(By.cssSelector("#signOut")));
        logoutButton.click();
    }

    public boolean atPage() {
        return driver.getCurrentUrl().equals(properties.getProperty("mainPage"));
    }
}
