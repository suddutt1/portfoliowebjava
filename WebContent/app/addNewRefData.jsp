<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<span style="font-weight: bold;font-size: 2em;" >Add new reference data</span>
<a href="home.wss" style="float:right;font-size: 1.0em;" id="backToHome"  >Back to home</a>
<a href="logout.wss" style="float:right;font-size: 1.0em;" id="logout"  >Logout</a>  
<p style="clear:both;">&nbsp;</p>
</div>
<form action="saveReferenceData.wss" method="post" id="refData">
	<div class="ui-widget ui-widget-content ui-corner-all" style="padding:10px;">
		<div>
		<label for="refDataType" style="display:inline-block;width:30%" >Select reference data type</label>
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
		</div>
		<div>
			<span style="display:inline-block;width:30%"><label for="code" >Refer data code</label></span><input type="text" id="code" name="code" />
		</div>
		<div>
			<span style="display:inline-block;width:30%"><label for="displayVal" >Display value</label></span><input type="text" id="displayVal" name="displayVal" />
		</div>
		<div>
		<input type="button" id="addBtn" value="Add reference data"/>
		</div>
	</div>
</form>
<!-- ui-dialog -->
<div id="dialog" title="Save result">
	<p id="message"></p>
</div>
</body>
<script type="text/javascript">

$(document).ready(function(){
	
	$("#refDataType").selectmenu();
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
	
	
	$("#addBtn").button().click(function(event){
		$.post("saveReferenceData.wss", $('#refData').serialize()).done(function(data){
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