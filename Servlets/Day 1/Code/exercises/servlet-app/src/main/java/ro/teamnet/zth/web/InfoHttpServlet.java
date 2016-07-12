package ro.teamnet.zth.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;


/**
 * Created by user on 7/12/2016.
 */
public class InfoHttpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Enumeration headerNames = req.getHeaderNames();
        String table = "<table style=\"width:100%\">\n";



        while (headerNames.hasMoreElements()){
            String hName = headerNames.nextElement().toString();
            table += " <tr > <td> ";
            table += hName + "</td>";
            table += "<td> " + req.getHeader(hName) + "</td>";
            table += "  <td> </tr>";
        }

        table += "  </table>\n" ;



        resp.setContentType("text/html");
        resp.getWriter().write(table);

    }
}
