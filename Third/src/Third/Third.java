package Third;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Third extends HttpServlet {
    private static String username = "admin";
    private static String userpwd = "1";


    public Third(){
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("do Get ...");
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        String role = req.getParameter("role");

        if ("normal".equals(role)){
            System.out.println("This is normal");
            req.getRequestDispatcher("Hello.jsp").forward(req, resp);
            return ;
        }
        else{
            String name = req.getParameter("name");
            String password = req.getParameter("password");

            PrintWriter out=resp.getWriter();

            if(name == null || password == null) {
                out.print("<script charset=UTF-8>alert('Fail to log in!');window.location.href='index.html'</script>");
                return;
            }

            if(name.equals(username)==false || password.equals(userpwd)==false) {
                out.print("<script charset=UTF-8>alert('Fail to log in!');window.location.href='index.html'</script>");
                return;
            }

            req.getRequestDispatcher("admin.jsp").forward(req, resp);
            return;
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("do Post ...");
        doGet(req, resp);
    }
}
