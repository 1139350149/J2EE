<%@ page
        language="java"
        pageEncoding="utf-8"
        import="java.util.*,second.UnderGraduate" %>
<!DOCTYPE html>

<html>

<head>
	<title>软院找人--第二次作业</title>
</head>

<body>


<div>

	<%!
		ArrayList<UnderGraduate> underGraduateArrayList;
		int totalPageCount, currentPage, totalRows, numPerPage;
	%>

	<%
		underGraduateArrayList = (ArrayList<UnderGraduate>)session.getAttribute("underGraduateList");
		totalPageCount = (Integer)session.getAttribute("totalPageCount");
		currentPage = (Integer)session.getAttribute("currentPage");
		totalRows = (Integer)session.getAttribute("maxRows");
		numPerPage = (Integer)session.getAttribute("numPerPage");
//		System.out.println(underGraduateArrayList);
//		System.out.println(totalPageCount);
//		System.out.println(currentPage);
//		System.out.println(totalRows);
//		System.out.println(numPerPage);
	%>
	<form action="Second" method="get">
		输入查询内容吧
		<input type="text" name="query"/>
		<input type="submit" value="Search">
	</form>
	<br>
	当前共查询到<%=totalRows%>条信息，每页显示<%=numPerPage%>条信息<p>
	

	<br>

	<table>
	
		<thead>
		<tr>
			<td>ID</td>
			<td>姓名</td>
			<td>电话号码</td>
			<td>QQ</td>
			<td>邮箱</td>
		</tr>
		</thead>
		<tbody>
		<%
			for (int i = (currentPage - 1) * numPerPage;i < currentPage * numPerPage && i < totalRows && i > -1; i++){
				out.println("<tr>");
				out.println("<td>" + underGraduateArrayList.get(i).getID() + "</td>");
				out.println("<td>" + underGraduateArrayList.get(i).getName() + "</td>");
				out.println("<td>" + underGraduateArrayList.get(i).getTel() + "</td>");
				out.println("<td>" + underGraduateArrayList.get(i).getQQ() + "</td>");
				out.println("<td>" + underGraduateArrayList.get(i).getMail() + "</td>");
				out.println("</tr>");
			}
		%>
		</tbody>
	</table>
	<br>
	<form action="Second" method="get">
		更新每页显示数量为
		<input type="text" name="updatePerPage" />
		<input type="submit" value="Update" />
	</form>
	<hr>
	<a href="Second?currentPage=1">首页</a>
	<%
		if (currentPage <= 1){
			out.println(currentPage + " / " + totalPageCount + " 页");
		}
		else {
			out.println("<a href=\"Second?currentPage=" + (currentPage - 1) + "\">上一页</a>");
			out.println(currentPage + " / " + totalPageCount + " 页");
		}
		if (currentPage < totalPageCount){
			out.println("<a href=\"Second?currentPage=" + (currentPage + 1) + "\">下一页</a>");
		}
		out.println("<a href=\"Second?currentPage=" + totalPageCount + "\">尾页</a>");
	%>
</div>
</body>
</html>