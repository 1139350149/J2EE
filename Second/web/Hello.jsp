<%--
  Created by IntelliJ IDEA.
  User: 11393
  Date: 2020/10/15
  Time: 18:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>软院找人--第二次作业</title>
</head>

<body>

<div class="container">
    <form action="Second" method="get">
        输入查询内容吧</br>
        <input type="text" name="query"/>
        每页显示 <input type="text" name="numPerPage"/> 条（默认3条）
        <input type="submit" value="Search">
        </br>
    </form>
</div>

</body>

</html>
