
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
<title>Insert title here</title>
</head>
<body>
	<h1>LOGIN</h1>
	<form method="post" action="LoginServlet">
        <label for="email">Email:</label>
        <input type="text" id="email" name="email" required><br><br>

        <label for="clave">Clave:</label>
        <input type="text" id="clave" name="clave" required><br><br>

        <input type="submit" value="Enviar">
    </form>
</body>
</html>