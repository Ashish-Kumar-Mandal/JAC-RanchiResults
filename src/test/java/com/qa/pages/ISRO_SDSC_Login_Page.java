package com.qa.pages;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class ISRO_SDSC_Login_Page {
	WebDriver driver;

	@FindBy(id="userid")
	WebElement userId;
	
	public void setUserId(String data) {
		userId.clear();
		userId.sendKeys(data);
	}
	
	@FindBy(id="confData")
	WebElement password;
	
	public void setPassword(String data) {
		password.clear();
		password.sendKeys(data);
	}
	
	@FindBy(id="captchaService_answer")
	WebElement captchaService_answer;
	
	public void setCaptcha(String data) {
		captchaService_answer.clear();
		captchaService_answer.sendKeys(data);
	}
	
	@FindBy(id="loginCaptchaHolder_captchaService_image")
	WebElement captchaImg;
	
	public String getCaptchaValue() throws TesseractException, InterruptedException, IOException {
		
		File src = captchaImg.getScreenshotAs(OutputType.FILE);
		File imageFile = new File(System.getProperty("user.dir")+"\\CaptchaShots\\captcha.png");
		FileHandler.copy(src, imageFile);
		
        ITesseract instance = new Tesseract();
        Thread.sleep(2000);
        instance.setDatapath(System.getProperty("user.dir")+"\\tessdata");

        String result = instance.doOCR(imageFile);
//        String captchaValue = result.replaceAll("[^a-zA-Z0-9]", "");
        
		return result;
	}
	
	@FindBy(xpath="//*[@id=\"log\"]/div[3]/div[2]/div/div/a")
	WebElement login;
	
	public void goLogin() {
		login.click();
	}
	
	public ISRO_SDSC_Login_Page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
