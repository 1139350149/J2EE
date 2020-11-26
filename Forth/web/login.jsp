<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>软院找人--第四次作业</title>
    <link href="css/test.css" type="text/css" rel="stylesheet">
</head>
<body class="text-center">
<a class="url" href="Index.jsp">首页</a>
<form action="LoginServlet" method="post">
    <p>
        <input class="search" type="text" name="account" placeholder="请输入帐号"/>
        <input class="search" type="password" name="password" placeholder="请输入密码"/>
        <input class="submit" type="submit" name="role" value="normal">
        <input class="submit" type="submit" name="role" value="admin">
    </p>
</form>
</body>
</html>
