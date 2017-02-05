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
<c:set var="list_of_responses" scope="page" value="${requestScope[Attributes.LIST_OF_LIBRARIAN_RESPONSES]}"/>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <link rel="stylesheet" type="text/css" href='<c:url value="/css/main.css"/>'/>
    <title><fmt:message key="library_response_list.title" bundle="${message}"/></title>
</head>
<body>

<jsp:include page="_header.jsp"/>
<jsp:include page="_menu.jsp"/>

<div class="headline"><h3><fmt:message key="library_response_list.table.title" bundle="${message}"/></h3></div>

<jsp:include page="_popup_message.jsp"/>

<table cellpadding="0" class="print_table">
    <thead>
    <tr>
        <th><fmt:message key="library_response_list.table.response_id" bundle="${message}"/></th>
        <th><fmt:message key="library_response_list.table.order_id" bundle="${message}"/></th>
        <th><fmt:message key="library_response_list.table.client_name" bundle="${message}"/></th>
        <th><fmt:message key="library_response_list.table.book_description" bundle="${message}"/></th>
        <th><fmt:message key="library_response_list.table.processing_date" bundle="${message}"/></th>
        <th><fmt:message key="library_response_list.table.book_item" bundle="${message}"/></th>
        <th><fmt:message key="library_response_list.table.book_location" bundle="${message}"/></th>
        <th><fmt:message key="library_response_list.table.date_of_return" bundle="${message}"/></th>
        <th><fmt:message key="library_response_list.table.update" bundle="${message}"/></th>
    </tr>
    </thead>
    <tbody>
    <c:set var="count" value="${0}" scope="page"/>
    <c:forEach items="${list_of_responses}" var="response">
        <c:choose>
            <c:when test="${count%2==0}">
                <tr class="even">
            </c:when>
            <c:otherwise>
                <tr>
            </c:otherwise>
        </c:choose>
        <c:set var="count" value="${count+1}" scope="page"/>
        <td>${response.id}</td>
        <td>${response.order.id}</td>
        <td>${response.order.user.firstName}&nbsp;${response.order.user.lastName}</td>
        <td>${response.catalog.author} : ${response.catalog.title}</td>
        <td>
            <fmt:parseDate value="${response.processingDate}" pattern="yyyy-MM-dd"
                           var="processingDate" type="date"/>
            <fmt:formatDate value="${processingDate}" type="date" dateStyle="short"/>
        </td>
        <td>${response.bookLibraryIdentifier}</td>
        <td>${response.location}</td>
        <td>
            <c:choose>
                <c:when test="${response.dateOfReturn == null}">-</c:when>
                <c:otherwise>
                    <fmt:parseDate value="${response.dateOfReturn}" pattern="yyyy-MM-dd"
                                   var="dateOfReturn" type="date"/>
                    <fmt:formatDate value="${dateOfReturn}" type="date" dateStyle="short"/>
                </c:otherwise>
            </c:choose>
        </td>
        <td>
            <c:choose>
                <c:when test="${response.order.type eq OrderType.COMPLETED}">-</c:when>
                <c:otherwise>
                    <c:set var="catalog_path" scope="page"
                           value="${user_scope_url}${Paths.LIBRARY_RESPONSES}${Paths.DELIMITER}${response.id}${Paths.COMPLETING}"/>
                    <a href="${catalog_path}"><fmt:message key="library_response_list.table.update_link"
                                                           bundle="${message}"/></a>
                </c:otherwise>
            </c:choose>
        </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<jsp:include page="_footer.jsp"/>
</body>
</html>