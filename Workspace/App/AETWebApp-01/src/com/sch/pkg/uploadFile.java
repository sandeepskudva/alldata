package com.sch.pkg;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Random;
import java.util.TimeZone;
import org.apache.poi.EncryptedDocumentException;
import com.Ostermiller.util.LineEnds;
import com.github.javafaker.Faker;
import com.jcraft.jsch.Channel;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellNotAvailableException;
import com.profesorfalken.jpowershell.PowerShellResponse;
import com.sun.media.sound.InvalidFormatException;

public class uploadFile {
	static String ipaddress = "10.36.20.51";

	public static String feedFile(String Filedate,String filetime, String Txndate,int count,String agencyname)  {

	
	
  
	
	try {
		
		String[] s1 =Txndate.split("-");
		Txndate = s1[0]+s1[1]+s1[2];	
		System.out.println(Txndate);
		
		String[] s2 =Filedate.split("-");
		System.out.println(s1[2]+s2[1]+s2[0]);
		
		
		common.ExcelWrite("Inputs", "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", 3, 11, count+"");
		
		
		common.ExcelWrite("Inputs", "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", 3, 64, Txndate);
		common.ExcelWrite("Inputs", "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", 3, 68, Txndate);		
		common.ExcelWrite("Data3", "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", 2, 1, s2[2]);
		common.ExcelWrite("Data3", "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", 3, 1, s2[1]);
		common.ExcelWrite("Data3", "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", 4, 1, s2[0]);
		common.ExcelWrite("Data3", "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", 5, 1, filetime);
		
		
		
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 return "";
	
	}
	
	
	
	public static String createFile() throws EncryptedDocumentException, InvalidFormatException, org.apache.poi.openxml4j.exceptions.InvalidFormatException, IOException, ParseException, SQLException
	{
		String agencyname = getagencyselection();
		
		
		if(agencyname.equals("NJTP"))
		{
			
			
			createNJTPVtrFile();
		}
		else if(agencyname.equals("GSP"))
		{
			createGSPVtrFile();
		}
		else if(agencyname.equals("DRJ"))
		{
			createDRJVtrFile();
		}
		else if(agencyname.equals("DRBA"))
		{
			createVtrFileDRBA();
		}
		else if(agencyname.equals("DRPA"))
		{
			createVtrFileDRPA();
		}
		else if(agencyname.equals("SJTA"))
		{
			createVtrFileSJTA();
		}
		else if(agencyname.equals("CAPEMAY"))
		{
			createVtrFileCAPEMAY();
		}
		     
		return "";
	}
	
	public static String Enteragencyselection(String agencyname) throws EncryptedDocumentException, InvalidFormatException, org.apache.poi.openxml4j.exceptions.InvalidFormatException, IOException
	{
		PrintWriter writer = new PrintWriter("E:\\TSG\\Workspace\\App\\WebApp-01\\Agency.txt", "UTF-8");
		writer.println(agencyname);		
		writer.close();
		return "";
	}
	
	public static String getagencyselection() throws EncryptedDocumentException, InvalidFormatException, org.apache.poi.openxml4j.exceptions.InvalidFormatException, IOException
	{
		
		BufferedReader br = new BufferedReader(new FileReader("E:\\TSG\\Workspace\\App\\WebApp-01\\Agency.txt"));
	    try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            line = br.readLine();
	        }
	        return sb.toString();
	    } finally {
	        br.close();
	    }
	}
	
	public static String convertToUnix(String filename)  
	{
		 
		 File f = new File("E:\\TSG\\Workspace\\App\\WebApp-01\\Files\\"+filename);
		 int style = LineEnds.STYLE_UNIX;
		 try {
			LineEnds.convert(f, style);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 try {
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		return "";
	}
	
	
	public static String getFileName() throws EncryptedDocumentException, InvalidFormatException, org.apache.poi.openxml4j.exceptions.InvalidFormatException, IOException
	{
		String agencyname = getagencyselection();
		String filename =common.ExcelRead("Data3", "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", 24, 1);
		System.out.println(filename);
		return filename;
		
	}
	
	public static String insertxferinFile(String xfer) throws EncryptedDocumentException, InvalidFormatException, org.apache.poi.openxml4j.exceptions.InvalidFormatException, IOException, InterruptedException
	{
		String agencyname = getagencyselection();
		common.ExcelWrite("Data3", "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", 6, 1,xfer);	
		System.out.println("Written to excel :"+xfer);
		
		return "";
		
	}
	
	public static String getxferid(String filename) throws SQLException
	{
		
		
		
			DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver()); 
		    String dbURL = "jdbc:oracle:thin:@10.36.20.51:1521/njrbtst";
		    String strUserID = "pbatch";
		    String strPassword = "batchp"; 
		    Connection c=DriverManager.getConnection(dbURL,strUserID,strPassword);
		    
		    String Statement = "select XFER_CONTROL_ID from t_xfer_control where XFER_FILE_NAME = '"+filename+"'"; 
		    
		    ResultSet rs = c.createStatement().executeQuery(Statement);
		    String xfer = null;
		   
		    while(rs.next()){
		    	xfer =rs.getString("XFER_CONTROL_ID");  
		    }
		   
		    if(xfer==null)
		    {	
		    	xfer = "NULL";		  

		    }
		  	    
			c.close();
			return xfer;
		}
	
	
	public static String getViol_tx_status(String xferid) throws SQLException
	{
		
		
		
			DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver()); 
		    String dbURL = "jdbc:oracle:thin:@(DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST = "+ipaddress+")(PORT = 1521)) (CONNECT_DATA =(SERVER = DEDICATED)(SERVICE_NAME = njrbtst)))";
		    String strUserID = "pbatch";
		    String strPassword = "batchp"; 
		    Connection c=DriverManager.getConnection(dbURL,strUserID,strPassword);
		    
		    String Statement = "select viol_tx_status from  t_viol_tx where viol_tx_status not in  (162,163,127) and extern_file_id = "+xferid+" and rownum =1"; 
		    
		    ResultSet rs = c.createStatement().executeQuery(Statement);
		    String vts = null;
		   
		    while(rs.next()){
		    	vts =rs.getString("VIOL_TX_STATUS");  
		    }
		    System.out.println(vts);
		    if(vts==null)
		    {	
		    	vts = "NULL";		  

		    }
		  	    
			c.close();
			return vts;
		}
	
	public static String getCitationStatus(String xferid) throws SQLException
	{
		
		
		
			DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver()); 
		    String dbURL = "jdbc:oracle:thin:@(DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST = "+ipaddress+")(PORT = 1521)) (CONNECT_DATA =(SERVER = DEDICATED)(SERVICE_NAME = njrbtst)))";
		    String strUserID = "pbatch";
		    String strPassword = "batchp"; 
		    Connection c=DriverManager.getConnection(dbURL,strUserID,strPassword);
		    
		    String Statement = "select citation_level_cd from v_citation_detail where LANE_TX_ID in (select LANE_TX_ID from t_viol_tx where extern_file_id = '"+xferid+"') and rownum =1"; 
		    
		    ResultSet rs = c.createStatement().executeQuery(Statement);
		    String vts = null;
		   
		    while(rs.next()){
		    	vts =rs.getString("CITATION_LEVEL_CD");  
		    }
		    System.out.println(vts);
		    if(vts==null)
		    {	
		    	vts = "NULL";		  

		    }
		  	    
			c.close();
			return vts;
		}
	
	
	public static String verifyPlate(String Plate_state,String plate_number) throws SQLException
	{
		
		
		    System.out.println("Veriying state : "+Plate_state);
		    System.out.println("Veriying number : "+plate_number);
		    
		    		    	
		    
			DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver()); 
		    String dbURL = "jdbc:oracle:thin:@(DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST = "+ipaddress+")(PORT = 1521)) (CONNECT_DATA =(SERVER = DEDICATED)(SERVICE_NAME = njrbtst)))";
		    String strUserID = "pbatch";
		    String strPassword = "batchp"; 
		    Connection c=DriverManager.getConnection(dbURL,strUserID,strPassword);
		    
		    String Statement1 = "select count(plate_number) from v_vehicle where plate_state ='"+Plate_state+"' and plate_number ='"+plate_number+"'"; 
		    String Statement2 = "select count(plate_number) from t_oa_plates where plate_state ='"+Plate_state+"' and plate_number ='"+plate_number+"'"; 
		    
		    ResultSet rs1 = c.createStatement().executeQuery(Statement1);
		    ResultSet rs2= c.createStatement().executeQuery(Statement2);
		    String hplatecnt = null;
		    String aplatecnt = null;
		   
		    while(rs1.next()){
		    	hplatecnt =rs1.getString("count(plate_number)");  
		    }
		    
		    while(rs2.next()){
		    	aplatecnt =rs2.getString("count(plate_number)");  
		    }
		    System.out.println(hplatecnt+aplatecnt);
		    
		  	int cnt = Integer.parseInt(hplatecnt)+Integer.parseInt(aplatecnt);   
			c.close();
			return cnt+"";
		  
		}
	
	
	
	
	
	public static String insertxferid(String filename) throws SQLException, EncryptedDocumentException, InvalidFormatException, org.apache.poi.openxml4j.exceptions.InvalidFormatException, IOException
	{
		
		
		
		DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver()); 
			   String dbURL = "jdbc:oracle:thin:@(DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST = "+ipaddress+")(PORT = 1521)) (CONNECT_DATA =(SERVER = DEDICATED)(SERVICE_NAME = njrbtst)))";
		    String strUserID = "pbatch";
		    String strPassword = "batchp"; 
		    Connection c=DriverManager.getConnection(dbURL,strUserID,strPassword);
		    String agencyname = getagencyselection();
		    String pathname = null;
		    String Statement = null;
		    
		    if(agencyname.equals("NJTP"))
		    {
		    	 pathname = "njtp";
		    	 Statement = "insert into t_xfer_control (XFER_CONTROL_ID, XFER_FILE_ID, XFER_FILE_NAME, DATE_CREATED, TIME_CREATED, FILE_SIZE, FILE_CHECKSUM, NUM_RECS, HASH_TOTAL, XFER_PATH, XFER_XMIT_STATUS, XFER_RETRY, UPDATE_TS, FILE_TYPE) values ((select Max( XFER_CONTROL_ID) + 1 from t_xfer_control where length( XFER_CONTROL_ID) = 8),711, '"+filename+"', SYSDATE, '08:00:13', 3857466, 0, 9, 0, '/app/vector/data_files/"+pathname+"_qatp', 'S', '0', SYSDATE, 'tr')";
		    	
		    }
		    else if(agencyname.equals("GSP"))
		    {
		    	pathname = "njha";
		    	Statement = "insert into t_xfer_control (XFER_CONTROL_ID, XFER_FILE_ID, XFER_FILE_NAME, DATE_CREATED, TIME_CREATED, FILE_SIZE, FILE_CHECKSUM, NUM_RECS, HASH_TOTAL, XFER_PATH, XFER_XMIT_STATUS, XFER_RETRY, UPDATE_TS, FILE_TYPE) values ((select Max( XFER_CONTROL_ID) + 1 from t_xfer_control where length( XFER_CONTROL_ID) = 8),711, '"+filename+"', SYSDATE, '08:00:13', 3857466, 0, 9, 0, '/app/vector/data_files/"+pathname+"_qatp', 'S', '0', SYSDATE, 'tr')";
		    }
		    else if(agencyname.equals("DRJ"))
		    {
		    	 pathname = "drj";
		    	 Statement = "insert into t_xfer_control (XFER_CONTROL_ID, XFER_FILE_ID, XFER_FILE_NAME, DATE_CREATED, TIME_CREATED, FILE_SIZE, FILE_CHECKSUM, NUM_RECS, HASH_TOTAL, XFER_PATH, XFER_XMIT_STATUS, XFER_RETRY, UPDATE_TS, FILE_TYPE) values ((select Max( XFER_CONTROL_ID) + 1 from t_xfer_control where length( XFER_CONTROL_ID) = 8),711, '"+filename+"', SYSDATE, '08:00:13', 3857466, 0, 9, 0, '/app/vector/data_files/"+pathname+"_qatp', 'S', '0', SYSDATE, 'VIO')";
		    }
		    else if(agencyname.equals("DRBA"))
		    {
		    	 pathname = "drba";
		    	 Statement = "insert into t_xfer_control (XFER_CONTROL_ID, XFER_FILE_ID, XFER_FILE_NAME, DATE_CREATED, TIME_CREATED, FILE_SIZE, FILE_CHECKSUM, NUM_RECS, HASH_TOTAL, XFER_PATH, XFER_XMIT_STATUS, XFER_RETRY, UPDATE_TS, FILE_TYPE) values ((select Max( XFER_CONTROL_ID) + 1 from t_xfer_control where length( XFER_CONTROL_ID) = 8),711, '"+filename+"', SYSDATE, '08:00:13', 3857466, 0, 9, 0, '/app/vector/data_files/"+pathname+"_qatp', 'S', '0', SYSDATE, 'ST')";
		    }
		    else if(agencyname.equals("DRPA"))
		    {
		    	 pathname = "drpa";
		    	 Statement = "insert into t_xfer_control (XFER_CONTROL_ID, XFER_FILE_ID, XFER_FILE_NAME, DATE_CREATED, TIME_CREATED, FILE_SIZE, FILE_CHECKSUM, NUM_RECS, HASH_TOTAL, XFER_PATH, XFER_XMIT_STATUS, XFER_RETRY, UPDATE_TS, FILE_TYPE) values ((select Max( XFER_CONTROL_ID) + 1 from t_xfer_control where length( XFER_CONTROL_ID) = 8),711, '"+filename+"', SYSDATE, '08:00:13', 3857466, 0, 9, 0, '/app/vector/data_files/"+pathname+"_qatp', 'S', '0', SYSDATE, 'VIO')";
		    }
		    else if(agencyname.equals("SJTA"))
		    {
		    	 pathname = "njsj";
		    	 Statement = "insert into t_xfer_control (XFER_CONTROL_ID, XFER_FILE_ID, XFER_FILE_NAME, DATE_CREATED, TIME_CREATED, FILE_SIZE, FILE_CHECKSUM, NUM_RECS, HASH_TOTAL, XFER_PATH, XFER_XMIT_STATUS, XFER_RETRY, UPDATE_TS, FILE_TYPE) values ((select Max( XFER_CONTROL_ID) + 1 from t_xfer_control where length( XFER_CONTROL_ID) = 8),711, '"+filename+"', SYSDATE, '08:00:13', 3857466, 0, 9, 0, '/app/vector/data_files/"+pathname+"_qatp', 'S', '0', SYSDATE, 'tr')";
		    }
		    else if(agencyname.equals("CAPEMAY"))
		    {
		    	 pathname = "njsj";
		    	 Statement = "insert into t_xfer_control (XFER_CONTROL_ID, XFER_FILE_ID, XFER_FILE_NAME, DATE_CREATED, TIME_CREATED, FILE_SIZE, FILE_CHECKSUM, NUM_RECS, HASH_TOTAL, XFER_PATH, XFER_XMIT_STATUS, XFER_RETRY, UPDATE_TS, FILE_TYPE) values ((select Max( XFER_CONTROL_ID) + 1 from t_xfer_control where length( XFER_CONTROL_ID) = 8),711, '"+filename+"', SYSDATE, '08:00:13', 3857466, 0, 9, 0, '/app/vector/data_files/"+pathname+"_qatp', 'S', '0', SYSDATE, 'tr')";
		    }
		   
		    System.out.println(Statement);
		    c.createStatement().executeQuery(Statement);
	    	c.createStatement().executeQuery("Commit");
	    	
	    	return "";
	   }
	
	public static String threetofour(String xferid) throws SQLException
	{
		
		
		   System.out.println("Exfer id is  "+xferid);
		   DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver()); 
			String dbURL = "jdbc:oracle:thin:@(DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST = "+ipaddress+")(PORT = 1521)) (CONNECT_DATA =(SERVER = DEDICATED)(SERVICE_NAME = njrbtst)))";
		    String strUserID = "pbatch";
		    String strPassword = "batchp"; 
		    Connection c=DriverManager.getConnection(dbURL,strUserID,strPassword);
		    
		    
		    
		       
		   		    
		    String Statement = "update t_viol_tx set viol_tx_status = 154 where extern_file_id = '"+xferid+"'";
		    c.createStatement().executeQuery(Statement);
	    	c.createStatement().executeQuery("Commit");
	    	
	    	Statement = "update t_viol_tx set viol_tx_status = 4,event_type=3 where extern_file_id = '"+xferid+"'";
		    c.createStatement().executeQuery(Statement);
	    	c.createStatement().executeQuery("Commit");
	    	
		   
	    	
	    	return "";
	   }
	
	
	
	public static String fileTransfer(String ip,String username,String password,String path,String filename) throws SftpException, FileNotFoundException {

   	 JSch jsch = new JSch();
   	    Session session = null;
   	    try {
               session = jsch.getSession(username,ip, 22);

   	        session.setConfig("StrictHostKeyChecking", "no");
   	        session.setPassword(password);
   	        session.connect();
   	        System.out.println("Connected to session successfully");
   	        Channel channel = session.openChannel("sftp");
   	        channel.connect();
   	        System.out.println("Connected to Channel successfully");
   	        ChannelSftp sftpChannel = (ChannelSftp) channel;

   	        // this will read file with the name test.txt and write to remote directory
   	        sftpChannel.cd(path);
   	        File f = new File("E:/TSG/Workspace/App/WebApp-01/Files/"+filename);
   	        System.out.println("Filename is : "+f.getName());
   	        sftpChannel.put(new FileInputStream(f), f.getName()); // here you can also change the target file name by replacing f.getName() with your choice of name

   	        sftpChannel.exit();
   	        session.disconnect();
   	    } catch (JSchException e) {
   	        e.printStackTrace();  
   	    }
   	return "";
   }
	
	public static String runCommand(String command) throws InterruptedException, JSchException, IOException, EncryptedDocumentException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {

	   	    JSch jsch = new JSch();
			Session session = null;
			
		  
		    	
		    	session = jsch.getSession("50004561", "10.36.20.47", 22);

		        session.setConfig("StrictHostKeyChecking", "no");
		        String password = common.ExcelRead("Sheet1", "E:\\Crede\\Crede.xlsx", 1, 1);
		        session.setPassword(password);
		        session.connect();
		        System.out.println("Connected to session successfully");
		    	
		        Channel channel=session.openChannel("shell");
	            OutputStream ops = channel.getOutputStream();
	            PrintStream ps = new PrintStream(ops, true);
	            
	            channel.connect();
	            ps.println("sudo su - pbatch"); 
	            Thread.sleep(1000);
	            ps.println("q");
	            Thread.sleep(1000);
	           // ps.println("cd /app/vector/shell");
	            ps.println(command);
	            ps.close();
	            
	            InputStream in=channel.getInputStream();
	             byte[] bt=new byte[1024];


	             while(true)
	             {

	             while(in.available()>0)
	             {
	             int i=in.read(bt, 0, 1024);
	             if(i<0)
	              break;
	                String str=new String(bt, 0, i);
	              //displays the output of the command executed.
	                System.out.print(str);


	             }
	             if(channel.isClosed())
	             {

	                 break;
	            }
	             Thread.sleep(1000);
	             channel.disconnect();
	             session.disconnect(); 
	             
	             }

	             return "";   
		    } 
		    
	
	public static String setplateQuery(String xfer, String[] platestate,String[] platenumber) throws InterruptedException, JSchException, IOException, NumberFormatException, EncryptedDocumentException, org.apache.poi.openxml4j.exceptions.InvalidFormatException, SQLException {

	   	  
		String agencyname = getagencyselection();
		int totalviolations = Integer.parseInt(common.ExcelRead("PlateDetails", "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", 1, 7));
		
		
		DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver()); 
		String dbURL = "jdbc:oracle:thin:@(DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST = "+ipaddress+")(PORT = 1521)) (CONNECT_DATA =(SERVER = DEDICATED)(SERVICE_NAME = njrbtst)))";
	    String strUserID = "pbatch";
	    String strPassword = "batchp"; 
	    Connection c=DriverManager.getConnection(dbURL,strUserID,strPassword);
	    
	    String Statement1 = "select LANE_TX_ID from t_viol_tx where extern_file_id ='"+xfer+"'"; 
	   
	    
	    ResultSet rs1 = c.createStatement().executeQuery(Statement1);
	    String lanetxid[] = new String[totalviolations];
	    int i=0;
	    
	    while(rs1.next()){
	    	lanetxid[i] =rs1.getString("LANE_TX_ID");  
	    	i++;
	    }
	    
	    int k=0;
	    int l=0;
	    int number = totalviolations/platenumber.length;
	    System.out.println("PLATE LENGTH IS "+number);
	    for(int j=0;j<totalviolations;j++)
	    {   
	    	if(k==number)
	    	{
	    		k=0;
	    		l++;
	    	}
	    	k++;
//	    	common.ExcelWrite("PlateDetails", "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", j+1, 0,lanetxid[j]);	    	
//	    	common.ExcelWrite("PlateDetails", "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", j+1, 1,platestate[l]);
//	    	common.ExcelWrite("PlateDetails", "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", j+1, 2,platenumber[l]);
	    	
	    	
	    	String Statement2 = "update t_viol_tx set viol_tx_status =5,plate_state = '"+platestate[l]+"'  , plate_number = '"+platenumber[l]+"' where lane_tx_id ='"+lanetxid[j]+"'";
	    	c.createStatement().executeQuery(Statement2);
	    	c.createStatement().executeQuery("Commit");
	    	
	    }    
		
	
	               c.close();
		    	   return "";   
		    } 
		    
	
	
public static String createDMVLine(int i,String platestate,String platenumber,String lanetxid,String lastname,String middlename,String firstname,String Address1,String Address2,String City,String State,String Zip) throws SQLException  {

			DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver()); 
			String dbURL = "jdbc:oracle:thin:@(DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST = "+ipaddress+")(PORT = 1521)) (CONNECT_DATA =(SERVER = DEDICATED)(SERVICE_NAME = njrbtst)))";
			String strUserID = "pbatch";
			String strPassword = "batchp"; 
			Connection c=DriverManager.getConnection(dbURL,strUserID,strPassword);
			String Statement = "select SOURCE_FORMAT from t_dmv_name_policy where SOURCE_IND = 'D' and State ='"+State+"'"; 
			ResultSet rs = c.createStatement().executeQuery(Statement);
			
            String SOURCE_FORMAT="LFM";
			
			while(rs.next()){
				SOURCE_FORMAT=rs.getString("SOURCE_FORMAT");  
		    	
		    }
			
			String CONST1 = "2017052679838432000163201714120161412017141201729000 14822   00VAN     99945   201703310000000000000000021";
		    String CONST2 = "00000000          00000000          000000000000   D";	    
		    String zip2="";
		    String VIN = "FER0012";
		    String License = "NJ1234";
		    String dmvtext =null;
		    String name =null;
			
			if(SOURCE_FORMAT.equals("LFM"))
			{
				name = lastname + " " + firstname + " " + middlename;
					
			}
			else
			{   name = firstname + " " + middlename+ " " +lastname;
				
			}
		    
			dmvtext = common.setLength(platestate,2)+common.setLength(platenumber,10)+"00"+common.setLength(platestate,2)+common.setLength(platenumber,8)+"00"+common.setLength(lanetxid,12)+"NJT"+CONST1+common.setLength(lastname,20)+common.setLength(name,35)+common.setLength(Address1,35)+common.setLength(Address2,35)+common.setLength(City,30)+common.setLength(State,2)+common.setLength(Zip,5)+common.setLength(zip2,4)+common.setLength(VIN,30)+common.setLength(License,30)+CONST2;
		    
		    
		    
		    System.out.println(dmvtext);
		    System.out.println("Round "+i+" Completed");
	    	
	    
		
		
		
		return dmvtext.toUpperCase();   
} 
	

public static  String createDMVFile(String[] dmvfile) throws IOException, InterruptedException{

	
	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYYMMdd");
	 String todaydate = LocalDate.now().format(formatter);
	 String filename ="NJ_dmv_response_"+todaydate+".dat";
	 String filepath ="E:\\TSG\\Workspace\\App\\WebApp-01\\Files\\"+filename;
	 
	 File f = new File(filepath);
	 if(f.exists() && !f.isDirectory()) { 
		    f.delete();
		}
	
	 
	 BufferedWriter bw = new BufferedWriter(new FileWriter(filepath, true));
	 for(int i=0;i<dmvfile.length;i++)
	 {
		 
		 bw.write(dmvfile[i]+"\n");
	 //bw.newLine();
	 }
	 System.out.println("File created");
	 bw.flush();
	 bw.close();
	
	 
	  
	    

return filename;   
} 
	



	
	
public static String licaRetry(String xfer) throws InterruptedException, JSchException, IOException, NumberFormatException, EncryptedDocumentException, org.apache.poi.openxml4j.exceptions.InvalidFormatException, SQLException {

	   	  	
		
		DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver()); 
		String dbURL = "jdbc:oracle:thin:@(DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST = "+ipaddress+")(PORT = 1521)) (CONNECT_DATA =(SERVER = DEDICATED)(SERVICE_NAME = njrbtst)))";
	    String strUserID = "pbatch";
	    String strPassword = "batchp"; 
	    Connection c=DriverManager.getConnection(dbURL,strUserID,strPassword);
	    
	    String Statement1 = "update t_viol_lica_queue set processed_Date = trunc(sysdate)-1 where LANE_TX_ID in (select LANE_TX_ID from t_viol_tx where extern_file_id ='"+xfer+"')"; 
	    String Statement2 = "update t_Viol_Tx_Event set event_timestamp = event_timestamp-4 where LANE_TX_ID in (select LANE_TX_ID from t_viol_tx where extern_file_id ='"+xfer+"')"; 
	   
	    c.createStatement().executeQuery(Statement1);
	    c.createStatement().executeQuery("Commit");	  
	    c.createStatement().executeQuery(Statement2);
	    c.createStatement().executeQuery("Commit");	 
		
	
	    c.close();
		return "";   
    } 


public static String imageIns(String lanetxid) throws InterruptedException,  IOException, NumberFormatException, SQLException, ParseException, EncryptedDocumentException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {

  	System.out.println("lanetxid is "+lanetxid);
  	 String imagefile ="";
	
	DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver()); 
	String dbURL = "jdbc:oracle:thin:@(DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST = "+ipaddress+")(PORT = 1521)) (CONNECT_DATA =(SERVER = DEDICATED)(SERVICE_NAME = njrbtst)))";
    String strUserID = "pbatch";
    String strPassword = "batchp"; 
    Connection c=DriverManager.getConnection(dbURL,strUserID,strPassword);
    String Statement = "select tx_timestamp,tx_date,lane_id,tx_seq_number,plate_state,plate_number,plaza_id,dmv_plate_type from t_viol_tx where lane_tx_id = '"+lanetxid+"'";
    ResultSet rs = c.createStatement().executeQuery(Statement);
    
    String Statement1 = "select COUNT(LANE_TX_ID) from t_viol_image_tx where lane_tx_id  = '"+lanetxid+"'";
    ResultSet rs1 = c.createStatement().executeQuery(Statement1);
    
    
    int count =0;
    while(rs1.next()){
    
    count = rs1.getInt("COUNT(LANE_TX_ID)");
    System.out.println("Count is "+count);
   
    }
	
    if(count==0)
    {	
    
	Time tx_timestamp =null;
	Date tx_date =null;
	String laneid =null;
	String tx_seq_number=null;
	String plate_state=null;
	String plate_number = null;
	String plaza_id =null;
	String dmv_plate_type=null;		
	
    
	while(rs.next()){
	
		tx_timestamp =rs.getTime("TX_TIMESTAMP");
		tx_date =rs.getDate("TX_DATE");
		laneid =rs.getString("LANE_ID");
		tx_seq_number=rs.getString("TX_SEQ_NUMBER");
		plate_state=rs.getString("PLATE_STATE");
		plate_number = rs.getString("PLATE_NUMBER");
		plaza_id =rs.getString("PLAZA_ID");
		dmv_plate_type=rs.getString("DMV_PLATE_TYPE");	
    	 
    	
    }   
	
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		df.setTimeZone(TimeZone.getTimeZone("EST"));
		String date = df.format(tx_timestamp);
		String format = date.toString();
		String txdate = tx_date.toString();
		
		SimpleDateFormat df1 = new SimpleDateFormat("MM/dd/YYYY");
	    String trandate = df1.format(tx_date);
		
		SimpleDateFormat df2 = new SimpleDateFormat("dd-MMM-YYYY hh.mm.SS a");
		df2.setTimeZone(TimeZone.getTimeZone("EST"));
		String datetime = df2.format(tx_timestamp);
		Random r = new Random();
		int random  =r.nextInt(50);;
    
		
		String agencyname = getagencyselection();
		imagefile = "01318E_18eE"+format+random+agencyname;		
		String zipfile = agencyname+"_"+format+random+".ZIP";
    
        

    System.out.println(zipfile);
   String insertscript0 ="BEGIN "; 
   String insertscript1 ="insert into t_viol_image_tx(LANE_TX_ID,in_file_name,out_file_name,lane_image_cnt,proc_image_cnt,receive_date, process_date,lane_id,tx_seq_number,process_desc,ocr_conf,ocr_plate_state,ocr_plate_number,ocr_plate_type,ocr_plate_type_conf,ocr_status,tx_date,zip_file_name,tx_timestamp,update_ts,axle_count,plaza_id,img_file_index,IMP_TYPE) values";
   String insertscript2="('"+lanetxid+"','"+imagefile+"','"+imagefile+"','1','1',to_date('"+trandate+"','mm/dd/yyyy'),to_date('"+trandate+"','mm/dd/yyyy'),'"+laneid+"','"+tx_seq_number+"','IGR','850','"+plate_state+"','"+plate_number+"','"+dmv_plate_type+"','850','30',to_date('"+trandate+"','mm/dd/yyyy'),'"+zipfile+"','"+datetime+"','"+datetime+"','2','"+plaza_id+"','1','2')" ; 
   String insertscript3 ="; EXCEPTION WHEN OTHERS THEN NULL; END;"; 
   String statement2 = insertscript0+insertscript1+insertscript2+insertscript3;
   System.out.println(statement2);
   c.createStatement().executeQuery(statement2);
   c.createStatement().executeQuery("Commit");	  
      
   
    }  
   
  

    c.close();
	return imagefile;   
} 

public static String deleteQueue(String imagename) throws InterruptedException,  IOException, NumberFormatException, SQLException, ParseException {

  	
	
	DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver()); 
	String dbURL = "jdbc:oracle:thin:@(DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST = "+ipaddress+")(PORT = 1521)) (CONNECT_DATA =(SERVER = DEDICATED)(SERVICE_NAME = njrbtst)))";
    String strUserID = "pbatch";
    String strPassword = "batchp"; 
    Connection c=DriverManager.getConnection(dbURL,strUserID,strPassword);
    
   
   String statement3 = "delete from t_lpr_queue where CSC_LOOKUP_KEY in (select CSC_LOOKUP_KEY from t_xlpr_queue where IMG_FILE_NAME = '"+imagename+"')";
   c.createStatement().executeQuery(statement3);
   c.createStatement().executeQuery("Commit");   
   String statement4 = "delete from t_xlpr_queue where IMG_FILE_NAME = '"+imagename+"'";
   c.createStatement().executeQuery(statement4);
   c.createStatement().executeQuery("Commit");  
   String statement5 = "delete from t_h_xlpr_queue where IMG_FILE_NAME = '"+imagename+"'";
   c.createStatement().executeQuery(statement5);
   c.createStatement().executeQuery("Commit"); 
   
  

    c.close();
	return "";   
} 

public static String runSiebeljobP(String jobname)  {

	PowerShell powerShell = null;
	   try {
	       //Creates PowerShell session (we can execute several commands in the same session)
	       powerShell = PowerShell.openSession();
	       
	       //Execute a command in PowerShell session
	       PowerShellResponse response = powerShell.executeCommand("$pass = cat E:\\crede\\crede.txt | ConvertTo-SecureString -AsPlainText ???Force");
	       
	       //Print results
	       System.out.println("Command1 executed" + response.getCommandOutput());
	       
	       String username = "52003819";
	       //Execute another command in the same PowerShell session
	       response = powerShell.executeCommand("$cred = new-object -typename System.Management.Automation.PSCredential -argumentlist "+"TTTSSDEV\\"+username+""+",$pass");
	       
	       //Print results
	       System.out.println("Command2 executed:" + response.getCommandOutput());
	       
         response = powerShell.executeCommand("Invoke-Command -ComputerName 10.36.20.41 -ScriptBlock {cmd.exe /c 'D:\\vector\\com\\"+jobname+"1.bat'} -credential $cred");
	       
	       //Print results
	       System.out.println("Command3 executed:" + response.getCommandOutput());
	   } catch(PowerShellNotAvailableException ex) {
	       //Handle error when PowerShell is not available in the system
	       //Maybe try in another way?
	   } finally {
	       //Always close PowerShell session to free resources.
	       if (powerShell != null)
	         powerShell.close();
	   }
 	  
	
	
	    	   return "";   
	    } 
	    
public static String updateACK(String xfer) throws SQLException  {

	
	
	DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver()); 
	String dbURL = "jdbc:oracle:thin:@(DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST = "+ipaddress+")(PORT = 1521)) (CONNECT_DATA =(SERVER = DEDICATED)(SERVICE_NAME = njrbtst)))";
    String strUserID = "pbatch";
    String strPassword = "batchp"; 
    Connection c=DriverManager.getConnection(dbURL,strUserID,strPassword);
    
   
   String statement = "update siebel.s_order set PRICING_DT = trunc(sysdate)-31 , X_ACK_LEVEL=DLVRY_STATUS_CD where x_notice_num in (select citation_number from v_citation_detail where lane_tx_id in (select LANE_TX_ID from t_viol_tx where extern_file_id = '"+xfer+"'))";
   c.createStatement().executeQuery(statement);
   c.createStatement().executeQuery("Commit");      
   
  

    c.close();
	return "";   
} 
public static String getcitationlevel(String xferid) throws SQLException
{
	
	
	
		DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver()); 
	    String dbURL = "jdbc:oracle:thin:@(DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST = "+ipaddress+")(PORT = 1521)) (CONNECT_DATA =(SERVER = DEDICATED)(SERVICE_NAME = njrbtst)))";
	    String strUserID = "pbatch";
	    String strPassword = "batchp"; 
	    Connection c=DriverManager.getConnection(dbURL,strUserID,strPassword);
	    
	    String Statement = "select citation_level_cd from v_citation_detail where LANE_TX_ID in (select LANE_TX_ID from t_viol_tx where extern_file_id = '"+xferid+"') and rownum =1"; 
	    
	    ResultSet rs = c.createStatement().executeQuery(Statement);
	    String clc = null;
	   
	    while(rs.next()){
	    	clc =rs.getString("citation_level_cd");  
	    }
	    System.out.println(clc);
	    if(clc==null)
	    {	
	    	clc = "NULL";		  

	    }
	  	    
		c.close();
		return clc;
	}

public static int getPlatecount(String xferid) throws SQLException
{
	
	
	
		DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver()); 
	    String dbURL = "jdbc:oracle:thin:@(DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST = "+ipaddress+")(PORT = 1521)) (CONNECT_DATA =(SERVER = DEDICATED)(SERVICE_NAME = njrbtst)))";
	    String strUserID = "pbatch";
	    String strPassword = "batchp"; 
	    Connection c=DriverManager.getConnection(dbURL,strUserID,strPassword);
	    
	    String Statement = "select count(DISTINCT plate_number) from t_viol_tx where  extern_file_id = '"+xferid+"'"; 
	    
	    ResultSet rs = c.createStatement().executeQuery(Statement);
	    String cnt = null;
	   
	    while(rs.next()){
	    	cnt =rs.getString("count(DISTINCT plate_number)");  
	    }
	    System.out.println(cnt);
	    if(cnt==null)
	    {	
	    	cnt = "NULL";		  

	    }
	  	    
		c.close();
		return Integer.parseInt(cnt);
	}

public static String deleteDMVFiles() throws SQLException  {

	
	
	DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver()); 
	String dbURL = "jdbc:oracle:thin:@(DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST = "+ipaddress+")(PORT = 1521)) (CONNECT_DATA =(SERVER = DEDICATED)(SERVICE_NAME = njrbtst)))";
    String strUserID = "pbatch";
    String strPassword = "batchp"; 
    Connection c=DriverManager.getConnection(dbURL,strUserID,strPassword);
    
   
   String statement = "delete from t_dmv_file where FILE_DATE = trunc(sysdate)";
   String statement2 = "delete from t_dmv_file where FILE_DATE = trunc(sysdate)-1 and file_status =0";
   c.createStatement().executeQuery(statement);
   c.createStatement().executeQuery("Commit");     
   c.createStatement().executeQuery(statement2);
   c.createStatement().executeQuery("Commit"); 
   
  

    c.close();
	return "";   
} 


public static String deleteimage(String xfer) throws SQLException  {

	
	
	DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver()); 
	String dbURL = "jdbc:oracle:thin:@(DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST = "+ipaddress+")(PORT = 1521)) (CONNECT_DATA =(SERVER = DEDICATED)(SERVICE_NAME = njrbtst)))";
    String strUserID = "pbatch";
    String strPassword = "batchp"; 
    Connection c=DriverManager.getConnection(dbURL,strUserID,strPassword);
    
   
   String statement = "delete from t_viol_image_tx where LANE_TX_ID in (select LANE_TX_ID from t_viol_tx where extern_file_id ='"+xfer+"')";  
   c.createStatement().executeQuery(statement);
   c.createStatement().executeQuery("Commit");       

    c.close();
	return "";   
} 


public static String addresscleanse(String xfer) throws SQLException
{
	
	
	
	DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver()); 
		   String dbURL = "jdbc:oracle:thin:@(DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST = "+ipaddress+")(PORT = 1521)) (CONNECT_DATA =(SERVER = DEDICATED)(SERVICE_NAME = njrbtst)))";
	    String strUserID = "pbatch";
	    String strPassword = "batchp"; 
	    Connection c=DriverManager.getConnection(dbURL,strUserID,strPassword);
	    
        String Statement1 = "select ADDR_CLEANSED_FLAG from t_violator_info where LANE_TX_ID in (select LANE_TX_ID from t_viol_tx where extern_file_id = '"+xfer+"')";
	    
	    ResultSet rs = c.createStatement().executeQuery(Statement1);
	    String clc = null;
	   
	    while(rs.next()){
	    	clc =rs.getString("ADDR_CLEANSED_FLAG");  
	    }
	    
	    if(clc==null)
	    {	
	    String Statement2 = "update t_violator_info set ADDR_CLEANSED_FLAG ='Y' where LANE_TX_ID in (select LANE_TX_ID from t_viol_tx where extern_file_id = '"+xfer+"')";
	    c.createStatement().executeQuery(Statement2);
    	c.createStatement().executeQuery("Commit");
	    }
    	
    	return "";
   }
public static void createNJTPVtrFile() throws IOException, ParseException, EncryptedDocumentException, org.apache.poi.openxml4j.exceptions.InvalidFormatException, SQLException
{

	
	 String agencyname = getagencyselection();
	 String YYYY = common.ExcelRead("Data3", "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", 4, 1);
	 String MM = common.ExcelRead("Data3", "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", 3, 1);
	 String DD = common.ExcelRead("Data3", "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", 2, 1);
	 
	 String HHMMSS = common.ExcelRead("Data3", "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", 5, 1);
	 String header = "H012.0";
	 String xfer = common.ExcelRead("Data3", "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", 6, 1);
	  
	
	String headertext = null; 
    String Trailertext= null;
    String Filename = null;
    String RecordCount = common.ExcelRead("PlateDetails", "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", 1, 7);
    

    String xfer10 = "00" + xfer;
    String AgencyID = "003";	     
    
   
    String ARecordCount = "00000000".substring(RecordCount.length()) + RecordCount;
    String Trailer = "T";
    String checksum = "e2ad";
    String Fileext =".tr";
    Filename = "v" + YYYY + MM + DD + HHMMSS + AgencyID + Fileext; 
    String pathname = "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm";
    System.out.println(pathname);
    common.ExcelWrite("Data3", pathname, 24, 1,Filename);
    
    System.out.println(Filename);
    
    String filepath ="E:\\TSG\\Workspace\\App\\WebApp-01\\Files\\"+Filename;
    File file = new File(filepath);
    file.delete();
    BufferedWriter bw = new BufferedWriter(new FileWriter(filepath, true));
    headertext = header+ xfer10 + AgencyID + YYYY + MM+ DD + HHMMSS + ARecordCount;
    bw.write(headertext+"\n");
   // System.out.println(headertext);
    
    String DetailRecord0="D20330000000";
    int extref = randomnum();
    String entryplazalane = getPlazaLane();//"18E 18e";
    String DetailRecord1="1"+entryplazalane;
    String txdate = common.ExcelRead("Inputs", "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", 3, 64);
    String plazalane = getPlazaLane();//"012 18E";
    String DetailRecord2="0010084781900053581"+plazalane;
    String DetailRecord3 ="0002290178000.00000.00002.00002.00002.00030301007201000002031000********C***007***********03S0335R0000000000000000800000000000000010000000128088080";
    String DetailRecord=null;
    
    int count = Integer.parseInt(RecordCount);
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	String dateInString = txdate + getRandomTime();
	
	java.util.Date date = sdf.parse(dateInString);
	
	 int minsToAdd = 30; 
	Calendar calendar = Calendar.getInstance();
   calendar.setTime(date);
    for(int i=0;i<count;i++)
	   {	
   	 calendar.add(Calendar.MINUTE, minsToAdd);
        date = calendar.getTime();
        
        String Time1 = sdf.format(date); 
        extref++;
        DetailRecord = DetailRecord0 +extref +DetailRecord1 + Time1 +DetailRecord2 +Time1 + DetailRecord3;
   	 //System.out.println(DetailRecord);
   	 bw.write(DetailRecord+"\n");
	   }
     Trailertext = Trailer + ARecordCount + checksum;
       // System.out.println(Trailertext);
     bw.write(Trailertext+"\n");
     System.out.println("File created");
	 bw.flush();
	 bw.close();

}

public static void createGSPVtrFile() throws IOException, ParseException, EncryptedDocumentException, org.apache.poi.openxml4j.exceptions.InvalidFormatException, SQLException
{

	
	 String agencyname = getagencyselection();
	 String YYYY = common.ExcelRead("Data3", "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", 4, 1);
	 String MM = common.ExcelRead("Data3", "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", 3, 1);
	 String DD = common.ExcelRead("Data3", "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", 2, 1);
	 
	 String HHMMSS = common.ExcelRead("Data3", "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", 5, 1);
	 String header = "H012.0";
	 String xfer = common.ExcelRead("Data3", "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", 6, 1);
	  
	
	String headertext = null; 
    String Trailertext= null;
    String Filename = null;
    String RecordCount = common.ExcelRead("PlateDetails", "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", 1, 7);
    String NORACM = common.ExcelRead("PlateDetails", "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", 1, 8);

    String xfer10 = "00" + xfer;
    String AgencyID = "002";	     
    
   
    String ARecordCount = "00000000".substring(RecordCount.length()) + RecordCount;
    String Trailer = "T";
    String checksum = "e2ad";
    String Fileext =".tr";
    Filename = "v" + YYYY + MM + DD + HHMMSS + AgencyID + Fileext; 
    String pathname = "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm";
    System.out.println(pathname);
    common.ExcelWrite("Data3", pathname, 24, 1,Filename);
    
    System.out.println(Filename);
    
    String filepath ="E:\\TSG\\Workspace\\App\\WebApp-01\\Files\\"+Filename;
    File file = new File(filepath);
    file.delete();
    BufferedWriter bw = new BufferedWriter(new FileWriter(filepath, true));
    headertext = header+ xfer10 + AgencyID + YYYY + MM+ DD + HHMMSS + ARecordCount;
    bw.write(headertext+"\n");
   // System.out.println(headertext);
    
    String ACMRECORD ="2";
    if(NORACM.equals("ACM"))
    {	
     ACMRECORD ="1";
    }
    
    String DetailRecord0="D"+ACMRECORD+"0330000000";
    int extref = randomnum();
    String entryplazalane = getPlazaLane();//"18E 18e";
    String DetailRecord1="1"+entryplazalane;
    
    String txdate = common.ExcelRead("Inputs", "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", 3, 64);
    String plazalane = getPlazaLane();
    String DetailRecord2="0010084781900053581"+plazalane;
    String DetailRecord3 ="0002290178000.00000.00003.00002.00002.00030303007203000002031000********C***007***********01S0335R0000000000000000800000000000000010000000128088080";
    
    String DetailRecord=null;
    
    int count = Integer.parseInt(RecordCount);
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	String dateInString = txdate + getRandomTime();
	
	java.util.Date date = sdf.parse(dateInString);
	
	 int minsToAdd = 30; 
	Calendar calendar = Calendar.getInstance();
   calendar.setTime(date);
    for(int i=0;i<count;i++)
	   {	
   	 calendar.add(Calendar.MINUTE, minsToAdd);
        date = calendar.getTime();
        
        String Time1 = sdf.format(date); 
        System.out.println(Time1);
        extref++;
        DetailRecord = DetailRecord0 +extref +DetailRecord1 + Time1 +DetailRecord2 +Time1 + DetailRecord3;
   	 //System.out.println(DetailRecord);
   	 bw.write(DetailRecord+"\n");
	   }
     Trailertext = Trailer + ARecordCount + checksum;
       // System.out.println(Trailertext);
     bw.write(Trailertext+"\n");
     System.out.println("File created");
	 bw.flush();
	 bw.close();

}


public static void createDRJVtrFile() throws IOException, ParseException, EncryptedDocumentException, org.apache.poi.openxml4j.exceptions.InvalidFormatException
{

	String agencyname = getagencyselection();
	 String YYYY = common.ExcelRead("Data3", "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", 4, 1);
	 String MM = common.ExcelRead("Data3", "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", 3, 1);
	 String DD = common.ExcelRead("Data3", "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", 2, 1);
	 
	 String HHMMSS = common.ExcelRead("Data3", "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", 5, 1);
	
	 String xfer = common.ExcelRead("Data3", "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", 6, 1);
	 String header = "H";
	
	 String headertext = null; 
    String Trailertext= null;
    String Filename = null;
    
    String RecordCount = common.ExcelRead("PlateDetails", "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", 1, 7);
    
    String AgencyID = "020";	     
    
   
    String ARecordCount = "000000".substring(RecordCount.length()) + RecordCount;
    String Trailer = "E";
    String serial="04";
    String Fileext =".VIO";
    Filename = YYYY + MM + DD + serial + Fileext;  
    String pathname = "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm";
    System.out.println(pathname);
    common.ExcelWrite("Data3", pathname, 24, 1,Filename);
    System.out.println(Filename);
    
    String filepath ="E:\\TSG\\Workspace\\App\\WebApp-01\\Files\\"+Filename;
    File file = new File(filepath);
    file.delete();
    BufferedWriter bw = new BufferedWriter(new FileWriter(filepath, true));
     
   
	headertext = header+ ARecordCount + YYYY + MM+ DD + HHMMSS + serial ;
    bw.write(headertext+"\n");
    System.out.println(headertext);
    
    String DetailRecord0="[B";
    int extref = randomnum();
    System.out.println(extref);
    String DetailRecord1="*******10**************000000010000002020200000000**50Y00000000000000000000000000000002+050";
    String txdate = common.ExcelRead("Inputs", "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", 3, 64);
    String DetailRecord2="NHL01WR00010*****00000000000";
    String DetailRecord3 ="000]";
    String DetailRecord=null;
    
    int count = Integer.parseInt(RecordCount);
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	String dateInString = txdate + "000000";
	java.util.Date date = sdf.parse(dateInString);
	
	 int minsToAdd = 30; 
	Calendar calendar = Calendar.getInstance();
   calendar.setTime(date);
    for(int i=0;i<count;i++)
	   {	
   	 calendar.add(Calendar.MINUTE, minsToAdd);
        date = calendar.getTime();
        
        String Time1 = sdf.format(date);
        extref++;
   	 DetailRecord = DetailRecord0 + Time1+ DetailRecord1  + Time1 +DetailRecord2  + extref + DetailRecord3;
   	 System.out.println(DetailRecord);
   	 bw.write(DetailRecord+"\n");
	   }
	    
    
     Trailertext = Trailer;  

     System.out.println(Trailertext);
     bw.write(Trailertext+"\n");
     System.out.println("File created");
	 bw.flush();
	 bw.close();

}


public static void createVtrFileDRBA() throws IOException, ParseException, EncryptedDocumentException, org.apache.poi.openxml4j.exceptions.InvalidFormatException
{

	String agencyname = getagencyselection();
	 String YYYY = common.ExcelRead("Data3", "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", 4, 1);
	 String MM = common.ExcelRead("Data3", "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", 3, 1);
	 String DD = common.ExcelRead("Data3", "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", 2, 1);
	 
	 String HHMMSS = common.ExcelRead("Data3", "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", 5, 1);
	
	 String xfer = common.ExcelRead("Data3", "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", 6, 1);
	 String header = "H";
	
	 String headertext = null; 
     String Trailertext= null;
     String Filename = null;     
     String RecordCount = common.ExcelRead("PlateDetails", "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", 1, 7);
     
     int rec = Integer.parseInt(RecordCount) +2;
   
    String ARecordCount = "0000000000".substring((rec+"").length()) + rec;
    String Trailer = "T";
    String Fileext =".ST";
    Filename = YYYY + MM + DD + HHMMSS + "64432"+ Fileext;    
    System.out.println(Filename);
    
    String filepath ="E:\\TSG\\Workspace\\App\\WebApp-01\\Files\\"+Filename;
    java.io.File file = new java.io.File(filepath);
    file.delete(); 
    String pathname = "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm";
    common.ExcelWrite("Data3", pathname, 24, 1,Filename);
    BufferedWriter bw = new BufferedWriter(new FileWriter(filepath, true));
    headertext = header+ "0000064432" + YYYY + MM+ DD + HHMMSS;
    bw.write(headertext+"\n");
    System.out.println(headertext);
    
    String DetailRecord1="D0000000";
    int extref = randomnum();
    String DetailRecord2="DMB 02";
    String txdate = common.ExcelRead("Inputs", "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", 3, 64);
    String DetailRecord3="0000030479M7***********V110111000203001G     NRS00NYN";
    String DetailRecord=null;
    
    int count = Integer.parseInt(RecordCount);
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	String dateInString = txdate + getRandomTime();
	java.util.Date date = sdf.parse(dateInString);
	
	 int minsToAdd = 30; 
	Calendar calendar = Calendar.getInstance();
   calendar.setTime(date);
    for(int i=0;i<count;i++)
	   {	
   	 calendar.add(Calendar.MINUTE, minsToAdd);
        date = calendar.getTime();
        
        String Time1 = sdf.format(date);
        extref++;
   	 DetailRecord = DetailRecord1 +extref+DetailRecord2+ Time1 +DetailRecord3;
   	 System.out.println(DetailRecord);
   	 bw.write(DetailRecord+"\n");
	   }
	   
    
    
    
     Trailertext = Trailer + ARecordCount; 
   

     System.out.println(Trailertext);
     bw.write(Trailertext+"\n");
     System.out.println("File created");
    
	 bw.flush();
	 bw.close();

}

public static void createVtrFileDRPA() throws IOException, ParseException, EncryptedDocumentException, org.apache.poi.openxml4j.exceptions.InvalidFormatException
{
    
	String agencyname = getagencyselection();
	 String YYYY = common.ExcelRead("Data3", "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", 4, 1);
	 String MM = common.ExcelRead("Data3", "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", 3, 1);
	 String DD = common.ExcelRead("Data3", "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", 2, 1);
	 
	 String HHMMSS = common.ExcelRead("Data3", "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", 5, 1);
	
	
	
	 String header = "H";
		
	 String headertext = null; 
     String Trailertext= null;
     String Filename = null;     
     String RecordCount = common.ExcelRead("PlateDetails", "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", 1, 7);
     
  
   String ARecordCount = "000000".substring(RecordCount.length()) + RecordCount;
   String Trailer = "E";
   String Fileext =".VIO";
   Filename = YYYY + MM + DD + "01"+ Fileext;    
   System.out.println(Filename);
   
   String filepath ="E:\\TSG\\Workspace\\App\\WebApp-01\\Files\\"+Filename;
   java.io.File file = new java.io.File(filepath);
   file.delete(); 
   String pathname = "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm";
   common.ExcelWrite("Data3", pathname, 24, 1,Filename);
   BufferedWriter bw = new BufferedWriter(new FileWriter(filepath, true));
   headertext = header+ ARecordCount+YYYY + MM+ DD + HHMMSS+"01";
   bw.write(headertext+"\n");
   System.out.println(headertext);
   
   String txdate = common.ExcelRead("Inputs", "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", 3, 64);
   String DetailRecord1="[B"+txdate;
   String DetailRecord2="*************1000000000000000000000010082106000000000000**50100000000000000000000000000000005+000";
   String DetailRecord3="4  03WR00037.50]";
   String DetailRecord=null;
   
   int count = Integer.parseInt(RecordCount);
   
   SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	String dateInString = txdate + getRandomTime();
	java.util.Date date = sdf.parse(dateInString);
	
	 int minsToAdd = 30; 
	Calendar calendar = Calendar.getInstance();
  calendar.setTime(date);
   for(int i=0;i<count;i++)
	   {	
  	 calendar.add(Calendar.MINUTE, minsToAdd);
       date = calendar.getTime();
       
       String Time1 = sdf.format(date);
      
  	 DetailRecord = DetailRecord1 +DetailRecord2+Time1+DetailRecord3;
  	 System.out.println(DetailRecord);
  	 bw.write(DetailRecord+"\n");
	   }
	   
   
   
   
    Trailertext = Trailer; 
  

    System.out.println(Trailertext);
    bw.write(Trailertext+"\n");
    System.out.println("File created");
    
	 bw.flush();
	 bw.close();

}

public static void createVtrFileSJTA() throws IOException, ParseException, EncryptedDocumentException, org.apache.poi.openxml4j.exceptions.InvalidFormatException, SQLException
{
    
	String agencyname = getagencyselection();
	 String YYYY = common.ExcelRead("Data3", "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", 4, 1);
	 String MM = common.ExcelRead("Data3", "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", 3, 1);
	 String DD = common.ExcelRead("Data3", "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", 2, 1);
	 
	 String HHMMSS = common.ExcelRead("Data3", "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", 5, 1);
	 String xfer = common.ExcelRead("Data3", "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", 6, 1);
     String RecordCount = common.ExcelRead("PlateDetails", "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", 1, 7);
     String HHMM = HHMMSS.substring(0, 4);
     String NORACM = common.ExcelRead("PlateDetails", "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", 1, 8);
	 String header = "H";
	 String headertext = null; 
    String Trailertext= null;
    String Filename = null;     
  
   String ARecordCount = "000000".substring(RecordCount.length()) + RecordCount;
   String Trailer = "T";
   String xfer10 = xfer;
   String Fileext =".tr";
   String checksum = "2af9";
   String DLS ="001";
   String Msgtype="032";
   Filename = "v" + YYYY + MM + DD + HHMM + "00721" + Fileext;       
   System.out.println(Filename);
   
   String filepath ="E:\\TSG\\Workspace\\App\\WebApp-01\\Files\\"+Filename;
   java.io.File file = new java.io.File(filepath);
   file.delete(); 
   String pathname = "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm";
   common.ExcelWrite("Data3", pathname, 24, 1,Filename);
   BufferedWriter bw = new BufferedWriter(new FileWriter(filepath, true));
   headertext = header+ YYYY + MM+ DD + HHMMSS +YYYY + MM+ DD + HHMMSS +"001"+xfer10+"007"+MM+DD+YYYY+"000000";
   bw.write(headertext+"\n");
   System.out.println(headertext);
   
   String txdate = common.ExcelRead("Inputs", "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", 3, 64);
	int extref = randomnum();
	String plazalane = getPlazaLane();
   String DetailRecord1="2"+plazalane.replaceAll("\\s","");
   String DetailRecord2="000000002001.50000.000000000000000000200000000200000298260007292d00000000000000000000000000000000000000000420f7001";
   String DetailRecord3="0010000001.500000000000001.50001.50001.50";
   String DetailRecord=null;
   
   int count = Integer.parseInt(RecordCount);
   
   SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	String dateInString = txdate +getRandomTime();
	java.util.Date date = sdf.parse(dateInString);
	
	 int minsToAdd = 30; 
	Calendar calendar = Calendar.getInstance();
  calendar.setTime(date);
   for(int i=0;i<count;i++)
	   {	
  	 calendar.add(Calendar.MINUTE, minsToAdd);
       date = calendar.getTime();
       
       String Time1 = sdf.format(date);
       extref++;
  	 DetailRecord = DetailRecord1+Time1+DLS+0+extref+Msgtype+DetailRecord2+Time1+DetailRecord3;
  	 System.out.println(DetailRecord);
  	 bw.write(DetailRecord+"\n");
	   }
   Trailertext = Trailer + ARecordCount + checksum;
   

   System.out.println(Trailertext);
   bw.write(Trailertext+"\n");
   System.out.println("File created");
	 bw.flush();
	 bw.close();


}	   
public static void createVtrFileCAPEMAY() throws IOException, ParseException, EncryptedDocumentException, org.apache.poi.openxml4j.exceptions.InvalidFormatException, SQLException
{
    
	String agencyname = getagencyselection();
	 String YYYY = common.ExcelRead("Data3", "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", 4, 1);
	 String MM = common.ExcelRead("Data3", "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", 3, 1);
	 String DD = common.ExcelRead("Data3", "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", 2, 1);
	 
	 String HHMMSS = common.ExcelRead("Data3", "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", 5, 1);
	 String xfer = common.ExcelRead("Data3", "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", 6, 1);
     String RecordCount = common.ExcelRead("PlateDetails", "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", 1, 7);
     String HHMM = HHMMSS.substring(0, 4);
     String NORACM = common.ExcelRead("PlateDetails", "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", 1, 8);
	 String header = "H";
	 String headertext = null; 
    String Trailertext= null;
    String Filename = null;     
  
   String ARecordCount = "000000".substring(RecordCount.length()) + RecordCount;
   String Trailer = "T";
   String xfer10 = xfer;
   String Fileext =".tr";
   String checksum = "2af9";
   String DLS ="001";
   String Msgtype="032";
   Filename = "v" + YYYY + MM + DD + HHMM + "00721" + Fileext;       
   System.out.println(Filename);
   
   String filepath ="E:\\TSG\\Workspace\\App\\WebApp-01\\Files\\"+Filename;
   java.io.File file = new java.io.File(filepath);
   file.delete(); 
   String pathname = "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm";
   common.ExcelWrite("Data3", pathname, 24, 1,Filename);
   BufferedWriter bw = new BufferedWriter(new FileWriter(filepath, true));
   headertext = header+ YYYY + MM+ DD + HHMMSS +YYYY + MM+ DD + HHMMSS +"001"+xfer10+"007"+MM+DD+YYYY+"000000";
   bw.write(headertext+"\n");
   System.out.println(headertext);
   
   String txdate = common.ExcelRead("Inputs", "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", 3, 64);
	int extref = randomnum();
	String plazalane = getPlazaLane();
   String DetailRecord1="2"+plazalane.replaceAll("\\s","");
   String DetailRecord2="000000002001.50000.000000000000000000200000000200000298260007292d00000000000000000000000000000000000000000420f7001";
   String DetailRecord3="0010000001.500000000000001.50001.50001.50";
   String DetailRecord=null;
   
   int count = Integer.parseInt(RecordCount);
   
   SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	String dateInString = txdate +getRandomTime();
	java.util.Date date = sdf.parse(dateInString);
	
	 int minsToAdd = 30; 
	Calendar calendar = Calendar.getInstance();
  calendar.setTime(date);
   for(int i=0;i<count;i++)
	   {	
  	 calendar.add(Calendar.MINUTE, minsToAdd);
       date = calendar.getTime();
       
       String Time1 = sdf.format(date);
       extref++;
  	 DetailRecord = DetailRecord1+Time1+DLS+0+extref+Msgtype+DetailRecord2+Time1+DetailRecord3;
  	 System.out.println(DetailRecord);
  	 bw.write(DetailRecord+"\n");
	   }
	   
   
   
   
   Trailertext = Trailer + ARecordCount + checksum;
  

    System.out.println(Trailertext);
    bw.write(Trailertext+"\n");
    System.out.println("File created");
	 bw.flush();
	 bw.close();


}


public static String getPlazaLane() throws SQLException, EncryptedDocumentException, InvalidFormatException, org.apache.poi.openxml4j.exceptions.InvalidFormatException, IOException
{
    Random rand = new Random();
	
    String agency = getagencyselection();
    String plazaLane=null;
    if(agency.equals("NJTP"))
    {	
	String[] plaza = {"001","002","003","004","005","006","06A","007","07A","008","08A","009","010","011","012","013","13A","014","14A","14B","14C","15E","15W","16E","16W","17N","18E","18W","17S","06B","15X"};
	int index = rand.nextInt(plaza.length);		
	String selectedplaza = plaza[index];				
	String lane = getlane(selectedplaza,"13");
	 plazaLane = "0"+selectedplaza+lane;	
	 System.out.println("plazaLane is "+plazaLane);
    }
    else if(agency.equals("GSP"))
    {
    	String[] plaza = {"001","002","003","004","007","009","010","011","012","013","014","015","016","017","020","021","022","026","027","037","038","039","040","041","042","043","044","045","046","047","048","049","050","051","052","055","056","057","058","061","069","070","076","077","078","059","060","053","054","062","063","035"};
    	int index = rand.nextInt(plaza.length);		
    	String selectedplaza = plaza[index];				
    	String lane = getlane(selectedplaza,"12");
    	 plazaLane = "0"+selectedplaza+lane;	
    	 System.out.println("plazaLane is "+plazaLane);
    }
    
    else if(agency.equals("SJTA"))
    {
    	String[] plaza = {"04","12","09","17","28","33","38","41","05","10","16"};
    	int index = rand.nextInt(plaza.length);		
    	String selectedplaza = plaza[index];				
    	String lane = getlane(selectedplaza,"6");
    	plazaLane = selectedplaza+lane;	
    	 System.out.println("plazaLane is "+plazaLane);
    }
    
    else if(agency.equals("CAPEMAY"))
    {
    	String[] plaza = {"91","92","93","94","95"};
    	int index = rand.nextInt(plaza.length);		
    	String selectedplaza = plaza[index];				
    	String lane = getlane(selectedplaza,"6");
    	 plazaLane = selectedplaza+lane;	
    	 System.out.println("plazaLane is "+plazaLane);
    }
	
	return plazaLane;
	
}



public static String getlane(String plaza, String agencyid) throws SQLException
{
	
	
	
	DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver()); 
		   String dbURL = "jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST = 10.36.20.51)(PORT = 1521))(CONNECT_DATA = (SERVER = DEDICATED)(SERVICE_NAME = njrbtst)))";
	    String strUserID = "pbatch";
	    String strPassword = "batchp"; 
	    Connection c=DriverManager.getConnection(dbURL,strUserID,strPassword);
	    
	    String Statement1 = "SELECT extern_lane_id FROM  ( SELECT extern_lane_id FROM t_lane where plaza_id in (select plaza_id from t_plaza where agency_id ="+agencyid+" and extern_plaza_id = '"+plaza+"')"; 
	    String Statement2 = "ORDER BY DBMS_RANDOM.VALUE )  WHERE ROWNUM = 1 ";
	   
	    ResultSet rs = c.createStatement().executeQuery(Statement1+Statement2);
	    String lane = null;
	    while(rs.next()){
	    	lane =rs.getString("EXTERN_LANE_ID");  
	    }
	    
	    if(lane==null)
	    {	
	    	lane = "NULL";

	    }
	  
	    
	         System.out.println("LANE is "+lane);
	    	 String Alane = "   ".substring(lane.length()) + lane;
	   
		c.close();
	  
		return Alane;
	}

public static int randomnum() {
	
    Random r = new Random( System.currentTimeMillis() );
    return ((1 + r.nextInt(8)) * 10000 + r.nextInt(10000));
}

public static String getfirstname()
{
	Faker f = new Faker();
	return f.name().firstName().replace("'", "");
	
	
}


public static String getRandomTime()
{
	java.util.Date curDate = new java.util.Date();

	SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
	String DateToStr = format.format(curDate);
	format = new SimpleDateFormat("hhmmss");    	
	DateToStr = format.format(curDate);
    System.out.println(DateToStr);
    return DateToStr;
	
}	

public static String getsystime()
{
	String time=null;
	
	   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HHmmss");  
	   LocalDateTime now = LocalDateTime.now();  
	   time = dtf.format(now);  
	
	return time;
	
}


public static String getLastname()
{
	Faker f = new Faker();
	return f.name().lastName().replace("'", "");
	
}
public static String getAddress()
{
	Faker f = new Faker();
	return f.address().streetAddress(false).replace("'", "");
	
}

public static int getDMVRequestPlateCount(String xfer) throws InterruptedException,  IOException, NumberFormatException, SQLException, ParseException {

  	
	
	DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver()); 
    String dbURL = "jdbc:oracle:thin:@(DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST = "+ipaddress+")(PORT = 1521)) (CONNECT_DATA =(SERVER = DEDICATED)(SERVICE_NAME = njrbtst)))";
    String strUserID = "pbatch";
    String strPassword = "batchp"; 
    Connection c=DriverManager.getConnection(dbURL,strUserID,strPassword);
    
    String Statement = "select * from t_viol_tx where extern_file_id ='"+xfer+"' and viol_tx_status = 7" ; 
    
    ResultSet rs = c.createStatement().executeQuery(Statement);
    int i=1;
    String txdate=null; 
   while(rs.next())
   {
   txdate = rs.getString("TX_DATE");
   i++;
   }

   
  

    c.close();
	return i;   
} 

public static void imageInsertthread(int totalsize) throws org.apache.poi.openxml4j.exceptions.InvalidFormatException, IOException
{
	try {
		String filename =uploadFile.getFileName();
		String xfer = uploadFile.getxferid(filename);
		
		DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver()); 
		String dbURL = "jdbc:oracle:thin:@(DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST = "+ipaddress+")(PORT = 1521)) (CONNECT_DATA =(SERVER = DEDICATED)(SERVICE_NAME = njrbtst)))";
	    String strUserID = "pbatch";
	    String strPassword = "batchp"; 
	    Connection c=DriverManager.getConnection(dbURL,strUserID,strPassword);
	    
	    String Statement1 = "select LANE_TX_ID from t_viol_tx where extern_file_id ='"+xfer+"'"; 
	    String Statement2 = "select COUNT(LANE_TX_ID) from t_viol_image_tx where LANE_TX_ID in (select LANE_TX_ID from t_viol_tx where extern_file_id ='"+xfer+"')"; 
	   
	    
	    ResultSet rs1 = c.createStatement().executeQuery(Statement1);
	    ResultSet rs2 = c.createStatement().executeQuery(Statement2);
	    String lanetxid[] = new String[totalsize];
	    int i=0;
	    String imagename=null;;
	    int countofimage =0;
	    while(rs2.next()){
	    	countofimage = rs2.getInt("COUNT(LANE_TX_ID)");
	    }
	    
	    
	    if(countofimage!=totalsize)
	    { 	
	    
	    while(rs1.next()){
	    	lanetxid[i] =rs1.getString("LANE_TX_ID");  
	    	imagename =uploadFile.imageIns(lanetxid[i]);
	    	if(!imagename.equals(""))
	    	uploadFile.deleteQueue(imagename);
	    	i++;
	    }
	    System.out.println("Number of transactions loaded "+i);
	    }
	    
		
		
		
	} catch (Exception e) {
	}
	
}

public static int getImageCount() throws org.apache.poi.openxml4j.exceptions.InvalidFormatException, IOException
{
	int countofimage =0;
	try {
		String filename =uploadFile.getFileName();
		String xfer = uploadFile.getxferid(filename);
		
		DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver()); 
		String dbURL = "jdbc:oracle:thin:@(DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST = "+ipaddress+")(PORT = 1521)) (CONNECT_DATA =(SERVER = DEDICATED)(SERVICE_NAME = njrbtst)))";
	    String strUserID = "pbatch";
	    String strPassword = "batchp"; 
	    Connection c=DriverManager.getConnection(dbURL,strUserID,strPassword);
	    
	    
	    String Statement = "select COUNT(LANE_TX_ID) from t_viol_image_tx where LANE_TX_ID in (select LANE_TX_ID from t_viol_tx where extern_file_id ='"+xfer+"')"; 
	   
	    
	    
	    ResultSet rs2 = c.createStatement().executeQuery(Statement);
	   
	    int i=0;
	    String imagename=null;;
	    
	    while(rs2.next()){
	    	countofimage = rs2.getInt("COUNT(LANE_TX_ID)");
	    }
	    
	    
	   
		
		

	} 
	catch (Exception e) {
	}
	return countofimage; 
}


public static int getRespPlateCount() throws org.apache.poi.openxml4j.exceptions.InvalidFormatException, IOException
{
	int countofplate =0;
	try {
		String filename =uploadFile.getFileName();
		String xfer = uploadFile.getxferid(filename);
		
		DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver()); 
		String dbURL = "jdbc:oracle:thin:@(DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST = "+ipaddress+")(PORT = 1521)) (CONNECT_DATA =(SERVER = DEDICATED)(SERVICE_NAME = njrbtst)))";
	    String strUserID = "pbatch";
	    String strPassword = "batchp"; 
	    Connection c=DriverManager.getConnection(dbURL,strUserID,strPassword);
	    
	    
	    String Statement = "select COUNT(LANE_TX_ID) from t_viol_tx where VIOL_TX_STATUS =7 and extern_file_id ='"+xfer+"'"; 
	   
	    
	    
	    ResultSet rs2 = c.createStatement().executeQuery(Statement);
	   
	   
	   
	    
	    while(rs2.next()){
	    	countofplate = rs2.getInt("COUNT(LANE_TX_ID)");
	    }
	    
	    
	   
		
		

	} 
	catch (Exception e) {
	}
	return countofplate; 
}



public static void startThread(int size)  
{
Runnable r1 = new Runnable() {
    public void run() {
    	try {
			uploadFile.imageInsertthread(size);
		} catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
};
Thread t1= new Thread(r1,"Thread1");
t1.start(); 

}
}