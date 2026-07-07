package ipcamera.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class daynightpageobject {

    WebDriver driver;

    public daynightpageobject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // ─── Navigation ───────────────────────────────
    @FindBy(id = "configuration")               WebElement configuration;
    @FindBy(id = "basicset")                    WebElement basicsetting;
    @FindBy(id = "imgset")                      WebElement imagesetting;
    @FindBy(id = "appearance")                  WebElement appearancemenu;
    @FindBy(id = "daynight-cnfg")               WebElement dayandnighttab;

    // ─── Template ─────────────────────────────────
    @FindBy(css = "#template-index")            WebElement template;

    // ─── IR LEDs Mode — radio buttons ─────────────
    @FindBy(css = "#ir-leds0")                  WebElement irledauto;
    @FindBy(css = "#ir-leds1")                  WebElement irledalwaysoff;
    @FindBy(css = "#ir-leds2")                  WebElement irledalwayson;
    @FindBy(css = "#ir-leds3")                  WebElement irledschedule;

    // ─── Day/Night Switching — dropdowns ──────────
    @FindBy(css = "#day-nit")                   WebElement daytonight;
    @FindBy(css = "#nit-day")                   WebElement nighttoday;

    // ─── Schedule — From / To ─────────────────────
    @FindBy(css = "#hh0")                       WebElement fromhour;
    @FindBy(css = "#mm0")                       WebElement frommins;
    @FindBy(css = "#hh1")                       WebElement tohour;
    @FindBy(css = "#mm1")                       WebElement tomins;

    // ─── IR LEDs Intensity Mode — radio buttons ────
    @FindBy(css = "#intensity_auto")            WebElement intensityauto;
    @FindBy(css = "#intensity_manual")          WebElement intensitymanual;

    // ─── Intensity values ─────────────────────────
    @FindBy(css = "#irleds_far_val")            WebElement intensityfar;
    @FindBy(css = "#irleds_val")                WebElement intensitynear;

    // ─── Buttons ──────────────────────────────────
    @FindBy(id = "btndefault")                  WebElement btndefault;
    @FindBy(id = "btnsave")                     WebElement btnsave;
    @FindBy(id = "btncancel")                   WebElement btncancel;
    @FindBy(id = "btnyes")                      WebElement btnyes;

    // ═══════════════════════════════════════════════
    // NAVIGATION
    // ═══════════════════════════════════════════════
    public void clickconfiguration()  { configuration.click();  }
    public void clickbasicsetting()   { basicsetting.click();   }
    public void clickimagesetting()   {
        if (!imagesetting.isDisplayed())
        {
        clickbasicsetting();}
        else
        {
            imagesetting.click();
        }
    }
    public void clickappearancemenu() { appearancemenu.click(); }
    public void clickdayandnighttab() { dayandnighttab.click(); }

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
    // TEMPLATE — dropdown
    // ═══════════════════════════════════════════════
    public void settemplate(String value) { new Select(template).selectByVisibleText(value); }
    public String gettemplate() { return new Select(template).getFirstSelectedOption().getText(); }

    // ═══════════════════════════════════════════════
    // IR LED MODE — radio buttons
    // ═══════════════════════════════════════════════
    public void setirledmode(String value) {
        switch (value.toLowerCase()) {
            case "auto":       if (!irledauto.isSelected())       irledauto.click();       break;
            case "alwaysoff":  if (!irledalwaysoff.isSelected())  irledalwaysoff.click();  break;
            case "alwayson":   if (!irledalwayson.isSelected())   irledalwayson.click();   break;
            case "schedule":   if (!irledschedule.isSelected())   irledschedule.click();   break;
        }
    }

    public String getirledmode() {
        if (irledauto.isSelected())      return "auto";
        if (irledalwaysoff.isSelected()) return "alwaysoff";
        if (irledalwayson.isSelected())  return "alwayson";
        if (irledschedule.isSelected())  return "schedule";
        return "";
    }

    // ═══════════════════════════════════════════════
    // DAY TO NIGHT SWITCHING — dropdown
    // ═══════════════════════════════════════════════
    public void setdaytonight(String value) { new Select(daytonight).selectByVisibleText(value); }
    public String getdaytonight() { return new Select(daytonight).getFirstSelectedOption().getText(); }

    // ═══════════════════════════════════════════════
    // NIGHT TO DAY SWITCHING — dropdown
    // ═══════════════════════════════════════════════
    public void setnighttoday(String value) { new Select(nighttoday).selectByVisibleText(value); }
    public String getnighttoday() { return new Select(nighttoday).getFirstSelectedOption().getText(); }

    // ═══════════════════════════════════════════════
    // SCHEDULE — FROM time
    // ═══════════════════════════════════════════════
    public void setfromhour(String value) { settextbox(fromhour, value); }
    public String getfromhour() { return fromhour.getAttribute("value"); }

    public void setfrommins(String value) { settextbox(frommins, value); }
    public String getfrommins() { return frommins.getAttribute("value"); }

    // ═══════════════════════════════════════════════
    // SCHEDULE — TO time
    // ═══════════════════════════════════════════════
    public void settohour(String value) { settextbox(tohour, value); }
    public String gettohour() { return tohour.getAttribute("value"); }

    public void settomins(String value) { settextbox(tomins, value); }
    public String gettomins() { return tomins.getAttribute("value"); }

    // ═══════════════════════════════════════════════
    // IR LEDs INTENSITY MODE — radio buttons
    // ═══════════════════════════════════════════════
    public void setintensitymode(String value) {
        switch (value.toLowerCase()) {
            case "auto":   if (!intensityauto.isSelected())   intensityauto.click();   break;
            case "smart":  if (!intensityauto.isSelected())   intensityauto.click();   break; // ⚠️ verify smart locator
            case "manual": if (!intensitymanual.isSelected()) intensitymanual.click(); break;
        }
    }

    public String getintensitymode() {
        if (intensityauto.isSelected())   return "auto";
        if (intensitymanual.isSelected()) return "manual";
        return "";
    }

    // ═══════════════════════════════════════════════
    // INTENSITY — FAR and NEAR
    // ═══════════════════════════════════════════════
    public void setintensityfar(String value)  { settextbox(intensityfar,  value); }
    public String getintensityfar()  { return intensityfar.getAttribute("value");  }

    public void setintensitynear(String value) { settextbox(intensitynear, value); }
    public String getintensitynear() { return intensitynear.getAttribute("value"); }

    // ═══════════════════════════════════════════════
    // BUTTONS — your existing pattern
    // ═══════════════════════════════════════════════
    public void clickdefault() { btndefault.click(); }
    public void clicksave()    { btnsave.click();    }
    public void clickcancel()  { btncancel.click();  }
    public void clickyes()     { btnyes.click();     }

    public void clickdefaultandconfirm() {
        clickdefault();
        switchtoparentframe();   // panel-frame → config-frame
        clickyes();
        switchtoconfigframe();   // back inside
    }
}
