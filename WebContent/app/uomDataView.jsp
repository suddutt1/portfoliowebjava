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
<span style="font-weight: bold;font-size: 2em;" >View UOM details</span>
<a href="home.wss?tab=3" style="float:right;font-size: 1.0em;" id="backToHome"  >Back to home</a>
<a href="logout.wss" style="float:right;font-size: 1.0em;" id="logout"  >Logout</a>  
<p style="clear:both;">&nbsp;</p>
</div>
<div class="ui-widget ui-widget-content ui-corner-all" style="padding:10px;">
<div id="dataDisplay">
<p><br/></p>
<table id="uomDataTable" class="display" cellspacing="0" width="100%">
    <thead>
        <tr>
            <th>PMP Project Id</th>
			<th>Uom Name</th>
			<th>Geography</th>
			<th>Managed Type</th>
			<th>UOM StartDate</th>
			<th>UOM Manager</th>
			<th>Program Manager</th>
			<th>Account Manage</th>
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
	
	dataTable = $('#uomDataTable').DataTable( {
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
            "url": "loadAllUOMList.wss",
            "data": function ( d ) {
                //d.mgrType = $("#mgrType").val();
            }
		},
		"order": [[ 1, "asc" ]]
		,
		"columns": [
            { "data" : "pmpProjectId" },
			{ "data" : "uomName" },
			{ "data" : "displayFields.geo" },
			{ "data" : "displayFields.managdType" },
			{ "data" : "uomStartDate" },
			{ "data" : "displayFields.uomMgr" },
			{ "data" : "displayFields.progMgr" },
			{ "data" : "displayFields.accountMgr" },
			{ "data" : "uomId" },
        ]
        });
      
	$("#viewBtn").button().click(function(event){
		//dataTable.ajax.reload();
        
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