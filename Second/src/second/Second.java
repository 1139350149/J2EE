package second;

import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;


public class Second extends HttpServlet {
    private ArrayList<UnderGraduate> underGraduateList;
    private int maxRows;
    private int totalPageCount;

    public Second() {
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


        ExcelReader rf = new ExcelReader(fileName);

        String query;
        if(req.getParameter("query") != null) {
            query = req.getParameter("query");
        } else {
            query = (String)req.getSession().getAttribute("query");
        }


        req.getSession().setAttribute("query", query);

        System.out.println("query = " + query);
        if(query == null || query.equals("")) {
            req.getRequestDispatcher("Hello.jsp").forward(req, resp);
            return;
        }

        int currentPage;        //当前页数量
        if(req.getParameter("currentPage") == null)	{//如果没有设置
            currentPage = 1;
        } else
            currentPage = Integer.parseInt(req.getParameter("currentPage"));
        req.getSession().setAttribute("currentPage", currentPage);

        System.out.println(req.getAttribute("currentPage"));
        System.out.println("every page");

        int numPerPage;
        String update = req.getParameter("updatePerPage");
        String newPage = req.getParameter("numPerPage");
        Object o3 = req.getSession().getAttribute("numPerPage");

        if(update != null) {		// 有更新
            if(update.equals("")) {
                numPerPage = 3;
            } else {
                numPerPage = Integer.parseInt(update);
            }
            currentPage = 1;

        } else {				// 无更新
            if(newPage != null) {		//来自Hello.JSP
                if(newPage.equals(""))
                    numPerPage = 3;
                else
                    numPerPage = Integer.parseInt(newPage);
            } else {				// 下一页
                numPerPage = (Integer)o3;
            }

        }

        if(numPerPage == 0)
            numPerPage = 3;

        req.getSession().setAttribute("numPerPage", numPerPage);

        System.out.println("in SearchServlet currentPage = " + currentPage);
        System.out.println("in SearchServlet numPerPage = " + numPerPage);

        underGraduateList = rf.search(query);

        maxRows = underGraduateList.size();

        totalPageCount = (int)Math.ceil((double)underGraduateList.size() / numPerPage);



        req.getSession().setAttribute("totalPageCount", totalPageCount);

        System.out.println("totalPageCount is " + totalPageCount);

        req.getSession().setAttribute("maxRows", maxRows);

        //查询
        req.getSession().setAttribute("underGraduateList", underGraduateList);

        System.out.println("underGraduateList size = " + maxRows);

        //把request转发给JSP
        req.getRequestDispatcher("SplitPages.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPost ing...");

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        doGet(req, resp);
    }
}
