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
    <script src="js/show_single_poi.js"></script>
</head>

<body>

<% 
String poiId = request.getParameter("id"); 
pageContext.setAttribute("poiId", poiId);
%>

<h1>Point of Interest</h1>
<h5>Team Antes-Klobucar-Handler-Hoelbling</h5>

<h2 id="poiName">POI</h2>

<p id="poiContent"></p>

<p><a href="index.jsp">Back</a></p>

</body>
</html>
<%-- //[END all]--%>
