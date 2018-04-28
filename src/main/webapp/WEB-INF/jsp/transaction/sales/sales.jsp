<!-- /** -->
<!--  * @author M.Vithusanth -->
<!--  * @Created On 28th April 2018 -->
<!--  * @Purpose Sales  Page  -->
<!--  */ -->

<%@page import="com.vithu.uscms.entities.Sales"%>
<%@page import="java.util.List"%>
<%@page import="com.vithu.uscms.others.GenericResult"%>
<%@ include file="../../../layouts/taglib.jsp"%>


<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<a
			href="/salesAddView.html?token=<%=session.getAttribute("Token")%>"
			class="btn btn-success">Add New Sales</a>
	</section>

	<!-- Main content -->
	<section class="content">
		<div class="row">
			<div class="col-md-6">
				<div class="box box-body box-success">
					<table id="view-table"
						class="table table-condensed table-bordered table-hover table-striped table-pad">
						<thead>
							<tr>
								<th style='text-align: center'>#</th>
								<th>Customer</th>
								<th>Sold</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${sales.result}" var="sale">
								<tr onclick="viewSalesProduct(${sale.id})" id="${sale.id}">
									<td></td>
									<td>${sale.customer.user.name}</td>
									<td>${sale.tDate}</td>
									<td style='text-align: center; position: relative;'><i
										class='arrow glyphicon glyphicon-arrow-right'></i></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>

			<div class="col-md-6">
				<div class="box box-body box-info" id="trans-details">
					<table
						class="table table-condensed table-hover table-striped table-bordered">
						<thead>
							<tr>
								<th>Product</th>
								<th>Unit Price</th>
								<th>Qty</th>
								<th>Discount</th>
								<th>Total</th>
							</tr>
						</thead>
						<tbody id="s-product">
						</tbody>
						<tfoot>
							<tr>
								<th colspan="4" style='text-align: center'>Grand Total</th>
								<th class='number' id="grand-total"></th>
							</tr>
						</tfoot>
					</table>

					<hr />

					<div class="input-group">
						<label class="input-group-addon">Sales Officer</label> <input
							type="text" readonly class="form-control" id="s-officer" />
					</div>

					<div class="input-group">
						<label class="input-group-addon">Added By</label> <input
							type="text" readonly class="form-control" id="added-by" />
					</div>

					<div class="input-group">
						<label class="input-group-addon">Department</label> <input
							type="text" readonly class="form-control" id="dept-name" />
					</div>

					<hr />

					<div style="width: 100%; text-align: center" id="pop-table-footer">
					</div>
				</div>
			</div>
		</div>
	</section>

	<!-- /.content -->
</div>


<!-- REQUIRED JS SCRIPTS -->
<script
	src="<c:url value=" /resources/plugins/datatables/jquery.dataTables.min.js" />"></script>
<script
	src="<c:url value="/resources/plugins/datatables/dataTables.bootstrap.min.js" />"></script>

<script>
	var sales = "";
	sales = ${sales.resultString};

	//Data table
	$(function() {
		$('#view-table').DataTable({
			"scrollX" : true,
			"info":false
		});
	});

	//Function for view sales products
	function viewSalesProduct(id) {
		$(".selected-row").removeClass("selected-row");
		$("#"+id).addClass("selected-row");

		var grandTotal=0;
		$("#po-product").empty();
		$.each(sales.result, function(i, sale) {
			if (sale.id == id) {
				console.log(sale);
				console.log(sale.dept.name);

				$("#added-by").val(sale.addedBy.user.name);
				$("#dept-name").val(sale.dept.name);
				$("#s-officer").val(sale.salesOfficer.user.name);
				
				$.each(sale.salesProduct, function(i, sp) {
					console.log(sp);
					var htmlStr = "<tr>";
					htmlStr += "<td >"+sp.product.name+" | <i style='font-size:12px;'>"+sp.product.code+"</i></td>";
					htmlStr += "<td class='number'>"+formatNumber(sp.unitPrice,2)+"</td>";
					htmlStr += "<td class='number'>"+formatNumber(sp.qty,2)+"</td>";
					htmlStr += "<td class='number'>"+formatNumber(sp.totDiscount,2)+"</td>";
					htmlStr += "<td class='number'>"+formatNumber(sp.qty*sp.unitPrice-sp.totDiscount,2)+"</td>";
					htmlStr += "</tr>";
					
					$("#s-product").append(htmlStr);
					grandTotal=grandTotal+(sp.qty*sp.unitPrice-sp.totDiscount);
				});
				$("#grand-total").html(formatNumber(grandTotal,2));
			}
		});
	}
	
	$(document).ready(function () {
		$("#sidebar-style").addClass('sidebar-collapse');
	});
	
</script>