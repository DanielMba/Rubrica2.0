<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Crea Contatto</title>
</head>
	<body>
		<form action="/RubricaTomcat/CreaContatto" method="post">
			<div style="color: #FF0000;">${messaggioErrore}</div>
			Nome:<br>
			<input type="text" name="nome"><br>	
			Cognome:<br>
			<input type="text" name="cognome"><br>
			Indirizzo:<br>
			<input type="text" name="indirizzo"><br>
			Telefono:<br>
			<input type="text" name="telefono"><br>
			Eta:<br>
			<input type="text" name="eta"><br>
			<p>
			<input type="submit" name ="salva" value="Salva">
			<input type="submit" name="annulla" value="Annulla">
			</p>
		</form>
	</body>
</html>