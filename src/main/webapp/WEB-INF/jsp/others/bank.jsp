<!-- /** -->
<!--  * @author M.Vithusanth -->
<!--  * @CreatedOn 23rd April 2018 -->
<!--  * @Purpose Bank Page  -->
<!--  */ -->

<%@page import="com.vithu.uscms.entities.Bank"%>
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
						<th>Acc Name</th>
						<th>Number</th>
						<th>Bank Name</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${banks.result}" var="bank">
						<tr onclick="singleView(${bank.id})">
							<td>${bank.name}</td>
							<td>${bank.number}</td>
							<td>${bank.bankName}</td>
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
						<label class="input-group-addon">Acc Name</label> <input type="text"
							id="acc-name" readonly class="form-control " />
					</div>
					<div class="input-group">
						<label class="input-group-addon">Number</label> <input type="text"
							id="number" readonly class="form-control " />
					</div>
					<div class="input-group">
						<label class="input-group-addon">Bank Name</label> <input type="text"
							id="bank-name" readonly class="form-control " />
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
	var banks = "";
	banks = ${banks.resultString};

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

	//To view single bank acc
	function singleView(id) {
		console.log(banks.result);
		$.each(banks.result, function(i, banks){
			if(banks.id==id){
				$("#acc-name").val(banks.name);
				$("#number").val(banks.number);
				$("#bank-name").val(banks.bankName);
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