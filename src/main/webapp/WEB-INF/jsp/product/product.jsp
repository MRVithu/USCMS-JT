<!-- /** -->
<!--  * @author M.Vithusanth -->
<!--  * @Created On 21th April 2018 -->
<!--  * @Purpose Product Page  -->
<!--  */ -->

<%@page import="com.vithu.uscms.entities.Product"%>
<%@page import="java.util.List"%>
<%@page import="com.vithu.uscms.others.GenericResult"%>
<%@ include file="../../layouts/taglib.jsp"%>


<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<button id="btn-add" id="addProduct" class="btn btn-success">Add
			New Product</button>
	</section>

	<!-- Main content -->
	<section class="content">
		<div class="box box-body">
			<table id="user-table" style="cursor: pointer;"
				class="table table-condensed table-bordered table-hover table-striped table-pad">
				<thead>
					<tr>
						<th style="width: 50px;"></th>
						<th>Name</th>
						<th>Code</th>
						<th>Brand</th>
						<th>Item Type</th>
						<th>Description</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${products.result}" var="product">
						<tr>
							<td style="width: 50px;"><a
								onclick="updateProduct(${product.id})"> <i
									class="fa fa-pencil-square-o"></i>
							</a> <a class=" text-danger" onclick="deleteProduct(${product.id})">
									<i class="fa fa-trash-o"></i>
							</a></td>
							<td onclick="singleView(${product.id})">${product.name}</td>
							<td onclick="singleView(${product.id})">${product.code}</td>
							<td onclick="singleView(${product.id})">${product.brand.name}</td>
							<td onclick="singleView(${product.id})">${product.itemType.name}</td>
							<td onclick="singleView(${product.id})">${product.description}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>
	<!-- /.content -->
</div>


<!-- modal -->
<!-- add new employee Modal -->
<div class="modal fade" id="modal" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<form role="form" method="post" onsubmit="return update(this);">
				<div class="modal-header">
					<button type="button" class="close" onclick="clear()">×</button>
					<h4 class="modal-title">View Product</h4>
					<div class="alert alert-info" id="res-msg" style="margin-top: 5px;">
						<strong>fill all and hit save.</strong>
					</div>
				</div>
				<div class="modal-body">
					<div class="row">
						<input type="hidden" id="pro-id" />
						<div class="input-group">
							<label class="input-group-addon">Code</label> <input type="text"
								id="code" class="form-control " />
						</div>
						<div class="input-group">
							<label class="input-group-addon">Name</label> <input type="text"
								id="name" class="form-control " />
						</div>
						<div class="input-group">
							<label class="input-group-addon ">Brand</label> <select
								id="brand" class="form-control type" name="brand">
								<!-- Dropdown List Option -->
								<c:forEach items="${brands.result}" var="brand">
									<option value="${brand.id}">${brand.name}</option>
								</c:forEach>
							</select>
						</div>
						<div class="input-group">
							<label class="input-group-addon ">Item Type</label> <select
								id="item-type" class="form-control type" name="item">
								<!-- Dropdown List Option -->
								<c:forEach items="${itemTypes.result}" var="itemType">
									<option value="${itemType.id}">${itemType.name}</option>
								</c:forEach>
							</select>
						</div>
						<div class="input-group">
							<label class="input-group-addon">Size</label> <input type="text"
								id="size" class="form-control " /><label
								class="input-group-addon small-addon">Units</label>
						</div>
						<div class="input-group">
							<label class="input-group-addon">Purchase Price</label> <input
								type="number" value="0" id="pur-price"
								class="form-control number field-wid" /><label
								class="input-group-addon small-addon">Rs.</label>
						</div>
						<div class="input-group">
							<label class="input-group-addon">Sales Price</label> <input
								type="number" value="0" id="sales-price"
								class="form-control number field-wid" /><label
								class="input-group-addon small-addon">Rs.</label>
						</div>
						<div class="input-group">
							<label class="input-group-addon">Min Price</label> <input
								type="number" value="0" id="min-price"
								class="form-control number field-wid" /><label
								class="input-group-addon small-addon">Rs.</label>
						</div>
						<div class="input-group">
							<label class="input-group-addon">Discount</label> <input
								type="number" value="0" id="discount"
								class="form-control number field-wid" /><label
								class="input-group-addon small-addon"><b>%</b></label>
						</div>
						<div class="input-group">
							<label class="input-group-addon">Description</label> <input
								type="text" id="description" class="form-control " />
						</div>
						<div class="input-group" id="added-by-div">
							<label class="input-group-addon">Added By</label> <input
								type="text" id="added-by" class="form-control " />
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<input id="reset-btn" type="reset"
						class="btn btn-primary alert-warning" style="float: left;"
						onclick="clear()" value="Reset" /> <input id="close-btn"
						type="button" class="btn btn-primary alert-info"
						onclick="clear();$('#modal').modal('hide')" value="Close" /> <input
						id="submit-btn" type="submit"
						class="btn btn-primary alert-success" value="Save">
				</div>
			</form>
		</div>
	</div>
</div>


<!-- REQUIRED JS SCRIPTS -->
<script
	src="<c:url value=" /resources/plugins/datatables/jquery.dataTables.min.js" />"></script>
<script
	src="<c:url value="/resources/plugins/datatables/dataTables.bootstrap.min.js" />"></script>

<script>
	var products = "";
	products = ${products.resultString};

	//Data table
	$(function() {
		$('#user-table').DataTable({
			"aoColumnDefs" : [ {
				"bSortable" : false,
				"aTargets" : [ 0 ]
			}, {
				"bSearchable" : false,
				"aTargets" : [ 0 ]
			} ],
			"scrollX" : true
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
		$("#code").val("P");
		$(".modal-title").html("Add Product");
		$(".form-control").prop("readonly", false);
		$(".form-control").prop("disabled", false);
		$("#modal").modal({backdrop: 'static', keyboard: false});
		$("#reset-btn").show();
		$("#submit-btn").show();
		$("#added-by-div").hide();
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
		$("#added-by-div").hide();
		$("#added-by-div").show();
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
				$("#added-by").val(product.addedBy.user.name)
				
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