package ipcamera.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class streamprofileobject {

    WebDriver driver;

    public streamprofileobject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // ─── Navigation ───────────────────────────────
    @FindBy(id = "configuration")      WebElement configuration;
    @FindBy(id = "basicset")           WebElement basicsetting;
    @FindBy(id = "stream")             WebElement streamprofie;   // ✅ removed duplicate

    // ─── Profile Table (right side) ───────────────
    @FindBy(id = "pro_name0")          WebElement profilename0;
    @FindBy(id = "pro_name1")          WebElement profilename1;
    @FindBy(id = "pro_name2")          WebElement profilename2;
    @FindBy(id = "pro_name3")          WebElement profilename3;

    // ─── Form Fields ──────────────────────────────
    @FindBy(id = "profile-no")         WebElement profileno;
    @FindBy(id = "name")               WebElement name;
    @FindBy(id = "codec")              WebElement codec;
    @FindBy(id = "codec-text")         WebElement codectext;
    @FindBy(id = "resolution")         WebElement resolution;
    @FindBy(id = "cbr")                WebElement cbr;
    @FindBy(id = "vbr")                WebElement vbr;
    @FindBy(id = "bit-rate")           WebElement bitrate;
    @FindBy(id = "image-quality")      WebElement imagequality;
    @FindBy(id = "fps")                WebElement fps;
    @FindBy(id = "fpsrange")           WebElement fpsrange;
    @FindBy(id = "gop")                WebElement gop;
    @FindBy(css = "label[name='lagoprange']") WebElement goprange;
    @FindBy(id = "adaptive-streaming") WebElement adaptivestreaming;
    @FindBy(id = "btnadaptive")        WebElement btnadaptive;
    @FindBy(id = "bandwidth-optimization") WebElement bandwidthoptimization;
    @FindBy(id = "audio")              WebElement audio;
    @FindBy(id = "smart-stream")       WebElement smartstream;
    @FindBy(id = "btnsmartstream")     WebElement btnsmartstream;
    @FindBy(id = "roi")                WebElement roi;
    @FindBy(id = "btnroi")             WebElement btnroi;
    @FindBy(css = ".header-label.close-btn") WebElement closebtn;
    
 // ─── Locators ─────────────────────────────────────
    @FindBy(id = "btndefault")   WebElement btndefault;
    @FindBy(id = "btnsave")      WebElement btnsave;
    @FindBy(id = "btncancel")    WebElement btncancel;
    @FindBy(id = "btnyes")       WebElement btnyes;      // confirm dialog — check your actual id

    
    
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
    
    

    // ─── Navigation Methods ───────────────────────
    public void clickconfiguartion() {
        configuration.click();
    }
    public void clickbasicsetting() {
        basicsetting.click();
    }
    public void clickstreamprofie() {
        streamprofie.click();   // ✅ fixed — was empty before
    }

    // ─── Profile Table Click ──────────────────────
    public void clickprofile(int profilenumber) {
        switch (profilenumber) {
            case 1: profilename0.click(); break;
            case 2: profilename1.click(); break;
            case 3: profilename2.click(); break;
            case 4: profilename3.click(); break;
        }
    }

    // ─── Profile No (read only) ───────────────────
    public String getprofileno() {
        return profileno.getAttribute("value");
    }

    // ─── Name ─────────────────────────────────────
    public void setname(String value) {
        name.click();
        name.sendKeys(Keys.CONTROL + "a");
        name.sendKeys(Keys.DELETE);
        name.sendKeys(value);
    }
    public String getname() {
        return name.getAttribute("value");
    }

    // ─── Codec (dropdown) ─────────────────────────
    public void setcodec(String value) {
        new Select(codec).selectByVisibleText(value);
    }
    public String getcodec() {
        return new Select(codec).getFirstSelectedOption().getText();
    }
    public String getcodectext() {
        return codectext.getText();  // profile 3 read-only
    }

    // ─── Resolution (dropdown) ────────────────────
    public void setresolution(String value) {
        new Select(resolution).selectByVisibleText(value);
    }
    public String getresolution() {
        return new Select(resolution).getFirstSelectedOption().getText();
    }

    // ─── Bit Rate Control (CBR / VBR) ────────────
    public void selectCBR() {
        if (!cbr.isSelected()) cbr.click();
    }
    public void selectVBR() {
        if (!vbr.isSelected()) vbr.click();
    }
    public boolean isCBRselected() { return cbr.isSelected(); }
    public boolean isVBRselected() { return vbr.isSelected(); }

    // ─── Bit Rate (dropdown) ──────────────────────
    public void setbitrate(String value) {
        new Select(bitrate).selectByVisibleText(value);
    }
    public String getbitrate() {
        return new Select(bitrate).getFirstSelectedOption().getText();
    }

    // ─── Image Quality (dropdown) ─────────────────
    public void setimagequality(String value) {
        new Select(imagequality).selectByVisibleText(value);
    }
    public String getimagequality() {
        return new Select(imagequality).getFirstSelectedOption().getText();
    }

    // ─── FPS ──────────────────────────────────────
    public void setfps(String value) {
        fps.click();
        fps.sendKeys(Keys.CONTROL + "a");
        fps.sendKeys(Keys.DELETE);
        fps.sendKeys(value);
    }
    public String getfps()      { return fps.getAttribute("value"); }
    public String getfpsrange() { return fpsrange.getText(); }

    // ─── GOP ──────────────────────────────────────
    public void setgop(String value) {
        gop.click();
        gop.sendKeys(Keys.CONTROL + "a");
        gop.sendKeys(Keys.DELETE);
        gop.sendKeys(value);
    }
    public String getgop()      { return gop.getAttribute("value"); }
    public String getgoprange() { return goprange.getText(); }

    // ─── Adaptive Streaming (checkbox) ────────────
    public void enableadaptivestreaming()  { if (!adaptivestreaming.isSelected()) adaptivestreaming.click(); }
    public void disableadaptivestreaming() { if (adaptivestreaming.isSelected())  adaptivestreaming.click(); }
    public boolean isadaptivestreamingselected() { return adaptivestreaming.isSelected(); }
    public void clickbtnadaptive() { btnadaptive.click(); }

    // ─── Audio (checkbox) ─────────────────────────
    public void enableaudio()  { if (!audio.isSelected()) audio.click(); }
    public void disableaudio() { if (audio.isSelected())  audio.click(); }
    public boolean isaudioselected() { return audio.isSelected(); }
    public boolean isaudiovisible() {return audio.isDisplayed();}

    // ─── Smart Stream (checkbox) ──────────────────
    public void enablesmartstream()  { if (!smartstream.isSelected()) smartstream.click(); }
    public void disablesmartstream() { if (smartstream.isSelected())  smartstream.click(); }
    public boolean issmartStreamselected() { return smartstream.isSelected(); }
    public void clickbtnsmartstream() { btnsmartstream.click(); }

    // ─── ROI (profile 4 only) ─────────────────────
    public void enableroi()  { if (!roi.isSelected()) roi.click(); }
    public void disableroi() { if (roi.isSelected())  roi.click(); }
    public boolean isroiselected() { return roi.isSelected(); }
    public void clickbtnroi() { btnroi.click(); }

    // ─── Close Button ─────────────────────────────
    public void clickclose() { closebtn.click(); }
}