package src.main.pagefactory.pageobjects.accounts;

import org.openqa.selenium.By;
import src.main.common.BaseTest;
import src.main.pagefactory.pageobjects.reusable.ReUsable;

public class SettingsPageObject extends BaseTest {

    public static final String LOGOUT_BUTTON_XPATH = "//*[@text='Log Out']";
    public static final String PROFILE_IMAGE_XPATH = "//android.widget.ImageButton[@package='com.fivemobile.thescore'][@index='0']";
    public static final String SETTINGS_SECTION_XPATH = "//*[@text='Settings']";
    public static final String LOGOUT_POPUP_BUTTON_XPATH = "//*[@text='LOG OUT']";

    public void logout() {
        try {
            driver.findElement(By.xpath(PROFILE_IMAGE_XPATH)).click();
            ReUsable.swipeScreen(ReUsable.Direction.UP);
            driver.findElement(By.xpath(SETTINGS_SECTION_XPATH)).click();
            driver.findElement(By.xpath(LOGOUT_BUTTON_XPATH)).click();
            driver.findElement(By.xpath(LOGOUT_POPUP_BUTTON_XPATH)).click();
            System.out.println("User is logged out from the device successfully");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error occurred while logout from the app..");
        }
    }
}
