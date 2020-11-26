<%--
  Created by IntelliJ IDEA.
  User: 26960
  Date: 2020/10/18
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Account manager</title>
    <link href="css/test.css" rel="stylesheet" type="text/css">
</head>
<body class="text-center">
<c:choose>
    <c:when test="${role=='admin'}">
        <h3>管理员${AdminAcc}你好！</h3>
    </c:when>
</c:choose>
<a class="url" href="index.jsp">首页</a>
<p style="height: 50px; font-size: 40px;">删除学生功能</p>
<form action="AdminServlet" method="post">
    <input class="search" type="text" style="width:500px; height:50px;" name="id" placeholder="输入要删除的学号"/>
    <input class="submit" type="submit" name="action" value="delete"/>
</form>
<p style="height: 50px; font-size: 40px;">添加学生功能</p>
<form action="AdminServlet" method="post">
    <input class="search" type="text" name="id" placeholder="输入要添加的学号"/>
    <input class="search" type="text" name="name" placeholder="输入要添加的姓名"/>
    <input class="search" type="text" name="tel" placeholder="输入要添加的电话号"/>
    <input class="search" type="text" name="qq" placeholder="输入要添加的QQ"/>
    <input class="search" type="text" name="mail" placeholder="输入要添加的邮箱"/>
    <input class="submit" type="submit" name="action" value="add"/>
</form>
<p style="height: 50px; font-size: 40px;">修改学生信息</p>
<form action="AdminServlet" method="get">
    <input class="search" type="text" name="id" placeholder="输入学号(不可修改，只作为查找依据)">
    <input class="search" type="text" name="name" placeholder="输入名字">
    <input class="search" type="text" name="tel" placeholder="输入电话号码">
    <input class="search" type="text" name="qq" placeholder="输入学号qq">
    <input class="search" type="text" name="mail" placeholder="输入mail">
    <input class="submit" type="submit" name="action" value="change">
</form>
<p style="height: 50px; font-size: 40px;">查询学生信息</p>
<form action="SearchServlet" method="post">
        <input class="search" type="text" name="query"/>
        <input class="submit" type="submit" name="action" value="searchAdmin">
</form>

<br/>
</body>
</html>
