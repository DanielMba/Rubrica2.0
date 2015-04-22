

import java.io.IOException;
import java.sql.SQLException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lib.Database;
import lib.Persona;

/**
 * Servlet implementation class Contatti
 */
@WebServlet(name = "index", description = "mostra i contatti presenti nella rubrica", urlPatterns = { "/index" })
public class Contatti extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				 
		try {
			Vector<Persona> vector = Database.visualizzaRubrica();
			request.setAttribute("contatti",vector);
	        request.getRequestDispatcher("/rubrica.jsp").forward(request, response);
		}catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}