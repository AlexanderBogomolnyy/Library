<%@ page import="ua.training.library.controller.configuration.*" %>
<%@ page import="ua.training.library.model.entity.states.Role" %>
<%@ page import="ua.training.library.controller.i18n.Languages"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="view" uri="http://ua.training.library/webapp/views/tlds/viewtags" %>

<fmt:setLocale value="${sessionScope[Attributes.LOCALE]}"/>
<fmt:setBundle basename="content" var="message"/>

<c:set var="url_base" scope="page" value="${pageContext.request.contextPath}${Paths.BASE}"/>
<c:set var="user_scope_url" scope="page" value="${url_base}${Paths.DELIMITER}${user.role.name().toLowerCase()}"/>
<c:set var="catalog" scope="page" value="${requestScope[Attributes.CATALOG]}"/>
<c:set var="order_path" scope="page" value="${requestScope[Attributes.ORDER_PATH]}"/>

<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
  <link rel="stylesheet" type="text/css" href='<c:url value="/css/main.css"/>' />
  <link rel="stylesheet" type="text/css" href='<c:url value="/css/frames.css"/>' />
  <title><fmt:message key="order.title" bundle="${message}" /></title>
</head>
<body>

<jsp:include page="_header.jsp"/>
<jsp:include page="_menu.jsp"/>
<jsp:include page="_popup_message.jsp"/>

<div id="window_container">
  <div id="window_frame">
    <form action="${order_path}" method="POST" id="subForm">
      <p><strong><fmt:message key="order.form_title" bundle="${message}" /></strong></p>
      <div class="form_labels">
        <label for="title"><fmt:message key="catalog.table.title" bundle="${message}" /></label><br/><input type="text" name="title" value="${catalog.title}" id="title" readonly/><br/>
        <label for="author"><fmt:message key="catalog.table.authors" bundle="${message}" /></label><br/><input type="text" name="author" value="${catalog.author}" id="author" readonly/><br/>
        <label for="year_of_publication"><fmt:message key="catalog.table.year_of_publication" bundle="${message}" /></label><br/><input type="text" name="year_of_publication" value="${catalog.yearOfPublication}" id="year_of_publication" readonly/><br/>
        <label for="date_of_issue"><fmt:message key="order.date_of_issue" bundle="${message}" /></label><br/><input required type="datetime-local" name="date_of_issue" value="${order.dataOfIssue}" id="date_of_issue"/><br/>
        <label for="expected_date_of_return"><fmt:message key="order.expected_date_of_return" bundle="${message}" /></label><br/><input required type="datetime-local" name="expected_date_of_return" value="${order.expectedDateOfReturn}" id="expected_date_of_return"/><br/>
        <label for="expected_book_location"><fmt:message key="order.expected_book_location" bundle="${message}" /></label><br/><select size="1" name="expected_book_location" id="expected_book_location" >
          <option selected value="ON_HAND">On hand</option>
          <option value="READING_ROOM">Reading room</option>
      </select><br/>
      </div>
      <div class="button_box">
        <input type="submit" value="<fmt:message key="order.order_button" bundle="${message}" />" id="button" class="button-link"/>
      </div>
    </form>
  </div>
</div>
<jsp:include page="_footer.jsp"/>

</body>
</html>