/*CLASSE BAVARD QUI IMPLEMENTE PAPOTAGELISTENER */	

import java.util.ArrayList;


public class Bavard implements PapotageListener {

/*VARIABLES*/
	private String nom;
	private boolean connecte = false;
	private Concierge concierge;
	private InterfaceBavard interfaceBavard = null;


/*CONSTRUCTEUR*/		
	public Bavard(String nom, Concierge concierge){
		this.nom=nom;
		this.concierge = concierge;
	}
	
/*GETTER*/	
	public InterfaceBavard getIb() {
		return interfaceBavard;
	}
	
	public String getNom() {
		return nom;
	}

/*SETTER*/	
	public void setInterfaceBavard(InterfaceBavard interfaceBavard) {
		this.interfaceBavard = interfaceBavard;
	}

	public void setConnecte(boolean connecte) {
		this.connecte = connecte;
	}

//
	public boolean isConnecte() {
		return connecte;
	}
	
	
/* METHODES */		
	
		// Permet d'envoyer un message à une personne précise
		public void envoyerMessage(String sujet,String corps,PapotageListener destinataire,Bavard envoyeur){
			PapotageEvent papoEvent = new PapotageEvent(this,sujet,corps);
			
			this.concierge.envoieMessage(papoEvent, destinataire, envoyeur);
		}
		
		// Permet d'envoyer un message à toutes les personnes d'Epapotage
		public void envoyerMessageATous(String sujet,String corps) {
			PapotageEvent papoEvent = new PapotageEvent(this,sujet,corps);
			
			concierge.envoyerMessageATous(papoEvent, this);
		}


}
