package BrowserFactory;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.Constants;

import java.net.MalformedURLException;
import java.net.URL;

public class FirefoxDriverManager implements BrowserManager{
    @Override
    public WebDriver createDriver(String env, boolean headless) {
        WebDriver driver = null;
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        if (headless) {
            firefoxOptions.addArguments("--headless=new");
        }
        if(env.equalsIgnoreCase("local")) {
            driver = new FirefoxDriver(firefoxOptions);
        } else if(env.equalsIgnoreCase("remote")){
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setPlatform(Platform.WIN11);
            capabilities.setBrowserName("firefox");
//            capabilities.setCapability("firefoxOptions", firefoxOptions);
            firefoxOptions.merge(capabilities);
            try {
                driver = new RemoteWebDriver(new URL(Constants.HUB_URL), capabilities);
            } catch (MalformedURLException ignored) {
            }
        }
        return driver;
    }
}
