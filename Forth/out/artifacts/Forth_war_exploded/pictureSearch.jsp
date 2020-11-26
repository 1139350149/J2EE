<%--
  Created by IntelliJ IDEA.
  User: 11393
  Date: 2020/10/23
  Time: 19:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>软院找人--第四次作业</title>
    <link href="css/test.css" rel="stylesheet" type="text/css">
    <script src="JavaScript/jquery-3.5.1.js" type="text/javascript" rel="script"></script>
</head>
<body class="text-center">
<form action="UploadServlet" method="post" enctype="multipart/form-data">
    <input class="search" type="file" name="uploadfile">
    <input class="submit" id="button" type="submit" value="图片查找">
</form>
</body>
</html>
