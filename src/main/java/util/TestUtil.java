package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.google.common.io.Files;

import pages.TestBase;

public class TestUtil extends TestBase {
	
	public static long pageLoadTimeout = 60;
	public static long implicitWaitTimeout = 60;
	
	static Workbook wb ;
	static Sheet sheet;
	
	
	public TestUtil() {
		super();
	}
	
	
	
	public static void takeScreenshotAtEndOfTest() throws IOException {
		
		File srcFile = ( (TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		Files.copy(srcFile, new File(System.getProperty("user.dir")+"/screenshots/"+System.currentTimeMillis()+".png"));
			
	}
	
	public static String[][] getTestData (String sheetName){
		FileInputStream file =null;
		
		try {
			file = new FileInputStream(System.getProperty("user.dir")+prop.getProperty("test_data_path"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			wb = WorkbookFactory.create(file);
			
			sheet = wb.getSheet(sheetName);
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 String[][] obj = new String[sheet.getLastRowNum()+1][sheet.getRow(0).getLastCellNum()];
		int row = 1;
		int col=1;
		System.out.println(sheet.getLastRowNum());
		System.out.println(sheet.getRow(0).getLastCellNum());
		 for (int i = 0; i <= sheet.getLastRowNum(); i++) {
			 System.out.println("row"+row++);
				for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
					 System.out.println("col"+col++);
					//obj[i][k]=sheet.getRow(i + 1).getCell(k).getStringCellValue();
					if(sheet.getRow(i).getCell(k).getCellType() == CellType.STRING) {
						obj[i][k]=sheet.getRow(i).getCell(k).toString();
					}else if(sheet.getRow(i).getCell(k).getCellType() == CellType.BOOLEAN) {
						obj[i][k]=sheet.getRow(i).getCell(k).toString();
					}else {
						obj[i][k]=String.valueOf((int)sheet.getRow(i).getCell(k).getNumericCellValue());
					}
					System.out.println(obj[i][k]);
				}
			}
		
		
		return obj;
		
	}
	
	
	

}
