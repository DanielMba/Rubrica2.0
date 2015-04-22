<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.Vector" %>
    <%@ page import="lib.Persona" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<style>
			table, th, td {
			    border: 1px solid black;
			    border-collapse: collapse;
			}
			th, td {
			    padding: 5px;
			    text-align: left;
			}
		</style>
		<title>Rubrica</title>
	</head>
	<body>
	
	<% Vector<Persona> contatti = (Vector<Persona>)request.getAttribute("contatti");
		if (contatti.size() == 0){ %>
			<tr><td></td>			
				<form action="/RubricaTomcat/MainServlet" method="post">
					<input type="submit" name ="nuovo" value="Nuovo">
				</form>
			</tr>
		<% }else{%>
			<table>
			  <tr>
			    <th>Cognome</th>
			    <th>Nome</th>
			    <th>Indirizzo</th>
			    <th>Telefono</th>
			    <th>Eta</th>
			  </tr>
			<%for(int i = 0; i < contatti.size(); i++){
				Persona p = contatti.get(i); %>
			<tr>
				<td><%= p.getCognome()%></td>
				<td><%= p.getNome()%></td>
				<td><%= p.getIndirizzo()%></td>
				<td><%= p.getTelefono()%></td>
				<td><%= p.getEta()%></td>
				<td>
					<form action="/RubricaTomcat/MainServlet" method="post">
						<input name="id" type="hidden" value="<%=p.getId()%>">
						<input type="submit" name ="nuovo" value="Nuovo">
						<input type="submit" name ="modifica" value="Modifica">
						<input type="submit" name ="elimina" value="Elimina">
					</form>
				</td>			
			</tr>			
		<%}
		}%>
		</table>	
	</body>
</html>