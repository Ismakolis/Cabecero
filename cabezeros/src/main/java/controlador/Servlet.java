package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

//implementamos un path o una llave de acceso
@WebServlet("/servlet")
public class Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
// el cabecero que devuelve el servidor
        resp.setContentType("text/html; charset=UTF-8");

        String metodoHttp = req.getMethod();
        String requestURI = req.getRequestURI();
        String  requestURL = req.getRequestURL().toString();
        String contextPath = req.getContextPath();
        String servletPath = req.getServletPath();
        String ip = req.getLocalAddr();
        String port = req.getServletPath();
        String scheme = req.getScheme();
        String host = req.getHeader("host");
        String url1="://"+host+":"+port+contextPath+servletPath;

                PrintWriter out = resp.getWriter();

                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset=\"UTF-8\">");
                out.println("<title>Cabeceros</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Servlet</h1>");
                out.println("<ul>");
                out.println("<li>Metodo utilizado para la peticion:   " + metodoHttp);
                out.println("<li> Uri utilizada: " + requestURI);
                out.println("<li> URL utilizada para la peticion: " +requestURL);
                out.println("<li> Servlet utilizada: " + servletPath);
                out.println("<li> IP utilizada: " + ip);
                out.println("<li> Port utilizada: " + port);
                out.println("<li > Squema utilizada: "+scheme);
                out.println("<li> Host utilizado: "+host);
                out.println("<li> URL utilizada para la peticion:  "+url1);
                out.println("</ul>");
                out.println("</body>");
                out.println("</html>");

    }
}
