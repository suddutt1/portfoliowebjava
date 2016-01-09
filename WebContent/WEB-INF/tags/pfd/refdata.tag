<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ attribute name="datalist" required="true" type="java.util.List"%>
<%@ attribute name="name" required="true" %>
<%@ attribute name="id" required="true" %>
<%@ attribute name="selected" required="false" %>
<%@ attribute name="defopt" required="false" %>
<select name="${name}" id="${id}" >
<c:if test="${defopt !=null && selected eq null}">
	<option value="" selected>Select</option>
</c:if>
<c:if test="${datalist !=null }">
	<c:forEach items="${datalist}" var="refData">
			<c:if test="${refData.code eq selected}">
				<option value="${refData.code}" selected >${refData.displayValue}</option>
			</c:if>
			<c:if test="${refData.code ne selected}">
				<option value="${refData.code}" >${refData.displayValue}</option>
			</c:if>
	</c:forEach>
</c:if>
</select>