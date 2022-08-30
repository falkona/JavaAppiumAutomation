package tests;

import driver.DriverManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public abstract class CoreTestCase {

    protected AppiumDriver driver;

    public void setUp() throws Exception {
        DriverManager driverManager = DriverManager.getInstance();
        String platform = driverManager.getPlatform();
        DesiredCapabilities capabilities = driverManager.getCapabilitiesByPlatform(platform);
        driverManager.setDriverByPlatform(platform, capabilities);
    }

    public void tearDown() {
        driver.quit();
    }

}
