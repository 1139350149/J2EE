<%--
  Created by IntelliJ IDEA.
  User: 26960
  Date: 2020/10/18
  Time: 9:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>软院找人--第五次作业</title>
    <link href="css/test.css" rel="stylesheet" type="text/css">
</head>
<body class="text-center">
<a class="url" href="index.jsp">首页</a>
<form action="LoginServlet" method="post">
        <input class="search" type="text"  name="account" placeholder="请输入帐号"/>
        <input class="search" type="password" name="password" placeholder="请输入密码"/>
        <input class="submit" type="submit" name="role" value="normal" >
        <input class="submit" type="submit" name="role" value="admin" >
</form>
</body>
</html>
