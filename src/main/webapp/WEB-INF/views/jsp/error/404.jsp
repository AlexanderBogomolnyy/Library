<%@ page import="ua.training.library.controller.configuration.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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

<jsp:include page="/WEB-INF/views/jsp/_header.jsp"/>
<jsp:include page="/WEB-INF/views/jsp/_menu.jsp"/>

<div id="error_box">
  <div class="cell">
    <div class="content_img">
      <img src="<c:url value="/images/crash_img.png"/>"/>
    </div>
  </div>
  <div class="cell">
    <div class="content">
      <h1> 404 Error </h1>
      <h2> Sorry, the page not found </h2>

      <h3> The link you followed probably broken, or the page has been removed.</h3>

      <p><a class="button-link" href="<c:url value="${pageContext.request.contextPath}${Paths.BASE}${Paths.HOME}"/>"><fmt:message key="error.return_home" bundle="${message}"/></a></p>
    </div>
  </div>
</div>
<jsp:include page="/WEB-INF/views/jsp/_footer.jsp"/>
</body>
</html>