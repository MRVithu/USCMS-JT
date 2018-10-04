<!-- /** -->
<!--  * @author M.Vithusanth -->
<!--  * @CreatedOn 21th April 2018 -->
<!--  * @Purpose Brand Page  -->
<!--  */ -->

<%@page import="com.vithu.uscms.entities.Brand"%>
<%@page import="java.util.List"%>
<%@page import="com.vithu.uscms.others.GenericResult"%>
<%@ include file="../../layouts/taglib.jsp"%>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header"><button id="btn-add" id="addProduct" class="btn btn-success">Add
			New Brand</button></section>

	<!-- Main content -->
	<section class="content">
		<div class="box box-body">
			<table id="user-table"  style="cursor:pointer;"
				class="table table-condensed table-bordered table-hover table-striped table-pad">
				<thead>
					<tr>
						<th style="width: 50px;"></th>
						<th>Name</th>
						<th>Description</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${brands.result}" var="brand">
						<tr>
							<td style="width: 50px;"><a
								onclick="updateBrand(${brand.id})"> <i
									class="fa fa-pencil-square-o"></i>
							</a> <a class=" text-danger" onclick="deleteBrand(${brand.id})">
									<i class="fa fa-trash-o"></i>
							</a></td>
							<td  onclick="singleView(${brand.id})">${brand.name}</td>
							<td  onclick="singleView(${brand.id})">${brand.description}</td>
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
			<form id="productFrom" onsubmit="return update(this);">
				<div class="modal-header">
					<button type="button" class="close" onclick="clear()">×</button>
					<h4 class="modal-title">View Brand</h4>
					
				</div>
				<div class="modal-body">
					<div class="row">
						<input type="hidden" id="pro-id" />
						<div class="input-group">
							<label class="input-group-addon">Name</label> <input type="text"
								id="name" name="name" class="form-control " />
						</div>
						<div class="input-group">
							<label class="input-group-addon">Description</label> <input
								type="text" id="description" name="description"
								class="form-control " />
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<input id="reset-btn" type="reset"
						class="btn btn-primary alert-warning" style="float: left;"
						onclick="clear()" value="Reset" /> <input id="close-btn"
						type="button" class="btn btn-primary alert-info"
						onclick="clear();$('#modal').modal('hide')" value="Close" /> <input
						id="submit-btn" type="submit"
						class="btn btn-primary alert-success" value="Save">
				</div>
			</form>
		</div>
	</div>
</div>


<!-- REQUIRED JS SCRIPTS -->
<script
	src="<c:url value=" /resources/plugins/datatables/jquery.dataTables.min.js" />"></script>
<script
	src="<c:url value="/resources/plugins/datatables/dataTables.bootstrap.min.js" />"></script>

<script>
	var brands = "";
	brands = ${brands.resultString};

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

	//To view single brand
	function singleView(id) {
		console.log(brands.result);
		$.each(brands.result, function(i, brand){
			if(brand.id==id){
				$("#name").val(brand.name);
				$("#description").val(brand.description);
			}
		});
		$('#view-modal').modal({
			backdrop : 'static',
			keyboard : false
		});
		$("#reset-btn").hide();
		$("#submit-btn").hide();
		$("#modal").modal("show");
	}
	
	function deleteBrand(id){
		$("#del-item-id").val(id);
		$("#del-modal").modal("show");
	}

	//Function for delete existing product
	function confirmDelelte(){
		var id = $("#del-item-id").val();
		try{
			$.ajax({
		        url:'http://localhost:8080/deleteBrand/'+ id +'.json?token=<%=session.getAttribute("Token")%>',
		        type: 'POST',
		        data: { id:id },
		        success: function (res) {
		        	console.log(res );
		        	
		        	if (res.status == false) {
						alertMessage(res.description, 'error');
					} else if (res.status == true) {
						alertMessage(res.description, 'success');
					}

		            setTimeout(function() {
						if (res.status == true){
							window.location.reload(true);
						}
					}, 600);
				},
		        error: function (res) {
		            alert("res");
		        }
		    });
		}
		catch(err){
			alert(err);
		}
		return false;
	}
	
	//Function for add new product
	$("#btn-add").on("click",function(){
		method="addBrand.json";
		clear();
		$("#code").val("P");
		$(".modal-title").html("Add Product");
		$(".form-control").prop("readonly", false);
		$(".form-control").prop("disabled", false);
		$("#modal").modal({backdrop: 'static', keyboard: false});
		$("#reset-btn").show();
		$("#submit-btn").show();
		$("#modal").modal("show");
	});
	
	//Function for update vehicle
	function updateBrand(id){
		method = "updateBrand.json";
		$(".form-control").prop("readonly", false);
		$(".form-control").prop("disabled", false);
  		fillDataToModal(id);
  		$(".modal-title").html("Edit Product");
  		$("#pro-id").val(id);
  		$("#modal").modal({backdrop: 'static', keyboard: false});
		$("#reset-btn").show();
		$("#submit-btn").show();
  		$("#modal").modal('show');
  	}

	//Function for submit all data. 
	function update(form){
		try
		{
			if ($("#name").val().trim() == ""){
				alertMessage("Brand name can not be empty", "error");
				
			}else{
				var brand = {};
				brand.id=$("#pro-id").val();
				brand.name=$("#name").val();
				brand.description=$("#description").val();
				
				$.ajax({
					
					type: 'POST',
					url: 'http://localhost:8080/'+method+'?token=<%=session.getAttribute("Token")%>',
					data: {data:JSON.stringify(brand)},
					success: function(res) {
						console.log(res);
						console.log(res.status);
						$("#res-msg strong").html(res.msg);
						
						if (res.status == false) {
							alertMessage(res.description, 'error');
						} else if (res.status == true) {
							alertMessage(res.description, 'success');
						}
	
						setTimeout(function() {
							if (res.message == "SUCCESS"){
								$("#modal").modal("hide");
								window.location.reload(true);
							}
						}, 1000);
					},
					error: function(xhr, textStatus, errorThrown) {
						console.log(xhr);
						alert("error");
						console.log(errorThrown);
					}
				});
			}
		}
		catch(err){
			alert(err);
		}
	return false;
	}
	
	//Fill data in modal.
	function fillDataToModal(id){
		console.log(brands.result);
		$.each(brands.result, function(i, brand){
			if(brand.id==id){
				$("#name").val(brand.name);
				$("#description").val(brand.description);
				
			}
		});
	}
	
	//Function for clear all fields in all modals.
	function clear(){
		$("#name").val("");
		$("#description").val("");
  	}
</script>