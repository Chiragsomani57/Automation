package ipcamera.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class usermanagementpageobject {

    WebDriver driver;

    public usermanagementpageobject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // ═══════════════════════════════════════════════
    // NAVIGATION
    // ═══════════════════════════════════════════════
    @FindBy(id = "configuration")      WebElement configuration;
    @FindBy(id = "usseraccounts")      WebElement useraccounttab;  // ⚠️ verify id
    @FindBy(id = "useracc")            WebElement useracclink;
    @FindBy(id = "globalpolicy")       WebElement globalpolicylink;

    // ═══════════════════════════════════════════════
    // USER ACCOUNT — FORM FIELDS
    // ═══════════════════════════════════════════════
    @FindBy(css = "#enable")           WebElement userenable;
    @FindBy(css = "#user-name")        WebElement username;

    // ─── User Type radio buttons ──────────────────
    @FindBy(css = "#usertype2")        WebElement usertypeadmin;
    @FindBy(css = "#usertype1")        WebElement usertypeoperator;
    @FindBy(css = "#usertype0")        WebElement usertypeviewer;

    // ─── Access Rights checkboxes ─────────────────
    @FindBy(css = "#basic-settings")   WebElement basicsettings;
    @FindBy(css = "#advanced-settings")WebElement advancedsettings;
    @FindBy(css = "#network-settings") WebElement networksettings;
    @FindBy(css = "#user-account")     WebElement useraccount;
    @FindBy(css = "#event")            WebElement event;
    @FindBy(css = "#storage")          WebElement storagemanagement;
    @FindBy(css = "#log")              WebElement log;
    @FindBy(css = "#system")           WebElement systemmaintenance;
    @FindBy(css = "#playback")         WebElement playback;
    @FindBy(css = "#globalpolicy")     WebElement globalpolicy;
    @FindBy(css = "#language-settings")WebElement languagesettings;

    // ─── User Table Links ─────────────────────────
    @FindBy(css = "#user-name0")       WebElement usernameadmin;
    @FindBy(css = "#user-name1")       WebElement usernameoperator;
    @FindBy(css = "#user-name2")       WebElement usernameviewer;
    @FindBy(css = "#user-type0")       WebElement usertypeadminlink;
    @FindBy(css = "#user-type1")       WebElement usertypeoperatorlink;
    @FindBy(css = "#user-type2")       WebElement usertypeviewerlink;

    // ═══════════════════════════════════════════════
    // GLOBAL POLICY — FORM FIELDS
    // ═══════════════════════════════════════════════
    @FindBy(css = "#min-char")         WebElement minchars;
    @FindBy(css = "#policy-strength")  WebElement passwordstrength;
    @FindBy(css = "#pwdvaldt")         WebElement setpasswordvalidity;
    @FindBy(css = "#reset-timer")      WebElement resettimer;
    @FindBy(css = "#pwd-attempt")      WebElement lockafterattempts;
    @FindBy(css = "#attempt-cnt")      WebElement maxattempts;
    @FindBy(css = "#unlock-timer")     WebElement autounlock;

    // ═══════════════════════════════════════════════
    // BUTTONS
    // ═══════════════════════════════════════════════
    @FindBy(id = "btndefault")         WebElement btndefault;
    @FindBy(id = "btnsave")            WebElement btnsave;
    @FindBy(id = "btncancel")          WebElement btncancel;
    @FindBy(id = "btnyes")             WebElement btnyes;
    @FindBy(id = "btndelete")          WebElement btndelete;     // ⚠️ verify id
       // ⚠️ verify id

    // ═══════════════════════════════════════════════
    // FRAME SWITCHING
    // ═══════════════════════════════════════════════
    public void switchtopanelframe() {
        WebElement panelframe = driver.findElement(By.id("panel-frame"));
        driver.switchTo().frame(panelframe);
    }

    public void switchtoconfigframe() {
        WebElement configframe = driver.findElement(By.id("config-frame"));
        driver.switchTo().frame(configframe);
    }

    public void switchtoparentframe() {
        driver.switchTo().parentFrame();
    }

    // ═══════════════════════════════════════════════
    // HELPERS
    // ═══════════════════════════════════════════════
    public void clickoutside() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.activeElement.blur();");
    }

    public void settextbox(WebElement element, String value) {
        element.click();
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);
        element.sendKeys(value);
        clickoutside();
    }

    public void setcheckbox(WebElement element, boolean enable) {
        if (enable  && !element.isSelected()) element.click();
        if (!enable && element.isSelected())  element.click();
    }

    // ═══════════════════════════════════════════════
    // NAVIGATION METHODS
    // ═══════════════════════════════════════════════
    public void clickconfiguration()    { configuration.click();    }
    public void clickuseraccounttab()   { useraccounttab.click();   }
    public void clickuseracclink()      { useracclink.click();      }
    public void clickglobalpolicylink() { globalpolicylink.click(); }

    // ═══════════════════════════════════════════════
    // USER TABLE — click by row index
    // ═══════════════════════════════════════════════
    public void clickuserrow(int index) {
        driver.findElement(By.id("userNameId" + (index - 1))).click();
    }

    public String getusernamebyrow(int index) {
        return driver.findElement(By.id("user-name" + (index - 1))).getText();
    }

    public String getusertypebyrow(int index) {
        return driver.findElement(By.id("user-type" + (index - 1))).getText();
    }

    // ═══════════════════════════════════════════════
    // USER ENABLE
    // ═══════════════════════════════════════════════
    public void enableuser()             { setcheckbox(userenable, true);  }
    public void disableuser()            { setcheckbox(userenable, false); }
    public boolean isuserenabled()       { return userenable.isSelected(); }

    // ═══════════════════════════════════════════════
    // USERNAME
    // ═══════════════════════════════════════════════
    public void setusername(String value){ settextbox(username, value); }
    public String getusername()          { return username.getAttribute("value"); }






    // ═══════════════════════════════════════════════
    // USER TYPE — radio buttons
    // ═══════════════════════════════════════════════
    public void setusertype(String value) {
        switch (value.toLowerCase()) {
            case "admin":    if (!usertypeadmin.isSelected())    usertypeadmin.click();    break;
            case "operator": if (!usertypeoperator.isSelected()) usertypeoperator.click(); break;
            case "viewer":   if (!usertypeviewer.isSelected())   usertypeviewer.click();   break;
        }
    }

    public String getusertype() {
        if (usertypeadmin.isSelected())    return "admin";
        if (usertypeoperator.isSelected()) return "operator";
        if (usertypeviewer.isSelected())   return "viewer";
        return "";
    }

    // ═══════════════════════════════════════════════
    // ACCESS RIGHTS — getters
    // ═══════════════════════════════════════════════
    public boolean isbasicsettings()     { return basicsettings.isSelected();    }
    public boolean isadvancedsettings()  { return advancedsettings.isSelected(); }
    public boolean isnetworksettings()   { return networksettings.isSelected();  }
    public boolean isuseraccount()       { return useraccount.isSelected();      }
    public boolean isevent()             { return event.isSelected();             }
    public boolean isstorage()           { return storagemanagement.isSelected();}
    public boolean islog()               { return log.isSelected();               }
    public boolean issystem()            { return systemmaintenance.isSelected(); }
    public boolean isplayback()          { return playback.isSelected();          }
    public boolean isglobalpolicy()      { return globalpolicy.isSelected();      }
    public boolean islanguagesettings()  { return languagesettings.isSelected();  }

    // ═══════════════════════════════════════════════
    // GLOBAL POLICY — setters / getters
    // ═══════════════════════════════════════════════
    public void setminchars(String value)   { settextbox(minchars, value); }
    public String getminchars()             { return minchars.getAttribute("value"); }

    public void setpasswordstrength(String value) {
        new Select(passwordstrength).selectByVisibleText(value);
    }
    public String getpasswordstrength() {
        return new Select(passwordstrength).getFirstSelectedOption().getText();
    }

    public void enablepasswordvalidity()    { setcheckbox(setpasswordvalidity, true);  }
    public void disablepasswordvalidity()   { setcheckbox(setpasswordvalidity, false); }
    public boolean ispasswordvalidityenabled() { return setpasswordvalidity.isSelected(); }

    public void setresettimer(String value) { settextbox(resettimer, value); }
    public String getresettimer()           { return resettimer.getAttribute("value"); }

    public void enablelockafterattempts()   { setcheckbox(lockafterattempts, true);  }
    public void disablelockafterattempts()  { setcheckbox(lockafterattempts, false); }
    public boolean islockafterattemptsenabledabled() { return lockafterattempts.isSelected(); }

    public void setmaxattempts(String value){ settextbox(maxattempts, value); }
    public String getmaxattempts()          { return maxattempts.getAttribute("value"); }

    public void setautounlock(String value) { settextbox(autounlock, value); }
    public String getautounlock()           { return autounlock.getAttribute("value"); }

    // ═══════════════════════════════════════════════
    // BUTTONS
    // ═══════════════════════════════════════════════
    public void clickdefault()  { btndefault.click(); }
    public void clicksave()     { btnsave.click();    }
    public void clickcancel()   { btncancel.click();  }
    public void clickyes()      { btnyes.click();     }
    public void clickdelete()   { btndelete.click();  }

    public void clickdefaultandconfirm() {
        clickdefault();
        switchtoparentframe();
        clickyes();
        switchtoconfigframe();
    }

    public void clickdeleteandconfirm() {
        clickdelete();
        switchtoparentframe();
        clickyes();
        switchtoconfigframe();
    }
}
