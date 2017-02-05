<%@ page import="ua.training.library.controller.configuration.*" %>
<%@ page import="ua.training.library.model.entity.states.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="view" uri="http://ua.training.library/webapp/views/tlds/viewtags" %>

<fmt:setLocale value="${sessionScope[Attributes.LOCALE]}"/>
<fmt:setBundle basename="content" var="message"/>
<c:set var="user" scope="page" value="${sessionScope[Attributes.LOGINED_USER]}"/>
<c:set var="client" scope="page" value="${Role.CLIENT}"/>
<c:set var="url_base" scope="page" value="${pageContext.request.contextPath}${Paths.BASE}"/>
<c:set var="user_scope_url" scope="page" value="${url_base}${Paths.DELIMITER}${user.role.name().toLowerCase()}"/>
<c:set var="catalog_list" scope="page" value="${requestScope[Attributes.CATALOG_LIST]}"/>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <link rel="stylesheet" type="text/css" href='<c:url value="/css/main.css"/>'/>
    <title><fmt:message key="catalog.title" bundle="${message}"/></title>
</head>
<body>

<jsp:include page="_header.jsp"/>
<jsp:include page="_menu.jsp"/>

<div class="headline"><h3><fmt:message key="catalog.title" bundle="${message}"/></h3></div>

<jsp:include page="_popup_message.jsp"/>

<table cellpadding="0" class="print_table">
    <thead>
    <tr>
        <th><fmt:message key="catalog.table.title" bundle="${message}"/></th>
        <th><fmt:message key="catalog.table.authors" bundle="${message}"/></th>
        <th><fmt:message key="catalog.table.year_of_publication" bundle="${message}"/></th>
        <th><fmt:message key="catalog.table.category" bundle="${message}"/></th>
        <th><fmt:message key="catalog.table.isbn" bundle="${message}"/></th>
        <th><fmt:message key="catalog.table.amount_available" bundle="${message}"/></th>
        <view:access-by-role user="${user}" roles="${client}">
            <th><fmt:message key="catalog.table.order_title" bundle="${message}"/></th>
        </view:access-by-role>
    </tr>
    </thead>
    <tbody>
    <c:set var="count" value="${0}" scope="page"/>
    <c:forEach items="${catalog_list}" var="catalog">
        <c:choose>
            <c:when test="${count%2==0}">
                <tr class="even">
            </c:when>
            <c:otherwise>
                <tr>
            </c:otherwise>
        </c:choose>
        <c:set var="count" value="${count+1}" scope="page"/>
        <td>${catalog.title}</td>
        <td>${catalog.author}</td>
        <td>${catalog.yearOfPublication}</td>
        <td>${catalog.category.name}</td>
        <td>${catalog.isbn}</td>
        <td>${catalog.amountAvailable}</td>
        <view:access-by-role user="${user}" roles="${client}">
            <td>
                <c:set var="catalog_path" scope="page"
                       value="${user_scope_url}${Paths.CATALOG}${Paths.DELIMITER}${catalog.id}${Paths.ORDER}"/>
                <a href="${catalog_path}"><fmt:message key="catalog.table.order" bundle="${message}"/></a>
            </td>
        </view:access-by-role>
        </tr>
    </c:forEach>
    </tbody>
</table>
<jsp:include page="_footer.jsp"/>
</body>
</html>