<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib tagdir="/WEB-INF/tags/pfd"  prefix="pfd" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="js/jqueryui/jquery-ui.css" rel="stylesheet">
<link href="css/jquery.dataTables_themeroller.css" rel="stylesheet">
<script src="js/jquery.js"></script>
<script src="js/jqueryui/jquery-ui.js"></script>
<script src="js/jquery.dataTables.min.js"></script>
<style type="text/css">
.mandatory {
	font-weight: bold;
	color: #ff0000;
}
</style>
<title>Portfolio database</title>
</head>
<body>
<div id="appToolBar" >
<span style="font-weight: bold;font-size: 2em;" >Add new manager data</span>
<a href="home.wss" style="float:right;font-size: 1.0em;" id="backToHome"  >Back to home</a>
<a href="logout.wss" style="float:right;font-size: 1.0em;" id="logout"  >Logout</a>  
<p style="clear:both;">&nbsp;</p>
</div>
<form action="addNewManager.wss" method="post" id="mgrData">
	<div class="ui-widget ui-widget-content ui-corner-all" style="padding:10px;">
		<div>
		<label for="mgrType" style="display:inline-block;width:30%" >Select manager type<pfd:mandatory /></label>
		<select id="mgrType" name="mgrType">
			<option value="pem">People Manager</option>
			<option value="tpm">Tower/Portfolio Manager</option>
			<option value="pal">Practice Area Lead(PAL)</option>
			<option value="am">Account manager</option>
			<option value="bam">Business Area Manager(BAM)</option>
			<option value="spmpm">SPM/PM</option>
			<option value="uompm">UOM Manager</option>
			<option value="pm">Project Manager</option>
			<option value="dpe">Delivery Project Executive(DPE)</option>
			<option value="dir">Director</option>
			<option value="l1">L1 Executive</option>
			<option value="l2">L2 Executive</option>
			<option value="flm">First Line Managers</option>
			<option value="slm">Second Line Managers</option>
		</select>
		</div>
		<div>
			<span style="display:inline-block;width:30%"><label for="empId" >Employee id</label><pfd:mandatory /></span><input type="text" id="empId" name="empId" />
		</div>
		<div>
			<span style="display:inline-block;width:30%"><label for="empName" >Name</label><pfd:mandatory /></span><input type="text" id="empName" name="empName" />
		</div>
		<div>
			<span style="display:inline-block;width:30%"><label for="notesId" >Notes id</label><pfd:mandatory /></span><input type="text" id="notesId" name="notesId" />
		</div>
		<div>
			<span style="display:inline-block;width:30%"><label for="emailId" >Intranet id</label><pfd:mandatory /></span><input type="text" id="emailId" name="emailId" />
		</div>
		
		<div>
		<input type="button" id="addBtn" value="Add manager"/>
		</div>
	</div>
</form>
<!-- ui-dialog -->
<div id="dialog" title="Save result">
	<p id="message"></p>
</div>
</body>
<script type="text/javascript">

$(document).ready(function(){
	
	$("#mgrType").selectmenu();
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
		$.post("addNewManager.wss", $('#mgrData').serialize()).done(function(data){
			var response = jQuery.parseJSON(data);
			var infoText ="Unknown response";
			if(response.status ==0)
			{
				infoText = "Sucessful save";
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
</html>