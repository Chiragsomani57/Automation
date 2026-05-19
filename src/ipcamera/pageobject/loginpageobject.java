package ipcamera.pageobject;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class loginpageobject  {
	
	WebDriver driver;
	String ipaddress;
	String loginuser;
	String loginpassword;


	public loginpageobject(WebDriver driver) throws IOException
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(id="username")
	WebElement username;
	
	@FindBy(id="password")
	WebElement password;
	
	@FindBy(id="btnlogin")
	WebElement loginbtn;
	
	@FindBy(className="msg-space")
	WebElement  errormsg;
	
	public void logintocamera()
	{
		username.sendKeys(loginuser);
		password.sendKeys(loginpassword);
		loginbtn.click();
	}
	
	public void login(String user,String passcode)
	{
		username.sendKeys(user);
		password.sendKeys(passcode);
		loginbtn.click();
	}
	
	
	
	public String geterrormsg()
	{
		return errormsg.getText();
	}
	
	public String getindexurl() throws IOException
	{
		Properties  pro=new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + 
			    "/src/ipcamera/resources/globaldata.properties");
		pro.load(fis);
		ipaddress=System.getProperty("ipaddress")!=null ? System.getProperty("ipaddress"):pro.getProperty("ipaddress");
		String indexurl="http://"+ ipaddress+"/html/index.html";
		loginuser=System.getProperty("username")!=null ? System.getProperty("username"):pro.getProperty("username");
		loginpassword=System.getProperty("password")!=null ? System.getProperty("password"):pro.getProperty("password");
		return indexurl;
	}
	
	public String getmainurl() 
	{
	
		String mainurl="http://"+ipaddress+"/html/main.html";
		return mainurl;
	}
	
	
}
