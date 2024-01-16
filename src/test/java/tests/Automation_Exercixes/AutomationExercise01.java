package tests.Automation_Exercixes;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AutomationExercisePage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;
import utilities.TestBaseRapor;

import java.io.IOException;

public class AutomationExercise01 extends TestBaseRapor {
    AutomationExercisePage autEx = new AutomationExercisePage();

    @Test
    public void TC01() {
        extentTest = extentReports.createTest("Test Case 1: Register User", "User registers");
        extentTest.info("1. Launch browser, navigate to url and Verify that home page is visible successfully");
        // Automation Ex sayfasına gider.
        ReusableMethods.waitForPageToLoad(10);
        Driver.getDriver().get(ConfigReader.getProperty("autExUrl"));
        extentTest.info("2. Click on 'Signup / Login' button");
        //Signup/Login linkine tıklar.
        autEx.signupLoginLink.click();
        ReusableMethods.webelementScreenshotCek(autEx.signupLoginLink , "logingElementi");

        extentTest.info("3. Verify 'New User Signup!' is visible");

        extentTest.info("4. Enter name and email address");

        extentTest.info("5. Click 'Signup' button");

        extentTest.pass("6. Verify that 'ENTER ACCOUNT INFORMATION' is visible");

        Driver.closeDriver();
    }


}