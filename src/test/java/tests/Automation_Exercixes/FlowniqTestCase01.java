package tests.Automation_Exercixes;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AutomationExercisePage;
import pages.FlowniqPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;
import utilities.TestBaseRapor;

public class FlowniqTestCase01 extends TestBaseRapor {
    FlowniqPage fnq = new FlowniqPage();

    @Test
    public void test01() {
        extentTest = extentReports.createTest("Testin Adi", "FLOWNIQ TASK");

        extentTest.info("1. Visit our FLOWNIQ website at https://test-app.flowniq.com.");
        Driver.getDriver().get(ConfigReader.getProperty("FlowniqBaseUrl"));
        extentTest.info("2. Log into the web app using the following credentials:");
        fnq.loginPageKcformUsernameInput.sendKeys(ConfigReader.getProperty("FlowniqUsername"));
        fnq.loginPageKcformPasswordInput.sendKeys(ConfigReader.getProperty("FlowniqPassword"));
        fnq.loginPageKcformSubmitButton.click();
        extentTest.info("3. Create a new flow.");

        extentTest.info("4. Add an Input element to the form, label it “First name”, and make it required.");

        extentTest.info("5. Add a Date element to the form and make sure that it displays today’s date per default.");

        extentTest.pass("6. Give an info text of your choice to the Date element.");
        extentTest.pass("7. Check that a tooltip containing the info text appears on the Date element.");
        extentTest.pass("8. Save the flow and close it.");

        Driver.closeDriver();
    }
}