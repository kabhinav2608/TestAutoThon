package driver;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class MyMobileDriver {

    private DesiredCapabilities capabilities = new DesiredCapabilities();
    public AndroidDriver androidDriver;

    public MobileDriver newMobileDriver() throws MalformedURLException {
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.0.0");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Xiaomi Mi1");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
        String serverUrl = "http://0.0.0.0:4723/wd/hub";
        androidDriver = new AndroidDriver(new URL(serverUrl), capabilities);
        return androidDriver;
    }
}
