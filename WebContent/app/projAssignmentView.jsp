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
<link href="css/dataTables.tableTools.css" rel="stylesheet">
<script src="js/jquery.js"></script>
<script src="js/jqueryui/jquery-ui.js"></script>
<script src="js/jquery.dataTables.min.js"></script>
<script src="js/dataTables.tableTools.js"></script>

<title>Portfolio database</title>
</head>
<body>
<div id="appToolBar" >
<span style="font-weight: bold;font-size: 2em;" >Project Assignments</span>
<a href="home.wss?tab=2" style="float:right;font-size: 1.0em;" id="backToHome"  >Back to home</a>
<a href="logout.wss" style="float:right;font-size: 1.0em;" id="logout"  >Logout</a>  
<p style="clear:both;">&nbsp;</p>
</div>
<div class="ui-widget ui-widget-content ui-corner-all" style="padding:10px;">
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
<label style="display:inline-block;width:12%;" for="projectId">&nbsp;</label>
<span><input type="button" id="viewBtn" value="View"/> </span>
</div>
<div id="dataDisplay">
<p><br/></p>
<table id="reportDataTbl" class="display" cellspacing="0" width="100%">
    <thead>
        <tr>
			<th>Employee ID</th>
			<th>Employee Name</th>
			<th>PMP Project ID</th>
			<th>UOM Name</th>
			<th>Project Name</th>
			<th>Geography</th>
			<th>Project Type</th>
			<th>Managed Type</th>
			<th>Primary Project Technology</th>
			<th>Domain Area (if any)</th>
			<th>Start Date of the Project</th>
			<th>UOM Manager</th>
			<th>Project Manager</th>
			<th>People Manager</th>
			<th>Program Manager</th>
			<th>Account Manager</th>
			<th>Director</th>
			<th>DPE</th>
			<th>BAM</th>
			<th>PMP ID</th>
			<th>Employee Notes Id</th>
			<th>Employee Internet Id</th>
			<th>Employee Type</th>
			<th>ELTP Flag</th>
			<th>Gender</th>
			<th>Role</th>
			<th>Primary Skill</th>
			<th>Project Start Date for Individual</th>
			<th>Project End Date (Actual / Projected) for Individual</th>
			<th>Release Flag</th>
			<th>Release Reason</th>
			<th>Location</th>
			<th>Location - Building</th>
			<th>Band</th>
			<th>Sub Band</th>
			<th>Core resource</th>
			<th>Remarks</th>
			<th>Project Id( Internal)</th>
			<th>UOM Id( Internal)</th>
        </tr>
    </thead>
</table>
</div>
</div>
</body>
<script type="text/javascript">
$.fn.dataTable.TableTools.defaults.aButtons = [ "copy", "csv", "xls" ];
var dataTable;
$(document).ready(function(){
	
	
	<c:if test="${not empty param.preloadPrjid}">
		$("#projectId").selectmenu().val("${param.preloadPrjid}");;
		$("#projectId").selectmenu("refresh");
	</c:if>
	<c:if test="${empty param.preloadPrjid}">
			$("#projectId").selectmenu();
	</c:if>
	dataTable = $('#reportDataTbl').DataTable( {
		"dom": 'T<"clear">lfrtip',
		"tableTools": {
            "sSwfPath": "swf/copy_csv_xls_pdf.swf"
        },
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
            "url": "loadAllProjAssignments.wss",
            "data": function ( d ) {
                d.projectId = $("#projectId").val();
            }
		},
		"order": [[ 1, "asc" ]]
		,
		"columns": [
			{ 
            	"data": "empDetails.empId",
            	"render": function ( data, type, full, meta ) {
                    return "<a href=\"loadProjectDataPage.wss?requestType=view_projassign&objIdHexString="+full["objIdHexString"]+"\">"+data+"</a>";
                }
			},
			{ "data" :  "empDetails.name" },
			{ "data" :  "uomDetails.pmpProjectId" },
			{ "data" :  "uomDetails.uomName" },
			{ "data" :  "projDetails.projectName" },
			{ "data" :  "uomDetails.displayFields.geo" },
			{ "data" :  "projDetails.displayFileds.projType" },
			{ "data" :  "uomDetails.displayFields.managdType" },
			{ "data" :  "projDetails.displayFileds.projPrimTech" },
			{ "data" :  "projDetails.displayFileds.domain" },
			{ "data" :  "uomDetails.uomStartDate" },
			{ "data" :  "uomDetails.displayFields.uomMgr" },
			{ "data" :  "projDetails.displayFileds.projMgr" },
			{ "data" :  "empDetails.displayFields.pem" },
			{ "data" :  "uomDetails.displayFields.progMgr" },
			{ "data" :  "uomDetails.displayFields.accountMgr" },
			{ "data" :  "projDetails.displayFileds.director" },
			{ "data" :  "projDetails.displayFileds.dpe" },
			{ "data" :  "projDetails.displayFileds.bam" },
			{ "data" :  "pmpId" },
			{ "data" :  "empDetails.notesId" },
			{ "data" :  "empDetails.intranetId" },
			{ "data" :  "empDetails.displayFields.empType" },
			{ "data" :  "empDetails.eltpFg" },
			{ "data" :  "empDetails.gender" },
			{ "data" :  "displayFields.role" },
			{ "data" :  "empDetails.displayFields.primarySkill" },
			{ "data" :  "startDate" },
			{ "data" :  "endDate" },
			{ "data" :  "releasedFg" },
			{ "data" :  "displayFields.rollOffType" },
			{ "data" :  "empDetails.displayFields.location" },
			{ "data" :  "empDetails.displayFields.building" },
			{ "data" :  "empDetails.band" },
			{ "data" :  "empDetails.subBand" },
			{ "data" :  "isCore" },
			{ "data" :  "releaseReasonText" },
			{ "data" :  "projDetails.projectId" },
			{ "data" :  "projDetails.uomId" },

        ]
        });
    
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
	$("#viewBtn").button().click(function(event){
		dataTable.ajax.reload();
        
    } );
	
});

</script>
</html>