package lib;

public class Persona {

	private String nome, cognome, indirizzo, telefono;
	private int eta,id;
	
	
	public Persona(int id, String nome,String cogn, String indir, String tel, int eta){
		this.id = id;
		this.nome = nome;
		this.cognome = cogn;
		this.indirizzo = indir;
		this.telefono = tel;
		this.eta = eta;
	}
	
	public Persona(String nome,String cogn, String indir, String tel, int eta){
		this.nome = nome;
		this.cognome = cogn;
		this.indirizzo = indir;
		this.telefono = tel;
		this.eta = eta;
	}
	
	public int getId(){ 
		return id;
	}
	
	public String getNome(){
		return nome;
	}
	
	public String getCognome(){
		return cognome;
				
	}
	
	public String getIndirizzo(){
		return indirizzo;
	}
	
	public String getTelefono(){
		return telefono;
	}
	
	public int getEta(){
		return eta;
	}
	
}
