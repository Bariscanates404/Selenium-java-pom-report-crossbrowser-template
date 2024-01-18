package tests.Automation_Exercixes;

import com.github.javafaker.Faker;
import org.testng.annotations.Test;
import pages.AutomationExercisePage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;
import utilities.TestBaseRapor;

public class AutomationExercise01 extends TestBaseRapor {
    AutomationExercisePage autEx = new AutomationExercisePage();
    Faker faker = new Faker();
    String fakeUserName = faker.name().username();
    String fakeFirstName = faker.name().nameWithMiddle();
    String fakeEmail = faker.internet().emailAddress();
    String fakePassword = faker.internet().password();
    String fakeAddress = faker.address().streetAddressNumber();
    String fakeAddress2 = faker.address().fullAddress();
    String fakeMobilePhone = faker.phoneNumber().cellPhone();

    @Test
    public void TC01() {
        extentTest = extentReports.createTest("Test Case 1: Register User", "User registers");
        extentTest.info("1. 2. 3. Launch browser, navigate to url and Verify that home page is visible successfully");
        Driver.getDriver().get(ConfigReader.getProperty("autExUrl"));
        ReusableMethods.waitForPageToLoad(5);
        extentTest.info("4. Click on 'Signup / Login' button");
        autEx.headerSignupLoginLink.click();
        extentTest.info("5. Verify 'New User Signup!' is visible");
        autEx.loginContentFormNewUserSingupText.isDisplayed();
        extentTest.info("4. Enter name and email address");
        autEx.loginContentFormNameInput.sendKeys(fakeUserName);
        autEx.loginContentFormEmailInput.sendKeys(fakeEmail);
        extentTest.info("5. Click 'Signup' button");
        autEx.loginContentFormSignupButton.click();
        extentTest.pass("6. Verify that 'ENTER ACCOUNT INFORMATION' is visible");

    }


}