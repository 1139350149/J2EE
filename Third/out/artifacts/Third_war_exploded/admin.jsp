<%--
  Created by IntelliJ IDEA.
  User: 11393
  Date: 2020/10/19
  Time: 22:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>软院找人--第三次作业</title>
</head>
<body>
    <div>
        <form action="Admin" method="get">
            增加一条信息：<br/>
            <table>
                <tr>
                    <td>&nbsp;&nbsp; 用户id</td>
                    <td>&nbsp;<input type="text" name="id"></td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp; 用户姓名</td>
                    <td>&nbsp;<input type="text" name="name"></td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp; 电话号码</td>
                    <td>&nbsp;<input type="text" name="tel"></td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp; QQ号码</td>
                    <td>&nbsp;<input type="text" name="qq"></td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp; 用户邮箱</td>
                    <td>&nbsp;<input type="text" name="mail"></td>
                </tr>
            </table>

            <input type="submit" name="action" value="add">
        </form>

        <br/>
        <form action="Admin" method="get">
            删除一条信息：<br/>
            <table>
                <tr>
                    <td>&nbsp;&nbsp; 用户id</td>
                    <td>&nbsp;<input type="text" name="id"></td>
                </tr>
            </table>

            <input type="submit" name="action" value="delete">
        </form>

        <br/>
        <form action="Admin" method="get">
            修改一条信息：<br/>
            <table>
                <tr>
                    <td>&nbsp;&nbsp; 用户id</td>
                    <td>&nbsp;<input type="text" name="id"></td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp; 用户姓名</td>
                    <td>&nbsp;<input type="text" name="name"></td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp; 电话号码</td>
                    <td>&nbsp;<input type="text" name="tel"></td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp; QQ号码</td>
                    <td>&nbsp;<input type="text" name="qq"></td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp; 用户邮箱</td>
                    <td>&nbsp;<input type="text" name="mail"></td>
                </tr>
            </table>

            <input type="submit" name="action" value="change">
        </form>

        <br/>
        <form action="SearchServlet" method="get">

            输入查询内容吧：<br/>
            <table>
                <tr>
                    <td>&nbsp;&nbsp; 查询内容</td>
                    <td>&nbsp;<input type="text" name="query"/></td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp; 每页显示条数</td>
                    <td>&nbsp;<input type="text" name="numPerPage"/></td>
                </tr>
            </table>

            <input type="submit" name="action" value="searchAdmin">
        </form>
    </div>
</body>
</html>
