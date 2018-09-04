package driver;

import java.net.MalformedURLException;
import java.net.URL;
import net.thucydides.core.webdriver.DriverSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * @author kumar on 28/08/18
 * @project X-search
 */

public class MyGridDriver implements DriverSource {

  ChromeOptions chromeOptions = new ChromeOptions();

  @Override
  public WebDriver newDriver() {
    chromeOptions.addArguments("headless");
    chromeOptions.addArguments("window-size=1200x1200");
    try {
      RemoteWebDriver remoteWebDriver = new RemoteWebDriver(
          new URL("http://127.0.0.1:4444/wd/hub"), DesiredCapabilities.chrome());
      System.out.println("Chrome-Grid");
      return remoteWebDriver;
    } catch (MalformedURLException e) {
      e.printStackTrace();
      System.out.println("Chrome");
      return new ChromeDriver(chromeOptions);
    }
  }

  @Override
  public boolean takesScreenshots() {
    return true;
  }
}
