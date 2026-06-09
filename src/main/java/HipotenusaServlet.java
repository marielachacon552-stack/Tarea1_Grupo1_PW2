import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "hipotenusa", value = "/hipotenusa")
public class HipotenusaServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, jakarta.servlet.ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/hipotenusa.html");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, jakarta.servlet.ServletException {
        String aParam = request.getParameter("a");
        String bParam = request.getParameter("b");
        try {
            double a = Double.parseDouble(aParam);
            double b = Double.parseDouble(bParam);

            if (a <= 0 || b <= 0) {
                throw new NumberFormatException("Los catetos deben ser positivos");
            }

            double hipotenusa = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));

            String entrada = "Cateto A: " + String.format("%.2f", a) + "|Cateto B: " + String.format("%.2f", b);
            String salida = String.format("%.4f", hipotenusa);

            String url = "resultado.html?tarea=" + URLEncoder.encode("Calcular Hipotenusa", StandardCharsets.UTF_8.toString())
                    + "&entrada=" + URLEncoder.encode(entrada, StandardCharsets.UTF_8.toString())
                    + "&salida=" + URLEncoder.encode(salida, StandardCharsets.UTF_8.toString())
                    + "&volver=hipotenusa.html";

            response.sendRedirect(url);

        } catch (NumberFormatException e) {
            String entrada = "Cateto A: " + (aParam != null ? aParam : "") + "|Cateto B: " + (bParam != null ? bParam : "");
            String salida = "Error: datos inválidos. " + e.getMessage();
            String url = "resultado.html?tarea=" + URLEncoder.encode("Calcular Hipotenusa", StandardCharsets.UTF_8.toString())
                    + "&entrada=" + URLEncoder.encode(entrada, StandardCharsets.UTF_8.toString())
                    + "&salida=" + URLEncoder.encode(salida, StandardCharsets.UTF_8.toString())
                    + "&volver=hipotenusa.html";
            response.sendRedirect(url);
        }
    }
}
