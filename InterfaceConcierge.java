/*CLASSE DE L'INTERFACE CONCIERGE*/

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;

public class InterfaceConcierge extends JFrame implements ActionListener {

/*VARIABLES*/
	private Concierge concierge;
	private String message="";	
	
	//Jpanel
	private JPanel panel = new JPanel();
	private JPanel panel2 = new JPanel(); //zone pour ajouter un bavard	
	private JLabel titre = new JLabel("Boite de réception"); 
	private JLabel titre2 = new JLabel("Bavards"); 
	private JLabel labelNom = new JLabel("Nouveau bavard "); 
	
	//Jbutton
	private JButton boutonAjouterBavard = new JButton("Ajouter");

	//JtextField
	private JTextField texte = new JTextField("",8); 

	
	//JtextPane
	private JTextPane messageReception = new JTextPane();
	
	JTextArea bavardsCo = new JTextArea();	
	
/*CONSTRUCTEUR*/	
	public InterfaceConcierge(Concierge concierge) {
		super();
		
		//on relie le concierge et l interface
		this.concierge = concierge;
		this.concierge.setIg(this);		
		
		//on relie le concierge et l'interface a interfaceConnexion
		InterfaceConnexion interfaceConnexion = new InterfaceConnexion(); //creation interface de connexion
		interfaceConnexion.setConcierge(concierge);
		interfaceConnexion.setIg(this);
		
		//Caracteristiques de la fenetre 
		this.setTitle("Concierge"); //On donne un titre à l'application
		setLocation(10, 10);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit a l application de se fermer lors du clic sur la croix			
		
		//Layout
		BoxLayout layout = new BoxLayout(panel,BoxLayout.Y_AXIS); //layout
		panel.setLayout(layout); //mise en place du layout dans le panel
		
		Border zoneVide=  BorderFactory.createEmptyBorder(10,10,10,10); //pour creer des espaces vides entre les differents elem
		panel.setBorder(zoneVide); //ajout des espaces vides au panel
		
		//1 - panelUn : ajout du 1er titre
		titre.setAlignmentX(Component.CENTER_ALIGNMENT); //pour que les "titres" soient centrés - "boites de reception :"
		titre.setFont(new Font("Comic Sans MS", Font.BOLD,14)); 
		panel.add(titre); //ajout du titre au panel	  	
		
		//zone pour ajouter un bavard
		labelNom.setForeground(Color.darkGray); //attribut du texte
		labelNom.setFont(new Font("Comic Sans MS", Font.BOLD,14)); 
		
		panel2.add(labelNom); //"ajouter un bavard :" a cote de la zone de saisie
		panel2.add(texte); //zone de saisie
		panel2.add(boutonAjouterBavard); //ajout du bouton dans le panel
		
		panel2.setBackground(Color.ORANGE);
		
		boutonAjouterBavard.addActionListener(this); //boutons en ecouteurs
        boutonAjouterBavard.setActionCommand("ajouterBavard");			
		
        //2 - panelUn : ajout de la zone pour voir les messages
	 	this.messageReception.setEditable(false); //pour ne pas pouvoir ecrire dans la zone des messages
	 	
	 	JScrollPane scrollPaneMessage = new JScrollPane(this.messageReception); 
	 	panel.add(scrollPaneMessage);
		
	 	this.messageReception.setPreferredSize(new Dimension(300, 200)); //dimension de la zone ou les mesages s affichent
		this.messageReception.setMinimumSize(new Dimension(30, 30));
		
		//3 - panelUn : ajout du 2e titre
		titre2.setAlignmentX(Component.CENTER_ALIGNMENT); //"bavards :"
		titre2.setFont(new Font("Comic Sans MS", Font.BOLD,14)); 
		panel.add(titre2);
		
		//4 - panelUn : ajout de la zone pour voir les bavards connectes / deconnectes
		this.bavardsCo.setEditable(false); //pour ne pas pouvoir ecrire dans la zone des bavards connectes / deconnectes
		
		JScrollPane scrollPaneBavardCo = new JScrollPane(bavardsCo);
		panel.add(scrollPaneBavardCo); 
		
		panel.add(panel2);
		panel.setBackground(Color.ORANGE);
		
		//pour rendre l'interface visible
		Container container = this.getContentPane(); 
		container.add(panel);
		pack();
		setVisible(true);	
	}
	
/*GETTER*/	
	public Concierge getConcierge() {
		return concierge;
	}
	
/*METHODES*/	
			//Permet d'afficher le messages dans son englobalite
			public void afficheMess(PapotageEvent mess, PapotageListener envoyeur, PapotageListener destinataire) {		
				 String phrase = "";

				 phrase += this.message = this.message + "Message de " + envoyeur.getNom() 
				 + "\n" + "à : "  + destinataire.getNom() 
				 + "\n"+ "sujet :" + mess.getSujet() 
				 + "\n" + mess.getCorps()+ "\n";				
				 messageReception.setText(phrase);	
			}
			
			//Permet d'afficher les bavards connectes / deconnectes
			public void afficheConnectes() {
				String bavardCo="";
				 //on parcourt la liste des bavards
				for (PapotageListener bavard : concierge.getListeEcouteurs()) {
					//si le bavard est connecte
					if (bavard.isConnecte()) { 
						//on recupere le pseudo du bavard 
						bavard.getIb().afficheConnectes(); 
						//on affiche son statut : "connecte"
						bavardCo +=  "bavard " + bavard.getNom() + ": connecté(e) - " ; 
					//si le bavard n est pas connecte	
					}else { 
						//on affiche son statut "deconnecte"
						bavardCo += "bavard " + bavard.getNom() + ": déconnecté(e) - " ; 
					}
					//affichage
					bavardsCo.setText(bavardCo); 
				}
			}
			
			//Permet de gerer l'action du bouton pour ajouter un bavard
		    public void actionPerformed(ActionEvent e) {
		        ArrayList<String> listeNoms = new ArrayList<String>();
		        
		      //si on clique sur le bouton creer un nouveau bavard
		        if (e.getActionCommand().equals("ajouterBavard")){ 
		        	//on parcourt la liste des ecouteurs
		            for (PapotageListener pl : this.concierge.getListeEcouteurs()) { 
		            	//on ajoute le pseudo du bavard a la liste
		                listeNoms.add(pl.getNom()); 
		            }
		            
		            String nomBavard = texte.getText();
		            //si aucun nom est entre
		            if (nomBavard.isEmpty()) { 
		            	texte.setText("");
		            	
		            //si un bavard a deja le meme nom
		            }else if (listeNoms.contains(nomBavard)) { 
		            	texte.setText("");
		            
		            //sinon
		            }else {
		            	//creation du bavard a partir du pseudo entre
		            	Bavard bavard = concierge.generateBavard(nomBavard); 
		            	InterfaceBavard nouveauBavard = new InterfaceBavard(bavard);
		            	texte.setText("");
		            	nouveauBavard.setBavard(bavard);
		            	nouveauBavard.setConcierge(concierge);
		            	bavard.setIb(nouveauBavard);
		            }
		        }
		    }
}