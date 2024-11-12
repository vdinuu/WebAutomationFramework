package base;

import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import utils.Constants;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.*;

public class UIActions {
    public static Properties prop;
    public static HashMap<String, Object> data = new HashMap<>();

    public UIActions(){
        prop = new Properties();
        try {
            FileInputStream input = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/config/config.properties");
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void enterText(WebElement element, String textToEnter){
        waitForElementClickable(element);
        element.sendKeys(textToEnter);
    }

    public static void waitForElementVisible(WebElement element, int waitTime){
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(waitTime));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForElementVisible(WebElement element){
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(Constants.mediumWaitInSeconds));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForElementClickable(WebElement element){
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(Constants.smallWaitInSeconds));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForElementClickable(By locator){
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(Constants.smallWaitInSeconds));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    public static void click(WebElement element){
        waitForElementClickable(element);
        element.click();
    }
    public static void click(By locator){
        waitForElementClickable(locator);
        findElement(locator).click();
    }

    public static String getText(WebElement element){
        waitForElementVisible(element);
        return element.getText().trim();
    }
    public static String getText(By locator){
        waitForElementVisible(locator, Constants.smallWaitInSeconds);
        return findElement(locator).getText().trim();
    }
    public static String getFormattedText(String text, int beginIndex){
        String formattedText= text.replace("\n", ".").replace(",", "");
        if(!formattedText.isBlank() && !formattedText.isEmpty()){
            return formattedText.substring(beginIndex);
        }
        return formattedText;
    }
    public static boolean isDisplayed(WebElement element){
        boolean flag= false;
        try {
            if(element.isDisplayed()){
                flag = true;
            }
        } catch (Exception ignored) {
        }
        return flag;
    }

    public static void waitForElementToDisappear(WebElement element){
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(Constants.smallWaitInSeconds));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public static void selectDropdownByVisibleText(WebElement element, String textToSelect){
        Select select = new Select(element);
        select.selectByVisibleText(textToSelect);
    }

    public static void selectDropdownByIndex(WebElement element, int index){
        Select select = new Select(element);
        select.selectByIndex(index);
    }

    public static void selectDropdownByValue(WebElement element, String value){
        Select select = new Select(element);
        select.selectByValue(value);
    }

    public static void selectCheckBox(WebElement element){
        if(!element.isSelected()){
            click(element);
        }
    }

    public static void acceptAlert(){
        Alert alert = DriverFactory.getDriver().switchTo().alert();
        alert.accept();
    }

    public static void dismissAlert(){
        Alert alert = DriverFactory.getDriver().switchTo().alert();
        alert.dismiss();
    }
    public static void fluentWaitUntilVisible(WebElement element){
        Wait<WebDriver> wait = new FluentWait<WebDriver>(DriverFactory.getDriver()).
                withTimeout(Duration.ofSeconds(10)).
                pollingEvery(Duration.ofMillis(500)).
                ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    //Drag and drop
    public static void dragAndDrop(WebElement source, WebElement destination){
        Actions actions = new Actions(DriverFactory.getDriver());
        actions.dragAndDrop(source, destination).build().perform();
    }
    public static void switchToFrameByIndex(int index){
        DriverFactory.getDriver().switchTo().frame(index);
    }
    public static void switchToFrameByIndex(WebElement element){
        DriverFactory.getDriver().switchTo().frame(element);
    }
    public static void switchToDefaultContent(){
        DriverFactory.getDriver().switchTo().defaultContent();
    }

    public static byte[] takeScreenshot(){
        TakesScreenshot screenshot = (TakesScreenshot) DriverFactory.getDriver();
        byte[] src = screenshot.getScreenshotAs(OutputType.BYTES);
        return src;
    }
    public static WebElement findElement(By locator){
        return DriverFactory.getDriver().findElement(locator);
    }

    public static List<WebElement> findElements(By locator){
        return DriverFactory.getDriver().findElements(locator);
    }
    public static void scrollToElement(WebElement element){
        Actions actions = new Actions(DriverFactory.getDriver());
        actions.scrollToElement(element).build().perform();
    }
    public static void switchToNewTab(String parentHandle){
        Set<String> handles = DriverFactory.getDriver().getWindowHandles();
        for(String handle: handles){
            if(!handle.equals(parentHandle)){
                DriverFactory.getDriver().switchTo().window(handle);
            }
        }
    }
    public static void performKeyAction(Keys keyAction){
        Actions actions = new Actions(DriverFactory.getDriver());
        actions.sendKeys(keyAction).build().perform();
    }

    public static void staticWait(long milliSeconds){
        try {
            Thread.sleep(milliSeconds);
        } catch (InterruptedException ignored) {
        }
    }

    public static void scrollToElementUsingJs(WebElement element){
        ((JavascriptExecutor) DriverFactory.getDriver()).executeScript("arguments[0].scrollIntoView();", element);
    }

    public static void scrollByCoordinatesJS(int x){
        JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
        js.executeScript("window.scrollBy(0, "+x+")");
    }
    public static void selectDynamicDropDown(List<WebElement> elements, String dropdownOption){
        Optional<WebElement> dropdown = elements.stream().filter(e -> e.getText().trim().equals(dropdownOption)).findFirst();
        if(dropdown.isPresent()){
            click(dropdown.get());
        } else{
            System.out.println(dropdownOption+" is not found in dropdown list");
        }
    }
    public static void waitForElementVisible(By locator, int waitTime){
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(waitTime));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public static void waitForElementsToLoad(By locator, int waitTime, int numberOfElements){
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(waitTime));
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(locator, numberOfElements));
    }
    public static void clearField(By locator){
        findElement(locator).clear();
    }
    public static void clearField(WebElement element){
        element.clear();
    }

}
