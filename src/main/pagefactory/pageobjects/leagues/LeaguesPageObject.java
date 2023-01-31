package src.main.pagefactory.pageobjects.leagues;

import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import src.main.common.BaseTest;
import src.main.pagefactory.pageobjects.reusable.ReUsable;

public class LeaguesPageObject extends BaseTest {

    public static final String LEAGUES_ICON_XPATH = "//android.widget.TextView[@text='Leagues']";
    public static final String LEAGUE_XPATH1 = "//android.widget.TextView[@text='";
    public static final String LEAGUE_XPATH2 = "']";
    public static final String DATA_ID = "com.fivemobile.thescore:id/viewPager";
    public static final String LEAGUE_SUB_TAB_XPATH1 = "//android.widget.TextView[@text='";
    public static final String LEAGUE_SUB_TAB_XPATH2 = "']";

    public void verifyLeaguesPage(String leagueName, String subTab) {
        try {
            driver.findElement(By.xpath(LEAGUES_ICON_XPATH)).click();
            System.out.println("Clicked on Leagues button successfully");
            driver.findElement(By.xpath(LEAGUE_XPATH1 + leagueName + LEAGUE_XPATH2)).click();
            System.out.println("Clicked on " + leagueName + " successfully");
            driver.findElement(By.id(DATA_ID)).isDisplayed();
            System.out.println("The data for " + leagueName + " is displayed");
            driver.findElement(By.xpath(LEAGUE_SUB_TAB_XPATH1 + subTab + LEAGUE_SUB_TAB_XPATH2)).click();
            System.out.println("Clicked on " + subTab + " successfully");
            driver.findElement(By.id(DATA_ID)).isDisplayed();
            System.out.println("The data for " + subTab + " is displayed");
            getTest().log(LogStatus.PASS,"Leagues page data is verified successfully");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error occurred while verifying Leagues data..");
            getTest().log(LogStatus.FAIL,"Leagues page data is verified successfully");
        }
    }
}


