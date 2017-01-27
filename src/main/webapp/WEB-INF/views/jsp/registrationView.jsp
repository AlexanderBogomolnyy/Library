<%@ page import="ua.training.library.controller.configuration.*" %>
<%@ page import="ua.training.library.model.entity.states.Role" %>
<%@ page import="ua.training.library.controller.i18n.Languages"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="view" uri="http://ua.training.library/webapp/views/tlds/viewtags" %>

<fmt:setLocale value="${sessionScope[Attributes.LOCALE]}"/>
<fmt:setBundle basename="content" var="message"/>

<c:set var="url_base" scope="page" value="${pageContext.request.contextPath}"/>
<c:set var="user" scope="page" value="${requestScope[Attributes.USER_ON_REGISTRATION]}"/>

<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
  <link rel="stylesheet" type="text/css" href='<c:url value="/css/main.css"/>' />
  <link rel="stylesheet" type="text/css" href='<c:url value="/css/frames.css"/>' />
  <script type="text/javascript">
    window.onload = function () {
      document.getElementById("password").onchange = validatePassword;
      document.getElementById("conf_password").onchange = validatePassword;
    };
    function validatePassword(){
      var conf_pass=document.getElementById("conf_password").value;
      var pass=document.getElementById("password").value;
      if(pass!=conf_pass)
        document.getElementById("conf_password").setCustomValidity("Passwords Don't Match");
      else {
        document.getElementById("conf_password").setCustomValidity('');
      }
    }
  </script>
  <title><fmt:message key="registration.title" bundle="${message}" /></title>
</head>
<body>

<jsp:include page="_header.jsp"/>
<jsp:include page="_menu.jsp"/>
<jsp:include page="_popup_message.jsp"/>

<div id="window_container">
  <div id="window_frame">
    <form action="${url}${Paths.REGISTRATION}" method="POST" id="subForm">
      <p><strong><fmt:message key="registration.form_title" bundle="${message}" /></strong></p>
      <div class="form_labels">
        <label for="firstName"><fmt:message key="registration.first_name" bundle="${message}" /></label><br/><input type="text" name="firstName" value="${user.firstName}" id="firstName"/><br/>
        <label for="lastName"><fmt:message key="registration.last_name" bundle="${message}" /></label><br/><input type="text" name="lastName" value="${user.lastName}" id="lastName"/><br/>
        <label for="email"><fmt:message key="registration.email" bundle="${message}" /></label><br/><input required type="email" name="email" value="${user.email}" id="email"/><br/>
        <label for="login"><fmt:message key="registration.login" bundle="${message}" /></label><br/><input required type="text" name="login" value="${user.login}" id="login"/><br/>
        <label for="password"><fmt:message key="registration.password" bundle="${message}" /></label><br/><input required type="password" name="password" value="" id="password"/><br/>
        <label for="conf_password"><fmt:message key="registration.confirm_password" bundle="${message}" /></label><br/><input required type="password" value="" id="conf_password"/><br/>
        <label for="role"><fmt:message key="registration.role" bundle="${message}" /></label><br/><select size="1" name="role" id="role" >
          <option selected>CLIENT</option>
          <option>LIBRARIAN</option>
        </select><br/>
      </div>
      <div class="button_box">
        <input type="submit" value="<fmt:message key="registration.register" bundle="${message}" />" id="button" class="button-link"/>
      </div>
    </form>
  </div>
</div>
<jsp:include page="_footer.jsp"/>

</body>
</html>