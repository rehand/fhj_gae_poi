$(document).ready(function() {
	var poiId = getParameterByName("id");
	
	console.log("Try to get POI for ID " + poiId);
	
	$.getJSON("resources/poi/" + poiId, showPoi);
});

function showPoi(data) {
	console.log("Successfully loaded POI");

	$("#poiName").replaceWith("<h2>POI " + data.name + "</h2>");
	$("#poiContent").append("<p><strong>Creator:</strong> " + data.creator + "</p>");
	$("#poiContent").append("<p><strong>Category:</strong> " + data.category + "</p>");
	$("#poiContent").append("<p><strong>Description:</strong> " + data.description + "</p>");
	$("#poiContent").append("<p><strong>Latitude:</strong> " + data.lat + "</p>");
	$("#poiContent").append("<p><strong>Longitude:</strong> " + data.lon + "</p>");
}

function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}