<!-- /** -->
<!--  * @author M.Vithusanth -->
<!--  * @Created On 27th April 2018 -->
<!--  * @Purpose Purchase  Page  -->
<!--  */ -->

<%@page import="com.vithu.uscms.entities.Purchase"%>
<%@page import="java.util.List"%>
<%@page import="com.vithu.uscms.others.GenericResult"%>
<%@ include file="../../../layouts/taglib.jsp"%>


<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<a
			href="/purchaseAddView.html?token=<%=session.getAttribute("Token")%>"
			class="btn btn-success">Add New Purchase</a>
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
								<th>Code</th>
								<th>Supplier</th>
								<th>Purchased</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${purchases.result}" var="purchase">
								<tr onclick="viewPurProduct(${purchase.id})" id="${purchase.id}">
									<td></td>
									<td>${purchase.code}</td>
									<td>${purchase.supplier.user.name}</td>
									<td>${purchase.tDate}</td>
									<td style='text-align: center; position: relative;'><i
										class='arrow glyphicon glyphicon-arrow-right'></i>
									</td>
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
								<th>Exp. Price</th>
								<th>Qty</th>
								<th>Discount</th>
								<th>Total</th>
							</tr>
						</thead>
						<tbody id="po-product">
						</tbody>
						<tfoot>
							<tr>
								<th colspan="4"  style='text-align:center'>Grand Total</th>
								<th class='number' id="grand-total"></th>
							</tr>
						</tfoot>
					</table>

					<hr />

					<div class="input-group">
						<label class="input-group-addon">Added By</label> <input
							type="text" readonly class="form-control" id="added-by" />
					</div>

					<div class="input-group">
						<label class="input-group-addon">Department</label> <input
							type="text" readonly class="form-control" id="dept-name" />
					</div>

					<div class="input-group">
						<label class="input-group-addon">Note</label>
						<textarea readonly rows="1" class="form-control" id="note"></textarea>
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
	var purchases = "";
	purchases = ${purchases.resultString};

	//Data table
	$(function() {
		$('#view-table').DataTable({
			"scrollX" : true,
			"info":false
		});
	});

	//Function for view purchase order products
	function viewPurProduct(id) {
		$(".selected-row").removeClass("selected-row");
		$("#"+id).addClass("selected-row");

		var grandTotal=0;
		$("#po-product").empty();
		$.each(purchases.result, function(i, purchase) {
			if (purchase.id == id) {
				console.log(purchase);
				console.log(purchase.dept.name);
				console.log(purchase.note);

				$("#added-by").val(purchase.addedBy.user.name);
				$("#dept-name").val(purchase.dept.name);
				$("#note").val(purchase.note);
				
				$.each(purchase.purProduct, function(i, pp) {
					console.log(pp);
					var htmlStr = "<tr>";
					htmlStr += "<td >"+pp.product.name+" | <i style='font-size:12px;'>"+pp.product.code+"</i></td>";
					htmlStr += "<td class='number'>"+formatNumber(pp.unitPrice,2)+"</td>";
					htmlStr += "<td class='number'>"+formatNumber(pp.qty,2)+"</td>";
					htmlStr += "<td class='number'>"+formatNumber(pp.totDiscount,2)+"</td>";
					htmlStr += "<td class='number'>"+formatNumber(pp.qty*pp.unitPrice-pp.totDiscount,2)+"</td>";
					htmlStr += "</tr>";
					
					$("#po-product").append(htmlStr);
					grandTotal=grandTotal+(pp.qty*pp.unitPrice-pp.totDiscount);
				});
				$("#grand-total").html(formatNumber(grandTotal,2));
			}
		});
	}
	
	
	$(document).ready(function () {
		$("#sidebar-style").addClass('sidebar-collapse');
	});
	
</script>