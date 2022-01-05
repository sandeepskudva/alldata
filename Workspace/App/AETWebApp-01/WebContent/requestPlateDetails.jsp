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
	
    $("#BuildPlate").prop('disabled', true); 
	
})

</script>
<%String agencyname = uploadFile.getagencyselection(); %>

<%int num = Integer.parseInt(common.ExcelRead("PlateDetails", "E:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", 1, 6));%>
 <script type="text/javascript">
 var cnt=false;
function verifyVehicle(i,num)
{
	
	
	var plate_state = document.forms["verification"]["PlateState"+i].value;
	var plate_number = document.forms["verification"]["PlateNumber"+i].value;
	
	console.log(plate_state+","+plate_number);
	
	
	

	$.ajax({
		 type: 'GET',
		    url: "/WebApp-01//buttonServlet?plate_state="+plate_state+"&plate_number="+plate_number,		    
		    success: function(data, status, xhr) {
		        var code1 = xhr.getResponseHeader('accountNum');
		        var message;
		        
		       
		
		        if(code1=='0')
		        {
		        	
		        	   message = "Plate does not exist";
					  $('#message'+i).html(message);
					  $('#message'+i).css("color", "green");
					 
		        }
		       else
			  {
			  message = "Plate already exists";
			  $('#message'+i).html(message);
			  $('#message'+i).css("color", "red");
			  cnt=true;
			
			  }
		      
		      
		    }
		});



}


function getcontent(num)
{
	var desc1 ="Plate";
	
	var count =($(".message").text().match(new RegExp(desc1, "g")) || []).length;
	
	if(count==num)
	{
		
		 $("#BuildPlate").prop('disabled', false); 
		 
	}
	else
	{
		 $("#BuildPlate").prop('disabled', true); 
		
	}	
	
	

}
setInterval(function(){ 
	getcontent(<%=num%>); 
}, 3000);

</script>
<script>
$(document).ready(function(){
$("#DistributePlate").click(function(){
var txt1=$("#GeneralPlate").val();
var txt2=$("#GeneralState").val();

for(var i=1;i<=<%=num%>;i++)
	
{	
	
	$("#PlateNumber"+i).val(txt1+i);
	$("#PlateState"+i).val(txt2);
	verifyVehicle(i,<%=num%>);
 }  
	
 
 });
 });
 
$(document).ready(function(){
	$("#DuplicatePlate").click(function(){
	var txt1=$("#GeneralPlate").val();
	var txt2=$("#GeneralState").val();

	for(var i=1;i<=<%=num%>;i++)
		
	{	
		
		$("#PlateNumber"+i).val(txt1);
		$("#PlateState"+i).val(txt2);
		verifyVehicle(i,<%=num%>);
	 }  
		
	 
	 });
	 });
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
<h1>Request Image Details Page</h1>
</div>
<center><img src='IMG/status4.png' ></img></center>
<nav>
  <ul class="top-menu">
    <li><a href="index.jsp">Home</a><div class="menu-item" id="home"></div></li>
    <li><a href="CreateNew.jsp">Create New Set</a><div class="menu-item" id="CreateNew"></div></li>
    <li><a href="ExistingViolations.jsp">OpenExisting</a><div class="menu-item" id="OpenExisting"></div></li>
    <li><a href="contact.jsp">Contact</a><div class="menu-item" id="contact"></div></li>
    <li><a href="about.jsp">About</a><div class="menu-item" id="About"></div></li>
  </ul>
</nav>
<form>
<tr>
     <td align="center"><input type ="text" id ="GeneralState" name ="GeneralState"/></td>  
     <td align="center"><input type ="text" id ="GeneralPlate" name ="GeneralPlate"/></td> 
      <td align="center"><input type ="button" id ="DistributePlate" value="DistributePlate"></td>
       <td align="center"><input type ="button" id ="DuplicatePlate" value="DuplicatePlate"></td>
     </tr>
</form>
<form font-size="75px;" name="verification" action="${pageContext.request.contextPath}/buttonServlet" method="post">

<table style="width:40%" border="2">

 <tr style ="background : Navy">
<td align="center"><whi/>Plate State</td>
<td align="center"><whi/>Plate Number</td>
<td align="center"><whi/>Verify Plate</td>
<td align="center"><whi/>Message</td>
</tr>


  


<%for(int i=1;i<=num;i++)
	
{	
	%>
	
	<tr>
     <td align="center"><input type ="text" id ="PlateState<%=i%>" name ="PlateState<%=i%>"/></td> 
    <td align="center"><input type ="text" id ="PlateNumber<%=i%>" name ="PlateNumber<%=i%>"/></td> 
    <td align="center"><input type ="button" id ="vplate<%=i%>" name ="vplate<%=i%>" value="Verify Plate" onClick ="verifyVehicle(<%=i%>,<%=num%>)"/></td> 
    <td align="center" style = "background : black"><span id="message<%=i%>" class="message"></span></td> 
    </tr> 
 <%} %>     

 
    
    

</table>
<input type='submit' name='BuildPlate' id='BuildPlate'  value='BuildPlate'/>

</form>
</body>
</html>


