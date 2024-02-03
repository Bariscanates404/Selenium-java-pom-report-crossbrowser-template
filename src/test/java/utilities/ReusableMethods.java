package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import io.github.sukgu.Shadow;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

public class ReusableMethods {

    static String BASE_DIR = System.getProperty("user.dir") + "\\imgs\\";
    static WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
    static Actions actions = new Actions(Driver.getDriver());



    /**
     * waitFor
     * <pre>
     *            Saniye bazinda hardwait yapar.
     * </pre>
     *
     * @param sec kac saniye beklenmesini istiyorsunuz
     * @author Baris Can Ates
     */
    public static void waitFor(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * getScreenshot
     * <pre>
     *            Gorunen ekranin ekran goruntusunu alir
     * </pre>
     *
     * @author Baris Can Ates
     */
    public static String getScreenshot(String name) throws IOException {
        // naming the screenshot with the current date to avoid duplication
        String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        // TakesScreenshot is an interface of selenium that takes the screenshot
        TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);
        // full path to the screenshot location
        String target = System.getProperty("user.dir") + "/target/Screenshots/" + name + date + ".png";
        File finalDestination = new File(target);
        // save the screenshot to the path given
        FileUtils.copyFile(source, finalDestination);
        return target;
    }

    public static void getScreenShotToWholePage(WebDriver driver,String screenshotIsmi)  {

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;

        // dosya adini dinamik yapalim
        // target/screenshots/tumSayfaSS.png
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter istenenFormat = DateTimeFormatter.ofPattern("yyMMddHHmm");
        localDateTime.format(istenenFormat); // 2310080829

        String dinamikDosyaAdi = "src/tmp/"+screenshotIsmi+
                                    localDateTime.format(istenenFormat)+".jpg";
        File tumSayfaSS = new File(dinamikDosyaAdi);
        File geciciDosya = takesScreenshot.getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(geciciDosya,tumSayfaSS);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void webelementScreenshotCek(WebElement istenenWebelement,String screenshotIsmi){

        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter istenenFormat = DateTimeFormatter.ofPattern("yyMMddHHmm");
        localDateTime.format(istenenFormat); // 2310080829

        String dinamikDosyaAdi = "src/tmp/"+screenshotIsmi+
                localDateTime.format(istenenFormat)+".jpg";
        File webelementSS = new File(dinamikDosyaAdi);

        File geciciDosya = istenenWebelement.getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(geciciDosya,webelementSS);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * switchToWindow
     * <pre>
     *             Handle winhow by title
     * </pre>
     *
     * @param targetTitle
     * @author Baris Can Ates
     */
    public static void switchToWindow(String targetTitle) {
        String origin = Driver.getDriver().getWindowHandle();
        for (String handle : Driver.getDriver().getWindowHandles()) {
            Driver.getDriver().switchTo().window(handle);
            if (Driver.getDriver().getTitle().equals(targetTitle)) {
                return;
            }
        }
        Driver.getDriver().switchTo().window(origin);
    }

    /**
     * shadowRootElementHandling
     * <pre>
     *            Shadow root icerisindeki elemanlara ulasmamizi ve uzerinlerinde islem
     *            yapmamizi saglayar
     * </pre>
     *
     * @param cssLocatorOfParentShadowRootElement ShadowHost yani ulasilmak istenen shadow
     *                                            elementin icerisinde bulundugu standart
     *                                            DOM locatorlar ile erisilebilem ilk parenti
     * @param cssLocatorOfShadowRootElement Ulasmak istedigimiz shadow root element
     * @author Baris Can Ates
     */
    public static WebElement shadowRootElementHandling(String cssLocatorOfParentShadowRootElement, String cssLocatorOfShadowRootElement) {
        Shadow shadow = new Shadow(Driver.getDriver());
        ReusableMethods.waitFor(1);
        WebElement elPar = Driver.getDriver().findElement(By.cssSelector(cssLocatorOfParentShadowRootElement));
        WebElement element = shadow.getShadowElement(elPar, cssLocatorOfShadowRootElement);
        return element;
    }

    /**
     * moveToElementAndHover
     * <pre>
     *            Mouse verilen elementin uzerine hareket eder ve hoveroveri aktif eder
     * </pre>
     *
     * @param element Hover aktif etmek istediginiz WebElement
     * @author Baris Can Ates
     */
    public static void moveToElementAndHover(WebElement element) {
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(element).perform();
    }

    /**
     * getGivenWebElementListsElementTexts
     * <pre>
     *            WebElement listesi verilen elementlerin Textlerini String
     *            Liste olarak dondurur
     * </pre>
     *
     * @param list WebElementList
     * @return String List ((((Returns given workorders elements id attribute as string))))
     * @author Baris Can Ates
     */
    public static List<String> getGivenWebElementListsElementTexts(List<WebElement> list) {
        List<String> elemTexts = new ArrayList<>();
        for (WebElement el : list) {
            if (!el.getText().isEmpty()) {
                elemTexts.add(el.getText());
            }
        }
        return elemTexts;
    }

    /**
     * Waits for a WebElement to be visible within a specified timeout.
     *
     * <p>
     * This method waits for the provided WebElement to be visible within the given timeout.
     * If the element does not become visible within the specified duration, it logs an error and
     * throws a RuntimeException.
     * </p>
     *
     * @param element The WebElement to wait for visibility.
     * @param timeout The maximum time (in seconds) to wait for the element to become visible.
     * @return WebElement The visible WebElement.
     * @throws RuntimeException If the element is not visible within the specified timeout.
     * @author Baris Can Ates
     */
    public static WebElement waitForElementsVisibility(WebElement element, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
            return wait.until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException e) {
            // Log an error and throw a RuntimeException if the element is not visible after the timeout
            String errorMessage = String.format(
                    "Element %s is not visible after %d seconds.",
                    element.toString(), timeout);
            System.err.println(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }


    /**
     * waitForAnElementsClickability
     * <pre>
     *           WebElement olarak verilen elementin tiklanabilir hale gelmesini
     *           bekler eger verilen sure icerisinde gelirse WebElement olarak doner
     * </pre>
     *
     * @param element Hedef WebElement
     * @param timeout beklemesini sitediginiz sure (saniye)
     * @return WebElement
     * @author Baris Can Ates
     */
    public static WebElement waitForAnElementsClickability(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * waitForAnElementsClickablity
     * <pre>
     *           By Locatori verilen WebElementin tiklanabilir hale gelmesini
     *           bekler eger verilen sure icerisinde gelirse WebElement olarak doner
     * </pre>
     *
     * @param locator By Locator
     * @param timeout beklemesini sitediginiz sure (saniye)
     * @return WebElement
     * @author Baris Can Ates
     */
    public static WebElement waitForClickablility(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Attempts to click a WebElement within a specified timeout.
     *
     * <p>
     * This method attempts to click the provided WebElement within the specified timeout.
     * If the click is unsuccessful during the timeout, it logs an error and throws a RuntimeException.
     * </p>
     *
     * @param element The WebElement to click.
     * @param timeout The maximum time (in seconds) to attempt clicking the element.
     * @throws RuntimeException If the element cannot be clicked within the specified timeout.
     * @author Baris Can Ates
     */
    public static void clickAnElementWithTimeOut(WebElement element, int timeout) {
        for (int i = 0; i < timeout; i++) {
            try {
                element.click();
                return;
            } catch (WebDriverException e) {
                // Log an error if clicking is unsuccessful, and retry
                System.err.println(String.format(
                        "Attempt %d: Clicking on element %s failed. Retrying...", i + 1, element.toString()));
                waitFor(1);
            }
        }
        // Log an error and throw a RuntimeException if clicking is unsuccessful after the retry attempts
        String errorMessage = String.format(
                "Clicking on element %s failed after %d attempts.", element.toString(), timeout);
        System.err.println(errorMessage);
        throw new RuntimeException(errorMessage);
    }



    /**
     * waitForPageToLoad
     * <pre>
     *            Verilen Web sayfasinin tam anlamiyla yuklendigini kontrol eder
     * </pre>
     *
     * @param timeout maksimum kac saniye icerisinde sayfanin yuklenmesini
     *                istediginizi yaziniz (saniye)
     * @author Baris Can Ates
     */
    public static void waitForPageToLoad(long timeout) {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        try {
            System.out.println("Waiting for page to load...");
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
            wait.until(expectation);
        } catch (Throwable error) {
            System.out.println(
                    "Timeout waiting for Page Load Request to complete after " + timeout + " seconds");
        }
    }

    /**
     * verifyURLorText
     * <pre>
     *            Verilen iki textin/URLnin ayni olup olmadigini kontrol eder
     * </pre>
     *
     * @param expectedURL ilk text/url
     * @param actualURL   ikinci text/url
     * @return Textler ayni ise True doner
     * @author Baris Can Ates
     */
    public static boolean verifyURLorText(String actualURL, String expectedURL) {
        boolean result = false;
        if (actualURL.equals(expectedURL)) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    /**
     * fileDownloadVerification
     * <pre>
     *            Bir  dosyanin basari ile download edilip edilmedigini kontrol eder
     * </pre>
     *
     * @param fileName kontrol etmek istediginiz dosyanin adi
     * @return String (Returns given workorders elements id attribute as string)
     * @author Baris Can Ates
     */
    public static boolean fileDownloadVerification(String fileName) {
        boolean result = false;
        String dosyaAdi = fileName;
        String farklikisim = System.getProperty("user.home");
        String ortakKisim = "\\downloads\\" + dosyaAdi;
        String arananDosyaYolu = farklikisim + ortakKisim;
        if (Files.exists(Paths.get(arananDosyaYolu))) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    /**
     * getAnWebElementsText
     * <pre>
     *            By Locatoru verilen WebElementin textini dondurur
     *
     * </pre>
     *
     * @param locator By Locator
     * @return List<String> WebElementin texti
     * @author Baris Can Ates
     */
    //========Returns the Text of the element given an element locator==//
    public static List<String> getAnWebElementsText(By locator) {
        List<WebElement> elems = Driver.getDriver().findElements(locator);
        List<String> elemTexts = new ArrayList<>();
        for (WebElement el : elems) {
            if (!el.getText().isEmpty()) {
                elemTexts.add(el.getText());
            }
        }
        return elemTexts;
    }


    /**
     * moveMauseToElement
     * <pre>
     *          Parametre olarak verilen WebElemente mouse goturur
     * </pre>
     *
     * @param WebElement WebElement
     * @author Baris Can Ates
     */
    public static void moveMauseToElement(WebElement WebElement) {
        actions.moveToElement(WebElement).perform();
        ReusableMethods.waitFor(2);

    }

    /**
     * moveMauseToCoordinates
     * <pre>
     *          Koordinatleri verilen bolgeye mouse hareket ettirir
     * </pre>
     *
     * @param x Coordinates
     * @author Baris Can Ates
     */
    public static void moveMauseToCoordinates(int x, int y) {
        actions
                .moveByOffset(x, y)
                .clickAndHold().moveByOffset(800, 200)
                .build()
                .perform();

    }


    /**
     * checkAnyElementIsActiveOrDisplayedXPATH
     * <pre>
     *         XPATH standardinda WebElementi verilen herhangi bir elementin varligini kontrol eder
     * </pre>
     *
     * @param element Xpath WebElement element ByLocator
     * @author Baris Can Ates
     */
    public static boolean checkAnyElementExistenceByXpathWebElement (WebElement element) {
        ReusableMethods.waitFor(1);

        List<WebElement> elementList = element.findElements(By.xpath("."));

        if (elementList.size() > 0) {
            // Element is present
            return true;
        } else {
            // Element is not present
            return false;
        }
    }
    /**
     * checkAnyElementIsActiveOrDisplayedXPATH
     * <pre>
     *         CSS standardinda WebElementi verilen herhangi bir elementin varligini kontrol eder
     * </pre>
     *
     * @param element WebElement element ByLocator
     * @author Baris Can Ates
     */
    public static boolean checkAnyElementExistenceByCssWebElement (WebElement element) {
        ReusableMethods.waitFor(1);

        List<WebElement> elementList = element.findElements(By.cssSelector("."));

        if (elementList.size() > 0) {
            // Element is present
            return true;
        } else {
            // Element is not present
            return false;
        }
    }

    /**
     * checkAnyElementIsActiveOrDisplayedXPATH
     * <pre>
     *         XPATH standardinda locatoru verilen herhangi bir elementin varligini kontrol eder
     * </pre>
     *
     * @param XPATHlocator String locator
     * @author Baris Can Ates
     */
    public static boolean checkAnyElementExistenceStringXPATH(String XPATHlocator) {
        ReusableMethods.waitFor(1);

        List<WebElement> elementList = Driver.getDriver().findElements(By.xpath(XPATHlocator));
        if (elementList.size() > 0) {
            // Element is present
            return true;
        } else {
            // Element is not present
            return false;
        }
    }

    /**
     * checkAnyElementIsActiveOrDisplayedCSS
     * <pre>
     *         CSS standardinda locatoru verilen herhangi bir elementin varligini kontrol eder
     * </pre>
     *
     * @param CSSlocator String locator
     * @author Baris Can Ates
     */
    public static boolean checkAnyElementIsActiveOrDisplayedCSS(String CSSlocator) {
        ReusableMethods.waitFor(1);

        List<WebElement> elementList = Driver.getDriver().findElements(By.xpath(CSSlocator));
        if (elementList.size() > 0) {
            // Element is present
            return true;
        } else {
            // Element is not present
            return false;
        }
    }



    /**
     * Scrolls the web page to the top.
     *
     * <p>
     * This method uses JavaScriptExecutor to scroll the web page to the top.
     * </p>
     *
     * @param driver The WebDriver instance to perform the scrolling.
     * @author Baris Can Ates
     */
    public static void scrollToTop(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, 0)");
    }

    /**
     * Scrolls the web page to position the given WebElement in the middle of the visible area.
     *
     * <p>
     * This method utilizes JavaScriptExecutor to calculate the target scroll position
     * based on the Y coordinate of the provided WebElement and the inner height of the window.
     * It then scrolls to the calculated position, ensuring that the WebElement is positioned
     * in the middle of the visible area of the web page.
     * </p>
     *
     * @param driver   The WebDriver instance to perform the scrolling.
     * @param element  The WebElement to be centered in the middle of the visible area.
     *
     * @author Baris Can Ates
     */
    public static void scrollToMiddle(WebDriver driver, WebElement element) {
        // Create a JavascriptExecutor instance for executing JavaScript code
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

        /* WHY NUMBER
         * We used Number instead of double or long to accommodate both integer and
         * floating-point values that may be returned by JavaScript in the executeScript
         * method, allowing for flexibility in handling different numeric types.
         */

        // Get the Y coordinate of the provided WebElement
        Number elementY = (Number) jsExecutor.executeScript("return arguments[0].getBoundingClientRect().top", element);

        // Get the inner height of the window (visible area)
        Number innerHeight = (Number) jsExecutor.executeScript("return window.innerHeight");

        // Calculate the target scroll position to bring the element to the middle
        double targetScrollPosition = elementY.doubleValue() + innerHeight.doubleValue() / 2;

        // Scroll to the target position
        jsExecutor.executeScript("window.scrollTo(0, arguments[0])", targetScrollPosition);
    }

    /**
     * Scrolls the web page to the bottom.
     *
     * <p>
     * This method uses JavaScriptExecutor to scroll the web page to the bottom.
     * </p>
     *
     * @param driver The WebDriver instance to perform the scrolling.
     * @author Baris Can Ates
     */
    public static void scrollToBottom(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    /**
     * Scrolls the web page to make the provided WebElement visible.
     *
     * <p>
     * This method uses JavaScriptExecutor to scroll the web page to make the provided WebElement visible.
     * </p>
     *
     * @param driver  The WebDriver instance to perform the scrolling.
     * @param element The WebElement to be made visible.
     * @author Baris Can Ates
     */
    public static void scrollIntoView(WebDriver driver, WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Scrolls the web page by a specified number of pixels.
     *
     * <p>
     * This method uses JavaScriptExecutor to scroll the web page by a specified number of pixels.
     * Positive values scroll down, negative values scroll up.
     * </p>
     *
     * @param driver The WebDriver instance to perform the scrolling.
     * @param pixels The number of pixels to scroll.
     * @author Baris Can Ates
     */
    public static void scrollByPixels(WebDriver driver, int pixels) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0, arguments[0])", pixels);
    }


    /**
     * Selects an option from a dropdown by its visible text.
     *
     * <p>
     * This method uses the Select class in Selenium to choose an option from the dropdown
     * based on its visible text.
     * </p>
     *
     * @param dropdownElement WebElement representing the dropdown
     * @param visibleText     The visible text of the option to be selected
     * @author Baris Can Ates
     */
    public static void selectByVisibleText(WebElement dropdownElement, String visibleText) {
        Select select = new Select(dropdownElement);
        select.selectByVisibleText(visibleText);
    }

    /**
     * Selects an option from a dropdown by its index.
     *
     * <p>
     * This method uses the Select class in Selenium to choose an option from the dropdown
     * based on its index.
     * </p>
     *
     * @param dropdownElement WebElement representing the dropdown
     * @param index           The index of the option to be selected
     * @author Baris Can Ates
     */
    public static void selectByIndex(WebElement dropdownElement, int index) {
        Select select = new Select(dropdownElement);
        select.selectByIndex(index);
    }

    /**
     * Selects an option from a dropdown by its value.
     *
     * <p>
     * This method uses the Select class in Selenium to choose an option from the dropdown
     * based on its value.
     * </p>
     *
     * @param dropdownElement WebElement representing the dropdown
     * @param value           The value of the option to be selected
     * @author Baris Can Ates
     */
    public static void selectByValue(WebElement dropdownElement, String value) {
        Select select = new Select(dropdownElement);
        select.selectByValue(value);
    }

    /**
     * Gets all options from a dropdown.
     *
     * <p>
     * This method uses the Select class in Selenium to retrieve all available options
     * from the dropdown.
     * </p>
     *
     * @param dropdownElement WebElement representing the dropdown
     * @return List of all options as Strings
     * @author Baris Can Ates
     */
    public static List<String> getAllOptions(WebElement dropdownElement) {
        Select select = new Select(dropdownElement);
        List<WebElement> optionElements = select.getOptions();
        return optionElements.stream().map(WebElement::getText).collect(Collectors.toList());
    }

}
