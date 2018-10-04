<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.vithu.uscms.session.CurrentUser"%>
<%@ include file="../../layouts/taglib.jsp"%>



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
		<div >
			<input class="btn btn-primary btn-block " style="width:325px;margin:0px 15px 0px 15px; background-color:#80007094;" name="submit"
				type="submit" value="Sign In"  />
		</div>
		<!-- /.col -->
	</div>
</form>
	
<script>
	function logIn(form) {
		try {
			if ($("#user-name").val().trim() == "") {
				alertMessage("User name can not be empty", 'error');
			} else if ($("#password").val().trim() == "") {
				alertMessage("Password can not be empty", 'error');
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
							alertMessage(res.description, 'error');
						} else if (res.status == true) {
							alertMessage(res.description, 'success');
						}
						setTimeout(function() {
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
	
	/** ***************************************************************************** **/
	/** ALERT MESSAGE FUNCTIONS												          **/
	/** ***************************************************************************** **/

	function alertMessage(msg, type) {
		$.notify(msg, {
			clickToHide : true,
			globalPosition : 'top right',
			style : 'bootstrap',
			className : type,
			showAnimation : 'slideDown',
			showDuration : 400,
			hideAnimation : 'slideUp',
			hideDuration : 200
		});
	}
</script>