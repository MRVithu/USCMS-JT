/**
 * @author M.Vithusanth
 * @CreatedOn 22th April 2018
 * @Purpose Script for all project
 */

/** ***************************************************************************** **/
/** CHECK LOGIN FUNCTION											              **/
/** ***************************************************************************** **/

//var apiUrl = 'http://localhost:8080/USCMS-JT/';
var apiUrl = 'http://127.0.0.1:8080/';

$(document).ready(function() {
	var sessionToken = $("#checklogintext").val();
		console.log("session token : "+sessionToken);
	if (sessionToken === "null" || sessionToken.trim() === "" || sessionToken === "undifined") {
		$("#hidePurposeFooterBtn").css("display", "none");
		$("#modal-title").html("Log in to continue.");


		setTimeout(function() {
			$("#hidePurposeFooterBtn").css("display", "block");
			$("#modal-title").html(" ");
			$("#conMsg").modal('hide');
			window.location.href = "/directlogin";
		}, 1500);
	}
});



function formatNumber(value, decimal = 0) {
	try {
		num = parseFloat(value);

		if (isNaN(num))
			num = 0;

		num = num.toFixed(decimal);
		num = num.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,")
		return num;
	} catch (err) {
		var x = 0;
		return x.toFixed(decimal);
	}
}

function searchValue(searchStr, valueList) {
	var found = false;
	try {
		searchStr = searchStr.toLowerCase();
		$.each(valueList, function(vi, v) {
			if (v != undefined && v != null && v.toLowerCase().indexOf(searchStr) >= 0)
				found = true;
		});
	} catch (err) {}

	return found;
}


function searchProducts(productList) {
	console.log(productList);
	var srchVal = $("#trans-search-box").val().trim();
	var gridLimit = 50;

	$(".screen-overlay").show();

	if (srchVal == "") {
		$(".trans-srch").animate({
			marginTop : "0px"
		});
		$(".screen-overlay").fadeOut();
		$("#product-grid").fadeOut();
	} else {
		$(".screen-overlay").show();
		$("#product-grid").show();
		$("#product-grid-table tbody").empty();
		$(".trans-srch").animate({
			marginTop : "-10px"
		})

		var printedRowCount = 0;
		$.each(productList, function(pi, p) {
			if (printedRowCount <= gridLimit) {
				var srchValList = [ p.name, p.code, p.itemType.name, p.brand.name, p.selleingPrice ];

				if (searchValue(srchVal, srchValList)) {
					var htmlStr = "<tr data-id='" + p.id + "' class='trans-row'><td style='text-align:center'>" + (++printedRowCount) + "</td>";
					htmlStr += "<td>" + p.name + "</td>";
					htmlStr += "<td>" + p.code + "</td>";
					htmlStr += "<td>" + p.itemType.name + "</td>";
					htmlStr += "<td>" + p.brand.name + "</td>";
					htmlStr += "<td class='number'>" + formatNumber(p.selleingPrice, 2) + "</td></tr>";
					$("#product-grid-table tbody").append(htmlStr);
				}
			} else {
				return false;
			}
		});

		$(".trans-row td").off("click");
		$(".trans-row td").click(function() {
			var productId = $(this).parent().attr("data-id");
			showProductModal(productId, false);
			$("#trans-search-box").val("");
		});
	}
}

function getObjectMapFromList(objList, keyAttr) {
	var objMap = {};
	try {
		$.each(objList, function(oi, o) {
			try {
				var keyVal = o[keyAttr];
				objMap[keyVal] = o;
			} catch (e) {}
		});
	} catch (err) {}
	return objMap;
}

/** ***************************************************************
COMMON EVENT HANDLERS
*************************************************************** **/
$("input[type=number]").on("blur", function() {
	var thisVal = $(this).val();
	var numVal = 0;

	try {
		numVal = parseFloat(thisVal).toFixed(2);
	} catch (e) {}

	numVal = isNaN(numVal) ? 0.00 : thisVal;
});

$("input[type=number]").on("focus", function() {
	$(this).select();
});


function getMaxId(type, id) {
	var code = ""
	var maxId = 0;
	if (isNaN(id)) {
		maxId = 1;
		maxId++;
	} else {
		maxId = id;
		maxId++;
		code = type + "0000" + maxId;
	}
	return code;
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

function formatCurrency(x) {
	x = parseFloat(x).toFixed(2);
    x = x.toString();
    var pattern = /(-?\d+)(\d{3})/;
    while (pattern.test(x))
        x = x.replace(pattern, "$1,$2");
    return x;
}