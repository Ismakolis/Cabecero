package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelos.Productos;
import services.ProductoServicesImplement;
import services.Productoservicio;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

//Anotacion
@WebServlet({"/producto.xls", "/productohtml"})

public class ProductosXls extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Productoservicio servicios = new ProductoServicesImplement();
        List<Productos> productos = servicios.listar();

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        //obtener el servlet path
        String servletPath = req.getServletPath();
        //CREO UNA VARIABLE TIPO BOLLEAN PARA VERIFICA QUE PATH STROY OBTENIENDO
        boolean xls = servletPath.endsWith(".xls");

        String metodoHttp = req.getMethod();
        if (xls) {
            resp.setContentType("application/vnd.ms-excel");
            resp.setHeader("Content-Disposition", "attachment:file=productos.xls");
        }
        try (PrintWriter out = resp.getWriter()) {
            if(!xls ) {

                //Se crea la plantilla html
                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset=\"UTF-8\">");
                out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
                out.println("<title>Listado de productos</title>");
                //
                out.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css\" rel=\"stylesheet\">");
                out.println("</head>");
                out.println("<body class=\"bg-light\">");
                out.println("<div class=\"container my-5\">");
                out.println("<a href=\"" + req.getContextPath() + "/producto.xls\" class=\"btn btn-primary\">Descargar documento Excel</a>");
                out.println("<h1 class=\"text-center mb-4\">Listado de Productos</h1>");
            }

                out.println("<table class=\"table table-bordered table-hover\">");
                out.println("<thead class=\"table-dark\">");
                out.println("<tr>");
                out.println("<th>ID PRODUCTO</th>");
                out.println("<th>NOMBRE</th>");
                out.println("<th>CATEGORIA</th>");
                out.println("<th>PRECIO</th>");
                out.println("</tr>");
                out.println("</thead>");
                out.println("<tbody>");
                //
                productos.forEach(p -> {
                    out.println("<tr>");
                    out.println("<td>" + p.getIdProductos() + "</td>");
                    out.println("<td>" + p.getNombre() + "</td>");
                    out.println("<td>" + p.getCategoria() + "</td>");
                    out.println("<td>" + p.getPrecio() + "</td>");
                    out.println("</tr>");
                });
                out.println("</tbody>");
                out.println("</table>");
            out.println("<div class=\"text-center mt-4\">");
            out.println("</div>");
            out.println("</div>");
           if(xls) {
               out.println("</body>");
               out.println("</html>");
           }

        }
    }
}







