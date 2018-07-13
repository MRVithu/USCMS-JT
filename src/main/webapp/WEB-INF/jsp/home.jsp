

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>



<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<div class="row">
			<div class="col-md-6">
				<a
					href="/salesAddView.html?token=<%=session.getAttribute("Token")%>"
					class="btn btn-info">Sales</a>
					</br></br>
			
				<a
					href="/purchaseAddView.html?token=<%=session.getAttribute("Token")%>"
					class="btn btn-info">Purchase</a>
			</div>
		</div>
	</section>
</div>


<script>
	var logger="";
	logger="<%=session.getAttribute("logger")%>";
	console.log(logger);
</script>


