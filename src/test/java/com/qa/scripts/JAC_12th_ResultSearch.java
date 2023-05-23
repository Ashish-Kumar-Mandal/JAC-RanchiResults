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

public class JAC_12th_ResultSearch extends BaseScript {

	static int i = 0;
	static int rollNo = 0;
	
	public void searchResult() throws IOException {
		rollNo = Integer.parseInt(super.rollNumber);
		int rollNumberLength = super.rollNumber.length();

		while(true) {
			++i;			
			String roll_number = String.format("%0"+rollNumberLength+"d", rollNo);
			rollNo = rollNo + 1;
			
			jac12.setRollCode(super.rollCode);
			jac12.setRollNumber(roll_number);
			jac12.clickSubmit();
			
			if(jac12.getRollNumber() == null) {
				break;
			}
			
			if (jac12.totalNumberOfRows() == 8) {
				String result = "Roll Number: "+jac12.getRollNumber()+"\nName: "+capitalizeWord(jac12.getName())+"\nFather Name: "+capitalizeWord(jac12.getFatherName())+"\nMother Name: "+capitalizeWord(jac12.getMotherName())+"\nCollege Nmae: "+capitalizeWord(jac12.getCollegeName())+"\nTotal Marks: "+jac12.getTotalMarks()+"\nResult: "+jac12.getResultStatus();
				System.out.println(result);
			} else if (jac12.totalNumberOfRows() == 9) {
				String result = "Roll Number: "+jac12.getRollNumber()+"\nName: "+capitalizeWord(jac12.getName())+"\nFather Name: "+capitalizeWord(jac12.getFatherName())+"\nMother Name: "+capitalizeWord(jac12.getMotherName())+"\nCollege Nmae: "+capitalizeWord(jac12.getCollegeName())+"\nTotal Marks: "+jac12.getTotalMarksExtra()+"\nResult: "+jac12.getResultStatusExtra();
				System.out.println(result);
			} else {
				String result = "Roll Number: "+jac12.getRollNumber()+"\nName: "+capitalizeWord(jac12.getName())+"\nFather Name: "+capitalizeWord(jac12.getFatherName())+"\nMother Name: "+capitalizeWord(jac12.getMotherName())+"\nCollege Nmae: "+capitalizeWord(jac12.getCollegeName())+"\nTotal Marks: 0\nResult: F/A";
				System.out.println(result);
			}		
			System.out.println("-------------------------------------------");
	
			File file = new File(System.getProperty("user.dir") + "\\src\\test\\java\\com\\qa\\testdata\\JAC_12th_Results.xlsx");
			FileInputStream fis = new FileInputStream(file);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet("Temp");
	
			sheet.createRow(i);
			sheet.getRow(i).createCell(0).setCellValue(jac12.getRollNumber());
			sheet.getRow(i).createCell(1).setCellValue(capitalizeWord(jac12.getName()));
			sheet.getRow(i).createCell(2).setCellValue(capitalizeWord(jac12.getFatherName()));
			sheet.getRow(i).createCell(3).setCellValue(capitalizeWord(jac12.getMotherName()));
			sheet.getRow(i).createCell(4).setCellValue(capitalizeWord(jac12.getCollegeName()));
			
			if (jac12.totalNumberOfRows() == 8) {
				sheet.getRow(i).createCell(5).setCellValue(jac12.getTotalMarks());
				sheet.getRow(i).createCell(6).setCellValue(jac12.getResultStatus());
			} else if(jac12.totalNumberOfRows() == 9){
				sheet.getRow(i).createCell(5).setCellValue(jac12.getTotalMarksExtra());
				sheet.getRow(i).createCell(6).setCellValue(jac12.getResultStatusExtra());
			} else {
				sheet.getRow(i).createCell(5).setCellValue(0);
				sheet.getRow(i).createCell(6).setCellValue("F/A");
			}
			
			FileOutputStream fos = new FileOutputStream(file);
			workbook.write(fos);
			fos.close();
			workbook.close();
	
			jac12.goBack();
		}
	}
}
