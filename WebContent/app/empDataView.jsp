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
<span style="font-weight: bold;font-size: 2em;" >View All Employee Data</span>
<a href="home.wss?tab=2" style="float:right;font-size: 1.0em;" id="backToHome"  >Back to home</a>
<a href="logout.wss" style="float:right;font-size: 1.0em;" id="logout"  >Logout</a>  
<p style="clear:both;">&nbsp;</p>
</div>
<div class="ui-widget ui-widget-content ui-corner-all" style="padding:10px;">
<div style="padding-left: 10px;">
	<div>
		<span style="display:inline-block;width:40%"> 
			<label style="display:inline-block;width:30%;" for="projectId">Project</label> 
			<select name="projectId" id="projectId" >
					<option value="">Select project</option>
				<c:forEach items="${prjList}" var="projDetails">
					<option value="${projDetails.projectId}">${projDetails.projectName}</option>
				</c:forEach>
			</select>
		 </span>
	</div>
	<div>
	<br/>
	<input type="button" id="viewBtn" value="View"/>
	</div>
</div>
<div id="dataDisplay">
<p><br/></p>
<table id="empDataTable" class="display" cellspacing="0" width="100%">
    <thead>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Email</th>
            <th>Notes id</th>
            <th>Type</th>
            <th>ELTP</th>
            <th>Gender</th>
            <th>Skill</th>
            <th>Location</th>
            <th>Building</th>
            <th>PeM</th>
            <th>Band</th>
            <th>Sub-band</th>
            <th>Attrition type</th>
        </tr>
    </thead>
</table>
</div>

</div>

</body>
<script type="text/javascript">
var dataTable;
$(document).ready(function(){
	<c:if test="${not empty param.preloadPrjid}">
		$("#projectId").selectmenu().val("${param.preloadPrjid}");;
		$("#projectId").selectmenu("refresh");
	</c:if>
	<c:if test="${empty param.preloadPrjid}">
			$("#projectId").selectmenu();
	</c:if>

	dataTable = $('#empDataTable').DataTable( {
		"jQueryUI": true,
		"searching": false,
		"pagingType": "simple",
		"processing": true,
        "serverSide": false, 
        "lengthChange": false,
        "scrollX": true,
        "scrollY": 400,
        "infoCallback": function( settings, start, end, max, total, pre ) {
    			return start+"&nbsp;to&nbsp;" + end + "&nbsp;of&nbsp;"+ total;
  		},
        "ajax": {
            "url": "loadEmployeeList.wss",
            "data": function ( d ) {
                d.projectId = $("#projectId").val();
            }
		},
		"order": [[ 1, "asc" ]]
		,
		"columns": [
            { 
            	"data": "empId",
            	"render": function ( data, type, row ) {
                    return "<a href=\"loadUpdateEmpData.wss?empId="+data+"\">"+data+"</a>";
                }
            },
			{ "data": "name"},
			{ "data": "intranetId"},
			{ "data": "notesId"},
			{ "data": "displayFields.empType"},
			{ "data": "eltpFg"},
			{ "data": "gender"},
			{ "data": "displayFields.primarySkill"},
			{ "data": "displayFields.location"},
			{ "data": "displayFields.building"},
			{ "data": "displayFields.pem"},
			{ "data": "band"},
			{ "data": "subBand"},
			{ "data": "attrType"}
        ]
        });
      
	$("#viewBtn").button().click(function(event){
		dataTable.ajax.reload();
        
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