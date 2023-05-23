package com.qa.scripts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.qa.utility.ExcelUtility;

public class JAC_10th_ResultSearch extends BaseScript {	
	static int i = 0;
	static int rollNo = 0;
		
	@Test
	public void searchResult() throws IOException {
		rollNo = Integer.parseInt(super.rollNumber);
		int rollNumberLength = super.rollNumber.length();
		
		while(true) {
			++i;			
			String roll_number = String.format("%0"+rollNumberLength+"d", rollNo);
			rollNo = rollNo + 1;		
			
			jac.setRollCode(super.rollCode);
			jac.setRollNumber(roll_number);
			jac.clickSubmit();
			
			if(jac.getRollNumber() == null) {
				break;
			}
			
			String result = "Roll Number: "+jac.getRollNumber()+"\nName: "+capitalizeWord(jac.getName())+"\nFather Name: "+capitalizeWord(jac.getFatherName())+"\nMother Name: "+capitalizeWord(jac.getMotherName())+"\nCollege Nmae: "+capitalizeWord(jac.getCollegeName())+"\nTotal Marks: "+jac.getTotalMarks()+"\nResult: "+jac.getResultStatus()+"\nPercentage: "+jac.getPercentage();
			System.out.println(result);		
			System.out.println("-------------------------------------------");
					
			File file = new File(System.getProperty("user.dir")+"\\src\\test\\java\\com\\qa\\testdata\\JAC_10th_Results.xlsx");
			FileInputStream fis = new FileInputStream(file);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet("Temp");
			
			sheet.createRow(i);
			sheet.getRow(i).createCell(0).setCellValue(jac.getRollNumber());
			sheet.getRow(i).createCell(1).setCellValue(capitalizeWord(jac.getName()));
			sheet.getRow(i).createCell(2).setCellValue(capitalizeWord(jac.getFatherName()));
			sheet.getRow(i).createCell(3).setCellValue(capitalizeWord(jac.getMotherName()));
			sheet.getRow(i).createCell(4).setCellValue(capitalizeWord(jac.getCollegeName()));
			sheet.getRow(i).createCell(5).setCellValue(jac.getTotalMarks());
			sheet.getRow(i).createCell(6).setCellValue(jac.getResultStatus());
			sheet.getRow(i).createCell(7).setCellValue(jac.getPercentage());
			
			FileOutputStream fos = new FileOutputStream(file);
			workbook.write(fos);
			fos.close();
			workbook.close();
			
			jac.goBack();
		}
	}	
}
