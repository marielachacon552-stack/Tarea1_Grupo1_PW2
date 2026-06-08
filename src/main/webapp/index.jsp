<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tarea 1 - Programación Web 2</title>
    <style>
        body { font-family: sans-serif; margin: 40px; background-color: #fafafa; }
        .box { background: white; padding: 20px; border: 1px solid #ddd; width: 350px; }
        .field { margin-bottom: 12px; }
        label { display: block; margin-bottom: 4px; font-weight: bold; }
        input, select { width: 100%; padding: 6px; }
        button { background: #2f5bea; color: white; padding: 8px; width: 100%; border: none; cursor: pointer; margin-top: 10px;}
    </style>
</head>
<body>
<h1>Menú de Navegación - Grupo 1</h1>
<p>Despliegue de los ejercicios de la guía práctica:</p>
<hr><br>

<div class="box">
    <h3>Ejercicio 1: Servlet Binarios</h3>
    <form action="servlet-binarios" method="post">
        <div class="field">
            <label>Operación:</label>
            <select name="operacion">
                <option value="binToDec">Binario a Decimal</option>
                <option value="decToBin">Decimal a Binario</option>
            </select>
        </div>
        <div class="field">
            <label>Ingrese el número:</label>
            <input type="text" name="numero" required placeholder="Ej: 111 o 25">
        </div>
        <button type="submit">Procesar Datos</button>
    </form>
</div>
</body>
</html>