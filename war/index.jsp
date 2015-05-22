<%-- //[START all]--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>

<%@ page import="java.util.List" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="/css/main.css"/>
    <script src="js/jquery-2.1.4.js"></script>
    <script src="js/show_pois.js"></script>
    <script src="js/post_poi.js"></script>
</head>

<body>

<h1>Point of Interest</h1>
<h5>Team Antes-Klobucar-Handler-Hoelbling</h5>

<h2>Vorhandene POIs</h2>
<section id="poisList">
</section>

<h2>Neuen POI anlegen</h2>

<%
    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();
    if (user != null) {
        pageContext.setAttribute("user", user);
%>

<p>
	<form id="newPoiForm">
	Name:<br>
	<input type="text" name="name">
	<br>
	Latitude:<br>
	<input type="text" name="latitude">
	<br>
	Longitude:<br>
	<input type="text" name="longitude">
	<br>
	Description:<br>
	<input type="text" name="description">
	<br>
	Category:<br>
	<input type="text" name="category">
	<br>
	<button type="button" id="sendPoi">Send</button>
	</form>
    <a href="<%= userService.createLogoutURL(request.getRequestURI()) %>">Logout</a></p>
<%
    } else {
%>
<p>
    <a href="<%= userService.createLoginURL(request.getRequestURI()) %>">Login</a>
    to post a POI.</p>
<%
    }
%>

</body>
</html>
<%-- //[END all]--%>
