function updatePoi(e) {
	e.preventDefault();
	
	var jsonString = $('#updatePoiForm').serializeArray().reduce(
			function(a, x) {
				a[x.name] = x.value;
				return a;
			}, {});
	var newPoi = JSON.stringify(jsonString);

	$.ajax({
		url : '/resources/poi/' + poiId,
		data: newPoi,
		type : 'POST',
		success : function(result) {
			console.log("Successfully updated POI");
			window.location = "/poi.jsp?id=" + poiId;
		}
	});
	
	return false;
}
