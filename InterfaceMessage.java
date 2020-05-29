/*CLASSE DE L'INTERFACE MESSAGE*/	

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

public class InterfaceMessage extends JFrame implements ActionListener{

/*VARIABLES*/	
	private Bavard bavard;
	private Concierge concierge;
	
	private JComboBox liste = new JComboBox(); //liste deroulante

	//Jpanel
	private JPanel panel1 = new JPanel();
	private JPanel panel2 = new JPanel();
	
	//Jlabel
	private JLabel destinataire = new JLabel("À :");
	private JLabel objet = new JLabel("Objet :");
	private JLabel corps = new JLabel("Corps :");	
	
	//Jtextfiel
	private JTextField cadreObjet = new JTextField("",20);
	private JTextField cadreCorps = new JTextField("",20);
	

	//Jbouton
	private JButton boutonEnvoyer = new JButton("Envoyer");
	private JButton boutonAnnuler = new JButton("Annuler");
	private JButton boutonPartagerATous = new JButton("Partager A tous");
	

/*CONSTRUCTEUR*/
	public InterfaceMessage(Bavard bavard, Concierge concierge) {
		super();
		
		this.bavard = bavard;
		this.concierge = concierge;
		
		// Boucle for qui permet d'afficher tous les bavards dans un liste deroulante "liste"
        for(PapotageListener popoListener : this.concierge.getListeEcouteurs()) {
        	liste.addItem(popoListener.getNom());
        }
         
		//Caracteristiques de la fenetre
		setTitle("Ecrire un nouveau message");
		this.setLocation(980,470);
		
		Border border=  BorderFactory.createEmptyBorder(5,10,5,10);
		
		//Caracteristiques des 3 boutons 
		boutonEnvoyer.addActionListener(this);
		boutonEnvoyer.setActionCommand("e");
		
		boutonAnnuler.addActionListener(this);
		boutonAnnuler.setActionCommand("a");	
		
        boutonPartagerATous.addActionListener(this);
        boutonPartagerATous.setActionCommand("p");
		
		//Container
		Container mainContainer = this.getContentPane();
		mainContainer.add(this.panel1);
		
		//Layout
		BoxLayout layout = new BoxLayout(panel1,BoxLayout.Y_AXIS);
		
		//Caracteristiques des Labels
		destinataire.setAlignmentX(Component.CENTER_ALIGNMENT);
		destinataire.setFont(new Font("Comic Sans MS", Font.BOLD,14)); 
		destinataire.setForeground(Color.black);
		
		objet.setAlignmentX(Component.CENTER_ALIGNMENT);
		objet.setFont(new Font("Comic Sans MS", Font.BOLD,14)); 
		objet.setForeground(Color.gray);
		
		corps.setAlignmentX(Component.CENTER_ALIGNMENT);
		corps.setFont(new Font("Comic Sans MS", Font.BOLD,14)); 
		corps.setForeground(Color.white);

		// Mise en place du panel n°1
		panel1.setLayout(layout);
		
		panel1.add(destinataire);
		panel1.add(liste);
		
		panel1.add(objet);
		panel1.add(cadreObjet);
		
		panel1.add(corps);
		panel1.add(cadreCorps);
		
		panel1.setBorder(border);
		panel1.setBackground(Color.ORANGE);
		
		// Mise en place du panel n°2
		panel2.add(boutonPartagerATous);
		panel2.add(boutonEnvoyer);
		panel2.add(boutonAnnuler);
		
		panel1.add(panel2);
		panel2.setBackground(Color.ORANGE);
		
		pack();
		setVisible(true);	
	}
/*SETTER*/
	public void setConcierge(Concierge concierge) {
		this.concierge = concierge;
	}
	
/*GETTER*/
	public Concierge getConcierge() {
		return concierge;
	}

/*METHODES*/
		//Permet de gerer les actions des 3 boutons
		public void actionPerformed(ActionEvent e) {
			
			//si on clique sur le bouton "Envoyer"
		    if(e.getActionCommand().equals("e")) { 
		    	Object selected = this.liste.getSelectedItem();
		    	PapotageListener destinataire = getPapotageListenerListe(selected);
		    	//alors le message sera envoye au destinataire selectionne dans la liste deroulante "liste"
		    	this.bavard.envoyerMessage(cadreObjet.getText(), cadreCorps.getText(), destinataire,this.bavard);
		    	//une fois l'appuie sur le bouton est fait la fenetre se ferme
		    	this.dispose();
		    }
		    
		    //si on clique sur le bouton "Partager A tous"
		    if(e.getActionCommand().contentEquals("p")) { 
		    	// Envoie un message a tous les destinataires
		    	//alors le message sera envoye à tous les bavards qui ont ete cree, apparaissant dans la liste deroulante
		    	this.bavard.envoieMessageATous(cadreObjet.getText(), cadreCorps.getText());
		    	//une fois l'appuie sur le bouton est fait la fenetre se ferme
		    	this.dispose(); 
		    }
		    
		   //si on clique sur le bouton "Annuler"
		    if(e.getActionCommand().equals("a")) { 
		    	//une fois l'appuie sur le bouton est fait la fenetre se ferme
		    	this.dispose();
		    }}
		
		//Permet de retourner le PapotageListener
		public PapotageListener getPapotageListenerListe(Object selected) {
			//parcourt la liste des ecouteurs
			for(PapotageListener papoListener : this.concierge.getListeEcouteurs()) {
				//si le pseudo en parametre = à un pseudo de la liste
				if (selected.equals(papoListener.getNom())){
					//on retourne le papoListener
					return papoListener;
				}
			}
			//sinon rien
			return null;
		}

}