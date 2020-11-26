<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>软院找人--第四次作业</title>
    <link href="css/test.css" type="text/css" rel="stylesheet">
</head>
<body class="text-center">
<a class="url" href="Index.jsp">首页</a>
<c:choose>
    <c:when test="${role=='admin'}">
        <h1>管理员${AdminAcc}你好！</h1>
        <a class="url" href="account.jsp">返回管理员界面</a>
    </c:when>
    <c:when test="${role=='normal'}">
        <h1>用户${NorAcc}你好！</h1>
        <img style="width:auto; height:220px;" src="<%=request.getContextPath()%>/ShowImgServlet?photo=${NorAcc}">
        <form action="UploadServlet?id=${NorAcc}" method="post" enctype="multipart/form-data">
            <input class="search" type="file" name="uploadfile">
            <input class="submit" id="button" type="submit" value="上传图片">
        </form>
    </c:when>
</c:choose>

<form action="TestServlet" method="post">
    <input class="search" type="text" name="information" placeholder="可输入电话号码/学号/教工号/QQ/姓名/邮箱查询"/>
    <input class="submit" type="submit" value="开始查找" />
</form>
</body>
</html>