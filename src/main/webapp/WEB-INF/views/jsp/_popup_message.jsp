<%@ page import="ua.training.library.controller.configuration.Attributes" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${sessionScope[Attributes.ERROR_MESSAGE] != null}">
    <p class="error">${sessionScope[Attributes.ERROR_MESSAGE]}</p>
    <c:remove var="errorMessage" scope="session"/>
</c:if>
<c:if test="${sessionScope[Attributes.SUCCESS_MESSAGE] != null}">
    <p class="success">${sessionScope[Attributes.SUCCESS_MESSAGE]}</p>
    <c:remove var="successMessage" scope="session"/>
</c:if>

