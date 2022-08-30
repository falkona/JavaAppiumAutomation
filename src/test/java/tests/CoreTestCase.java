package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public abstract class CoreTestCase {

    private static final String ANDROID_PLATFORM = "Android";
    private static final String IOS_PLATFORM = "iOS";

    protected AppiumDriver driver;

    public void setUp() throws Exception {
        String platform = getPlatform();
        DesiredCapabilities capabilities = getCapabilitiesByPlatform(platform);
        setDriverByPlatform(platform, capabilities);
    }

    public void tearDown() {
        driver.quit();
    }

    private DesiredCapabilities getCapabilitiesByPlatform(String platform) throws Exception {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (platform.equals(ANDROID_PLATFORM)) {
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("platformVersion", "9.0");
            capabilities.setCapability("deviceName", "abm_design");
            capabilities.setCapability("automationName", "Appium");
            capabilities.setCapability("appPackage", "com.vkontakte.android");
            capabilities.setCapability("appActivity", ".MainActivity");
            capabilities.setCapability("app", "/Users/darianos/Desktop/Сборки/vk.apk");
        } else if (platform.equals(IOS_PLATFORM)) {
            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("platformVersion", "14.5");
            capabilities.setCapability("deviceName", "iPhone 8");
            capabilities.setCapability("app", "");
            capabilities.setCapability("automationName", "XCUITest");
        } else {
            throw new Exception("Не найдены capabilities для платформы " + platform);
        }

        return capabilities;
    }

    private void setDriverByPlatform(String platform, DesiredCapabilities capabilities) throws Exception {
        if (platform.equals(ANDROID_PLATFORM)) {
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        } else if (platform.equals(IOS_PLATFORM)) {
            driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        } else {
            throw new Exception("Не найден драйвер для платформы " + platform);
        }
    }

    private String getPlatform() throws Exception{
        String platform = System.getenv("PLATFORM");
        if (platform.isEmpty()) {
            throw new Exception("Не задана переменная PLATFORM");
        }
        return platform;
    }

}
