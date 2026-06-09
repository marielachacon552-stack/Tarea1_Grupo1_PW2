import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "ServletBinarios", value = "/servlet-binarios")
public class BinarioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String operacion = safeTrim(request.getParameter("operacion"));
        String numeroInput = safeTrim(request.getParameter("numero"));

        String resultado = "";
        String nombreOperacion = "";


        if (operacion.isEmpty() || numeroInput.isEmpty()) {
            resultado = "Error: El campo del número no puede estar vacío.";
            nombreOperacion = "Validación interna";
        } else {
            if (operacion.equals("binToDec")) {
                nombreOperacion = "Cálculo de número binario a decimal";
                try {

                    int decimal = Integer.parseInt(numeroInput, 2);
                    resultado = String.valueOf(decimal);
                } catch (NumberFormatException e) {
                    resultado = "Error: Entrada no es un número binario válido (use solo 0 y 1).";
                }
            } else if (operacion.equals("decToBin")) {
                nombreOperacion = "Cálculo de número decimal a binario";
                try {

                    int decimal = Integer.parseInt(numeroInput);
                    resultado = Integer.toBinaryString(decimal);
                } catch (NumberFormatException e) {
                    resultado = "Error: Ingrese un número entero válido.";
                }
            }
        }


        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Respuesta Ejercicio 1</title>");
        out.println("<style>");
        out.println("body { font-family: Tahoma, sans-serif; margin: 30px; background-color: #f4f6f9; }");
        out.println("h2 { color: #111827; margin: 4px 0; font-size: 1.4em; }");
        out.println("h3 { color: #4b5563; margin: 2px 0; font-size: 1.1em; font-weight: normal; }");
        out.println("table { border-collapse: collapse; width: 55%; margin-top: 20px; background: white; }");
        out.println("th, td { border: 1px solid #bdc3c7; padding: 10px; text-align: left; }");
        out.println("th { background-color: #34495e; color: white; }");
        out.println(".back-link { display: inline-block; margin-top: 25px; color: #2f5bea; text-decoration: none; font-weight: bold; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");


        out.println("<h2>Servlet Tarea 1 - Integrantes Ejercicio 1:</h2>");
        out.println("<h3>•JENNIFER MARIELA MARQUEZ CHACON</h3>");
        out.println("<h3>•PAOLA ABIGAIL PEÑA HERNANDEZ</h3>");
        out.println("<h3>• Juan David Rivera Aguilera</h3>");
        out.println("<h3>•JUAN CARLOS LOPEZ PORTILLO");
        out.println("<h3>• Marlon Benjamín Matute Rodríguez</h3>");
        out.println("<h3>• RIGOBERTO RAUDALES SEVILLA </h3>");
        out.println("<br><hr>");


        out.println("<table>");
        out.println("<thead><tr><th>Concepto</th><th>Detalle del Proceso</th></tr></thead>");
        out.println("<tbody>");
        out.println("<tr><td><b>Operación Realizada:</b></td><td>" + nombreOperacion + "</td></tr>");
        out.println("<tr><td><b>Datos de Entrada:</b></td><td>" + numeroInput + "</td></tr>");
        out.println("<tr><td><b>Resultado de Salida (Respuesta):</b></td><td><span style='color: green; font-weight: bold;'>" + resultado + "</span></td></tr>");
        out.println("</tbody>");
        out.println("</table>");


        out.println("<a class='back-link' href='index.jsp'>← Volver al Menú Principal</a>");

        out.println("</body>");
        out.println("</html>");
    }


    private String safeTrim(String value) {
        return value == null ? "" : value.trim();
    }
}