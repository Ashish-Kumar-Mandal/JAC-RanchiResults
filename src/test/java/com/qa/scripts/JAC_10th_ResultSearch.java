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
		
	@Test(dataProvider="getData")
	public void searchResult(String rollCode, String rollNumber) throws IOException {
		++i;
		jac.setRollCode(rollCode);
		jac.setRollNumber(rollNumber);
		jac.clickSubmit();
		
		String result = "Roll Number: "+jac.getRollNumber()+"\nName: "+capitalizeWord(jac.getName())+"\nFather Name: "+capitalizeWord(jac.getFatherName())+"\nMother Name: "+capitalizeWord(jac.getMotherName())+"\nCollege Nmae: "+capitalizeWord(jac.getCollegeName())+"\nTotal Marks: "+jac.getTotalMarks()+"\nResult: "+jac.getResultStatus()+"\nPercentage: "+jac.getPercentage();
		System.out.println(result);		
		System.out.println("-------------------------------------------");
				
		File file = new File(System.getProperty("user.dir")+"\\src\\test\\java\\com\\qa\\testdata\\JAC_10th_Results.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("GCHSB");
		
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
		
	@DataProvider
	public String[][] getData() throws IOException{
		String xFile = System.getProperty("user.dir")+"\\src\\test\\java\\com\\qa\\testdata\\JAC_10th_RollNumbers.xlsx";
		String xSheetName = "GCHSB";
		
		int rowCount = ExcelUtility.getRowCount(xFile, xSheetName);
		int cellCount = ExcelUtility.getCellCount(xFile, xSheetName, rowCount);
		
		String[][] data = new String[rowCount][cellCount];
		
		for(int i=1; i<=rowCount; i++) {
			for(int j=0; j<cellCount; j++){
				data[i-1][j] = ExcelUtility.getCellData(xFile, xSheetName, i, j);
			}
		}
		
		return data;
	}
	
}
