package src.main.pagefactory.pageobjects.accounts;

import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.By;
import src.main.common.BaseTest;

public class LoginPageObject extends BaseTest {

    public static final String PROFILE_IMAGE_XPATH = "//*[@resource-id='com.fivemobile.thescore:id/toolbar']/*[@class='android.widget.ImageButton']";
    public static final String PROFILE_LOGIN_BUTTON_ID = "com.fivemobile.thescore:id/login";
    public static final String EMAIL_TEXT_ID = "com.fivemobile.thescore:id/email_input_edittext";
    public static final String PASSWORD_TEXT_ID = "com.fivemobile.thescore:id/password_input_edittext";
    public static final String LOGIN_BUTTON_ID = "com.fivemobile.thescore:id/action_button_text";
    public static final String BACK_BUTTON_XPATH = "//*[@content-desc='Navigate up']";

    public void loginToApp(String userName, String password) {
        try {
            if (driver.findElement(By.xpath(BACK_BUTTON_XPATH)) != null)
                driver.findElement(By.xpath(BACK_BUTTON_XPATH)).click();
            driver.findElement(By.xpath(PROFILE_IMAGE_XPATH)).click();
            driver.findElement(By.id(PROFILE_LOGIN_BUTTON_ID)).click();
            driver.findElement(By.id(EMAIL_TEXT_ID)).sendKeys(userName);
            driver.findElement(By.id(PASSWORD_TEXT_ID)).sendKeys(password);
            driver.findElement(By.id(LOGIN_BUTTON_ID)).click();
            System.out.println("User has been logged in successfully");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error occurred while login to app..");
        }
    }
}
