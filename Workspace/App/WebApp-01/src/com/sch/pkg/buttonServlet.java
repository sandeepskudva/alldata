package com.sch.pkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellNotAvailableException;
import com.profesorfalken.jpowershell.PowerShellResponse;






/**
 * Servlet implementation class MyServlet
 */
@SuppressWarnings("unused")
@WebServlet("/buttonServlet")
public class buttonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public buttonServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	
   
	String code1 = request.getParameter("plate_state");
	String code2 = request.getParameter("plate_number");
	String accountNum = null;
	try {
		 accountNum = uploadFile.verifyPlate(code1, code2);
	} catch (SQLException | EncryptedDocumentException | InvalidFormatException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	response.addHeader("accountNum", accountNum);
    
	

	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	   int size =0;
	   int totalsize =0;
		try {
			 String agencyname = uploadFile.getagencyselection();
			 size =  Integer.parseInt(common.ExcelRead("PlateDetails", "D:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", 1, 6));
			 totalsize = Integer.parseInt(common.ExcelRead("PlateDetails", "D:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", 1, 7));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		if (request.getParameter("RunJob") != null) {
			 try {
				 String agencyname = uploadFile.getagencyselection();
				 String command =null;
				 if(agencyname.equals("NJTP"))
				 {
					 command = "njtp_qatp.sh&"; 
				 }
				 else if(agencyname.equals("GSP"))
				 {
					 command = "njha_qatp.sh&"; 
				 }
				 else if(agencyname.equals("DRJ"))
				 {
					 command = "drjtbc_qatp.sh&";
				 }
				 else if(agencyname.equals("DRJAET"))
				 {
					 command = "drjtbc_qatp.sh&";
				 }
				 else if(agencyname.equals("DRBA"))
				 {
					 command = "drba_qatp.sh&";
				 }
				 else if(agencyname.equals("DRPA"))
				 {
					 command = "drpa_qatp.sh&";
				 }
				 else if(agencyname.equals("SJTA"))
				 {
					 command = "njsj_qatp.sh&";
				 }
				 else if(agencyname.equals("CAPEMAY"))
				 {
					 command = "njsj_qatp.sh&";
				 }
				uploadFile.runCommand(command);
				request.getRequestDispatcher("ExistingViolations.jsp").forward(request, response);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSchException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (EncryptedDocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}       
    }else if (request.getParameter("BuildPlate") != null) {
			
    	    
    	    String xfer;
			try {
				String filename =uploadFile.getFileName();
				xfer = uploadFile.getxferid(filename);
				
			System.out.println("SIZE is "+size);
			String platestate[] = new String[size];	
			String platenumber[] = new String[size];	
			for(int i=0;i<size;i++)
			{
				platestate[i] =request.getParameter("PlateState"+(i+1)); 
				platenumber[i]=	request.getParameter("PlateNumber"+(i+1)); 	
			}
				
				
			uploadFile.setplateQuery(xfer,platestate,platenumber);
			uploadFile.runCommand("dmvrecreate.sh&");
			uploadFile.deleteDMVFiles();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	   
			request.getRequestDispatcher("ExistingViolations.jsp").forward(request, response);
		  
    		}
            else if(request.getParameter("RunLica") != null)
    		{
    			try {
    				uploadFile.runCommand("vector_lica_nj.sh 72&");
				
					request.getRequestDispatcher("ExistingViolations.jsp").forward(request, response);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSchException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (EncryptedDocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
    		}
            else if(request.getParameter("RunDMVRequest") != null)
    		{
    			try {
					uploadFile.runCommand("vector_viol_dmv_request.sh&");
					request.getRequestDispatcher("ExistingViolations.jsp").forward(request, response);
				} catch (EncryptedDocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSchException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
    		}
		
            else if(request.getParameter("BuildResponse") != null)
    		{
            	   int newsize = 0;
            	   try {
					newsize = uploadFile.getRespPlateCount();
					System.out.println(newsize);
				} catch (InvalidFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	   String platestate[] = new String[newsize];	
    			   String platenumber[] = new String[newsize];
    			   String lanetxid[] = new String[newsize];
    			   String txdate[]= new String[newsize];
    			   String lastname[]= new String[newsize];
    			   String middlename[]= new String[newsize];
    			   String firstname[]= new String[newsize];    			  
    			   String Address1[]= new String[newsize];
    			   String Address2[]= new String[newsize];
    			   String City[]= new String[newsize];
    			   String State[]= new String[newsize];
    			   String Zip[]= new String[newsize];
    			   String dmvfile[] = new String[newsize];
            	
            	   for(int i=0;i<newsize;i++)
            	   {	   
            	   platestate[i] = request.getParameter("plateState"+(i+1)); 
            	   
            	   platenumber[i] = request.getParameter("plateNumber"+(i+1)); 
            	   lanetxid[i] = request.getParameter("lanetxid"+(i+1)); 
            	  
            	   txdate[i] = request.getParameter("txdate"+(i+1)); 
            	   lastname[i] = request.getParameter("LastName"+(i+1));
            	   middlename[i] = request.getParameter("middleName"+(i+1));
            	   firstname[i] = request.getParameter("firstName"+(i+1));            	  
    			   Address1[i] = request.getParameter("Address1"+(i+1)); 
    			   Address2[i] = request.getParameter("Address2"+(i+1));
    			   City[i] = request.getParameter("City"+(i+1)); 
    			   State[i] = request.getParameter("State"+(i+1));
    			  
    			   Zip[i] = request.getParameter("Zip"+(i+1));   			    
    			   
					try {
						dmvfile[i]=uploadFile.createDMVLine(i,platestate[i],platenumber[i],lanetxid[i],lastname[i],middlename[i],firstname[i],Address1[i],Address2[i],City[i],State[i],Zip[i]);
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				
            	   }
            	     
					try {
						String filename = uploadFile.createDMVFile(dmvfile);
						uploadFile.convertToUnix(filename);
						String username = common.ExcelRead("Sheet1", "D:\\Crede\\Crede.xlsx", 1, 0);
						System.out.println("username is "+username);
						String password = common.ExcelRead("Sheet1", "D:\\Crede\\Crede.xlsx", 1, 1);
						String DBSel = uploadFile.getDBselection();
						if(DBSel.equals("NJP2TST"))
						{
							uploadFile.fileTransfer("10.36.20.47",username,password,"/app/vector/NJP2TST/data_files/dmv_from",filename);
							System.out.println("Moved to NJP2TST path");
 
						}
						else if(DBSel.equals("NJAETTST"))
						{
							uploadFile.fileTransfer("10.36.20.47",username,password,"/app/vector/NJAETTST/data_files/dmv_from",filename);
							System.out.println("Moved to NJAETTST path");
						}
						else if(DBSel.equals("njrbtst"))
						{
							uploadFile.fileTransfer("10.36.20.47",username,password,"/app/vector/data_files/dmv_from",filename);
							System.out.println("Moved to NJRBTST path");
						}
						
					} catch (InterruptedException | SftpException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (EncryptedDocumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvalidFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
            	  
				   request.getRequestDispatcher("DMVResponse.jsp").forward(request, response);
				
				
    		}
            else if(request.getParameter("RunDMVResponse") != null)
    		{
    			try {
					uploadFile.runCommand("vector_dmv_response.sh&");
					request.getRequestDispatcher("ExistingViolations.jsp").forward(request, response);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSchException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (EncryptedDocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
    		}
		
            else if(request.getParameter("AddImage") != null)
    		{
    			try {
    				//uploadFile.startThread(totalsize);
    				
    				request.getRequestDispatcher("imagetransactions.jsp").forward(request, response);
					
				}  catch (Exception e) {
					
					
				}
    			
				
    		}
		else if(request.getParameter("RunSiebelJob") != null)
		{
			
				try {
					uploadFile.runSiebeljobP("VectorViolAcctCreation");
				} catch (EncryptedDocumentException | InvalidFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.getRequestDispatcher("citationQueue.jsp").forward(request, response);
			
			
		}
		
		else if(request.getParameter("NoticeProcessingTPMS") != null)
		{
			
			try {
				String agencyname = uploadFile.getagencyselection();
				
				String command =null;
				if(agencyname.equals("NJTP"))
				{
				  command = "viol_tev_notice_njta.sh&";
				}
				else if(agencyname.equals("GSP"))
				{
				  	
				  command = "viol_tev_notice_njha.sh&";
				  uploadFile.runCommand("viol_tev_notice_njha_acm.sh&"); 
				}
				else if(agencyname.equals("DRJ"))
				{
				  command = "viol_tev_notice_drjtbc.sh&";
				}
				else if(agencyname.equals("DRBA"))
				{
				  command = "viol_tev_notice_drba.sh&";
				}
				else if(agencyname.equals("DRPA"))
				{
				  command = "viol_tev_notice_dr.sh&";
				}
				else if(agencyname.equals("SJTA"))
				{
				  command = "viol_tev_notice_sj.sh&";
				  uploadFile.runCommand("viol_tev_notice_sj_acm.sh&"); 
				}
				else if(agencyname.equals("CAPEMAY"))
				{
				  command = "viol_tev_notice_sj.sh&";
				  uploadFile.runCommand("viol_tev_notice_sj_acm.sh&"); 
				}
				uploadFile.runCommand(command);
				request.getRequestDispatcher("RunNoticeProcessing.jsp").forward(request, response);
			} catch (InterruptedException | JSchException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (EncryptedDocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
		}
		else if(request.getParameter("RunNProcessingSJob") != null)
		{
			
				try {
					uploadFile.runSiebeljobP("VectorNoticeProcessing");
				} catch (EncryptedDocumentException | InvalidFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.getRequestDispatcher("ExistingViolations.jsp").forward(request, response);
			
			
		}
		else if(request.getParameter("DeleteImage") != null)
		{
				String filename;
				try {
					filename = uploadFile.getFileName();
					String xfer = uploadFile.getxferid(filename);
					uploadFile.deleteimage(xfer);
				} catch (EncryptedDocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				request.getRequestDispatcher("NoticeInformation.jsp").forward(request, response);
			
			
		}
		else if(request.getParameter("NoticeEscalation") != null)
		{  
				
				try {
					String filename =uploadFile.getFileName();
					String xfer = uploadFile.getxferid(filename);
					
					String citationlevel=uploadFile.getcitationlevel(xfer) ;
					if(citationlevel.contains("COLL4") || citationlevel.contains("COLL5"))
					{
									
								
						request.getRequestDispatcher("Finish.jsp").forward(request, response);
					}
					else
					{
						uploadFile.updateACK(xfer);
						String agencyname = uploadFile.getagencyselection();
						 String command =null;
						 if(agencyname.equals("NJTP"))
						 {
							 command = "njta_viol_tev_notice_esc.sh&"; 
						 }
						 else if(agencyname.equals("GSP"))
						 {
							 command = "njha_viol_tev_notice_esc.sh&"; 
						 }
						 else if(agencyname.equals("DRJ"))
						 {
							 command = "drjtbc_viol_tev_notice_esc.sh&"; 
						 }
						 else if(agencyname.equals("DRBA"))
						 {
							 command = "drba_viol_tev_notice_esc.sh&"; 
						 }
						 else if(agencyname.equals("DRPA"))
						 {
							 command = "drpa_viol_tev_notice_esc.sh&"; 
						 }
						 else if(agencyname.equals("SJTA"))
						 {
							 command = "sjta_viol_tev_notice_esc.sh&"; 
						 }
						 
						uploadFile.runCommand(command);		
						request.getRequestDispatcher("RunNoticeProcessing.jsp").forward(request, response);	
					}
					
					
				} catch (SQLException | EncryptedDocumentException | InvalidFormatException | InterruptedException | JSchException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	 
				
			
			
		}
		
		else if(request.getParameter("CreatePaymentFile") != null)
		{
			
				try {
					uploadFile.runSiebeljobP("VectorViolAcctCreation");
				} catch (EncryptedDocumentException | InvalidFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.getRequestDispatcher("citationQueue.jsp").forward(request, response);
			
			
		}
			
		else if  (request.getParameter("MoveViolation") != null) {
		 try {
			 String filename =uploadFile.getFileName();
			 String xferid =uploadFile.getxferid(filename);
			 
			
			  String violstatus =  uploadFile.getViol_tx_status(xferid);				
			  System.out.println("viol_tx_status is  "+violstatus);
			  if(violstatus.contentEquals("3"))
			    { 
				  uploadFile.threetofour(xferid);
				  try {
					 uploadFile.runCommand("qa_temp_img_ins.sh "+xferid+"&");
					  //uploadFile.startThread(totalsize);
					request.getRequestDispatcher("ExistingViolations.jsp").forward(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				 }
			    }
				  
			
			  else if(violstatus.contentEquals("104"))
			    { 
				  try {
					uploadFile.runCommand("qa_temp_img_ins.sh "+xferid+"&");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSchException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				  uploadFile.oneofourtofour(xferid);
				  request.getRequestDispatcher("ExistingViolations.jsp").forward(request, response);
			    }
			  
			  else if(violstatus.contentEquals("4"))
			  {
				  		  
				  request.getRequestDispatcher("requestPlateDetails.jsp").forward(request, response);
			  }
			  else if(violstatus.contentEquals("5"))
			  {
				 		  
				  request.getRequestDispatcher("Lica.jsp").forward(request, response);
			  }
			  else if(violstatus.contentEquals("23"))
			  {
				 try {
					uploadFile.licaRetry(xferid);
					request.getRequestDispatcher("Lica.jsp").forward(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 		  
				
			  } 
			  
			  else if(violstatus.contentEquals("6"))
			  {
				 try {
					request.getRequestDispatcher("DMVRequest.jsp").forward(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 		  
				
			  } 
			 			  
			  else if(violstatus.contentEquals("7"))
			  {
				 try {
					request.getRequestDispatcher("BuildResponse.jsp").forward(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 		  
				
			  }
			  else if(violstatus.contentEquals("159"))
			  {
				 try {
					request.getRequestDispatcher("BuildResponse.jsp").forward(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 		  
				
			  } 
			  else if(violstatus.contentEquals("131") || violstatus.contentEquals("137"))
			  {
				 try {
					 uploadFile.addresscleanse(xferid);
					 uploadFile.runCommand("vector_viol_account_creation.sh L 1&"); 
					 uploadFile.runCommand("vector_viol_account_creation.sh L 2&"); 
					 uploadFile.runCommand("vector_viol_account_creation.sh L 3&"); 
					 uploadFile.runCommand("vector_viol_account_creation.sh L 4&"); 
					 uploadFile.runCommand("vector_viol_account_creation.sh L 5&"); 
					request.getRequestDispatcher("imageInsert.jsp").forward(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 		  
				
			  } 
			  else if(violstatus.contentEquals("14"))
			  {
				 try {
					 uploadFile.runSiebeljobP("VectorViolAcctCreation");
					 request.getRequestDispatcher("ExistingViolations.jsp").forward(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 		  
				
			  } 
			  else if(violstatus.contentEquals("174"))
			  {
				 try {
					 uploadFile.runCommand("vector_dynamic_billing.sh&"); 
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 		  
				 request.getRequestDispatcher("TollBillInformation.jsp").forward(request, response);
			  } 
			  
			  else if(violstatus.contentEquals("109"))
			  {
				 try {
					 
					request.getRequestDispatcher("imageInsert.jsp").forward(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 		  
				
			  } 
			  else if(violstatus.contentEquals("139"))
			  {
				 try {
					 
					request.getRequestDispatcher("RunNoticeProcessing.jsp").forward(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 		  
				
			  } 
			 
			  else if(request.getParameter("RunUpdateQueries") != null)
				{
					try {
						uploadFile.runUpdateQueries(xferid);	
						request.getRequestDispatcher("ExistingViolations.jsp").forward(request, response);
						
					}
					catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 	
						
							
							 
							
					
					
				}
			  else if(violstatus.contentEquals("10"))
			  {
				 try {
												 
					
					request.getRequestDispatcher("NoticeInformation.jsp").forward(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 		  
				
			  } 
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}       
}
		
		

	}

}
