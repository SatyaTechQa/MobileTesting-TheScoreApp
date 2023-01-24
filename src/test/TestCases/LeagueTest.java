package src.test.TestCases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import src.main.common.BaseTest;
import src.main.pagefactory.PageFactory;

public class LeagueTest extends BaseTest {

    @Parameters({"UserName", "Password"})
    @Test
    public void verifyLeagueTest(String userName, String password) {
        PageFactory pageFactory = new PageFactory();
        pageFactory.getLoginPageObject().loginToApp(userName, password);
        pageFactory.getLeaguesPageObject().verifyLeaguesPage("NHL", "STANDINGS");
        pageFactory.getReUsable().navigateBack();
        pageFactory.getReUsable().verifyLeaguesPage();
        pageFactory.getSettingsPageObject().logout();
    }
}
