/*CLASSE CONCIERGE*/	


import java.util.ArrayList;


public class Concierge {
/*VARIABLES*/		
	private ArrayList <PapotageListener> listeEcouteurs;
	private InterfaceConcierge interfaceGestion;
	
/*CONSTRUCTEUR*/
	public Concierge() {	
		this.listeEcouteurs = new ArrayList <PapotageListener>();
	}
	
/*GETTER*/
	public ArrayList<PapotageListener> getListeEcouteurs() {
		return listeEcouteurs;
	}	
	
/*SETTER*/
	public void setListeBavards(ArrayList<PapotageListener> le) {
		this.listeEcouteurs = le;
	}
	public void setIg(InterfaceConcierge interfaceGestion) {
		this.interfaceGestion=interfaceGestion;
	}
	
/*METHODES*/	
		
		//Permet de creer un bavard 
	    public Bavard generateBavard(String nom){
	        Bavard b = new Bavard(nom,this);
	        this.addEcouteurs(b);
	        this.interfaceGestion.afficheConnectes();
	        //et de retourner un bavard
	        return b;
	    }
	    
	    //Permet de connecter un bavard
	    public void connecteBavard (PapotageListener b) {
	    	b.setConnecte(true);
	    	this.interfaceGestion.afficheConnectes();
	    }
	    
	    //Permet de deconnecter un bavard
	    public void deconnecteBavard (Bavard b) {
	    	b.setConnecte(false);
	    	this.interfaceGestion.afficheConnectes();
	    }
	
		//Permet d'ajouter PapotageListener à la listes des écouteurs
		public void addEcouteurs(PapotageListener b){
	        listeEcouteurs.add(b);
	    }
	
		//Permet d'enlever un PapotageListener a la liste des ecouteurs
		public void removeEcouteur(PapotageListener b){
	        listeEcouteurs.remove(b);
	    }
		
		
	    //Permet d'envoyer un message à tous les personnes 
	    public void envoieMessageATous(PapotageEvent message , PapotageListener expediteur) {
	        for(PapotageListener a : this.getListeEcouteurs()) {
	        	a.getIb().afficheMessR(message,expediteur);
	        	expediteur.getIb().afficheMessE(message, expediteur);
	        	
	        	interfaceGestion.afficheMess(message, expediteur, a);
	        }    
	    }
	    
	    //Permet d'envoyer un message au destinataire selectionne
	    public void envoieMessage(PapotageEvent message,PapotageListener destinataire,PapotageListener expediteur) {
	        destinataire.getIb().afficheMessR(message,expediteur);
	        expediteur.getIb().afficheMessE(message,destinataire);
	        
	        interfaceGestion.afficheMess(message,expediteur,destinataire);
	    }
	    


}
