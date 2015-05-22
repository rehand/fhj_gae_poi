<%-- //[START all]--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>

<%@ page import="java.util.List" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>

<%
    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();
    String userEmail = "";
    if (user != null) {
    	userEmail = user.getEmail();
    }
%>

<head>
    <link type="text/css" rel="stylesheet" href="/css/main.css"/>
    <script src="js/jquery-2.1.4.js"></script>
    <script src="js/delete_poi.js"></script>
    <script src="js/show_single_poi.js"></script>
    <script type="text/javascript">
    var userEmail = '<%= userEmail %>';
    </script>
</head>

<body>

<h1>Point of Interest</h1>
<h5>Team Antes-Klobucar-Handler-Hoelbling</h5>

<h2 id="poiName">POI</h2>

<p id="poiContent"></p>

<p><a href="index.jsp">Back</a></p>

</body>
</html>
<%-- //[END all]--%>
