<%@ page import="ua.training.library.controller.configuration.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope[Attributes.LOCALE]}"/>
<fmt:setBundle basename="content" var="message"/>

<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css" />
  <title><fmt:message key="home.title" bundle="${message}"/></title>
</head>
<body>

<jsp:include page="_header.jsp"/>
<jsp:include page="_menu.jsp"/>

<jsp:include page="_popup_message.jsp"/>

<div id="title" style="text-align: center"> <br> <fmt:message key="home.greetting" bundle="${message}"/><br><br>
</div>
<jsp:include page="_footer.jsp"/>

</body>
</html>

