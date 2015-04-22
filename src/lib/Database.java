package lib;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;





public class Database {
	private static Connection connect = null;
	private static Statement statement = null;
	private static Context context = null;
	private static DataSource datasource = null;
	private static ResultSet resultSet = null;
	
	

	// visualizzo i dati presenti nel db
	public static Vector<Persona> visualizzaRubrica() throws SQLException  {
    	
    	inizializza();
    	Vector<Persona> vector = new Vector<Persona>();
        
    	try{
    		String query = 	"SELECT * FROM persona";
    		resultSet = statement.executeQuery(query);
    		
    		
    		while(resultSet.next()){
	    		int id = resultSet.getInt("id");
	    		String nome = resultSet.getString("nome");
	    		String cognome = resultSet.getString("cognome");
	    		String indirizzo = resultSet.getString("indirizzo");
	    		String telefono = resultSet.getString("telefono");
	    		int eta = resultSet.getInt("eta");
	    		vector.add(new Persona(id, nome, cognome, indirizzo, telefono, eta));
    		}
    		
    	}catch(SQLException sql){
    		sql.printStackTrace();
    	}finally {
            statement.close();
            connect.close();
        }
    	return vector;
    }


	//inizializzo il connessione al db
	private static void inizializza() {
		try {
			context = new InitialContext();
			datasource = (DataSource) context.lookup("java:/comp/env/jdbc/rubrica");
			connect = datasource.getConnection();
           	statement = connect.createStatement();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}        
	}
	
	
	//inserisco un nuovo contatto nel db
	public static void inserisciContatto(Persona p) throws SQLException  {
	    	
	    	inizializza();        
	        
	    	try{
	    		
	    		String nome = p.getNome();
	    		String cognome = p.getCognome();
	    		String indirizzo = p.getIndirizzo();
	    		String telefono = p.getTelefono();
	    		int eta = p.getEta();
	    		
	    		String query = 	"INSERT INTO persona"
	    					  + "VALUES("+ "" +nome + cognome + indirizzo + telefono + eta + ")";	    				
	    		resultSet = statement.executeQuery(query);
	    		
	    	}catch(SQLException sql){    		
	    		sql.printStackTrace();
	    	}finally {            
	            statement.close();
	            connect.close();
	        }    	
	    }
	
	
	
	//modifico il contatto nel database 
	public static void modificaContatto(int id,Persona p) throws SQLException  {
    	
    	inizializza();        
        
    	try{
    		String nome = p.getNome();
    		String cognome = p.getCognome();
    		String indirizzo = p.getIndirizzo();
    		String telefono = p.getTelefono();
    		int eta = p.getEta();    		
    		
    		String query = 	"UPDATE persona"+
    						"SET nome = " + nome +
    						"cognome = "+ cognome +
    						"indirizzo = "+ indirizzo +
    						"telefono = "+ telefono +
    						"eta = "+ eta + 
    						"WHERE id = "+ id;
    					     				
    		resultSet = statement.executeQuery(query);
    		
    	}catch(SQLException sql){    		
    		sql.printStackTrace();
    	}finally {            
            statement.close();
            connect.close();
        }    	
    }
	
	
	//elimino il contatto nel database
	public static void eliminaContatto(int id) throws SQLException  {
    	
    	inizializza();        
        
    	try{
    		    		
    		String query = 	"DELETE FROM persona"+
    						"WHERE id = "+ id;
    					     				
    		resultSet = statement.executeQuery(query);
    		
    	}catch(SQLException sql){    		
    		sql.printStackTrace();
    	}finally {            
            statement.close();
            connect.close();
        }    	
    }
		
	
	

}