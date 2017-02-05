<%@ page import="ua.training.library.controller.configuration.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope[Attributes.LOCALE]}"/>
<fmt:setBundle basename="content" var="message"/>
<c:set var="url_base" scope="page" value="${pageContext.request.contextPath}${Paths.BASE}"/>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <link rel="stylesheet" type="text/css" href='<c:url value="/css/main.css"/>'/>
    <link rel="stylesheet" type="text/css" href='<c:url value="/css/frames.css"/>'/>
    <title><fmt:message key="login.title" bundle="${message}"/></title>
</head>
<body>

<jsp:include page="_header.jsp"/>
<jsp:include page="_menu.jsp"/>

<div class="headline"><h3><fmt:message key="login.title" bundle="${message}"/></h3></div>

<jsp:include page="_popup_message.jsp"/>

<c:set var="user" scope="page" value="${sessionScope[Attributes.LOGINED_USER]}"/>
<div id="window_container">
    <div id="window_frame">
        <form method="POST" action="${url_base}${Paths.LOGIN}">
            <p><strong><fmt:message key="login.form_title" bundle="${message}"/></strong></p>
            <div class="form_labels">
                <table border="0">
                    <tr>
                        <td><fmt:message key="login.user_login" bundle="${message}"/></td>
                        <td><input style="width: 285px" type="text" name="${Parameters.LOGIN}" value="${user.login}"/>
                        </td>
                    </tr>
                    <tr>
                        <td><fmt:message key="login.user_password" bundle="${message}"/></td>
                        <td><input style="width: 285px" type="password" name="${Parameters.PASSWORD}"/></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <div class="button_box">
                                <input type="submit" value="<fmt:message key="menu.login" bundle="${message}"/>"
                                       class="button-link" id="button"/>
                            </div>
                        </td>
                    </tr>
                </table>
                <%--<div style="color:blue; margin-bottom: 25px">User Name: admin, password: 123</div>--%>
            </div>
        </form>
    </div>
</div>
<jsp:include page="_footer.jsp"/>
</body>
</html>