package tests.Automation_Exercises;


import org.testng.annotations.Test;
import pages.AutomationExercisePage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.FakeTestData;
import utilities.ReusableMethods;
import utilities.TestBaseRapor;

public class AutomationExercise01 extends TestBaseRapor {

    AutomationExercisePage autEx = new AutomationExercisePage();

    @Test
    public void TC01() {
        extentTest = extentReports.createTest("Test Case 1: Register User", "User registers");
        // Step 1: Launch browser and navigate to the URL
        extentTest.info("Step 1: Launch browser and navigate to the URL");
        Driver.getDriver().get(ConfigReader.getProperty("autExUrl"));
        ReusableMethods.waitForPageToLoad(5);

        // Step 2: Click on 'Signup / Login' button
        extentTest.info("Step 2: Click on 'Signup / Login' button");
        autEx.headerSignupLoginLink.click();

        // Step 3: Verify 'New User Signup!' is visible
        extentTest.info("Step 3: Verify 'New User Signup!' is visible");
        autEx.loginContentFormNewUserSingupText.isDisplayed();

        // Step 4: Enter name and email address
        extentTest.info("Step 4: Enter name and email address");
        autEx.loginContentFormNameInput.sendKeys(FakeTestData.FAKE_USERNAME);
        autEx.loginContentFormEmailInput.sendKeys(FakeTestData.FAKE_EMAIL);

        // Step 5: Click 'Signup' button
        extentTest.info("Step 5: Click 'Signup' button");
        autEx.loginContentFormSignupButton.click();

        // Step 6: Verify that 'ENTER ACCOUNT INFORMATION' is visible
        extentTest.info("Step 6: Verify that 'ENTER ACCOUNT INFORMATION' is visible");
        autEx.signupContentLoginFormEnterAccountInformationText.isDisplayed();

        // Step 7: Fill details: Title, Name, Email, Password, Date of birth
        extentTest.info("Step 7: Fill details: Title, Name, Email, Password, Date of birth");
        autEx.signupContentLoginFormMrRadioButton.click();
        autEx.signupContentLoginFormPasswordInput.sendKeys(FakeTestData.FAKE_PASSWORD);

        // Select a day from the "Days" dropdown using its index
        extentTest.info("Step 7: Select a day from the 'Days' dropdown using its index");
        ReusableMethods.selectDropdownByIndex(autEx.signupContentLoginFormDaysDropdown, 1, "Days");

        // Select a month from the "Months" dropdown using its visible text
        extentTest.info("Step 7: Select a month from the 'Months' dropdown using its visible text");
        ReusableMethods.selectDropdownByText(autEx.signupContentLoginFormMonthsDropdown, "June", "Months");

        // Select a year from the "Years" dropdown using its value
        extentTest.info("Step 7: Select a year from the 'Years' dropdown using its value");
        ReusableMethods.selectDropdownByValue(autEx.signupContentLoginFormYearsDropdown, "1990", "Years");

        // Step 8: Select checkbox 'Sign up for our newsletter!'
        extentTest.info("Step 8: Select checkbox 'Sign up for our newsletter!'");
        autEx.signupContentLoginFormNewsletterRadioButton.click();

        // Step 9: Select checkbox 'Receive special offers from our partners!'
        extentTest.info("Step 9: Select checkbox 'Receive special offers from our partners!'");
        autEx.signupContentLoginFormSpecialOffersRadioButton.click();

        // Step 10: Fill details: First name, Last name, Company, Address, Address2, Country, State, City, Zipcode, Mobile Number
        extentTest.info("Step 10: Fill details: First name, Last name, Company, Address, Address2, Country, State, City, Zipcode, Mobile Number");
        autEx.signupContentLoginFormFirstNameInput.sendKeys(FakeTestData.FAKE_FIRST_NAME);
        autEx.signupContentLoginFormLastNameInput.sendKeys(FakeTestData.FAKE_LAST_NAME);
        autEx.signupContentLoginFormCompanyInput.sendKeys(FakeTestData.FAKE_COMPANY_NAME);
        autEx.signupContentLoginFormAdressInput.sendKeys(FakeTestData.FAKE_ADDRESS);
        autEx.signupContentLoginFormAdress2Input.sendKeys(FakeTestData.FAKE_ADDRESS_2);
        autEx.signupContentLoginFormCountryInput.sendKeys(FakeTestData.FAKE_COUNTRY);
        autEx.signupContentLoginFormStateInput.sendKeys(FakeTestData.FAKE_STATE);
        autEx.signupContentLoginFormCityInput.sendKeys(FakeTestData.FAKE_CITY);
        autEx.signupContentLoginFormZipCodeInput.sendKeys(FakeTestData.FAKE_ZIP_CODE);
        autEx.signupContentLoginFormMobileNumberInput.sendKeys(FakeTestData.FAKE_MOBILE_PHONE);

        // Step 11: Click 'Create Account button'
        extentTest.info("Step 11: Click 'Create Account button'");
        ReusableMethods.scrollToMiddleOfPage(Driver.getDriver(), autEx.signupContentLoginFormCreateAccountButton);
        autEx.signupContentLoginFormCreateAccountButton.click();


        //... (continue with additional steps if any)
    }
}
