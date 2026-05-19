package ipcamera.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class datetimepageobject {

    WebDriver driver;

    public datetimepageobject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // ─── Navigation ───────────────────────────────
    @FindBy(id = "configuration")       WebElement configuration;
    @FindBy(id = "basicset")            WebElement basicsetting;
    @FindBy(id = "datetime")         	WebElement dateandtime;    

    // ─── Time Zone ────────────────────────────────
    @FindBy(css = "#time-zone")         WebElement timezone;

    // ─── Date and Time ────────────────────────────
    @FindBy(css = "#date_sel")          WebElement date;
    @FindBy(css = "#hour")              WebElement hour;
    @FindBy(css = "#minute")            WebElement minute;
    @FindBy(css = "#second")            WebElement second;
    @FindBy(css = "#btnset")            WebElement btnset;
    @FindBy(css = "#syncwithpc")        WebElement syncwithpc;
    @FindBy(css = "#set-via-onvif")     WebElement setviaonvif;
    @FindBy(css = "#set-via-nvr-vms")   WebElement setviaapi;

    // ─── Auto Synchronize With NTP ────────────────
    @FindBy(css = "#synchronize")           WebElement synchronize;
    @FindBy(css = "#preferred-ntp-server")  WebElement preferredntpserver;
    @FindBy(css = "#update-time")           WebElement updatetime;

    // ─── Daylight Saving Time ─────────────────────
    @FindBy(css = "#dst")               WebElement dst;

    // ─── Forward Clock ────────────────────────────
    @FindBy(css = "#fwd-month")         WebElement fwdmonth;
    @FindBy(css = "#fwd-week")          WebElement fwdweek;
    @FindBy(css = "#fwd-day")           WebElement fwdday;
    @FindBy(css = "#fwd-time-hh")       WebElement fwdtimehh;
    @FindBy(css = "#fwd-time-mm")       WebElement fwdtimemm;

    // ─── Reverse Clock ────────────────────────────
    @FindBy(css = "#rev-month")         WebElement revmonth;
    @FindBy(css = "#rev-week")          WebElement revweek;
    @FindBy(css = "#rev-day")           WebElement revday;
    @FindBy(css = "#rev-time-hh")       WebElement revtimehh;
    @FindBy(css = "#rev-time-mm")       WebElement revtimemm;

    // ─── Buttons ──────────────────────────────────
    @FindBy(id = "btndefault")          WebElement btndefault;
    @FindBy(id = "btnsave")             WebElement btnsave;
    @FindBy(id = "btncancel")           WebElement btncancel;
    @FindBy(id = "btnyes")              WebElement btnyes;
    
    public void switchtopanelframe()


    {
    	WebElement panelframe= driver.findElement(By.id("panel-frame"));
    	driver.switchTo().frame(panelframe);
    }


    public void switchtoconfigframe()


    {
    	WebElement configframe= driver.findElement(By.id("config-frame"));
    	driver.switchTo().frame(configframe);
    }

    public void switchtoparentframe()

    {
    	driver.switchTo().parentFrame();
    }


	
    // ─── Default Button (inside panel-frame) ──────────
    public void clickdefault() {
        btndefault.click();
    }

    // ─── Save Button (inside panel-frame) ─────────────
    public void clicksave() {
        btnsave.click();
    }

    // ─── Cancel Button (inside panel-frame) ───────────
    public void clickcancel() {
        btncancel.click();
    }

   

    // ─── Click Yes in config-frame ────────────────────
    public void clickyes() {
    	btnyes.click();                               // click Yes button
    }

    // ─── Full Default Flow ────────────────────────────
    public void clickdefaultandconfirm() {
        clickdefault();
        switchtoparentframe();
        clickyes();          
        switchtoconfigframe();
    }

    // ═══════════════════════════════════════════════
    // NAVIGATION METHODS
    // ═══════════════════════════════════════════════
    public void clickconfiguration()  { configuration.click(); }
    public void clickbasicsetting()   { basicsetting.click(); }
    public void clickdateandtime()    { dateandtime.click(); }

    // ═══════════════════════════════════════════════
    // TIME ZONE
    // ═══════════════════════════════════════════════
    public void settimezone(String value) {
        new Select(timezone).selectByVisibleText(value);
    }
    public String gettimezone() {
        return new Select(timezone).getFirstSelectedOption().getText();
    }

    // ═══════════════════════════════════════════════
    // DATE
    // ═══════════════════════════════════════════════
    public void setdate(String value) {
        date.click();
        date.sendKeys(Keys.CONTROL + "a");
        date.sendKeys(Keys.DELETE);
        date.sendKeys(value);
    }
    public String getdate() {
        return date.getAttribute("value");
    }

    // ═══════════════════════════════════════════════
    // TIME — HH MM SS
    // ═══════════════════════════════════════════════
    public void sethour(String value) {
        hour.click();
        hour.sendKeys(Keys.CONTROL + "a");
        hour.sendKeys(Keys.DELETE);
        hour.sendKeys(value);
    }
    public String gethour() { return hour.getAttribute("value"); }

    public void setminute(String value) {
        minute.click();
        minute.sendKeys(Keys.CONTROL + "a");
        minute.sendKeys(Keys.DELETE);
        minute.sendKeys(value);
    }
    public String getminute() { return minute.getAttribute("value"); }

    public void setsecond(String value) {
        second.click();
        second.sendKeys(Keys.CONTROL + "a");
        second.sendKeys(Keys.DELETE);
        second.sendKeys(value);
    }
    public String getsecond() { return second.getAttribute("value"); }

    // ─── Set button ───────────────────────────────
    public void clickset()      { btnset.click(); }

    // ─── Sync with PC ─────────────────────────────
    public void clicksyncwithpc() { syncwithpc.click(); }

    // ═══════════════════════════════════════════════
    // SET VIA ONVIF
    // ═══════════════════════════════════════════════
    public void enablesetviaonvif()  { if (!setviaonvif.isSelected()) setviaonvif.click(); }
    public void disablesetviaonvif() { if (setviaonvif.isSelected())  setviaonvif.click(); }
    public boolean issetviaonvifselected() { return setviaonvif.isSelected(); }

    // ═══════════════════════════════════════════════
    // SET VIA API
    // ═══════════════════════════════════════════════
    public void enablesetviaapi()  { if (!setviaapi.isSelected()) setviaapi.click(); }
    public void disablesetviaapi() { if (setviaapi.isSelected())  setviaapi.click(); }
    public boolean issetviaApiselected() { return setviaapi.isSelected(); }

    // ═══════════════════════════════════════════════
    // AUTO SYNCHRONIZE WITH NTP
    // ═══════════════════════════════════════════════
    public void enablesynchronize()  { if (!synchronize.isSelected()) synchronize.click(); }
    public void disablesynchronize() { if (synchronize.isSelected())  synchronize.click(); }
    public boolean issynchronizeselected() { return synchronize.isSelected(); }

    public void setpreferredntpserver(String value) {
        new Select(preferredntpserver).selectByVisibleText(value);
    }
    public String getpreferredntpserver() {
        return new Select(preferredntpserver).getFirstSelectedOption().getText();
    }

    public void setupdatetime(String value) {
        new Select(updatetime).selectByVisibleText(value);
    }
    public String getupdatetime() {
        return new Select(updatetime).getFirstSelectedOption().getText();
    }

    // ═══════════════════════════════════════════════
    // DAYLIGHT SAVING TIME
    // ═══════════════════════════════════════════════
    public void enabledst()  { if (!dst.isSelected()) dst.click(); }
    public void disabledst() { if (dst.isSelected())  dst.click(); }
    public boolean isdstselected() { return dst.isSelected(); }

    // ─── Forward Clock ────────────────────────────
    public void setfwdmonth(String value) { new Select(fwdmonth).selectByVisibleText(value); }
    public String getfwdmonth() { return new Select(fwdmonth).getFirstSelectedOption().getText(); }

    public void setfwdweek(String value)  { new Select(fwdweek).selectByVisibleText(value); }
    public String getfwdweek() { return new Select(fwdweek).getFirstSelectedOption().getText(); }

    public void setfwdday(String value)   { new Select(fwdday).selectByVisibleText(value); }
    public String getfwdday() { return new Select(fwdday).getFirstSelectedOption().getText(); }

    public void setfwdtimehh(String value) {
        fwdtimehh.click();
        fwdtimehh.sendKeys(Keys.CONTROL + "a");
        fwdtimehh.sendKeys(Keys.DELETE);
        fwdtimehh.sendKeys(value);
    }
    public String getfwdtimehh() { return fwdtimehh.getAttribute("value"); }

    public void setfwdtimemm(String value) {
        fwdtimemm.click();
        fwdtimemm.sendKeys(Keys.CONTROL + "a");
        fwdtimemm.sendKeys(Keys.DELETE);
        fwdtimemm.sendKeys(value);
    }
    public String getfwdtimemm() { return fwdtimemm.getAttribute("value"); }

    // ─── Reverse Clock ────────────────────────────
    public void setrevmonth(String value) { new Select(revmonth).selectByVisibleText(value); }
    public String getrevmonth() { return new Select(revmonth).getFirstSelectedOption().getText(); }

    public void setrevweek(String value)  { new Select(revweek).selectByVisibleText(value); }
    public String getrevweek() { return new Select(revweek).getFirstSelectedOption().getText(); }

    public void setrevday(String value)   { new Select(revday).selectByVisibleText(value); }
    public String getrevday() { return new Select(revday).getFirstSelectedOption().getText(); }

    public void setrevtimehh(String value) {
        revtimehh.click();
        revtimehh.sendKeys(Keys.CONTROL + "a");
        revtimehh.sendKeys(Keys.DELETE);
        revtimehh.sendKeys(value);
    }
    public String getrevtimehh() { return revtimehh.getAttribute("value"); }

    public void setrevtimemm(String value) {
        revtimemm.click();
        revtimemm.sendKeys(Keys.CONTROL + "a");
        revtimemm.sendKeys(Keys.DELETE);
        revtimemm.sendKeys(value);
    }
    public String getrevtimemm() { return revtimemm.getAttribute("value"); }

    // ═══════════════════════════════════════════════
    // BUTTONS
    
}