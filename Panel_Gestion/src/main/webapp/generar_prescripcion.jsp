<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nueva receta</title>
</head>
<body>

<form method="get" action="PreinscripcionServlet">
	
		<label for="dni">DNI del paciente:</label> 
		<input type="text" id="dni" name="dni"> 
		
		<label for="fechaInicio">Fecha inicio de preinscripción:</label> 
		<input type="date" id="fechaInicio" name="fechaInicio"> 
		
		<label for="fechaFin">Fecha de fin de preinscripción::</label>
		<input type="date" id="fechaFin" name="fechaFin"> 
		
		<label for="periodicidad">Periodicidad (en horas):</label> 
		<input type="text" id="periodicidad" name="periodicidad"> 
		
		<input type="submit" value="Registrar preinscripción">
	</form>

</body>
</html>