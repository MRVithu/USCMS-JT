<!-- /** -->
<!--  * @author M.Vithusanth -->
<!--  * @Created On 23rd April 2018 -->
<!--  * @Purpose Purchase Order Add Page  -->
<!--  */ -->

<%@page import="com.vithu.uscms.entities.PurchaseOrder"%>
<%@page import="java.util.List"%>
<%@page import="com.vithu.uscms.others.GenericResult"%>
<%@ include file="../../../layouts/taglib.jsp"%>


<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<a
			href="/purchaseOrderAddView.html?token=<%=session.getAttribute("Token")%>"
			class="btn btn-success">Add New Purchase Order</a>
	</section>

	<!-- Main content -->
	<section class="content">
		<div class="row">
			<div class="col-md-8">
				<div class="box box-body box-success">
					<table id="view-table"
						class="table table-condensed table-bordered table-hover table-striped table-pad">
						<thead>
							<tr>
								<th style='text-align: center'>#</th>
								<th>Code</th>
								<th>Supplier</th>
								<th>Ordered</th>
								<th>Expected</th>
								<th style='text-align: center'>Status</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${purchaseOrders.result}" var="purchaseOrder">
								<tr onclick="viewPOProduct(${purchaseOrder.id})" id="${purchaseOrder.id}">
									<td></td>
									<td>${purchaseOrder.code}</td>
									<td>${purchaseOrder.supplier.user.name}</td>
									<td>${purchaseOrder.tDate}</td>
									<td>${purchaseOrder.expectedDate}</td>
									<td style='text-align: center; position: relative;'>${!purchaseOrder.isClosed
										? "<span class='label label-danger'>Pending</span>" : "<span
										class='label label-success'>Closed</span>"}<i
										class='arrow glyphicon glyphicon-arrow-right'></i>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>

			<div class="col-md-4">
				<div class="box box-body box-info" id="trans-details">
					<table
						class="table table-condensed table-hover table-striped table-bordered">
						<thead>
							<tr>
								<th>Product</th>
								<th>Exp. Price</th>
								<th>Qty</th>
								<th>Total</th>
							</tr>
						</thead>
						<tbody id="po-product">
						</tbody>
						<tfoot>
							<tr>
								<th colspan="3"  style='text-align:center'>Grand Total</th>
								<th id="grand-total"  class='number'></th>
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
	var purchaseOrders = "";
	purchaseOrders = ${purchaseOrders.resultString};

	//Data table
	$(function() {
		$('#view-table').DataTable({
			"scrollX" : true,
			"info":false
		});
	});

	//Function for view purchase order products
	function viewPOProduct(id) {
		$(".selected-row").removeClass("selected-row");
		$("#"+id).addClass("selected-row");

		
		
		var grandTotal=0;
		$("#po-product").empty();
		$.each(purchaseOrders.result, function(i, po) {
			
			
			if (po.id == id) {
				console.log(po.addedBy.user.name);
				console.log(po.dept.name);
				console.log(po.note);
				$("#added-by").val(po.addedBy.user.name);
				$("#dept-name").val(po.dept.name);
				$("#note").val(po.note);
				$.each(po.poProduct, function(i, pop) {
					
					var htmlStr = "<tr>";
					htmlStr += "<td >"+pop.product.name+" | <i style='font-size:12px'>"+pop.product.code+"</i></td>";
					htmlStr += "<td class='number'>"+formatNumber(pop.unitPrice,2)+"</td>";
					htmlStr += "<td class='number'>"+formatNumber(pop.qty,2)+"</td>";
					htmlStr += "<td class='number'>"+formatNumber(pop.qty*pop.unitPrice,2)+"</td>";
					htmlStr += "</tr>";
					
					$("#po-product").append(htmlStr);
					grandTotal=grandTotal+(pop.qty*pop.unitPrice);
				});
				$("#grand-total").html(formatNumber(grandTotal,2));
				if(po.isClosed==false){				
					 //htmlString="<input type='button' onclick='' id='btn-make-purchase' class='btn btn-info app-button tbtn' value='Make as purchase' />";
					 var htmlString ="<input type='button' onclick='closePurchaseOrder("+id+")' id='btn-close' class='btn btn-danger app-button tbtn'  value='Close Order' />"
					$("#pop-table-footer").html(htmlString);
				}
				else{
					$("#pop-table-footer").empty();
				}
			}
		});
	}
	
	//Function for close existing purchase order
	function closePurchaseOrder(id){
		try{
			$.ajax({
		        url:'http://localhost:8080/closePurchaseOrder/'+ id +'.json?token=<%=session.getAttribute("Token")%>',
		        type: 'POST',
		        data: { id:id },
		        success: function (res) {
		        	console.log(res );
		            
		            setTimeout(function() {
						//$("#res-msg").removeClass("alert-success").removeClass("alert-danger").addClass("alert-info");
						//$("#res-msg strong").html("Fill all fields and hit Save");
						if (res.message == "SUCCESS"){
							window.location.reload(true);
						}
					}, 500);
				},
		        error: function (res) {
		            alert("res");
		        }
		    });
		}
		catch(err){
			alert(err);
		}
		return false;
	}
	

	$(document).ready(function () {
		$("#sidebar-style").addClass('sidebar-collapse');
	});
	
</script>