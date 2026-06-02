package ipcamera.testcomponent; 

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import ipcamera.pageobject.loginpageobject;


public class basetest {
	
	public WebDriver driver;
	public loginpageobject loginpage;
	

	public WebDriver intializedriver() throws IOException
	
	{	
		Properties  pro=new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + 
			    "/src/ipcamera/resources/globaldata.properties");
		pro.load(fis);

	
		String browsername=System.getProperty("browser")!=null ? System.getProperty("browser"):pro.getProperty("browser");

		
		
		if (browsername.equals("chrome"))
			{
			
				WebDriverManager.chromedriver().setup();
				 driver=new ChromeDriver();
					
			}
		
		else if(browsername.equals("firefox"))
		{
//			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		
		}
	
		driver.manage().window().maximize();
		return driver;

	}
	//read data from the json file
	public List<HashMap<String, String>> getjsondata(String filepath) throws IOException {
		
		String jsoncontent=FileUtils.readFileToString(new File(filepath),StandardCharsets.UTF_8);
				
		//String to hasmap by jackson databid
				
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String, String>> data=mapper.readValue(jsoncontent, new TypeReference<List<HashMap<String,String>>>(){});
		return data;
	}
	
	
	
	@BeforeMethod(alwaysRun=true)
	public loginpageobject launchapplication() throws IOException
	{
		driver=intializedriver();
		loginpage=new loginpageobject(driver);
		driver.get(loginpage.getindexurl());
		return loginpage;
		
	}
	
	@AfterMethod(alwaysRun = true)
	public void teardown() {
	    if (driver != null) {
	        driver.quit();
	    }
	}
	
	public  String getscreenshot(String testcasename) throws IOException
	{
		
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File dest=new File(System.getProperty("user.dir")+"//reports//"+ testcasename+".png");
		 String filepath = System.getProperty("user.dir") +
                 "\\reports\\" + testcasename + ".png";

		 FileUtils.copyFile(source, new File(filepath));

		 // ✅ return full path with filename — not just folder
		 return filepath;
		
	}
	
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
	
	public void switchtodefaultcontent()

	{
		driver.switchTo().defaultContent();
	}
		
	public void switchtoparentframe()

	{
		driver.switchTo().parentFrame();
	}
		
	
	public HashMap<String, String> getcsvdata(String filepath,
			String testcasename,
			String cameratype) throws IOException {

		Reader reader = new InputStreamReader(
				new FileInputStream(filepath), StandardCharsets.UTF_8);

		CSVParser csvparser = new CSVParser(reader,
				CSVFormat.DEFAULT.withFirstRecordAsHeader().withTrim());

		for (CSVRecord record : csvparser) {
			String tc  = record.get("testcase").trim();
			String cam = record.get("cameratype").trim();

			// ✅ handles all options
			boolean cammatches = false;

			if (cam.equals("all")) {
				// Option 3 — all cameras
				cammatches = true;

			} else if (cam.contains("|")) {
				// Option 2 — OR condition
				for (String c : cam.split("\\|")) {
					if (c.trim().equals(cameratype)) {
						cammatches = true;
						break;
					}
				}

			} else {
				// Option 1 & 4 — exact match
				cammatches = cam.equals(cameratype);
			}

			if (tc.equals(testcasename) && cammatches) {
				HashMap<String, String> data = new HashMap<>();
				record.toMap().forEach((k, v) ->
				data.put(k.trim(), v.trim()));
				csvparser.close();
				return data;
			}
		}
		csvparser.close();
		return null;
	}

	//─── get camera type from properties or command line ─
	public String getcameratype() throws IOException {
		Properties pro = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") +
				"/src/ipcamera/resources/globaldata.properties");
		pro.load(fis);
		return System.getProperty("cameratype") != null ?
				System.getProperty("cameratype") :
					pro.getProperty("cameratype");
	}
	
}