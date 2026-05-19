package ipcamera.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class audiopageobject {

    WebDriver driver;

    public audiopageobject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // ─── Navigation ───────────────────────────────
    @FindBy(id = "configuration")      WebElement configuration;
    @FindBy(id = "basicset")           WebElement basicsetting;
    @FindBy(id = "audio")              WebElement audiosettings;

    // ─── Audio In ─────────────────────────────────
    @FindBy(css = "#input-encoder")    WebElement inputencoder;
    @FindBy(css = "#input-gain-val")   WebElement inputgain;

    // ─── Audio Out ────────────────────────────────
    @FindBy(css = "#audio-out")        WebElement audioout;
    @FindBy(css = "#output-gain-val")  WebElement outputgain;

    // ─── Buttons ──────────────────────────────────
    @FindBy(id = "btndefault")         WebElement btndefault;
    @FindBy(id = "btnsave")            WebElement btnsave;
    @FindBy(id = "btncancel")          WebElement btncancel;
    @FindBy(id = "btnyes")             WebElement btnyes;

    // ═══════════════════════════════════════════════
    // NAVIGATION
    // ═══════════════════════════════════════════════
    public void clickconfiguration() { configuration.click(); }
    public void clickbasicsetting()  { basicsetting.click();  }
    public void clickaudiosettings() { audiosettings.click(); }

    // ═══════════════════════════════════════════════
    // INPUT ENCODER — dropdown
    // ═══════════════════════════════════════════════
    public void setinputencoder(String value) {
        new Select(inputencoder).selectByVisibleText(value);
    }
    public String getinputencoder() {
        return new Select(inputencoder).getFirstSelectedOption().getText();
    }

    // ═══════════════════════════════════════════════
    // INPUT GAIN — textbox
    // ═══════════════════════════════════════════════
    public void setinputgain(String value) {
        inputgain.click();
        inputgain.sendKeys(Keys.CONTROL + "a");
        inputgain.sendKeys(Keys.DELETE);
        inputgain.sendKeys(value);
    }
    public String getinputgain() {
        return inputgain.getAttribute("value");
    }

    // ═══════════════════════════════════════════════
    // AUDIO OUT — checkbox
    // ═══════════════════════════════════════════════
    public void enableaudioout()  { if (!audioout.isSelected()) audioout.click(); }
    public void disableaudioout() { if (audioout.isSelected())  audioout.click(); }
    public boolean isaudiooutselected() { return audioout.isSelected(); }

    // ═══════════════════════════════════════════════
    // OUTPUT GAIN — textbox
    // ═══════════════════════════════════════════════
    public void setoutputgain(String value) {
        outputgain.click();
        outputgain.sendKeys(Keys.CONTROL + "a");
        outputgain.sendKeys(Keys.DELETE);
        outputgain.sendKeys(value);
    }
    public String getoutputgain() {
        return outputgain.getAttribute("value");
    }

    // ═══════════════════════════════════════════════
    // BUTTONS
    // ═══════════════════════════════════════════════
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
}
