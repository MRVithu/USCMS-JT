<!-- /** -->
<!--  * @author M.Vithusanth -->
<!--  * @CreatedOn 21th April 2018 -->
<!--  * @Purpose Product Type Page  -->
<!--  */ -->

<%@page import="com.vithu.uscms.entities.ProductType"%>
<%@page import="java.util.List"%>
<%@page import="com.vithu.uscms.others.GenericResult"%>
<%@ include file="../../layouts/taglib.jsp"%>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header"></section>

	<!-- Main content -->
	<section class="content">
		<div class="box box-body">
			<table id="user-table"  style="cursor:pointer;"
				class="table table-condensed table-bordered table-hover table-striped table-pad">
				<thead>
					<tr>
						<th>Name</th>
						<th>Description</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${productTypes.result}" var="productType">
						<tr onclick="singleView(${productType.id})">
							<td>${productType.name}</td>
							<td>${productType.description}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>
	<!-- /.content -->
</div>


<!-- modal -->
<div class="modal fade" id="modal" role="dialog">
	<div class="modal-dialog">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" onclick="clear()">�</button>
				<h4 class="modal-title">View Product Type</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="input-group">
						<label class="input-group-addon">Name</label> <input type="text"
							id="name" readonly class="form-control " />
					</div>
					<div class="input-group">
						<label class="input-group-addon">Description</label> <input type="text"
							id="description" readonly class="form-control " />
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
	var productTypes = "";
	productTypes = ${productTypes.resultString};

	//Data table
	$(function() {
		$('#user-table').DataTable({
			"aoColumnDefs" : [ {
				"bSortable" : false,
				"aTargets" : [ 0, 2, 3 ]
			}, {
				"bSearchable" : false,
				"aTargets" : [ 0 ]
			} ],
			"scrollX" : true
		});
	});

	//To view single product type
	function singleView(id) {
		fillDataToModal(id);
		$(".modal-title").html("View Product Type");
		$(".form-control").prop("readonly", true);
		$(".form-control").prop("disabled", true);
		$('#modal').modal({
			backdrop : 'static',
			keyboard : false
		});
		$("#modal").modal("show");
	}
		
	//Fill data in modal.
	function fillDataToModal(id){
		console.log(productTypes.result);
		$.each(productTypes.result, function(i, productType){
			if(productType.id==id){
				$("#name").val(productType.name);
				$("#description").val(productType.description);
			}
		});
	}
</script>