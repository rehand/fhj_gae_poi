$( document ).ready(function() {
	$("#sendPoi").click(postPoi);
});

function postPoi() {
	//Wrap up form inputs into an object
	var jsonString = $('#newPoiForm').serializeArray().reduce(function(a, x) { a[x.name] = x.value; return a; }, {});
	var newPoi = JSON.stringify(jsonString);
	
	$.post("/resources/poi", newPoi, function(response) {
		console.log(response);
		window.location = "/";
	}, 'json');
}