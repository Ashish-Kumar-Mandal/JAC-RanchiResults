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

	@Test(dataProvider = "getData")
	public void searchResult(String rollCode, String rollNumber) throws IOException {

		++i;
		jac12.setRollCode(rollCode);
		jac12.setRollNumber(rollNumber);
		jac12.clickSubmit();
		
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
		XSSFSheet sheet = workbook.getSheetAt(0);

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

	@DataProvider
	public String[][] getData() throws IOException {
		String xFile = System.getProperty("user.dir") + "\\src\\test\\java\\com\\qa\\testdata\\JAC_12th_RollNumbers.xlsx";
		String xSheetName = "Sheet1";

		int rowCount = ExcelUtility.getRowCount(xFile, xSheetName);
		int cellCount = ExcelUtility.getCellCount(xFile, xSheetName, rowCount);

		String[][] data = new String[rowCount][cellCount];

		for (int i = 1; i <= rowCount; i++) {
			for (int j = 0; j < cellCount; j++) {
				data[i - 1][j] = ExcelUtility.getCellData(xFile, xSheetName, i, j);
			}
		}

		return data;
	}

}
