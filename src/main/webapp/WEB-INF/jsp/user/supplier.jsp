<!-- /** -->
<!--  * @author M.Vithusanth -->
<!--  * @CreatedOn 23rd April 2018 -->
<!--  * @Purpose Customer Page  -->
<!--  */ -->

<%@page import="com.vithu.uscms.entities.Supplier"%>
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
						<th>Email</th>
						<th>Mobile</th>
						<th>User Name</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${suppliers.result}" var="supplier">
						<tr onclick="singleView(${supplier.user.id})">
							<td>${supplier.user.name}</td>
							<td>${supplier.user.email}</td>
							<td>${supplier.user.mobile}</td>
							<td>${supplier.user.userName}</td>
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
				<button type="button" class="close" onclick="clear()">×</button>
				<h4 class="modal-title">View Customer</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="input-group">
						<label class="input-group-addon">Name</label> <input type="text"
							id="name" readonly class="form-control " />
					</div>
					<div class="input-group">
						<label class="input-group-addon">Mobile</label> <input type="text"
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
	var suppliers = "";
	if(${suppliers.resultString} != ""){
		suppliers = ${suppliers.resultString};
	}
	

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
		console.log(suppliers.result);
		$.each(suppliers.result, function(i, supplier){
			if(supplier.user.id==id){
				//alert(supplier.user.name);
				$("#name").val(supplier.user.name);
				$("#mobile").val(supplier.user.mobile);
				$("#email").val(supplier.user.email);
				$("#user-name").val(supplier.user.userName);
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