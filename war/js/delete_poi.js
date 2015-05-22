function deletePoi() {

	$.ajax({
		url : '/resources/poi/' + poiId,
		type : 'DELETE',
		success : function(result) {
			console.log("Successfully deleted POI");
			
			window.location = "/";
		}
	});
}
