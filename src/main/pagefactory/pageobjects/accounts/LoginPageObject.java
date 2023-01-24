package src.main.pagefactory.pageobjects.accounts;

import org.openqa.selenium.By;
import src.main.common.BaseTest;

public class LoginPageObject extends BaseTest {
    public static final String PROFILE_IMAGE_XPATH = "//*[@class='android.widget.ImageButton'][@index='0']";
    public static final String NEWS_ID = "com.fivemobile.thescore:id/navigation_news";
    public static final String SCORE_IMAGE_ID = "com.fivemobile.thescore:id/message_icon";
    public static final String SCORE_LOGIN_ID ="com.fivemobile.thescore:id/secondary_button";
    public static final String PROFILE_LOGIN_BUTTON_ID = "com.fivemobile.thescore:id/login";
    public static final String EMAIL_TEXT_ID = "com.fivemobile.thescore:id/email_input_edittext";
    public static final String PASSWORD_TEXT_ID = "com.fivemobile.thescore:id/password_input_edittext";
    public static final String LOGIN_BUTTON_ID = "com.fivemobile.thescore:id/action_button_text";
    public static final String BACK_BUTTON_XPATH = "//*[@content-desc='Navigate up']";

    public void loginToApp(String userName, String password) {
        try {
            driver.findElement(By.id(NEWS_ID)).click();
            driver.findElement(By.id(SCORE_IMAGE_ID)).click();
            driver.findElement(By.id(SCORE_LOGIN_ID)).click();
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
