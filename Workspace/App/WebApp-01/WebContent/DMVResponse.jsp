
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
<h1>DMV Response Page</h1>
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
<form font-size="75px;" action="${pageContext.request.contextPath}/buttonServlet" method="post">
<table>  <table border="2" >




<tr> <whi/>DMV File has been moved to the desired location<tr>


 
</table>

 
<tr><button type="submit" name="RunDMVResponse" value="RunDMVResponse">Run DMV Response Job</button></tr>
  </table> <br>  
</form>
</body>
</html>


