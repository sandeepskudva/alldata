<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Schedular- QA</title>
<link rel ="stylesheet" href="CSS/indexStyle.css">

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
 <h1>Notice - Data Creator</h1>
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


<form action='MyServlet'>
<center><p> This is the Home Page for Creation of Notices and escalation of notices till collection</p></center>
</form>
<form style ="background : #57585A" class ="container">
<center><img src="IMG/sidewalk.png" height = '40' width ='527' ><img src="IMG/sidewalk.png" height = '40' width ='527'  ><img src="IMG/sidewalk.png" height = '40' width ='527' ></center>
<marquee direction="Right" scrollamount="19"><img class ="topright" src="IMG/car1.png" width="120" height="80" /><img src="IMG/truck1.png" width="120" height="80"/></marquee>
<center><img src="IMG/Road.png" height = '60' width ='521' ><img src="IMG/Road.png" height = '60' width ='521'  ><img src="IMG/Road.png" height = '60' width ='521' ></center>
<marquee direction="left" scrollamount="22"><img src="IMG/car2.png"  	 width="120" height="80"   /></marquee>
<marquee direction="Right" scrollamount="12"><img src="IMG/Runner.gif" width="120" height="80"/><img src="IMG/dog.gif" width="100" height="60"/></marquee>
<center><img src="IMG/sidewalk.png" height = '40' width ='527' ><img src="IMG/sidewalk.png" height = '40' width ='527'  ><img src="IMG/sidewalk.png" height = '40' width ='527' ></center>
</form>
</body>
</html>

