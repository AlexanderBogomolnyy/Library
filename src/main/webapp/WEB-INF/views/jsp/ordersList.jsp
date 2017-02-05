<%@ page import="ua.training.library.controller.configuration.*" %>
<%@ page import="ua.training.library.controller.i18n.Languages" %>
<%@ page import="ua.training.library.model.entity.states.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="view" uri="http://ua.training.library/webapp/views/tlds/viewtags" %>

<fmt:setLocale value="${sessionScope[Attributes.LOCALE]}"/>
<fmt:setBundle basename="content" var="message"/>

<c:if test="${sessionScope[Attributes.LOCALE].toLanguageTag() eq Languages.SUPPORTED_LOCALES[1].toLanguageTag()}">
    <fmt:setLocale value="${Languages.SUPPORTED_LOCALES[2]}"/>
</c:if>

<c:set var="user" scope="page" value="${sessionScope[Attributes.LOGINED_USER]}"/>
<c:set var="librarian" scope="page" value="${Role.LIBRARIAN}"/>
<c:set var="url_base" scope="page" value="${pageContext.request.contextPath}${Paths.BASE}"/>
<c:set var="user_scope_url" scope="page" value="${url_base}${Paths.DELIMITER}${user.role.name().toLowerCase()}"/>
<c:set var="list_of_orders" scope="page" value="${requestScope[Attributes.LIST_OF_ORDERS]}"/>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <link rel="stylesheet" type="text/css" href='<c:url value="/css/main.css"/>'/>
    <title><fmt:message key="orders.title" bundle="${message}"/></title>
</head>
<body>

<jsp:include page="_header.jsp"/>
<jsp:include page="_menu.jsp"/>

<div class="headline"><h3><fmt:message key="orders.title" bundle="${message}"/></h3></div>

<jsp:include page="_popup_message.jsp"/>

<table cellpadding="0" class="print_table">
    <thead>
    <tr>
        <th><fmt:message key="orders.table.order_id" bundle="${message}"/></th>
        <view:access-by-role user="${user}" roles="${librarian}">
            <th><fmt:message key="orders.table.client_name" bundle="${message}"/></th>
        </view:access-by-role>
        <th><fmt:message key="orders.table.book_description" bundle="${message}"/></th>
        <th><fmt:message key="orders.table.date_of_issue" bundle="${message}"/></th>
        <th><fmt:message key="orders.table.expected_date_of_return" bundle="${message}"/></th>
        <th><fmt:message key="orders.table.date_of_return" bundle="${message}"/></th>
        <th><fmt:message key="orders.table.expected_book_location" bundle="${message}"/></th>
        <th><fmt:message key="orders.table.status" bundle="${message}"/></th>
        <view:access-by-role user="${user}" roles="${librarian}">
            <th><fmt:message key="orders.table.process_the_order" bundle="${message}"/></th>
        </view:access-by-role>
    </tr>
    </thead>
    <tbody>

    <c:set var="count" value="${0}" scope="page"/>
    <c:forEach items="${list_of_orders}" var="order">
        <c:choose>
            <c:when test="${count%2==0}">
                <tr class="even">
            </c:when>
            <c:otherwise>
                <tr>
            </c:otherwise>
        </c:choose>
        <c:set var="count" value="${count+1}" scope="page"/>
        <td>${order.id}</td>
        <view:access-by-role user="${user}" roles="${librarian}">
            <td>${order.user.firstName}&nbsp;${order.user.lastName}</td>
        </view:access-by-role>
        <td>${order.catalog.author} : ${order.catalog.title}</td>
        <td><fmt:parseDate value="${order.dateOfIssue}" pattern="yyyy-MM-dd"
                           var="dateOfIssue" type="date"/>
            <fmt:formatDate value="${dateOfIssue}" type="date" dateStyle="short"/>
        </td>
        <td><fmt:parseDate value="${order.expectedDateOfReturn}" pattern="yyyy-MM-dd"
                           var="expectedDateOfReturn" type="date"/>
            <fmt:formatDate value="${expectedDateOfReturn}" type="date" dateStyle="short"/>
        </td>
        <td><c:choose>
            <c:when test="${order.dateOfReturn == null}">-</c:when>
            <c:otherwise>
                <fmt:parseDate value="${order.dateOfReturn}" pattern="yyyy-MM-dd"
                               var="dateOfReturn" type="date"/>
                <fmt:formatDate value="${dateOfReturn}" type="date" dateStyle="short"/>
            </c:otherwise>
        </c:choose>
        </td>
        <td>${order.expectedBookLocation}</td>
        <td>${order.type}</td>
        <view:access-by-role user="${user}" roles="${librarian}">
            <td>
                <c:choose>
                    <c:when test="${(order.type eq OrderType.COMPLETED) || (order.type eq OrderType.IN_PROCESSING)}">-</c:when>
                    <c:otherwise>
                        <c:set var="catalog_path" scope="page"
                               value="${user_scope_url}${Paths.ORDERS}${Paths.DELIMITER}${order.id}${Paths.LIBRARY_RESPONSE}"/>
                        <a href="${catalog_path}"><fmt:message key="orders.table.process" bundle="${message}"/></a>
                    </c:otherwise>
                </c:choose>
            </td>
        </view:access-by-role>
        </tr>
    </c:forEach>
    </tbody>
</table>
<jsp:include page="_footer.jsp"/>
</body>
</html>