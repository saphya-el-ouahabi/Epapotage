/*CLASS PAPOTAGE EVENT QUI EXTENDS D'UN OBJET EVENT*/	

import java.util.EventObject;


public class PapotageEvent extends EventObject {
	
/*VARIABLES*/		
	private String sujetMess;
	private String contenuMess;
	
	
/*CONSTRUCTEUR*/
	public PapotageEvent(Object source,String sujetMess, String contenuMess) {
		super(source);
		this.contenuMess=contenuMess;
		this.sujetMess = sujetMess;
	}

	
/*SETTER*/	
	public String getSujet() {
		return sujetMess;
	}

	public String getCorps() {
		return contenuMess;
	}
}