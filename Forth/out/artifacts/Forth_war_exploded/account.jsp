<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>软院找人--第四次作业</title>
    <link href="css/test.css" type="text/css" rel="stylesheet">
</head>
<body class="text-center">
<c:choose>
    <c:when test="${role eq 'admin'}">
        <h3>管理员${AdminAcc}你好！</h3>
    </c:when>
</c:choose>
<a class="url" href="Index.jsp">首页</a>
<p style="height: 50px; font-size: 40px;">删除学生功能</p>
<form action="AdminServlet" method="post">
    <p>	<input class="search" type="text" name="id" placeholder="输入要删除的学号"/>
        <input class="submit" type="submit" name="action" value="delete" /></p>
</form>
<h4>添加学生功能</h4>
<form action="AdminServlet" method="post">
    <p>	<input class="search" type="text" name="id" placeholder="输入要添加的学号"/>
        <input class="search" type="text" name="name" placeholder="输入要添加的姓名"/>
        <input class="search" type="text" name="tel" placeholder="输入要添加的电话号"/>
        <input class="search" type="text" name="qq" placeholder="输入要添加的QQ"/>
        <input class="search" type="text" name="mail" placeholder="输入要添加的邮箱"/>
        <input class="submit" type="submit" name="action" value="add"/></p>
</form>
<p style="height: 50px; font-size: 40px;">修改学生信息</p>
<form action="AdminServlet" method="get">
    <p><input class="search" type="text" name="id" placeholder="请输入学生id(不可修改)">
        <input class="search" type="text" name="name" placeholder="请输入学生名字">
        <input class="search" type="text" name="tel" placeholder="请输入学生电话">
        <input class="search" type="text" name="qq" placeholder="请输入学生qq">
        <input class="search" type="text" name="mail" placeholder="请输入学生邮箱">
    <input class="submit" type="submit" name="action" value="change"></p>
</form>
<p style="height: 50px; font-size: 40px;">查询学生信息</p>
<form action="SearchServlet" method="post">
    <p>
    <input class="search" type="text" name="query" />
    <input class="submit" type="submit" name="action" value="searchAdmin">
    </p>
</form>
</body>
</html>
