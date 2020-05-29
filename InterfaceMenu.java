/*CLASSE DE L'INTERFACE MENU*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

	public class InterfaceMenu extends JFrame implements ActionListener {

/*VARIABLES*/
		private JOptionPane jop = new JOptionPane();//boite de dialogue
		private JPanel panel1 = new JPanel();
		private JPanel panel2 = new JPanel();
		private JPanel panel3 = new JPanel();
		private JLabel label = new JLabel("Bienvenue dans notre service de messagerie instantanée !");
		private JButton boutonCommencer = new JButton("Commencer");
		
/*CONSTRUCTEUR*/		
		public InterfaceMenu() {
			
		this.setTitle("Accueil"); //titre à l'application
		this.setSize(450, 200); //taille à notre fenêtre
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //l'application se ferme lors du clic sur la croix
		this.setLocationRelativeTo(null); //centre la fenêtre sur l'écran
		
		//Texte
		//On définit le layout en lui indiquant qu'il travaillera en ligne
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.LINE_AXIS));
		
		panel1.add(Box.createRigidArea(new Dimension(0,100))); //2eme param =distance, hauteur
		label.setFont(new Font("Comic Sans MS", Font.BOLD,14)); 
		label.setForeground(Color.DARK_GRAY);
		panel1.add(label); 	
		panel1.setBackground(Color.ORANGE);
		
		//Bouton
		//On définit le layout en lui indiquant qu'il travaillera en ligne
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.LINE_AXIS));
		panel2.add(boutonCommencer);
		panel2.add(Box.createRigidArea(new Dimension(20,0))); //1er param = distance entre boutons
		panel2.setBackground(Color.ORANGE);
		
		// Ajout du bouton comme ecouteur
        boutonCommencer.addActionListener(this);  
        boutonCommencer.setActionCommand("Commencer");
		
		//Contient tout
		//On positionne maintenant ces deux lignes en colonne
		panel3.setLayout(new BoxLayout(panel3, BoxLayout.PAGE_AXIS));
		panel3.add(panel1);
		panel3.add(panel2);
		panel3.setBackground(Color.ORANGE);
					
		this.getContentPane().add(panel3);
		this.setVisible(true);    		
	  }

/*METHODE*/	
			//Permet l'action du bouton commencer
			public void actionPerformed(ActionEvent e) {
				//si on clique sur le bouton commencer
				if(e.getActionCommand().equals("Commencer")) {
					//boite de dialogue
					jop.showMessageDialog(null, "Veuillez creer un nouvel utilisateur d'Epapotage, \n Et vous connecter par la suite.", "Attention", JOptionPane.INFORMATION_MESSAGE);
	
					//on ouvre l'interface du concierge 
					Concierge c = new Concierge();
					InterfaceGestionnaire ig = new InterfaceGestionnaire(c);
				}   
			}
		
}