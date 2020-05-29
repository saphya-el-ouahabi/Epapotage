/*CLASSE DE L'INTERFACE CONNECTION*/	

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;


public class InterfaceConnexion extends JFrame implements ActionListener {
	
/*VARIABLES*/	
	
	private JLabel label = new JLabel("Nom de l'utilisateur");
	private JTextField pseudo = new JTextField("",10);
	private JButton boutonConnexion = new JButton("Connexion");
	private JPanel panel = new JPanel();
	
	private static Concierge concierge = new Concierge();
	private InterfaceGestionnaire interfaceGestion;


/*CONSTRUCTEUR*/
	public InterfaceConnexion() {
		super();
		setLocation(10, 510);
		setTitle("Identification");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Caracteristiques du Jlabel et Jtexfield
		label.setFont(new Font("Comic Sans MS", Font.BOLD,14));
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		this.pseudo.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		//Caracteristiques du bouton
		boutonConnexion.addActionListener(this);  
		boutonConnexion.setActionCommand("connexion");
		this.boutonConnexion.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		//Ensemblage des elements dans le Jpanel
		panel.add(label);
		panel.add(pseudo);
		panel.setBackground(Color.ORANGE);
		panel.add(boutonConnexion);
		setContentPane(panel);

		pack();
		setVisible(true);	
	}
	
/*SETTER*/	
	public Concierge setConcierge(Concierge concierge) {
		return concierge = concierge;
	}
	public void setIg(InterfaceGestionnaire interfaceGestion) {
		this.interfaceGestion = interfaceGestion;
	}

/*GETTER*/
	public Concierge getConcierge() {
		return concierge;
	}
	
/*METHODE*/
		//Permet de gerer l'action du bouton connexion:
		public void actionPerformed(ActionEvent e) {
			//si on clique sur le bouton
			if (e.getActionCommand().equals("connexion")){
				String nomUser = pseudo.getText();
				//on parcourt la liste des ecouteurs 
				for (PapotageListener bavard : interfaceGestion.getConcierge().getListeEcouteurs()) {
					//si le pseudo rentre dans le jTexfiel = à un pseudo de la liste
					if (nomUser.equals(bavard.getNom())) { 
						//alors on connecte 
						interfaceGestion.getConcierge().connecteBavard(bavard); 							
						pseudo.setText("");
						bavard.getIb().setVisible(true);
					}
					//sinon rien faire
					else {
					}}}
			//si on clique sur la croix pour fermer la fenetre connexion
			if (e.getActionCommand().equals("close")) { 
			//alors toutes les interfaces se ferment
			this.dispose(); 
			}
		}
	

}