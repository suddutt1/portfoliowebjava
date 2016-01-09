<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ attribute name="datalist" required="true" type="java.util.List"%>
<%@ attribute name="mgrtype" required="true" %>
<%@ attribute name="name" required="true" %>
<%@ attribute name="id" required="true" %>
<%@ attribute name="selected" required="false" %>
<%@ attribute name="defopt" required="false" %>
<select name="${name}" id="${id}" >
<c:if test="${defopt !=null && selected eq null}">
	<option value="" selected>Select</option>
</c:if>
<c:if test="${datalist !=null }">
	<c:forEach items="${datalist}" var="mgrData">
		<c:if test="${mgrData.mgrType eq mgrtype }">
			<c:if test="${mgrData.empId eq selected}">
				<option value="${mgrData.empId}" selected >${mgrData.empName}</option>
			</c:if>
			<c:if test="${mgrData.empId ne selected}">
				<option value="${mgrData.empId}" >${mgrData.empName}</option>
			</c:if>
		</c:if>	
	</c:forEach>
</c:if>
</select>