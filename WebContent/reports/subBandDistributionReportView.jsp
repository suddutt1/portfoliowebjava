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
<span style="font-weight: bold;font-size: 2em;" >Sub band distribution report</span>
<a href="home.wss?tab=1" style="float:right;font-size: 1.0em;" id="backToHome"  >Back to home</a> 
<a href="logout.wss" style="float:right;font-size: 1.0em;" id="logout"  >Logout</a> 
<p style="clear:both;">&nbsp;</p>
</div>
<div class="ui-widget ui-widget-content ui-corner-all" style="padding:10px;">
<div id="dataDisplay">
<p><br/></p>
<table id="reportDataTbl" class="display" cellspacing="0" width="100%">
    <thead>
        <tr>
			<th>UOM Name</th>
			<th>6G</th>
			<th>6A</th>
			<th>6B</th>
			<th>6V</th>
			<th>7A</th>
			<th>7B</th>
			<th>8</th>
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
        "iDisplayLength": 15,
        "scrollX": true,
        "scrollY": 400,
        "infoCallback": function( settings, start, end, max, total, pre ) {
    			return start+"&nbsp;to&nbsp;" + end + "&nbsp;of&nbsp;"+ total;
  		},
        "ajax": {
            "url": "retriveReportData.wss",
            "data": function ( d ) {
                d.reportType = "SUBBAND_REPORT";
            }
		},
		
		"columns": [
			{ "data" : "uomName" },
			{ "data" : "rowLine.6G" },
			{ "data" : "rowLine.6A" },
			{ "data" : "rowLine.6B" },
			{ "data" : "rowLine.6V" },
			{ "data" : "rowLine.7A" },
			{ "data" : "rowLine.7B" },
			{ "data" : "rowLine.8" },

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
	
});

</script>
</html>