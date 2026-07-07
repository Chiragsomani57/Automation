package ipcamera.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class advancednetworkpageobject {

    WebDriver driver;

    public advancednetworkpageobject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // ═══════════════════════════════════════════════
    // NAVIGATION
    // ═══════════════════════════════════════════════
    @FindBy(id = "configuration")  WebElement configuration;
    @FindBy(id = "networkset")     WebElement networksetting;
    @FindBy(id = "advanced")       WebElement advancedsettingtab;
    @FindBy(id = "upnp")           WebElement upnptab;
    @FindBy(id = "ddns")           WebElement ddnstab;
    @FindBy(id = "mxdns")          WebElement mxdnstab;
    @FindBy(id = "qos")            WebElement qostab;
    @FindBy(id = "802_1_X")        WebElement dot1xtab;

    // ═══════════════════════════════════════════════
    // UPnP AND BONJOUR
    // ═══════════════════════════════════════════════
    @FindBy(css = "#upnp")                  WebElement upnp;
    @FindBy(css = "#upnp-name")             WebElement upnpname;
    @FindBy(css = "#upnp-fwd")              WebElement upnpportforwarding;
    @FindBy(css = "#auto")                  WebElement portforwardingauto;
    @FindBy(css = "#manual")                WebElement portforwardingmanual;
    @FindBy(css = "#external-http-port")    WebElement externalhttpsport;
    @FindBy(css = "#external-rtsp-port")    WebElement externalrtspport;
    @FindBy(css = "#bonjour")               WebElement bonjour;
    @FindBy(css = "#bonjour-name")          WebElement bonjourname;

    // ═══════════════════════════════════════════════
    // DDNS
    // ═══════════════════════════════════════════════
    @FindBy(css = "#ddns")               WebElement ddnsenable;
    @FindBy(css = "#ddns-server")        WebElement ddnsserver;
    @FindBy(css = "#user-name")          WebElement ddnsusername;
    @FindBy(css = "#password")           WebElement ddnspassword;
    @FindBy(css = "#host-name")          WebElement ddnshostname;
    @FindBy(css = "#update-interval")    WebElement ddnsupdateinterval;
    @FindBy(css = "#btnupdate")          WebElement ddnsbtnupdate;

    // ═══════════════════════════════════════════════
    // MATRIX DNS
    // ═══════════════════════════════════════════════
    @FindBy(css = "#enable")      WebElement mxdnsenable;
    @FindBy(css = "#host-name")   WebElement mxdnshostname;
    @FindBy(css = "#fwd-port")    WebElement mxdnsfwdport;

    // ═══════════════════════════════════════════════
    // QoS
    // ═══════════════════════════════════════════════
    @FindBy(css = "#qos")               WebElement qosenable;
    @FindBy(css = "#live-dscp")         WebElement livevideodscp;
    @FindBy(css = "#event-dscp")        WebElement eventrecorddscp;
    @FindBy(css = "#management-dscp")   WebElement managementdscp;

    // ═══════════════════════════════════════════════
    // 802.1X
    // ═══════════════════════════════════════════════
    @FindBy(css = "#enable")            WebElement dot1xenable;
    @FindBy(css = "#eap-type")          WebElement dot1xeapmethod;
    @FindBy(css = "#user-name")         WebElement dot1xusername;
    @FindBy(css = "#password")          WebElement dot1xpassword;
    @FindBy(css = "#confirm-password")  WebElement dot1xconfirmpassword;

    // ═══════════════════════════════════════════════
    // BUTTONS
    // ═══════════════════════════════════════════════
    @FindBy(id = "btndefault")    WebElement btndefault;
    @FindBy(id = "btnsave")       WebElement btnsave;
    @FindBy(id = "btncancel")     WebElement btncancel;
    @FindBy(id = "btnyes")        WebElement btnyes;

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
    // NAVIGATION METHODS
    // ═══════════════════════════════════════════════
    public void clickconfiguration()    { configuration.click();    }
    public void clicknetworksetting()   {networksetting.click();    }
    public void clickadvancedsettingtab(){ advancedsettingtab.click(); }
    public void clickupnptab()          { upnptab.click();          }
    public void clickddnstab()          { ddnstab.click();          }
    public void clickmxdnstab()         { mxdnstab.click();         }
    public void clickqostab()           { qostab.click();           }
    public void clickdot1xtab()         { dot1xtab.click();         }

    // ═══════════════════════════════════════════════
    // UPnP AND BONJOUR — getters
    // ═══════════════════════════════════════════════
    public boolean isupnpselected()              { return upnp.isSelected(); }
    public String getupnpname()                  { return upnpname.getAttribute("value"); }
    public boolean isupnpportforwardingselected(){ return upnpportforwarding.isSelected(); }
    public boolean isportforwardingauto()        { return portforwardingauto.isSelected(); }
    public boolean isportforwardingmanual()      { return portforwardingmanual.isSelected(); }
    public String getexternalhttpsport()         { return externalhttpsport.getAttribute("value"); }
    public String getexternalrtspport()          { return externalrtspport.getAttribute("value"); }
    public boolean isbonjourselected()           { return bonjour.isSelected(); }
    public String getbonjourname()               { return bonjourname.getAttribute("value"); }

    // ═══════════════════════════════════════════════
    // DDNS — getters
    // ═══════════════════════════════════════════════
    public boolean isddnsenabled()        { return ddnsenable.isSelected(); }
    public String getddnsserver() {
        return new Select(ddnsserver).getFirstSelectedOption().getText();
    }
    public String getddnsusername()       { return ddnsusername.getAttribute("value"); }
    public String getddnspassword()       { return ddnspassword.getAttribute("value"); }
    public String getddnshostname()       { return ddnshostname.getAttribute("value"); }
    public String getddnsupdateinterval() { return ddnsupdateinterval.getAttribute("value"); }

    // ═══════════════════════════════════════════════
    // MATRIX DNS — getters
    // ═══════════════════════════════════════════════
    public boolean ismxdnsenabled()  { return mxdnsenable.isSelected(); }
    public String getmxdnshostname() { return mxdnshostname.getAttribute("value"); }
    public String getmxdnsfwdport()  { return mxdnsfwdport.getAttribute("value"); }

    // ═══════════════════════════════════════════════
    // QoS — getters
    // ═══════════════════════════════════════════════
    public boolean isqosenabled()         { return qosenable.isSelected(); }
    public String getlivevideodscp()      { return livevideodscp.getAttribute("value"); }
    public String geteventrecorddscp()    { return eventrecorddscp.getAttribute("value"); }
    public String getmanagementdscp()     { return managementdscp.getAttribute("value"); }

    // ═══════════════════════════════════════════════
    // 802.1X — getters
    // ═══════════════════════════════════════════════
    public boolean isdot1xenabled()        { return dot1xenable.isSelected(); }
    public String getdot1xeapmethod()      { return dot1xeapmethod.getAttribute("value"); }
    public String getdot1xusername()       { return dot1xusername.getAttribute("value"); }
    public String getdot1xpassword()       { return dot1xpassword.getAttribute("value"); }
    public String getdot1xconfirmpassword(){ return dot1xconfirmpassword.getAttribute("value"); }

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
