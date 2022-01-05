package com.sch.pkg;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

//import com.jcraft.jsch.Channel;
//import com.jcraft.jsch.ChannelSftp;
//import com.jcraft.jsch.JSch;
//import com.jcraft.jsch.JSchException;
//import com.jcraft.jsch.Session;
//import com.jcraft.jsch.SftpException;
import com.sun.media.sound.InvalidFormatException;

public class common
{
	static String ipaddress = "10.36.20.51";
	public static String killExcel() {
	    try {
	        Runtime.getRuntime().exec("taskkill /f /t /IM EXCEL.EXE");
	        Thread.sleep(1000);
	    }
	    catch (Exception ex) {
	       
	    }
	    
	    return "";
	}
public static String ExcelRead(String Sheetname,String Path,int row1, int col1) throws EncryptedDocumentException, InvalidFormatException, IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException{

		
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
	     
		String dbURL = "jdbc:oracle:thin:@(DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST = "+ipaddress+")(PORT = 1521)) (CONNECT_DATA =(SERVER = DEDICATED)(SERVICE_NAME = njrbtst)))";
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
		   String dbURL = "jdbc:oracle:thin:@(DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST = "+ipaddress+")(PORT = 1521)) (CONNECT_DATA =(SERVER = DEDICATED)(SERVICE_NAME = njrbtst)))";
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


public static String ExcelWrite(String Sheetname,String Path,int row1, int col1, String value) throws EncryptedDocumentException, InvalidFormatException, IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException{

	
	FileInputStream fis=new FileInputStream(Path);
	Workbook wb=WorkbookFactory.create(fis);
	Sheet sh=wb.getSheet(Sheetname);
	Row row=sh.getRow(row1);
	Cell cell=row.createCell(col1);	
	cell.setCellValue(value);
	
	
	FileOutputStream fos=new FileOutputStream(Path);
	wb.write(fos);
	fos.close();
	return null;
	

}

//public static void keepFileOnServer(String args[]) throws SftpException, FileNotFoundException {
//
//	 JSch jsch = new JSch();
//	    Session session = null;
//	    try {
//           session = jsch.getSession("50004561", "10.36.20.47", 22);
//
//	        session.setConfig("StrictHostKeyChecking", "no");
//	        session.setPassword("Rocky@123");
//	        session.connect();
//	        System.out.println("Connected to session successfully");
//	        Channel channel = session.openChannel("sftp");
//	        channel.connect();
//	        System.out.println("Connected to Channel successfully");
//	        ChannelSftp sftpChannel = (ChannelSftp) channel;
//
//	        // this will read file with the name test.txt and write to remote directory
//	        sftpChannel.cd("/app/vector/data_files/njtp_qatp");
//	        File f = new File("E:/TSG/Workspace/App/WebApp-01/Files/v20170727000037003.tr");
//	        sftpChannel.put(new FileInputStream(f), f.getName()); // here you can also change the target file name by replacing f.getName() with your choice of name
//
//	        sftpChannel.exit();
//	        session.disconnect();
//	    } catch (JSchException e) {
//	        e.printStackTrace();  
//	    }
//}


public static String setLength(String Orgstr,int newlength)
{
	String newWord = Orgstr;
    for(int count = Orgstr.length(); count < newlength; count++) {
        newWord = newWord+" ";
    }
    return newWord;
	
}




public void createFile(int agency) throws EncryptedDocumentException, InvalidFormatException, IOException, SQLException, InterruptedException, org.apache.poi.openxml4j.exceptions.InvalidFormatException
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
     
}
}
  

