<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib tagdir="/WEB-INF/tags/pfd"  prefix="pfd" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>     
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="js/jqueryui/jquery-ui.css" rel="stylesheet">
<link href="css/jquery.dataTables_themeroller.css" rel="stylesheet">
<script src="js/jquery.js"></script>
<script src="js/jqueryui/jquery-ui.js"></script>
<script src="js/jquery.dataTables.min.js"></script>
<title>Portfolio database</title>
</head>
<body>
<div id="appToolBar" >
<span style="font-weight: bold;font-size: 2em;" >Add new user</span>
<a href="home.wss?tab=3" style="float:right;font-size: 1.0em;" id="backToHome"  >Back to home</a>
<a href="logout.wss" style="float:right;font-size: 1.0em;" id="logout"  >Logout</a>  
<p style="clear:both;">&nbsp;</p>
</div>
<form action="addNewUser.wss" method="post" id="usrData">
	<div class="ui-widget ui-widget-content ui-corner-all" style="padding:10px;">
	<!--  Gen code start-->
	<div>
	<span style="display:inline-block;width:40%"> 
		<label style="display:inline-block;width:30%;" for="firstName">First name</label> 
		<input type="text" name="firstName" id="firstName" /> 
	 </span> 
	
	<span style="display:inline-block;width:40%"> 
		<label style="display:inline-block;width:30%;" for="lastName">Last name</label> 
		<input type="text" name="lastName" id="lastName" /> 
	 </span> 
	</div>
	<div><br/></div>
	<div> 
	<span style="display:inline-block;width:40%"> 
		<label style="display:inline-block;width:30%;" for="uid">User id</label> 
		<input type="text" name="uid" id="uid" /> 
	 </span> 
	
	<span style="display:inline-block;width:40%"> 
		<label style="display:inline-block;width:30%;" for="password">Password</label> 
		<input type="password" name="password" id="password" /> 
	 </span> 
	</div>
	<div><br/></div>
	<div>
	<span style="display:inline-block;width:40%"> 
		<label style="display:inline-block;width:30%;" for="emailId">Email Id</label> 
		<input type="text" name="emailId" id="emailId" /> 
	 </span> 
	
	<span style="display:inline-block;width:40%"> 
		<label style="display:inline-block;width:30%;" for="role">Role</label> 
		<select id="role" name="role">
			<option value="manager">Manager</option>
			<option value="admin">Admin</option>
			
		</select>
	 </span> 
	</div>
	<!--  Gen code ends -->
	<div><br/></div>
	<div>
	<input type="button" id="addBtn" value="Add user"/>
	</div>
	</div>
</form>

<!-- ui-dialog -->
<div id="dialog" title="Save result">
	<p id="message"></p>
</div>
<script>
$(document).ready(function(){
	$( "#dialog" ).dialog({
		autoOpen: false,
		width: 400,
		buttons: [
			{
				text: "Ok",
				click: function() {
					$( this ).dialog( "close" );
				}
			}
			]
		});
	
	$("#addBtn").button().click(function(event){
		$.post("addNewUser.wss", $('#usrData').serialize()).done(function(data){
			var response = jQuery.parseJSON(data);
			var infoText ="Unknown response";
			if(response.status ==0)
			{
				infoText = "Sucessful update";
			}
			else if( response.statusText !=null)
			{
				infoText = response.statusText;
			}
			$("#message").html(infoText);
			$("#dialog" ).dialog( "open" );
		});
        
    } );


		$("#backToHome").button({
							icons: {
								primary: 'ui-icon-home'
							}
			});
		$("#logout").button({
							icons: {
								primary: 'ui-icon-power'
							}
		});
});
</script>
</body>
</html>
