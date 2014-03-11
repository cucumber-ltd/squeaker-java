package squeaker;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.fail;

public class Stepdefs {

    private final WebDriver browser = new FirefoxDriver();

    @Given("^I'm not logged in$")
    public void I_m_not_logged_in() throws Throwable {
        // Of course we're not logged in
    }

    @When("^I visit the homepage$")
    public void I_visit_the_homepage() throws Throwable {
        visit("/");
    }

    @Then("^I should see \"([^\"]*)\"$")
    public void I_should_see(String expectedText) throws Throwable {
        String actualText = browser.findElement(By.tagName("body")).getText();
        assertContains(expectedText, actualText);
    }

    private void visit(String path) {
        String url = "http://squeaker.heroku.com" + path;
        browser.get(url);
    }

    private static void assertContains(String expectedText, String actualText) {
        if (!actualText.contains(expectedText)) {
            fail(String.format("Couldn't find [%s] in [%s]", expectedText, actualText));
        }
    }

    @After
    public void closeBrowser() {
        browser.close();
    }
}
