package macroTR;


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

import org.apache.poi.EncryptedDocumentException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


import com.Ostermiller.util.LineEnds;

public class FileCreationViol {
	
	
	public static void main (String args[]) throws EncryptedDocumentException, InvalidFormatException, IOException, SQLException
	{
		functionsViol a = new functionsViol();
		int agency = Integer.parseInt(JOptionPane.showInputDialog("Please select Agency  for Violations: \n 1. NJTP \n 2. GSP \n 3. DRBA \n 4. DRPA \n 5. SJTA \n 6. DRJ \n 7. BCBC \n 8. Exit"));
		
		a.createFile(agency);
		
              

	}
}	
	
	

 class functionsViol
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
public static String getxferid(String filename) throws SQLException
{
	
	
	
		DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver()); 
		//String dbURL1 = "jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST = tt0alddb01njz-vip)(PORT = 1521))(ADDRESS = (PROTOCOL = TCP)(HOST = tt0alddb02njz-vip)(PORT = 1521))(LOAD_BALANCE = yes)(CONNECT_DATA = (SERVER = DEDICATED)(SERVICE_NAME = njroltp)(FAILOVER_MODE =(TYPE = SELECT)(METHOD = BASIC)(RETRIES = 180)(DELAY = 5))))";

		String dbURL1 = "jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST = 10.36.20.102)(PORT = 1521))(CONNECT_DATA = (SERVER = DEDICATED)(SERVICE_NAME = njaettst)))";
	    String strUserID = "pbatch";
	    String strPassword = "batchp"; 
	    //String strPassword ="BATCHP!123";

	    Connection c=DriverManager.getConnection(dbURL1,strUserID,strPassword);
	    
	    String Statement = "select XFER_CONTROL_ID from t_xfer_control where XFER_FILE_NAME = '"+filename+"'"; 
	    
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
		   String dbURL = "jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST = 10.36.20.102)(PORT = 1521))(CONNECT_DATA = (SERVER = DEDICATED)(SERVICE_NAME = njrbtst)))";
	    String strUserID = "pbatch";
	    String strPassword = "batchp"; 
	    Connection c=DriverManager.getConnection(dbURL,strUserID,strPassword);
	    String agencyname= null;
	    String Statement = null;
	    if(agency ==1)
	    {
	      agencyname = "njtp";
	      Statement = "insert into t_xfer_control (XFER_CONTROL_ID, XFER_FILE_ID, XFER_FILE_NAME, DATE_CREATED, TIME_CREATED, FILE_SIZE, FILE_CHECKSUM, NUM_RECS, HASH_TOTAL, XFER_PATH, XFER_XMIT_STATUS, XFER_RETRY, UPDATE_TS, FILE_TYPE) values ((select Max( XFER_CONTROL_ID) + 1 from t_xfer_control where length( XFER_CONTROL_ID) = 8),711, '"+filename+"', to_date('25-11-2015', 'dd-mm-yyyy'), '08:00:13', 3857466, 0, 9, 0, '/app/vector/data_files/"+agencyname+"_qatp', 'S', '0', '19-AUG-13 08.00.13.000 AM -04:00', 'tr')";
	    }
	    else if(agency==2)
	    {
	    	agencyname = "njha";
	    	Statement = "insert into t_xfer_control (XFER_CONTROL_ID, XFER_FILE_ID, XFER_FILE_NAME, DATE_CREATED, TIME_CREATED, FILE_SIZE, FILE_CHECKSUM, NUM_RECS, HASH_TOTAL, XFER_PATH, XFER_XMIT_STATUS, XFER_RETRY, UPDATE_TS, FILE_TYPE) values ((select Max( XFER_CONTROL_ID) + 1 from t_xfer_control where length( XFER_CONTROL_ID) = 8),711, '"+filename+"', to_date('25-11-2015', 'dd-mm-yyyy'), '08:00:13', 3857466, 0, 9, 0, '/app/vector/data_files/"+agencyname+"_qatp', 'S', '0', '19-AUG-13 08.00.13.000 AM -04:00', 'tr')";
	    }
	    else if(agency==6)
	    {
	    	agencyname = "drj";
	    	Statement = "insert into t_xfer_control (XFER_CONTROL_ID, XFER_FILE_ID, XFER_FILE_NAME, DATE_CREATED, TIME_CREATED, FILE_SIZE, FILE_CHECKSUM, NUM_RECS, HASH_TOTAL, XFER_PATH, XFER_XMIT_STATUS, XFER_RETRY, UPDATE_TS, FILE_TYPE) values ((select Max( XFER_CONTROL_ID) + 1 from t_xfer_control where length( XFER_CONTROL_ID) = 8),711, '"+filename+"', to_date('25-11-2015', 'dd-mm-yyyy'), '08:00:13', 3857466, 0, 9, 0, '/app/vector/data_files/"+agencyname+"_qatp', 'S', '0', '19-AUG-13 08.00.13.000 AM -04:00', 'TXN')";
	    }
	    
	   
	    c.createStatement().executeQuery(Statement);
    	c.createStatement().executeQuery("Commit");
    	System.out.println("DB done");
}


public void createFile(int agency) throws EncryptedDocumentException, InvalidFormatException, IOException, SQLException
{
	String agencyname;
	String AgencyID = null;
	int numberoffields = 0;
	String Fileext = null;
	
	
	
	
	switch(agency)
	{
	case 1 :  agencyname = "NJTP";
			  JOptionPane.showMessageDialog(null,"Agency chosen: "+agencyname);
		      AgencyID   = "003";
		      numberoffields=46;	
		      Fileext = ".tr";
		       break;
	case 2 :  agencyname = "GSP";
			  JOptionPane.showMessageDialog(null,"Agency chosen: "+agencyname);
			  AgencyID   = "002";
			  numberoffields=46;	
			  Fileext = ".tr";
			  break;
			  
	case 6 :  agencyname = "DRJ";
	  		  JOptionPane.showMessageDialog(null,"Agency chosen: "+agencyname);
	  		  AgencyID   = "020";
	  		  numberoffields=46;	
	  		  Fileext = ".VIO";
	  		  break;
		       
	          
	           
			
	default : 	agencyname = "not configured" ;
	            
	            JOptionPane.showMessageDialog(null,"Agency chosen is "+agencyname);
	            
	            System.exit(0);
		
		
	}
	
	
	String Path = "D:\\TSG\\Workspace\\File creation\\Input\\"+agencyname+"\\Violation File\\"+agencyname+"ViolInput.xlsx";
	String Path1 = "D:\\TSG\\Workspace\\File creation\\Input\\"+agencyname+"\\Violation File\\"+agencyname+"ViolMacro.xlsm";
	
	String header = ExcelRead("Input",Path , 1, 1);
    String DD = ExcelRead("Input", Path, 2, 1);
    String MM = ExcelRead("Input", Path, 3, 1);
    String YYYY = ExcelRead("Input", Path, 4, 1);
    String HHMMSS = ExcelRead("Input", Path, 5, 1);
    
    
    String RecordCount = ExcelRead("Data2", Path1, 1, 1);
    
    
    
    //String TotalRevenue = ExcelRead("Input", Path, 9, 1);
   // String Seperator = a.ExcelRead("Input", Path, 10, 1);
    String Trailer = ExcelRead("Input", Path, 1, 4);
    String Checksum = ExcelRead("Input", Path, 2, 4);
    
    
    int cnt = Integer.parseInt(RecordCount)+2;
    JOptionPane.showMessageDialog(null,"Approximate time for creating file : "+cnt * 3+ "Seconds");
        
    String ARecordCount = null;
    
    String version_number="012.0";
    String xfer10 = null;
  String XFERID =null;;
  
    
    
    
    
     String headertext = null;
     String Trailertext= null;
     String Filename = null;
    
    switch(agency)
	{
	case 1 :  ARecordCount = "00000000".substring(RecordCount.length()) + RecordCount;
              
              headertext = header+ version_number + xfer10 + AgencyID + YYYY + MM+ DD + HHMMSS + ARecordCount;
              Trailertext = Trailer + ARecordCount + Checksum;
              Filename = "v" + YYYY + MM + DD + HHMMSS + AgencyID + Fileext;
              XFERID = getxferid(Filename);
              if(XFERID.equals("NULL"))
          	{	    			
          		insertxferid(Filename,agency);
          		XFERID = getxferid(Filename);
          	}
              xfer10 = "00" + XFERID;
              break;
	case 2 :  ARecordCount = "00000000".substring(RecordCount.length()) + RecordCount;
			 
			  headertext = header+ version_number + xfer10 + AgencyID + YYYY + MM+ DD + HHMMSS + ARecordCount;
			  Trailertext = Trailer + ARecordCount + Checksum;
			  Filename = "v" + YYYY + MM + DD + HHMMSS + AgencyID + Fileext;
			  XFERID = getxferid(Filename);
              if(XFERID.equals("NULL"))
          	{	    			
          		insertxferid(Filename,agency);
          		XFERID = getxferid(Filename);
          	}
              xfer10 = "00" + XFERID;
			  break;   
			  
	case 6 :  ARecordCount = "000000".substring(RecordCount.length()) + RecordCount;
	  		
	  		  String serial="04";
	  		  headertext = header+ ARecordCount + YYYY + MM+ DD + HHMMSS + serial ;
	  		  Trailertext = Trailer;
	  		  Filename = YYYY + MM + DD + serial + Fileext;
	  		 XFERID = getxferid(Filename);
             if(XFERID.equals("NULL"))
         	{	    			
         		insertxferid(Filename,agency);
         		XFERID = getxferid(Filename);
         	}
             xfer10 = "00" + XFERID;
	  		  break;
		       
	              
	    
			
	default : 	
	            System.exit(0);
		
		
	}
    
  
    
    ExcelWrite("Input", Path, 13, 1,headertext); 
    ExcelWrite("Input", Path, 19, 1,Trailertext); 
    ExcelWrite("Input", Path, 24, 1,Filename); 
    ExcelWrite("Input", Path, 8, 1,RecordCount);
  
    System.out.println(XFERID);
    
   PrintWriter writer = new PrintWriter("D:\\TSG\\Workspace\\File creation\\Output\\"+agencyname+"\\"+Filename);
   writer.println(headertext); 
   
   writer.println(Trailertext); 
   writer.close();
  
	
    /*
    writer.println(headertext); 
    final long startTime = System.currentTimeMillis();
    for(int i=2;i<cnt;i++)
    { 	
    	for(int j=0;j<numberoffields;j++)
       {
    		writer.print(ExcelRead("Data1", "D:\\TSG\\Workspace\\File creation\\Input\\"+agencyname+"\\Violation File\\"+agencyname+"ViolMacro.xlsm", i, j));
    		//System.out.print(a.ExcelRead("Data1", "D:\\TSG\\Workspace\\File creation\\Input\\NJTP_Generic Macro V1.4.xlsm", i, j));
       }
    	
    	writer.println();
    	//System.out.println();
    }	
    
    writer.println(Trailertext); 
    writer.close();
    final long endTime = System.currentTimeMillis();
    System.out.println("DONE, Number of transactions " +(cnt-2)); 
    System.out.println("Time taken to create File "+(endTime-startTime)/1000 + " Seconds"); 
    
    JOptionPane.showMessageDialog(null,numberoffields+" Fields Loaded Sucessfully with " +(cnt-2)+ " Transactions\n" + "Time taken to create File " +(endTime-startTime)/1000 + " Seconds"); */
    File f = new File("D:\\TSG\\Workspace\\File creation\\Output\\"+agencyname+"\\"+Filename);
    int style = LineEnds.STYLE_UNIX;
    LineEnds.convert(f, style);
    String Outputpath = "D:\\TSG\\Workspace\\File creation\\Output\\"+agencyname;
    Runtime.getRuntime().exec("explorer "+Outputpath);
    
          
	
}

		
		
}
	


