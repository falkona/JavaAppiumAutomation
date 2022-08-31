package tests;

import driver.DriverManager;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public abstract class CoreTestCase {

    protected AppiumDriver driver;

    protected void setUp() {
        DriverManager driverManager = DriverManager.getInstance();
        try {
            String platform = driverManager.getPlatform();
            DesiredCapabilities capabilities = driverManager.getCapabilitiesByPlatform(platform);
            driverManager.setDriverByPlatform(platform, capabilities);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void tearDown() {
        driver.quit();
    }

}
