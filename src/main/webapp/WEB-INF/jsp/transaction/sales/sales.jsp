
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<button id="btn-add" id="addProduct" class="btn btn-success">Add
			New</button>
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
								<tr onclick="viewPOProduct(${purchaseOrder.id})">
									<td></td>
									<td>${purchaseOrder.code}</td>
									<td>${purchaseOrder.code}</td>
									<td>${purchaseOrder.code}</td>
									<td>${purchaseOrder.code}</td>
									<td style='text-align: center; position: relative;'>${!purchaseOrder.isClosed
										? "<span class='label label-success'>Pending</span>" : "<span
										class='label label-danger'>Closed</span>"}<i
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
								<th class='number'>Exp. Price</th>
								<th class='number'>Qty</th>
								<th class='number'>Total</th>
							</tr>
						</thead>
						<tbody id="po-product">
						</tbody>
						<tfoot>
							<tr>
								<th colspan="3">Grand Total</th>
								<th id="grand-total"></th>
							</tr>
						</tfoot>
					</table>

					<hr />

					<div class="input-group">
						<label class="input-group-addon">Added By</label> <input
							type="text" readonly class="form-control" value="" />
					</div>

					<div class="input-group">
						<label class="input-group-addon">Department</label> <input
							type="text" readonly class="form-control" value="" />
					</div>

					<div class="input-group">
						<label class="input-group-addon">Note</label>
						<textarea readonly rows="1" class="form-control"></textarea>
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
		var grandTotal=0;
		$("#po-product").empty();
		$.each(purchaseOrders.result, function(i, po) {
			if (po.id == id) {
				$.each(po.poProduct, function(i, pop) {
					var htmlStr = "<tr>";
					htmlStr += "<td >"+pop.product.name+"</td>";
					htmlStr += "<td class='number'>"+formatNumber(pop.unitPrice,2)+"</td>";
					htmlStr += "<td class='number'>"+formatNumber(pop.qty,2)+"</td>";
					htmlStr += "<td class='number'>"+formatNumber(pop.qty*pop.unitPrice,2)+"</td>";
					htmlStr += "</tr>";
					
					$("#po-product").append(htmlStr);
					grandTotal=grandTotal+(pop.qty*pop.unitPrice);
				});
				$("#grand-total").html(formatNumber(grandTotal,2));
				
				var htmlString="<input type='button' onclick='' id='btn-make-purchase class='btn btn-info app-button tbtn' value='Make as purchase' ></input>";
				htmlString +="<input type='button' onclick='closePurchaseOrder("+id+")' id='btn-close' class='btn btn-danger app-button tbtn value='Close Order' ></input>";
				$("#pop-table-footer").html(htmlString);
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

	
	
</script>