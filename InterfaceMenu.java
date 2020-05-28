import javax.swing.*;
import javax.swing.text.html.HTMLEditorKit;
import java.io.IOException;
import java.awt.*;
import java.awt.event.*;
import java.awt.Toolkit.*; 

	public class InterfaceMenu extends JFrame implements ActionListener {

		public InterfaceMenu() {
			
		this.setTitle("Accueil"); //On donne un titre à l'application
		this.setSize(450, 200); //On donne une taille à notre fenêtre
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit à l'application de se fermer lors du clic sur la croix
		this.setLocationRelativeTo(null); //On centre la fenêtre sur l'écran
		
		//Texte
		JPanel b0 = new JPanel();
		//On définit le layout en lui indiquant qu'il travaillera en ligne
		b0.setLayout(new BoxLayout(b0, BoxLayout.LINE_AXIS));
		b0.add(Box.createRigidArea(new Dimension(0,100))); //2e param = dist hauteur
		JLabel label = new JLabel("Bienvenue dans notre service de messagerie instantanée !"); //texte
		label.setFont(new Font("Comic Sans MS", Font.BOLD,14)); //attributs du texte
		label.setForeground(Color.DARK_GRAY);
		b0.add(label); 	
		b0.setBackground(Color.ORANGE);
		
		//Boutons
		JPanel b1 = new JPanel();
		//On définit le layout en lui indiquant qu'il travaillera en ligne
		b1.setLayout(new BoxLayout(b1, BoxLayout.LINE_AXIS));
		JButton boutonCommencer = new JButton("Commencer");
		b1.add(boutonCommencer);
		b1.add(Box.createRigidArea(new Dimension(20,0))); //1er param = dist entre boutons
		b1.setBackground(Color.ORANGE);
		
		// Ajout du bouton comme ecouteur
        boutonCommencer.addActionListener(this);  
        boutonCommencer.setActionCommand("Commencer");
		
		//Contient tout
		JPanel b2 = new JPanel();
		//On positionne maintenant ces deux lignes en colonne
		b2.setLayout(new BoxLayout(b2, BoxLayout.PAGE_AXIS));
		b2.add(b0);
		b2.add(b1);
		b2.setBackground(Color.ORANGE);
					
		this.getContentPane().add(b2);
		this.setVisible(true);    		
	  }

		// Utilisation des boutons
		public void actionPerformed(ActionEvent e) {

			if(e.getActionCommand().equals("Commencer")) {
				Concierge c = new Concierge();
				//ouverture de la fenetre du concierge 
				InterfaceGestionnaire ig = new InterfaceGestionnaire(c);
   }   
}
}