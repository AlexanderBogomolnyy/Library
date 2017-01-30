<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="ua.training.library.controller.configuration.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>

<fmt:setLocale value="${sessionScope[Attributes.LOCALE]}" />
<fmt:setBundle basename="content" var="message"/>
<c:set var="user" scope="page" value="${sessionScope[Attributes.LOGINED_USER]}"/>
<c:set var="url_base" scope="page" value="${pageContext.request.contextPath}${Paths.BASE}"/>
<c:set var="user_scope_url" scope="page" value="${url_base}${Paths.DELIMITER}${user.role.name().toLowerCase()}"/>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <link rel="stylesheet" type="text/css" href='<c:url value="/css/main.css"/>' />
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
        <th><fmt:message key="catalog.table.order_title" bundle="${message}"/></th>
    </tr>
    </thead>
    <tbody>
    <%! Integer counter = 0; %>
    <c:forEach items="${requestScope[Attributes.CATALOG]}" var="catalog">
        <% if (counter%2 == 0) {%><tr class="even"><%} else {%><tr><%}%>
        <td>${catalog.title}</td>
        <td>${catalog.author}</td>
        <td>${catalog.yearOfPublication}</td>
        <td>${catalog.category.name}</td>
        <td>${catalog.isbn}</td>
        <td>${catalog.amountAvailable}</td>
        <td>
            <c:set var="catalog_path" scope="page"
                   value="${user_scope_url}${Paths.CATALOG}${Paths.DELIMITER}${catalog.id}${Paths.ORDER}"/>
            <a href="${catalog_path}"><fmt:message key="catalog.table.order" bundle="${message}"/></a>
        </td>
    </tr>
        <% counter++; %>
    </c:forEach>
    </tbody>
</table>
<jsp:include page="_footer.jsp"/>
</body>
</html>