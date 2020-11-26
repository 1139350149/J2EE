package first;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class First extends HttpServlet {
    public First() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("doGet ...");

        String fileName = req.getSession().getServletContext()
                .getRealPath("/")
                + "WEB-INF/contact/homework.xls";
        fileName = fileName.replace("\\", "/");

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        out.println("<html><head><title>第一次上机--软院找人</title></head>");
        out.println("<body bgcolor=\"white\"><font size=4>");

        ExcelReader rf = new ExcelReader(fileName);

        String query = req.getParameter("query");

        out.println("<form method=get>软院找人系统<p> ");

        out.println("<input type=\"text\" name=\"query\"/>");
        out.println("<input type=\"submit\" value=\"Search\"></form>");

        if(query!= null) {
            out.println("搜索" + query + "<p>");
            query = query.trim();	// 去除空格

            ArrayList<UnderGraduate> underGraduateList = rf.search(query);
            Iterator<UnderGraduate> it = underGraduateList.iterator();
            while(it.hasNext()) {
                UnderGraduate man = it.next();
                out.println("the content is " + man.printUnderGraduateInfo() + "<p>");
            }

        }

        out.println("</font></body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPost ing...");

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        doGet(req, resp);
    }
}
