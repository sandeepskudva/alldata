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
<%String filename =uploadFile.getFileName();
 String xfer =uploadFile.getxferid(filename);
 String viol_tx_status =uploadFile.getViol_tx_status(xfer);
 if(viol_tx_status.equals("10"))
 {
 String level = uploadFile.getcitationlevel(xfer); 
 if(level.contains("COL"))
 {
 viol_tx_status = "NTC";	 
 }
 else if(level.contains("A"))
 {	 
 viol_tx_status = "NTA";
 }
 else if(level.contains("B"))
 {	 
 viol_tx_status = "NTB";
 }
 
 }
 %>


</head>
<body>
<div class="loader"></div>
<div id="left"></div>
<div id="right"></div>
<div id="top"></div>
<div id="bottom"></div>
<div class="loader"></div>
<div class="stitched">
<h1>Notice Information</h1>
</div>
<center><img src='IMG/status<%=viol_tx_status%>.png' ></img></center>
<nav>
  <ul class="top-menu">
    <li><a href="index.jsp">Home</a><div class="menu-item" id="home"></div></li>
    <li><a href="CreateNew.jsp">Create New Set</a><div class="menu-item" id="CreateNew"></div></li>
    <li><a href="ExistingViolations.jsp">OpenExisting</a><div class="menu-item" id="OpenExisting"></div></li>
    <li><a href="contact.jsp">Contact</a><div class="menu-item" id="contact"></div></li>
    <li><a href="about.jsp">About</a><div class="menu-item" id="About"></div></li>
  </ul>
</nav>

 


<p style = "font-weight: bold;">Transactions for Xfer ID : <%=xfer%><p>


<form font-size="75px;"  action="${pageContext.request.contextPath}/buttonServlet" method="post">
<table border="2" >

<%
try
{
	DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver()); 
    String dbURL = "jdbc:oracle:thin:@(DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST = 10.36.20.51)(PORT = 1521)) (CONNECT_DATA =(SERVER = DEDICATED)(SERVICE_NAME = njrbtst)))";
    String strUserID = "pbatch";
    String strPassword = "batchp"; 
    Connection c=DriverManager.getConnection(dbURL,strUserID,strPassword);
    
    String Statement = "select DISTINCT ACCOUNT_NO,ACCOUNT_TYPE_CD,CITATION_NUMBER,CITATION_LEVEL_CD,PLATE_STATE,PLATE_NUMBER  from v_citation_detail D,V_ETC_ACCOUNT E, T_VIOL_TX T where T.EXTERN_FILE_ID ='"+xfer+"' and T.LANE_TX_ID = D.LANE_TX_ID and D.ETC_ACCOUNT_ID = E.ETC_ACCOUNT_ID"; 
    
    ResultSet rs = c.createStatement().executeQuery(Statement);
    
    %>  
   <tr style ="background : brown">
<td><whi/>ACCOUNT NUMBER</td>
<td><whi/>ACCOUNT TYPE</td>
<td><whi/>CITATION NUMBER</td>
<td><whi/>CITATION LEVEL</td>
<td><whi/>PLATE STATE</td>
<td><whi/>PLATE NUMBER</td>
</tr>
 <% 
while(rs.next())
{

%>
<tr style ="background : #f7f7f7">
<td><bla/><%=rs.getString("ACCOUNT_NO") %></td>
<td><bla/><%=rs.getString("ACCOUNT_TYPE_CD") %></td>
<td><bla/><%=rs.getString("CITATION_NUMBER") %></td>
<td><bla/><%=rs.getString("CITATION_LEVEL_CD") %></td>
<td><bla/><%=rs.getString("PLATE_STATE") %></td>
<td><bla/><%=rs.getString("PLATE_NUMBER") %></td>

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

 <tr><button type="submit" name="NoticeEscalation" value="NoticeEscalation">Escalate to Next Level</button></tr>
 <tr><button type="submit" name="DeleteImage" value="DeleteImage">DeleteImage</button></tr>
</form>
</body>
</html>


