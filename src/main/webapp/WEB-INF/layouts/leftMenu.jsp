 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  <%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    
    
  <aside class="main-sidebar" id = "tofix">

    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar fixed">

      <!-- Sidebar user panel (optional) -->
      <div class="user-panel">
        <div class="pull-left image">
          <img src="<c:url value="/resources/dist/img/user2-160x160.jpg" />" class="img-circle" alt="User Image">
        </div>
        <div class="pull-left info">
          <p>Alexander Pierce</p>
          <!-- Status -->
          <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
        </div>
      </div>

      <!-- search form (Optional) -->
      <form action="#" method="get" class="sidebar-form">
        <div class="input-group">
          <input type="text" name="q" class="form-control" placeholder="Search...">
              <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                </button>
              </span>
        </div>
      </form>
      <!-- /.search form -->

      <!-- Sidebar Menu -->
      <ul class="sidebar-menu">
       <li class="header ${current == 'home' ? 'active' : '' }">
       <a href="/home.html"> <i class="fa fa-home"></i><span>Home</span></a>
       </li>

		  <li class="treeview ${current == 'employee' or current == 'customer' ? 'active' : '' }">
          <a href="#"><i class="fa fa-user"></i> <span>Users</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
          	 <li class = "${current == 'employee' ? 'active' : '' }"><a href="/employee.html?token=<%= session.getAttribute("Token") %>"><i class="fa fa-circle-thin"></i><span> Employees</span></a></li>
           
            <li class = "${current == 'customer' ? 'active' : '' }"><a href="/customer.html?token=<%= session.getAttribute("Token") %>"><i class="fa fa-circle-thin"></i><span> Customers</span></a></li>
          </ul>
        </li>
		
	   <li class="treeview ${current == 'vehicle' ? 'active' : '' }">
          <a href="/vehicle.html?token=<%= session.getAttribute("Token") %>"><i class="fa fa-car"></i><span> Vehicles</span></a>
        </li>

          <li class="treeview ${current == 'route' ? 'active' : '' }">
          <a href="/route.html?token=<%= session.getAttribute("Token") %>"><i class="fa fa-road"></i><span> Routes</span></a>
        </li>
		  <li class="treeview">
          <a href="#"><i class="fa fa-cart-arrow-down"></i> <span>Sales</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li class="active"><a href="#"><i class="fa fa-circle-thin"></i><span> </span></a></li>
            <li class="active"><a href="#"><i class="fa fa-circle-thin"></i><span> </span></a></li>
            <li class="active"><a href="#"><i class="fa fa-circle-thin"></i><span> </span></a></li>
          </ul>
        </li>
		
        <li class="treeview">
          <a href="#"><i class="fa fa-truck"></i> <span>Purchase</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li class="active"><a href="#"><i class="fa fa-circle-thin"></i><span> </span></a></li>
            <li class="active"><a href="#"><i class="fa fa-circle-thin"></i><span> </span></a></li>
            <li class="active"><a href="#"><i class="fa fa-circle-thin"></i><span> </span></a></li>
          </ul>
        </li>

		  <li class="treeview">
          <a href="#"><i class="fa fa-money"></i> <span>Payments</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li class="active"><a href="#"><i class="fa fa-circle-thin"></i><span>Pay Cash</span></a></li>
            <li class="active"><a href="#"><i class="fa fa-circle-thin"></i> <span>Pay Card</span></a></li>
            <li class="active"><a href="#"><i class="fa fa-circle-thin"></i> <span>Pay Deposit</span></a></li>
            <li class="active"><a href="#"><i class="fa fa-circle-thin"></i> <span>Pay Cheque</span></a></li>
            <li class="active"><a href="#"><i class="fa fa-circle-thin"></i> <span>Pay Credit</span></a></li>
            <li class="active"><a href="#"><i class="fa fa-circle-thin"></i> <span>Pay Voucher</span></a></li>
            <li class="active"><a href="#"><i class="fa fa-circle-thin"></i> <span>Pay Loyalty</span></a></li>
          </ul>
        </li>
		
      <li class="treeview">
          <a href="#"><i class="fa fa-money"></i> <span>Transections</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li class="active"><a href="#"><i class="fa fa-circle-thin"></i><span>Customer Transections</span></a></li>
            <li class="active"><a href="#"><i class="fa fa-circle-thin"></i> <span>Supplier Transections</span></a></li>
            <li class="active"><a href="#"><i class="fa fa-circle-thin"></i> <span>ank Transections</span></a></li>
          </ul>
        </li>
		
		  <li class="treeview">
          <a href="#"><i class="fa fa-link"></i> <span>Others</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li class="active"><a href="#"><i class="fa fa-circle-thin "></i> <span>Departments</span></a></li>
            <li class="active"><a href="#"><i class="fa fa-circle-thin"></i> <span>Counters</span></a></li>
            <li class="active"><a href="#"><i class="fa fa-circle-thin"></i> <span>Banks</span></a></li>
          </ul>
        </li>
      </ul>
      <!-- /.sidebar-menu -->
    </section>
    <!-- /.sidebar -->

<!-- /.sidebar -->
  </aside>
