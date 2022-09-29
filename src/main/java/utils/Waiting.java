package utils;

import driver.DriverManager;
import lombok.experimental.UtilityClass;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

@UtilityClass
public class Waiting {

    public static <T> T until(ExpectedCondition<T> isTrue, long timeOutInSeconds) {
        return new WebDriverWait(DriverManager.getInstance().getDriver(), timeOutInSeconds)
                .until(isTrue);
    }

}
