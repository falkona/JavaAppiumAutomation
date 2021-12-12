package ui.locator;

import org.openqa.selenium.By;

public class AndroidBy {

    private By by;

    public AndroidBy(By value) {
        this.by = value;
    }

    public By getBy() {
        return by;
    }

    public void setBy(By by) {
        this.by = by;
    }

}
