package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
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
     * waitForElementsVisibility
     * <pre>
     *            Bir WebElementin gorunur olmasini verilen sure kadar bekler
     *            eger verilen sure icerisinde gorunur olursa WebElementi dondurur
     * </pre>
     *
     * @param element Hedef WebElement
     * @param timeout beklemesini sitediginiz sure (saniye)
     * @return WebElement
     * @author Baris Can Ates
     */
    public static WebElement waitForElementsVisibility(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOf(element));
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
     * clickAnElementWithTimeOut
     * <pre>
     *           WebElement olarak verilen elemente ozel bir zaman araliginda
     *           saniyede bir kere tiklamayi dener
     * </pre>
     *
     * @param element Hedef WebElement
     * @param timeout Tiklanmasini istediginiz sure (saniye)
     * @author Baris Can Ates
     */
    public static void clickAnElementWithTimeOut(WebElement element, int timeout) {
        for (int i = 0; i < timeout; i++) {
            try {
                element.click();
                return;
            } catch (WebDriverException e) {
                waitFor(1);
            }
        }
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
     *         XPATH standardinda locatoru verilen herhangi bir elementin varligini,
     *         gorunurlugunu kontrol eder
     * </pre>
     *
     * @param XPATHlocator String locator
     * @author Baris Can Ates
     */
    public static boolean checkAnyElementIsActiveOrDisplayedXPATH(String XPATHlocator) {
        ReusableMethods.waitFor(1);

        List<WebElement> elementList = Driver.getDriver().findElements(By.xpath(XPATHlocator));
        System.out.println(elementList.size());
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
     *         CSS standardinda locatoru verilen herhangi bir elementin varligini,
     *         gorunurlugunu kontrol eder
     * </pre>
     *
     * @param CSSlocator String locator
     * @author Baris Can Ates
     */
    public static boolean checkAnyElementIsActiveOrDisplayedCSS(String CSSlocator) {
        ReusableMethods.waitFor(1);

        List<WebElement> elementList = Driver.getDriver().findElements(By.xpath(CSSlocator));
        System.out.println(elementList.size());
        if (elementList.size() > 0) {
            // Element is present
            return true;
        } else {
            // Element is not present
            return false;
        }
    }



}
