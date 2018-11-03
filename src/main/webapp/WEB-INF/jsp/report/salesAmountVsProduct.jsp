
<%@page import="com.vithu.uscms.entities.Sales"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ include file="../../layouts/taglib.jsp"%>



<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
<!-- Content Header (Page header) -->
	<section class="content-header">
		<div class="input-group">
			<label class="input-group-addon ">Brand</label> <select id="brand" onchange="displayTableBody()"
				class="form-control type" name="role">
				<option value="0">All</option>
				<c:forEach items="${brands.result}" var="brand">
					<option value="${brand.id}">${brand.name}</option>
				</c:forEach>
			</select>
			
			<label class="input-group-addon ">Item Type</label> <select id="item-type" onchange="displayTableBody()"
				class="form-control type" name="role">
				<option value="0">All</option>
				<c:forEach items="${itemTypes.result}" var="itemType">
					<option value="${itemType.id}">${itemType.name}</option>
				</c:forEach>
			</select>
		</div>
	</section>

	<!-- Main content -->
	<section class="content">
		<div class="box box-body">
			<table id="employeeTable" class="table table-bordered table-striped">
				<thead>
					<tr>
						<th>Product</th>
						<th>Qty</th>
						<th>Sales</th>
					</tr>
				</thead>

				<tbody id="content-body">
					
				</tbody>
			</table>
		</div>

	</section>
</div>

<script
	src="<c:url value=" /resources/plugins/datatables/jquery.dataTables.min.js" />"></script>
<script
	src="<c:url value="/resources/plugins/datatables/dataTables.bootstrap.min.js" />"></script>

<script>
	var salesAmountVsProduct = "";
	salesAmountVsProduct = ${salesAmountVsProduct.resultString};
	console.log(salesAmountVsProduct);
	
	var itemTypes = "";
	itemTypes = ${itemTypes.resultString};
	console.log(itemTypes);
	var brands = "";
	brands = ${brands.resultString};
	console.log(brands);
	
	function displayTableBody(){
		$("#content-body").empty();
		var brand = $("#brand").val();
		var itemType = $("#item-type").val();
		if(brand == 0 && itemType == 0){
			$.each(salesAmountVsProduct.result, function(i, sp) {
				var htmlStr = "<tr> ";
					htmlStr += "<td>"  + sp.product.name+ "</td>";
					htmlStr += "<td style='text-align: right;'>"  + sp.qty+ "</td>";
					htmlStr += "<td style='text-align: right;'>"  + formatCurrency(sp.amount)+ "</td>";
					htmlStr += "</tr>";
					$("#content-body").append(htmlStr);
			});
		}
		else if(brand == 0 && itemType != 0){	
			$.each(salesAmountVsProduct.result, function(i, sp) {
			console.log(sp.itemType.id +"--"+itemType);
				if(sp.itemType.id == itemType ){
		
					var htmlStr = "<tr> ";
						htmlStr += "<td>"  + sp.product.name+ "</td>";
						htmlStr += "<td style='text-align: right;'>"  + sp.qty+ "</td>";
						htmlStr += "<td style='text-align: right;'>"  + formatCurrency(sp.amount)+ "</td>";
						htmlStr += "</tr>";
						$("#content-body").append(htmlStr);
				}

			});
		}
		else if(brand != 0 && itemType == 0){
			$.each(salesAmountVsProduct.result, function(i, sp) {
			console.log(sp.brand.id +"--"+brand);
				if(sp.brand.id == brand){
					var htmlStr = "<tr> ";
						htmlStr += "<td>"  + sp.product.name+ "</td>";
						htmlStr += "<td style='text-align: right;'>"  + sp.qty+ "</td>";
						htmlStr += "<td style='text-align: right;'>"  + formatCurrency(sp.amount)+ "</td>";
						htmlStr += "</tr>";
						$("#content-body").append(htmlStr);
				}

			});
		}
		else{
			$.each(salesAmountVsProduct.result, function(i, sp) {
			
			console.log(sp.itemType.id +"--"+itemType+"**"+sp.brand.id +"--"+brand);
				if(sp.brand.id == brand && sp.itemType.id == itemType){
					var htmlStr = "<tr> ";
						htmlStr += "<td>"  + sp.product.name+ "</td>";
						htmlStr += "<td style='text-align: right;'>"  + sp.qty+ "</td>";
						htmlStr += "<td style='text-align: right;'>"  + formatCurrency(sp.amount)+ "</td>";
						htmlStr += "</tr>";
						$("#content-body").append(htmlStr);
				}
			});
		}
		
			
			
	}
	
	$(document).ready(function(){	
		displayTableBody();
		
		$('#employeeTable').DataTable({
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
