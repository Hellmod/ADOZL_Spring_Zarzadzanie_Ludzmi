<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s"  uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><s:message code="hourEdit.pageName"/></title>
</head>
<body>
<%@include file="/WEB-INF/incl/menu.app" %>
<h2><s:message code="hourEdit.pageName"/></h2>

<p align="center">
    <c:out value="${message }" />
</p>
<sf:form id="hourForm" action="${pageContext.request.contextPath}/hour/updatehour/${hour.id_hours}" modelAttribute="hour" enctype="multipart/form-data" method="POST">
   <table width="500" border="0" cellpadding="4" cellspacing="1"
           align="center">

        <tr>
            <td width="130" align="right" ><s:message code="hour.hour_from"/></td>
            <td width="270" align="left"><sf:input path="hour_from" size="28"/></td>
        </tr>
        <tr>
            <td colspan="2" align="center"><font color="red"><sf:errors path="hour_from"/></font></td>
        </tr>

        <tr>
            <td width="130" align="right"><s:message code="hour.hour_to"/></td>
            <td width="270" align="left"><sf:input path="hour_to" size="28" /></td>
        </tr>

        <tr>
            <td colspan="2" align="center"><font color="red"><sf:errors path="hour_to"/></font></td>
        </tr>

        <tr>
            <td colspan="2" align="center" bgcolor="#fff">
                <input type="submit" value="<s:message code="button.save"/>" />
                <input type="button" value="<s:message code="button.cancel"/>" onclick="window.location.href='${pageContext.request.contextPath}/hour'"/>
            </td>
        </tr>

    </table>

</sf:form>
</body>
</html>