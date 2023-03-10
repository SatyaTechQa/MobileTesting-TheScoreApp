package src.main.pagefactory.pageobjects.reusable;

import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import src.main.common.BaseTest;
import java.time.Duration;

public class ReUsable extends BaseTest {

    public static final String BACK_BUTTON_XPATH = "//*[@content-desc='Navigate up']";
    public static final String LEAGUES_TEXT_XPATH = "//*[@text='Leagues']";

    public void navigateBack() {
        try {
            driver.findElement(By.xpath(BACK_BUTTON_XPATH)).click();
            System.out.println("Clicked on Back Button Successfully");
            getTest().log(LogStatus.PASS,"Clicked on Back button successfully");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error occurred while clicking back button..");
            getTest().log(LogStatus.FAIL,"Clicked on Back button successfully");
        }
    }

    public void verifyLeaguesPage(){
        try{
            driver.findElement(By.xpath(LEAGUES_TEXT_XPATH)).isDisplayed();
            System.out.println("Leagues page is verified successfully");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error occurred while verifying Leagues page..");
        }
    }


    public static void swipeScreen(Direction dir) {
        System.out.println("swipeScreen(): dir: '" + dir + "'"); // always log your actions

        // Animation default time:
        //  - Android: 300 ms
        //  - iOS: 200 ms
        // final value depends on your app and could be greater
        final int ANIMATION_TIME = 200; // ms
        final int PRESS_TIME = 200; // ms
        int edgeBorder = 10; // better avoid edges
        PointOption pointOptionStart, pointOptionEnd;
        // init screen variables
        Dimension dims = driver.manage().window().getSize();
        // init start point = center of screen
        pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);
        switch (dir) {
            case DOWN: // center of footer
                pointOptionEnd = PointOption.point(dims.width / 2, dims.height - edgeBorder);
                break;
            case UP: // center of header
                pointOptionEnd = PointOption.point(dims.width / 2, edgeBorder);
                break;
            case LEFT: // center of left side
                pointOptionEnd = PointOption.point(edgeBorder, dims.height / 2);
                break;
            case RIGHT: // center of right side
                pointOptionEnd = PointOption.point(dims.width - edgeBorder, dims.height / 2);
                break;
            default:
                throw new IllegalArgumentException("swipeScreen(): dir: '" + dir + "' NOT supported");
        }
        try {
            new TouchAction((PerformsTouchActions) driver)
                    .press(pointOptionStart)
                    // a bit more reliable when we add small wait
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                    .moveTo(pointOptionEnd)
                    .release().perform();
        } catch (Exception e) {
            System.err.println("swipeScreen(): TouchAction FAILED\n" + e.getMessage());
            return;
        }
        try {
            Thread.sleep(ANIMATION_TIME);
        } catch (InterruptedException e) {
            // ignore
        }
    }

    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT;
    }
}
