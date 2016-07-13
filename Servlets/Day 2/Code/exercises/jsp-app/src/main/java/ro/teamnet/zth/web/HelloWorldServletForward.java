package ro.teamnet.zth.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by user on 7/13/2016.
 */
public class HelloWorldServletForward extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");

        String user = "";
        user = req.getParameter("user");

        resp.getWriter().write("Hello <b>"+ user +
                " </b> from the Forward Servlet " +
                req.getAttribute("testAttribute"));
    }
}
