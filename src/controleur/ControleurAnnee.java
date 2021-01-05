package controleur;

import java.util.List;

import com.sun.media.jfxmedia.logging.Logger;

import donnee.HumiditeDAO;
import modele.HumiditeJour;
import modele.HumiditeMois;
import outil.Journal;
import vue.Navigateur;
import vue.VueCaptures;
import vue.VueJournee;
import vue.VueMois;

public class ControleurAnnee extends Controleur{

	public ControleurAnnee()
	{
		Logger.logMsg(Logger.INFO, "new ControleurAnnee()");
		Journal.ecrire(3, "Etape c");
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
	
	public void clicMois()
	{
		Navigateur.getInstance().afficherVue(VueMois.getInstance());
		
		HumiditeDAO HDAO = new HumiditeDAO();
		HDAO.recupHumidite("mois");
		List<HumiditeMois> HMois = HDAO.DecoderXMLMois();
		VueMois.getInstance().afficherMois(HMois);
	}	
}
