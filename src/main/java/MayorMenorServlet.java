import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@WebServlet(name = "numeros", value = "/numeros")
public class MayorMenorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            request.getRequestDispatcher("/numeros.html").forward(request, response);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "No se pudo mostrar el formulario.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String operacion = request.getParameter("operacion");
        String numerosInput = request.getParameter("numeros");

        String entrada = "Números: " + (numerosInput != null ? numerosInput : "");
        String salida;
        String tarea = "Operación";

        try {
            if (numerosInput == null || numerosInput.trim().isEmpty()) {
                salida = "Error: Por favor ingrese los números.";
            } else {
                String[] partes = numerosInput.split(",");
                List<Integer> numeros = new ArrayList<>();

                for (String p : partes) {
                    if (p.trim().isEmpty()) continue;
                    numeros.add(Integer.parseInt(p.trim()));
                }

                if (numeros.isEmpty()) {
                    salida = "Error: Debe ingresar al menos un número.";
                } else if ("mayor".equals(operacion)) {
                    tarea = "Encontrar el Mayor";
                    if (numeros.size() < 3) {
                        salida = "Error: Para encontrar el mayor necesita al menos 3 números.";
                    } else {
                        int mayor = Collections.max(numeros);
                        salida = String.valueOf(mayor);
                    }
                } else if ("menor".equals(operacion)) {
                    tarea = "Encontrar el Menor";
                    if (numeros.size() < 3) {
                        salida = "Error: Para encontrar el menor necesita al menos 3 números.";
                    } else {
                        int menor = Collections.min(numeros);
                        salida = String.valueOf(menor);
                    }
                } else if ("moda".equals(operacion)) {
                    tarea = "Encontrar la Moda";
                    Map<Integer, Integer> frecuencias = new HashMap<>();
                    for (int num : numeros) {
                        frecuencias.put(num, frecuencias.getOrDefault(num, 0) + 1);
                    }

                    int maxFrecuencia = 0;
                    List<Integer> modas = new ArrayList<>();

                    for (Map.Entry<Integer, Integer> entry : frecuencias.entrySet()) {
                        if (entry.getValue() > maxFrecuencia) {
                            maxFrecuencia = entry.getValue();
                            modas.clear();
                            modas.add(entry.getKey());
                        } else if (entry.getValue() == maxFrecuencia && maxFrecuencia > 1) {
                            modas.add(entry.getKey());
                        }
                    }

                    if (maxFrecuencia <= 1) {
                        salida = "No hay moda. Todos los números tienen la misma frecuencia.";
                    } else {
                        String modasStr = modas.toString().replaceAll("[\\[\\]]", "");
                        salida = "Moda: " + modasStr + " (aparece " + maxFrecuencia + " veces)";
                    }
                } else {
                    salida = "Error: Operación inválida.";
                }
            }
        } catch (NumberFormatException e) {
            salida = "Error: Ingrese solo números enteros separados por comas.";
        }

        String url = "resultado.html?tarea=" + URLEncoder.encode(tarea, StandardCharsets.UTF_8.toString())
                + "&entrada=" + URLEncoder.encode(entrada.replaceAll("\\n", "|"), StandardCharsets.UTF_8.toString())
                + "&salida=" + URLEncoder.encode(salida, StandardCharsets.UTF_8.toString())
                + "&volver=numeros.html";

        response.sendRedirect(url);
    }
}