<%@ page import="ua.training.library.controller.configuration.*" %>
<%@ page import="ua.training.library.model.entity.states.Role" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="view" uri="http://ua.training.library/webapp/views/tlds/viewtags" %>

<fmt:setLocale value="${sessionScope[Attributes.LOCALE]}"/>
<fmt:setBundle basename="content" var="message"/>

<c:set var="user" scope="page" value="${sessionScope[Attributes.LOGINED_USER]}"/>
<c:set var="librarian" scope="page" value="${Role.LIBRARIAN}"/>
<c:set var="client" scope="page" value="${Role.CLIENT}"/>

<div id="main_menu">
    <ul>
        <li><a href="${pageContext.request.contextPath}${Paths.HOME}"> <fmt:message key="menu.home"
                                                                                    bundle="${message}"/> </a>
        </li>
        <view:access-by-role user="${user}" roles="${librarian}, ${client}">
            <li>
                <a href="${pageContext.request.contextPath}${Paths.CATALOG}"> <fmt:message key="menu.catalog"
                                                                                           bundle="${message}"/> </a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}${Paths.ORDERS}"> <fmt:message key="menu.list_of_orders"
                                                                                          bundle="${message}"/> </a>
            </li>
        </view:access-by-role>
        <view:access-by-role user="${user}" roles="${librarian}">
            <li>
                <a href="${pageContext.request.contextPath}${Paths.CLIENTS}"> <fmt:message key="menu.list_of_clients"
                                                                                           bundle="${message}"/> </a>
            </li>
        </view:access-by-role>
        <view:access-by-role user="${user}" roles="${librarian}, ${client}">
            <li style="float:right">
                <a href="${pageContext.request.contextPath}${Paths.LOGOUT}"> <fmt:message key="menu.logout"
                                                                                          bundle="${message}"/> </a>
            </li>
        </view:access-by-role>
        <view:access-by-role user="${user}" noUser="true">
        <li style="float:right">
            <a href="${pageContext.request.contextPath}${Paths.LOGIN}"> <fmt:message key="menu.login"
                                                                                     bundle="${message}"/> </a>
        </li>
        </view:access-by-role>
    </ul>
</div>