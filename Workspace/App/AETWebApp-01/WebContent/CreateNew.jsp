<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.sch.pkg.*, java.util.*, java.lang.*, com.jcraft.jsch.*,org.apache.poi.*,java.sql.*,com.Ostermiller.util.*" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Schedular- QA</title>
<link rel ="stylesheet" href="CSS/indexStyle.css">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript">
$(window).load(function() {
	$(".loader").fadeOut("slow");
})
</script>

 <script type="text/javascript">
            function validate()
            {
            	var date = document.getElementById("filedate");
                var NumNotice = document.getElementById("NumNotice");
                
                var valid = true;
                if(date.value.length<=0 ||NumNotice.value.length<=0)
                    {
                        alert("Please fill all fields");
                        valid = false;
                    }
                
                else{
                    if(isNaN(NumNotice.value)){
                        alert("Please enter number only");
                valid = false;}
            }
                 
                return valid;
            };

        </script>
<script>
    
 
  function setOptions(value) 
  {
	  if(value=="GSP" || value == "SJTA")
	{
		  console.log("4 violations"+value);
		  document.getElementById("5").style.display = "none";
		  document.getElementById("ACM").style.display = "Block";
		  
	}	
	  else if (value == "CAPEMAY")
		  {
		  console.log("4 violations"+value);
		  document.getElementById("5").style.display = "none";
		  
		  
		  }
	  else
		{
		  console.log("5 violations"+value);
		  document.getElementById("5").style.display = "Block";		  
		  document.getElementById("ACM").style.display = "none";
		}  
	  
  }
      
    </script>
        
</head>
<body>
<div class="loader"></div>
<div id="left"></div>
<div id="right"></div>
<div id="top"></div>
<div id="bottom"></div>
<div class="stitched">
<h1>Create New Set</h1>
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
</nav><br>

<form font-size="75px;" action ="ConfirmDetails.jsp" name="myform" post" onsubmit="return validate()">
<p>Please enter File date and transaction date within 60 days otherwise the notice will not be created</p>

<table style="width:30%">
  <tr>
    <td><whi/>Agency :</td>
    <td align="left">
    <select name = "agencyid" id ="agencyid" onchange="setOptions(this.value)">
  <option value="NJTP">NJTP</option>
  <option value="GSP">GSP</option>
  <option value="DRJ">DRJ</option>
   <option value="DRBA">DRBA</option>
    <option value="DRPA">DRPA</option>
     <option value="SJTA">SJTA</option>
     <option value="CAPEMAY">CAPEMAY</option>
</select></td>  
  </tr>
  <tr>
    <td><whi/>File Date :</td>
    <td align="left"><input type ="date" id ="filedate" name ="filedate"/></td> 
  </tr>
  
  <tr>
    <td><whi/>Transaction Date :</td>
    <td align="left"><input type ="date" id ="trxDate" name ="trxDate"/></td> 
  </tr>
   <tr>
    <td><whi/>Normal or ACM</td>
    <td align="left">
    <select name = "ViolType" id= "ViolType">
    <option value="NOR">NORMAL</option>
    <option value="ACM" id ="ACM">ACM</option>
  </tr>
  <tr>
  <td><whi/>Number of notices :</td>
    <td align="left">
    <select name = "NumNotice" id= "NumNotice">
  <option value="1">1</option>
  <option value="2">2</option>
  <option value="3">3</option>
  <option value="4">4</option>
  <option value="5">5</option>
  <option value="6">6</option>
  <option value="7">7</option>
  <option value="8">8</option>
  <option value="9">9</option>
  <option value="10">10</option>
  <option value="50">50</option>
  
</select></td>  
      </tr>
  <tr>
    <td><whi/>Number of violations per notice : </td>
    <td align="left">
    <select name = "violnumber" id= "violnumber">
  <option value="1" id ="1">1</option>
  <option value="2" id ="2">2</option>
  <option value="3" id ="3">3</option>
  <option value="4" id= "4">4</option>
  <option value="5" id="5">5</option>

  
</select></td>  
  </tr>
</table>
<br>
<input type="submit" value="Confirm Details">
</form>
</body>
</html>


