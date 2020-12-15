package cucumber.test.features.steps;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.By;
import selenium.pageObjectTest.PropertiesReader;

import java.util.Properties;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byValue;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class MyStepdefs {
    @RegisterExtension
    static ScreenShooterExtension screenshotEmAll =
            new ScreenShooterExtension(true).to("target/screenshots");
    private final Properties properties = PropertiesReader.getProperties();

    @When("open {string} page")
    public void openMainPage(String pageName) {
        open(properties.getProperty(pageName));
    }

    @Given("opened {string} page")
    @Then("{string} page should be opened")
    @And("{string} page opened")
    public void pageShouldBeOpened(String page) {
        Assertions.assertEquals(properties.getProperty(page), url());
    }

    @And("type to input with name {string} text: {string}")
    public void typeToInputWithNameText(String inputName, String inputText) {
        $(By.name(inputName)).should(exist);
        $(By.name(inputName)).setValue(inputText);
    }

    @When("pressed button with value {string}")
    @And("press button with value {string}")
    public void pressButtonWithValue(String buttonName) {
        $(byValue(buttonName)).should(exist);
        $(byValue(buttonName)).click();
    }

    @Then("message with tag: {string} should appear")
    public void messageWithTagShouldAppear(String tagName) {
        $(By.id("messages")).should(exist);
        $$(byId(tagName)).shouldHave(CollectionCondition.sizeGreaterThan(0));
    }
}
