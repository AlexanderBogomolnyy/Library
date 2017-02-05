<%@ page import="ua.training.library.controller.configuration.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope[Attributes.LOCALE]}"/>
<fmt:setBundle basename="content" var="message"/>
<c:set var="url_base" scope="page" value="${pageContext.request.contextPath}${Paths.BASE}"/>

<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
  <link rel="stylesheet" type="text/css" href='<c:url value="/css/main.css"/>' />
  <link rel="stylesheet" type="text/css" href='<c:url value="/css/frames.css"/>' />
  <title><fmt:message key="profile.title" bundle="${message}"/></title>
</head>
<body>

<jsp:include page="_header.jsp"/>
<jsp:include page="_menu.jsp"/>

<div class="headline"><h3><fmt:message key="profile.title" bundle="${message}"/></h3></div>

<jsp:include page="_popup_message.jsp"/>

<c:set var="user" scope="page" value="${sessionScope[Attributes.LOGINED_USER]}"/>
<div id="window_container" style="width: 440px">
  <div id="window_frame" >
   <h4> <fmt:message key="profile.name" bundle="${message}"/> ${user.firstName} ${user.lastName}</h4>
   <h4> <fmt:message key="profile.login" bundle="${message}"/> ${user.login}</h4>
   <h4> <fmt:message key="profile.email" bundle="${message}"/> ${user.email}</h4>
   <h4> <fmt:message key="profile.role" bundle="${message}"/> ${user.role}</h4>
   <h4> <fmt:message key="profile.status" bundle="${message}"/> ${user.status}</h4>
   <div class="link_box"> <a class="button-link" href="${url_base}${Paths.LOGOUT}"><fmt:message key="profile.logout" bundle="${message}"/></a></div>
  </div>
</div>
<jsp:include page="_footer.jsp"/>
</body>
</html>