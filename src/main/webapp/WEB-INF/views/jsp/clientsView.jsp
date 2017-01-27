<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="ua.training.library.controller.configuration.Attributes" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>

<fmt:setLocale value="${sessionScope[Attributes.LOCALE]}" />
<fmt:setBundle basename="content" var="message"/>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <link rel="stylesheet" type="text/css" href='<c:url value="/css/main.css"/>' />
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
    <%! Integer counter = 0; %>
    <c:forEach items="${requestScope[Attributes.LIST_OF_CLIENTS]}" var="client">
        <% if (counter%2 == 0) {%><tr class="even"><%} else {%><tr><%}%>
        <td>${client.id}</td>
        <td>${client.login}</td>
        <td>${client.firstName}</td>
        <td>${client.lastName}</td>
        <td>${client.email}</td>
        <td>${client.status}</td>
    </tr>
        <% counter++; %>
    </c:forEach>
    </tbody>
</table>
<jsp:include page="_footer.jsp"/>
</body>
</html>