
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<header class="main-header">

	<!-- Logo -->
	<a href="home.html" class="logo"> <!-- mini logo for sidebar mini 50x50 pixels -->
		<span class="logo-mini"><b>J</b>T</span> <!-- logo for regular state and mobile devices -->
		<span class="logo-lg"><b>USCMS-JT</b></span>
	</a>

	<!-- Header Navbar -->
	<nav class="navbar navbar-static-top" role="navigation">
		<!-- Sidebar toggle button-->
		<a href="#" class="sidebar-toggle" data-toggle="offcanvas"
			role="button"> <span class="sr-only">Toggle navigation</span>
		</a>
		<!-- Navbar Right Menu -->
		<div class="navbar-custom-menu">
			<ul class="nav navbar-nav">
			
				<!-- Notifications Menu -->
				

				<!-- User Account Menu -->
				<li class="dropdown user user-menu">
					<!-- Menu Toggle Button --> <a href="#" class="dropdown-toggle"
					data-toggle="dropdown"> <!-- The user image in the navbar--> <img
						src="<c:url value="/resources/dist/img/vithu.jpg" />"
						class="user-image" alt="User Image"> <!-- hidden-xs hides the username on small devices so only the image appears. -->
						<span class="hidden-xs"><%=session.getAttribute("USER-NAME")%></span>
				</a>
					<ul class="dropdown-menu">
						<!-- The user image in the menu -->
						<li class="user-header"><img
							src="<c:url value="/resources/dist/img/vithu.jpg" />"
							class="img-circle" alt="User Image">

							<p>
								<%=session.getAttribute("NAME")%> 
								<small>Member since	<%=session.getAttribute("Register-Date")%> </small>
							</p></li>
						<!-- Menu Body -->
						
						<!-- Menu Footer-->
						<li class="user-footer">
							<div>
								<a href='/doLogout?token=26bcb6eb9a0dcddd'
									class="btn btn-default btn-flat"  style="width:100%">Sign out</a>
							</div>
						</li>
					</ul>
				</li>

				<!-- Control Sidebar Toggle Button -->
			
			</ul>
		</div>
	</nav>
</header>
