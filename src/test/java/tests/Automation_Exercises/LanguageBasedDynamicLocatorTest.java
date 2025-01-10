package tests.Automation_Exercises;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import utilities.Driver;
import utilities.TestBaseRapor;

import java.util.Set;

public class LanguageBasedDynamicLocatorTest extends TestBaseRapor {

    // Locators for different languages
    private final String exampleButtonEN = "//button[text()='EXPLORE']";
    private final String exampleButtonDE = "//button[text()='ENTDECKEN SIE']";

    @Test
    public void testLanguageBasedLocators() {
        extentTest = extentReports.createTest("Language-Based Locator Test", "Tests language-specific locators based on browser language.");

        // Step 1: Launch browser and navigate to flypgs.com
        extentTest.info("Step 1: Launch browser and navigate to flypgs.com");
        Driver.getDriver().get("https://www.flypgs.com");
        extentTest.info("URL opened successfully: https://www.flypgs.com");

        // Step 2: Wait for page to load
        extentTest.info("Step 2: Wait for page to load");
        utilities.ReusableMethods.waitForPageToLoad(10);
        extentTest.info("Page loaded successfully.");

        // Step 3: Extract cookies and determine language
        extentTest.info("Step 3: Extract cookies and determine language");
        Set<Cookie> cookies = Driver.getDriver().manage().getCookies();
        extentTest.info("Cookies retrieved: " + cookies.toString());

        String language = null;
        for (Cookie cookie : cookies) {
            extentTest.info("Inspecting cookie: Name = " + cookie.getName() + ", Value = " + cookie.getValue());
            if (cookie.getName().equals("language_code")) {
                language = cookie.getValue();
                extentTest.info("Language cookie found: " + language);
                break;
            }
        }

        if (language == null) {
            extentTest.fail("Language cookie not found! Test cannot proceed.");
            throw new RuntimeException("Language cookie not found!");
        }

        // Step 4: Choose locator based on language
        extentTest.info("Step 4: Choose locator based on language");
        String locatorToUse;
        if (language.equalsIgnoreCase("EN")) {
            locatorToUse = exampleButtonEN;
            extentTest.info("Language detected as EN. Using locator: " + locatorToUse);
        } else if (language.equalsIgnoreCase("DE")) {
            locatorToUse = exampleButtonDE;
            extentTest.info("Language detected as DE. Using locator: " + locatorToUse);
        } else {
            extentTest.fail("Unsupported language detected: " + language);
            throw new RuntimeException("Unsupported language: " + language);
        }

        // Step 5: Perform an action using the determined locator
        extentTest.info("Step 5: Perform an action using the determined locator");
        try {
            WebElement button = Driver.getDriver().findElement(By.xpath(locatorToUse));
            extentTest.info("Element located successfully with locator: " + locatorToUse);
            button.click();
            extentTest.pass("Successfully clicked the button for language: " + language);
        } catch (Exception e) {
            extentTest.fail("Failed to find or click the button. Locator: " + locatorToUse);
            throw e;
        }
    }
}
