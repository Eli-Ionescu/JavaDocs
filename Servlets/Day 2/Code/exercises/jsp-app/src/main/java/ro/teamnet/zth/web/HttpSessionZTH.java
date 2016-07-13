package ro.teamnet.zth.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by user on 7/13/2016.
 */
public class HttpSessionZTH extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = "";
        user = req.getParameter("username");
        String password = "";
        password = req.getParameter("password");

        if(user.equals("admin") && password.equals("admin")){
            resp.getWriter().write("Welcome back! \nUsername: "+ user);
        }
        else{
            RequestDispatcher requestDispatcher =
                    req.getRequestDispatcher("views/loginFail.jsp");

            req.getSession().setAttribute("user", "<3 George");
            req.getSession().setAttribute("session",req.getSession());
            requestDispatcher.forward(req, resp);
        }
    }
}
