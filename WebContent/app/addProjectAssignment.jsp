<%@page import="com.google.gson.GsonBuilder"%>
<%@page import="com.google.gson.Gson"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib tagdir="/WEB-INF/tags/pfd"  prefix="pfd" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    
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
<span style="font-weight: bold;font-size: 2em;" >Add project assignment </span>
<a href="home.wss?tab=2" style="float:right;font-size: 1.0em;" id="backToHome"  >Back to home page</a>
<a href="logout.wss" style="float:right;font-size: 1.0em;" id="logout"  >Logout</a>  
<p style="clear:both;">&nbsp;</p>
</div>
<form id="projDataFrm" action="addProjAssignment.wss" method="post">
<input type="hidden" name="objIdHexString" id="objIdHexString" value="${projDetails.objIdHexString}"/>
<div class="ui-widget ui-widget-content ui-corner-all" style="padding:10px;">
	<div style="padding-left: 10px;">
	<div>
	<span style="display:inline-block;width:40%"> 
		<label style="display:inline-block;width:30%;" for="empid">Employee</label> 
		<select name="empid" id="empid">
			<option value="">Select..</option>
			<c:forEach items="${empList}" var="empDetails">
				<option value="${empDetails.empId}" >${empDetails.name}-(${empDetails.empId})</option>	
			</c:forEach>
		</select>
	 </span> 
	</div>
	<div><br/></div>
	<div>
		<span style="display:inline-block;width:40%"> 
			<label style="display:inline-block;width:30%;" for ="projectId">Project</label>
		<select name="projectId" id="projectId">
			<option value="">Select..</option>
			<c:forEach items="${projList}" var="projDetails">
				<option value="${projDetails.projectId}" >${projDetails.projectName}</option>	
			</c:forEach>
		</select>
	 	</span> 
		<span style="display:inline-block;width:40%"> 
		<label style="display:inline-block;width:30%;" for="startDate">Assignment Start Date</label> 
		<input type="text" name="startDate" id="startDate"  />
	 </span> 
	</div>
	<div><br/></div>
	<div>
		<span style="display:inline-block;width:40%"> 
			<label style="display:inline-block;width:30%;" for="endDate">Assignment End Date</label> 
			<input type="text" name="endDate" id="endDate"  /> 
		 </span> 
		 <span style="display:inline-block;width:40%"> 
			<label style="display:inline-block;width:30%;" for="role">Role</label> 
			<pfd:refdata name="role" datalist="${roleList}" id="role" defopt="true" ></pfd:refdata>
		 </span> 
	</div>
	<div><br/></div>
	<div>
		<span style="display:inline-block;width:40%"> 
		<label style="display:inline-block;width:30%;" >Is Core Resource</label> 
		<span id="coreRadioSet">
			<input type="radio" id="isCoreY" name="isCore" value="Y"  ><label for="isCoreY">Yes</label>
			<input type="radio" id="isCoreN" name="isCore" value="N" checked="checked" ><label for="isCoreN">No</label>
		</span> 
	 	</span> 
		<span style="display:inline-block;width:40%"> 
			<label style="display:inline-block;width:30%;" for="pmpId">PMP Id</label> 
			<input type="text" name="pmpId" id="pmpId"  /> 
		 </span> 
	</div>
	<div><br/></div>
	<div>
	<span style="display:inline-block;width:40%"> 
		<label style="display:inline-block;width:30%;" for="releasedFg">Released flag</label> 
		<span id="releaseRadioSet">
			<input type="radio" id="releasedFgY" name="releasedFg" value="Y"  ><label for="releasedFgY">Yes</label>
			<input type="radio" id="releasedFgN" name="releasedFg" value="N" checked="checked" ><label for="releasedFgN">No</label>
		</span>
	 </span> 
	</div>
	<div><br/></div>
	<div>
	<div><br/></div>
	<div>
		<input type="button" id="addAssignment" value="Add assignment"/>
	</div>
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
	
	$("#rollOffType").selectmenu({width: 300}).selectmenu( "menuWidget" ).css("height","200px");	
	$("#role").selectmenu({width: 300}).selectmenu( "menuWidget" ).css("height","200px");
	$("#empid").selectmenu({width: 300}).selectmenu( "menuWidget" ).css("height","300px");
	$("#projectId").selectmenu({width: 300}).selectmenu( "menuWidget" ).css("height","300px");
	
	$("#startDate").datepicker();
	$("#endDate").datepicker();
	$("#releaseRadioSet" ).buttonset();
	$("#coreRadioSet").buttonset();
	
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
	
	$("#addAssignment").button().click(function(event){
		$.post("addNewPojectAssignment.wss", $('#projDataFrm').serialize()).done(function(data){
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