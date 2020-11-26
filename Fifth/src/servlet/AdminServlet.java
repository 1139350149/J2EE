package servlet;

import Dao.BaseDao;
import entity.Man;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AdminServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		System.out.println("in admin servlet do get");
		request.setCharacterEncoding ("utf-8");
		BaseDao db = new BaseDao();
		
		System.out.println("after database");
		String action = request.getParameter("action");
		boolean flag = false;
		PrintWriter out=response.getWriter();
		
		System.out.println("before if");
		if(action.equals("add")) {
			flag = add(request, response, db);
			if(flag == false) {
				out.print("<script charset=UTF-8>alert('Fail to add!');window.location.href='account.jsp'</script>");
				return;
			} else {
				out.print("<script charset=UTF-8>alert('Successfully added!');window.location.href='account.jsp'</script>");
				return;
			}
		}
		
		else if(action.equals("delete")) {
			System.out.println("admin servlet del");
			flag = delete(request, response, db);
			if(flag == false) {
				out.print("<script charset=UTF-8>alert('Fail to delete!');window.location.href='account.jsp'</script>");
				return;
			} else {
				out.print("<script charset=UTF-8>alert('Successfully deleted!');window.location.href='account.jsp'</script>");
				return;
			}
		}
		
		else if(action.equals("change")) {
			System.out.println("admin servlet change");
			flag = change(request, response, db);
			if(flag == false) {
				out.print("<script charset=UTF-8>alert('Fail to change!');window.location.href='account.jsp'</script>");
				return;
			} else {
				out.print("<script charset=UTF-8>alert('Successfully changed!');window.location.href='account.jsp'</script>");
				return;
			}
		}
	}
	
	private boolean add(HttpServletRequest request, HttpServletResponse response, BaseDao db) {
		
		String id = request.getParameter("id");
		if(id == null || id == "")
			return false;
		
		List<Integer> ans = db.getMaxPages(id, 1);
		int maxRows = ans.get(1);
		if(maxRows != 0)	
			return false;
		
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		String qq = request.getParameter("qq");
		String mail = request.getParameter("mail");
		
		Man man = new Man(id, name, tel, qq, mail);
		
		return db.add(man);
	}

	private boolean delete(HttpServletRequest request, HttpServletResponse response, BaseDao db) {
		
		String id = request.getParameter("id");
		if(id == null || id == "")
			return false;
		
		List<Integer> ans = db.getMaxPages(id, 1);
		int maxRows = ans.get(1);
		if(maxRows == 0)	
			return false;
		
		return db.delete(id);
		
	}
	
	private boolean change(HttpServletRequest request, HttpServletResponse response, BaseDao db) {
		
		boolean flag = delete(request, response, db);
		if(flag == false) 
			return false;
		
		flag = add(request, response, db);
		if(flag == false)
			return false;
		
		return true;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doGet(request, response);
	}

}
