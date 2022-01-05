<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.sch.pkg.*, java.util.*, java.lang.*, com.jcraft.jsch.*,org.apache.poi.*,com.Ostermiller.*,java.text.*,java.time.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Schedular- QA</title>
<link rel ="stylesheet" href="CSS/indexStyle.css">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="E://TSG/Workspace/App/WebApp-01/WebContent/WEB-INF/libjquery-3.1.1.min.js"></script>
<script type="text/javascript">
$(window).load(function() {
	$(".loader").fadeOut("slow");
	
})

</script>

<script type="text/javascript">
$(function(){
	$("#uploadedFile").hide();
	$("#CreateFile").click(function(){
	 
		$("#uploadedFile").show();	
	});
});
	
</script>

<script>

function cancelAction()
{	
document.location.href("CreateNewPaymentFile.jsp"); 
}

</script>


</head>
<body>
<div class="loader"></div>
<div id="left"></div>
<div id="right"></div>
<div id="top"></div>
<div id="bottom"></div>
<div class="loader"></div>
<div class="stitched">
<h1>Confirm Payment File Details Page</h1>
</div>

<form font-size="75px;" >

<%--action = "XferFileCreation.jsp" method="get" onsubmit="return validate()"  > --%>


<%   String DB = request.getParameter("DB");
	 String CollectionAgencyname = request.getParameter("CollAgency");
     String agencyname = request.getParameter("agencyid"); 
     System.out.println(agencyname);
     String FileOperation = request.getParameter("FileOperation"); 
     String filedate = request.getParameter("filedate");
     String filetime = uploadFile.getsystime();
     System.out.println("current Time is "+filetime);
     String NumOfRecords =  request.getParameter("NumofRecords"); 
     String CitationNumber =  request.getParameter("Noticenumber"); 
     
     
  %>
  <table>
   <tr><whi/>Connected DB : <%=DB%></tr>
   <tr><whi/>Collection Agency Name : <%=CollectionAgencyname%></tr>
   <tr><whi/>Agency Name : <%=agencyname%></tr>
   <tr><whi/>File Operation : <%=FileOperation%></tr>
   <tr><whi/>File Date : <%=filedate%></tr>
   <tr><whi/>Number of Records :<%=NumOfRecords%> </tr>
   <tr><whi/>Citation number :<%=CitationNumber%> </tr>
  
    
   <%String macroname =agencyname+"ViolMacro"; %>   
   
   
 
  
  <tr><a href="CreateNewPaymentFile.jsp"><input type="button" value="Back" name="cancel"></a></tr>
  <tr><a href="XferFileCreation.jsp"><input type="button" value="Create File" name="createFile"></a></tr>    </tr>
 <%--<tr><input type="submit" value="Create File" id = "CreateFile"></tr>--%>
 
  </table> <br>
  
  <%=uploadFile.createFile()%> 

  
</form>
</body>
</html>


