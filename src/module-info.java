/**
 * 
 */
/**
 * 
 */
module Framework {
	exports test;                    // Export the package
	opens test to org.testng;
    opens ipcamera.testcomponent to org.testng;
    opens ipcamera.pageobject to org.seleniumhq.selenium.support;
	requires org.seleniumhq.selenium.api;
	requires org.seleniumhq.selenium.chrome_driver;
	requires io.github.bonigarcia.webdrivermanager;
	requires org.seleniumhq.selenium.firefox_driver;
	requires org.testng;
	requires org.seleniumhq.selenium.support;
	requires org.apache.commons.io;
	requires com.fasterxml.jackson.databind;
	requires com.fasterxml.jackson.core;
	requires extentreports;
	requires org.apache.commons.csv;
}