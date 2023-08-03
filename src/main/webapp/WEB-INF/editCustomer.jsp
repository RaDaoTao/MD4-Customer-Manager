<%--
  Created by IntelliJ IDEA.
  User: hung1
  Date: 8/3/2023
  Time: 2:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Edit Customer</h1>
<form action="<%=request.getContextPath()%>/CustomerServlet" method="post">
    <label for="id">ID</label>
    <input type="text" id="id" disabled value="${customer.id}">
    <input type="hidden" name="id" value="${customer.id}">
    <label for="name">Name</label>
    <input type="text" id="name" name="name" value="${customer.name}">
    <label for="email">Email</label>
    <input type="text"  id="email" name="email" value="${customer.email}">
    <label for="address">Address</label>
    <input type="text"  id="address" name="address" value="${customer.address}">
    <input type="submit" value="UPDATE" name="action"/>
</form>
</body>
</html>
