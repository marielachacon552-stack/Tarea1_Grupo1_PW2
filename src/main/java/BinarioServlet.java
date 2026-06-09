import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "binarios", value = "/binarios")
public class BinarioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            request.getRequestDispatcher("/binario.html").forward(request, response);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "No se pudo mostrar el formulario.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String tipo = request.getParameter("tipo");
        String valor = request.getParameter("valor");

        String entrada = "Valor: " + (valor != null ? valor : "");
        String salida;
        String tarea = "Conversión";

        try {
            if (valor == null || valor.trim().isEmpty()) {
                salida = "Error: Por favor ingrese un valor.";
            } else if ("bin2dec".equals(tipo)) {
                tarea = "Conversión de Binario a Decimal";
                int decimal = Integer.parseInt(valor.trim(), 2);
                salida = String.valueOf(decimal);
            } else if ("dec2bin".equals(tipo)) {
                tarea = "Conversión de Decimal a Binario";
                int decimal = Integer.parseInt(valor.trim());
                String binario = Integer.toBinaryString(decimal);
                salida = binario;
            } else {
                salida = "Error: Tipo de conversión inválido.";
            }
        } catch (NumberFormatException e) {
            salida = "Error: El valor ingresado no es válido para el tipo de conversión seleccionado.";
        }

        String url = "resultado.html?tarea=" + URLEncoder.encode(tarea, StandardCharsets.UTF_8.toString())
                + "&entrada=" + URLEncoder.encode(entrada.replaceAll("\\n", "|"), StandardCharsets.UTF_8.toString())
                + "&salida=" + URLEncoder.encode(salida, StandardCharsets.UTF_8.toString())
                + "&volver=binario.html";

        response.sendRedirect(url);
    }
}