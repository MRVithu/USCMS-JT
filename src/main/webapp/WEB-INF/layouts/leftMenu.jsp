
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<aside class="main-sidebar" id="tofix">

	<!-- sidebar: style can be found in sidebar.less -->
	<section class="sidebar fixed">

		<!-- Sidebar user panel (optional) -->
		<div class="user-panel">
			<div class="pull-left image">
				<img src="<c:url value="/resources/dist/img/vithu.jpg" />"
					class="img-circle" alt="User Image">
			</div>
			<div class="pull-left info">
				<p>Alexander Pierce</p>
				<!-- Status -->
				<a href="#"><i class="fa fa-circle text-success"></i> Online</a>
			</div>
		</div>

		<!-- search form (Optional) -->
		<form action="#" method="get" class="sidebar-form">
			<div class="input-group">
				<input type="text" name="q" class="form-control"
					placeholder="Search..."> <span class="input-group-btn">
					<button type="submit" name="search" id="search-btn"
						class="btn btn-flat">
						<i class="fa fa-search"></i>
					</button>
				</span>
			</div>
		</form>
		<!-- /.search form -->

		<!-- Sidebar Menu -->
		<ul class="sidebar-menu">
			<li class="header ${current == 'home' ? 'active' : '' }"><a
				href="/home.html"> <i class="fa fa-home"></i><span>Home</span></a></li>

			<li
				class="treeview ${current == 'employee' or current == 'customer' or current == 'supplier' ? 'active' : '' }">
				<a href="#"><i class="fa fa-user"></i> <span>Users</span> <span
					class="pull-right-container"> <i
						class="fa fa-angle-left pull-right"></i>
				</span> </a>
				<ul class="treeview-menu">
					<li class="${current == 'employee' ? 'active' : '' }"><a
						href="/employee.html?token=<%=session.getAttribute("Token")%>"><i
							class="fa fa-circle-thin"></i><span> Employees</span></a></li>

					<li class="${current == 'customer' ? 'active' : '' }"><a
						href="/customer.html?token=<%=session.getAttribute("Token")%>"><i
							class="fa fa-circle-thin"></i><span> Customers</span></a></li>

					<li class="${current == 'supplier' ? 'active' : '' }"><a
						href="/supplier.html?token=<%=session.getAttribute("Token")%>"><i
							class="fa fa-circle-thin"></i><span> Suppliers</span></a></li>
				</ul>
			</li>

			<li
				class="treeview ${current == 'brand' or current == 'product' or current== 'productType' or current== 'itemType'  ? 'active' : '' }">
				<a href="#"><i class="fa  fa-link"></i> <span>Product</span> <span
					class="pull-right-container"> <i
						class="fa fa-angle-left pull-right"></i>
				</span> </a>
				<ul class="treeview-menu">
					<li class="${current == 'product' ? 'active' : '' }"><a
						href="/product.html?token=<%=session.getAttribute("Token")%>"><i
							class="fa fa-circle-thin"></i><span> Products</span></a></li>

					<li class="${current == 'brand' ? 'active' : '' }"><a
						href="/brand.html?token=<%=session.getAttribute("Token")%>"><i
							class="fa fa-circle-thin"></i><span> Brands</span></a></li>

					<li class="${current == 'productType' ? 'active' : '' }"><a
						href="/productType.html?token=<%=session.getAttribute("Token")%>"><i
							class="fa fa-circle-thin"></i><span> Product Types</span></a></li>

					<li class="${current == 'itemType' ? 'active' : '' }"><a
						href="/itemType.html?token=<%=session.getAttribute("Token")%>"><i
							class="fa fa-circle-thin"></i><span> Item Types</span></a></li>
				</ul>
			</li>
			
			<li
				class="treeview ${current == 'sales' or current == 'salesAdd' or current== 'salesOrder'  ? 'active' : '' }">
				<a href="#"><i class="fa  fa-cart-arrow-down"></i> <span>Sales</span> <span
					class="pull-right-container"> <i
						class="fa fa-angle-left pull-right"></i>
				</span> </a>
				<ul class="treeview-menu">
					<li class="${current == 'sales' ? 'active' : '' }"><a
						href="/sales.html?token=<%=session.getAttribute("Token")%>"><i
							class="fa fa-circle-thin"></i><span> Sales</span></a></li>

					<li class="${current == 'brand' ? 'active' : '' }"><a
						href="/salesOrder.html?token=<%=session.getAttribute("Token")%>"><i
							class="fa fa-circle-thin"></i><span> Sales Order</span></a></li>

				</ul>
			</li>

			
			<li
				class="treeview ${current == 'purchaseOrder' or current == 'purchase'   ? 'active' : '' }">
				<a href="#"><i class="fa  fa-truck"></i> <span>Purchase</span> <span
					class="pull-right-container"> <i
						class="fa fa-angle-left pull-right"></i>
				</span> </a>
				<ul class="treeview-menu">
					<li class="${current == 'purchaseOrder' ? 'active' : '' }"><a
						href="/purchase.html?token=<%=session.getAttribute("Token")%>"><i
							class="fa fa-circle-thin"></i><span> Purchase</span></a></li>

					<li class="${current == 'purchase' ? 'active' : '' }"><a
						href="/purchaseOrder.html?token=<%=session.getAttribute("Token")%>"><i
							class="fa fa-circle-thin"></i><span> Purchase Order</span></a></li>

				</ul>
			</li>

			<li class="treeview"><a href="#"><i class="fa fa-money"></i>
					<span>Transections</span> <span class="pull-right-container">
						<i class="fa fa-angle-left pull-right"></i>
				</span> </a>
				<ul class="treeview-menu">
					<li class="active"><a href="#"><i
							class="fa fa-circle-thin"></i><span>Customer Transections</span></a></li>
					<li class="active"><a href="#"><i
							class="fa fa-circle-thin"></i> <span>Supplier Transections</span></a></li>
					<li class="active"><a href="#"><i
							class="fa fa-circle-thin"></i> <span>ank Transections</span></a></li>
				</ul></li>

			<li
				class="treeview ${current == 'bank' or current == 'counter' or current== 'department'  ? 'active' : '' }">
				<a href="#"><i class="fa  fa-link"></i> <span>Others</span> <span
					class="pull-right-container"> <i
						class="fa fa-angle-left pull-right"></i>
				</span> </a>
				<ul class="treeview-menu">
					<li class="${current == 'bank' ? 'active' : '' }"><a
						href="/bank.html?token=<%=session.getAttribute("Token")%>"><i
							class="fa fa-circle-thin"></i><span> Banks</span></a></li>

					<li class="${current == 'department' ? 'active' : '' }"><a
						href="/department.html?token=<%=session.getAttribute("Token")%>"><i
							class="fa fa-circle-thin"></i><span> Departments</span></a></li>

					<li class="${current == 'counter' ? 'active' : '' }"><a
						href="/counter.html?token=<%=session.getAttribute("Token")%>"><i
							class="fa fa-circle-thin"></i><span> Counters</span></a></li>

				</ul>
			</li>

		</ul>
		<!-- /.sidebar-menu -->
	</section>
	<!-- /.sidebar -->

	<!-- /.sidebar -->
</aside>
