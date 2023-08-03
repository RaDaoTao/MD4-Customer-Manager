<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hung1
  Date: 8/3/2023
  Time: 2:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>List Customer</h1>
<a href="<%=request.getContextPath()%>/CustomerServlet?action=CREATE">Add</a>
<table border="10" cellpadding="20" cellspacing="10">
    <thead>
    <tr>
        <th>STT</th>
        <th>Name</th>
        <th>Email</th>
        <th>Address</th>
        <th colspan="2">Action</th>
    </tr>
    </thead>
    <tbody>
        <c:forEach items="${customers}" var="c" varStatus="item">
            <tr>
                <td>${item.count}</td>
                <td>${c.name}</td>
                <td>${c.email}</td>
                <td>${c.address}</td>
                <td><a href="<%=request.getContextPath()%>/CustomerServlet?action=EDIT&id=${c.id}">Edit</a></td>
                <td><a onclick="return confirm('do you want to delete this item ? ')" href="<%=request.getContextPath()%>/CustomerServlet?action=DELETE&id=${c.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</body>
</html>
