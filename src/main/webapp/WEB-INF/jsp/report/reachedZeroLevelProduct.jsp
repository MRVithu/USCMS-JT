<!-- /** -->
<!--  * @author M.Vithusanth -->
<!--  * @CreatedOn 21th April 2018 -->
<!--  * @Purpose Brand Page  -->
<!--  */ -->

<%@page import="com.vithu.uscms.entities.Report"%>
<%@page import="com.vithu.uscms.entities.Brand"%>
<%@page import="com.vithu.uscms.entities.Product"%>
<%@page import="java.util.List"%>
<%@page import="com.vithu.uscms.others.GenericResult"%>
<%@ include file="../../layouts/taglib.jsp"%>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	

	<!-- Main content -->
	<section class="content">
		<div class="box box-body">
			<table id="user-table"  style="cursor:pointer;"
				class="table table-condensed table-bordered table-hover table-striped table-pad">
				<thead>
					<tr>
						<th>Pro Code</th>
						<th>Pro Name</th>
						<th>Pro Brand</th>
						<th>Reorder level</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${reachedZeroLevelProduct.result}" var="re">
						<tr>
							<td>${re.product.code}</td>
							<td>${re.product.name}</td>
							<td>${re.product.brand.name}</td>
							<td>${re.product.reOrderQty}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
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
	var reachedZeroLevelProduct = "";
	reachedZeroLevelProduct = ${reachedZeroLevelProduct.resultString};
	console.log(reachedZeroLevelProduct);
	
	
	
	$(document).ready(function(){	
		
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
</script>