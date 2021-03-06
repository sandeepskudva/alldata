package Filetransfer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.util.SystemOutLogger;

public class createBPOFile {

	public static void main(String[] args) throws SQLException, EncryptedDocumentException, InvalidFormatException, IOException {
		// TODO Auto-generated method stub

		String Path = "E:\\TSG\\Workspace\\File creation\\Input\\BPOData.xlsx";
		
		
		 int i=1;
		    
		   
		    String Covered = ExcelRead("Input",Path , i, 0);//Y 
		    
		    String LANE_TX_ID = null;
		    String ImageStatus = null;
		    String PlateState= null;
		    String PlateNumber = null;
		    
		    while(!Covered.equals("LAST"))
		    {	   
		        
		    	if(!Covered.equals("LAST"))
		    	{	
		    	Covered = ExcelRead("Input", Path, i, 0); 
		    	
		    	LANE_TX_ID= ExcelRead("Input", Path, i, 1);
		    	ImageStatus =ExcelRead("Input", Path, i, 2);
		    	PlateState =ExcelRead("Input", Path, i, 3);
		    	PlateNumber =ExcelRead("Input", Path, i, 4);
		    	
		    	System.out.println(LANE_TX_ID);
		    	getDetailRecord(LANE_TX_ID,ImageStatus,PlateState,PlateNumber, i,Path);
		    	}
		    	
		    	i++;	
		          
		    }	
		  
		
		//String filepath ="E:\\TSG\\Workspace\\App\\WebApp-01\\Files\\"+Filename;	
		
	}
	
	public static void getDetailRecord(String LANETXID, String ImageStatus, String PlateState,String PlateNumber, int index , String path) throws SQLException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		String DetailRecord = "D";
		String preLANETXID = "0000";
		String ClerkID = "11112222";
		String ViolType = "T";
		String PrePlate = "   ";
		String PlateType = "        "; 
		String PlateCountry = "USA";
		String ImageNumber = "01";
		String Selection_code = "02";
			
		
		String BPORECORD = DetailRecord + preLANETXID + LANETXID + ClerkID+ ImageStatus + ViolType + PrePlate + addspaces(PlateNumber,10) + addspaces(PlateState,2) +PlateType +addspaces(PlateCountry,4) + getTimeStamp(LANETXID,"YYYYMMDDHHMMSS") + getReviewedDate("YYYYMMDDHHMMSS") + ImageNumber + addspaces(getImageName(LANETXID),33) + Selection_code;
		System.out.println(BPORECORD);
		ExcelWrite("Input", path,index , 5,BPORECORD); 
	}
	
	public static String addspaces(String s,int totallength)
	{
		int length = s.length();
		int numberofspaces = totallength- length;
		for(int i=0;i<numberofspaces;i++)
		{
			s = s+" ";
		}
		
	 return s;	
	}
	
	
	public static String getImageName(String LANETXID) throws SQLException
	{
		
		
		
			DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver()); 
		    String dbURL = "jdbc:oracle:thin:@10.36.20.51:1521/njrbtst";
		    String strUserID = "pbatch";
		    String strPassword = "pbatch443"; 
		    Connection c=DriverManager.getConnection(dbURL,strUserID,strPassword);
		    
		    String Statement = "select OUT_FILE_NAME from t_viol_image_tx WHERE LANE_TX_ID = '"+LANETXID+"'"; 
		    
		    
		    ResultSet rs = c.createStatement().executeQuery(Statement);
		    String plaza = null;
		   
		    while(rs.next()){
		    	plaza =rs.getString("OUT_FILE_NAME");  
		    }
		   
		    if(plaza==null)
		    {	
		    	plaza = "NULL";		  

		    }
		  	    
			c.close();
			return plaza;
		}
	
	public static String getTimeStamp(String LANETXID,String Format) throws SQLException
	{
		
		
		
			DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver()); 
		    String dbURL = "jdbc:oracle:thin:@10.36.20.51:1521/njrbtst";
		    String strUserID = "pbatch";
		    String strPassword = "pbatch443"; 
		    Connection c=DriverManager.getConnection(dbURL,strUserID,strPassword);
		    
		    String Statement = "SELECT TO_CHAR(TX_TIMESTAMP,'"+Format+"') as \"FORMAT\" FROM T_VIOL_TX WHERE LANE_TX_ID = '"+LANETXID+"'"; 
		    
		    ResultSet rs = c.createStatement().executeQuery(Statement);
		    String plaza = null;
		   
		    while(rs.next()){
		    	plaza =rs.getString("FORMAT");  
		    }
		   
		    if(plaza==null)
		    {	
		    	plaza = "NULL";		  

		    }
		  	    
			c.close();
			return plaza;
		}
	public static String getReviewedDate(String Format) throws SQLException
	{
		
		
		
			DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver()); 
		    String dbURL = "jdbc:oracle:thin:@10.36.20.51:1521/njrbtst";
		    String strUserID = "pbatch";
		    String strPassword = "pbatch443"; 
		    Connection c=DriverManager.getConnection(dbURL,strUserID,strPassword);
		    
		    String Statement = "SELECT TO_CHAR(SYSDATE,'"+Format+"') as \"FORMAT\" FROM DUAL"; 
		    
		    ResultSet rs = c.createStatement().executeQuery(Statement);
		    String plaza = null;
		   
		    while(rs.next()){
		    	plaza =rs.getString("FORMAT");  
		    }
		   
		    if(plaza==null)
		    {	
		    	plaza = "NULL";		  

		    }
		  	    
			c.close();
			return plaza;
		}
	
	public static String getTimeStamp(String LANETXID) throws SQLException
	{
		
		
		
			DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver()); 
		    String dbURL = "jdbc:oracle:thin:@10.36.20.51:1521/njrbtst";
		    String strUserID = "pbatch";
		    String strPassword = "pbatch443"; 
		    Connection c=DriverManager.getConnection(dbURL,strUserID,strPassword);
		    
		    String Statement = "SELECT TO_CHAR(TX_TIMESTAMP) as \"FORMAT\" FROM T_VIOL_TX WHERE LANE_TX_ID = '"+LANETXID+"'"; 
		    
		    ResultSet rs = c.createStatement().executeQuery(Statement);
		    String plaza = null;
		   
		    while(rs.next()){
		    	plaza =rs.getString("FORMAT");  
		    }
		   
		    if(plaza==null)
		    {	
		    	plaza = "NULL";		  

		    }
		  	    
			c.close();
			return plaza;
		}
	
	
	
	public static String vehicleSeq() throws SQLException
	{
	    String num= null;    
	    Random r = new Random( System.currentTimeMillis() );
	    return "00"+ ((1 + r.nextInt(6)) * 10000 + r.nextInt(10000));		
		
	}
	
	public static String ExcelRead(String Sheetname,String Path,int row1, int col1) throws EncryptedDocumentException, InvalidFormatException, IOException{

		
		FileInputStream fis=new FileInputStream(Path);
		Workbook wb=WorkbookFactory.create(fis);
		Sheet sh=wb.getSheet(Sheetname);
		Row row=sh.getRow(row1);
		Cell cell=row.getCell(col1);
		String cellvalue=cell.getStringCellValue();	
		return cellvalue;

	}
	
	public static void ExcelWrite(String Sheetname,String Path,int row1, int col1, String value) throws EncryptedDocumentException, InvalidFormatException, IOException{

		
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
			
	
}
