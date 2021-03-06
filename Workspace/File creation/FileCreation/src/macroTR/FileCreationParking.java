package macroTR;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;


import javax.swing.JOptionPane;

import org.apache.poi.EncryptedDocumentException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import com.Ostermiller.util.LineEnds;

public class FileCreationParking {
	
	
	public static void main (String args[]) throws EncryptedDocumentException, InvalidFormatException, IOException
	{
		Parkingfunctions a = new Parkingfunctions();
		int input = Integer.parseInt(JOptionPane.showInputDialog("Please select Agency : \n 1. FNTX \n 2. INRX \n 3. INTX \n 4. FTXN"));
		String filetype = null;
		if(input==1)
		{
			filetype = "FNTX";
		}
		else if(input==2)			
		{
			filetype = "INRX";
		}
		else if(input==3)	
		{
			filetype = "INTX";
		}
		else 
		{
			filetype = "FTXN";
		}
		a.createFile(filetype);
		
              

	}
}	
	
	

 class Parkingfunctions
{
public String ExcelRead(String Sheetname,String Path,int row1, int col1) throws EncryptedDocumentException, InvalidFormatException, IOException{

		
	FileInputStream fis=new FileInputStream(Path);
	Workbook wb=WorkbookFactory.create(fis);
	Sheet sh=wb.getSheet(Sheetname);
	Row row=sh.getRow(row1);
	Cell cell=row.getCell(col1);
	String cellvalue=cell.getStringCellValue();	
	return cellvalue;

}



public void ExcelWrite(String Sheetname,String Path,int row1, int col1, String value) throws EncryptedDocumentException, InvalidFormatException, IOException{

	
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


public void createFile(String filetype) throws EncryptedDocumentException, InvalidFormatException, IOException
{
	
	
	int numberoffields=0;
	String Path=null;
	String Path1=null;		
	
	if(filetype.equalsIgnoreCase("FNTX"))
	{
		numberoffields =24;
		Path = "E:\\TSG\\Workspace\\File creation\\Input\\Parking\\FNTX\\ParkingFNTXInput.xlsx";
		Path1 = "E:\\TSG\\Workspace\\File creation\\Input\\Parking\\FNTX\\ParkingFNTXv1.0.xlsm";
				
	}
	else if(filetype.equalsIgnoreCase("INRX"))
	{
		numberoffields = 5;
		Path = "E:\\TSG\\Workspace\\File creation\\Input\\Parking\\INRX\\ParkingINRXInput.xlsx";
		Path1 = "E:\\TSG\\Workspace\\File creation\\Input\\Parking\\INRX\\ParkingINRXv1.0.xlsm";
	}
	
	else if(filetype.equalsIgnoreCase("INTX"))
	{
		numberoffields = 24;
		Path = "E:\\TSG\\Workspace\\File creation\\Input\\Parking\\INTX\\ParkingINTXInput.xlsx";
		Path1 = "E:\\TSG\\Workspace\\File creation\\Input\\Parking\\INTX\\ParkingINTXv1.0.xlsm";
	}
	else if(filetype.equalsIgnoreCase("FTXN"))
	{
		numberoffields =25;
		Path = "E:\\TSG\\Workspace\\File creation\\Input\\Parking\\FTXN\\ParkingFTXNInput.xlsx";
		Path1 = "E:\\TSG\\Workspace\\File creation\\Input\\Parking\\FTXN\\ParkingFTXNv1.0.xlsm";
	}
	
	
	
	
	String Fileext = "."+filetype;
    String DD = ExcelRead("Input", Path, 2, 1);
    String MM = ExcelRead("Input", Path, 3, 1);
    String YYYY = ExcelRead("Input", Path, 4, 1);
    String HHMMSS = ExcelRead("Input", Path, 5, 1);
    
    String RecordCount = ExcelRead("Data2", Path1, 1, 1);
    String fo_id = ExcelRead("Input", Path, 9, 1);
    String agencyid = ExcelRead("Input", Path, 7, 1);
    String filenumber = ExcelRead("FileParameter", Path, 1, 1);
    int num1 = Integer.parseInt(filenumber) + 1;
    String newfilenumber = "" + num1;
       
    
    int cnt = Integer.parseInt(RecordCount)+2;
    JOptionPane.showMessageDialog(null,"Approximate time for creating file : "+(cnt * numberoffields*0.25)+ "Seconds");
        
    String ARecordCount = null;
    String AFileNum = null;
    
    String headertext = null;
   
    String Filename = null;
    
    ARecordCount = "00000000".substring(RecordCount.length()) + RecordCount;
    AFileNum = "000000".substring(filenumber.length()) + filenumber;
  
    headertext = filetype+ fo_id+ agencyid+ YYYY + MM + DD  + HHMMSS + ARecordCount + AFileNum;
    String underscore= "_";
    Filename = fo_id + underscore +agencyid + underscore + YYYY + MM + DD + HHMMSS + Fileext;
             
	
    
    
    
    
    ExcelWrite("Input", Path, 13, 1,headertext); 
   
    ExcelWrite("Input", Path, 24, 1,Filename); 
    
    ExcelWrite("Input", Path, 8, 1,RecordCount);
  
    
    PrintWriter writer = new PrintWriter("E:\\TSG\\Workspace\\File creation\\Output\\Parking\\"+Filename);
    
    writer.println(headertext); 
    final long startTime = System.currentTimeMillis();
    for(int i=2;i<cnt;i++)
    { 	
    	for(int j=0;j<numberoffields;j++)
       {
    		writer.print(ExcelRead("Data1",Path1, i, j));
    		//System.out.print(a.ExcelRead("Data1", "E:\\TSG\\Workspace\\File creation\\Input\\NJTP_Generic Macro V1.4.xlsm", i, j));
       }
    	
    	writer.println();
    	//System.out.println();
    }	
    
    
    writer.close();
    final long endTime = System.currentTimeMillis();
    System.out.println("DONE, Number of transactions " +(cnt-2));
    System.out.println("Time taken to create File "+(endTime-startTime)/1000 + " Seconds");
    
    JOptionPane.showMessageDialog(null,numberoffields+" Fields Loaded Sucessfully with " +(cnt-2)+ " Transactions\n" + "Time taken to create File " +(endTime-startTime)/1000 + " Seconds");
    File f = new File("E:\\TSG\\Workspace\\File creation\\Output\\Parking\\"+Filename);
    int style = LineEnds.STYLE_UNIX;
    LineEnds.convert(f, style);
    String Outputpath = "E:\\TSG\\Workspace\\File creation\\Output\\Parking\\";
    Runtime.getRuntime().exec("explorer "+Outputpath);
    ExcelWrite("FileParameter", Path, 1, 1,newfilenumber); 
    
          
	
}

		
		
}
	


