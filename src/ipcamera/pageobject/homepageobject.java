package ipcamera.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class homepageobject {

    WebDriver driver;

    public homepageobject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "appearanceHome-cnfg")
    WebElement appearance;

    @FindBy(id = "brightness-val")
    WebElement brightness;

    @FindBy(id = "contrast-val")
    WebElement contrast;

    @FindBy(id = "saturation-val")
    WebElement saturation;

    @FindBy(id = "hue-val")
    WebElement hue;

    public void clickapperance() {
        List<WebElement> elements = driver.findElements(By.id("appearanceHome-cnfg"));
        if (!elements.isEmpty()) {
            elements.get(0).click();
        } else {
            System.out.println("Appearance element not found in DOM");
        }
    }

    // ─── Brightness ───────────────────────────────
    public void setbrightness(String value) {
    	brightness.sendKeys(Keys.CONTROL+"a");   
    	brightness.sendKeys(value);
    	brightness.sendKeys(Keys.ENTER);
    }
    
    
    public String getbrightness() {
        return brightness.getAttribute("value");
    }

    // ─── Contrast ─────────────────────────────────
    public void setcontrast(String value) {
    	contrast.sendKeys(Keys.CONTROL+"a");   
    	contrast.sendKeys(value);
    	contrast.sendKeys(Keys.ENTER);
    }
    public String getcontrast() {
        return contrast.getAttribute("value");
    }

    // ─── Saturation ───────────────────────────────
    public void setsaturation(String value) {
    	saturation.sendKeys(Keys.CONTROL+"a");   
    	saturation.sendKeys(value);
    	saturation.sendKeys(Keys.ENTER);
    }
    public String getsaturation() {
        return saturation.getAttribute("value");
    }

    // ─── Hue ──────────────────────────────────────
    public void sethue(String value) {
    	hue.sendKeys(Keys.CONTROL+"a");   
    	hue.sendKeys(value);
    	hue.sendKeys(Keys.ENTER);
    	

    }
    public String gethue() {
        return hue.getAttribute("value");
    }
}