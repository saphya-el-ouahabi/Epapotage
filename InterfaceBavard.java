/*CLASSE DE L'INTERFACE BAVARD (messagerie)*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;


public class InterfaceBavard extends JFrame implements ActionListener {

/*VARIABLES*/	
	private Bavard bavard;
	private Concierge concierge;
	
	//String
	private String msgRecus = ""; //chaine de characteres qui va contenir tous les messages recus
	private String msgEnvoyes = ""; //tous les messages envoyes
	
	//Jlabel
	private JLabel labelMsgRecu = new JLabel("Les messages reçus :");
	private JLabel labelMsgEnvoye = new JLabel("Les messages envoyés :");
	private JLabel labelBavard = new JLabel("Bavard :");

	//Jbutton
	private JButton boutonMsg = new JButton("Ecrire un Message");
	private JButton boutonDeco = new JButton("Deconnexion");

	//Jpanel + JtextPane
	private JPanel panel = new JPanel();

	JTextPane cadreMsgRecus = new JTextPane();
	JTextPane cadreMsgEnvoyes = new JTextPane();

	//JtextArea
	JTextArea bavardsCo = new JTextArea();	
	
	
/*CONSTRUCTEUR*/	
	public InterfaceBavard (Bavard bavard){
		super();
		this.bavard=bavard; 
		
		this.bavardsCo.setEditable(false); //pour ne pas pouvoir ecrire dans la zone des bavards connectes / deconnectes
		
		//Caracteristiques de la fenetre
		this.setTitle("Messagerie de "+this.bavard.getNom());
		this.setLocation(980,10);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 		
		
		//Caracteristiques des boutons
		boutonMsg.addActionListener(this);
		boutonMsg.setActionCommand("ecrire un nouveau msg");

	    boutonDeco.addActionListener(this);
	    boutonDeco.setActionCommand("se deconnecter");
	    
	    //Bordures
	 	Border border1=  BorderFactory.createEmptyBorder(5,5,5,5);
	 	Border border2=  BorderFactory.createEmptyBorder(10,10,10,10);
	 	Border border3=  BorderFactory.createEmptyBorder(5,5,5,5);
	 	
	    //Listes deroulantes
		JScrollPane scrollPane = new JScrollPane(bavardsCo);
	 	this.bavardsCo.setEditable(false);
	 	scrollPane.setBorder(border3);
	 	scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);

	 	JScrollPane scrollPane1 = new JScrollPane(this.cadreMsgRecus);
	 	this.cadreMsgRecus.setEditable(false);
	 	scrollPane1.setAlignmentX(Component.CENTER_ALIGNMENT);
	 	
	 	JScrollPane scrollPane2 = new JScrollPane(this.cadreMsgEnvoyes);
	 	this.cadreMsgEnvoyes.setEditable(false);
	 	scrollPane2.setAlignmentX(Component.CENTER_ALIGNMENT);

	 	//Layout
	 	BoxLayout layout = new BoxLayout(panel,BoxLayout.Y_AXIS);
	 	
	    //Caracteristiques des labels
	 	labelMsgRecu.setFont(new Font("Comic Sans MS", Font.BOLD,14));
	 	labelMsgRecu.setAlignmentX(Component.RIGHT_ALIGNMENT);
	 	
	 	labelMsgEnvoye.setFont(new Font("Comic Sans MS", Font.BOLD,14));
	 	labelMsgEnvoye.setBorder(border3);
	 	labelMsgEnvoye.setAlignmentX(Component.RIGHT_ALIGNMENT);
	 	
	 	labelBavard.setFont(new Font("Comic Sans MS", Font.BOLD,14));
	 	labelBavard.setBorder(border3);
	 	labelBavard.setAlignmentX(Component.RIGHT_ALIGNMENT);
	 	
	 	//Caracteristiques de la zone d'affichage des messages recus
	 	this.cadreMsgRecus.setBorder(border2);
		this.cadreMsgRecus.setPreferredSize(new Dimension(250, 100));
		this.cadreMsgRecus.setMinimumSize(new Dimension(100, 100));
		this.cadreMsgRecus.setAlignmentX(Component.CENTER_ALIGNMENT);
	 		
		//Caracteristiques de la zone d'affichage des messages envoyes
	 	this.cadreMsgEnvoyes.setBorder(border2); 	
		this.cadreMsgEnvoyes.setPreferredSize(new Dimension(150, 85));
		this.cadreMsgEnvoyes.setMinimumSize(new Dimension(10, 10));
	 	this.cadreMsgEnvoyes.setAlignmentX(Component.CENTER_ALIGNMENT);

	 	//Ensemblage des elements dans le panel
	 	panel.setBorder(border1);
	 	panel.setLayout(layout);
	 	panel.add(boutonDeco);
	 	panel.add(labelMsgRecu);
	 	
	 	panel.add(scrollPane1);
	 	panel.add(labelMsgEnvoye);
	 	
	 	panel.add(scrollPane2);
		panel.add(labelBavard);
		
	 	panel.add(scrollPane);
	 	panel.add(boutonMsg);

	 	
	 	panel.setBackground(Color.ORANGE);
	 		
	 	//pour rendre l'interface visible
	 	Container mainContainer = this.getContentPane();
	 	mainContainer.add(this.panel);
	 	pack();
		this.setVisible(false);
	}
	
/*SETTER*/	
	public void setBavard(Bavard bavard) {
		this.bavard = bavard;
	}
	
	public void setConcierge(Concierge concierge) {
		this.concierge = concierge;
	}
	
/*GETTER*/		
	public Bavard getBavard() {
		return bavard;
	}

/*METHODES*/
			//Permet de gerer l'action des boutons
			public void actionPerformed(ActionEvent e) {
				
				//si on clique sur le bouton nouveau message
			    if(e.getActionCommand().equals("ecrire un nouveau msg")) { 
			    	// on appelle l'interface message qui permet d'en ecrire un
			    	InterfaceMessage iM = new InterfaceMessage(bavard,concierge);
						iM.setConcierge(concierge);
			    }
			  
			   //si on clique sur le bouton deconnexion
			    if(e.getActionCommand().equals("se deconnecter")) {
			    	//le concierge deconnecte le bavard
			    	this.concierge.deconnecteBavard(bavard);
			    	//et on ferme l'interface
			    	this.dispose();
			    }
			    
			    //si on souhaite fermer cette interface
			    if(e.getActionCommand().equals(EXIT_ON_CLOSE)) { 
			    	// ceci est possible seulement par le bié du bouton deconnexion
			    	this.concierge.deconnecteBavard(bavard);
			    	this.dispose(); //fermeture
			    }
			}
		
			
			// Permet d'afficher les messages recus dans la zone dediee
			public void afficheMessageRecu(PapotageEvent mess, PapotageListener envoyeur) {
				 String phrase = "";
		
				 phrase += this.msgRecus = this.msgRecus 
						 + "Message de " + envoyeur.getNom() 
						 + "\n"+ "sujet :" + mess.getSujet() 
						 + "\n" + mess.getCorps()+ "\n";
				 
				 cadreMsgRecus.setText(phrase);	
			}
			
			
			// Permet d'afficher les messages envoyes dans la zone associee
			public void afficheMessageEnvoye(PapotageEvent mess, PapotageListener destinataire) {
				 String phrase = "";
		
				 phrase += this.msgEnvoyes = this.msgEnvoyes 
						 + "\n" + "à : "  + destinataire.getNom() 
						 + "\n"+ "sujet :" + mess.getSujet() 
						 + "\n" + mess.getCorps()+ "\n";
				 
				 cadreMsgEnvoyes.setText(phrase);	
			}
			
			
			// Permet d'afficher les personnes qui sont connectes
			public void afficherStatutBavard() {
				String bavardCo = "";
				
				//si il y a aucun autre utilisateur a par celui qui est connecte
				if (concierge.getListeEcouteurs().size()==1) {
					//alors ne rien afficher
					bavardCo = "";
				} 
				
				//si il y a d'autre utilisateur en plus de celui qui est connecte
				else {
					//on parcourt la liste des ecouteurs 
					for (PapotageListener bavard : concierge.getListeEcouteurs()) {
						//et si le pseudo du bavard est differente du pseudo passe en parametre
						if (!bavard.getNom().equals(this.bavard.getNom())){
							
							
							//afficher connectee
							if (bavard.isConnecte()) {
								bavardCo += bavard.getNom() + ": connecté(e) -"; 
							
							//sinon afficher deconnectee
							}else {
								bavardCo += bavard.getNom() + ": déconnecté(e) -"; 
							}
						}
					}
				}
				//affichage
				bavardsCo.setText(bavardCo); 
			}
			



}