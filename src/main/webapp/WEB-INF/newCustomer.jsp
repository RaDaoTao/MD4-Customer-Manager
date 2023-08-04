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
<h1>New Customer</h1>
<form action="<%=request.getContextPath()%>/CustomerServlet" method="post">
    <label for="name">Name</label>
    <input type="text" id="name" name="name" >
    <label for="email">Email</label>
    <input type="text" id="email" name="email" >
    <label for="address">Address</label>
    <input type="text" id="address" name="address" >
    <br>
    <label for="phone">Phone</label>
    <input type="text" id="phone" name="phone" >
    <label >Gender</label>
    <input type="radio" name="sex" value="true"><span>Male</span>
    <input type="radio" name="sex" value="false"><span>Female</span>
    <label for="age">Age</label>
    <input type="number" name="age" id="age">
    <input type="submit" value="ADD" name="action"/>
</form>
</body>
</html>
