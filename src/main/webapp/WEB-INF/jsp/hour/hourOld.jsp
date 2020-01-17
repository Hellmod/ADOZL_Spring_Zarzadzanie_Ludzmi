<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s"  uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:message code="menu.hour"/></title>
</head>
<body>
<%@include file="/WEB-INF/incl/menu.app" %>
<h1><s:message code="menu.hour"/></h1>

<table width="1000" border="1" cellpadding="6" cellspacing="0" >
	<tr>
		<td width="40" align="center"><s:message code="hour.id_hours"/></td>
		<td width="200" align="center"><s:message code="hour.id_user"/></td>
		<td width="200" align="center"><s:message code="hour.hour_from"/></td>
		<td width="220" align="center"><s:message code="hour.hour_to"/></td>

	</tr>
	<c:forEach var="u" items="${hourList }">
			<tr>
				<td width="200" align="center"><c:out value="${u.id_hours }" /></td>
				<td width="200" align="center"><c:out value="${u.id_user }" /></td>
				<td width="200" align="center"><c:out value="${u.hour_from }" /></td>
				<td width="200" align="center"><c:out value="${u.hour_to }" /></td>
				<td width="200" align="center"><a href="hour/edit/${u.id_hours }"><s:message code="button.edit"/></a></td>
			</tr>
	</c:forEach>
	
</table>
</body>
</html>