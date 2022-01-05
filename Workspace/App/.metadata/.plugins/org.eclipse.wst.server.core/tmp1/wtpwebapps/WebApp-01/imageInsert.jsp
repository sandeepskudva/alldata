
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.sch.pkg.*, java.util.*, java.lang.*, com.jcraft.jsch.*,org.apache.poi.*,java.sql.*" %>
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

</head>
<body>
<div class="loader"></div>
<div id="left"></div>
<div id="right"></div>
<div id="top"></div>
<div id="bottom"></div>
<div class="loader"></div>
<div class="stitched">
<h1>Pre Violation Account Creation Page</h1>
</div>
<center><img src='IMG/status109.png' ></img></center>
<nav>
  <ul class="top-menu">
    <li><a href="index.jsp">Home</a><div class="menu-item" id="home"></div></li>
    <li><a href="CreateNew.jsp">Create New Set</a><div class="menu-item" id="CreateNew"></div></li>
    <li><a href="ExistingViolations.jsp">OpenExisting</a><div class="menu-item" id="OpenExisting"></div></li>
    <li><a href="contact.jsp">Contact</a><div class="menu-item" id="contact"></div></li>
    <li><a href="about.jsp">About</a><div class="menu-item" id="About"></div></li>
  </ul>
</nav>
<form font-size="75px;" action="${pageContext.request.contextPath}/buttonServlet" method="post">
<div id ="rload">
<table>  <table border="2" >



<%String filename =uploadFile.getFileName();
 String xfer =uploadFile.getxferid(filename); %>
<p style = "font-weight: bold;">The Following Transactions for <%=xfer%> are in t_eim_viol_account </p>
<button type="button" onClick="refreshPage()">Refresh</button>
<script>
function refreshPage() {
 	$("#rload").load( "imageInsert.jsp #rload" );
} 
</script>
<%
try
{
	String ipaddress = "10.36.20.51";
	String DBSel = uploadFile.getDBselection();
    String dbURL = null;
    String strUserID = null;
    String strPassword = null;
	DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver()); 
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
    
    String Statement = "select * from t_eim_viol_account where LANE_TX_ID in (select LANE_TX_ID from t_viol_tx where extern_file_id = '"+xfer+"')"; 
    
    ResultSet rs = c.createStatement().executeQuery(Statement);
    
    %>  
 <tr style ="background : brown">
<td style="color:white;margin-left:10px;font-weight: bold;padding: 4px 6px;">LANE_TX_ID</td>
<td style="color:white;margin-left:10px;font-weight: bold;padding: 4px 6px;">RECORD_TYPE</td>
<td style="color:white;margin-left:10px;font-weight: bold;padding: 4px 6px;">ETC_ACCOUNT_ID</td>
<td style="color:white;margin-left:10px;font-weight: bold;padding: 4px 6px;">FIRST NAME</td>
<td style="color:white;margin-left:10px;font-weight: bold;padding: 4px 6px;">MIDDLE NAME</td>
<td style="color:white;margin-left:10px;font-weight: bold;padding: 4px 6px;">LAST NAME</td>
<td style="color:white;margin-left:10px;font-weight: bold;padding: 4px 6px;">STREET1</td>
<td style="color:white;margin-left:10px;font-weight: bold;padding: 4px 6px;">STREET2</td>
<td style="color:white;margin-left:10px;font-weight: bold;padding: 4px 6px;">CITY</td>
<td style="color:white;margin-left:10px;font-weight: bold;padding: 4px 6px;">STATE</td>
<td style="color:white;margin-left:10px;font-weight: bold;padding: 4px 6px;">ZIP CODE</td>
<td style="color:white;margin-left:10px;font-weight: bold;padding: 4px 6px;">PLATE STATE</td>
<td style="color:white;margin-left:10px;font-weight: bold;padding: 4px 6px;">PLATE NUMBER</td>
<td style="color:white;margin-left:10px;font-weight: bold;padding: 4px 6px;">DMV RETURN DATE</td>
</tr>
 <% 
while(rs.next())
{

%>
<tr style ="background : #f7f7f7">
<td style="color:black;margin-left:10px;font-weight: bold;padding: 4px 6px;"><%=rs.getString("LANE_TX_ID") %></td>
<td style="color:black;margin-left:10px;font-weight: bold;padding: 4px 6px;"><%=rs.getString("RECORD_TYPE") %></td>
<td style="color:black;margin-left:10px;font-weight: bold;padding: 4px 6px;"><%=rs.getString("ETC_ACCOUNT_ID") %></td>
<td style="color:black;margin-left:10px;font-weight: bold;padding: 4px 6px;"><%=rs.getString("FIRST_NAME") %></td>
<td style="color:black;margin-left:10px;font-weight: bold;padding: 4px 6px;"><%=rs.getString("MIDDLE_NAME")%></td>
<td style="color:black;margin-left:10px;font-weight: bold;padding: 4px 6px;"><%=rs.getString("LAST_NAME") %></td>
<td style="color:black;margin-left:10px;font-weight: bold;padding: 4px 6px;"><%=rs.getString("STREET_1") %></td>
<td style="color:black;margin-left:10px;font-weight: bold;padding: 4px 6px;"><%=rs.getString("STREET_2") %></td>
<td style="color:black;margin-left:10px;font-weight: bold;padding: 4px 6px;"><%=rs.getString("CITY") %></td>
<td style="color:black;margin-left:10px;font-weight: bold;padding: 4px 6px;"><%=rs.getString("STATE") %></td>
<td style="color:black;margin-left:10px;font-weight: bold;padding: 4px 6px;"><%=rs.getString("ZIP_CODE") %></td>
<td style="color:black;margin-left:10px;font-weight: bold;padding: 4px 6px;"><%=rs.getString("PLATE_STATE") %></td>
<td style="color:black;margin-left:10px;font-weight: bold;padding: 4px 6px;"><%=rs.getString("PLATE_NUMBER") %></td>
<td style="color:black;margin-left:10px;font-weight: bold;padding: 4px 6px;"><%=rs.getString("DMV_RETURN_DATE") %></td>
 <%

}
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
<tr><button type="submit" name="AddImage" value="AddImage">Add Image in Image TX</button></tr> 

  </table> <br>  
  </div>
</form>
</body>
</html>


