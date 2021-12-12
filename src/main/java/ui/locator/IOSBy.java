package ui.locator;

import org.openqa.selenium.By;

public class IOSBy {

    private By by;

    public IOSBy(By value) {
        this.by = value;
    }

    public By getBy() {
        return by;
    }

    public void setBy(By by) {
        this.by = by;
    }

}
