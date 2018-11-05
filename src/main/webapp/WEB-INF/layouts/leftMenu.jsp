
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>



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
				<p><%=session.getAttribute("NAME")%></p>
				<!-- Status -->
				<a href="#"><i style="color:#06fb30" class="fa fa-circle text-success"></i> Online</a>
			</div>
		</div>
		<%-- 
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
 --%>

		<hr>
		<!-- Sidebar Menu -->
		<ul class="sidebar-menu">
			<li id='user-m'
				class="treeview">
				<a href="#"><i class="fa fa-user"></i> <span>Users</span> <span
					class="pull-right-container"> <i
						class="fa fa-angle-left pull-right"></i>
				</span> </a>
				<ul class="treeview-menu">
					<li class="menu-employee"><a
						href="/employee.html?token=<%=session.getAttribute("Token")%>"><i
							class="fa fa-circle-thin"></i><span> ${currentPage} Employees</span></a></li>

					<li class="menu-customer"><a
						href="/customer.html?token=<%=session.getAttribute("Token")%>"><i
							class="fa fa-circle-thin"></i><span> Customers</span></a></li>

					<li class="menu-supplier"><a
						href="/supplier.html?token=<%=session.getAttribute("Token")%>"><i
							class="fa fa-circle-thin"></i><span> Suppliers</span></a></li>
				</ul>
			</li>

			<li id="pro-m"
				class='treeview   <%=session.getAttribute("ROLE") == "Cashier" ? "hidden" : ""%> '>
				<a href="#"><i class="fa  fa-link"></i> <span>Product</span> <span
					class="pull-right-container"> <i
						class="fa fa-angle-left pull-right"></i>
				</span> </a>
				<ul class="treeview-menu">
					<li class="menu-product"><a
						href="/product.html?token=<%=session.getAttribute("Token")%>"><i
							class="fa fa-circle-thin"></i><span> Products</span></a></li>

					<li class="menu-brand"><a
						href="/brand.html?token=<%=session.getAttribute("Token")%>"><i
							class="fa fa-circle-thin"></i><span> Brands</span></a></li>

					<li class="menu-productType"><a
						href="/productType.html?token=<%=session.getAttribute("Token")%>"><i
							class="fa fa-circle-thin"></i><span> Product Types</span></a></li>

					<li class="menu-itemType"><a
						href="/itemType.html?token=<%=session.getAttribute("Token")%>"><i
							class="fa fa-circle-thin"></i><span> Item Types</span></a></li>
				</ul>
			</li>

			<li id="sal-m"
				class="treeview">
				<a href="#"><i class="fa  fa-cart-arrow-down"></i> <span>Sales</span>
					<span class="pull-right-container"> <i
						class="fa fa-angle-left pull-right"></i>
				</span> </a>
				<ul class="treeview-menu">
					<li class="menu-sales"><a
						href="/sales.html?token=<%=session.getAttribute("Token")%>"><i
							class="fa fa-circle-thin"></i><span> Sales</span></a></li>

<%-- 
					<li class="menu-productType"><a
						href="/salesOrder.html?token=<%=session.getAttribute("Token")%>"><i
							class="fa fa-circle-thin"></i><span> Sales Order</span></a></li>
 --%>
				</ul>
			</li>


			<li id="pur-m"
				class="treeview">
				<a href="#"><i class="fa  fa-truck"></i> <span>Purchase</span> <span
					class="pull-right-container"> <i
						class="fa fa-angle-left pull-right"></i>
				</span> </a>
				<ul class="treeview-menu">
					<li class="menu-purchase"><a
						href="/purchase.html?token=<%=session.getAttribute("Token")%>"><i
							class="fa fa-circle-thin"></i><span> Purchase</span></a></li>

					<li class="menu-purchaseOrder"><a
						href="/purchaseOrder.html?token=<%=session.getAttribute("Token")%>"><i
							class="fa fa-circle-thin"></i><span> Purchase Order</span></a></li>

				</ul>
			</li>

			

			<li id="otr-m"
				class="treeview">
				<a href="#"><i class="fa  fa-link"></i> <span>Others</span> <span
					class="pull-right-container"> <i
						class="fa fa-angle-left pull-right"></i>
				</span> </a>
				<ul class="treeview-menu">
					<li class="menu-bank"><a
						href="/bank.html?token=<%=session.getAttribute("Token")%>"><i
							class="fa fa-circle-thin"></i><span> Banks</span></a></li>

					<li class="menu-department"><a
						href="/department.html?token=<%=session.getAttribute("Token")%>"><i
							class="fa fa-circle-thin"></i><span> Departments</span></a></li>

					<li class="menu-counter"><a
						href="/counter.html?token=<%=session.getAttribute("Token")%>"><i
							class="fa fa-circle-thin"></i><span> Counters</span></a></li>

				</ul>
			</li>

			<li id="rep-m"
				class="treeview">
				<a href="#"><i class="ion ion-stats-bars"></i> <span>Report</span> <span
					class="pull-right-container"> <i
						class="fa fa-angle-left pull-right"></i>
				</span> </a>
				<ul class="treeview-menu">
					<li class="menu-reachedZeroLevelProduct"><a
						href="/reachedZeroLevelProduct.html?token=<%=session.getAttribute("Token")%>"><i
							class="fa fa-circle-thin"></i><span> Reached Zero Level Products</span></a></li>
				
					<li class="menu-reachedMinLevelProduct"><a
						href="/reachedMinLevelProduct.html?token=<%=session.getAttribute("Token")%>"><i
							class="fa fa-circle-thin"></i><span> Reached Reorder Level Products</span></a></li>
				
					<li class="menu-salesAmountVsQty"><a
						href="/salesAmountVsQty.html?token=<%=session.getAttribute("Token")%>"><i
							class="fa fa-circle-thin"></i><span> Sales Amount Vs Qty</span></a></li>

					<li class="menu-purAmountVsQty"><a
						href="/purAmountVsQty.html?token=<%=session.getAttribute("Token")%>"><i
							class="fa fa-circle-thin"></i><span> Purchase Amount Vs Qty</span></a></li>
							
							
					<li class="menu-salesAmountVsProduct"><a
						href="/salesAmountVsProduct.html?token=<%=session.getAttribute("Token")%>"><i
							class="fa fa-circle-thin"></i><span> Sales Amount Vs Product</span></a></li>


				</ul>
			</li>


		</ul>
		<!-- /.sidebar-menu -->
	</section>
	<!-- /.sidebar -->

	<!-- /.sidebar -->
</aside>

<script>
	var role = "<%=session.getAttribute("ROLE")%>";
	console.log("*********************"+role);
	
	if(role == 'Cashier'){
		$("#user-m").addClass("hidden");
		$("#pro-m").addClass("hidden");
		$("#rep-m").addClass("hidden");
		$("#otr-m").addClass("hidden");
		$("#pur-m").addClass("hidden");
	}
	else if(role == 'Admin'){
		$("#user-m").removeClass("hidden");
		$("#pro-m").removeClass("hidden");
		$("#rep-m").removeClass("hidden");
		$("#otr-m").removeClass("hidden");
		$("#pur-m").removeClass("hidden");
	
	}

</script>