<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.sanivoice.gestion_interna.GestorUsuarios"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Registrar CITA</h1> 

	<form method="post" action="">
        <label for="dni">DNI:</label>
        <input type="text" id="dni" name="dni" required><br><br>

		<label for="opciones">Selecciona una opci�n:</label>
        <%GestorUsuarios.getGestorUsuarios().obtenerCentro("dni");%>

        <input type="submit" value="Enviar">
    </form>

</body>
</html>