<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<span style="font-weight: bold;font-size: 2em;" >View All reference data</span>
<a href="home.wss" style="float:right;font-size: 1.0em;" id="backToHome"  >Back to home</a>
<a href="logout.wss" style="float:right;font-size: 1.0em;" id="logout"  >Logout</a>  
<p style="clear:both;">&nbsp;</p>
</div>
<div class="ui-widget ui-widget-content ui-corner-all" style="padding:10px;">
<div style="padding-left: 10px;">
	<label for="refDataType" >Select reference data type</label>
	<select id="refDataType" name="refDataType">
		<option value="sector">Sector</option>
		<option value="empType">Employee type</option>
		<option value="role">Employee role</option>
		<option value="skill">Employee skill</option>
		<option value="location">Location</option>
		<option value="locationBlding">Location-building</option>
		<option value="band">Band</option>
		<option value="subBand">Sub-band</option>
		<option value="projectType">Project type</option>
		<option value="manageType">Manage type</option>
		<option value="projectPrimTech">Primary project technology</option>
		<option value="domainArea">Domain area</option>
		<option value="geography">Geography</option>
		<option value="releaseReason">Release Reason</option>
		<option value="attrReason">Attrition Reason</option>
		<option value="bc">Blue Community</option>
	</select>
	<div>
	<input type="button" id="viewBtn" value="View"/>
	</div>
</div>
<div id="dataDisplay">
<p><br/></p>
<table id="refDataTable" class="display" cellspacing="0" width="100%">
    <thead>
        <tr>
            <th>Type</th>
            <th>Code</th>
            <th>Display value</th>
            
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
	$("#refDataType").selectmenu();
	dataTable = $('#refDataTable').DataTable( {
		"dom": 'T<"clear">lfrtip',
		"tableTools": {
            "sSwfPath": "swf/copy_csv_xls_pdf.swf"
        },
		"jQueryUI": true,
		"searching": false,
		"pagingType": "simple",
		"processing": true,
        "serverSide": true, 
        "lengthChange": false,
        "infoCallback": function( settings, start, end, max, total, pre ) {
    			return "&nbsp;";
  		},
        "ajax": {
            "url": "loadReferenceData.wss",
            "data": function ( d ) {
                d.refDataType = $("#refDataType").val();
            }
		}
		,
		"columns": [
            { "data": "type" },
            { "data": "code" },
            { "data": "displayValue" }
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