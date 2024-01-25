package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class FlowniqPage {

    public FlowniqPage() {

        PageFactory.initElements(Driver.getDriver(), this);

    }

    /*
     * ==========================================
     *   Login Page
     * ==========================================
     */

    //Login Page → key cloak card → form → Username input
    @FindBy(xpath = "//div[@class='keycloak-card']//input[@id='username']")
    public WebElement loginPageKcformUsernameInput;

    //Login Page → key cloak card → form → password input
    @FindBy(xpath = "//div[@class='keycloak-card']//input[@id='password']")
    public WebElement loginPageKcformPasswordInput;

    //Login Page → key cloak card → form → SignIn Button
    @FindBy(xpath = "//div[@id='kc-form']//button[@type='submit']")
    public WebElement loginPageKcformSubmitButton;



}