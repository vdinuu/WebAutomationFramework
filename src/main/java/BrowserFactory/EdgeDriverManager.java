package BrowserFactory;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.Constants;

import java.net.MalformedURLException;
import java.net.URL;

public class EdgeDriverManager implements BrowserManager{
    @Override
    public WebDriver createDriver(String env, boolean headless) {
        WebDriver driver = null;
        EdgeOptions edgeOptions = new EdgeOptions();
        if (headless) {
            edgeOptions.addArguments("--headless=new");
        }
        if(env.equalsIgnoreCase("local")) {
            driver = new EdgeDriver(edgeOptions);
        } else if(env.equalsIgnoreCase("remote")){
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setPlatform(Platform.WIN11);
            capabilities.setBrowserName("MicrosoftEdge");
//            capabilities.setCapability("edgeOptions", edgeOptions);
            edgeOptions.merge(capabilities);
            try {
                driver = new RemoteWebDriver(new URL(Constants.HUB_URL), capabilities);
            } catch (MalformedURLException ignored) {
            }
        }
        return driver;
    }
}
