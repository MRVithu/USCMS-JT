<!-- /** -->
<!--  * @author M.Vithusanth -->
<!--  * @Created On 23rd April 2018 -->
<!--  * @Purpose Purchase Order Page  -->
<!--  */ -->

<%@page import="com.vithu.uscms.entities.PurchaseOrder"%>
<%@page import="java.util.List"%>
<%@page import="com.vithu.uscms.others.GenericResult"%>
<%@ include file="../../../layouts/taglib.jsp"%>


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
								<tr>
									<td><a class="btn" onclick="updateProduct(${purchaseOrder.id})">
											<i class="fa fa-pencil-square-o"></i>
									</a> <a class="btn text-danger"
										onclick="deleteProduct(${purchaseOrder.id})"> <i
											class="fa fa-trash-o"></i>
									</a></td>
									<td onclick="singleView(${purchaseOrder.id})">${purchaseOrder.code}</td>
									<td onclick="singleView(${purchaseOrder.id})">${purchaseOrder.code}</td>
									<td onclick="singleView(${purchaseOrder.id})">${purchaseOrder.code}</td>
									<td onclick="singleView(${purchaseOrder.id})">${purchaseOrder.code}</td>
									<td onclick="singleView(${purchaseOrder.id})">${purchaseOrder.isClosed}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>

			<div class="col-md-4">
				<div class="box box-body box-info" id="trans-details"></div>
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

	//Function for view single product
	function singleView(id) {
		fillDataToModal(id);
		$(".modal-title").html("View Product");
		$(".form-control").prop("readonly", true);
		$(".form-control").prop("disabled", true);
		$('#modal').modal({
			backdrop : 'static',
			keyboard : false
		});
		$("#reset-btn").hide();
		$("#submit-btn").hide();
		$("#modal").modal("show");
	}
	
	//Function for delete existing product
	function deleteProduct(id){
		try{
			$.ajax({
		        url:'http://localhost:8080/deleteProduct/'+ id +'.json?token=<%=session.getAttribute("Token")%>',
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
	
	//Function for add new product
	$("#btn-add").on("click",function(){
		method="addProduct.json";
		clear();
		$(".modal-title").html("Add Product");
		$(".form-control").prop("readonly", false);
		$(".form-control").prop("disabled", false);
		$("#modal").modal({backdrop: 'static', keyboard: false});
		$("#reset-btn").show();
		$("#submit-btn").show();
		$("#modal").modal("show");
	});
	
	//Function for update vehicle
	function updateProduct(id){
		method = "updateProduct.json";
		$(".form-control").prop("readonly", false);
		$(".form-control").prop("disabled", false);
  		fillDataToModal(id);
  		$(".modal-title").html("Edit Product");
  		$("#pro-id").val(id);
  		$("#modal").modal({backdrop: 'static', keyboard: false});
		$("#reset-btn").show();
		$("#submit-btn").show();
  		$("#modal").modal('show');
  	}

	//Function for submit all data. 
	function update(form){
		try
		{
			if ($("#code").val().trim() == ""){
				$("#res-msg").removeClass("alert-success").removeClass("alert-info").addClass("alert-danger");
				$("#res-msg strong").html("Product code can not be empty");
			}else{
				var product = {};
				product.id=$("#pro-id").val();
				product.name=$("#name").val();
				product.code=$("#code").val();
				product.size=$("#size").val();
				product.purPrice=$("#pur-price").val();
				product.salesPrice=$("#sales-price").val();
				product.minPrice=$("#min-price").val();
				product.discount=$("#discount").val();
				product.description=$("#description").val();
				product.brand=$("#brand").val();
				product.itemType=$("#item-type").val();
				
				console.log(product);
				console.log(method);
				$.ajax({
					
					type: 'POST',
					url: 'http://localhost:8080/'+method+'?token=<%=session.getAttribute("Token")%>',
					data: {data:JSON.stringify(product)},
					success: function(res) {
						console.log(res);
						console.log(res.status);
						$("#res-msg strong").html(res.msg);
	
						if (res.status == false) {
							$("#res-msg").removeClass("alert-success").removeClass("alert-info").addClass("alert-danger");
							$("#res-msg strong").html(res.description);
						} else if (res.status == true) {
							$("#res-msg").removeClass("alert-danger").removeClass("alert-info").addClass("alert-success");
							$("#res-msg strong").html(res.description);
						}
	
						setTimeout(function() {
							$("#res-msg").removeClass("alert-success").removeClass("alert-danger").addClass("alert-info");
							$("#res-msg strong").html("Fill all fields and hit Save");
							if (res.message == "SUCCESS"){
								$("#modal").modal("hide");
								window.location.reload(true);
							}
						}, 1000);
					},
					error: function(xhr, textStatus, errorThrown) {
						console.log(xhr);
						alert("error");
						console.log(errorThrown);
					}
				});
			}
		}
		catch(err){
			alert(err);
		}
	return false;
	}
	
	//Fill data in modal.
	function fillDataToModal(id){
		console.log(products.result);
		$.each(products.result, function(i, product){
			if(product.id==id){
				$("#name").val(product.name);
				$("#code").val(product.code);
				$("#size").val(product.size);
				$("#pur-price").val(product.lastPurchasePrice);
				$("#sales-price").val(product.selleingPrice);
				$("#min-price").val(product.minPrice);
				$("#discount").val(product.discount);
				$("#description").val(product.description);
				
				$("#brand").prepend("<option>"+product.brand.name+"</option>");
				$("#item-type").prepend("<option>"+product.itemType.name+"</option>");
			}
		});
	}
	
	//Function for clear all fields in all modals.
	function clear(){
		$("#name").val("");
		$("#code").val("");
		$("#size").val("");
		$("#pur-price").val("");
		$("#sales-price").val("");
		$("#min-price").val("");
		$("#discount").val("");
		$("#description").val("");
  	}
</script>