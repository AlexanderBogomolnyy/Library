<%@ page import="ua.training.library.controller.configuration.*" %>
<%@ page import="ua.training.library.model.entity.states.Role" %>
<%@ page import="ua.training.library.controller.i18n.Languages" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="view" uri="http://ua.training.library/webapp/views/tlds/viewtags" %>

<fmt:setLocale value="${sessionScope[Attributes.LOCALE]}"/>
<fmt:setBundle basename="content" var="message"/>

<c:if test="${sessionScope[Attributes.LOCALE].toLanguageTag() eq Languages.SUPPORTED_LOCALES[1].toLanguageTag()}">
    <fmt:setLocale value="${Languages.SUPPORTED_LOCALES[2]}"/>
</c:if>

<c:set var="url_base" scope="page" value="${pageContext.request.contextPath}${Paths.BASE}"/>
<c:set var="user_scope_url" scope="page" value="${url_base}${Paths.DELIMITER}${user.role.name().toLowerCase()}"/>
<c:set var="response" scope="page" value="${requestScope[Attributes.LIBRARY_RESPONSE]}"/>
<c:set var="response_path" scope="page" value="${requestScope[Attributes.RESPONSE_PATH]}"/>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <link rel="stylesheet" type="text/css" href='<c:url value="/css/main.css"/>'/>
    <link rel="stylesheet" type="text/css" href='<c:url value="/css/frames.css"/>'/>
    <title><fmt:message key="library_response.title" bundle="${message}"/></title>
</head>
<body>

<jsp:include page="_header.jsp"/>
<jsp:include page="_menu.jsp"/>
<jsp:include page="_popup_message.jsp"/>

<div id="window_container">
    <div id="window_frame">
        <form action="${response_path}" method="POST" id="subForm">
            <p><strong><fmt:message key="library_response.form.title" bundle="${message}"/></strong></p>

            <div class="form_labels">
                <label for="order_id"><fmt:message key="library_response.form.order_id"
                                                   bundle="${message}"/></label><br/><input type="text"
                                                                                            name="order_id"
                                                                                            value="${response.order.id}"
                                                                                            id="order_id"
                                                                                            readonly/><br/>
                <label for="client_name"><fmt:message key="library_response.form.client_name"
                                                      bundle="${message}"/></label><br/><input type="text"
                                                                                               name="client_name"
                                                                                               value="${response.order.user.firstName} ${response.order.user.lastName}"
                                                                                               id="client_name"
                                                                                               readonly/><br/>
                <label for="processing_date"><fmt:message key="library_response.form.processing_date"
                                                          bundle="${message}"/></label><br/><input required
                                                                                                   type="datetime-local"
                                                                                                   name="processing_date"
                                                                                                   value="${response.processingDate}"
                                                                                                   id="processing_date"
                                                                                                   readonly/><br/>
                <label for="book_item"><fmt:message key="library_response.form.book_example"
                                                    bundle="${message}"/></label><br/><input type="text"
                                                                                             name="book_item"
                                                                                             value="${response.bookLibraryIdentifier}"
                                                                                             id="book_item"
                                                                                             readonly/><br/>
                <label for="book_location"><fmt:message key="library_response.form.book_location"
                                                        bundle="${message}"/></label><br/><input type="text"
                                                                                                 name="book_location"
                                                                                                 value="${response.location}"
                                                                                                 id="book_location"
                                                                                                 readonly/><br/>
                <label for="date_of_return"><fmt:message key="library_response.form.date_of_return"
                                                         bundle="${message}"/></label><br/><input required
                                                                                                  type="datetime-local"
                                                                                                  name="date_of_return"
                                                                                                  value="${response.dateOfReturn}"
                                                                                                  id="date_of_return"
                                                                                                  readonly/><br/>
            </div>
            <div class="button_box">
                <input type="submit" value="<fmt:message key="library_response.form.complete" bundle="${message}" />"
                       id="button" class="button-link"/>
            </div>
        </form>
    </div>
</div>

<jsp:include page="_footer.jsp"/>

</body>
</html>