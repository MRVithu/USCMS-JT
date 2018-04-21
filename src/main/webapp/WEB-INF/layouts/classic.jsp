<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
     <c:set var="contextPath" value="${pageContext.request.contextPath} "/>
     
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

	  <!-- Font Awesome -->
	  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
	  <!-- Ionicons -->
	  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
	 
	  <!-- Bootstrap Core CSS -->
	  <link href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />" rel="stylesheet">
		
	  <!-- Theme style -->
	  <link href="<c:url value="/resources/dist/css/AdminLTE.min.css" />" rel="stylesheet">
		
	  <!-- AdminLTE Skins.  -->
   	  <link href="<c:url value="/resources/dist/css/skins/skin-blue.min.css" />" rel="stylesheet">
   	  
   	    <!-- Common css  -->
   	  <link href="<c:url value="/resources/common/css/DropMe.css" />" rel="stylesheet">
   	  
   	   <!-- DataTable  -->
   	  <link href="<c:url value="/resources/plugins/datatables/dataTables.bootstrap.css" />" rel="stylesheet">
   	  
   	  	<!--multiple-select  -->
   	  <link href="<c:url value="/resources/dist/css/multiple-select.css" />" rel="stylesheet">
   	  

   	  <title><tiles:getAsString name="title"/></title>

</head>

<body class="hold-transition skin-blue sidebar-mini">
	 
	<!-- REQUIRED JS SCRIPTS -->
	<!-- jQuery 2.2.3 -->
	<script src="<c:url value="/resources/plugins/jQuery/jquery-2.2.3.min.js" />"></script>
	<script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js" />"></script>
	<!-- AdminLTE App -->
	<script src="<c:url value="/resources/dist/js/app.min.js" />"></script>	
	<!-- Common JS -->
	<script src="<c:url value="/resources/common/js/dropme.js" />"></script>
	<!-- common resources -->
	<jsp:include page="../jsp/common/modalCommon.jsp" />
	
	<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx" %>
	
	 <!-- wrapper -->
	<div class="wrapper">
	
	<!-- Hideen input to check logger -->
	<input type = "text" id ="checklogintext" value = "<%= session.getAttribute("Token") %>" hidden= "hidden"/>

	  <!-- Main Header -->
	  <tiles:insertAttribute name = "header"></tiles:insertAttribute>
	 
	  <!-- Left side column. contains the logo and sidebar -->
	  <tiles:insertAttribute name = "leftMenu"></tiles:insertAttribute>
	 
	  <!-- Body -->
	  <tiles:insertAttribute name = "containerbody"></tiles:insertAttribute>
	
	  <!-- Main Footer -->
	  <tiles:insertAttribute name = "footer"></tiles:insertAttribute>

	  <!-- Add the sidebar's background. This div must be placed immediately after the control sidebar -->
	  <div class="control-sidebar-bg"></div>
	  
	</div>
	<!-- ./wrapper -->
	
</body>
</html>