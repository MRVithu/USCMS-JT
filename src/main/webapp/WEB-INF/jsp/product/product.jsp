<!-- /** -->
<!--  * @author M.Vithusanth -->
<!--  * @CreatedOn 21th April 2018 -->
<!--  * @Purpose Product Page  -->
<!--  */ -->

<%@page import="com.vithu.uscms.entities.Product"%>
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
						<th>Code</th>
						<th>Brand</th>
						<th>Description</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${products.result}" var="product">
						<tr onclick="singleView(${product.id})">
							<td>${product.name}</td>
							<td>${product.code}</td>
							<td>${product.brand.name}</td>
							<td>${product.description}</td>
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
<div class="modal fade" id="view-modal" role="dialog">
	<div class="modal-dialog">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" onclick="clear()">�</button>
				<h4 class="modal-title">View Product</h4>
			</div>
			<div class="modal-body">
				<div class="row">
				<div class="input-group">
						<label class="input-group-addon">Code</label> <input type="text"
							id="name" readonly class="form-control " />
					</div>
					<div class="input-group">
						<label class="input-group-addon">Name</label> <input type="text"
							id="name" readonly class="form-control " />
					</div>
					<div class="input-group">
						<label class="input-group-addon">Description</label> <input type="text"
							id="mobile" readonly class="form-control " />
					</div>
					<div class="input-group">
						<label class="input-group-addon">Email</label> <input type="text"
							id="email" readonly class="form-control " />
					</div>
					<div class="input-group">
						<label class="input-group-addon">User Name</label> <input
							type="text" id="user-name" readonly class="form-control " />
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
				"aTargets" : [ 0, 2, 3 ]
			}, {
				"bSearchable" : false,
				"aTargets" : [ 0 ]
			} ],
			"scrollX" : true
		});
	});

	//To view single customer
	function singleView(id) {
		console.log(products.result);
		$.each(products.result, function(i, product){
			if(product.id==id){
				alert(product.name);
				//$("#name").val(cus.user.name);
				//$("#mobile").val(cus.user.mobile);
				//$("#email").val(cus.user.email);
				//$("#user-name").val(cus.user.userName);
			}
		});
		$('#view-modal').modal({
			backdrop : 'static',
			keyboard : false
		});
		$("#view-modal").modal("show");
	}
</script>
</script>