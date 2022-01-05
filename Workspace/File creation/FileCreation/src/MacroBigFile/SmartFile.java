

package MacroBigFile;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.apache.commons.lang3.SystemUtils;
import org.apache.poi.EncryptedDocumentException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import com.Ostermiller.util.LineEnds;

public class SmartFile {
	
	
	public static void main (String args[]) throws EncryptedDocumentException, InvalidFormatException, IOException, SQLException, InterruptedException
	{
		allfunctions a = new allfunctions();
		int agency = Integer.parseInt(JOptionPane.showInputDialog("Please select Agency : \n 1. NJTP \n 2. GSP \n 8. Exit"));
		
		a.createFile(agency);
		
              

	}
}	
	
	

 class allfunctions
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

public static String getxferid(String filename) throws SQLException
{
	
	
	
	DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver()); 
		 String dbURL = "jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST = 10.36.20.51)(PORT = 1521))(CONNECT_DATA = (SERVER = DEDICATED)(SERVICE_NAME = njrbtst)))";
	    String strUserID = "pbatch";
	    String strPassword = "batchp"; 
	    Connection c=DriverManager.getConnection(dbURL,strUserID,strPassword);
	    
	    String Statement = "select XFER_CONTROL_ID from tpms.t_xfer_control where XFER_FILE_NAME = '"+filename+"'"; 
	    
	    ResultSet rs = c.createStatement().executeQuery(Statement);
	    String xfer = null;
	    int cnt =0;
	    while(rs.next()){
	    	xfer =rs.getString("XFER_CONTROL_ID");  
	    }
	    System.out.println(xfer);
	    if(xfer==null)
	    {	
	    	xfer = "NULL";
	    	cnt = 1;

	    }
	    else
	    { 
	    
	    	int n = JOptionPane.showConfirmDialog(null,"Xfer ID "+xfer+" Exists already for File : "+filename+" , Do you want to Continue with file creation ?","Please Confirm",JOptionPane.YES_NO_OPTION);
	    	
	    	if(n==JOptionPane.NO_OPTION){
	    	System.exit(0);
	    	}
	    	System.out.println("xfer done");
	      
	    }
	    
	    
		c.close();
		return xfer;
	}

public static void insertxferid(String filename,int agency) throws SQLException
{
	
	
	
	DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver()); 
		   String dbURL = "jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST = 10.36.20.51)(PORT = 1521))(CONNECT_DATA = (SERVER = DEDICATED)(SERVICE_NAME = njrbtst)))";
	    String strUserID = "pbatch";
	    String strPassword = "batchp"; 
	    Connection c=DriverManager.getConnection(dbURL,strUserID,strPassword);
	    String agencyname= null;;
	    if(agency ==1)
	    {
	      agencyname = "njtp";
	    }
	    else if(agency==2)
	    {
	    	agencyname = "njha";
	    }
	    
	    String Statement = "insert into t_xfer_control (XFER_CONTROL_ID, XFER_FILE_ID, XFER_FILE_NAME, DATE_CREATED, TIME_CREATED, FILE_SIZE, FILE_CHECKSUM, NUM_RECS, HASH_TOTAL, XFER_PATH, XFER_XMIT_STATUS, XFER_RETRY, UPDATE_TS, FILE_TYPE) values ((select Max( XFER_CONTROL_ID) + 1 from t_xfer_control where length( XFER_CONTROL_ID) = 8),711, '"+filename+"', to_date('25-11-2015', 'dd-mm-yyyy'), '08:00:13', 3857466, 0, 9, 0, '/app/vector/data_files/"+agencyname+"_qatp', 'S', '0', '19-AUG-13 08.00.13.000 AM -04:00', 'tr')";
	    c.createStatement().executeQuery(Statement);
    	c.createStatement().executeQuery("Commit");
    	System.out.println("DB done");
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


public void createFile(int agency) throws EncryptedDocumentException, InvalidFormatException, IOException, SQLException, InterruptedException
{
	String agencyname;
	String AgencyID = null;
	
	
	
	
	switch(agency)
	{
	case 1 :  agencyname = "NJTP";
			  JOptionPane.showMessageDialog(null,"Agency chosen: "+agencyname);
		      AgencyID   = "003";	     
		      break;
		       
	case 2 :  agencyname = "GSP";
	          JOptionPane.showMessageDialog(null,"Agency chosen: "+agencyname);  
	          AgencyID   = "002";          
	          break;

	
	           
			
	default : 	agencyname = "not configured" ;
	            
	            JOptionPane.showMessageDialog(null,"Agency chosen is "+agencyname);
	            
	            System.exit(0);
		
		
	}
	
	
	
	String Path1 = "E:\\TSG\\Workspace\\File creation\\Macro code\\"+agencyname+"\\Smart File Creator.xlsm";
	
	
   
    
    
    
    
        
    
 
     String Filename = ExcelRead("Data3",Path1 , 24, 1);
     System.out.println(Filename);
     
     String XFERID = getxferid(Filename);
 	if(XFERID.equals("NULL"))
 	{	    			
 		insertxferid(Filename,agency);
 		XFERID = getxferid(Filename);
 	}
    
    
    
   
    
    ExcelWrite("Data3", Path1, 6, 1,XFERID); 
    
    
     System.out.println("Xfer Id added");
     String Outputpath = "E:\\TSG\\Workspace\\File creation\\Macro code\\"+agencyname;
     String filepath = "E:\\TSG\\Workspace\\File creation\\Macro code\\Output";
     Runtime.getRuntime().exec("explorer "+Outputpath+"\\MacroSM.vbs"); 
     Runtime.getRuntime().exec("explorer "+filepath);   
  
    
     
//   
//     File f = new File("E:\\TSG\\Workspace\\File creation\\Macro code\\Output\\"+Filename);
//     int style = LineEnds.STYLE_UNIX;
//     LineEnds.convert(f, style);
//     Runtime.getRuntime().exec("explorer "+filepath);          
	
}
		
		
}
	


