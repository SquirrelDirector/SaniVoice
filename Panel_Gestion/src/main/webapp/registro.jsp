
<%
//Para mandar redirecciones desde JSP es así
//response.sendRedirect("http://google.es");
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title></title>
</head>
<body>
	<h1>REGISTRO</h1>
	<form method="post" action="RegistroServlet">
	
		<label for="nombre">Nombre:</label> 
		<input type="text" id="nombre" name="nombre"> 
		<label for="apellidos">Apellidos:</label> 
		<input type="text" id="apellidos" name="apellidos"> 
		<label for="dni">DNI:</label>
		<input type="text" id="dni" name="dni"> 
		<label for="tarjeta_sanitaria">Tarjeta sanitaria:</label> 
		<input type="text" id="tarjeta_sanitaria" name="tarjeta_sanitaria"> 
		<label for="telefono">Teléfono:</label> 
		<input type="text" id="telefono" name="telefono"> 
		<label for="domicilio">Domicilio:</label>
		<input type="text" id="domicilio" name="domicilio"> 
		<label for="correo_electronico">Correo electrónico:</label> 
		<input type="text" id="correo_electronico" name="correo_electronico"> 
		<label for="contraseña">Contraseña:</label> 
		<input type="text" id="contraseña" name="contraseña">
		
		<input type="submit" value="Enviar">
	</form>
</body>
</html>