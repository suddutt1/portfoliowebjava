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
<span style="font-weight: bold;font-size: 2em;" >View All manager data</span>
<a href="home.wss" style="float:right;font-size: 1.0em;" id="backToHome"  >Back to home</a>
<a href="logout.wss" style="float:right;font-size: 1.0em;" id="logout"  >Logout</a>  
<p style="clear:both;">&nbsp;</p>
</div>
<div class="ui-widget ui-widget-content ui-corner-all" style="padding:10px;">
<div style="padding-left: 10px;">
	<label for="refDataType" >Select manager type</label>
	<select id="mgrType" name="mgrType">
		<option value="pem">People Manager</option>
		<option value="tpm">Tower/Portfolio Manager</option>
		<option value="pal">Practice Area Lead(PAL)</option>
		<option value="am">Account manager</option>
		<option value="bam">Business Area Manager(BAM)</option>
		<option value="spmpm">SPM/PM</option>
		<option value="uompm">UOM Manager</option>
		<option value="pm">Sub Project Manager</option>
		<option value="dpe">Delivery Project Executive(DPE)</option>
		<option value="dir">Director</option>
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
            <th>Type Code</th>
            <th>Employee Id</th>
            <th>Name</th>
            <th>Notes Id</th>
            <th>Email Id</th>
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
	$("#mgrType").selectmenu();
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
            "url": "loadManagerList.wss",
            "data": function ( d ) {
                d.mgrType = $("#mgrType").val();
            }
		}
		,
		"columns": [
            { "data": "mgrType" },
            { "data": "empId" },
            { "data": "empName" },
            { "data": "notesId" },
            { "data": "emailId"}
            
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