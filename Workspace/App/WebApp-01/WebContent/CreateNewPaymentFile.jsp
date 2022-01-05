<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.sch.pkg.*, java.util.*, java.lang.*, com.jcraft.jsch.*,org.apache.poi.*,java.sql.*,com.Ostermiller.util.*" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CreateNewPaymentFile</title>
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
<h1>Create New Payment File</h1>
</div>



<form font-size="75px;" action ="ConfirmPaymentFileDetails.jsp" name="myform" post" onsubmit="return validate()">


<table style="width:30%">
<tr>
<td><whi/>DB :</td>
<td align="left">
    <select name = "DB" id ="DB" onchange="setOptions(this.value)">
  <option value="NJP2TST">NJP2TST</option>
  <option value="njrbtst">NJRBTST</option>
  <option value="NJAETTST">NJAETTST</option>
</select></td>  
</tr>
<tr>
<td><whi/>Collection Agency :</td>
<td align="left">
    <select name = "CollAgency" id ="CollAgency" onchange="setOptions(this.value)">
  <option value="COLL5">PAM</option>
  <option value="COLL4">RMCB</option>
</select></td>  
</tr>

  <tr>
    <td><whi/>Agency :</td>
    <td align="left">
    <select name = "agencyid" id ="agencyid" onchange="setOptions(this.value)">
  <option value="NJTP">NJTP</option>
  <option value="GSP">GSP</option>
  <option value="DRJ">DRJ</option>
  <option value="DRJAET">DRJAET</option>
   <option value="DRBA">DRBA</option>
    <option value="DRPA">DRPA</option>
     <option value="SJTA">SJTA</option>
     <option value="CAPEMAY">CAPEMAY</option>
</select></td>  
  </tr>
  <tr>
<td><whi/>File Operation :</td>
<td align="left">
    <select name = "FileOperation" id ="FileOperation" onchange="setOptions(this.value)">
  <option value="Payment">Payment</option>
  <option value="Dismissal">Dismissal</option>
</select></td>  
</tr>
  <tr>
    <td><whi/>File Date :</td>
    <td align="left"><input type ="date" id ="filedate" name ="filedate"/></td> 
  </tr>
  
  
   
  <tr>
  <td><whi/>Number of Records :</td>
<td align="left"><input type ="text" id ="NumofRecords" name ="NumofRecords"/></td> 
      </tr>
<tr>      
<td><whi/>Notice number :</td>
    <td align="left"><input type ="text" id ="Noticenumber" name ="Noticenumber"/></td>
 </tr>   
</table>
<br>
<input type="submit" value="Confirm Details">
</form>
</body>
</html>


