
<%@page import="com.vithu.uscms.entities.Sales"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>



<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<div class="row">
			<div class="col-md-6">
				<a
					href="/salesAddView.html?token=<%=session.getAttribute("Token")%>"
					class="btn btn-info">Sales</a> </br>
				</br> <a
					href="/purchaseAddView.html?token=<%=session.getAttribute("Token")%>"
					class="btn btn-info">Purchase</a>
			</div>
		</div>
	</section>
	<!-- Main content -->
	<section class="content">
		<!-- Small boxes (Stat box) -->
		<div class="row">
			<div class="col-lg-3 col-xs-6">
				<!-- small box -->
				<div class="small-box bg-aqua">
					<div class="inner">
						<h3>${todayPurchaseOrders}</h3>

						<p>Today Purchase Order</p>
					</div>
					<div class="icon">
						<i class="ion ion-bag"></i>
					</div>
					<a href="#" class="small-box-footer">More info <i
						class="fa fa-arrow-circle-right"></i></a>
				</div>
			</div>
			<!-- ./col -->
			<div class="col-lg-3 col-xs-6">
				<!-- small box -->
				<div class="small-box bg-green">
					<div class="inner">
						<h3>
							53<sup style="font-size: 20px">%</sup>
						</h3>

						<p>Bounce Rate</p>
					</div>
					<div class="icon">
						<i class="ion ion-stats-bars"></i>
					</div>
					<a href="#" class="small-box-footer">More info <i
						class="fa fa-arrow-circle-right"></i></a>
				</div>
			</div>
			<!-- ./col -->
			<div class="col-lg-3 col-xs-6">
				<!-- small box -->
				<div class="small-box bg-yellow">
					<div class="inner">
						<h3>${todayRegisteredCustomers}</h3>

						<p>Today Customer Registrations</p>
					</div>
					<div class="icon">
						<i class="ion ion-person-add"></i>
					</div>
					<a href="#" class="small-box-footer">More info <i
						class="fa fa-arrow-circle-right"></i></a>
				</div>
			</div>
			<!-- ./col -->
			<div class="col-lg-3 col-xs-6">
				<!-- small box -->
				<div class="small-box bg-red">
					<div class="inner">
						<h3>${todayRegisteredSupplier}</h3>

						<p>Today Supplier Registrations</p>
					</div>
					<div class="icon">
						<i class="ion ion-person-add"></i>
					</div>
					<a href="#" class="small-box-footer">More info <i
						class="fa fa-arrow-circle-right"></i></a>
				</div>
			</div>
			<!-- ./col -->
		</div>
		<!-- /.row -->
	</section>
</div>


<script>
	var logger="";
	logger="<%=session.getAttribute("USER-NAME")%>";
	console.log(logger);
	
	var todayRegisteredCustomers = "";
	todayRegisteredCustomers = ${todayRegisteredCustomers};
	console.log("--"+todayRegisteredCustomers);
	
	var todayPurchaseOrders = "";
	todayPurchaseOrders = ${todayPurchaseOrders};
	console.log("--"+todayPurchaseOrders);
	
	var todayRegisteredSupplier = "";
	todayRegisteredSupplier = ${todayRegisteredSupplier};
	console.log("--"+todayRegisteredSupplier);

	var sales = "";
	sales = ${sales.resultString};
	console.log(sales);
	
</script>


