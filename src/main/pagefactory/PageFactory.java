package src.main.pagefactory;

import src.main.pagefactory.pageobjects.accounts.LoginPageObject;
import src.main.pagefactory.pageobjects.leagues.LeaguesPageObject;
import src.main.pagefactory.pageobjects.reusable.ReUsable;
import src.main.pagefactory.pageobjects.accounts.SettingsPageObject;

public class PageFactory {

    private final LoginPageObject loginPageObject;
    private final LeaguesPageObject leaguesPageObject;
    private final SettingsPageObject settingsPageObject;
    private final ReUsable reUsable;

    public PageFactory() {
        leaguesPageObject = new LeaguesPageObject();
        loginPageObject = new LoginPageObject();
        settingsPageObject = new SettingsPageObject();
        reUsable = new ReUsable();
    }

    public LoginPageObject getLoginPageObject() {
        return loginPageObject;
    }

    public LeaguesPageObject getLeaguesPageObject() {
        return leaguesPageObject;
    }

    public SettingsPageObject getSettingsPageObject() {
        return settingsPageObject;
    }

    public ReUsable getReUsable() {
        return reUsable;
    }
}
