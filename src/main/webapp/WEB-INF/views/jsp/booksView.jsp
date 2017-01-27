<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.io.*,java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
  <link rel="stylesheet" type="text/css" href='<c:url value="/css/main.css"/>' />
  <title>Book List</title>
</head>
<body>

<jsp:include page="_header.jsp"/>
<jsp:include page="_menu.jsp"/>

<div class="headline"><h3>Book List</h3></div>

<jsp:include page="_popup_message.jsp"/>

  <table cellpadding="0" class="print_table">
    <thead>
    <tr>
      <th>ID</th>
      <th>Title</th>
      <th>Author</th>
      <th>Description</th>
      <th>Publisher</th>
      <th>Publication<br/>year</th>
    </tr>
    </thead>
    <tbody>
    <%! Integer counter = 0; %>
    <c:forEach items="${books}" var="book">
      <% if (counter%2 == 0) {%><tr class="even"><%} else {%><tr><%}%>
      <td>${book.id}</td>
      <td>${book.title}</td>
      <td>${book.author}</td>
      <td>${book.description}</td>
      <td>${book.publisher}</td>
      <td>${book.publicationYear}</td>
    </tr>
      <% counter++; %>
    </c:forEach>
    </tbody>
  </table>
<jsp:include page="_footer.jsp"/>
</body>
</html>