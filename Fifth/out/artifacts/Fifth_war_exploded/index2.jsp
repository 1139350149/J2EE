<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
    <c:when test="${content.size()!=0 }">
        <table align="center" border="0" width="85%">
            <tr bgcolor="#6699FF" width="30">
                <td>学号</td>
                <td>姓名</td>
                <td>电话号</td>
                <td>QQ</td>
                <td>邮箱</td>
                <td>照片</td>
            </tr>
            <c:forEach var="g" items="${content}" begin="${0}" end="${dataNum-1}">
                <tr bgcolor="#DDDDDD" width="410">
                    <td>${g.getId()}</td>
                    <td>${g.getName() }</td>
                    <td>${g.getTel() }</td>
                    <td>${g.getQq()}</td>
                    <td>${g.getMail()}</td>
                    <td><img style="width:auto; height:110px;"
                             src="<%=request.getContextPath()%>/ShowImgServlet?photo=${g.getId()}"></td>
                </tr>
            </c:forEach>
            <tr bgcolor="#6699FF" width="30">
                <td colspan="6">
                    共${sessionScope.totalNum}条记录，共${sessionScope.totalPage}页，当前第${sessionScope.pageNum}页
                    每页${sessionScope.dataNum}条记录
                    <a href="/Fifth/PageServlet?pageNum=${1}">[首页]</a>
                    <c:if test="${pageNum>1}">
                        <a href="/Fifth/PageServlet?pageNum=${pageNum-1 }">[上一页]</a>
                    </c:if>
                    <c:if test="${pageNum==1}">
                        [上一页]
                    </c:if>

                    <c:if test="${pageNum<totalPage}">
                        <a href="/Fifth/PageServlet?pageNum=${pageNum+1 }">[下一页]</a>
                    </c:if>
                    <c:if test="${pageNum==totalPage}">
                        [下一页]
                    </c:if>
                    <a href="/Fifth/PageServlet?pageNum=${totalPage }">[尾页]</a>
                </td>
            </tr>
        </table>
        <form action="/Fifth/jumpServlet" method="post">
            <input class="search" type="text" name="info">
            <input class="submit" type="submit" value="进行查找">
        </form>
        <form action="/Fifth/modifyPage" method="post">
            <input class="search" type="text" name="info1">
            <input class="submit" type="submit" value="设置页条目">
        </form>
    </c:when>

    <c:otherwise>
        未查询到相关信息！
    </c:otherwise>
</c:choose>










