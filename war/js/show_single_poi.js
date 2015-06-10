$(document).ready(function() {
	poiId = getParameterByName("id");
	
	console.log("Try to get POI for ID " + poiId);
	
	$.getJSON("resources/poi/" + poiId, showPoi);
});

function showPoi(data) {
	console.log("Successfully loaded POI");

	$("#poiName").replaceWith("<h2>POI " + data.name + "</h2>");
	
	if(data.creator === userEmail) {
		$("#poiContent").append('<form id="updatePoiForm">'
							+	'Name:<br/>'
		 					+	'<input type="text" name="name"><br/>'
							+	'Latitude:<br/>'
							+	'<input type="number" step="any" min="-90" max="90" name="lat"><br/>'
							+	'Longitude:<br/>'
							+	'<input type="number" step="any" min="-180" max="180" name="lon"><br/>'
							+	'Description:<br/>'
							+	'<input type="text" name="description"><br/>'
							+	'Category:<br/>'
							+	'<input type="text" name="category"><br/>'
							+	'<button type="submit">Update</button></form>');

		for (var fieldName in data) {
			$('input[name="' + fieldName + '"]').val(data[fieldName]);
		}
		$('#updatePoiForm').submit(updatePoi);

		$("#poiContent").append("<br><button type='button' id='deletePoi'>Delete POI</button>");
		$("#deletePoi").click(deletePoi);
	} else {
		$("#poiContent").append("<p><strong>Creator:</strong> " + data.creator + "</p>");
		$("#poiContent").append("<p><strong>Category:</strong> " + data.category + "</p>");
		$("#poiContent").append("<p><strong>Description:</strong> " + data.description + "</p>");
		$("#poiContent").append("<p><strong>Latitude:</strong> " + data.lat + "</p>");
		$("#poiContent").append("<p><strong>Longitude:</strong> " + data.lon + "</p>");
	}
}

function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}