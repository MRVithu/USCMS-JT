<!-- /** -->
<!--  * @author M.Vithusanth->
<!--  * @CreatedOn 20 april 2018 -->
<!--  * @Purpose Employee Page  -->
<!--  */ -->

<%@page import="com.vithu.uscms.entities.Employee"%>
<%@page import="com.vithu.uscms.entities.UserRole"%>
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
			<table id="employeeTable" class="table table-bordered table-striped">
				<thead>
					<tr>
						<th style="width: 30px;"></th>
						<th>Name</th>
						<th>Mobile</th>
						<th>Role</th>
						<th>Region</th>
					</tr>
				</thead>

				<tbody>
					<c:forEach items="${employees.result}" var="emp">
						<tr onclick="">
							<td style="width: 30px;"><a class=""
								onclick="updateEmployee(${emp.user.id})"> <i
									class="fa fa-pencil-square-o"></i>
							</a> <a class=" text-danger"
								onclick="deleteEmployee(${emp.user.id})"> <i
									class="fa fa-trash-o"></i>
							</a></td>
							<td onclick="singleView(${emp.user.id})">${emp.user.name}</td>
							<td onclick="singleView(${emp.user.id})">${emp.user.mobile}</td>
							<td onclick="singleView(${emp.user.id})">${emp.role}</td>
							<td onclick="singleView(${emp.user.id})">${emp.address}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

	</section>
</div>


<!-- modal -->
<div class="modal fade" id="modal" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<form id="ItemTypeFrom" onsubmit="return update(this);">
				<div class="modal-header">
					<button type="button" class="close" onclick="clear()">×</button>
					<h4 class="modal-title">View Employee</h4>

				</div>
				<div class="modal-body">
					<div class="row">
						<input type="hidden" id="u-id" />
						<input type="hidden" id="e-id" />
						<div class="input-group">
							<label class="input-group-addon">Name</label> <input type="text"
								id="name" name="name" class="form-control " />
						</div>
						<div class="input-group">
							<label class="input-group-addon">User Name</label> <input
								type="text" id="user-name" name="user-name"
								class="form-control " />
						</div>
						<div class="input-group">
							<label class="input-group-addon">Mobile</label> <input
								type="number" id="mobile" name="mobile" class="form-control " />
						</div>
						<div class="input-group">
							<label class="input-group-addon">NIC</label> <input type="text"
								id="nic" name="nic"  class="form-control " />
						</div>
						<div class="input-group">
							<label class="input-group-addon">Mail</label> <input
								type="text" id="mail" name="mail" class="form-control " />
						</div>
						<div class="input-group">
							<label class="input-group-addon">Contact</label> <input
								type="number" id="contact" name="contact" class="form-control " />
						</div>
						<div class="input-group">
							<label class="input-group-addon">Date of Birth</label> <input
								type="date" id="dob" name="dob" class="form-control " />
						</div>
						<div class="input-group">
							<label class="input-group-addon ">Role</label> <select id="role"
								class="form-control type" name="role">
								<c:forEach items="${role.result}" var="role">
									<option value="${role.id}">${role.name}</option>
								</c:forEach>
							</select>
						</div>
						<div class="input-group">
							<label class="input-group-addon">Address</label> <input
								type="textarea" id="address" name="description"
								class="form-control " />
						</div>
						<div class="input-group ">
							<label class="input-group-addon">Password</label> <input
								type="text" id="pwd" name="pwd" class="form-control pwd" />
						</div>
						<div class="input-group">
							<label class="input-group-addon">Con Password</label> <input
								type="text" id="con-pwd" name="con-pwd" class="form-control pwd" />
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





<script
	src="<c:url value=" /resources/plugins/datatables/jquery.dataTables.min.js" />"></script>
<script
	src="<c:url value="/resources/plugins/datatables/dataTables.bootstrap.min.js" />"></script>

<script>
	var employees = "";
	employees = ${employees.resultString};
	console.log(employees);

	$(document).ready(function() {		
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
	
	//To view single employee
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
	
	//Function for add new employee
	$("#btn-add").on("click",function(){
		method="addEmployee.json";
		clear();
		$(".modal-title").html("Add New Employee");
		$(".form-control").prop("readonly", false);
		$(".form-control").prop("disabled", false);
		$("#modal").modal({backdrop: 'static', keyboard: false});
		$("#reset-btn").show();
		$("#submit-btn").show();
		$("#modal").modal("show");
	});
	
	//Function for update employee
	function updateEmployee(id){
		method = "updateEmployee.json";
		$(".form-control").prop("readonly", false);
		$(".form-control").prop("disabled", false);
  		fillDataToModal(id);
  		$(".modal-title").html("Edit Employee");
  		$("#id").val(id);
  		$("#modal").modal({backdrop: 'static', keyboard: false});
		$("#reset-btn").show();
		$("#submit-btn").show();
  		$("#modal").modal('show');
  	}
		
	function deleteEmployee(id){
		$("#del-item-id").val(id);
		$("#del-modal").modal("show");
	}

	//Function for delete existing product
	function confirmDelelte(){
		var id = $("#del-item-id").val();
		try{
			$.ajax({
		        url:'http://localhost:8080/deleteEmployee/'+ id +'.json?token=<%=session.getAttribute("Token")%>',
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
	
	//Function for submit all data. 
	function update(form) {
		var emp = {};
		emp.uid = $("#u-id").val();
		emp.eid = $("#e-id").val();
		emp.userName = $("#user-name").val();
		emp.name = $("#name").val();
		emp.mobile = $("#mobile").val();
		emp.nic = $("#nic").val();
		emp.email = $("#mail").val();
		if($("#contact").val() == "" ){
			emp.contact = 0;
		}
		else{
			emp.contact = $("#contact").val();
		}
		
		emp.dob = $("#dob").val();
		emp.role = $("#role").val();
		emp.address = $("#address").val();
		emp.password = $("#pwd").val();
		console.log(emp);
		
		try
		{
			if (emp.userName.trim() == "") {
				alertMessage("User name can not be empty", 'error');
			} else if (emp.password.trim() == "") {
				alertMessage("User password can not be empty", 'error');
			}else{
				$.ajax({
					
					type: 'POST',
					url: 'http://localhost:8080/'+method+'?token=<%=session.getAttribute("Token")%>',
					data: {data:JSON.stringify(emp)},
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

	

	//Fill data in modal.
	function fillDataToModal(empId) {
		$.each(employees.result, function(i, employee) {
			if (employee.user.id == empId) {
				$("#u-id").val(employee.user.id);
				$("#e-id").val(employee.id);
				$("#name").val(employee.user.name);
				$("#user-name").val(employee.user.userName);
				$("#mobile").val(employee.user.mobile);
				$("#dob").val(employee.dob);
				$("#nic").val(employee.nic);
				$("#mail").val(employee.user.email);
				$("#contact").val(employee.contact);
				$("#address").val(employee.address);
			}
		});
	}

	
	//Function for clear all fields in all modals.
	function clear() {
		$("#name").val("");
		$("#u-id").val("");
		$("#e-id").val("");
		$("#user-name").val("");
		$("#mobile").val(" ");
		$("#contact").val("");
		$("#nic").val("");
		$("#role").val("");
		$("#address").val("");
		$("#dob").val("");
		$("#mail").val("");
		$("#pwd").val("");
		$("#con-pwd").val("");
	}

	// validations
	$("#empConfirmPassword").on("change", function() {
		if ($("#empConfirmPassword").val() == $("#empPassword").val()) {
			$("#submitBtn").attr("disabled", false);
		} else {
			alertMessage(danger);
			$("#resMsg span").html("Password mis match!");
			$("#empPassword").val(" ");
			$("#empConfirmPassword").val(" ");
			$('#empConfirmPassword').focus();
		}
	});
	
	//Confirm password
	$(".pwd").on("keyup", function(){
	       pwd = $("#pwd").val();
	       conPwd = $("#con-pwd").val();
	       if(pwd == conPwd){
	            $('#con-pwd').css('border-color', 'green');
	            $('#submit-btn').prop("disabled",false);
	       }
	       else {
	            $('#con-pwd').css('border-color', 'red');
	            $('#submit-btn').prop("disabled",true);
	       }
	  });

</script>