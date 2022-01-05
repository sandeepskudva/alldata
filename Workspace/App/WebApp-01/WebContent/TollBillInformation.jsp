<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.sch.pkg.*, java.util.*, java.lang.*, com.jcraft.jsch.*,org.apache.poi.*,java.sql.*,com.Ostermiller.util.*" %>
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
<h1>Toll Bill Information</h1>
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
<table>  <table border="2" >



<%String filename =uploadFile.getFileName();
 String xfer =uploadFile.getxferid(filename); 
 int MAX = uploadFile.getTransactionBillCount();
 %>
<p style = "font-weight: bold;">The Following Transactions for <%=xfer%> are in t_h_account_toll_bill </p>
<p style = "font-weight: bold;">The CURRENT STATUS OF THE  TRANSACTIONS in t_h_account_toll_bill is <%=MAX%>   </p>

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
    
    String Statement = "select LANE_TX_ID, ETC_ACCOUNT_ID, TOLL_BILL_NUM, STATUS,POSTED_AMOUNT from t_h_account_toll_bill where etc_account_id in (select ETC_ACCOUNT_ID from t_viol_tx where EXTERN_FILE_ID = '"+xfer+"') AND STATUS = '"+MAX+"'"; 
 
    
    ResultSet rs = c.createStatement().executeQuery(Statement);
    
    %>  
 <tr style ="background : brown">
<td><whi/>LANE_TX_ID</td>
<td><whi/>ETC_ACCOUNT_ID</td>
<td><whi/>TOLL_BILL_NUM</td>
<td><whi/>STATUS</td>
<td><whi/>POSTED_AMOUNT</td>
</tr>
 <% 
while(rs.next())
{

%>
<tr style ="background : #f7f7f7">
<td><bla/><%=rs.getString("LANE_TX_ID") %></td>
<td><bla/><%=rs.getString("ETC_ACCOUNT_ID") %></td>
<td><bla/><%=rs.getString("TOLL_BILL_NUM") %></td>
<td><bla/><%=rs.getString("STATUS") %></td>
<td><bla/><%=rs.getString("POSTED_AMOUNT")%></td>
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
<tr><button type="submit" name="RunUpdateQueries" value="RunUpdateQueries">Run Update Queries</button></tr> 

  </table> <br>  
</form>
</body>
</html>


