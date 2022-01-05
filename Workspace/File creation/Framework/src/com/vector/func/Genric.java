package com.vector.func;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Genric extends SuperTestNG {
	 
	 public void alwayswait(int timeinseconds) 
		{
			try {
				Thread.sleep(timeinseconds * 1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(timeinseconds+" seconds Wait is over");
		}

		public void controlplus(WebDriver driver,String character)
		{
			Actions actionObj = new Actions(driver);
		    actionObj.keyDown(Keys.CONTROL)
		             .sendKeys(Keys.chord(character))
		             .keyUp(Keys.CONTROL)
		             .perform();
		}
		
		public static void dropdown(WebDriver driver, WebElement element, String value)
		{
			Select select = new Select(element);
		    select.deselectAll();
		    select.selectByVisibleText(value);
		}
		
		public String ExcelRead(String Sheetname,String Path,int row1, int col1) throws EncryptedDocumentException, InvalidFormatException, IOException{

			
			FileInputStream fis=new FileInputStream(Path);
			Workbook wb=WorkbookFactory.create(fis);
			Sheet sh=wb.getSheet(Sheetname);
			Row row=sh.getRow(row1);
			Cell cell=row.getCell(col1);
			String cellvalue=cell.getStringCellValue();	
			return cellvalue;

		}



		public  void ExcelWrite(String Sheetname,String Path,int row1, int col1, String value) throws EncryptedDocumentException, InvalidFormatException, IOException{

			
			FileInputStream fis=new FileInputStream(Path);
			Workbook wb=WorkbookFactory.create(fis);
			Sheet sh=wb.getSheet(Sheetname);
			Row row=sh.getRow(row1);
			Cell cell=row.createCell(col1);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(value);
			FileOutputStream fos=new FileOutputStream(Path);
			wb.write(fos);
			fos.close();
			

		 }
		
		public static void savefunc() throws AWTException
		{
			    Robot r  = new Robot();
			    r.keyPress(KeyEvent.VK_CONTROL);
			    r.keyPress(KeyEvent.VK_S);
			    r.keyRelease(KeyEvent.VK_CONTROL);
			    r.keyRelease(KeyEvent.VK_S);
			    
		}
		
		public static void enterPress() throws AWTException
		{
			    Robot r  = new Robot();
			    r.keyPress(KeyEvent.VK_ENTER);
			    r.keyRelease(KeyEvent.VK_ENTER);
			    
		}
	
	
}