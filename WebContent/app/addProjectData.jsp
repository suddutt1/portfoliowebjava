<%@page import="com.google.gson.GsonBuilder"%>
<%@page import="com.google.gson.Gson"%>
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
<span style="font-weight: bold;font-size: 2em;" >Add New Project </span>
<a href="home.wss?tab=2" style="float:right;font-size: 1.0em;" id="backToHome"  >Back to home page</a>
<a href="logout.wss" style="float:right;font-size: 1.0em;" id="logout"  >Logout</a>  
<p style="clear:both;">&nbsp;</p>
</div>
<form id="projDataFrm" action="addNewProjData.wss" method="post">
<div class="ui-widget ui-widget-content ui-corner-all" style="padding:10px;">
<div style="padding-left: 10px;">
	<div>
		<span style="display:inline-block;width:40%"> 
			<label style="display:inline-block;width:30%;" for="uomId">UOM</label> 
			<select name="uomId" id="uomId" >
					<option value="">Select UOM</option>
				<c:forEach items="${uomList}" var="uomDetails">
					<option value="${uomDetails.uomId}">(${uomDetails.uomName})-(${uomDetails.pmpProjectId})</option>
				</c:forEach>
			</select> 
		 </span> 
	</div>
	<div>
	<span style="display:inline-block;width:40%"> 
		<label style="display:inline-block;width:30%;" for="projectName">Project Name</label> 
		<input type="text" name="projectName" id="projectName" /> 
	 </span> 
	<span style="display:inline-block;width:40%"> 
		<label style="display:inline-block;width:30%;" for="projMgr">Project Manager</label>
		<pfd:mgrrefdata name="projMgr" mgrtype="pm" datalist="${mgrList}" id="projMgr" defopt="true"></pfd:mgrrefdata>
	 </span> 
	</div>
	<div><br/></div>
	<div>
	<span style="display:inline-block;width:40%"> 
		<label style="display:inline-block;width:30%;" for="slm">Second Line Manager</label> 
		<pfd:mgrrefdata name="slm" mgrtype="slm" datalist="${mgrList}" id="slm" selected="${projDetails.slm}"></pfd:mgrrefdata>
 	 </span> 
	<span style="display:inline-block;width:40%"> 
		<label style="display:inline-block;width:30%;" for="blueCommunity">Blue Community</label> 
		<input type="text" name="blueCommunity" id="blueCommunity" /> 
	 </span> 
	</div>
	<div><br/></div>
	<div>
	<span style="display:inline-block;width:40%"> 
		<label style="display:inline-block;width:30%;" for="flm">First Line Manager</label> 
		<pfd:mgrrefdata name="flm" mgrtype="flm" datalist="${mgrList}" id="flm" selected="${projDetails.flm}"></pfd:mgrrefdata>
	 </span> 
	<span style="display:inline-block;width:40%"> 
		<label style="display:inline-block;width:30%;" for="l2ex">L2 Executive</label> 
		<pfd:mgrrefdata name="l2ex" mgrtype="l2" datalist="${mgrList}" id="l2ex" selected="${projDetails.l2ex}"></pfd:mgrrefdata>
	</span> 
	</div>
	<div><br/></div>
		<div>
		<span style="display:inline-block;width:40%"> 
		<label style="display:inline-block;width:30%;" for="l2ex">L1 Executive</label> 
			<pfd:mgrrefdata name="l1ex" mgrtype="l1" datalist="${mgrList}" id="l1ex" selected="${projDetails.l1ex}"></pfd:mgrrefdata>
		</span> 
	</div>
	<div><br/></div>
	<div>
	<span style="display:inline-block;width:40%"> 
		<label style="display:inline-block;width:30%;" for="projType">Project Type</label> 
		<pfd:refdata name="projType" datalist="${projType}" id="projType"></pfd:refdata>
	</span> 
	<span style="display:inline-block;width:40%"> 
		<label style="display:inline-block;width:30%;" for="projPrimTech">Project Primary Technology</label> 
		<pfd:refdata name="projPrimTech" datalist="${projTech}" id="projPrimTech"></pfd:refdata>

	 </span> 
	</div>
	<div><br/></div>
	<div>
	<span style="display:inline-block;width:40%"> 
		<label style="display:inline-block;width:30%;" for="domain">Domain Area</label> 
		<pfd:refdata name="domain" datalist="${domainArea}" id="domain"></pfd:refdata>
	 </span> 
	</div>
	<div><br/></div>
	<div>
		<input type="button" id="addProject" value="Add Project"/>
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
	
	$("#uomId").selectmenu(
		{
			width: 300,
			select: function( event, ui ) {
				//Will change this if required
				//console.log($("#uomId").val());
			}
		});
	$("#projMgr").selectmenu({width: 300}).selectmenu( "menuWidget" ).css("height","200px");
	$("#l1ex").selectmenu({width: 300}).selectmenu( "menuWidget" ).css("height","200px");
	$("#l2ex").selectmenu({width: 300}).selectmenu( "menuWidget" ).css("height","200px");
	$("#flm").selectmenu({width: 300}).selectmenu( "menuWidget" ).css("height","200px");	
	$("#slm").selectmenu({width: 300}).selectmenu( "menuWidget" ).css("height","200px");
	$("#projType").selectmenu({width: 300});
	$("#projPrimTech").selectmenu({width: 300});
	$("#domain").selectmenu({width: 300}).selectmenu( "menuWidget" ).css("height","200px");	
	
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
	
	$("#addProject").button().click(function(event){
		$.post("addNewProjData.wss", $('#projDataFrm').serialize()).done(function(data){
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