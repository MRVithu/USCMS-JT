<!-- /** -->
<!--  * @author M.Vithusanth -->
<!--  * @Created On 28th April 2018 -->
<!--  * @Purpose Sales Order Add Page  -->
<!--  */ -->

<%@page import="com.vithu.uscms.entities.Product"%>
<%@page import="com.vithu.uscms.entities.Customer"%>
<%@page import="com.vithu.uscms.entities.Employee"%>
<%@page import="com.vithu.uscms.entities.Department"%>
<%@page import="java.util.List"%>
<%@page import="com.vithu.uscms.others.GenericResult"%>
<%@ include file="../../../layouts/taglib.jsp"%>


<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->

	<!-- Main content -->
	<section class="content">
		<div class="row">
			<div class="col-md-6">
				<div class="box box-body box-success">
					<div class="input-group trans-srch">
						<input type="text" id="trans-search-box" autofocus
							name="search-box" class="form-control search-box"
							placeholder="Search Products"> <label class="input-group-addon trans-srch-label"><i
							class="glyphicon glyphicon-search"> </i></label> <label
							class="input-group-addon trans-srch-label"
							onclick="$('#trans-search-box').val('');$('#trans-search-box').trigger('change');"><i
							class="glyphicon glyphicon-remove"> </i></label>
					</div>

					<div style="position: relative">
						<!-- Added products -->
						<table id="view-table"
							class="table table-condensed table-bordered table-hover table-striped table-pad">
							<thead>
								<tr>
									<th style='text-align: center'>#</th>
									<th>Product</th>
									<th class='number'>Selling Price</th>
									<th class='number'>Min Price</th>
									<th class='number'>Quantity</th>
									<th class='number'>Total</th>
								</tr>
							</thead>
							<tbody id="trans-product-cont">

							</tbody>
						</table>

						<!-- Product selection grid -->
						<div class="table-grid" id="product-grid">
							<table id="product-grid-table"
								class="table table-condensed table-bordered table-hover table-striped table-pad">
								<thead style="background-color:#ccc">
									<tr>
										<th style='text-align: center'>#</th>
										<th>Product</th>
										<th>Code</th>
										<th>Brand</th>
										<th>Item Type</th>
										<th class='number'>Selling price</th>
									</tr>
								</thead>
								<tbody style="background-color:#eee">

								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>

			<div class="col-md-6">
				<div class="box box-body box-info" id="trans-details">
					<div id="tabs">
						<ul class="nav nav-tabs" style="margin: -10px 0 1px 0;">

							<li id="tab-1"><a data-toggle='tab' href="#tabs-1">Cash
									Payments</a></li>
							<li id="tab-2"><a data-toggle='tab' href="#tabs-2">Other
									Modes</a></li>
						</ul>
						<div class="tab-content" id="dep-prop-body">
							<div id="tabs-1" class="tab-pane fade in active">

								<!-- Sales Date -->
								<div class="input-group">
									<label class="input-group-addon">Sales Date</label> <input
										type="date" class="form-control" id="sales-date" value="" />
								</div>

								<!-- Code -->
								<div class="input-group">
									<label class="input-group-addon">Sales Code</label> <input 
										type="text" class="form-control" id="sales-code" value="" disabled/>
								</div>

								<!-- Departments -->
								<div class="input-group">
									<label class="input-group-addon">Department</label> <select
										class="form-control" id="department">
										<!-- Dropdown List Option -->
										<c:forEach items="${departments.result}" var="department">
											<option value="${department.id}">${department.name}</option>
										</c:forEach>
									</select>
								</div>

								<!-- Supplier -->
								<div class="input-group">
									<label class="input-group-addon">Customer</label> <select
										class="form-control" id="customer">
										<!-- Dropdown List Option -->
										<c:forEach items="${customers.result}" var="customer">
											<option value="${customer.id}">${customer.user.name}</option>
										</c:forEach>
									</select>
								</div>
								
								<!-- Sales Officer -->
								<div class="input-group">
									<label class="input-group-addon">Sales Officer</label> <select
										class="form-control" id="sales-officer">
										<!-- Dropdown List Option -->
										<c:forEach items="${employees.result}" var="employee">
											<option value="${employee.id}">${employee.user.name}</option>
										</c:forEach>
									</select>
								</div>

								<!-- Total Discount -->
								<div class="input-group">
									<label class="input-group-addon">Total Discount</label> <input
										type="number" class="form-control number" value="0.00"
										onchange='refreshAddedProductList()' id="total-discount" /> <label
										class="input-group-addon">Rs.</label>
								</div>

								<!-- Total Amount -->
								<div class="input-group">
									<label class="input-group-addon large-input">Total</label> <input
										type="number"  class="form-control number large-input"
										value="0.00" id="grand-total" disabled/> <label
										class="input-group-addon large-input">Rs.</label>
								</div>


								<!-- Paid Amount -->
								<div class="input-group">
									<label class="input-group-addon large-input">Paid</label> <input
										style="background-color: #1af91a" onchange="adjustPayment()"
										type="number" class="form-control number large-input"
										value="0.00" id="paid" /> <label
										class="input-group-addon large-input">Rs.</label>
								</div>

								<!-- Credit Amount -->
								<div class="input-group">
									<label class="input-group-addon large-input">Credit</label> <input
										style="background-color: #fd5064" type="text" 
										class="form-control number large-input" value="0.00"
										id="credit-amount" disabled/> <label
										class="input-group-addon large-input">Rs.</label>
								</div>

								<div>
									<input type="button" onclick="saveTransaction(this)"
										style="float: right" data-click="3"
										class="btn btn-success large-btn" name="submit"
										value="Proceed (3)" />
								</div>
							</div>
							<div id="tabs-2" class="tab-pane fade">

								<!-- Total Cheque Amount -->
								<div class="input-group">
									<label class="input-group-addon">Cheque Amount</label> <input
										type="number"  class="form-control number"
										value="0.00" id="tot-che-amount" disabled/> <label
										class="input-group-addon ">Rs.</label>
								</div>


								<div class="box box-body box-warn">
									<div id="tabs">
										<ul class="nav nav-tabs" style="padding: 2px; margin: 2px;">
											<li id="tab-2-1"><a data-toggle='tab' href="#tabs-2-1">Cheque</a></li>
											<li id="tab-2-2"><a data-toggle='tab' href="#tabs-2-2">Deposit</a></li>
										</ul>
										<div class="tab-content" id="dep-prop-body">
											<div id="tabs-2-1" class="tab-pane fade in active">
												<div class="row">
													<div class="col-md-6">
													
														<!-- cheque Date -->
														<div class="input-group">
															<label class="input-group-addon">Cheque Date</label> <input
																type="date" class="form-control" id="che-date" value="" />
														</div>
														<!-- Bank -->
														<div class="input-group">
															<label class="input-group-addon">Bank</label> <input
																type="text" class="form-control " id="che-bank" />
														</div>

														<!-- Cheque Number -->
														<div class="input-group">
															<label class="input-group-addon">Cheque No</label> <input
																type="text" class="form-control" id="che-num" />
														</div>

														<!--  Cheque amount -->
														<div class="input-group">
															<label class="input-group-addon">Amount</label> <input
																type="number" class="form-control number" value="0.00"
																id="che-amount" /> <label class="input-group-addon "
																style="width: 10px">Rs.</label>
														</div>

														<div>
															<input type="button" onclick="clearChequeFields(this)"
																style="float: left" class="btn btn-warning small-btn"
																value="Clear all" /> <input type="button"
																onclick="addCheque(this)" style="float: right"
																class="btn btn-success small-btn" value="Add" />
														</div>
													</div>
													<div class="col-md-6">
														<div class="box box-body box-info" id="trans-details">
															<table
																class="table table-condensed table-hover table-striped table-bordered">
																<thead>
																	<tr>
																		<th style="width: 10px">#</th>
																		<th>Che No</th>
																		<th>Amount</th>
																	</tr>
																</thead>
																<tbody id="che-list">
																</tbody>
																<tfoot>
																	<tr>
																		<th colspan="2" style='text-align: center'>Grand
																			Total</th>
																		<th class='number' id="che-total"></th>
																	</tr>
																</tfoot>
															</table>
														</div>
													</div>
												</div>
											</div>
											<div id="tabs-2-2" class="tab-pane"></div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<!-- Modal -->
	<div class="modal fade" id="trans-modal" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">
						<input type='text' class='editable-title' id='trans-product'
							value='Item' />
					</h4>
				</div>
				<form action="" method="post" onsubmit="return validateForm('A')">
					<div class="modal-body">
						<div class="alert alert-danger msgBox" id="msgBox"
							style="display: none">
							<strong id="errMsg"> </strong>
						</div>

						<input type="hidden" id="prod-id" value="" />

						<!-- Add or edit -->
						<input type="hidden" id="etype" value="A" /> <input type="hidden"
							id="edit-id" value="A" />

						<div class="input-group">
							<label class="input-group-addon">Quantity</label> <input
								type="number" value="0" onchange="adjustPrices('qty')"
								id="trans-qty" name="mobile"
								class="form-control number field-wid" placeholder=""> <label
								class="input-group-addon small-addon">Units</label>
						</div>
						<div class="input-group">
							<label class="input-group-addon">Min price</label> <input
								type="number" value="0" 
								id="trans-mp" name="name" class="form-control number field-wid"
								placeholder="" disabled> <label
								class="input-group-addon small-addon">Rs.</label>
						</div>
						<div class="input-group">
							<label class="input-group-addon">Selling Price</label> <input
								type="number" onchange="adjustPrices('unit')" value="0" class="form-control number field-wid"
								id="trans-sp"  /> <label
								class="input-group-addon small-addon">Rs.</label>
						</div>
						<div class="input-group">
							<label class="input-group-addon special-input">Total</label> <input
								type="number" value="0" onchange="adjustPrices('total')"
								id="trans-total" name="contact"
								class="form-control field-wid number special-input"
								placeholder=""> <label
								class="input-group-addon small-addon special-input">Rs.</label>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default btn-fl-left"
							data-dismiss="modal">Close</button>
						<input type="button" onclick="removeProductFromTheList()"
							class="btn btn-danger" name="submit" value="Remove" /> <input
							type="button" onclick="addProductToTheList()"
							class="btn btn-success" name="submit" value="Add" />
					</div>
				</form>
			</div>
		</div>
	</div>

	<!-- Transaction Form -->
	<form method="post" id="transForm" action="" style="display: none">
		<input type="hidden" name="transJson" id="transJson" value='' />
	</form>

	<!-- /.content -->
</div>


<!-- REQUIRED JS SCRIPTS -->
<script
	src="<c:url value=" /resources/plugins/datatables/jquery.dataTables.min.js" />"></script>
<script
	src="<c:url value="/resources/plugins/datatables/dataTables.bootstrap.min.js" />"></script>

<script>
	var products = "";
	products = ${products.resultString};
	
	var salesMaxId="";
	salesMaxId = ${salesMaxId.result};
	
	
	$("#srch-bar-cont, #action-cont").css("visibility", "hidden");

	var productList = [];
	var productMap = {}; //will be made fom the list
	var addedProductList = []; //products added for transactions
	var addedChequeList = []; // cheques added for payments
	var productIndex = 0;
	var chequeId = 0;

	$(document).ready(function () {
		//printDate();
		
		$("#sidebar-style").addClass('sidebar-collapse');

		var code=getMaxId("SALE", salesMaxId.result);
		$("#sales-code").val(code);
		
		productList = products.result;
		productMap = getObjectMapFromList(productList, "id");

		console.log(productList);

		$("#trans-search-box").on("keyup", function(){
			searchProducts(productList);
		});

		$("#trans-search-box").on("change", function(){
			searchProducts(productList);
		});

		$("#product-grid-table tbody tr td").on("click", function(){
			showProductModal($(this).parent().attr("data-id"), false);
		});
		
		
	});

	//this method will be called from commons.js - searchProduct function
	function showProductModal(pid, isToEdit)
	{
		var prodId = 0;
		var prodName = "";
		var sp = 0;
		var mp = 0;
		var qty = 0;
		var total = 0;

		$(".trans-srch").animate({marginTop:"0px"});
		$(".screen-overlay").fadeOut();
		$("#product-grid").fadeOut();

		if(isToEdit)
		{
			var product = addedProductList[pid];
			prodName = product.name;
			sp = product.selleingPrice;
			mp = product.minPrice;
			qty = product.quantity;
			total = product.total;
			prodId = product.id;
			$("#etype").val("E");
			$("#edit-id").val(pid);
		}
		else
		{
			var product = productMap[pid];
			prodName = product.name + " - " + product.code;
			sp = product.selleingPrice;
			mp = product.minPrice;
			qty = 1.00;
			total = sp;
			prodId = product.id;
			console.log(product);
			$("#etype").val("A");			
		}

		$("#prod-id").val(prodId);
		$("#trans-product").val(prodName);
		$("#trans-sp").val(parseFloat(sp + "").toFixed(2));
		$("#trans-mp").val(parseFloat(mp + "").toFixed(2));
		$("#trans-qty").val(parseFloat(qty + "").toFixed(2));
		$("#trans-total").val(parseFloat(total + "").toFixed(2));

		$('#trans-modal').modal({backdrop: 'static', keyboard: false}) ;
		$("#trans-modal").modal("show");
	}

	function removeProductFromTheList()
	{
		var pid = $("#edit-id").val();
		delete addedProductList[pid];
		refreshAddedProductList();
		$("#trans-modal").modal("hide");
		$("#trans-search-box").focus();
	}

	function addProductToTheList(){
		var product = {};
		var pid = "";

		if($("#etype").val() == "A"){
			product = {};
			product.id = $("#prod-id").val();
			product.name = $("#trans-product").val();
			product.sellingPrice = parseFloat($("#trans-sp").val()).toFixed(2);
			product.minPrice = parseFloat($("#trans-mp").val()).toFixed(2);
			product.quantity = parseFloat($("#trans-qty").val()).toFixed(2);
			product.total = parseFloat($("#trans-total").val()).toFixed(2);
			var pid = "P" + (++productIndex);
			product.pid = pid;
			addedProductList[pid] = product;
		}
		else {
			pid = $("#edit-id").val();
			product = addedProductList[pid];
			product.name = $("#trans-product").val();
			product.sellingPrice = parseFloat($("#trans-sp").val()).toFixed(2);
			product.minPrice = parseFloat($("#trans-mp").val()).toFixed(2);
			product.quantity = parseFloat($("#trans-qty").val()).toFixed(2);
			product.total = parseFloat($("#trans-total").val()).toFixed(2);
		}
			refreshAddedProductList();
			$("#trans-modal").modal("hide");
			$("#trans-search-box").focus();
	}

	function refreshAddedProductList(){
		$("#trans-product-cont").html("");
		var counter = 0;
		var total = 0;
		for (const key of Object.keys(addedProductList)) {
			var product = addedProductList[key];
			var ondblclickFunc = 'showProductModal("'+ product.pid +'", true)';
			var htmlStr = "<tr id='trans-"+ product.pid +"' ondblclick ='"+ ondblclickFunc +"'>"
											+ "<td>" + (counter + 1) + "</td>"
											+ "<td>" + product.name + "</td>"
											+ "<td class='number'>" + product.sellingPrice + "</td>"
											+ "<td class='number'>" + product.minPrice + "</td>"
											+ "<td class='number'>" + product.quantity + "</td>"
											+ "<td class='number'>" + product.total + "</td>"
										+ "</tr>";
			counter++;
			total += parseFloat(product.total);
			$("#trans-product-cont").append(htmlStr);
		}
		var netTotal=total-parseFloat($("#total-discount").val());
		$("#grand-total").val(netTotal.toFixed(2));
		adjustPayment();
	}

	function adjustPrices(code){

		if(code == "unit" || code == "qty"){
			var sp = $("#trans-sp").val();
			var qty = $("#trans-qty").val();
			var tot = 0;

			try{ tot = (parseFloat(sp)*parseFloat(qty)).toFixed(2); tot = isNaN(tot) ? 0.00 : tot }catch(e){}
			$("#trans-total").val(tot);
		}
		else if(code == "total"){
			var tot = $("#trans-total").val();
			var qty = $("#trans-qty").val();
			var up = 0;

			try{ up = (parseFloat(tot)/parseFloat(qty)).toFixed(2); up = isNaN(up) ? 0.00 : up }catch(e){}
			$("#trans-pp").val(up);
		}
	}
	
	function adjustPayment(){
		var paid = 0;
		var credit = 0;
		var cheque = 0;

		credit=$("#grand-total").val()-$("#tot-che-amount").val()-$("#paid").val();
		$("#credit-amount").val(credit.toFixed(2));
	}
	
	function deleteCheque(id){
		delete addedChequeList[id];
		console.log(addedChequeList);
		refreshAddedChequeList();
		
	}
	
	function addCheque(){
		var cheque={};
		chequeId++;
		
		cheque.id=chequeId;
		cheque.number=$("#che-num").val();
		cheque.bank=$("#che-bank").val();
		cheque.amount=$("#che-amount").val();
		cheque.date=$("#che-date").val();
		addedChequeList[chequeId] = cheque;
		console.log(addedChequeList);
	
		refreshAddedChequeList();
		
		clearChequeFields();
	}
	
	function refreshAddedChequeList(){
		var total=0;
		$("#che-list").empty();
		for (const key of Object.keys(addedChequeList)) {
			var cheque = addedChequeList[key];
			var htmlStr = "<tr>"
				+ "<td onclick='deleteCheque("+cheque.id+")' class='btn text-danger'><i class='fa fa-trash-o'></i></td>"
				+ "<td>" +cheque.number + "</td>"
				+ "<td class='number'>" +parseFloat(cheque.amount).toFixed(2)+ "</td>"
			+ "</tr>";
			
			$("#che-list").append(htmlStr);
			
			total=	total+parseFloat(cheque.amount);
		}
		$("#che-total").html(formatNumber(total,2));
		$("#tot-che-amount").val(total.toFixed(2));
		adjustPayment();
	}
	
	function clearChequeFields(){
		$("#che-bank").val("");
		$("#che-num").val("");
		$("#che-amount").val("0.00");
	}

	
	
	var clickTimeout = {};
	function saveTransaction(e)
	{
		var clicks = parseInt($(e).attr("data-click"));

		if(clicks == 1)
		{
			if(Object.keys(addedProductList).length > 0)
			{
				try{
					var transObj = {};
					var productArray = [];
					for (const key of Object.keys(addedProductList)) {
						var product = addedProductList[key];
						productArray.push(product);
					}

					var chequeArray = [];
					for (const key of Object.keys(addedChequeList)) {
						var cheque = addedChequeList[key];
						chequeArray.push(cheque);
					}
					
					transObj.code=$("#sales-code").val();
					transObj.customer = $("#customer").val();
					transObj.salesOfficer=$("#sales-officer").val();
					transObj.department = $("#department").val();
					transObj.salesDate = $("#sales-date").val();
					transObj.payCash = $("#paid").val();
					transObj.payCredit = $("#credit-amount").val();
					transObj.payTotal =  parseFloat($("#credit-amount").val())+parseFloat($("#paid").val())+parseFloat($("#tot-che-amount").val());
					transObj.products = productArray;
					transObj.payCheques = chequeArray;
					
					console.log(transObj);
				
					$.ajax({
						
						type: 'POST',
						url: 'http://localhost:8080/addSales.json?token=<%=session.getAttribute("Token")%>',
						data: {data:JSON.stringify(transObj)},
						 headers : {
						     'Access-Control-Allow-Origin' : '*'
						    },
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
				catch(err){
					alert(err);
				}
			}
			else {
				showErrorMsg("Please add some products before saving the sales.");
			}
		}
		else {
			clicks--;
			$(e).attr("data-click", clicks);
			$(e).val("Save (" + clicks + ")");
		}

		clearTimeout(clickTimeout);
		clickTimeout = setTimeout(function(){
			$(e).attr("data-click", 3);
			$(e).val("Save (" + 3 + ")");
		}, 10000);
	}

	$("#view-table tbody tr:first-child").trigger("click");
	
	function printDate(){
		var d = new Date();
        var todayDate = "";
        if((d.getMonth()+1) < 10){
             todayDate = d.getFullYear() + "-0" + (d.getMonth()+1) + "-" + d.getDate();
        }
        else{
             todayDate = d.getFullYear() + "-" + (d.getMonth()+1) + "-" + d.getDate();
        }

     

        alert(d);
        alert(todayDate);
        $('#sales-date').val(d);
        //$('#check-out').val(tomorrowDate);
	}
</script>