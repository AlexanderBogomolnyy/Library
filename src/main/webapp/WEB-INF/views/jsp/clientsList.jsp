<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
         import="ua.training.library.controller.configuration.Attributes" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope[Attributes.LOCALE]}"/>
<fmt:setBundle basename="content" var="message"/>

<c:set var="client_list" value="${requestScope[Attributes.LIST_OF_CLIENTS]}" scope="page"/>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <link rel="stylesheet" type="text/css" href='<c:url value="/css/main.css"/>'/>
    <title><fmt:message key="clients.title" bundle="${message}"/></title>
</head>
<body>

<jsp:include page="_header.jsp"/>
<jsp:include page="_menu.jsp"/>

<div class="headline"><h3><fmt:message key="clients.title" bundle="${message}"/></h3></div>

<jsp:include page="_popup_message.jsp"/>

<table cellpadding="0" class="print_table">
    <thead>
    <tr>
        <th><fmt:message key="clients.table.client_id" bundle="${message}"/></th>
        <th><fmt:message key="clients.table.login" bundle="${message}"/></th>
        <th><fmt:message key="clients.table.first_name" bundle="${message}"/></th>
        <th><fmt:message key="clients.table.last_name" bundle="${message}"/></th>
        <th><fmt:message key="clients.table.email" bundle="${message}"/></th>
        <th><fmt:message key="clients.table.status" bundle="${message}"/></th>
    </tr>
    </thead>
    <tbody>

    <c:set var="count" value="${0}" scope="page"/>
    <c:forEach items="${client_list}" var="client">
        <c:choose>
            <c:when test="${count%2==0}">
                <tr class="even">
            </c:when>
            <c:otherwise>
                <tr>
            </c:otherwise>
        </c:choose>
        <c:set var="count" value="${count+1}" scope="page"/>
        <td>${client.id}</td>
        <td>${client.login}</td>
        <td>${client.firstName}</td>
        <td>${client.lastName}</td>
        <td>${client.email}</td>
        <td>${client.status}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<jsp:include page="_footer.jsp"/>
</body>
</html>