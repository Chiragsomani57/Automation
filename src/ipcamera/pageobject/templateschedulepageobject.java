package ipcamera.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class templateschedulepageobject {

    WebDriver driver;

    public templateschedulepageobject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // ─── Navigation ───────────────────────────────
    @FindBy(id = "configuration")               WebElement configuration;
    @FindBy(id = "imagesetting")                WebElement imagesetting;   // ⚠️ verify id
    @FindBy(css = "a[name='appearance-cnfg']")  WebElement appearancemenu;
    @FindBy(linkText = "Template Schedule")     WebElement templatescheduletab;

    // ─── Enable Checkbox ──────────────────────────
    @FindBy(css = "#enable")                    WebElement enable;

    // ─── Template Info ────────────────────────────
    @FindBy(css = "#template-index")            WebElement templateindex;
    @FindBy(css = "#template-name")             WebElement templatename;
    @FindBy(css = "#active")                    WebElement active;

    // ─── Schedule From / To ───────────────────────
    @FindBy(css = "#hh0")                       WebElement fromhour;
    @FindBy(css = "#mm0")                       WebElement frommins;
    @FindBy(css = "#hh1")                       WebElement tohour;
    @FindBy(css = "#mm1")                       WebElement tomins;

    // ─── Entire Week ──────────────────────────────
    @FindBy(css = "#entireweek")                WebElement entireweek;

    // ─── Day Checkboxes ───────────────────────────
    @FindBy(css = "#chkday0")                   WebElement sun;
    @FindBy(css = "#chkday1")                   WebElement mon;
    @FindBy(css = "#chkday2")                   WebElement tue;
    @FindBy(css = "#chkday3")                   WebElement wed;
    @FindBy(css = "#chkday4")                   WebElement thu;
    @FindBy(css = "#chkday5")                   WebElement fri;
    @FindBy(css = "#chkday6")                   WebElement sat;

    // ─── Template Table Links ─────────────────────
    @FindBy(css = "#template_name0")            WebElement templatebasic;
    @FindBy(css = "#template_name1")            WebElement templateoutdoor;
    @FindBy(css = "#template_name2")            WebElement templateindoor;
    @FindBy(css = "#template_name3")            WebElement templateday;
    @FindBy(css = "#template_name4")            WebElement templatenight;

    // ─── Template Status ──────────────────────────
    @FindBy(css = "#template_status0")          WebElement statusbasic;
    @FindBy(css = "#template_status1")          WebElement statusoutdoor;
    @FindBy(css = "#template_status2")          WebElement statusindoor;
    @FindBy(css = "#template_status3")          WebElement statusday;
    @FindBy(css = "#template_status4")          WebElement statusnight;

    // ─── Buttons ──────────────────────────────────
    @FindBy(id = "btndefault")                  WebElement btndefault;
    @FindBy(id = "btnsave")                     WebElement btnsave;
    @FindBy(id = "btncancel")                   WebElement btncancel;
    @FindBy(id = "btnyes")                      WebElement btnyes;

    // ═══════════════════════════════════════════════
    // NAVIGATION
    // ═══════════════════════════════════════════════
    public void clickconfiguration()       { configuration.click();       }
    public void clickimagesetting()        { imagesetting.click();        }
    public void clickappearancemenu()      { appearancemenu.click();      }
    public void clicktemplatescheduletab() { templatescheduletab.click(); }

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
    // CLICK OUTSIDE — trigger change event
    // ═══════════════════════════════════════════════
    public void clickoutside() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.activeElement.blur();");
    }

    // ═══════════════════════════════════════════════
    // HELPER — set textbox + click outside
    // ═══════════════════════════════════════════════
    public void settextbox(WebElement element, String value) {
        element.click();
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);
        element.sendKeys(value);
        clickoutside();
    }

    // ═══════════════════════════════════════════════
    // HELPER — set checkbox state
    // ═══════════════════════════════════════════════
    public void setcheckbox(WebElement element, String value) {
        boolean shouldbeselected = value.equalsIgnoreCase("true");
        if (shouldbeselected && !element.isSelected()) element.click();
        if (!shouldbeselected && element.isSelected())  element.click();
    }

    // ═══════════════════════════════════════════════
    // ENABLE CHECKBOX
    // ═══════════════════════════════════════════════
    public void setenablecheckbox(String value) { setcheckbox(enable, value); }
    public boolean isenableselected()           { return enable.isSelected(); }

    // ═══════════════════════════════════════════════
    // TEMPLATE INFO — read only
    // ═══════════════════════════════════════════════
    public String gettemplateindex() { return templateindex.getAttribute("value"); }
    public String gettemplatename()  { return templatename.getAttribute("value");  }

    // ═══════════════════════════════════════════════
    // ACTIVE CHECKBOX
    // ═══════════════════════════════════════════════
    public void setactivecheckbox(String value) { setcheckbox(active, value); }
    public boolean isactiveselected()           { return active.isSelected(); }

    // ═══════════════════════════════════════════════
    // FROM TIME
    // ═══════════════════════════════════════════════
    public void setfromhour(String value) { settextbox(fromhour, value); }
    public String getfromhour()           { return fromhour.getAttribute("value"); }

    public void setfrommins(String value) { settextbox(frommins, value); }
    public String getfrommins()           { return frommins.getAttribute("value"); }

    // ═══════════════════════════════════════════════
    // TO TIME
    // ═══════════════════════════════════════════════
    public void settohour(String value)   { settextbox(tohour, value); }
    public String gettohour()             { return tohour.getAttribute("value"); }

    public void settomins(String value)   { settextbox(tomins, value); }
    public String gettomins()             { return tomins.getAttribute("value"); }

    // ═══════════════════════════════════════════════
    // ENTIRE WEEK
    // ═══════════════════════════════════════════════
    public void setentireweek(String value) { setcheckbox(entireweek, value); }
    public boolean isentireweekselected()   { return entireweek.isSelected(); }

    // ═══════════════════════════════════════════════
    // INDIVIDUAL DAY CHECKBOXES
    // ═══════════════════════════════════════════════
    public void setsun(String value) { setcheckbox(sun, value); }
    public void setmon(String value) { setcheckbox(mon, value); }
    public void settue(String value) { setcheckbox(tue, value); }
    public void setwed(String value) { setcheckbox(wed, value); }
    public void setthu(String value) { setcheckbox(thu, value); }
    public void setfri(String value) { setcheckbox(fri, value); }
    public void setsat(String value) { setcheckbox(sat, value); }

    public boolean issunselected() { return sun.isSelected(); }
    public boolean ismonselected() { return mon.isSelected(); }
    public boolean istueselected() { return tue.isSelected(); }
    public boolean iswedselected() { return wed.isSelected(); }
    public boolean isthuselected() { return thu.isSelected(); }
    public boolean isfriselected() { return fri.isSelected(); }
    public boolean issatselected() { return sat.isSelected(); }

    // ═══════════════════════════════════════════════
    // SET ALL DAYS FROM DATA
    // ═══════════════════════════════════════════════
    public void setalldays(String s, String m, String t,
                           String w, String th, String f, String sa) {
        setsun(s);  setmon(m);  settue(t);  setwed(w);
        setthu(th); setfri(f);  setsat(sa);
    }

    // ═══════════════════════════════════════════════
    // TEMPLATE TABLE — click by index
    // ═══════════════════════════════════════════════
    public void clicktemplate(int index) {
        switch (index) {
            case 1: templatebasic.click();   break;
            case 2: templateoutdoor.click(); break;
            case 3: templateindoor.click();  break;
            case 4: templateday.click();     break;
            case 5: templatenight.click();   break;
        }
    }

    public String gettemplatenamefromtable(int index) {
        switch (index) {
            case 1: return templatebasic.getText();
            case 2: return templateoutdoor.getText();
            case 3: return templateindoor.getText();
            case 4: return templateday.getText();
            case 5: return templatenight.getText();
            default: return "";
        }
    }

    // ═══════════════════════════════════════════════
    // TEMPLATE STATUS
    // ═══════════════════════════════════════════════
    public String getstatus(int index) {
        switch (index) {
            case 1: return statusbasic.getText();
            case 2: return statusoutdoor.getText();
            case 3: return statusindoor.getText();
            case 4: return statusday.getText();
            case 5: return statusnight.getText();
            default: return "";
        }
    }

    // ═══════════════════════════════════════════════
    // BUTTONS — your existing pattern
    // ═══════════════════════════════════════════════
    public void clickdefault() { btndefault.click(); }
    public void clicksave()    { btnsave.click();    }
    public void clickcancel()  { btncancel.click();  }
    public void clickyes()     { btnyes.click();     }

    public void clickdefaultandconfirm() {
        clickdefault();
        switchtoparentframe();
        clickyes();
        switchtoconfigframe();
    }
}
