<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.sch.pkg.*, java.util.*, java.lang.*, com.jcraft.jsch.*,org.apache.poi.*,java.sql.*,java.time.*,java.text.*" %>
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
<h1>File Creation Page</h1>
</div>
<center><img src='IMG/statusNULL.png' ></img></center>
<nav>
  <ul class="top-menu">
    <li><a href="index.jsp">Home</a><div class="menu-item" id="home"></div></li>
    <li><a href="CreateNew.jsp">Create New Set</a><div class="menu-item" id="CreateNew"></div></li>
    <li><a href="ExistingViolations.jsp">OpenExisting</a><div class="menu-item" id="OpenExisting"></div></li>
    <li><a href="contact.jsp">Contact</a><div class="menu-item" id="contact"></div></li>
    <li><a href="about.jsp">About</a><div class="menu-item" id="About"></div></li>
  </ul>
</nav>
<form font-size="75px;" action = "moveAndRun.jsp" method="post" >

<table>
<%String agencyname =uploadFile.getagencyselection();%>
<%=uploadFile.createFile()%>
 
     <%String filename =uploadFile.getFileName();%>
     
    <tr> <whi/>File Name created : <%=filename%><tr>
     <%String xfer =uploadFile.getxferid(filename);%>
     <%if(xfer.equals("NULL"))
     {
    	 uploadFile.insertxferid(filename); 
    	 xfer =uploadFile.getxferid(filename);
     }     
     %>          
    
     <%=uploadFile.insertxferinFile(xfer)%>
      <tr><whi/>XFER ID Created : <%=xfer%></tr>
     <%=uploadFile.createFile()%> 
     
     <p> Please note this xfer id since it can be used in the process of notice creation, escalation and collection</p>    
     <tr><input type="submit" value="Move File" id = "MoveFile"></tr>
    
  </table>
	
</form>
</body>
</html>


