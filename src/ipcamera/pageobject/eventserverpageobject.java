package ipcamera.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class eventserverpageobject {

    WebDriver driver;

    public eventserverpageobject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // ═══════════════════════════════════════════════
    // NAVIGATION
    // ═══════════════════════════════════════════════
    @FindBy(id = "configuration")   WebElement configuration;
    @FindBy(id = "event")           WebElement event;
    @FindBy(id = "eventsrv")        WebElement eventserver;
    @FindBy(id = "ftp")             WebElement ftptab;
    @FindBy(id = "smtp")            WebElement emailtab;
    @FindBy(id = "sms")             WebElement smstab;
    @FindBy(id = "tcp")             WebElement tcptab;
    @FindBy(id = "snmp")            WebElement snmptab;
    @FindBy(css = "a[name='snmp-config']") WebElement snmpsettinglink;
    @FindBy(css = "a[name='snmp-notif']")  WebElement snmpnotificationlink;

    // ═══════════════════════════════════════════════
    // FTP
    // ═══════════════════════════════════════════════
    @FindBy(css = "#enable")          WebElement ftpenable;
    @FindBy(css = "#server-address")  WebElement ftpserveraddress;
    @FindBy(css = "#port")            WebElement ftpport;
    @FindBy(css = "#user-name")       WebElement ftpusername;
    @FindBy(css = "#password")        WebElement ftppassword;
    @FindBy(css = "#folder-name")     WebElement ftpfoldername;
    @FindBy(css = "#btntest")         WebElement ftpbtntest;

    // ═══════════════════════════════════════════════
    // EMAIL
    // ═══════════════════════════════════════════════
    @FindBy(css = "#enable")                     WebElement emailenable;
    @FindBy(css = "#server-address")             WebElement emailserveraddress;
    @FindBy(css = "#port")                       WebElement emailport;
    @FindBy(css = "#user-name")                  WebElement emailusername;
    @FindBy(css = "#password")                   WebElement emailpassword;
    @FindBy(css = "#sender-email-id")            WebElement senderemailid;
    @FindBy(css = "#encryption")                 WebElement encryption;
    @FindBy(css = "#receivermail")                WebElement receivermail;
    @FindBy(css = "#mail-transmission-timeout")  WebElement mailtransmissiontimeout;
    @FindBy(css = "#btntest")                    WebElement emailbtntest;

    // ═══════════════════════════════════════════════
    // SMS
    // ═══════════════════════════════════════════════
    @FindBy(css = "#enable")            WebElement smsenable;
    @FindBy(css = "#service-provider")  WebElement serviceprovider;
    @FindBy(css = "#user-name")         WebElement smsusername;
    @FindBy(css = "#password")          WebElement smspassword;
    @FindBy(css = "#sender-id")         WebElement senderid;
    @FindBy(css = "#flash-message")     WebElement flashmessage;
    @FindBy(css = "#btnchkbal")         WebElement btnchkbal;
    @FindBy(css = "#mobileno")          WebElement mobileno;
    @FindBy(css = "#btntest")           WebElement smsbtntest;

    // ═══════════════════════════════════════════════
    // TCP
    // ═══════════════════════════════════════════════
    @FindBy(css = "#enable")          WebElement tcpenable;
    @FindBy(css = "#server-address")  WebElement tcpserveraddress;
    @FindBy(css = "#port")            WebElement tcpport;
    @FindBy(css = "#btntest")         WebElement tcpbtntest;

    // ═══════════════════════════════════════════════
    // SNMP — Setting tab
    // ═══════════════════════════════════════════════
    @FindBy(css = "#snmp-enable")    WebElement snmpenable;
    @FindBy(css = "#snmp-port")      WebElement snmpport;
    @FindBy(css = "#version-index")  WebElement snmpversion;
    @FindBy(css = "#sysname")        WebElement sysname;
    @FindBy(css = "#syscontact")     WebElement syscontact;
    @FindBy(css = "#syslocation")    WebElement syslocation;
    @FindBy(css = "#access-type")    WebElement accesstype;
    @FindBy(css = "#comname")        WebElement comname;
    @FindBy(css = "#dwbtn")          WebElement downloadmibbtn;

    // ═══════════════════════════════════════════════
    // SNMP — Notification tab
    // ═══════════════════════════════════════════════
    @FindBy(css = "#trapenable")        WebElement trapenable;
    @FindBy(css = "#trap-notif")        WebElement trapnotif;
    @FindBy(css = "#trap-ipaddr")       WebElement trapipaddr;
    @FindBy(css = "#trap-port")         WebElement trapport;
    @FindBy(css = "#retry-attempt")     WebElement retryattempt;
    @FindBy(css = "#retry-interval")    WebElement retryinterval;
    @FindBy(css = "#btntest")           WebElement snmpnotifbtntest;

    // ═══════════════════════════════════════════════
    // BUTTONS
    // ═══════════════════════════════════════════════
    @FindBy(id = "btndefault")  WebElement btndefault;
    @FindBy(id = "btnsave")     WebElement btnsave;
    @FindBy(id = "btncancel")   WebElement btncancel;
    @FindBy(id = "btnyes")      WebElement btnyes;

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
    public void clickconfiguration()       { configuration.click();       }
    public void clickevent()               { event.click();       }
    public void clickeventserver()         { eventserver.click();       }
    public void clickftptab()              { ftptab.click();              }
    public void clickemailtab()            { emailtab.click();            }
    public void clicksmstab()              { smstab.click();              }
    public void clicktcptab()              { tcptab.click();              }
    public void clicksnmptab()             { snmptab.click();             }
    public void clicksnmpsettinglink()     { snmpsettinglink.click();     }
    public void clicksnmpnotificationlink(){ snmpnotificationlink.click();}

    // ═══════════════════════════════════════════════
    // FTP — setters / getters
    // ═══════════════════════════════════════════════
    public void enableftp()                { setcheckbox(ftpenable, true);  }
    public void disableftp()               { setcheckbox(ftpenable, false); }
    public boolean isftpenabled()          { return ftpenable.isSelected(); }

    public void setftpserveraddress(String value) { settextbox(ftpserveraddress, value); }
    public String getftpserveraddress()           { return ftpserveraddress.getAttribute("value"); }

    public void setftpport(String value)   { settextbox(ftpport, value); }
    public String getftpport()             { return ftpport.getAttribute("value"); }

    public void setftpusername(String value) { settextbox(ftpusername, value); }
    public String getftpusername()           { return ftpusername.getAttribute("value"); }

    public void setftppassword(String value) { settextbox(ftppassword, value); }
    public String getftppassword()           { return ftppassword.getAttribute("value"); }

    public void setftpfoldername(String value) { settextbox(ftpfoldername, value); }
    public String getftpfoldername()            { return ftpfoldername.getAttribute("value"); }

    public void clickftptestconnection()   { ftpbtntest.click(); }

    // ═══════════════════════════════════════════════
    // EMAIL — setters / getters
    // ═══════════════════════════════════════════════
    public void enableemail()              { setcheckbox(emailenable, true);  }
    public void disableemail()             { setcheckbox(emailenable, false); }
    public boolean isemailenabled()        { return emailenable.isSelected(); }

    public void setemailserveraddress(String value) { settextbox(emailserveraddress, value); }
    public String getemailserveraddress()            { return emailserveraddress.getAttribute("value"); }

    public void setemailport(String value) { settextbox(emailport, value); }
    public String getemailport()           { return emailport.getAttribute("value"); }

    public void setemailusername(String value) { settextbox(emailusername, value); }
    public String getemailusername()            { return emailusername.getAttribute("value"); }

    public void setemailpassword(String value) { settextbox(emailpassword, value); }
    public String getemailpassword()            { return emailpassword.getAttribute("value"); }

    public void setsenderemailid(String value) { settextbox(senderemailid, value); }
    public String getsenderemailid()            { return senderemailid.getAttribute("value"); }

    public void setencryption(String value) { new Select(encryption).selectByVisibleText(value); }
    public String getencryption() { return new Select(encryption).getFirstSelectedOption().getText(); }

    public void setreceivermail(String value) { settextbox(receivermail, value); }
    public String getreceivermail()            { return receivermail.getAttribute("value"); }

    public void setmailtransmissiontimeout(String value) { settextbox(mailtransmissiontimeout, value); }
    public String getmailtransmissiontimeout()            { return mailtransmissiontimeout.getAttribute("value"); }

    public void clickemailtestconnection() { emailbtntest.click(); }

    // ═══════════════════════════════════════════════
    // SMS — setters / getters
    // ═══════════════════════════════════════════════
    public void enablesms()                { setcheckbox(smsenable, true);  }
    public void disablesms()               { setcheckbox(smsenable, false); }
    public boolean issmsenabled()          { return smsenable.isSelected(); }

    public void setserviceprovider(String value) { new Select(serviceprovider).selectByVisibleText(value); }
    public String getserviceprovider() { return new Select(serviceprovider).getFirstSelectedOption().getText(); }

    public void setsmsusername(String value) { settextbox(smsusername, value); }
    public String getsmsusername()            { return smsusername.getAttribute("value"); }

    public void setsmspassword(String value) { settextbox(smspassword, value); }
    public String getsmspassword()            { return smspassword.getAttribute("value"); }

    public void setsenderid(String value)  { settextbox(senderid, value); }
    public String getsenderid()            { return senderid.getAttribute("value"); }

    public void enableflashmessage()       { setcheckbox(flashmessage, true);  }
    public void disableflashmessage()      { setcheckbox(flashmessage, false); }
    public boolean isflashmessageenabled() { return flashmessage.isSelected(); }

    public void clickcheckbalance()        { btnchkbal.click(); }

    public void setmobileno(String value)  { settextbox(mobileno, value); }
    public String getmobileno()            { return mobileno.getAttribute("value"); }

    public void clicksmstestconnection()   { smsbtntest.click(); }

    // ═══════════════════════════════════════════════
    // TCP — setters / getters
    // ═══════════════════════════════════════════════
    public void enabletcp()                { setcheckbox(tcpenable, true);  }
    public void disabletcp()               { setcheckbox(tcpenable, false); }
    public boolean istcpenabled()          { return tcpenable.isSelected(); }

    public void settcpserveraddress(String value) { settextbox(tcpserveraddress, value); }
    public String gettcpserveraddress()            { return tcpserveraddress.getAttribute("value"); }

    public void settcpport(String value)   { settextbox(tcpport, value); }
    public String gettcpport()             { return tcpport.getAttribute("value"); }

    public void clicktcptestconnection()   { tcpbtntest.click(); }

    // ═══════════════════════════════════════════════
    // SNMP — Setting tab — setters / getters
    // ═══════════════════════════════════════════════
    public void enablesnmp()               { setcheckbox(snmpenable, true);  }
    public void disablesnmp()              { setcheckbox(snmpenable, false); }
    public boolean issnmpenabled()         { return snmpenable.isSelected(); }

    public void setsnmpport(String value)  { settextbox(snmpport, value); }
    public String getsnmpport()            { return snmpport.getAttribute("value"); }

    public void setsnmpversion(String value) { new Select(snmpversion).selectByVisibleText(value); }
    public String getsnmpversion() { return new Select(snmpversion).getFirstSelectedOption().getText(); }

    public void setsysname(String value)   { settextbox(sysname, value); }
    public String getsysname()             { return sysname.getAttribute("value"); }

    public void setsyscontact(String value){ settextbox(syscontact, value); }
    public String getsyscontact()          { return syscontact.getAttribute("value"); }

    public void setsyslocation(String value){ settextbox(syslocation, value); }
    public String getsyslocation()          { return syslocation.getAttribute("value"); }

    public void setaccesstype(String value) { new Select(accesstype).selectByVisibleText(value); }
    public String getaccesstype() { return new Select(accesstype).getFirstSelectedOption().getText(); }

    public void setcomname(String value)   { settextbox(comname, value); }
    public String getcomname()             { return comname.getAttribute("value"); }

    public void clickdownloadmib()         { downloadmibbtn.click(); }

    // ═══════════════════════════════════════════════
    // SNMP — Notification tab — setters / getters
    // ═══════════════════════════════════════════════
    public void enabletrap()               { setcheckbox(trapenable, true);  }
    public void disabletrap()              { setcheckbox(trapenable, false); }
    public boolean istrapenabled()         { return trapenable.isSelected(); }

    public String gettrapnotif()           { return trapnotif.getAttribute("value"); }

    public void settrapipaddr(String value){ settextbox(trapipaddr, value); }
    public String gettrapipaddr()          { return trapipaddr.getAttribute("value"); }

    public void settrapport(String value)  { settextbox(trapport, value); }
    public String gettrapport()            { return trapport.getAttribute("value"); }

    public void setretryattempt(String value) { settextbox(retryattempt, value); }
    public String getretryattempt()            { return retryattempt.getAttribute("value"); }

    public void setretryinterval(String value) { settextbox(retryinterval, value); }
    public String getretryinterval()            { return retryinterval.getAttribute("value"); }

    public void clicksnmpnotiftestconnection() { snmpnotifbtntest.click(); }

    // ═══════════════════════════════════════════════
    // TEST CONNECTION RESULT MESSAGE
    // ═══════════════════════════════════════════════
    @FindBy(className = "msg-space") WebElement connectionmessage;
    public String getconnectionmessage() { return connectionmessage.getText(); }

    // ═══════════════════════════════════════════════
    // BUTTONS
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
