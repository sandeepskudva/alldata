<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Schedular- QA</title>
<link rel ="stylesheet" href="CSS/indexStyle.css">
<link href='http://fonts.googleapis.com/css?family=Kaushan+Script' rel='stylesheet' type='text/css'>

<link href='https://fonts.googleapis.com/css?family=PT+Sans+Caption:400,700' rel='stylesheet' type='text/css'>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript">
$(window).load(function() {
	$(".loader").fadeOut("slow");
})
</script>
</head>
<body>
<div id="left"></div>
<div id="right"></div>
<div id="top"></div>
<div id="bottom"></div>
<div class="loader"></div>
<div class="stitched">
 <h1>Adhoc Jobs</h1>
</div>

        
<nav>
  <ul class="top-menu">
    <li><a href="index.jsp">Home</a><div class="menu-item" id="home"></div></li>
    <li><a href="CreateNew.jsp">Create New Set</a><div class="menu-item" id="CreateNew"></div></li>
    <li><a href="ExistingViolations.jsp">OpenExisting</a><div class="menu-item" id="OpenExisting"></div></li>
    <li><a href="contact.jsp">Contact</a><div class="menu-item" id="contact"></div></li>
    <li><a href="about.jsp">About</a><div class="menu-item" id="About"></div></li>
  </ul>
</nav>



<form font-size="75px;"  action="${pageContext.request.contextPath}/buttonServlet" method="post">
<table style="width:30%">
  <tr>
    <td><whi/>Siebel Job :</td>
    <td align="left">
    <select name = "SiebelJob">
    <option value="00">select</option>
  <option value="01">VACJob</option>
  <option value="02">NoticeProcessingJob</option>
</select></td>
 
  </tr>
</table>
<table style="width:30%">
  <tr>
    <td><whi/>TPMS Job :</td>
    <td align="left">
    <select name = "TPMSJob">
  <option value="10">select</option>
  <option value="11">QATPNJTP</option>
  <option value="12">QATPGSP</option>
  <option value="13">LICA</option>
  <option value="14">DMVRequest</option>
  <option value="15">DMVResponse</option>
  <option value="16">TPMSVAC</option>
  <option value="17">NoticeProcessingNJTP</option>
  <option value="18">NoticeProcessingGSP</option>
  <option value="19">NoticeEscalationNJTP</option>
  <option value="20">NoticeEscalationGSP</option>
</select></td>  
  </tr>
</table>
<td><input type="submit" value="Go"></td> 
</form>
</body>
</html>

