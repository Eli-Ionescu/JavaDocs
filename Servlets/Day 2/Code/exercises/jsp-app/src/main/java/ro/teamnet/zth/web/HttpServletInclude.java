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
public class HttpServletInclude extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");

        RequestDispatcher requestDispatcher =
                req.getRequestDispatcher("/included");

        req.setAttribute("someAttribute", "someAttributeValue");
        requestDispatcher.include(req, resp);

        resp.getWriter().write("Hello <b> George "+
                " </b> from the Forward Servlet " +
                req.getAttribute("someAttribute"));



    }
}
