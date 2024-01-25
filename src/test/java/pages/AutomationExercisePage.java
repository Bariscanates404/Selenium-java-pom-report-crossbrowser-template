package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class AutomationExercisePage {

    public AutomationExercisePage() {

        PageFactory.initElements(Driver.getDriver(), this);

    }



    /*
     * ==========================================
     *   Main Page
     * ==========================================
     */


    //Main page  → Header → "Signup / Login" Link Locator
    @FindBy(xpath = "//header[@id='header'] //a[contains(text(), 'Signup')]")
    public WebElement headerSignupLoginLink;




    /*
     * ==========================================
     *   /Login
     * ==========================================
     */

    //Login page  → Content → Form → "New User Signup!" Text element
    @FindBy(xpath = "//section[@id='form'] //div[@class='row'] //*[contains(text(), 'User Signup')]")
    public WebElement loginContentFormNewUserSingupText;

    //Login page  → Content → Form → "Name" input element
    @FindBy(xpath = "//section[@id='form']  //div[@class='signup-form'] //input[@name='name']")
    public WebElement loginContentFormNameInput;

    //Login page  → Content → Form → "Email Adress" input element
    @FindBy(xpath = "//section[@id='form']  //div[@class='signup-form'] //input[@name='email']")
    public WebElement loginContentFormEmailInput;

    //Login page  → Content → Form → "Singup" button element
    @FindBy(xpath = "//section[@id='form'] //div[@class='signup-form']  //button[contains(text(), 'Signup')]")
    public WebElement loginContentFormSignupButton;




    /*
     * ==========================================
     *   /signup
     * ==========================================
     */


    //Signup page  → Content → Login Form → "Enter Account Information" Text element
    @FindBy(xpath = "//section[@id='form']//div[@class='login-form']//*[contains(text(), 'Enter Account Information')]")
    public WebElement signupContentLoginFormEnterAccountInformationText;

    //Signup page  → Content → Login Form → "Mr" radio button
    @FindBy(xpath = "//section[@id='form']//div[@class='login-form']//input[@id='id_gender1']")
    public WebElement signupContentLoginFormMrRadioButton;


    //Signup page  → Content → Login Form → Password Input
    @FindBy(xpath = "//section[@id='form']//div[@class='login-form']//input[@id='password']")
    public WebElement signupContentLoginFormPasswordInput;

    //Signup page  → Content → Login Form → Days Dropdown
    @FindBy(xpath = "//select[@id='days']")
    public WebElement signupContentLoginFormDaysDropdown;

    //Signup page  → Content → Login Form → Months Dropdown
    @FindBy(xpath = "//select[@id='months']")
    public WebElement signupContentLoginFormMonthsDropdown;

    //Signup page  → Content → Login Form → Years Dropdown
    @FindBy(xpath = "//select[@id='years']")
    public WebElement signupContentLoginFormYearsDropdown;


    //Signup page  → Content → Login Form → Newslatter Radio Button
    @FindBy(xpath = "//div[@class='login-form']//input[@id='newsletter']")
    public WebElement signupContentLoginFormNewsletterRadioButton;

    //Signup page  → Content → Login Form → Special Offers Radio Button
    @FindBy(xpath = "//div[@class='login-form']//input[@id='optin'] ")
    public WebElement signupContentLoginFormSpecialOffersRadioButton;

    //Signup page  → Content → Login Form → First Name Input
    @FindBy(xpath = "//div[@class='login-form']//input[@id='first_name']")
    public WebElement signupContentLoginFormFirstNameInput;

    //Signup page  → Content → Login Form → Last Name Input
    @FindBy(xpath = "//div[@class='login-form']//input[@id='last_name']")
    public WebElement signupContentLoginFormLastNameInput;

    //Signup page  → Content → Login Form → Company Input
    @FindBy(xpath = "//div[@class='login-form']//input[@id='company']")
    public WebElement signupContentLoginFormCompanyInput;

    //Signup page  → Content → Login Form → Adress Inout
    @FindBy(xpath = "//div[@class='login-form']//input[@id='address1']")
    public WebElement signupContentLoginFormAdressInput;

    //Signup page  → Content → Login Form → Adress2 Input
    @FindBy(xpath = "//div[@class='login-form']//input[@id='address2']")
    public WebElement signupContentLoginFormAdress2Input;

    //Signup page  → Content → Login Form → Country Input
    @FindBy(xpath = "//div[@class='login-form']//select[@id='country']")
    public WebElement signupContentLoginFormCountryInput;

    //Signup page  → Content → Login Form → State Input
    @FindBy(xpath = "//div[@class='login-form']//input[@id='state']")
    public WebElement signupContentLoginFormStateInput;

    //Signup page  → Content → Login Form → City Input
    @FindBy(xpath = "//div[@class='login-form']//input[@id='city']")
    public WebElement signupContentLoginFormCityInput;

    //Signup page  → Content → Login Form → ZipCode Input
    @FindBy(xpath = "//div[@class='login-form']//input[@id='zipcode']")
    public WebElement signupContentLoginFormZipCodeInput;

    //Signup page  → Content → Login Form → Mobile Number Input
    @FindBy(xpath = "//div[@class='login-form']//input[@id='mobile_number']")
    public WebElement signupContentLoginFormMobileNumberInput;

    //Signup page  → Content → Login Form → Create Account Button
    @FindBy(xpath = "//div[@class='login-form']//button[@data-qa='create-account']")
    public WebElement signupContentLoginFormCreateAccountButton;










}