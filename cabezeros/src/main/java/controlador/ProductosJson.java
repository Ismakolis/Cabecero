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
@WebServlet({"/productojson", "/producto.json"})

public class ProductosJson extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Productoservicio servicios = new ProductoServicesImplement();
        List<Productos> productos = servicios.listar();

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        //obtener el servlet path
        String servletPath = req.getServletPath();
        //CREO UNA VARIABLE TIPO BOLLEAN PARA VERIFICA QUE PATH STROY OBTENIENDO
        //agregamos una variable para el arcvhivo JSON
        boolean json = servletPath.endsWith(".json");
        //Se implementa el metodo para formar el json de forma manual


        // Abrir un flujo de escritura para enviar la respuesta al cliente
        try (PrintWriter out = resp.getWriter()) {
            if (json) {
                // Si la solicitud es para JSON
                resp.setContentType("application/json");
                resp.setHeader("Content-Disposition", "attachment; filename=productos.json");

                //
                out.println("["); // Inicio del arreglo JSON
                //en el for sde utiliza una variable para un inicio y un final entpnces el cucle for se usa el i comumnente
                //el .size me devuelve el numero total de eleentos que tiene la lista productos para que funcione como un final
                for (int i = 0; i < productos.size(); i++) {
                    //se usa el metodo get para consultar dastos de manera segura
                    Productos produ = productos.get(i);
                    //se inicia el formarto json con una llave
                    out.println("  {");
                    //se obtiene el valor con la variable produ
                    out.println("    \"idProductos\": " + produ.getIdProductos() + ",");
                    out.println("    \"nombre\": \"" + produ.getNombre() + "\",");
                    out.println("    \"categoria\": \"" + produ.getCategoria() + "\",");
                    out.println("    \"precio\": " + produ.getPrecio());
                    out.println("  }" );
                }
                //se cierra la estructyura del json
                out.println("]");
            }
            // Respuesta en formato HTML
            resp.setContentType("text/html");

            out.println("<!DOCTYPE html>");
            out.println("<html lang=\"en\">");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("<title>Listado de Productos</title>");
            out.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css\" rel=\"stylesheet\">");
            out.println("</head>");
            out.println("<body class=\"bg-light\">");
            out.println("<div class=\"container my-5\">");
            out.println("<a href=\"" + req.getContextPath() + "/producto.json\" class=\"btn btn-primary mb-4\">Descargar documento JSON</a>");
            out.println("<h1 class=\"text-center mb-4\">Listado de Productos</h1>");
            out.println("<table class=\"table table-bordered table-hover\">");
            out.println("<thead class=\"table-dark\">");
            out.println("<tr>");
            out.println("<th>ID PRODUCTO</th>");
            out.println("<th>NOMBRE</th>");
            out.println("<th>CATEGORÍA</th>");
            out.println("<th>PRECIO</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");
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
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}

