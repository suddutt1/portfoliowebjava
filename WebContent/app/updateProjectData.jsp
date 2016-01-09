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
<span style="font-weight: bold;font-size: 2em;" >Update  project data</span>
<a href="loadProjectDataPage.wss?requestType=viewall_proj" style="float:right;font-size: 1.0em;" id="backToHome"  >Back to project list</a>
<a href="logout.wss" style="float:right;font-size: 1.0em;" id="logout"  >Logout</a>  
<p style="clear:both;">&nbsp;</p>
</div>
<form id="projDataFrm" action="updateProjetData.wss" method="post">
<input type="hidden" name="objIdHexString" id="objIdHexString" value="${projDetails.objIdHexString}"/>
<div class="ui-widget ui-widget-content ui-corner-all" style="padding:10px;">
<div style="padding-left: 10px;">
	<div>
		<span style="display:inline-block;width:40%"> 
			<label style="display:inline-block;width:30%;" for="uomId">UOM</label> 
			<select name="uomId" id="uomId" >
					<option value="">Select UOM</option>
				<c:forEach items="${uomList}" var="uomDetails">
					<c:if test="${uomDetails.uomId eq projDetails.uomId }">
						<option value="${uomDetails.uomId}" selected>(${uomDetails.uomName})-(${uomDetails.pmpProjectId})</option>
					</c:if>
					<c:if test="${uomDetails.uomId ne projDetails.uomId }">
						<option value="${uomDetails.uomId}" >(${uomDetails.uomName})-(${uomDetails.pmpProjectId})</option>
					</c:if>
				</c:forEach>
			</select> 
		 </span> 
	</div>
	<div>
	<span style="display:inline-block;width:40%"> 
		<label style="display:inline-block;width:30%;" for="projectName">Project Name</label> 
		<input type="text" name="projectName" id="projectName" value="${projDetails.projectName}"/> 
	 </span> 
	<span style="display:inline-block;width:40%"> 
		<label style="display:inline-block;width:30%;" for="projMgr">Project Manager</label>
		<pfd:mgrrefdata name="projMgr" mgrtype="pm" datalist="${mgrList}" id="projMgr" selected="${projDetails.projMgr}"></pfd:mgrrefdata>
	 </span> 
	</div>
	<div><br/></div>
	<div>
	<span style="display:inline-block;width:40%"> 
		<label style="display:inline-block;width:30%;" for="dpe">DPE</label> 
		<pfd:mgrrefdata name="dpe" mgrtype="dpe" datalist="${mgrList}" id="dpe" selected="${projDetails.dpe}"></pfd:mgrrefdata>
 	 </span> 
	<span style="display:inline-block;width:40%"> 
		<label style="display:inline-block;width:30%;" for="blueCommunity">Blue Community</label> 
		<input type="text" name="blueCommunity" id="blueCommunity" value = "${projDetails.blueCommunity}"/> 
	 </span> 
	</div>
	<div><br/></div>
	<div>
	<span style="display:inline-block;width:40%"> 
		<label style="display:inline-block;width:30%;" for="bam">BAM</label> 
		<pfd:mgrrefdata name="bam" mgrtype="bam" datalist="${mgrList}" id="bam" selected="${projDetails.bam}"></pfd:mgrrefdata>
	 </span> 
	<span style="display:inline-block;width:40%"> 
		<label style="display:inline-block;width:30%;" for="director">Director</label> 
		<pfd:mgrrefdata name="director" mgrtype="dir" datalist="${mgrList}" id="director" selected="${projDetails.director}"></pfd:mgrrefdata>
	</span> 
	</div>
	<div><br/></div>
	<div>
	<span style="display:inline-block;width:40%"> 
		<label style="display:inline-block;width:30%;" for="projType">Project Type</label> 
		<pfd:refdata name="projType" datalist="${projType}" id="projType" selected="${projDetails.projType}"></pfd:refdata>
	</span> 
	<span style="display:inline-block;width:40%"> 
		<label style="display:inline-block;width:30%;" for="projPrimTech">Project Primary Technology</label> 
		<pfd:refdata name="projPrimTech" datalist="${projTech}" id="projPrimTech" selected="${projDetails.projPrimTech}"></pfd:refdata>

	 </span> 
	</div>
	<div><br/></div>
	<div>
	<span style="display:inline-block;width:40%"> 
		<label style="display:inline-block;width:30%;" for="domain">Domain Area</label> 
		<pfd:refdata name="domain" datalist="${domainArea}" id="domain" selected="${projDetails.domain}"></pfd:refdata>
	 </span> 
	 <span style="display:inline-block;width:40%"> 
		<label style="display:inline-block;width:30%;">Project Id ( Internal)</label> 
		<label>${projDetails.projectId}</label>
	 </span>
	</div>
	<div><br/></div>
	<div>
		<input type="button" id="updateProject" value="Update Project"/>
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
	$("#bam").selectmenu({width: 300}).selectmenu( "menuWidget" ).css("height","200px");
	$("#dpe").selectmenu({width: 300}).selectmenu( "menuWidget" ).css("height","200px");
	$("#director").selectmenu({width: 300}).selectmenu( "menuWidget" ).css("height","200px");	
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
	
	$("#updateProject").button().click(function(event){
		$.post("updateProjetData.wss", $('#projDataFrm').serialize()).done(function(data){
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