package Third;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class AdminService extends HttpServlet {
    public AdminService(){
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("do Get ...");
        req.setCharacterEncoding ("utf-8");

        Database db = new Database();

        String action = req.getParameter("action");
        boolean flag = false;
        PrintWriter out = resp.getWriter();

        switch (action){
            case "add": {
                System.out.println("Admin add");
                flag = add(req, resp, db);
                if(!flag) {
                    out.print("<script charset=UTF-8>alert('Fail to add!');window.location.href='admin.jsp'</script>");
                    return;
                } else {
                    out.print("<script charset=UTF-8>alert('Successfully added!');window.location.href='admin.jsp'</script>");
                    return;
                }
            }
            case "delete": {
                System.out.println("Admin delete");
                flag = delete(req, resp, db);
                if(!flag) {
                    out.print("<script charset=UTF-8>alert('Fail to delete!');window.location.href='admin.jsp'</script>");
                    return;
                } else {
                    out.print("<script charset=UTF-8>alert('Successfully deleted!');window.location.href='admin.jsp'</script>");
                    return;
                }
            }
            case "change": {
                System.out.println("Admin change");
                flag = change(req, resp, db);
                if(!flag) {
                    out.print("<script charset=UTF-8>alert('Fail to change!');window.location.href='admin.jsp'</script>");
                    return;
                } else {
                    out.print("<script charset=UTF-8>alert('Successfully changed!');window.location.href='admin.jsp'</script>");
                    return;
                }
            }
        }
    }

    private boolean add(HttpServletRequest request, HttpServletResponse response, Database db) {

        String id = request.getParameter("id");
        if(id == null || "".equals(id))
            return false;

        List<Integer> ans = db.getMaxPages(id, 1);	//先判断这个人是否已经存在
        int maxRows = ans.get(1);
        if(maxRows != 0)
            return false;

        String name = request.getParameter("name");
        String tel = request.getParameter("tel");
        String qq = request.getParameter("qq");
        String mail = request.getParameter("mail");

        UnderGraduate underGraduate = new UnderGraduate(id, name, tel, qq, mail);

        return db.add(underGraduate);
    }

    private boolean delete(HttpServletRequest request, HttpServletResponse response, Database db) {

        String id = request.getParameter("id");
        if(id == null || "".equals(id))
            return false;

        List<Integer> ans = db.getMaxPages(id, 1);	//先判断这个人是否已经存在
        int maxRows = ans.get(1);
        if(maxRows == 0)
            return false;

        return db.delete(id);

    }

    private boolean change(HttpServletRequest request, HttpServletResponse response, Database db) {

        boolean flag = delete(request, response, db);
        if(!flag)
            return false;

        flag = add(request, response, db);

        return flag;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("do Post ...");
        doGet(req, resp);
    }

}
