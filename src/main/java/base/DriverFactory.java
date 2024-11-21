package base;

import BrowserFactory.BrowserManager;
import BrowserFactory.ChromeDriverManager;
import BrowserFactory.EdgeDriverManager;
import BrowserFactory.FirefoxDriverManager;
import org.openqa.selenium.WebDriver;
import utils.Constants;

import java.time.Duration;

import static base.UIActions.prop;

public class DriverFactory {
    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    public static void initializeDriver(String browserName, String url, boolean headless) {
        BrowserManager browser = switch (browserName.toLowerCase()) {
            case "chrome" -> new ChromeDriverManager();
            case "firefox" -> new FirefoxDriverManager();
            case "edge" -> new EdgeDriverManager();
            default -> throw new IllegalArgumentException("Wrong browser given : " + browserName);
        };
        tlDriver.set(browser.createDriver(prop.getProperty("executionEnv"), headless));
        getDriver().manage().deleteAllCookies();
        getDriver().manage().window().maximize();
        getDriver().get(url);
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Constants.mediumWaitInSeconds));
    }

    public static synchronized WebDriver getDriver() {
        return tlDriver.get();
    }
}
