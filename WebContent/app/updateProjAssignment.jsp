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
<span style="font-weight: bold;font-size: 2em;" >Update  project assignment </span>
<a href="loadProjectDataPage.wss?requestType=viewall_assignment&preloadPrjid=${projDetails.projectId}" style="float:right;font-size: 1.0em;" id="backToHome"  >Back to project list</a>
<a href="logout.wss" style="float:right;font-size: 1.0em;" id="logout"  >Logout</a>  
<p style="clear:both;">&nbsp;</p>
</div>
<form id="projDataFrm" action="updateProjAssignment.wss" method="post">
<input type="hidden" name="objIdHexString" id="objIdHexString" value="${projDetails.objIdHexString}"/>
<div class="ui-widget ui-widget-content ui-corner-all" style="padding:10px;">
	<div style="padding-left: 10px;">
		<input type="hidden" name="objIdHexString" id="objIdHexString" value="${projDetails.objIdHexString}"/> 
		<input type="hidden" name="empid" id="empid" value="${projDetails.empid}" />
		<input type="hidden" name="projectId" id="projectId" value="${projDetails.projectId}" />
	<div>
	<span style="display:inline-block;width:40%"> 
		<label style="display:inline-block;width:30%;" for="empid">Employee</label> 
		<label>${empName}</label> 
	 </span> 
	
	</div>
	<div><br/></div>
	<div>
	<span style="display:inline-block;width:40%"> 
		<label style="display:inline-block;width:30%;" >Project</label>
		<label>${projName}</label>
	 </span> 
	
	<span style="display:inline-block;width:40%"> 
		<label style="display:inline-block;width:30%;" for="startDate">Assignment Start Date</label> 
		<input type="text" name="startDate" id="startDate" value="<fmt:formatDate value="${projDetails.startDate}" pattern="MM/dd/yyyy"/>" />
		 
	 </span> 
	
	</div>
	<div><br/></div>
	<div>
	<span style="display:inline-block;width:40%"> 
		<label style="display:inline-block;width:30%;" for="endDate">Assignment End Date</label> 
		<input type="text" name="endDate" id="endDate" value="<fmt:formatDate value="${projDetails.endDate}" pattern="MM/dd/yyyy"/>"  /> 
		
	 </span> 
	
	<span style="display:inline-block;width:40%"> 
		<label style="display:inline-block;width:30%;" for="role">Role</label> 
		<pfd:refdata name="role" datalist="${roleList}" id="role" selected="${projDetails.role}"></pfd:refdata>
		
	 </span> 
	
	</div>
	<div><br/></div>
	<div>
		
		<span style="display:inline-block;width:40%"> 
		<label style="display:inline-block;width:30%;" for="releasedFg">Is Core Resource</label> 
		<span id="coreRadioSet">
		<c:if test="${projDetails.isCore eq 'Y' }">
		<input type="radio" id="isCoreY" name="isCore" value="Y" checked="checked" ><label for="isCoreY">Yes</label>
		<input type="radio" id="isCoreN" name="isCore" value="N" ><label for="isCoreN">No</label>
		</c:if>
		<c:if test="${projDetails.isCore eq 'N' }">
		<input type="radio" id="isCoreY" name="isCore" value="Y"  ><label for="isCoreY">Yes</label>
		<input type="radio" id="isCoreN" name="isCore" value="N" checked="checked" ><label for="isCoreN">No</label>
		</c:if>
		</span> 
	 	</span> 
	
	<span style="display:inline-block;width:40%"> 
		<label style="display:inline-block;width:30%;" for="pmpId">PMP Id</label> 
		<input type="text" name="pmpId" id="pmpId" value="${projDetails.pmpId}" /> 
	 </span> 
	
	</div>
	<div><br/></div>
	<div>
	<span style="display:inline-block;width:40%"> 
		<label style="display:inline-block;width:30%;" for="rollOffType">Roll Off Type</label>
		<c:if test="${not empty  projDetails.rollOffType }">
			<pfd:refdata name="rollOffType" datalist="${releaseReasonList}" id="rollOffType"  selected= "${projDetails.rollOffType }" ></pfd:refdata>
		</c:if> 
		<c:if test="${empty projDetails.rollOffType   }">
			<pfd:refdata name="rollOffType" datalist="${releaseReasonList}" id="rollOffType"  defopt="true" ></pfd:refdata>
		</c:if> 
		
		
	 </span> 
	<span style="display:inline-block;width:40%"> 
		<label style="display:inline-block;width:30%;" for="releaseReasonText">Release Reason</label> 
		<input type="text" name="releaseReasonText" id="releaseReasonText" value="${projDetails.releaseReasonText}"  /> 
	 </span> 
	</div>
	<div><br/></div>
	<div>
	<span style="display:inline-block;width:40%"> 
		<label style="display:inline-block;width:30%;" for="releasedFg">Released flag</label> 
		<span id="releaseRadioSet">
		<c:if test="${projDetails.releasedFg eq 'Y' }">
		<input type="radio" id="releaseFgY" name="releasedFg" value="Y" checked="checked" ><label for="releaseFgY">Yes</label>
		<input type="radio" id="releaseFgN" name="releasedFg" value="N" ><label for="releaseFgN">No</label>
		</c:if>
		<c:if test="${projDetails.releasedFg eq 'N' }">
		<input type="radio" id="releaseFgY" name="releasedFg" value="Y"  ><label for="releaseFgY">Yes</label>
		<input type="radio" id="releaseFgN" name="releasedFg" value="N" checked="checked" ><label for="releaseFgN">No</label>
		</c:if>
	</span>
	
	 </span> 
	
	</div>
	<div><br/></div>
	<div>
	<div><br/></div>

	
	<div>
		<input type="button" id="upateAssignment" value="Update assignment"/>
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
	
	$("#upateAssignment").button().click(function(event){
		$.post("updateProjAssignment.wss", $('#projDataFrm').serialize()).done(function(data){
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