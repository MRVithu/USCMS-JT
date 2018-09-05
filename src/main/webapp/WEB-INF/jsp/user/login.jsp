<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.vithu.uscms.session.CurrentUser"%>
<%@ include file="../../layouts/taglib.jsp"%>



<div id="res-message"></div>
<hr>
<form  role="form" method="post" onsubmit="return logIn(this);">
	<div class="form-group has-feedback">
		<input type="text" name="username" id="user-name" class="form-control"
			placeholder="username" /> <span
			class="glyphicon glyphicon-envelope form-control-feedback"></span>
	</div>
	<div class="form-group has-feedback">
		<input type="password" name="password" id="password"
			class="form-control" placeholder="Password" /> <span
			class="glyphicon glyphicon-lock form-control-feedback"></span>
	</div>
	<div class="row">
		<!-- /.col -->
		<div class="col-xs-4">
			<input class="btn btn-primary btn-block btn-flat" name="submit"
				type="submit" value="Sign In" />
		</div>
		<!-- /.col -->
	</div>
</form>

<script>
	function logIn(form) {
		try {
			if ($("#user-name").val().trim() == "") {
				$("#res-message").html("User name can not be empty");
			} else if ($("#password").val().trim() == "") {
				$("#res-message").html("Password can not be empty");
			} else {
				var user = {};
				user.userName = $("#user-name").val();
				user.password = $("#password").val();
				console.log(user);
				$.ajax({
					type : 'POST',
					url : 'http://127.0.0.1:8080/doLogin',
					data : {
						data : JSON.stringify(user)
					},
					success : function(res) {
						res = JSON.parse(res)
						console.log(res);
						console.log(res.status);
						if (res.status == false) {
							$("#res-message").removeClass("alert-success").removeClass("alert-info").addClass("alert-danger");
							$("#res-message").html(res.description);
						} else if (res.status == true) {
							$("#res-message").addClass("alert-success").removeClass("alert-info").removeClass("alert-danger");
							$("#res-message").html(res.description);
						}
						setTimeout(function() {
							$("#res-message").removeClass("alert-success").addClass("alert-info").removeClass("alert-danger");
							$("#res-message").html("Fill all fields and hit Save");
							if (res.status == true) {
								window.location.href = "/home";
							}
						}, 500);
					},
					error : function(xhr, textStatus, errorThrown) {
						console.log(xhr);
						alert("error");
						console.log(errorThrown);
					}
				});
			}
		} catch (err) {
			alert(err);
		}
		return false;
	}
</script>