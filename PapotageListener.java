/*INTERFACE PAPOTAGELISTENER QUI EST IMPLEMENTER PAR LA CLASSE BAVARD*/	

import java.util.ArrayList;

public interface PapotageListener {

	public boolean isConnecte();
	
/*SETTER*/	
	public void setInterfaceBavard(InterfaceBavard interfaceBavard);
	public void setConnecte(boolean connecte); 

	
/*GETTER*/	
	public String getNom();
	public InterfaceBavard getIb();

}