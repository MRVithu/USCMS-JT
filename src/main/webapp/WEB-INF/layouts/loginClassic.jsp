<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
   
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

	<link rel="icon" href="/resources/images/logo.png">
	  <!-- Font Awesome -->
	  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
	  <!-- Ionicons -->
	  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
	 
	  <!-- Bootstrap Core CSS -->
	  <link href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />" rel="stylesheet">
		
	  <!-- Theme style -->
	  <link href="<c:url value="/resources/dist/css/AdminLTE.min.css" />" rel="stylesheet">
		
	  <!-- AdminLTE Skins.  -->
   	  <link href="<c:url value="/resources/plugins/iCheck/square/blue.css" />" rel="stylesheet">
   	  
   	  <!-- Common css  -->
   	  <link href="<c:url value="/resources/common/css/uscms.css" />" rel="stylesheet">
   	  
   	  <title><tiles:getAsString name="title"/></title>
</head>

<body class="hold-transition login-page fixed" style="background-image:url('/resources/images/bg.jpg')">

	<!-- REQUIRED JS SCRIPTS -->
	<!-- jQuery 2.2.3 -->
	<script src="<c:url value="/resources/plugins/jQuery/jquery-2.2.3.min.js" />"></script>
	<script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js" />"></script>
	<!-- notify -->
	<script src="<c:url value="/resources/dist/js/notify.js" />"></script>
	
	
	<div class="login-box">
		
		 <div class="login-box-body" style="background-color: #dac6d554;border-radius:5px 5px 5px 5px;">
		   <img src="<c:url value="/resources/images/logo.png" />" class="user-image" style="border-radius:8px;width: 100px; height:100px; margin-left:100px" alt="User Image">
		    
		    <tiles:insertAttribute name = "body"/>
		
	     </div>
	     
   </div>
   <footer class="main-footer">
	<!-- To the right -->
	<div class="pull-right hidden-xs">Developed By MRVithu</div>
	<!-- Default to the left -->
	<strong>Copyright &copy;<script>document.write(new Date().getFullYear());</script> <a href="#">Jagathees Traders</a>.
	</strong> All rights reserved.
</footer>
</body>

</html>
