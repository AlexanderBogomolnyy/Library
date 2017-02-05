<%@ page import="ua.training.library.controller.configuration.*" %>
<%@ page import="ua.training.library.model.entity.states.Role" %>
<%@ page import="ua.training.library.controller.i18n.Languages" %>
<%@ page import="java.time.LocalDate" %>
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
<c:set var="order" scope="page" value="${requestScope[Attributes.RESPONSE_ORDER]}"/>
<c:set var="bookList" scope="page" value="${requestScope[Attributes.EXAMPLES_OF_A_BOOK]}"/>

<%--<c:set var="response_path" scope="page" value="${requestScope[Attributes.RESPONSE_PATH]}"/>--%>
<%--<c:set var="order_completing_path" scope="page" value="${requestScope[Attributes.ORDER_COMPLETING_PATH]}"/>--%>


<c:set var="isEmptyBookList" value="${bookList.isEmpty()}"/>
<c:choose>
<c:when test="${!isEmptyBookList}">
    <c:set var="action_path" scope="page" value="${requestScope[Attributes.RESPONSE_PATH]}"/>
</c:when>
    <c:otherwise>
        <c:set var="action_path" scope="page" value="${requestScope[Attributes.ORDER_COMPLETING_PATH]}"/>
    </c:otherwise>
</c:choose>


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
            <form action="${action_path}" method="POST" id="subForm">
                <p><strong><fmt:message key="library_response.form.title" bundle="${message}"/></strong></p>
                <div class="form_labels">
                    <label for="order_id"><fmt:message key="library_response.form.order_id"
                                                       bundle="${message}"/></label><br/><input type="text"
                                                                                                name="order_id"
                                                                                                value="${order.id}"
                                                                                                id="order_id" readonly/><br/>
                    <label for="client_name"><fmt:message key="library_response.form.client_name"
                                                          bundle="${message}"/></label><br/><input type="text"
                                                                                                   name="client_name"
                                                                                                   value="${order.user.firstName} ${order.user.lastName}"
                                                                                                   id="client_name"
                                                                                                   readonly/><br/>
                    <label for="book_description"><fmt:message key="library_response.form.book_description"
                                                               bundle="${message}"/></label><br/><input type="text"
                                                                                                        name="book_description"
                                                                                                        value="${order.catalog.author} : ${order.catalog.title}"
                                                                                                        id="book_description"
                                                                                                        readonly/><br/>
                    <label for="expected_book_location"><fmt:message key="library_response.form.expected_book_location"
                                                                     bundle="${message}"/></label><br/><input
                        type="text" name="expected_book_location" value="${order.expectedBookLocation}"
                        id="expected_book_location" readonly/><br/>
                    <label for="processing_date"><fmt:message key="library_response.form.processing_date"
                                                              bundle="${message}"/></label><br/><input required
                                                                                                       type="datetime-local"
                                                                                                       name="processing_date"
                                                                                                       value="${LocalDate.now()}"
                                                                                                       id="processing_date"
                                                                                                       readonly/><br/>
                    <c:choose>
                    <c:when test="${!isEmptyBookList}">
                    <label for="book_example"><fmt:message key="library_response.form.book_example"
                                                        bundle="${message}"/></label><br/><select size="1"
                                                                                                  name="book_example"
                                                                                                  id="book_example"
                                                                                                  required="required">
                    <c:forEach items="${bookList}" var="book">
                        <option selected value="${book.id}">[${book.libraryIdentifier}] ${book.catalog.author}
                            : ${book.catalog.title}</option>
                    </c:forEach>
                </select><br/>
                    <label for="book_location"><fmt:message key="library_response.form.book_location"
                                                            bundle="${message}"/></label><br/><select size="1"
                                                                                                      name="book_location"
                                                                                                      id="book_location"
                                                                                                      required="required">
                    <option selected value="ON_HAND">On hand</option>
                    <option value="READING_ROOM">Reading room</option>
                </select><br/>
                    <input type="hidden" name="catalog_id" id="catalog_id" value="${order.catalog.id}" readonly>

                </div>
                <div class="button_box">
                    <input type="submit" value="<fmt:message key="library_response.form.button" bundle="${message}" />"
                           id="button_perform" class="button-link"/>
                </div>
                </c:when>
                <c:otherwise>
                    </div>
                    <div class="button_box">
                        <input type="submit" value="<fmt:message key="library_response.form.complete" bundle="${message}" />"
                               id="button_complete" class="button-link"/>
                    </div>
                </c:otherwise>
                </c:choose>
            </form>
    </div>
</div>
<jsp:include page="_footer.jsp"/>

</body>
</html>