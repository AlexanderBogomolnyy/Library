<%@ page import="ua.training.library.controller.configuration.Attributes" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${requestScope[Attributes.ERROR_MESSAGE] != null}"><p class="error">${requestScope[Attributes.ERROR_MESSAGE]}</p></c:if>
<c:if test="${requestScope[Attributes.SUCCESS_MESSAGE] != null}"><p class="error">${requestScope[Attributes.SUCCESS_MESSAGE]}</p></c:if>