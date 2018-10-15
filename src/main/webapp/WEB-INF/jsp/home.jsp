
<%@page import="com.vithu.uscms.entities.Sales"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ include file="../layouts/taglib.jsp"%>



<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<div class="row">
			<div class="col-md-6">
				<a
					href="/salesAddView.html?token=<%=session.getAttribute("Token")%>"
					class="btn btn-info">Sales</a> <a
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

		<!-- AREA CHART -->
		<div class="box box-primary">
			<div class="box-header with-border">
				<h3 class="box-title">Last 14 days Sales Vs Purchases</h3>

				<div class="box-tools pull-right">
					<button type="button" class="btn btn-box-tool"
						data-widget="collapse">
						<i class="fa fa-minus"></i>
					</button>
					<button type="button" class="btn btn-box-tool" data-widget="remove">
						<i class="fa fa-times"></i>
					</button>
				</div>
			</div>
			<div class="box-body">
				<div class="chart">
					<canvas id="areaChart" style="height:250px"></canvas>
				</div>
			</div>
			<!-- /.box-body -->
		</div>

	</section>
</div>


<!-- ChartJS 1.0.1 -->
<script src="<c:url value="/resources/plugins/chartjs/Chart.min.js" />"></script>
<script>
	var logger="";
	logger="<%=session.getAttribute("USER-NAME")%>";
	//console.log(logger);
	
	var salesAmountDayBy = "";
	salesAmountDayBy = ${salesAmountDayBy.resultString};
	console.log(salesAmountDayBy);
	
	var purAmountDayBy = "";
	purAmountDayBy = ${purAmountDayBy.resultString};
	console.log(purAmountDayBy);
	
	var todayPurchaseOrders = "";
	todayPurchaseOrders = ${todayPurchaseOrders};
	console.log("--"+todayPurchaseOrders);
	
	var todayRegisteredSupplier = "";
	todayRegisteredSupplier = ${todayRegisteredSupplier};
	console.log("--"+todayRegisteredSupplier);

	var sales = "";
	sales = ${sales.resultString};
	console.log(sales);
	
	var d = new Date();
	var t = d.getFullYear() + "-" + (d.getMonth()+1) + "-" + d.getDate();

	d.setDate(d.getDate() - 1);
	var t1 = d.getFullYear() + "-" + (d.getMonth()+1) + "-" + d.getDate();

	d.setDate(d.getDate() - 1);
	var t2 = d.getFullYear() + "-" + (d.getMonth()+1) + "-" + d.getDate();

	d.setDate(d.getDate() - 1);
	var t3 = d.getFullYear() + "-" + (d.getMonth()+1) + "-" + d.getDate();

	d.setDate(d.getDate() - 1);
	var t4 = d.getFullYear() + "-" + (d.getMonth()+1) + "-" + d.getDate();

	d.setDate(d.getDate() - 1);
	var t5 = d.getFullYear() + "-" + (d.getMonth()+1) + "-" + d.getDate();

	d.setDate(d.getDate() - 1);
	var t6 = d.getFullYear() + "-" + (d.getMonth()+1) + "-" + d.getDate();

	d.setDate(d.getDate() - 1);
    var t7 = d.getFullYear() + "-" + (d.getMonth()+1) + "-" + d.getDate();

    d.setDate(d.getDate() - 1);
    var t8 = d.getFullYear() + "-" + (d.getMonth()+1) + "-" + d.getDate();

    d.setDate(d.getDate() - 1);
    var t9 = d.getFullYear() + "-" + (d.getMonth()+1) + "-" + d.getDate();

    d.setDate(d.getDate() - 1);
    var t10 = d.getFullYear() + "-" + (d.getMonth()+1) + "-" + d.getDate();

    d.setDate(d.getDate() - 1);
    var t11 = d.getFullYear() + "-" + (d.getMonth()+1) + "-" + d.getDate();

    d.setDate(d.getDate() - 1);
    var t12 = d.getFullYear() + "-" + (d.getMonth()+1) + "-" + d.getDate();

    d.setDate(d.getDate() - 1);
    var t13 = d.getFullYear() + "-" + (d.getMonth()+1) + "-" + d.getDate();
	
	var s = 0;
	var s1 = 0;
	var s2 = 0;
	var s3 = 0;
	var s4 = 0;
	var s5 = 0;
	var s6 = 0;
	var s7 = 0;
	var s8 = 0;
	var s9 = 0;
	var s10 = 0;
	var s11 = 0;
	var s12 = 0;
	var s13 = 0;
	
	$.each( salesAmountDayBy.result, function(i, data) {
		if(data.tDate == t){
			s = data.amount;
		}
		else if(data.tDate == t1){
			s1 = data.amount;
		}
		else if(data.tDate == t2){
			s2 = data.amount;
		}
		else if(data.tDate == t3){
			s3 = data.amount;
		}
		else if(data.tDate == t4){
			s4 = data.amount;
		}
		else if(data.tDate == t5){
			s5 = data.amount;
		}
		else if(data.tDate == t6){
			s6 = data.amount;
		}
		else if(data.tDate == t7){
			s7 = data.amount;
		}
		else if(data.tDate == t8){
			s8 = data.amount;
		}
		else if(data.tDate == t9){
			s9 = data.amount;
		}
		else if(data.tDate == t10){
			s10 = data.amount;
		}
		else if(data.tDate == t11){
			s11 = data.amount;
		}
		else if(data.tDate == t12){
			s12 = data.amount;
		}
		else if(data.tDate == t13){
			s13 = data.amount;
		}
	});
	
	var p = 0;
	var p1 = 0;
	var p2 = 0;
	var p3 = 0;
	var p4 = 0;
	var p5 = 0;
	var p6 = 0;
	var p7 = 0;
	var p8 = 0;
	var p9 = 0;
	var p10 = 0;
	var p11 = 0;
	var p12 = 0;
	var p13 = 0;

	$.each( purAmountDayBy.result, function(i, data) {
		if(data.tDate == t){
			p = data.amount;
		}
		else if(data.tDate == t1){
			p1 = data.amount;
		}
		else if(data.tDate == t2){
			p2 = data.amount;
		}
		else if(data.tDate == t3){
			p3 = data.amount;
		}
		else if(data.tDate == t4){
			p4 = data.amount;
		}
		else if(data.tDate == t5){
			p5 = data.amount;
		}
		else if(data.tDate == t6){
			p6 = data.amount;
		}
		else if(data.tDate == t7){
			p7 = data.amount;
		}
		else if(data.tDate == t8){
			p8 = data.amount;
		}
		else if(data.tDate == t9){
			p9 = data.amount;
		}
		else if(data.tDate == t10){
			p10 = data.amount;
		}
		else if(data.tDate == t11){
			p11 = data.amount;
		}
		else if(data.tDate == t12){
			p12 = data.amount;
		}
		else if(data.tDate == t13){
			p13 = data.amount;
		}
	});

    //--------------
    //- AREA CHART -
    //--------------

    // Get context with jQuery - using jQuery's .get() method.
    var areaChartCanvas = $("#areaChart").get(0).getContext("2d");
    // This will get the first returned node in the jQuery collection.
    var areaChart = new Chart(areaChartCanvas);

    var areaChartData = {
      labels: [t13, t12, t11, t10, t9, t8, t7, t6, t5, t4, t3, t2, t1, t],
      datasets: [
        {
          label: "Sales",
          fillColor: "rgba(210, 214, 222, 1)",
          strokeColor: "rgba(210, 214, 222, 1)",
          pointColor: "rgba(210, 214, 222, 1)",
          pointStrokeColor: "#c1c7d1",
          pointHighlightFill: "#fff",
          pointHighlightStroke: "rgba(220,220,220,1)",
          data: [s13, s12, s11, s10, s9, s8, s7, s6, s5, s4, s3, s2, s1, s]
        },
        {
          label: "Purchase",
          fillColor: "rgba(60,141,188,0.9)",
          strokeColor: "rgba(60,141,188,0.8)",
          pointColor: "#3b8bba",
          pointStrokeColor: "rgba(60,141,188,1)",
          pointHighlightFill: "#fff",
          pointHighlightStroke: "rgba(60,141,188,1)",
          data: [p13, p12, p11, p10, p9, p8, p7, p6, p5, p4, p3, p2, p1, p]
        }
      ]
    };

    var areaChartOptions = {
      //Boolean - If we should show the scale at all
      showScale: true,
      //Boolean - Whether grid lines are shown across the chart
      scaleShowGridLines: false,
      //String - Colour of the grid lines
      scaleGridLineColor: "rgba(0,0,0,.05)",
      //Number - Width of the grid lines
      scaleGridLineWidth: 1,
      //Boolean - Whether to show horizontal lines (except X axis)
      scaleShowHorizontalLines: true,
      //Boolean - Whether to show vertical lines (except Y axis)
      scaleShowVerticalLines: true,
      //Boolean - Whether the line is curved between points
      bezierCurve: true,
      //Number - Tension of the bezier curve between points
      bezierCurveTension: 0.3,
      //Boolean - Whether to show a dot for each point
      pointDot: false,
      //Number - Radius of each point dot in pixels
      pointDotRadius: 4,
      //Number - Pixel width of point dot stroke
      pointDotStrokeWidth: 1,
      //Number - amount extra to add to the radius to cater for hit detection outside the drawn point
      pointHitDetectionRadius: 20,
      //Boolean - Whether to show a stroke for datasets
      datasetStroke: true,
      //Number - Pixel width of dataset stroke
      datasetStrokeWidth: 2,
      //Boolean - Whether to fill the dataset with a color
      datasetFill: true,
      //String - A legend template
     //Boolean - whether to maintain the starting aspect ratio or not when responsive, if set to false, will take up entire container
      maintainAspectRatio: true,
      //Boolean - whether to make the chart responsive to window resizing
      responsive: true
    };
	
	areaChart.Line(areaChartData, areaChartOptions);
	
</script>


