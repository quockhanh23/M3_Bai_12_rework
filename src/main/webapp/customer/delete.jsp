<%--
  Created by IntelliJ IDEA.
  User: Asus VivoBook
  Date: 12/1/2021
  Time: 3:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post">
    <h2>Delete</h2>
    <table>
        <tr>
            <td>Id</td>
            <td>${aloDelete.id}</td>
        </tr>
        <tr>
            <td>Name</td>
            <td>${aloDelete.name}</td>
        </tr>
        <tr>
            <td>Age</td>
            <td>${aloDelete.age}</td>
        </tr>
        <tr>
            <td></td>
            <td><button>Delete</button></td>
        </tr>
    </table>
</form>
</body>
</html>
