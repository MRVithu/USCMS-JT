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
	<section class="content-header">
		<button id="btn-add" id="addProduct" class="btn btn-success">Add
			New Item Type</button>
	</section>

	<!-- Main content -->
	<section class="content">
		<div class="box box-body">
			<table id="user-table" style="cursor: pointer;"
				class="table table-condensed table-bordered table-hover table-striped table-pad">
				<thead>
					<tr>
						<th style="width: 50px;"></th>
						<th>Name</th>
						<th>Product Type</th>
						<th>Description</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${itemTypes.result}" var="itemType">
						<tr>
							<td style="width: 50px;"><a
								onclick="updateItemType(${itemType.id})"> <i
									class="fa fa-pencil-square-o"></i>
							</a> <a class=" text-danger" onclick="deleteItemType(${itemType.id})">
									<i class="fa fa-trash-o"></i>
							</a></td>
							<td onclick="singleView(${itemType.id})">${itemType.name}</td>
							<td onclick="singleView(${itemType.id})">${itemType.proType.name}</td>
							<td onclick="singleView(${itemType.id})">${itemType.description}</td>
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
			<form id="ItemTypeFrom" onsubmit="return update(this);">
				<div class="modal-header">
					<button type="button" class="close" onclick="clear()">×</button>
					<h4 class="modal-title">View Brand</h4>

				</div>
				<div class="modal-body">
					<div class="row">
						<input type="hidden" id="id" />
						<div class="input-group">
							<label class="input-group-addon">Name</label> <input type="text"
								id="name" name="name" class="form-control " />
						</div>
						<div class="input-group">
							<label class="input-group-addon ">Pro Type</label> <select
								id="pro-type" class="form-control type" name="pro-type">
								<!-- Dropdown List Option -->
								<c:forEach items="${proTypes.result}" var="proType">
									<option value="${proType.id}">${proType.name}</option>
								</c:forEach>
							</select>
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
		$("#reset-btn").hide();
		$("#submit-btn").hide();
		$("#modal").modal("show");
	}
	

	function deleteItemType(id){
		$("#del-item-id").val(id);
		$("#del-modal").modal("show");
	}

	//Function for delete existing product
	function confirmDelelte(){
		var id = $("#del-item-id").val();
		try{
			$.ajax({
		        url:'http://localhost:8080/deleteItemType/'+ id +'.json?token=<%=session.getAttribute("Token")%>',
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
	
	//Function for add new item type
	$("#btn-add").on("click",function(){
		method="addItemType.json";
		clear();
		$("#code").val("P");
		$(".modal-title").html("Add Item Type");
		$(".form-control").prop("readonly", false);
		$(".form-control").prop("disabled", false);
		$("#modal").modal({backdrop: 'static', keyboard: false});
		$("#reset-btn").show();
		$("#submit-btn").show();
		$("#modal").modal("show");
	});
	
	//Function for update item type
	function updateItemType(id){
		method = "updateItemType.json";
		$(".form-control").prop("readonly", false);
		$(".form-control").prop("disabled", false);
  		fillDataToModal(id);
  		$(".modal-title").html("Edit Item Type");
  		$("#id").val(id);
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
				alertMessage("Item type name can not be empty", "error");
				
			}else{
				var itemType = {};
				itemType.id=$("#id").val();
				itemType.name=$("#name").val();
				itemType.description=$("#description").val();
				itemType.proType=$("#pro-type").val();
				//alert(itemType.id);
				$.ajax({
					
					type: 'POST',
					url: 'http://localhost:8080/'+method+'?token=<%=session.getAttribute("Token")%>',
					data: {data:JSON.stringify(itemType)},
					success: function(res) {
						console.log(res);
						console.log(res.status);
						
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
	
	
	//Function for clear all fields in all modals.
	function clear(){
		$("#name").val("");
		$("#description").val("");
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