package driver;

import net.thucydides.core.webdriver.DriverSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * @author kumar on 28/08/18
 * @project X-search
 */

public class MyChromeDriver implements DriverSource {


  @Override
  public WebDriver newDriver() {
    ChromeOptions chromeOptions = new ChromeOptions();
    //chromeOptions.addArguments("headless");
    chromeOptions.addArguments("window-size=1200x1200");
    return  new ChromeDriver(chromeOptions);
  }

  @Override
  public boolean takesScreenshots() {
    return true;
  }

}
