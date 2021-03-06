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

public class createSJImageVDF {

	public static void main(String[] args) throws SQLException, EncryptedDocumentException, InvalidFormatException, IOException {
		// TODO Auto-generated method stub

		String Path = "E:\\TSG\\Workspace\\File creation\\Input\\ImageData.xlsx";
		
		
		 int i=1;
		    
		   
		    String Covered = ExcelRead("Input",Path , i, 0);//Y 
		    
		    String LANE_TX_ID = null;
		    
		    while(!Covered.equals("LAST"))
		    {	   
		        
		    	if(!Covered.equals("LAST"))
		    	{	
		    	Covered = ExcelRead("Input", Path, i, 0); 
		    	
		    	LANE_TX_ID= ExcelRead("Input", Path, i, 1);
		    	System.out.println(LANE_TX_ID);
		    	prepareImage(LANE_TX_ID, Path, i);
		    	}
		    	
		    	i++;	
		          
		    }	
		  
		
		//String filepath ="E:\\TSG\\Workspace\\App\\WebApp-01\\Files\\"+Filename;	
		
	}
	
	public static void prepareImage(String LANE_TX_ID, String path, int index) throws SQLException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		String Agencyid = "007";
		String Const = "00";
		String plaza = getPlaza(LANE_TX_ID);
		String lane = getLane(LANE_TX_ID);
		String Direction = "e";
		String vehicleSeqNum = "0"+vehicleSeq();
		
		String YYYYMMDD = getTimeStamp(LANE_TX_ID,"YYYYMMDD");
		String YYYYMMDDHH24MISS = getTimeStamp(LANE_TX_ID,"YYYYMMDDHH24MISS");
		String imagenum = "01";
		String FrontRear = "r";
		
		String imageFileName = Agencyid + Const+ plaza + lane + Direction + vehicleSeqNum + YYYYMMDD + imagenum + FrontRear;
		String vdfFileName = Agencyid + Const + plaza + lane + Direction + vehicleSeqNum + YYYYMMDD ;
		System.out.println(imageFileName+".jpg");
		 ExcelWrite("Input", path,index , 2,imageFileName+".jpg"); 
		System.out.println(vdfFileName+".vdf");
		ExcelWrite("Input", path,index , 3,vdfFileName+".vdf"); 
		
		String vdfConst = "  ";
		String Timestamp = getTimeStamp(LANE_TX_ID);
		String sss = Timestamp.substring(10, 22);
		String HH = sss.substring(0,2);
		String MM = sss.substring(3,5);
		String SS = sss.substring(6,8);
		String MIL = sss.substring(9,12);
		String HHMMSSAAA = HH+MM+SS+MIL;
		String ConstantEnd = "0000000000";
		
		
		
		String vdfRecord = Agencyid + plaza + vdfConst + lane + Direction + YYYYMMDDHH24MISS + MIL + vehicleSeqNum + vehicleSeqNum + "01" + ConstantEnd;
		ExcelWrite("Input", path,index , 4,vdfRecord); 
		String FilePath = "E:\\TSG\\Workspace\\File creation\\Input\\vdffiles\\";
		File file = new File(FilePath);
		BufferedWriter bw = new BufferedWriter(new FileWriter(FilePath + vdfFileName+".vdf", true));
		bw.write(vdfRecord);
		System.out.println(vdfRecord);
		bw.write("\n");
		bw.flush();
		 bw.close();
	}

	public static String getPlaza(String LANETXID) throws SQLException
	{
		
		
		
			DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver()); 
		    String dbURL = "jdbc:oracle:thin:@10.36.20.51:1521/njrbtst";
		    String strUserID = "pbatch";
		    String strPassword = "pbatch443"; 
		    Connection c=DriverManager.getConnection(dbURL,strUserID,strPassword);
		    
		    String Statement = "SELECT EXTERN_PLAZA_ID FROM T_PLAZA WHERE PLAZA_ID IN (SELECT PLAZA_ID FROM T_VIOL_TX WHERE LANE_TX_ID = '"+LANETXID+"') "; 
		    
		    ResultSet rs = c.createStatement().executeQuery(Statement);
		    String plaza = null;
		   
		    while(rs.next()){
		    	plaza =rs.getString("EXTERN_PLAZA_ID");  
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
	
	public static String getLane(String LANETXID) throws SQLException
	{
		
		
		
			DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver()); 
		    String dbURL = "jdbc:oracle:thin:@10.36.20.51:1521/njrbtst";
		    String strUserID = "pbatch";
		    String strPassword = "pbatch443"; 
		    Connection c=DriverManager.getConnection(dbURL,strUserID,strPassword);
		    
		    String Statement = "SELECT EXTERN_LANE_ID FROM T_LANE WHERE LANE_ID IN (SELECT LANE_ID FROM T_VIOL_TX WHERE LANE_TX_ID = '"+LANETXID+"') "; 
		    
		    ResultSet rs = c.createStatement().executeQuery(Statement);
		    String lane = null;
		   
		    while(rs.next()){
		    	lane =rs.getString("EXTERN_LANE_ID");  
		    }
		   
		    if(lane==null)
		    {	
		    	lane = "NULL";		  

		    }
		  	    
			c.close();
			return lane;
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
