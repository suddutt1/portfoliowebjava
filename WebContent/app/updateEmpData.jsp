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
<span style="font-weight: bold;font-size: 2em;" >Update Employee Data</span>
<a href="loadEmployeeView.wss" style="float:right;font-size: 1.0em;" id="backToHome"  >View All Employee Data</a>
<a href="logout.wss" style="float:right;font-size: 1.0em;" id="logout"  >Logout</a>  
<p style="clear:both;">&nbsp;</p>
</div>
<form id="empDataForm" action="updateEmployeeDetails.wss" method="post">
<input type="hidden" name="empObjId" id="empObjId" value="${empObjId}" />
<div class="ui-widget ui-widget-content ui-corner-all" style="padding:10px;">
<div style="padding-left: 10px;">
<div>
	<span style="display:inline-block;width:40%">
		<label style="display:inline-block;width:30%;" for="empId">Id</label>
		<input type="text" name="empId" id="empId" value="${empDetails.empId}"/>
	</span>
	<span style="display:inline-block;width:40%">
			<label style="display:inline-block;width:30%;" for="name">Name</label>
			<input type="text" name="name" id="name" value="${empDetails.name}"/>
	</span>
</div>	
<div><br/></div>
<div>
	<span style="display:inline-block;width:40%">
		<label style="display:inline-block;width:30%;"  for="intranetId">Intranet id</label>
		<input type="text" name="intranetId" id="intranetId" value="${empDetails.intranetId}"/>
	</span>
	<span style="display:inline-block;width:40%">
			<label style="display:inline-block;width:30%;"   for="empType">Type</label>
			<pfd:refdata name="empType" datalist="${empTypeList}" id="empType" selected="${empDetails.empType}"></pfd:refdata>
	</span>
</div>		
<div><br/></div>
<div>
	<span style="display:inline-block;width:40%">
			<label style="display:inline-block;width:30%;"   for="notesId">Notes id</label>
			<input type="text" name="notesId" id="notesId" value="${empDetails.notesId}"/>
	</span>
	<span style="display:inline-block;width:40%">
			<label style="display:inline-block;width:30%;"   for="eltpFg">ELTP</label>
			<span id="eltpRadio">
			<c:if test="${empDetails.eltpFg eq 'Y' }">
				<input type="radio" id="radio1" name="eltpFg" value ="Y" checked="checked"><label for="radio1">Yes</label>
				<input type="radio" id="radio2" name="eltpFg" value ="N" ><label for="radio2">No</label>
			</c:if>
			<c:if test="${empDetails.eltpFg ne 'Y' }">
				<input type="radio" id="radio3" name="eltpFg" value ="Y" ><label for="radio3">Yes</label>
				<input type="radio" id="radio4" name="eltpFg" value ="N" checked="checked" ><label for="radio4">No</label>
			</c:if>
			</span>
	</span>
</div>
<div><br/></div>
<div>
	<span style="display:inline-block;width:40%">
			<label style="display:inline-block;width:30%;"   for="gender">Gender</label>
			<span id="genderRadio">
			<c:if test="${empDetails.gender eq 'Female' }">
				<input type="radio" id="radio5" name="gender" value ="Male" ><label for="radio5">Male</label>
				<input type="radio" id="radio6" name="gender" value ="Female" checked="checked" ><label for="radio6">Female</label>
			</c:if>
			<c:if test="${empDetails.gender ne 'Female' }">
				<input type="radio" id="radio7" name="gender" value ="Male" checked="checked" ><label for="radio7">Male</label>
				<input type="radio" id="radio8" name="gender" value ="Female"  ><label for="radio8">Female</label>
			</c:if>	
			</span>
	</span>
	<span style="display:inline-block;width:40%">
			<label style="display:inline-block;width:30%;"   for="primarySkill">Primary Skill</label>
			<pfd:refdata name="primarySkill" datalist="${skillList}" id="primarySkill" selected="${empDetails.primarySkill}"></pfd:refdata>
	</span>
</div>
<div><br/></div>
<div>
	<span style="display:inline-block;width:40%">
			<label style="display:inline-block;width:30%;"   for="location">Location</label>
			<pfd:refdata name="location" datalist="${locationList}" id="location" selected="${empDetails.location}"></pfd:refdata>
	</span>
	<span style="display:inline-block;width:40%">
			<label style="display:inline-block;width:30%;"   for="building">Building</label>
			<pfd:refdata name="building" datalist="${buildingList}" id="building" selected="${empDetails.building}"></pfd:refdata>
	</span>
</div>
<div><br/></div>
<div>
	<span style="display:inline-block;width:40%">
			<label style="display:inline-block;width:30%;"   for="band">Band</label>
			<pfd:refdata name="band" datalist="${bandList}" id="band" selected="${empDetails.band}"></pfd:refdata>
	</span>
	<span style="display:inline-block;width:40%">
			<label style="display:inline-block;width:30%;"   for="subBand">Sub band</label>
			<pfd:refdata name="subBand" datalist="${subBandList}" id="subBand" selected="${empDetails.subBand}"></pfd:refdata>
	</span>
</div>
<div><br/></div>
<div>
	<span style="display:inline-block;width:40%">
			<label style="display:inline-block;width:30%;"   for="pem">PeM</label>
			<pfd:mgrrefdata name="pem" mgrtype="pem" datalist="${mgrList}" id="pem" selected="${empDetails.pem}"></pfd:mgrrefdata>
	</span>
	<span style="display:inline-block;width:40%">
			<label style="display:inline-block;width:30%;"   for="attrType">Attrition type</label>
			<input type="text" name="attrType" id="attrType" value="${empDetails.attrType}"/>
	</span>
</div>
<div><br/></div>
<div>
	<input type="button" id="updateBtn" value="Update data"/>
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
	$("#pem").selectmenu().selectmenu( "menuWidget" ).css("height","50px");
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
	
	$("#updateBtn").button().click(function(event){
		$.post("updateEmployeeDetails.wss", $('#empDataForm').serialize()).done(function(data){
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