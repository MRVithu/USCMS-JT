<!-- /** -->
<!--  * @author M.Vithusanth -->
<!--  * @CreatedOn 23rd April 2018 -->
<!--  * @Purpose for Department Page  -->
<!--  */ -->

<%@page import="com.vithu.uscms.entities.Department"%>
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
						<th>Type</th>
						<th>Description</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${departments.result}" var="department">
						<tr onclick="singleView(${department.id})">
							<td>${department.name}</td>
							<td>${department.type}</td>
							<td>${department.description}</td>
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
				<h4 class="modal-title">View Bank</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="input-group">
						<label class="input-group-addon">Name</label> <input type="text"
							id="name" readonly class="form-control " />
					</div>
					<div class="input-group">
						<label class="input-group-addon">Type</label> <input type="text"
							id="type" readonly class="form-control " />
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
	var departments = "";
	departments = ${departments.resultString};

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

	//To view single department
	function singleView(id) {
		console.log(departments.result);
		$.each(departments.result, function(i, department){
			if(department.id==id){
				$("#name").val(department.name);
				$("#type").val(department.type);
				$("#description").val(department.description);
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