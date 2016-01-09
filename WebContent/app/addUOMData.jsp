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
<span style="font-weight: bold;font-size: 2em;" >Add UOM</span>
<a href="home.wss?tab=3" style="float:right;font-size: 1.0em;" id="backToHome"  >Back to home page</a>
<a href="logout.wss" style="float:right;font-size: 1.0em;" id="logout"  >Logout</a>  
<p style="clear:both;">&nbsp;</p>
</div>
<form id="uomDataFrm" action="addNewUOMData.wss" method="post">
<div class="ui-widget ui-widget-content ui-corner-all" style="padding:10px;">
<div style="padding-left: 10px;">
	<div>
		<span style="display:inline-block;width:40%"> 
			<label style="display:inline-block;width:30%;" for="pmpProjectId">PMP Project Id</label> 
			<input type="text" name="pmpProjectId" id="pmpProjectId" /> 
		 </span> 
		<span style="display:inline-block;width:40%"> 
			<label style="display:inline-block;width:30%;" for="accountMgr">Account Manager</label> 
			<pfd:mgrrefdata name="accountMgr" mgrtype="am" datalist="${mgrList}" id="accountMgr" defopt="true"></pfd:mgrrefdata>
		 </span> 
	
	</div>
	<div><br/></div>
	<div>
		<span style="display:inline-block;width:40%"> 
			<label style="display:inline-block;width:30%;" for="uomName">UOM Name</label> 
			<input type="text" name="uomName" id="uomName" /> 
		 </span> 
		<span style="display:inline-block;width:40%"> 
			<label style="display:inline-block;width:30%;" for="geo">Geography</label> 
			<pfd:refdata name="geo" datalist="${geoList}" id="geo"></pfd:refdata>
		 </span> 
	</div>
	<div><br/></div>
	<div>
		<span style="display:inline-block;width:40%"> 
			<label style="display:inline-block;width:30%;" for="managdType">Manage type</label> 
		 	<pfd:refdata name="managdType" datalist="${manageType}" id="managdType"></pfd:refdata>
		 </span> 
		<span style="display:inline-block;width:40%"> 
			<label style="display:inline-block;width:30%;" for="uomStartDate">UOM Start Date</label> 
			<input type="text" name="uomStartDate" id="uomStartDate" /> 
		 </span> 
	</div>
	<div><br/></div>
	<div>
		<span style="display:inline-block;width:40%"> 
			<label style="display:inline-block;width:30%;" for="uomMgr">UOM Manager</label> 
			<pfd:mgrrefdata name="uomMgr" mgrtype="uompm" datalist="${mgrList}" id="uomMgr" defopt="true"></pfd:mgrrefdata>
		 </span> 
		<span style="display:inline-block;width:40%"> 
			<label style="display:inline-block;width:30%;" for="progMgr">Program Manager</label> 
 			<pfd:mgrrefdata name="progMgr" mgrtype="tpm" datalist="${mgrList}" id="progMgr" defopt="true"></pfd:mgrrefdata>
		 </span> 
	</div>
	<div><br/></div>
	<div>
	</div>
	<div><br/></div>
	<div>
		<input type="button" id="addUOM" value="Add UOM"/>
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
	$("#uomMgr").selectmenu({width: 300}).selectmenu( "menuWidget" ).css("height","200px");
	$("#accountMgr").selectmenu({width: 300}).selectmenu( "menuWidget" ).css("height","200px");
	$("#progMgr").selectmenu({width: 300}).selectmenu( "menuWidget" ).css("height","200px");
	$("#geo").selectmenu({width: 300});
	$("#managdType").selectmenu({width: 300});
	$("#uomStartDate").datepicker();
	
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
	
	$("#addUOM").button().click(function(event){
		$.post("addNewUOMData.wss", $('#uomDataFrm').serialize()).done(function(data){
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