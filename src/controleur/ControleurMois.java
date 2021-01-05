package controleur;

import java.util.List;

import com.sun.media.jfxmedia.logging.Logger;

import donnee.HumiditeDAO;
import modele.HumiditeAnnee;
import modele.HumiditeJour;
import vue.Navigateur;
import vue.VueAnnee;
import vue.VueCaptures;
import vue.VueJournee;
import vue.VueMois;

public class ControleurMois extends Controleur{

	public ControleurMois()
	{
		Logger.logMsg(Logger.INFO, "new ControleurAnnee()");
	}
	
	public void clicAccueil()
	{
		Navigateur.getInstance().afficherVue(VueCaptures.getInstance());
		
	}
	
	public void clicJournee()
	{
		Navigateur.getInstance().afficherVue(VueJournee.getInstance());
		
		HumiditeDAO HDAO = new HumiditeDAO();
		HDAO.recupHumidite("jour");
		List<HumiditeJour> HJour = HDAO.DecoderXMLJour();
		VueJournee.getInstance().afficherJour(HJour);	
	}
	
	public void clicAnnee()
	{
		Navigateur.getInstance().afficherVue(VueAnnee.getInstance());
		
		HumiditeDAO HDAO = new HumiditeDAO();
		HDAO.recupHumidite("annee");
		List<HumiditeAnnee> HAnnee = HDAO.DecoderXMLAnnee();
		VueAnnee.getInstance().afficherAnnee(HAnnee);
	}
	
}
