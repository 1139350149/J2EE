<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>PeopleQuery in academy of software</title>
    <link href="css/test.css" rel="stylesheet" type="text/css">
</head>
<body class="text-center">
<h1>软院找人</h1>
<form action="TestServlet" method="post">
    <input class="search" type="text" name="information" placeholder="可输入电话号码/学号/教工号/QQ/姓名/邮箱查询"/>
        <input class="submit" type="submit" value="开始找人"/>
</form>
<c:choose>
    <c:when test="${role=='admin'}">
        <h4>管理员${AdminAcc}你好！</h4>
    </c:when>
    <c:when test="${role=='normal'}">
        <h4>用户${NorAcc}你好！</h4>
    </c:when>
</c:choose>
<a class="url" href="login.jsp"> 登录 </a>
</body>
</html>