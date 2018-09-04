package Driver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author kumar on 28/08/18
 * @project X-search
 */

public class MyMobileDriver{

  public AndroidDriver mobileDriver() throws MalformedURLException {

    AppiumDriverLocalService appiumService;
    appiumService = AppiumDriverLocalService.buildDefaultService();
    appiumService.start();
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
    capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.0.0");
    capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "4000");
    capabilities.setCapability(MobileCapabilityType.BROWSER_NAME,"Chrome");
    capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"mi a1");
    capabilities.setCapability("chromedriverExecutable","chromedriver");
    return new AndroidDriver(new URL(appiumService.getUrl().toString()), capabilities);

  }

  public static void main(String[] args) {
    MyMobileDriver myMobileDriver = new MyMobileDriver();
    try {
      WebDriver driver =  myMobileDriver.mobileDriver();
      driver.get("https://www.google.com");
      System.out.println("-----Current URL---"+driver.getCurrentUrl());
      driver.quit();
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
  }
}
