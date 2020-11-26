<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>软院找人--第四次作业</title>
    <link href="css/test.css" rel="stylesheet" type="text/css">
</head>
<body class="text-center">
<h1>软院找人</h1>
<form action="TestServlet" method="post">
    <p><input class="search" type="text" name="information" placeholder="请输入电话号码/学号/教工号/QQ/姓名/邮箱的一部分"/>
        <input class="submit" type="submit" value="开始查找"/></p>
</form>
<a class="url" href="pictureSearch.jsp">图片查询</a>
<a class="url" href="login.jsp"> 登录 </a>
</body>
</html>