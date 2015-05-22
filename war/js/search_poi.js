$(document).ready(function() {
	$("#searchPoi").click(search);
});

function search() {
	var nameSearchString = $("#searchName").val();
	
	console.log("Try to get POIs for name " + nameSearchString);
	
	$.getJSON("resources/poi?name=" + nameSearchString, showSearchResults);
}

function showSearchResults(data) {
	console.log("Successfully loaded POIs for search string");

	var items = [];
	$.each(data, function(key, val) {
		items.push("<li id='" + val.id + "'><a href='poi.jsp?id=" + val.id + "'>" + val.name + " by " + val.creator  + "</a></li>");
	});
	
	$("#poisSearchResults").empty();
	
	$("<ul/>", {
		html : items.join("")
	}).appendTo("#poisSearchResults");
}