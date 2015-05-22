$(document).ready(function() {
	console.log("Try to load all POIs");
	
	$.getJSON("resources/poi", showPois);
});

function showPois(data) {
	console.log("Successfully loaded all POIs");
	
	var items = [];
	$.each(data, function(key, val) {
		items.push("<li id='" + val.id + "'><a href='poi.jsp?id=" + val.id + "'>" + val.name + " by " + val.creator  + "</a></li>");
	});

	$("<ul/>", {
		html : items.join("")
	}).appendTo("#poisList");
	
	window.location = "/";
}