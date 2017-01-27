<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="ua.training.library.controller.configuration.Attributes" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope[Attributes.LOCALE]}" />
<fmt:setBundle basename="content" var="message"/>

<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
  <link rel="stylesheet" type="text/css" href='<c:url value="/css/main.css"/>' />
  <title><fmt:message key="error.title" bundle="${message}"/> </title>
</head>
<body>

<jsp:include page="_header.jsp"/>
<jsp:include page="_menu.jsp"/>

<div class="error" >
  <h1>ACCESS DENIED</h1>
  <h3>Sorry, you have no permission!</h3>
</div>

<jsp:include page="_footer.jsp"/>
</body>
</html>