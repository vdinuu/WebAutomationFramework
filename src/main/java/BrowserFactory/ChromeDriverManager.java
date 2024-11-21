package BrowserFactory;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.Constants;

import java.net.MalformedURLException;
import java.net.URL;

public class ChromeDriverManager implements BrowserManager{
    @Override
    public WebDriver createDriver(String env, boolean headless) {
        WebDriver driver = null;
        ChromeOptions options = new ChromeOptions();
        if (headless) {
            options.addArguments("--headless=new");
        }
        if(env.equalsIgnoreCase("local")) {
            driver = new ChromeDriver(options);
        } else if(env.equalsIgnoreCase("remote")){
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setPlatform(Platform.WIN11);
            capabilities.setBrowserName("chrome");
            options.merge(capabilities);
//            capabilities.setCapability("chromeOptions", options);

            try {
                driver = new RemoteWebDriver(new URL(Constants.HUB_URL), capabilities);
            } catch (MalformedURLException ignored) {
            }
        }
        return driver;
    }
}
