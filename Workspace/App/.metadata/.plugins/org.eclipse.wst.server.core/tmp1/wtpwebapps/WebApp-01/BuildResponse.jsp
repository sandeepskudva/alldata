<%@page import="java.io.Console"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.sch.pkg.*, java.util.*, java.lang.*, com.jcraft.jsch.*,org.apache.poi.*,java.sql.*,com.Ostermiller.util.*,com.github.*" %>
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
 <%String filename =uploadFile.getFileName();
 String xfer =uploadFile.getxferid(filename); %>
<script type="text/javascript">

$(document).ready(function(){
	$("#AutoComplete").click(function(){	
		var cnt = $(":input[id^=firstName]").length;
		 
		
		 
		 <%
		int count =uploadFile.getDMVRequestPlateCount(xfer);
		
		
		for(int j=1;j<count;j++)			
		{	
		
	   String firstname =uploadFile.getfirstname().toUpperCase();
	   String lastname =uploadFile.getLastname().toUpperCase();
	   String address =uploadFile.getAddress().toUpperCase();
	   String city ="NEWARK";
	   String state ="NJ";
	   String zip ="07114";
	   %>	
		
		$("#firstName"+<%=j%>).val('<%=firstname%>');
		$("#LastName"+<%=j%>).val('<%=lastname%>');
		$("#Address1"+<%=j%>).val('<%=address%>');
		$("#City"+<%=j%>).val('<%=city%>');
		$("#State"+<%=j%>).val('<%=state%>');
		$("#Zip"+<%=j%>).val('<%=zip%>');
		
	<%	
		} 
    %> 
	 });
	 });
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
<h1>Build Response</h1>
</div>
<center><img src='IMG/status7.png' ></img></center>
<nav>
  <ul class="top-menu">
    <li><a href="index.jsp">Home</a><div class="menu-item" id="home"></div></li>
    <li><a href="CreateNew.jsp">Create New Set</a><div class="menu-item" id="CreateNew"></div></li>
    <li><a href="ExistingViolations.jsp">OpenExisting</a><div class="menu-item" id="OpenExisting"></div></li>
    <li><a href="contact.jsp">Contact</a><div class="menu-item" id="contact"></div></li>
    <li><a href="about.jsp">About</a><div class="menu-item" id="About"></div></li>
  </ul>
</nav>

 


<p style = "font-weight: bold;">Please fill details for DMV Response for XferID : <%=xfer%><p>




<tr><button style = "padding-margin-left: 10px;" id="AutoComplete" value="AutoComplete">AutoComplete</button></tr>
<form font-size="65px;"  action="${pageContext.request.contextPath}/buttonServlet" method="post">

<table border="2" >

<%
try
{   
	String ipaddress = "10.36.20.51";
	DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver()); 
	String DBSel = uploadFile.getDBselection();
    String dbURL = null;
    String strUserID = null;
    String strPassword = null;
    if(DBSel.equals("NJP2TST"))
	{
		 dbURL = "jdbc:oracle:thin:@(DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST = "+ipaddress+")(PORT = 1521)) (CONNECT_DATA =(SERVER = DEDICATED)(SERVICE_NAME = njp2tst)))";
	     strUserID = "pbatch";
	     strPassword = "pbatch443"; 
	}
    else if(DBSel.equals("NJAETTST"))
	{
		 dbURL = "jdbc:oracle:thin:@(DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST = "+ipaddress+")(PORT = 1521)) (CONNECT_DATA =(SERVER = DEDICATED)(SERVICE_NAME = NJAETTST)))";
	     strUserID = "pbatch";
	     strPassword = "pbatch443"; 
	} 
	else if(DBSel.equals("njrbtst"))
	{
		 dbURL = "jdbc:oracle:thin:@(DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST = "+ipaddress+")(PORT = 1521)) (CONNECT_DATA =(SERVER = DEDICATED)(SERVICE_NAME = njrbtst)))";
	     strUserID = "pbatch";
	     strPassword = "pbatch443"; 
	} 
    Connection c=DriverManager.getConnection(dbURL,strUserID,strPassword);
    
    String Statement = "select * from t_viol_tx where extern_file_id ='"+xfer+"' and viol_tx_status = 7" ; 
    
    ResultSet rs = c.createStatement().executeQuery(Statement);
    
    %>  
 
   <tr style ="background : brown">
<td style="color:white;margin-left:10px;font-weight: bold;padding: 4px 6px;">PLATE STATE</td>
<td style="color:white;margin-left:10px;font-weight: bold;padding: 4px 6px;">PLATE NUMBER</td>
<td style="color:white;margin-left:10px;font-weight: bold;padding: 4px 6px;">LANE_TX_ID</td>
<td style="color:white;margin-left:10px;font-weight: bold;padding: 4px 6px;">TX_DATE</td>
<td style="color:white;margin-left:10px;font-weight: bold;padding: 4px 6px;">LAST NAME</td>
<td style="color:white;margin-left:10px;font-weight: bold;padding: 4px 6px;">MIDDLE NAME</td>
<td style="color:white;margin-left:10px;font-weight: bold;padding: 4px 6px;">FIRST NAME</td>
<td style="color:white;margin-left:10px;font-weight: bold;padding: 4px 6px;">ADDRESS1</td>
<td style="color:white;margin-left:10px;font-weight: bold;padding: 4px 6px;">ADDRESS2</td>
<td style="color:white;margin-left:10px;font-weight: bold;padding: 4px 6px;">CITY</td>
<td style="color:white;margin-left:10px;font-weight: bold;padding: 4px 6px;">STATE</td>
<td style="color:white;margin-left:10px;font-weight: bold;padding: 4px 6px;">ZIP</td>
</tr>
 <% 
 int i=1;
 String txdate=null; 
while(rs.next())
{
txdate = rs.getString("TX_DATE");
%>
<tr style ="background : #f7f7f7">
<td style="color:black;margin-left:10px;font-weight: bold;padding: 4px 6px;"><input style ="border: none" type="text" id="plateState<%=i%>" name = "plateState<%=i%>" value ="<%=rs.getString("PLATE_STATE") %>" readonly> </input></td>
<td style="color:black;margin-left:10px;font-weight: bold;padding: 4px 6px;"><input style ="border: none" type="text" id="plateNumber<%=i%>" name = "plateNumber<%=i%>" value ="<%=rs.getString("PLATE_NUMBER") %>" readonly> </input></td>
<td style="color:black;margin-left:10px;font-weight: bold;padding: 4px 6px;"><input style ="border: none" type="text" id="lanetxid<%=i%>" name = "lanetxid<%=i%>" value ="<%=rs.getString("LANE_TX_ID") %>" readonly> </input></td>
<td style="color:black;margin-left:10px;font-weight: bold;padding: 4px 6px;"><input style ="border: none" type="text" id="txdate<%=i%>" name = "txdate<%=i%>" value ="<%=txdate%>" readonly> </input></td>
<td><input type ="text" id ="LastName<%=i%>" name ="LastName<%=i%>"/></td>
<td><input type ="text" id ="middleName<%=i%>" name ="middleName<%=i%>"/></td>
<td><input type ="text" id ="firstName<%=i%>" name ="firstName<%=i%>"/></td>
<td><input type ="text" id ="Address1<%=i%>" name ="Address1<%=i%>"/></td>
<td><input type ="text" id ="Address2<%=i%>" name ="Address2<%=i%>"/></td>
<td><input type ="text" id ="City<%=i%>" name ="City<%=i%>"/></td>
<td><input type ="text" id ="State<%=i%>" name ="State<%=i%>"/></td>
<td><input type ="text" id ="Zip<%=i%>" name ="Zip<%=i%>"/></td>
</tr>

 <%
i++;
}

request.setAttribute("myCount", i-1);

%>

</table>
<%
c.close();
}
catch(Exception e)
{
e.printStackTrace();
}
%>
 
 <tr><button type="submit" name="BuildResponse" value="BuildResponse">Build Response File</button></tr>
</form>
</body>
</html>


