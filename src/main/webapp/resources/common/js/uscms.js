//s.sugashan
//common js

/** ***************************************************************************** **/
/** CHECK LOGIN FUNCTION											              **/
/** ***************************************************************************** **/

$(document).ready(function(){
	var sessionToken = $("#checklogintext").val();
	console.log(sessionToken);
	if(sessionToken === "null" || sessionToken.trim() === "" || sessionToken === "undifined" ){
		$("#hidePurposeFooterBtn").css("display", "none");
		$("#modal-title").html("Log in to continue.");
		
		
		setTimeout(function(){
			$("#hidePurposeFooterBtn").css("display", "block");
			$("#modal-title").html(" ");
			$("#conMsg").modal('hide');
			window.location.href = "/directlogin";
		}, 1500);
	}
});


/** ***************************************************************************** **/
/** ALERT MESSAGE FUNCTIONS												          **/
/** ***************************************************************************** **/
function alertMessage(aMes){
	if(aMes==info){
		$("#res-msg").removeClass("alert-success").removeClass("alert-danger").addClass("alert-info");
		$("#res-msg strong").html("Fill all fields and hit Save");
	}
	else if(aMes==danger){
		$("#res-msg").removeClass("alert-success").removeClass("alert-info").addClass("alert-danger");
	}
	else if(aMes==success){
		$("#res-msg").removeClass("alert-info").removeClass("alert-danger").addClass("alert-success");
	}
	return false;
}