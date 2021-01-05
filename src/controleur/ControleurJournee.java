package controleur;

import java.util.List;

import com.sun.media.jfxmedia.logging.Logger;

import donnee.HumiditeDAO;
import modele.HumiditeAnnee;
import modele.HumiditeMois;
import vue.Navigateur;
import vue.VueAnnee;
import vue.VueCaptures;
import vue.VueMois;

public class ControleurJournee extends Controleur{

	public ControleurJournee()
	{
		Logger.logMsg(Logger.INFO, "new ControleurJournee()");
	}
	
	public void clicAccueil()
	{
		Navigateur.getInstance().afficherVue(VueCaptures.getInstance());
		
	}
	
	public void clicMois()
	{
		Navigateur.getInstance().afficherVue(VueMois.getInstance());
		
		HumiditeDAO HDAO = new HumiditeDAO();
		HDAO.recupHumidite("mois");
		List<HumiditeMois> HMois = HDAO.DecoderXMLMois();
		VueMois.getInstance().afficherMois(HMois);
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
