<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="lib.Persona" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Modifica Contatto</title>
	</head>
		<body>
		
			<% Persona persona = (Persona)request.getAttribute("contatto"); %>
		
			<form action="/RubricaTomcat/ModificaContatto" method="post">
					<div style="color: #FF0000;">${messaggioErrore}</div>
					Nome:<br>
					<input type="text" name="nome" value="<%=persona.getNome()%>"><br>	
					Cognome:<br>
					<input type="text" name="cognome" value="<%=persona.getCognome()%>"><br>
					Indirizzo:<br>
					<input type="text" name="indirizzo" value="<%=persona.getIndirizzo()%>"><br>
					Telefono:<br>
					<input type="text" name="telefono" value="<%=persona.getTelefono()%>"><br>
					Eta:<br>
					<input type="text" name="eta" value="<%=persona.getEta()%>"><br>			
					<p>
					<input name="id" type="hidden" value="<%=persona.getId()%>">
					<input type="submit" name ="aggiorna" value="Aggiorna">
					<input type="submit" name="annulla" value="Annulla">
					</p>
				</form>
				
		</body>
</html>