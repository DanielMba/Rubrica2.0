

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lib.Database;
import lib.Persona;

/**
 * Servlet implementation class CreaContatto
 */
@WebServlet(description = "crea un nuovo contatto nel db", urlPatterns = { "/CreaContatto" })
public class CreaContatto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("annulla") != null){
			request.getRequestDispatcher("/rubrica").forward(request, response);
			return;
		}
		
		String nome  = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String indirizzo = request.getParameter("indirizzo");
		String telefono = request.getParameter("telefono");
		String eta2 = request.getParameter("eta");
		
		try{
			if(nome != null && nome.length() > 1){
				if(cognome != null && cognome.length() > 1){
					if(indirizzo != null){
						if(telefono != null && telefono.matches("[0-9]+") && telefono.length() <= 15) {
							int eta = Integer.parseInt(eta2);
							if (eta > 0 && eta <= 100){								
								Database.inserisciContatto(new Persona(nome, cognome, indirizzo, telefono, eta));
								request.getRequestDispatcher("/rubrica").forward(request, response);
							}else{
								request.setAttribute("messaggioErrore", "eta non valida");
								request.getRequestDispatcher("/nuovo.jsp").forward(request, response);
							}
						}else{
							request.setAttribute("messaggioErrore", "telefono non valido");
							request.getRequestDispatcher("/nuovo.jsp").forward(request, response);
						}
					}else{
						request.setAttribute("messaggioErrore", "indirizzo non valido");
						request.getRequestDispatcher("/nuovo.jsp").forward(request, response);
					}
				}else{
					request.setAttribute("messaggioErrore", "cognome non valido");
					request.getRequestDispatcher("/nuovo.jsp").forward(request, response);
				}				
			}else{
				request.setAttribute("messaggioErrore", "nome non valido");
				request.getRequestDispatcher("/nuovo.jsp").forward(request, response);
			}		
		}catch(NumberFormatException | SQLException e) {
			request.setAttribute("messaggioErrore", "eta non valida");
			request.getRequestDispatcher("/nuovo.jsp").forward(request, response);		
		}
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		
	}

}