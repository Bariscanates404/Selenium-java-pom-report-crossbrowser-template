package tests.Automation_Exercixes;

import com.github.javafaker.Faker;
import org.openqa.selenium.support.ui.Select;
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
    String fakeLastName = faker.name().lastName();
    String fakeEmail = faker.internet().emailAddress();
    String fakePassword = faker.internet().password();
    String fakeAddress = faker.address().streetAddressNumber();
    String fakeAddress2 = faker.address().fullAddress();
    String fakeMobilePhone = faker.phoneNumber().cellPhone();
    String fakeCompanyName = faker.company().name();
    String fakecountry = faker.country().name();
    String fakeState = faker.address().state();
    String fakeCity = faker.address().city();
    String fakeZipCode = faker.address().zipCode();

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
        extentTest.info("6. Enter name and email address");
        autEx.loginContentFormNameInput.sendKeys(fakeUserName);
        autEx.loginContentFormEmailInput.sendKeys(fakeEmail);
        extentTest.info("7. Click 'Signup' button");
        autEx.loginContentFormSignupButton.click();
        extentTest.pass("8. Verify that 'ENTER ACCOUNT INFORMATION' is visible");
        autEx.signupContentLoginFormEnterAccountInformationText.isDisplayed();
        extentTest.pass("9. Fill details: Title, Name, Email, Password, Date of birth");
        // Click on the "Mr" radio button in the title section
        autEx.signupContentLoginFormMrRadioButton.click();

        // Enter a fake password into the password input field
        autEx.signupContentLoginFormPasswordInput.sendKeys(fakePassword);

        // Select a day from the "Days" dropdown using its index
        Select select = new Select(autEx.signupContentLoginFormDaysDropdown);
        select.selectByIndex(1);

        // Select a month from the "Months" dropdown using its visible text
        Select select1 = new Select(autEx.signupContentLoginFormMonthsDropdown);
        select1.selectByVisibleText("June");

        // Select a year from the "Years" dropdown using its value
        Select select2 = new Select(autEx.signupContentLoginFormYearsDropdown);
        select2.selectByValue("1990");

        extentTest.pass("10. Select checkbox 'Sign up for our newsletter!");
        autEx.signupContentLoginFormNewsletterRadioButton.click();

        extentTest.pass("11. Select checkbox 'Receive special offers from our partners!'");
        autEx.signupContentLoginFormSpecialOffersRadioButton.click();
        extentTest.pass("12. Fill details: First name, Last name, Company, Address, Address2, Country, State, City, Zipcode, Mobile Number");
        // Input fake first name
        autEx.signupContentLoginFormFirstNameInput.sendKeys(fakeFirstName);
        // Input fake last name
        autEx.signupContentLoginFormLastNameInput.sendKeys(fakeLastName);
        // Input fake company name
        autEx.signupContentLoginFormCompanyInput.sendKeys(fakeCompanyName);
        // Input fake address
        autEx.signupContentLoginFormAdressInput.sendKeys(fakeAddress);
        // Input fake address2
        autEx.signupContentLoginFormAdress2Input.sendKeys(fakeAddress2);
        // Input fake country
        autEx.signupContentLoginFormCountryInput.sendKeys(fakecountry);
        // Input fake state
        autEx.signupContentLoginFormStateInput.sendKeys(fakeState);
        // Input fake city
        autEx.signupContentLoginFormCityInput.sendKeys(fakeCity);
        // Input fake zipcode
        autEx.signupContentLoginFormZipCodeInput.sendKeys(fakeZipCode);
        // Input fake mobile number
        autEx.signupContentLoginFormMobileNumberInput.sendKeys(fakeMobilePhone);

        extentTest.pass("13. Click 'Create Account button'");
        ReusableMethods.waitForAnElementsClickability(autEx.signupContentLoginFormCreateAccountButton,5000).click();

    }
}