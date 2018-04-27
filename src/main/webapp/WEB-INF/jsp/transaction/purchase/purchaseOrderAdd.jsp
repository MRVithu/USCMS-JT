<!-- /** -->
<!--  * @author M.Vithusanth -->
<!--  * @Created On 26th April 2018 -->
<!--  * @Purpose Purchase Order Add Page  -->
<!--  */ -->

<%@page import="com.vithu.uscms.entities.Product"%>
<%@page import="com.vithu.uscms.entities.Supplier"%>
<%@page import="com.vithu.uscms.entities.Department"%>
<%@page import="java.util.List"%>
<%@page import="com.vithu.uscms.others.GenericResult"%>
<%@ include file="../../../layouts/taglib.jsp"%>


<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	
	<div class="alert alert-success alert-dismissable"
		style="display: none; margin: 5px 20px" id="succAlert">
		<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
		<strong id="succMsg"></strong>
	</div>

	<div class="alert alert-danger alert-dismissable"
		style="display: none; margin: 5px 20px" id="errAlert">
		<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
		<strong id="errMsg"></strong>
	</div>

	<div class="alert alert-info alert-dismissable"
		style="display: none; margin: 5px 20px" id="infoAlert">
		<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
		<strong id="infoMsg"></strong>
	</div>
	<!-- Main content -->
	<section class="content">
		<div class="row">
			<div class="col-md-7">
				<div class="box box-body box-success">
					<div class="input-group trans-srch">
						<input type="text" id="trans-search-box" name="search-box"
							class="form-control search-box" placeholder="Search Products">
						<label class="input-group-addon trans-srch-label"><i
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
									<th class='number'>Purchase Price</th>
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
								<thead>
									<tr>
										<th style='text-align: center'>#</th>
										<th>Product</th>
										<th>Code</th>
										<th>Brand</th>
										<th>Item Type</th>
										<th class='number'>Selling price</th>
									</tr>
								</thead>
								<tbody>

								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>

			<div class="col-md-5">
				<div class="box box-body box-info" id="trans-details">
					<!-- PO Date -->
					<div class="input-group">
						<label class="input-group-addon">PO Date</label> <input
							type="date" class="form-control" id="po-date" value="" />
					</div>

					<!-- Code -->
					<div class="input-group">
						<label class="input-group-addon">PO Code</label> <input
							type="text" class="form-control" id="po-code" value="" />
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
						<label class="input-group-addon">Supplier</label> <select
							class="form-control" id="supplier">
							<!-- Dropdown List Option -->
							<c:forEach items="${suppliers.result}" var="supplier">
								<option value="${supplier.id}">${supplier.user.name}</option>
							</c:forEach>
						</select>
					</div>

					<!-- Expected Date -->
					<div class="input-group">
						<label class="input-group-addon">Expected Date</label> <input
							type="date" class="form-control" id="ex-date" value="" />
					</div>

					<!-- Note -->
					<div class="input-group">
						<label class="input-group-addon">Note</label> <input type="text"
							class="form-control" id="note" />
					</div>
					<!-- Total Amount -->
					<div class="input-group">
						<label class="input-group-addon large-input">Total</label> <input
							type="text" readonly class="form-control large-input"
							value="0.00" id="grand-total" /> <label
							class="input-group-addon large-input">Rs.</label>
					</div>

					<hr />
					<input type="button" onclick="saveTransaction(this)"
						style="float: right" data-click="3"
						class="btn btn-success large-btn" name="submit" value="Save (3)" />
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
								id="trans-qty" name="mobile" class="form-control field-wid"
								placeholder=""> <label
								class="input-group-addon small-addon">Units</label>
						</div>
						<div class="input-group">
							<label class="input-group-addon">Purchase price</label> <input
								type="number" value="0" onchange="adjustPrices('unit')"
								id="trans-pp" name="name" class="form-control field-wid"
								placeholder=""> <label
								class="input-group-addon small-addon">Rs.</label>
						</div>
						<div class="input-group">
							<label class="input-group-addon">Selling Price</label> <input
								type="number" value="0" class="form-control field-wid"
								id="trans-sp" disabled /> <label
								class="input-group-addon small-addon">Rs.</label>
						</div>
						<div class="input-group">
							<label class="input-group-addon special-input">Total</label> <input
								type="number" value="0" onchange="adjustPrices('total')"
								id="trans-total" name="contact"
								class="form-control field-wid special-input" placeholder="">
							<label class="input-group-addon small-addon special-input">Rs.</label>
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

	$("#srch-bar-cont, #action-cont").css("visibility", "hidden");

	var productList = [];
	var productMap = {}; //will be made fom the list
	var addedProductList = []; //products added for transactions
	var productIndex = 0;

	$(document).ready(function () {
		$("#sidebar-style").addClass('sidebar-collapse');
		
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
		var pp = 0;
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
			pp = product.lastPurchasePrice;
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
			pp = product.lastPurchasePrice;
			qty = 1.00;
			total = pp;
			prodId = product.id;
			$("#etype").val("A");
		}

		$("#prod-id").val(prodId);
		$("#trans-product").val(prodName);
		$("#trans-sp").val(parseFloat(sp + "").toFixed(2));
		$("#trans-pp").val(parseFloat(pp + "").toFixed(2));
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

	function addProductToTheList()
	{
		var product = {};
		var pid = "";

		if($("#etype").val() == "A")
		{
			product = {};
			product.id = $("#prod-id").val();
			product.name = $("#trans-product").val();
			product.sellingPrice = parseFloat($("#trans-sp").val()).toFixed(2);
			product.purchasePrice = parseFloat($("#trans-pp").val()).toFixed(2);
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
			product.purchasePrice = parseFloat($("#trans-pp").val()).toFixed(2);
			product.quantity = parseFloat($("#trans-qty").val()).toFixed(2);
			product.total = parseFloat($("#trans-total").val()).toFixed(2);
		}
			refreshAddedProductList();
			$("#trans-modal").modal("hide");
			$("#trans-search-box").focus();
	}

	function refreshAddedProductList()
	{
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
											+ "<td class='number'>" + product.purchasePrice + "</td>"
											+ "<td class='number'>" + product.quantity + "</td>"
											+ "<td class='number'>" + product.total + "</td>"
										+ "</tr>";
			counter++;
			total += parseFloat(product.total);
			$("#trans-product-cont").append(htmlStr);
		}

		$("#grand-total").val(total.toFixed(2));
	}

	function adjustPrices(code)
	{
		if(code == "unit" || code == "qty")
		{
			var up = $("#trans-pp").val();
			var qty = $("#trans-qty").val();
			var tot = 0;

			try{ tot = (parseFloat(up)*parseFloat(qty)).toFixed(2); tot = isNaN(tot) ? 0.00 : tot }catch(e){}
			$("#trans-total").val(tot);
		}
		else if(code == "total")
		{
			var tot = $("#trans-total").val();
			var qty = $("#trans-qty").val();
			var up = 0;

			try{ up = (parseFloat(tot)/parseFloat(qty)).toFixed(2); up = isNaN(up) ? 0.00 : up }catch(e){}
			$("#trans-pp").val(up);
		}
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
	
						transObj.supplier = $("#supplier").val();
						transObj.department = $("#department").val();
						transObj.exDate = $("#ex-date").val();
						transObj.poDate = $("#po-date").val();
						transObj.code = $("#po-code").val();
						transObj.note = $("#note").val();
						transObj.products = productArray;
						/* $("#transJson").val(JSON.stringify(transObj));
						$("#transForm").submit(); */
					
						$.ajax({
							
							type: 'POST',
							url: 'http://localhost:8080/addPurchaseOrder.json?token=<%=session.getAttribute("Token")%>',
							data: {data:JSON.stringify(transObj)},
							success: function(res) {
								console.log(res);
								console.log(res.status);
								$("#res-msg strong").html(res.msg);
			
								if (res.status == false) {
									showErrorMsg("Please add some products before saving the purchase order");
								} else if (res.status == true) {
									showSuccessMsg("Purchase order added successfully.");
								}
			
								setTimeout(function() {
									showErrorMsg("Please add some products before saving the purchase order");
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
					showErrorMsg("Please add some products before saving the purchase order");
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
</script>