package lib;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
	private static PreparedStatement prp_statment = null;
	
	

	// visualizzo i dati presenti nel db
	public static Vector<Persona> visualizzaRubrica() throws SQLException  {
    	
    	inizializza();
    	Vector<Persona> vector = new Vector<Persona>();
        
    	try{
    		String query = 	"SELECT * FROM persona ORDER BY cognome";
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


	//inizializzo la connessione al db
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
	    		
	    		String persona_nome = p.getNome();
	    		String persona_cognome = p.getCognome();
	    		String persona_indirizzo = p.getIndirizzo();
	    		String persona_telefono = p.getTelefono();
	    		int persona_eta = p.getEta();
	    		
	    		String query = 	"INSERT INTO persona (id, nome, cognome, indirizzo, telefono, eta)"
	    						+ "VALUES (?,?,?,?,?,?)";					
	 
	    		prp_statment = connect.prepareStatement(query);
	    		prp_statment.setString(1,null);
	    		prp_statment.setString(2,persona_nome);
	    		prp_statment.setString(3,persona_cognome);
	    		prp_statment.setString(4,persona_indirizzo);
	    		prp_statment.setString(5,persona_telefono);
	    		prp_statment.setInt(6,persona_eta);	    		
	    		prp_statment.executeUpdate();	    		
	    		
	    	}catch(SQLException sql){ 
	    		sql.printStackTrace();
	    	}finally {            
	    		prp_statment.close();
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
    		    		
    		String query = 	"UPDATE persona SET  nome = ?, cognome = ?, indirizzo = ?, telefono = ?, eta = ? WHERE id = ?";    		
    		
    		prp_statment = connect.prepareStatement(query);
    		prp_statment.setString(1,nome);
    		prp_statment.setString(2,cognome);
    		prp_statment.setString(3,indirizzo);
    		prp_statment.setString(4,telefono);
    		prp_statment.setInt(5,eta);
    		prp_statment.setInt(6,id);	    		
    		prp_statment.executeUpdate();
    		
    	}catch(SQLException sql){    		
    		sql.printStackTrace();
    	}finally {            
    		prp_statment.close();
            connect.close();
        }    	
    }
	
	
	//elimino il contatto nel database
	public static void eliminaContatto(int id) throws SQLException  {
    	
    	inizializza();        
        
    	try{
    		    		
    		String query = 	"DELETE FROM persona "+
    						"WHERE id = "+ id;
    					     				
    		statement.execute(query);
    		
    	}catch(SQLException sql){    		
    		sql.printStackTrace();
    	}finally {            
            statement.close();
            connect.close();
        }    	
    }
	
	
	
	//ritorna il contatto nel database
		public static Persona cercaContatto(int id) throws SQLException  {
	    	
	    	inizializza();        
	    	Persona persona = null;
	    	try{
	    		    		
	    		String query = 	"SELECT * FROM persona "+
	    						"WHERE id = "+ id;
	    					     				
	    		resultSet = statement.executeQuery(query); 
	    		
	    		while(resultSet.next()){
	    			int ide = resultSet.getInt("id");
		    		String nome = resultSet.getString("nome");
		    		String cognome = resultSet.getString("cognome");
		    		String indirizzo = resultSet.getString("indirizzo");
		    		String telefono = resultSet.getString("telefono");
		    		int eta = resultSet.getInt("eta");
	    		
		    		persona = new Persona(ide, nome, cognome, indirizzo, telefono, eta);
	    		}
	    	}catch(SQLException sql){
	    		sql.printStackTrace();
	    	}finally {
	            statement.close();
	            connect.close();
	        }
	    	return persona;
	    }
		
	
	

}