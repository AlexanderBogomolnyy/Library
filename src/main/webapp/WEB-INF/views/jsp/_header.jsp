<%@ page import="ua.training.library.controller.configuration.*" %>
<%@ page import="ua.training.library.model.entity.states.Role" %>
<%@ page import="ua.training.library.controller.i18n.Languages" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="view" uri="http://ua.training.library/webapp/views/tlds/viewtags" %>

<fmt:setLocale value="${sessionScope[Attributes.LOCALE]}"/>
<fmt:setBundle basename="content" var="message"/>

<c:set var="locales" scope="page" value="${Languages.SUPPORTED_LOCALES}"/>
<c:set var="user" scope="page" value="${sessionScope[Attributes.LOGINED_USER]}"/>
<c:set var="librarian" scope="page" value="${Role.LIBRARIAN}"/>
<c:set var="client" scope="page" value="${Role.CLIENT}"/>
<c:set var="url_base" scope="page" value="${pageContext.request.contextPath}${Paths.BASE}"/>
<c:set var="profile_link" scope="page"
       value="${url_base}${Paths.DELIMITER}${user.role.name().toLowerCase()}${Paths.PROFILE}"/>

<div id="header">
    <h1 class="title"><fmt:message key="application.title" bundle="${message}"/></h1>

    <div class="user">
        <view:access-by-role user="${user}" roles="${librarian},${client}">
            <b><fmt:message key="application.greeting" bundle="${message}"/>&nbsp;<a
                    href="${profile_link}">${user.login}</a></b>
        </view:access-by-role>
        <view:access-by-role user="${user}" noUser="true">
            <a href="${url_base}${Paths.REGISTRATION}"><fmt:message key="application.registration"
                                                                    bundle="${message}"/></a>
        </view:access-by-role>
    </div>
    <div class="language">
        <c:forEach items="${locales}" var="locale"> &nbsp
            <a class="button-link" style="padding: 3px 15px;" href="${url_base}${Paths.HOME}${locale.language}">
                    ${locale.language}
            </a>
        </c:forEach>
    </div>
</div>
