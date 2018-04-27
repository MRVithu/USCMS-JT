<!-- /** -->
<!--  * @author M.Vithusanth->
<!--  * @CreatedOn 20 april 2018 -->
<!--  * @Purpose Employee Page  -->
<!--  */ -->

<%@page import="com.vithu.uscms.entities.Employee"%>
<%@page import="com.vithu.uscms.entities.Region"%>
<%@page import="com.vithu.uscms.entities.UserRole"%>
<%@ include file="../../layouts/taglib.jsp"%>


<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<button data-toggle="modal" data-target="#add-edit-employee"
			id="addEmployee" class="btn btn-success">Add New Employee</button>
		<div class="box">
			<div class="box-header">
				<div class="" id="main-res-msg"
					style="margin-top: 5px; display: none;">
					<strong></strong>
				</div>
			</div>
			<!-- /.box-header -->
			<div class="box-body">
				<table id="employeeTable" class="table table-bordered table-striped">
					<thead>
						<tr>
							<th style="width:30px;"></th>
							<th>Name</th>
							<th>Mobile</th>
							<th>Role</th>
							<th>Region</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach items="${employees.result}" var="emp">
							<tr onclick="">
								<td style="width:30px;"><a class="" onclick="editEmployee(${emp.user.id})">
										<i class="fa fa-pencil-square-o"></i>
								</a> <a class=" text-danger"
									onclick="deleteUserModal(${emp.user.id})"> <i
										class="fa fa-trash-o"></i>
								</a></td>
								<td onclick="singleView(${emp.user.id})">${emp.user.name}</td>
								<td onclick="singleView(${emp.user.id})">${emp.user.mobile}</td>
								<td onclick="singleView(${emp.user.id})">${emp.role}</td>
								<td onclick="singleView(${emp.user.id})">${emp.region}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<!-- /.box-body -->
		</div>
		<!-- /.box -->
	</section>
</div>

<!-- add new employee Modal -->
<div class="modal fade" id="add-edit-employee" role="dialog">
	<div class="modal-dialog">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">Add New Employee</h4>
				<div class="alert alert-info" id="res-msg" style="margin-top: 5px;">
					<strong>Fill all the fields and hit Save</strong>
				</div>
			</div>
			<div class="modal-body" style="margin-top: -30px;">
				<form method="POST" id="empForm">
					<input type="hidden" id="userId" name="id" /> <input type="hidden"
						id="employeeId" name="id" />

					<div class="row">
						<div class="col-md-12">
							<label for="Address">User Name:</label> <input type="text"
								class="form-control" id="empUserName" name="userName"
								placeholder="User Name" autofocus />
						</div>
					</div>

					<br />
					<div class="row">
						<div class="form-group col-md-6">
							<label for="Name">Name:</label> <input class="form-control"
								id="empName" name="name" type="text" placeholder="Name" />
						</div>

						<div class="form-group col-md-6">
							<label for="Mobile">Mobile:</label> <input class="form-control"
								id="empMobile" name="mobile" type="number" placeholder="Mobile" />
						</div>
					</div>

					<div class="row">
						<div class="form-group col-md-6">
							<label for="NIC">NIC:</label> <input class="form-control"
								id="empNic" type="text" name="nic" placeholder="NIC" />
						</div>

						<div class="form-group col-md-6">
							<label for="Email">Email:</label> <input class="form-control"
								id="empEmail" type="text" name="email" placeholder="Email" />
						</div>
					</div>

					<div class="row">
						<div class="form-group col-md-6">
							<label for="Contact">Contact:</label> <input class="form-control"
								id="empContact" type="number" name="contact"
								placeholder="Contact" />
						</div>

						<div class="form-group col-md-6">
							<label for="Dob">Date of Birth:</label> <input type="date"
								id="empDob" class="form-control" name="dob"
								placeholder="Date of birth" />
						</div>
					</div>

					<div class="row">
						<div class="form-group col-md-6">
							<label for="Role">Role:</label> <select class="form-control"
								id="empRole" name="role">
								<c:forEach items="${role.result}" var="role">
									<option value="${role.id}">${role.name}</option>
								</c:forEach>
							</select>
						</div>

						<div class="form-group col-md-6">
							<label for="Region">Region:</label> <select class="form-control"
								id="empRegion-id" name="region">
								<c:forEach items="${region.result}" var="region">
									<option value="${region.id}">${region.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>

					<div class="row">
						<div class="col-md-12">
							<label for="Address">Address:</label>
							<textarea class="form-control" row="3" id="empAddress"
								" name="address" placeholder="address"></textarea>
						</div>
					</div>
					<br />
					<div class="row" id="passwordRow">
						<div class="form-group col-md-6">
							<label id="hide-pwd" for="Password">Password:</label> <input
								class="form-control" id="empPassword" name="password"
								type="password" placeholder="Password"
								onmouseenter="$(this).attr('type', 'text');"
								onmouseleave="$(this).attr('type', 'password');" />
						</div>

						<div class="form-group col-md-6">
							<label id="ConfirmPassword" for="ConfirmPassword">Confirm
								Password:</label> <input class="form-control" id="empConfirmPassword"
								name="confirmPassword" type="password"
								placeholder="Confirm Password" />
						</div>
					</div>

					<br />
					<div class="row">
						<div class="form-group col-md-6 viewSingleHide">
							<button type="button" class="btn btn-lg btn-block btn-info"
								id="submitBtn" value="Submit" disabled="disabled"
								onclick="submitEmployee()">Submit</button>
						</div>
						<div class="form-group col-md-3 viewSingleHide">
							<button type="reset" class="btn btn-lg btn-block btn-secondary"
								onclick="clear()">Reset</button>
						</div>
						<div class="form-group col-md-3" id="singleViewQuit">
							<button type="button" id="restToOk"
								class="btn btn-lg btn-block btn-secondary"
								onclick="singleViewQuit()">close</button>
						</div>
					</div>
				</form>
			</div>
		</div>

	</div>
</div>


<script
	src="<c:url value=" /resources/plugins/datatables/jquery.dataTables.min.js" />"></script>
<script
	src="<c:url value="/resources/plugins/datatables/dataTables.bootstrap.min.js" />"></script>

<script>
	var success = "success";
	var danger = "danger";
	var info = "info";
	var submitOrupdate = "";
	var employees = ${employees.resultString};

	$(document).ready(function() {
		submitOrupdate = "addEmployee";

		$("#userId").val(0);
		
		
		
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

	function deleteUserModal(uid) {
		$("#dleUser").attr("onclick", "deleteUser(" + uid + ")");
		$("#conMsg").modal('show');
	}

	function submitEmployee() {
		var emp = {};
		emp.id = $("#userId").val();
		emp.eid = $("#employeeId").val();
		emp.userName = $("#empUserName").val();
		emp.name = $("#empName").val();
		emp.mobile = $("#empMobile").val();
		emp.nic = $("#empNic").val();
		emp.email = $("#empEmail").val();
		emp.contact = $("#empContact").val();
		emp.dob = $("#empDob").val();
		emp.role = $("#empRole").val();
		emp.region = $("#empRegion-id").val();
		emp.address = $("#empAddress").val();
		emp.password = $("#empPassword").val();
		console.log(emp);
		if (emp.userName.trim() == "") {
			$("#res-msg").removeClass("alert-success").removeClass("alert-info").addClass("alert-danger");
			$("#res-msg strong").html("user name can not be empty");
		} else if (emp.password.trim() == "") {
			$("#res-msg").removeClass("alert-success").removeClass("alert-info").addClass("alert-danger");
			$("#res-msg strong").html("user password can not be empty");
		} else {
			$.ajax({
				type : 'POST',
				url : 'http://localhost:8080/' + submitOrupdate + '.json?token=<%= session.getAttribute("Token") %>',
				data : emp,
				success : function(data, textStatus) {
					console.log(data);
					if (data.status == true) {
						$("#main-res-msg").css("display", "block");
						$("#add-edit-employee").modal('hide');
						$("#main-res-msg").removeClass("alert-info").addClass("alert-success");
						$("#main-res-msg strong").html(data.description + "-" + data.message);
						clear();
						setTimeout(function() {
							$("#main-res-msg").removeClass("alert-info").removeClass("alert-success");
							$("#main-res-msg strong").html("");
							window.location.reload(true);
						}, 1500);
					} else {
						$("#res-msg").removeClass("alert-info").addClass("alert-danger");
						$("#res-msg strong").html(data.description);
					}
				},
				error : function(xhr, textStatus, errorThrown) {
					$("#main-res-msg").removeClass("alert-success").removeClass("alert-info").addClass("alert-danger");
					$("#main-res-msg strong").html(xhr);
					console.log(xhr);
				}
			});
		}
	}

	function deleteUser(id) {
		$.ajax({
			type : 'POST',
			url : 'http://localhost:8080/deleteEmployee/' + id + '.json?token=<%= session.getAttribute("Token") %>',
			success : function(data, textStatus) {
				console.log(data);
				if (data.status == true) {
					$("#main-res-msg").css("display", "block");
					$("#main-res-msg").removeClass("alert-info").removeClass("alert-danger").addClass("alert-success");
					$("#main-res-msg strong").html(data.description);
					setTimeout(function() {
						$("#main-res-msg").removeClass("alert-info").removeClass("alert-success");
						$("#main-res-msg strong").html("");
						window.location.reload(true);
					}, 2000);
				} else {
					$("#main-res-msg").removeClass("alert-info").removeClass("alert-success").addClass("alert-danger");
					$("#main-res-msg strong").html(data.description);
				}
			},
			error : function(xhr, textStatus, errorThrown) {
				$("#main-res-msg").removeClass("alert-success").removeClass("alert-info").addClass("alert-danger");
				$("#main-res-msg strong").html(xhr);
				console.log(xhr);
			}
		});
	}

	function singleViewQuit() {
		$("#submitBtn").attr("disabled", true);
		$("#passwordRow").css("display", "block");
		clear();
		$("#restToOk").attr("value", "close");
		$(".form-control").attr("readonly", false);
		$("#res-msg").css("display", "block");
		$(".modal-title").html("Add New Employee");
		$(".viewSingleHide").css("display", "block");
		$("#add-edit-employee").modal('hide');
		$("#empConfirmPassword").css("display", "block");
		$("#ConfirmPassword").css("display", "block");
	}

	function fillDataToModal(empId) {
		$.each(employees.result, function(i, employee) {
			if (employee.user.id == empId) {
				$("#employeeId").val(employee.id);
				$("#empUserName").val(employee.user.userName);
				$("#userId").val(employee.user.id);
				$("#empName").val(employee.user.name);
				$("#empMobile").val(employee.user.mobile);
				$("#empNic").val(employee.nic);
				$("#empEmail").val(employee.user.email);
				$("#empContact").val(employee.contact);
				$("#empDob").val(employee.dob);
				$("#empRole").val(employee.roleId);
				$("#empRegion-id").val(employee.regionId);
				$("#empAddress").val(employee.address);
				$("#empPassword").val(employee.user.password);
				$("#empConfirmPassword").val(employee.user.password);
			}
		});
	}

	function singleView(empId) {
		submitOrupdate = "addEmployee";
		fillDataToModal(empId);
		$('#add-edit-employee').modal({
			backdrop : 'static',
			keyboard : false
		});
		$("#restToOk").attr("value", "Ok");
		$("#add-edit-employee").modal('show');
		$("#res-msg").css("display", "none");
		$(".modal-title").html("Employee");
		$(".viewSingleHide").css("display", "none");
		$(".form-control").prop("readonly", true);
		$("#empConfirmPassword ").css("display", "none");
		$("#ConfirmPassword").css("display", "none");
		$("#empPassword").css("display", "none");
		$("#hide-pwd").css("display", "none");
	}

	function editEmployee(empId) {
		$("#submitBtn").attr("disabled", false);
		$("#passwordRow").css("display", "none");
		submitOrupdate = "updateEmployee";
		$('#add-edit-employee').modal({
			backdrop : 'static',
			keyboard : false
		});
		fillDataToModal(empId);
		$(".modal-title").html("Edit Employee");
		$("#userId").val(empId);
		$("#add-edit-employee").modal('show');
	}

	function clear() {
		$("#empUserName").val(" ");
		$("#employeeId").val(" ");
		$("#userId").val(0);
		$("#empName").val(" ");
		$("#empMobile").val(" ");
		$("#empNic").val("");
		$("#empEmail").val("");
		$("#empContact").val("");
		$("#empDob").val("");
		$("#empRole").val("");
		$("#empRegion-id").val("");
		$("#empAddress").val("");
		$("#empPassword").val("");
		$("#empConfirmPassword").val("");
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

	$("#empEmail").on("change", function validateEmail1() {
		if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test($('#empEmail').val())) {
			alertMessage(info);
			$("#empContact").focus();
		} else {
			alertMessage(danger);
			$("#resMsg strong").html("Enter a valid email!");
			$('#empEmail').focus();
		}
	});
</script>