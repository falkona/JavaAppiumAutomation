package ui.locator;

public class Locator {

    private IOSBy iOSBy;
    private AndroidBy androidBy;

    public Locator(IOSBy iOSBy, AndroidBy androidBy) {
        this.iOSBy = iOSBy;
        this.androidBy = androidBy;
    }

    public Locator(AndroidBy androidBy) {
        this.androidBy = androidBy;
    }

    public Locator(IOSBy iOSBy) {
        this.iOSBy = iOSBy;
    }

    public IOSBy getiOSBy() {
        return iOSBy;
    }

    public AndroidBy getAndroidBy() {
        return androidBy;
    }

    public void setiOSBy(IOSBy iOSBy) {
        this.iOSBy = iOSBy;
    }

}
