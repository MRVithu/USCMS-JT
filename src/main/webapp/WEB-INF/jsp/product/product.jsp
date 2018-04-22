<!-- /** -->
<!--  * @author M.Vithusanth -->
<!--  * @CreatedOn 21th April 2018 -->
<!--  * @Purpose Product Page  -->
<!--  */ -->

<%@page import="com.vithu.uscms.entities.Product"%>
<%@page import="java.util.List"%>
<%@page import="com.vithu.uscms.others.GenericResult"%>
<%@ include file="../../layouts/taglib.jsp"%>

<style>

</style>


<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<button id="btn-add" id="addProduct" class="btn btn-success">Add
			New</button>
	</section>

	<!-- Main content -->
	<section class="content">
		<div class="box box-body">
			<table id="user-table" style="cursor: pointer;"
				class="table table-condensed table-bordered table-hover table-striped table-pad">
				<thead>
					<tr>
						<th></th>
						<th>Name</th>
						<th>Code</th>
						<th>Brand</th>
						<th>Description</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${products.result}" var="product">
						<tr>
							<td><a class="btn" onclick="updateVehicle(${product.id})">
									<i class="fa fa-pencil-square-o"></i>
							</a> <a class="btn text-danger"
								onclick="deleteVehicle(${product.id})"> <i
									class="fa fa-trash-o"></i>
							</a></td>
							<td onclick="singleView(${product.id})">${product.name}</td>
							<td onclick="singleView(${product.id})">${product.code}</td>
							<td onclick="singleView(${product.id})">${product.brand.name}</td>
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
			<div class="modal-header">
				<button type="button" class="close" onclick="clear()">×</button>
				<h4 class="modal-title">View Product</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="input-group">
						<label class="input-group-addon">Code</label> <input type="text"
							id="code" class="form-control " />
					</div>
					<div class="input-group">
						<label class="input-group-addon">Name</label> <input type="text"
							id="name" class="form-control " />
					</div>
					<div class="input-group">
						<label class="input-group-addon ">Brand</label> <select id="brand"
							class="form-control type" name="brand">
							<!-- Dropdown List Option -->
							<%-- <c:forEach items="${routes.result}" var="route">
								<option value="${route.id}">${route.name}</option>
							</c:forEach> --%>
						</select>
					</div>
					<div class="input-group">
						<label class="input-group-addon ">Item Type</label> <select id="item-type"
							class="form-control type" name="item">
							<!-- Dropdown List Option -->
							<%-- <c:forEach items="${routes.result}" var="route">
								<option value="${route.id}">${route.name}</option>
							</c:forEach> --%>
						</select>
					</div>
					<div class="input-group">
						<label class="input-group-addon ">Consumer Type</label> <select id="consumer-type"
							class="form-control type" name="consumer-type">
							<!-- Dropdown List Option -->
							<%-- <c:forEach items="${routes.result}" var="route">
								<option value="${route.id}">${route.name}</option>
							</c:forEach> --%>
						</select>
					</div>
					<div class="input-group">
						<label class="input-group-addon">Size</label> <input type="text"
							id="size" class="form-control " />
					</div>
					<div class="input-group">
						<label class="input-group-addon">Purchase Price</label> <input type="text"
							id="pur-price" class="form-control " />
					</div>
					<div class="input-group">
						<label class="input-group-addon">Sales Price</label> <input
							type="text" id="sales-price" class="form-control " />
					</div>
					<div class="input-group">
						<label class="input-group-addon">Min Price</label> <input
							type="text" id="min-price" class="form-control " />
					</div>
					<div class="input-group">
						<label class="input-group-addon">Discount</label> <input
							type="text" id="discount" class="form-control " />
					</div>
					<div class="input-group">
						<label class="input-group-addon">Description</label> <input
							type="text" id="description" class="form-control " />
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Ok</button>
			</div>
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
				"aTargets" : [ 0, 3, 4 ]
			}, {
				"bSearchable" : false,
				"aTargets" : [ 0 ]
			} ],
			"scrollX" : true
		});
	});

	//To view single product
	function singleView(id) {
		fillDataToModal(id);
		$(".form-control").prop("readonly", true);
		$(".form-control").prop("disabled", true);
		$('#modal').modal({
			backdrop : 'static',
			keyboard : false
		});
		$("#modal").modal("show");
	}
	
	//Fill data in modal
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
				$("#consumer-type").prepend("<option>"+product.consumerType.name+"</option>");
			}
		});
	}
</script>