<nav class="navbar navbar-dark bg-table navbar-expand-lg">

			<a class="navbar-brand" href="/"><img src="/resources/images/logo.png" width="30" height="30" class="d-inline-block mr-1 align-bottom" alt=""> <s:message code="menu.adozl"/></a>

			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#mainmenu" aria-controls="mainmenu" aria-expanded="false" aria-label="Przełącznik nawigacji">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="mainmenu">

				<ul class="navbar-nav mr-auto">

					<li class="nav-item active">
						<a class="nav-link" href="/"> <s:message code="menu.mainPage"/> </a>
					</li>
<sec:authorize access="isAuthenticated()">
					<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown" role="button" aria-expanded="false" id="submenu" aria-haspopup="true"> <s:message code="menu.hour"/> </a>

						<div class="dropdown-menu" aria-labelledby="submenu">

							<a class="dropdown-item" href="/hour"> <s:message code="menu.hour"/> </a>
							<a class="dropdown-item" href="/hour/addhour"> <s:message code="menu.hourAdd"/> </a>

							<div class="dropdown-divider"></div>

							<a class="dropdown-item" href="#"> Sylwetki zawodników </a>
							<a class="dropdown-item" href="#"> Skocznie narciarskie </a>

						</div>

					</li>

					<li class="nav-item">
						<a class="nav-link" href="/profil"> <s:message code="menu.profil"/> </a>
					</li>

					<li class="nav-item">
						<a class="nav-link" href="/hour"> <s:message code="menu.hour"/> </a>
					</li>

					<li class="nav-item">
						<a class="nav-link disabled" href="/hour/addhour"> <s:message code="menu.hourAdd"/> </a>
					</li>
</sec:authorize>
<sec:authorize access="hasRole('ROLE_ADMIN')">
					<li class="nav-item">
						<a class="nav-link" href="/admin/users/1"> <s:message code="menu.users"/></a>
					</li>
</sec:authorize>

				</ul>
<sec:authorize access="isAuthenticated()">
				<a class="nav-link" href="/logout"> Wyloguj </a>
</sec:authorize>
<sec:authorize access="hasRole('ANONYMOUS')">
				<a class="nav-link" href="/login"> <s:message code="menu.login"/> </a>
				<a class="nav-link" href="/register"> <s:message code="menu.register"/> </a>
</sec:authorize>
			</div>

		</nav>