package src.main.pagefactory.pageobjects.reusable;

import org.openqa.selenium.By;
import src.main.common.BaseTest;

public class ReUsable extends BaseTest {

    public static final String BACK_BUTTON_XPATH = "//*[@content-desc='Navigate up']";
    public static final String LEAGUES_TEXT_XPATH = "//*[@text='Leagues']";

    public void navigateBack() {
        try {
            driver.findElement(By.xpath(BACK_BUTTON_XPATH)).click();
            System.out.println("Clicked on Back Button Successfully");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error occurred while clicking back button..");
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

}
