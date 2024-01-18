package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class AutomationExercisePage {

    public AutomationExercisePage(){

        PageFactory.initElements(Driver.getDriver(),this);

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



}