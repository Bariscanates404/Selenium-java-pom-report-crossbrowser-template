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


    //Classic Tag name - Attribute - Value Selector
    @FindBy(xpath = "//header[@id='header'] //a[contains(text(), 'Signup')]")
    public WebElement signupLoginLink;






}