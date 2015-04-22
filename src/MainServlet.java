

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
 * Servlet implementation class MainServlet
 */

@WebServlet(description = "gestisci le diverse operazioni", urlPatterns = { "/MainServlet" })
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Persona persona = null;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("nuovo") != null){
			request.getRequestDispatcher("/nuovo.jsp").forward(request, response);
		}else if(request.getParameter("modifica") != null){
			
			int id = Integer.parseInt(request.getParameter("id"));
			
			try {
				persona = Database.cercaContatto(id);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			request.setAttribute("contatto",persona);
			request.getRequestDispatcher("/modifica.jsp").forward(request, response);
			
		}else if(request.getParameter("elimina") != null){
			
			int id = Integer.parseInt(request.getParameter("id"));
			try {
				Database.eliminaContatto(id);
			} catch (SQLException e) {
				e.printStackTrace();
			}			
			request.getRequestDispatcher("/rubrica").forward(request, response);
		}		
	}

}
