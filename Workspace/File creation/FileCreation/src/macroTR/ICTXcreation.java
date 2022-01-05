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

public class ICTXcreation {
	
	
	public static void main (String args[]) throws EncryptedDocumentException, InvalidFormatException, IOException
	{
		functions1 a = new functions1();
		int agency = Integer.parseInt(JOptionPane.showInputDialog("Please select Away Agency : \n 1. 008 - TB \n 2. 004 - NY \n 3. 005 - PA \n 4. 010 VDOT \n 8. Exit"));
		
		a.createFile(agency);
		
              

	}
}	
	
	

 class functions1
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


public void createFile(int agency) throws EncryptedDocumentException, InvalidFormatException, IOException
{
	String agencyname;
	String AgencyID = null;
	int numberoffields = 27;
	String Fileext = ".ICTX";
	
	
	
	
	switch(agency)
	{
	case 1 :  agencyname = "TB";
			  JOptionPane.showMessageDialog(null,"Agency chosen: "+agencyname);
		      AgencyID   = "008";		      
		      break;
		       
	case 2 :  agencyname = "NY";
			  JOptionPane.showMessageDialog(null,"Agency chosen: "+agencyname);
			  AgencyID   = "004";		      
			  break;

	case 3 :  agencyname = "PA";
	  	      JOptionPane.showMessageDialog(null,"Agency chosen: "+agencyname);
	  	      AgencyID   = "005";		      
	  	      break;          
	
	case 6 :  agencyname = "VDOT";
      		  JOptionPane.showMessageDialog(null,"Agency chosen: "+agencyname);
      		  AgencyID   = "010";		      
      		  break; 
	           
	           
			
	default : 	agencyname = "not configured" ;
	            
	            JOptionPane.showMessageDialog(null,"Agency chosen is "+agencyname);
	            
	            System.exit(0);
		
		
	}
	
	
	String Path = "E:\\TSG\\Workspace\\File creation\\Away\\ICTX\\ICTXMacro.xlsm";
	
	
	String FILETYPE = ExcelRead("Header", Path,1, 0);
    String FromAgencyID = AgencyID;
    String ToAgencyID  = "022";
    String FILEDATE = ExcelRead("Header", Path, 1, 3);
    String FILETIME = ExcelRead("Header", Path,1, 4);
    String FileNum = ExcelRead("Header", Path,1, 6);
    
    
    String RecordCount = ExcelRead("Header", Path, 1, 5);
    
    
    
    int cnt = Integer.parseInt(RecordCount)+1;
    JOptionPane.showMessageDialog(null,"Approximate time for creating file : "+cnt * 3+ "Seconds");
        
     
    
     String headertext = null;
     
     String Filename = null;
    
     headertext = FILETYPE+  FromAgencyID +ToAgencyID + FILEDATE  + FILETIME + RecordCount + FileNum;
     Filename = FromAgencyID + "_" + ToAgencyID + "_" +FILEDATE + FILETIME + Fileext;   
    
    
    ExcelWrite("Output", Path, 0, 1,headertext); 
    
    ExcelWrite("Output", Path, 1, 1,Filename); 
    
    
    
    PrintWriter writer = new PrintWriter("E:\\TSG\\Workspace\\File creation\\Away\\ICTX\\File\\"+Filename);
    
    writer.println(headertext); 
    final long startTime = System.currentTimeMillis();
    for(int i=1;i<cnt;i++)
    { 	
    	for(int j=0;j<numberoffields;j++)
       {
    		writer.print(ExcelRead("Detail", Path, i, j));
    		//System.out.print(a.ExcelRead("Data1", "E:\\TSG\\Workspace\\File creation\\Input\\NJTP_Generic Macro V1.4.xlsm", i, j));
       }
    	
    	writer.println();
    	//System.out.println();
    }	
    
   
    writer.close();
    final long endTime = System.currentTimeMillis();
    System.out.println("DONE, Number of transactions " +(cnt-1));
    System.out.println("Time taken to create File "+(endTime-startTime)/1000 + " Seconds");
    
    JOptionPane.showMessageDialog(null,numberoffields+" Fields Loaded Sucessfully with " +(cnt-1)+ " Transactions\n" + "Time taken to create File " +(endTime-startTime)/1000 + " Seconds");
    File f = new File("E:\\TSG\\Workspace\\File creation\\Away\\ICTX\\File\\"+Filename);
    int style = LineEnds.STYLE_UNIX;
    LineEnds.convert(f, style);
    String Outputpath = "E:\\TSG\\Workspace\\File creation\\Away\\ICTX\\File";
    Runtime.getRuntime().exec("explorer "+Outputpath);
    
          
	
}

		
		
}
	


