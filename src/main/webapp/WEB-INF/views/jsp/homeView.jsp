<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css" />
  <title>Home Page</title>
</head>
<body>

<jsp:include page="_header.jsp"/>
<jsp:include page="_menu.jsp"/>

<div class="headline"><h3>Home Page</h3></div>

<jsp:include page="_popup_message.jsp"/>

This is Library Service web application for library administration. <br><br>
<b>It includes the following functions:</b>
<ul>
  <li>Login</li>
  <li>LogOut</li>
  <li>Storing administrator information in cookies</li>
  <li>Getting books list</li>
  <li>Getting catalog</li>
  <li>Getting administrator list</li>
  <li>Getting visitor list</li>
  <li>Adding/updating/removing book information</li>
  <li>Adding/updating/removing visitor information</li>
  <li>Issuing book on subscription or in reading room</li>
  <li>Returning book from subscription or reading room</li>
</ul>

<jsp:include page="_footer.jsp"/>

</body>
</html>

