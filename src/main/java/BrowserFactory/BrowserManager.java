package BrowserFactory;

import org.openqa.selenium.WebDriver;

public interface BrowserManager {
    WebDriver createDriver(boolean headless);
}
