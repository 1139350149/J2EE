package Third;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;
import java.util.*;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static List<UnderGraduate> underGraduateList = null;
	
	private int maxRows;
	private int totalPageCount;
	
       
    public SearchServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("do Get ...");
		
		String query;
		if(request.getParameter("query") != null) {
			query = request.getParameter("query");
		} else {
			query = (String)request.getSession().getAttribute("query");
		}
		
		request.getSession().setAttribute("query", query);
		
		String action = request.getParameter("action");
		if(action == null)
			action = "";
		if("searchAdmin".equals(action)) {
			request.getSession().setAttribute("from", "admin");
		} else {
			request.getSession().setAttribute("from", "normal");
		}

		System.out.println("query = " + query);
		if(query == null || "".equals(query)) {
			System.out.println("action = " + action);
			if(action.equals("searchAdmin")) {
				request.getRequestDispatcher("admin.jsp").forward(request, response);
				return;
			}
			else {
				request.getRequestDispatcher("Hello.jsp").forward(request, response);
				return;
			}
		}
		
		
		int currentPage;
		if(request.getParameter("currentPage") == null)	{
			currentPage = 1;
		} else
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
        request.getSession().setAttribute("currentPage", currentPage);
		

		int numPerPage;
		String s1 = request.getParameter("updatePerPage");
		String s2 = request.getParameter("numPerPage");
		Object o3 = request.getSession().getAttribute("numPerPage");
		
		if(s1 != null) {
			if("".equals(s1)) {
				numPerPage = 3;
			} else {
				numPerPage = Integer.parseInt(s1);
			}
			currentPage = 1;
			
		} else {
			if(s2 != null) {
				if("".equals(s2))
					numPerPage = 3;
				else
					numPerPage = Integer.parseInt(s2);
			} else {
				numPerPage = (Integer)o3;
			}
			
		}
		
		if(numPerPage == 0)
			numPerPage = 3;
		
		request.getSession().setAttribute("numPerPage", numPerPage);

		Database db = new Database();
		
		List<Integer> ans = db.getMaxPages(query, numPerPage);
		totalPageCount = ans.get(0);
		maxRows = ans.get(1);
        underGraduateList = db.searchOf(query, currentPage, numPerPage);
        request.getSession().setAttribute("maxRows", maxRows);
		
        request.getSession().setAttribute("totalPageCount", totalPageCount);

        request.getSession().setAttribute("underGraduateList", underGraduateList);

		request.getRequestDispatcher("SplitPages.jsp").forward(request, response);
		
		db.release();
	}

	/*c
	 * post
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		doGet(request, response);
	}

	
	
	
}
