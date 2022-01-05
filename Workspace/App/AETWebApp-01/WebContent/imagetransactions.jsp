
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.sch.pkg.*, java.util.*, java.lang.*, com.jcraft.jsch.*,org.apache.poi.*,java.sql.*,com.profesorfalken.jpowershell.*" %>
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
<h1>Image transactions</h1>
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
 int count = uploadFile.getImageCount();
 %>
<p style = "font-weight: bold;">The Following Transactions for <%=xfer%> are in t_viol_image_tx </p>
<p style = "font-weight: bold;">The total transactions in t_viol_image_tx is <%=count%>   </p>

<%
try
{
	DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver()); 
    String dbURL = "jdbc:oracle:thin:@(DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST = 10.36.20.51)(PORT = 1521)) (CONNECT_DATA =(SERVER = DEDICATED)(SERVICE_NAME = njrbtst)))";
    String strUserID = "pbatch";
    String strPassword = "batchp"; 
    Connection c=DriverManager.getConnection(dbURL,strUserID,strPassword);
    
    String Statement = "select * from t_viol_image_tx where LANE_TX_ID in (select LANE_TX_ID from t_viol_tx where extern_file_id = '"+xfer+"')"; 
    
    ResultSet rs = c.createStatement().executeQuery(Statement);
    
    %>  
 <tr style ="background : brown">
<td><whi/>LANE_TX_ID</td>
<td><whi/>TX_TIMESTAMP</td>
<td><whi/>OUT_FILE_NAME</td>
<td><whi/>PLATE STATE</td>
<td><whi/>PLATE NUMBER</td>
</tr>
 <% 
while(rs.next())
{

%>
<tr style ="background : #f7f7f7">
<td><bla/><%=rs.getString("LANE_TX_ID") %></td>
<td><bla/><%=rs.getString("TX_TIMESTAMP") %></td>
<td><bla/><%=rs.getString("OUT_FILE_NAME") %></td>
<td><bla/><%=rs.getString("OCR_PLATE_STATE") %></td>
<td><bla/><%=rs.getString("OCR_PLATE_NUMBER")%></td>
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
<tr><button type="submit" name="RunSiebelJob" value="RunSiebelJob">Run Siebel Job</button></tr> 

  </table> <br>  
</form>
</body>
</html>


