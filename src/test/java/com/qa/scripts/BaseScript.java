package com.qa.scripts;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.qa.pages.JAC_10th_ResultPage;
import com.qa.pages.JAC_12th_ResultPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseScript {

	WebDriver driver;
	FileInputStream fileLoc;
	
	JAC_10th_ResultPage jac;
	JAC_12th_ResultPage jac12;
	
	@Parameters({"Browser", "url"})
	@BeforeClass
	public void setUp(String Browser, String url) throws IOException {	
		
		if(Browser.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions opt = new ChromeOptions();
			opt.addArguments("--disable-notifications");
			driver = new ChromeDriver(opt);			
		}else if(Browser.equalsIgnoreCase("Edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(url);		
		
		jac = new JAC_10th_ResultPage(driver);
		jac12 = new JAC_12th_ResultPage(driver);
	}
		
	@AfterClass
	public void tearDown() throws InterruptedException {
		driver.quit();
	}
	
	public static String capitalizeWord(String str){  
	    String words[]=str.split("\\s");  
	    String capitalizeWord="";  
	    for(String w:words){  
	        String first=w.substring(0,1);  
	        String afterfirst=w.substring(1);  
	        capitalizeWord+=first.toUpperCase()+afterfirst.toLowerCase()+" ";  
	    }  
	    return capitalizeWord.trim();  
	} 
}
