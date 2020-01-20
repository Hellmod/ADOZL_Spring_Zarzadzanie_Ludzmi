<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><s:message code="menu.users"/></title>

	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<link rel="stylesheet" href="/resources/css/bootstrap.min.css">
	<link rel="stylesheet" href="/resources/css/main.css">
	<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,700&amp;subset=latin-ext" rel="stylesheet">

	<!--[if lt IE 9]>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
	<![endif]-->
</head>
<body>
<header>
	<%@include file="/WEB-INF/incl/menuNew.app" %>
</header>
<main>
	<c:set var="licznik" value="${recordStartCounter }"/>
	<section class="adozl">

		<div class="container">

			<header>
				<h1><s:message code="menu.users" /></h1>
				<p><c:out value="${message }" /></p>
			</header>

			<div class="row">

				<div class="col-sm-10 offset-sm-1">

					<table class="table table-striped table-dark">
						<thead>
						<tr>
							<td width="40" align="center"></td>
							<td width="40" align="center"><b><s:message code="admin.user.id"/></b></td>
							<td width="200" align="center"><b><s:message code="register.name"/></b></td>
							<td width="200" align="center"><b><s:message code="register.lastName"/></b></td>
							<td width="220" align="center"><b><s:message code="register.email"/></b></td>
							<td width="100" align="center"><b><s:message code="profil.czyAktywny"/></b></td>
							<td width="100" align="center"><b><s:message code="profil.czyZwolniony"/></b></td>
							<td width="200" align="center"><b><s:message code="profil.rola"/></b></td>
						</tr>
						</thead>
						<tbody>
							<c:forEach var="u" items="${userList }">
								<c:set var="licznik" value="${licznik+1}"/>
								<tr onmouseover="changeTrBg(this)" onmouseout="defaultTrBg(this)">
									<td align="right"><c:out value="${licznik }"/></td>
									<td align="right"><c:out value="${u.id }" /></td>
									<td align="left"><c:out value="${u.name }" /></td>
									<td align="left"><c:out value="${u.lastName }" /></td>
									<td align="center"><c:out value="${u.email }" /></td>
									<td align="center">
										<c:choose>
											<c:when test="${u.active == 1 }">
												<font color="green"><s:message code="word.tak"/></font>
											</c:when>
											<c:otherwise>
												<font color="red"><s:message code="word.nie"/></font>
											</c:otherwise>
										</c:choose>
									</td>
									<td align="center">
										<c:choose>
											<c:when test="${u.is_fired == false }">
												<font color="green"><s:message code="word.zatrudniony"/></font>
											</c:when>
											<c:otherwise>
												<font color="red"><s:message code="word.zwolniony"/></font>
											</c:otherwise>
										</c:choose>
									</td>
									<td align="center">
										<c:choose>
											<c:when test="${u.nrRoli == 1 }">
												<span color="text-success"><s:message code="word.admin"/></span>
											</c:when>
											<c:when test="${u.nrRoli == 3 }">
												<span class="text-primary"><s:message code="word.controller"/></span>
											</c:when>
											<c:otherwise>
												<span class="text-info"><s:message code="word.user"/></span>
											</c:otherwise>
										</c:choose>
									</td>
									<td ><input type="button" value="<s:message code="button.edit"/>"
										onclick="window.location.href='${pageContext.request.contextPath}edit/${u.id }'"/></td>
								</tr>
							</c:forEach>
						</tbody>
						<tbody>
						<tr>
							<td colspan="3" width="300" align="left">
								<s:message code="info.page"/> ${currentPage} <s:message code="info.from"/> ${totalPages}
							</td>
							<td colspan="5" align="right">
				
								<c:if test="${currentPage > 1}">
									<input type="button"
										   onclick="window.location.href='${pageContext.request.contextPath}/admin/users/${currentPage - 1}'"
										   value="<s:message code="link.poprzedni"/>"/>&nbsp;&nbsp;
								</c:if>
				
								<c:if test="${currentPage < totalPages}">
									<input type="button"
										   onclick="window.location.href='${pageContext.request.contextPath}/admin/users/${currentPage + 1}'"
										   value="<s:message code="link.nastepny"/>"/>
								</c:if>
				
							</td>
						</tr>
						</tbody>
					</table>
				</div>

			</div>

		</div>
	</section>

</main>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>

<script src="/resources/js/bootstrap.min.js"></script>

<script>
	document.getElementById("users").classList.add("active");
</script>
</body>
</html>