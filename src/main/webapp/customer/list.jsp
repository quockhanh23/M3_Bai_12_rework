<%--
  Created by IntelliJ IDEA.
  User: Asus VivoBook
  Date: 12/1/2021
  Time: 9:11 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>list</h2>
<form action="/customers">
    <input type="text" name="key">
    <button>Find</button>
</form>
<a href="/customers?action=create">create new</a>
<%--<a href="/customers?action=findByName">Find Name</a>--%>
<a href="/customers?action=showListByOrder">Order</a>
<c:forEach var="cus" items="${alo}">
    <h2>${cus.id}, ${cus.name}, ${cus.age}
        <a href="/customers?action=edit&id=${cus.id}">Edit</a>
        <a href="/customers?action=delete&id=${cus.id}">Delete</a>
    </h2>

</c:forEach>
</body>
</html>
