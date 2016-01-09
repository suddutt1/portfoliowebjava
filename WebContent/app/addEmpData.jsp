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
<span style="font-weight: bold;font-size: 2em;" >Add a new employee</span>
<a href="home.wss?tab=2" style="float:right;font-size: 1.0em;" id="backToHome"  >Back to home page</a>
<a href="logout.wss" style="float:right;font-size: 1.0em;" id="logout"  >Logout</a>  
<p style="clear:both;">&nbsp;</p>
</div>
<form id="empDataForm" action="addNewEmployeeDetails.wss" method="post">
<div class="ui-widget ui-widget-content ui-corner-all" style="padding:10px;">
<div style="padding-left: 10px;">
<div>
	<span style="display:inline-block;width:40%">
		<label style="display:inline-block;width:30%;" for="empId">Id</label>
		<input type="text" name="empId" id="empId"/>
	</span>
	<span style="display:inline-block;width:40%">
			<label style="display:inline-block;width:30%;" for="name">Name</label>
			<input type="text" name="name" id="name"/>
	</span>
</div>	
<div><br/></div>
<div>
	<span style="display:inline-block;width:40%">
		<label style="display:inline-block;width:30%;"  for="intranetId">Intranet id</label>
		<input type="text" name="intranetId" id="intranetId" />
	</span>
	<span style="display:inline-block;width:40%">
			<label style="display:inline-block;width:30%;"   for="empType">Type</label>
			<pfd:refdata name="empType" datalist="${empTypeList}" id="empType" ></pfd:refdata>
	</span>
</div>		
<div><br/></div>
<div>
	<span style="display:inline-block;width:40%">
			<label style="display:inline-block;width:30%;"   for="notesId">Notes id</label>
			<input type="text" name="notesId" id="notesId"/>
	</span>
	<span style="display:inline-block;width:40%">
			<label style="display:inline-block;width:30%;"   for="eltpFg">ELTP</label>
			<span id="eltpRadio">
				<input type="radio" id="radio1" name="eltpFg" value ="Y" ><label for="radio1">Yes</label>
				<input type="radio" id="radio2" name="eltpFg" value ="N" ><label for="radio2">No</label>
			</span>
	</span>
</div>
<div><br/></div>
<div>
	<span style="display:inline-block;width:40%">
			<label style="display:inline-block;width:30%;"   for="gender">Gender</label>
			<span id="genderRadio">
				<input type="radio" id="radio5" name="gender" value ="Male"  ><label for="radio5">Male</label>
				<input type="radio" id="radio6" name="gender" value ="Female" ><label for="radio6">Female</label>
			</span>
	</span>
	<span style="display:inline-block;width:40%">
			<label style="display:inline-block;width:30%;"   for="primarySkill">Primary Skill</label>
			<pfd:refdata name="primarySkill" datalist="${skillList}" id="primarySkill" defopt="select"></pfd:refdata>
	</span>
</div>
<div><br/></div>
<div>
	<span style="display:inline-block;width:40%">
			<label style="display:inline-block;width:30%;"   for="location">Location</label>
			<pfd:refdata name="location" datalist="${locationList}" id="location" defopt="select" ></pfd:refdata>
	</span>
	<span style="display:inline-block;width:40%">
			<label style="display:inline-block;width:30%;"   for="building">Building</label>
			<pfd:refdata name="building" datalist="${buildingList}" id="building" defopt="select" ></pfd:refdata>
	</span>
</div>
<div><br/></div>
<div>
	<span style="display:inline-block;width:40%">
			<label style="display:inline-block;width:30%;"   for="band">Band</label>
			<pfd:refdata name="band" datalist="${bandList}" id="band" defopt="select"></pfd:refdata>
	</span>
	<span style="display:inline-block;width:40%">
			<label style="display:inline-block;width:30%;"   for="subBand">Sub band</label>
			<pfd:refdata name="subBand" datalist="${subBandList}" id="subBand" defopt="select"></pfd:refdata>
	</span>
</div>
<div><br/></div>
<div>
	<span style="display:inline-block;width:40%">
			<label style="display:inline-block;width:30%;"   for="pem">PeM</label>
			<pfd:mgrrefdata name="pem" mgrtype="pem" datalist="${mgrList}" id="pem" defopt="select" ></pfd:mgrrefdata>
	</span>
</div>
<div><br/></div>
<div>
	<input type="button" id="addBtn" value="Add employee"/>
</div>
</div>
</div>
</form>
<!--  -->
<!-- ui-dialog -->
<div id="dialog" title="Save result">
	<p id="message"></p>
</div>
</body>
<script type="text/javascript">
$(document).ready(function(){
	$("#pem").selectmenu({"width":300}).selectmenu( "menuWidget" ).css("height","50px");
	$("#empType").selectmenu();
	$("#location").selectmenu().selectmenu( "menuWidget" ).css("height","100px");
	$("#subBand").selectmenu({width: 100}).selectmenu( "menuWidget" ).css("height","100px");;
	$("#band").selectmenu().selectmenu({width: 100});
	$("#building").selectmenu().selectmenu( "menuWidget" ).css("height","100px");
	$("#primarySkill").selectmenu().selectmenu( "menuWidget" ).css("height","100px");
	
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
		$.post("addNewEmployeeDetails.wss", $('#empDataForm').serialize()).done(function(data){
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
</html>