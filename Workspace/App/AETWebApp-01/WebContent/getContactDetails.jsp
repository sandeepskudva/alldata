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
 



</head>
<body>


<form font-size="75px;" action ="getContactDetails.jsp" name="myform" post" >
<td><tr>Plate State</td><td><input type ="text" id ="plateState" name ="plateState"/></tr></td>
<td><tr>Plate Number</td><td><input type ="text" id ="plateNumber" name ="plateNumber"/></tr></td>
  <td><select name = "addresstype" id= "addresstype">
  <option value="MAILING">MAILING</option>
  <option value="VIOLADDR">VIOLADDR</option>
  </select> </td>
<input type="submit" value="getDetails">
<table border="2" >

<%
String plateState = request.getParameter("plateState");
String plateNumber = request.getParameter("plateNumber");
String addressType = request.getParameter("addresstype");
try
{
	DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver()); 
    String dbURL = "jdbc:oracle:thin:@(DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST = 10.36.20.51)(PORT = 1521)) (CONNECT_DATA =(SERVER = DEDICATED)(SERVICE_NAME = njrbtst)))";
    String strUserID = "pbatch";
    String strPassword = "batchp"; 
    Connection c=DriverManager.getConnection(dbURL,strUserID,strPassword);
    
    String Statement = "select LAST_NAME,MID_NAME,FST_NAME,STREET_1,STREET_2,CITY,STATE,ZIP_CODE FROM V_ADDRESS,S_CONTACT WHERE etc_account_id in (select etc_account_id from v_vehicle where plate_state = '"+plateState+"' and plate_number = '"+plateNumber+"') AND etc_account_id = x_etc_account_id AND address_type_cd = '"+addressType+"'"; 
    
    ResultSet rs = c.createStatement().executeQuery(Statement);
    
    %>  
 
   <tr style ="background : brown">
<td style="color:white;margin-left:10px;font-weight: bold;padding: 4px 6px;">PLATE STATE</td>
<td style="color:white;margin-left:10px;font-weight: bold;padding: 4px 6px;">PLATE NUMBER</td>
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
 String lastname= "";
 String middlename = "";
 String firstname = "";
 String street1 = "";
 String street2="";
 String city ="";
 String state ="";
 String zipcode ="";

 
 
while(rs.next())
{
	lastname= rs.getString("LAST_NAME");
	middlename= rs.getString("MID_NAME");
	firstname= rs.getString("FST_NAME");
	street1= rs.getString("STREET_1");
	street2= rs.getString("STREET_2");
	city= rs.getString("CITY");
	state= rs.getString("STATE");
	zipcode= rs.getString("ZIP_CODE");
	
%>
<tr style ="background : #f7f7f7">
<td style="color:black;margin-left:10px;font-weight: bold;padding: 4px 6px;"><input style ="border: none" type="text" id="plateState" name = "plateState" value ="<%=plateState%>" readonly> </input></td>
<td style="color:black;margin-left:10px;font-weight: bold;padding: 4px 6px;"><input style ="border: none" type="text" id="plateNumber" name = "plateNumber" value ="<%=plateNumber%>" readonly> </input></td>
<td style="color:black;margin-left:10px;font-weight: bold;padding: 4px 6px;"><input style ="border: none" type="text" id="lastname" name = "lastname" value ="<%=lastname%>" readonly> </input></td>
<td style="color:black;margin-left:10px;font-weight: bold;padding: 4px 6px;"><input style ="border: none" type="text" id="middlename" name = "middlename" value ="<%=middlename%>" readonly> </input></td>
<td style="color:black;margin-left:10px;font-weight: bold;padding: 4px 6px;"><input style ="border: none" type="text" id="firstname" name = "firstname" value ="<%=firstname%>" readonly> </input></td>
<td style="color:black;margin-left:10px;font-weight: bold;padding: 4px 6px;"><input style ="border: none" type="text" id="street1" name = "street1" value ="<%=street1%>" readonly> </input></td>
<td style="color:black;margin-left:10px;font-weight: bold;padding: 4px 6px;"><input style ="border: none" type="text" id="street2" name = "street2" value ="<%=street2%>" readonly> </input></td>
<td style="color:black;margin-left:10px;font-weight: bold;padding: 4px 6px;"><input style ="border: none" type="text" id="city" name = "city" value ="<%=city%>" readonly> </input></td>
<td style="color:black;margin-left:10px;font-weight: bold;padding: 4px 6px;"><input style ="border: none" type="text" id="state" name = "state" value ="<%=state%>" readonly> </input></td>
<td style="color:black;margin-left:10px;font-weight: bold;padding: 4px 6px;"><input style ="border: none" type="text" id="zipcode" name = "zipcode" value ="<%=zipcode%>" readonly> </input></td>

</tr>

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
 

</form>
</body>
</html>


