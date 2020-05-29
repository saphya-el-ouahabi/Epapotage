import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;

public class InterfaceConcierge extends JFrame implements ActionListener {
	
	private Concierge concierge;	
	private JPanel panelUn = new JPanel();
	private JPanel panelDeux = new JPanel(); //zone pour ajouter un bavard	
	private JButton boutonAjouterBavard = new JButton("Ajouter");
	private JLabel titreUn = new JLabel("Boite de réception"); 
	private JLabel titreDeux = new JLabel("Bavards"); 
	private JLabel labelNom = new JLabel("Nouveau bavard "); 
	private String message="";	
	private JTextField texte = new JTextField("",10); 		
	private JTextPane messageReception = new JTextPane();
	JTextArea bavardsCo = new JTextArea();	
	
	public InterfaceConcierge(Concierge consierge) {
		super();
		
		//on relie le concierge et l interface
		this.concierge = consierge;
		this.concierge.setIg(this);		
		
		//on relie le concierge et l'interface a interfaceConnexion
		InterfaceConnexion interfaceConnexion = new InterfaceConnexion(); //creation interface de connexion
		interfaceConnexion.setConcierge(consierge);
		interfaceConnexion.setIg(this);
		
		this.setTitle("Concierge"); //On donne un titre à l'application
		this.setLocationRelativeTo(null); //On centre la fenetre sur l'ecran
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit a l application de se fermer lors du clic sur la croix			
		BoxLayout layout = new BoxLayout(panelUn,BoxLayout.Y_AXIS); //layout
		panelUn.setLayout(layout); //mise en place du layout dans le panel
		Border zoneVide1=  BorderFactory.createEmptyBorder(30,35,30,35); //pour creer des espaces vides entre les differents elem
		panelUn.setBorder(zoneVide1); //ajout des espaces vides au panel
		//1 - panelUn : ajout du 1er titre
		titreUn.setAlignmentX(Component.CENTER_ALIGNMENT); //pour que les "titres" soient centrés - "boites de reception :"
		panelUn.add(titreUn); //ajout du titre au panel	  	
		
		//zone pour ajouter un bavard
		labelNom.setForeground(Color.darkGray); //attribut du texte
		labelNom.setFont(new Font("Arial",Font.ITALIC,10)); 
		panelDeux.add(labelNom); //"ajouter un bavard :" a cote de la zone de saisie
		panelDeux.add(texte); //zone de saisie
		panelDeux.add(boutonAjouterBavard); //ajout du bouton dans le panel
		boutonAjouterBavard.addActionListener(this); //boutons en ecouteurs
        boutonAjouterBavard.setActionCommand("createBavard");			
		
        //2 - panelUn : ajout de la zone pour voir les messages
	 	this.messageReception.setEditable(false); //pour ne pas pouvoir ecrire dans la zone des messages
	 	JScrollPane scrollPaneMessage = new JScrollPane(this.messageReception); 
	 	panelUn.add(scrollPaneMessage);
		this.messageReception.setPreferredSize(new Dimension(300, 200)); //dimension de la zone ou les mesages s affichent
		this.messageReception.setMinimumSize(new Dimension(10, 10));
		//3 - panelUn : ajout du 2e titre
		titreDeux.setAlignmentX(Component.CENTER_ALIGNMENT); //"bavards :"
		panelUn.add(titreDeux);
		//4 - panelUn : ajout de la zone pour voir les bavards connectes / deconnectes
		bavardsCo.setEditable(false); //pour ne pas pouvoir ecrire dans la zone des bavards connectes / deconnectes
		JScrollPane scrollPane = new JScrollPane(bavardsCo);
		panelUn.add(scrollPane, BorderLayout.CENTER); 
		panelUn.add(panelDeux);
		
		//pour rendre l'interface visible
		Container container = this.getContentPane(); 
		container.add(panelUn);
		pack();
		setVisible(true);	
	}
	
	//getter
	public Concierge getConcierge() {
		return concierge;
	}
	
	//affiche tous les messages envoyes
	public void afficheMess(PapotageEvent mess, PapotageListener envoyeur, PapotageListener destinataire) {		
		 String phrase = "";		 
		 phrase += this.message = this.message + "Message de " + envoyeur.getNom() 
		 + "\n" + "à : "  + destinataire.getNom() 
		 + "\n"+ "sujet :" + mess.getSujet() 
		 + "\n" + mess.getCorps()+ "\n";				
		 messageReception.setText(phrase);	
	}
	
	//affiche les bavards connectes / deconnectes
	public void afficheConnectes() {
		String bavardCo="";
		for (PapotageListener bavard : concierge.getListeEcouteurs()) { //on parcourt la liste des bavards
			if (bavard.isConnecte()) { //si le bavard est connecte
				bavard.getIb().afficheConnectes(); //on recupere le pseudo du bavard 
				bavardCo += "bavard " + bavard.getNom() + " est en ligne - "; //on l affiche en ligne
			}else { //si le bavard n est pas connecte
				bavardCo += "bavard " + bavard.getNom() + " est hors ligne - "; //on l affiche hors ligne
			}
			bavardsCo.setText(bavardCo); //affichage
		}
	}
	
	//bouton pour ajouter un bavard
    public void actionPerformed(ActionEvent e) {
        ArrayList<String> listeNoms = new ArrayList<String>();
        
        if (e.getActionCommand().equals("createBavard")){ //creation d un nouveau bavard
            for (PapotageListener pl : this.concierge.getListeEcouteurs()) { //on parcourt la liste
                listeNoms.add(pl.getNom()); //on ajoute le pseudo du bavard a la liste
            }
            String nomBavard = texte.getText();
            
            if (nomBavard.isEmpty()) { //si aucun nom est entre
            	texte.setText("");
            }else if (listeNoms.contains(nomBavard)) { //si un bavard a deja le meme nom
            	texte.setText("");
            }else {
            	Bavard bavard = concierge.generateBavard(nomBavard); //creation du bavard a partir du pseudo entre
            	InterfaceBavard nouveauBavard = new InterfaceBavard(bavard);
            	texte.setText("");
            	nouveauBavard.setBavard(bavard);
            	nouveauBavard.setConcierge(concierge);
            	bavard.setIb(nouveauBavard);
            }
        }
    }
}