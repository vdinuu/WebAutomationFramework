package BrowserFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverManager implements BrowserManager{
    @Override
    public WebDriver createDriver(boolean headless) {
        ChromeOptions options = new ChromeOptions();
        if(headless) {
            options.addArguments("--headless=new");
        }
        return new ChromeDriver(options);
    }
}
