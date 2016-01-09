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
<h1>Portfolio data maintenance tool login</h1>
<form id="empDataForm" action="login.wss" method="post">
<div class="ui-widget ui-widget-content ui-corner-all" style="padding:10px;">
<div style="padding-left: 10px;">

<c:if test="${login_error != null }">
	<div class="ui-state-error ui-corner-all">
		<p>
			<span class="ui-icon ui-icon-alert" style="float: left; margin-right: .3em;"></span>${login_error}
		</p>
	</div>
	<div><br/></div>
</c:if>
<div>
	<span style="display:inline-block;width:40%">
		<label style="display:inline-block;width:30%;" for="userid">User id</label>
		<input type="text" name="userid" id="userid"/>
	</span>
</div>
<div><br/></div>
<div>	
	<span style="display:inline-block;width:40%">
			<label style="display:inline-block;width:30%;" for="pwd">Password</label>
			<input type="password" name="pwd" id="pwd"/>
	</span>
</div>
<div><br/></div>
	<div>
	<input type="submit" id="submit" value="Login"/>
	</div>	
</div>
</div>
</form>
</body>
<script>

</script>
</html>