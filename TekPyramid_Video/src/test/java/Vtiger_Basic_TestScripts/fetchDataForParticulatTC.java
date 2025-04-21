package Vtiger_Basic_TestScripts;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class fetchDataForParticulatTC {

	public static void main(String[] args) throws Throwable {

		FileInputStream fis = new FileInputStream("C:\\Users\\FloweR KinG\\OneDrive\\Desktop\\TekPyramid\\AdvSele\\multiData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sheet = wb.getSheet("Sheet1");

		Random r = new Random();
		int randomNum = r.nextInt(1000);

		int count = sheet.getLastRowNum();
		String expectedData = "TC01";
		for (int i = 1; i <= count; i++) {
			Row row = sheet.getRow(i);
			String firstColData = row.getCell(0).toString();

			if (firstColData.equals(expectedData)) {
				String secondColData = row.getCell(2).toString();
				System.out.println(secondColData + randomNum);
			} 
		}
	}
}
