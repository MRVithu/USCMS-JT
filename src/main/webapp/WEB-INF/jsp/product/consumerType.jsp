<!-- /** -->
<!--  * @author M.Vithusanth -->
<!--  * @CreatedOn 21th April 2018 -->
<!--  * @Purpose Consumer type Page  -->
<!--  */ -->

<%@page import="com.vithu.uscms.entities.ConsumerType"%>
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
					<c:forEach items="${consumerTypes.result}" var="consumerType">
						<tr onclick="singleView(${consumerType.id})">
							<td>${consumerType.name}</td>
							<td>${consumerType.description}</td>
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
				<h4 class="modal-title">View Customer</h4>
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
	var consumerTypes = "";
	consumerTypes = ${consumerTypes.resultString};

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

	//To view single customer
	function singleView(id) {
		console.log(consumerTypes.result);
		$.each(consumerTypes.result, function(i, consumerType){
			if(consumerType.id==id){
				//alert(cus.user.name);
				$("#name").val(consumerType.name);
				$("#description").val(consumerType.description);
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