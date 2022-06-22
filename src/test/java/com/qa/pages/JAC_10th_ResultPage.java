package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class JAC_10th_ResultPage {
	
	WebDriver driver;
	
	public void goBack() {
		driver.navigate().back();
	}
		
	@FindBy(name="rollcode")
	WebElement rollcode;
	
	public void setRollCode(String data) {
		rollcode.clear();
		rollcode.sendKeys(data);
	}
	
	@FindBy(name="rollno")
	WebElement rollno;
	
	public void setRollNumber(String data) {
		rollno.clear();
		rollno.sendKeys(data);
	}
	
	@FindBy(name="B1")
	WebElement B1;
	public void clickSubmit() {
		B1.click();
	}
		
	@FindBy(xpath="/html/body/form/div/div/div[1]/table/tbody/tr[2]/td[2]")
	WebElement rollNumber;	
	public String getRollNumber() {
		String rn = rollNumber.getText();
		return rn;
	}		
				   
	@FindBy(xpath="/html/body/form/div/div/div[1]/table/tbody/tr[3]/td[2]")
	WebElement name;	
	public String getName() {
		String n = name.getText();
		return n;
	}
	
	@FindBy(xpath="/html/body/form/div/div/div[1]/table/tbody/tr[4]/td[2]")
	WebElement fName;
	public String getFatherName() {
		String fn = fName.getText();
		return fn;
	}
	
	@FindBy(xpath="/html/body/form/div/div/div[1]/table/tbody/tr[5]/td[2]")
	WebElement mName;
	public String getMotherName() {
		String mn = mName.getText();
		return mn;
	}

	@FindBy(xpath="/html/body/form/div/div/div[1]/table/tbody/tr[6]/td[2]")
	WebElement collegeName;
	public String getCollegeName() {
		String cn = collegeName.getText();
		return cn;
	}
	
	@FindBy(xpath="/html/body/form/div/div/div[2]/table/tbody/tr[8]/td[2]")
	WebElement totalMarks;
	public String getTotalMarks() {
		String tm = totalMarks.getText();
		return tm;
	}
	
	@FindBy(xpath="/html/body/form/div/div/div[2]/table/tbody/tr[9]/td[2]")
	WebElement resultStatus;
	public String getResultStatus() {
		String rs = resultStatus.getText();
		return rs;
	}
	
	@FindBy(xpath="/html/body/form/div/div/div[2]/table/tbody/tr[10]/td[2]")
	WebElement percentage;	
	public String getPercentage() {
		String percent = percentage.getText();
		return percent;
	}
	
	public JAC_10th_ResultPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
