<!-- /** -->
<!--  * @author M.Vithusanth -->
<!--  * @CreatedOn 21th April 2018 -->
<!--  * @Purpose Item Type Page  -->
<!--  */ -->

<%@page import="com.vithu.uscms.entities.ItemType"%>
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
			<table id="user-table" style="cursor: pointer;"
				class="table table-condensed table-bordered table-hover table-striped table-pad">
				<thead>
					<tr>
						<th>Name</th>
						<th>Product Type</th>
						<th>Description</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${itemTypes.result}" var="itemType">
						<tr onclick="singleView(${itemType.id})">
							<td>${itemType.name}</td>
							<td>${itemType.proType.name}</td>
							<td>${itemType.description}</td>
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
				<h4 class="modal-title">View Item Type</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="input-group">
						<label class="input-group-addon">Name</label> <input type="text"
							id="name" readonly class="form-control " />
					</div>
					<div class="input-group">
						<label class="input-group-addon ">Pro Type</label> <select
							id="pro-type" class="form-control type" name="pro-type">
							<%-- <!-- Dropdown List Option -->
							<c:forEach items="${brands.result}" var="brand">
								<option value="${brand.id}">${brand.name}</option>
							</c:forEach> --%>
						</select>
					</div>
					<div class="input-group">
						<label class="input-group-addon">Dep</label> <input type="text"
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
	var itemTypes = "";
	itemTypes = ${itemTypes.resultString};

	//Data table
	$(function() {
		$('#user-table').DataTable({
			"aoColumnDefs" : [ {
				"bSortable" : false,
				"aTargets" : [ 0]
			}, {
				"bSearchable" : false,
				"aTargets" : [ 0 ]
			} ],
			"scrollX" : true
		});
	});

	//To view single item type
		function singleView(id) {
		fillDataToModal(id);
		$(".modal-title").html("View Item Type");
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
		console.log(itemTypes.result);
		$.each(itemTypes.result, function(i, itemType){
			if(itemType.id==id){
				
				$("#name").val(itemType.name);
				$("#description").val(itemType.description);
				$("#pro-type").prepend("<option>"+itemType.proType.name+"</option>");
			}
		});
	}
	
</script>