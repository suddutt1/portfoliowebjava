<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>         
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="js/jqueryui/jquery-ui.css" rel="stylesheet">
<script src="js/jquery.js"></script>
<script src="js/jqueryui/jquery-ui.js"></script>

<title>Portfolio database</title>

</head>
<body>
<div id="appToolBar" >
<span style="font-weight: bold;font-size: 2em;" >Portfolio data maintenance tool</span>
 	<a href="logout.wss" style="float:right;font-size: 1.0em;" id="logout"  >Logout</a> 
	<p style="clear:both;">&nbsp;</p>
</div>
<div id="main_tabs">
	<ul>
		<li><a href="#home">Home</a></li>
		<li><a href="#reports">Reports</a></li>
		<li><a href="#datamaintenance">Data maintenance</a></li>
		<c:if test="${user_role eq 'admin' }">	
		<li><a href="#admin">Administration</a></li>
		</c:if>
	</ul>
	<div id="home" style="height:250px;">
		Portfolio data maintenance tool. Please select necessary category of actions
	</div>
	<div id="reports" style="height:250px;">
		Select links from the list below to view reports ( PM ROLE)
		<ul>
			<li><a href="loadFullReportView.wss" >Full Portfolio Data</a></li>
			<li><a href="loadReportView.wss?requestJsp=bandMixReportView.jsp">Band Mix report</a></li>
			<li><a href="loadReportView.wss?requestJsp=genderReportView.jsp" >Gender report</a></li>
			<li><a href="loadReportView.wss?requestJsp=subBandDistributionReportView.jsp" >Sub band distribution report</a></li>
			<li><a href="loadReportView.wss?requestJsp=locationReportView.jsp">Location report </a></li>
			<li><a href="loadReportView.wss?requestJsp=tenuredResourceReportView.jsp" >Tenured resource report( Non critical)</a></li>
			<li><a href="loadReportView.wss?requestJsp=tenuredRscCriticalReportView.jsp">Tenured resource report( Critical)</a></li>
			<li><a href="loadReportView.wss?requestJsp=projectRoleReportView.jsp">Employee role in project report</a></li>
			<li><a href="loadReportView.wss?requestJsp=skillBasedReportView.jsp">Employee skill report </a></li>
			<li><a href="loadReportView.wss?requestJsp=empTypeReportView.jsp">Employee type report</a></li>
			
		</ul>	
	</div>
	<div id="datamaintenance" style="height:250px;">
		<div>Select links to manage portfolio data ( PM ROLE)</div>
		<ul>
			<li><a href="loadAddNewEmpData.wss" >Add a new Employee</a></li>
			<li><a href="loadEmployeeView.wss" >View/Modify an  Employee data</a></li>
			<li><a href="loadProjectDataPage.wss?requestType=add_projassignment">Add a new project assignment</a>
			<li><a href="loadProjectDataPage.wss?requestType=viewall_assignment">View/Modify project assignment</a>
			<li><a href="loadProjectDataPage.wss?requestType=add_proj">Add a new project</a></li>
			<li><a href="loadProjectDataPage.wss?requestType=viewall_proj">View/Modify projects</a></li>
		</ul>
	</div>
	<c:if test="${user_role eq 'admin' }">
	<div id="admin" style="height:250px;">
		<div>Perform administration task using the links given below ( ADMIN ROLE)</div>
		<ul>
			<li><a href="loadAddNewRefData.wss">Add new reference data</a></li>
			<li><a href="loadRefDataForView.wss">View/Download Reference data</a></li>
			<li><a href="loadAddNewManager.wss" >Add a Manager</a></li>
			<li><a href="loadManagerView.wss" >View/Modify Manager data</a></li>
			<li><a href="loadUOMDataPage.wss?requestType=add_uom" >Add a new UOM</a></li>
			<li><a href="loadUOMDataPage.wss?requestType=viewall_uom" >View/Modify UOM details</a></li>
			<li><a href="loadUserView.wss?requestJsp=addUser.jsp">Add a new application user</a></li>
		</ul>
	</div>
	</c:if>
</div>
</body>
<script>
$( "#main_tabs").tabs({"active": <%= request.getParameter("tab") %>});
$("#logout").button({
					icons: {
						primary: 'ui-icon-power'
					}
});
</script>
</html>