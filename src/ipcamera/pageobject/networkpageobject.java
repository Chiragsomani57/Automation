package ipcamera.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class networkpageobject {

    WebDriver driver;

    public networkpageobject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }



    // ═══════════════════════════════════════════════
    // NAVIGATION
    // ═══════════════════════════════════════════════
    @FindBy(id = "configuration")           WebElement configuration;
    @FindBy(id = "networkset")              WebElement networksetting;
    @FindBy(id = "basicsettab")             WebElement basicsettab;
    @FindBy(id = "basic")                   WebElement ipaddresssetting;
    @FindBy(id = "ipfilter")                WebElement ipfilter;
    @FindBy(id = "nas")                     WebElement nassetting;
    @FindBy(id = "ports")                   WebElement ports;
    @FindBy(id = "multicast")               WebElement multicast;
    @FindBy(className = "msg-space")        WebElement validationmessage;

    // ═══════════════════════════════════════════════
    // IP ADDRESS SETTINGS
    // ═══════════════════════════════════════════════
    @FindBy(css = "#host-name")             WebElement hostname;
    @FindBy(css = "#ipv4-configuration")    WebElement ipv4configuration;
    @FindBy(css = "#ipv4-address")          WebElement ipv4address;
    @FindBy(css = "#subnet-mask")           WebElement subnetmask;
    @FindBy(css = "#default-gateway")       WebElement defaultgateway;
    @FindBy(css = "#ipv4specified")         WebElement dnsspecified;
    @FindBy(css = "#ipv4auto")              WebElement dnsauto;
    @FindBy(css = "#v4-preferred-dns")      WebElement preferreddns;
    @FindBy(css = "#v4-alternate-dns")      WebElement alternatedns;

    // ═══════════════════════════════════════════════
    // IP ADDRESS FILTERING
    // ═══════════════════════════════════════════════
    @FindBy(css = "#ipv4")                  WebElement ipfilterenable;
    @FindBy(css = "#ipv4-allow")            WebElement ipfilterallow;
    @FindBy(css = "#ipv4-deny")             WebElement ipfilterdeny;
    @FindBy(css = "#ipv4from")              WebElement ipv4from;
    @FindBy(css = "#ipv4to")               WebElement ipv4to;
    @FindBy(css = "#btnipv4add")            WebElement btnipv4add;

    // ═══════════════════════════════════════════════
    // NAS SETTINGS
    // ═══════════════════════════════════════════════
    @FindBy(css = "#enable")                WebElement nasenable;
    @FindBy(css = "#name")                  WebElement nasname;
    @FindBy(css = "#ip-address")            WebElement nasipaddress;
    @FindBy(css = "#user-name")             WebElement nasusername;
    @FindBy(css = "#password")              WebElement naspassword;
    @FindBy(css = "#file-format")           WebElement nasfileformat;
    @FindBy(css = "#folder-name")           WebElement nasfoldername;
    @FindBy(css = "#btntest")               WebElement btntest;

    // ═══════════════════════════════════════════════
    // PORTS
    // ═══════════════════════════════════════════════
    @FindBy(css = "#http-port")             WebElement httpport;
    @FindBy(css = "#rtsp-port")             WebElement rtspport;
    @FindBy(css = "#https-port")            WebElement httpsport;
    @FindBy(css = "#https-stream-port")     WebElement httpsstreamport;
    @FindBy(css = "#rtp-video-port")        WebElement rtpvideoport;
    @FindBy(css = "#rtcp-video-port")       WebElement rtcpvideoport;
    @FindBy(css = "#port-mapping")          WebElement portmapping;
    @FindBy(css = "#external-rtsp-port")    WebElement externalrtspport;

    // ═══════════════════════════════════════════════
    // MULTICAST
    // ═══════════════════════════════════════════════
    @FindBy(css = "#v4-multicast")          WebElement multicastenable;
    @FindBy(css = "#v4-address")            WebElement multicastaddress;
    @FindBy(css = "#v4-ttl")               WebElement multicastttl;

    // ═══════════════════════════════════════════════
    // BUTTONS
    // ═══════════════════════════════════════════════
    @FindBy(id = "btndefault")              WebElement btndefault;
    @FindBy(id = "btnsave")                WebElement btnsave;
    @FindBy(id = "btncancel")              WebElement btncancel;
    @FindBy(id = "btnyes")                 WebElement btnyes;

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
    public void clicknetworksetting()   {networksetting.click();    }
    public void clickbasicsettab()      { basicsettab.click();      }
    public void clickipaddresssetting() { ipaddresssetting.click(); }
    public void clickipfilter()         { ipfilter.click();         }
    public void clicknassetting()       { nassetting.click();       }
    public void clickports()            { ports.click();            }
    public void clickmulticast()        { multicast.click();        }

    // ═══════════════════════════════════════════════
    // HOST NAME
    // ═══════════════════════════════════════════════
    public void sethostname(String value) { settextbox(hostname, value); }
    public String gethostname()           { return hostname.getAttribute("value"); }

    // ═══════════════════════════════════════════════
    // IPv4 CONFIGURATION — dropdown
    // ═══════════════════════════════════════════════
    public void setipv4configuration(String value) {
        new Select(ipv4configuration).selectByVisibleText(value);
    }
    public String getipv4configuration() {
        return new Select(ipv4configuration).getFirstSelectedOption().getText();
    }

    // ═══════════════════════════════════════════════
    // IPv4 ADDRESS / SUBNET / GATEWAY
    // ═══════════════════════════════════════════════
    public void setipv4address(String value)    { settextbox(ipv4address,    value); }
    public String getipv4address()              { return ipv4address.getAttribute("value");    }

    public void setsubnetmask(String value)     { settextbox(subnetmask,     value); }
    public String getsubnetmask()               { return subnetmask.getAttribute("value");     }

    public void setdefaultgateway(String value) { settextbox(defaultgateway, value); }
    public String getdefaultgateway()           { return defaultgateway.getAttribute("value"); }

    // ═══════════════════════════════════════════════
    // DNS
    // ═══════════════════════════════════════════════
    public void selectdnsspecified()    { if (!dnsspecified.isSelected()) dnsspecified.click(); }
    public void selectdnsauto()         { if (!dnsauto.isSelected())      dnsauto.click();      }
    public boolean isdnsspecified()     { return dnsspecified.isSelected(); }
    public boolean isdnsauto()          { return dnsauto.isSelected();      }

    public void setpreferreddns(String value)   { settextbox(preferreddns, value); }
    public String getpreferreddns()             { return preferreddns.getAttribute("value"); }

    public void setalternatedns(String value)   { settextbox(alternatedns, value); }
    public String getalternatedns()             { return alternatedns.getAttribute("value"); }

    // ═══════════════════════════════════════════════
    // IP FILTER
    // ═══════════════════════════════════════════════
    public void enableipfilter()        { setcheckbox(ipfilterenable, true);  }
    public void disableipfilter()       { setcheckbox(ipfilterenable, false); }
    public boolean isipfilterenabled()  { return ipfilterenable.isSelected(); }

    public void selectfilterallow()     { if (!ipfilterallow.isSelected()) ipfilterallow.click(); }
    public void selectfilterdeny()      { if (!ipfilterdeny.isSelected())  ipfilterdeny.click();  }
    public boolean isfilterallow()      { return ipfilterallow.isSelected(); }
    public boolean isfilterdeny()       { return ipfilterdeny.isSelected();  }

    public void setipv4from(String value) { settextbox(ipv4from, value); }
    public String getipv4from()           { return ipv4from.getAttribute("value"); }

    public void setipv4to(String value)   { settextbox(ipv4to, value); }
    public String getipv4to()             { return ipv4to.getAttribute("value"); }

    public void clickaddipfilter()        { btnipv4add.click(); }

    // ═══════════════════════════════════════════════
    // NAS
    // ═══════════════════════════════════════════════
    public void enablenas()             { setcheckbox(nasenable, true);  }
    public void disablenas()            { setcheckbox(nasenable, false); }
    public boolean isnasdenabled()      { return nasenable.isSelected(); }

    public void setnasname(String value)       { settextbox(nasname,       value); }
    public String getnasname()                 { return nasname.getAttribute("value");       }

    public void setnasipaddress(String value)  { settextbox(nasipaddress,  value); }
    public String getnasipaddress()            { return nasipaddress.getAttribute("value");  }

    public void setnasusername(String value)   { settextbox(nasusername,   value); }
    public String getnasusername()             { return nasusername.getAttribute("value");   }

    public void setnaspassword(String value)   { settextbox(naspassword,   value); }
    public String getnaspassword()             { return naspassword.getAttribute("value");   }

    public void setnasfileformat(String value) {
        new Select(nasfileformat).selectByVisibleText(value);
    }
    public String getnasfileformat() {
        return new Select(nasfileformat).getFirstSelectedOption().getText();
    }

    public void setnasfoldername(String value) { settextbox(nasfoldername, value); }
    public String getnasfoldername()           { return nasfoldername.getAttribute("value"); }

    public void clicktestconnection()          { btntest.click(); }

    public String getvalidationmessage()          {return  validationmessage.getText();}

    // ═══════════════════════════════════════════════
    // PORTS
    // ═══════════════════════════════════════════════
    public void sethttpport(String value)         { settextbox(httpport,        value); }
    public String gethttpport()                   { return httpport.getAttribute("value");        }

    public void setrtspport(String value)         { settextbox(rtspport,        value); }
    public String getrtspport()                   { return rtspport.getAttribute("value");        }

    public void sethttpsport(String value)        { settextbox(httpsport,       value); }
    public String gethttpsport()                  { return httpsport.getAttribute("value");       }

    public void sethttpsstreamport(String value)  { settextbox(httpsstreamport, value); }
    public String gethttpsstreamport()            { return httpsstreamport.getAttribute("value"); }

    public void setrtpvideoport(String value)     { settextbox(rtpvideoport,    value); }
    public String getrtpvideoport()               { return rtpvideoport.getAttribute("value");    }

    public void setrtcpvideoport(String value)    { settextbox(rtcpvideoport,   value); }
    public String getrtcpvideoport()              { return rtcpvideoport.getAttribute("value");   }

    public void enableportmapping()               { setcheckbox(portmapping, true);  }
    public void disableportmapping()              { setcheckbox(portmapping, false); }
    public boolean isportmappingenabled()         { return portmapping.isSelected(); }

    public void setexternalrtspport(String value) { settextbox(externalrtspport, value); }
    public String getexternalrtspport()           { return externalrtspport.getAttribute("value"); }

    // ═══════════════════════════════════════════════
    // MULTICAST
    // ═══════════════════════════════════════════════
    public void enablemulticast()                 { setcheckbox(multicastenable, true);  }
    public void disablemulticast()                { setcheckbox(multicastenable, false); }
    public boolean ismulticastenabled()           { return multicastenable.isSelected(); }

    public void setmulticastaddress(String value) { settextbox(multicastaddress, value); }
    public String getmulticastaddress()           { return multicastaddress.getAttribute("value"); }

    public void setmulticastttl(String value)     { settextbox(multicastttl, value); }
    public String getmulticastttl()               { return multicastttl.getAttribute("value"); }

    // ═══════════════════════════════════════════════
    // BUTTONS
    // ═══════════════════════════════════════════════
    public void clickdefault()  { btndefault.click(); }
    public void clicksave()     { btnsave.click();    }
    public void clickcancel()   { btncancel.click();  }
    public void clickyes()      { btnyes.click();     }

    public void clickdefaultandconfirm() {
        clickdefault();
        switchtoparentframe();
        clickyes();
        switchtoconfigframe();
    }
}
