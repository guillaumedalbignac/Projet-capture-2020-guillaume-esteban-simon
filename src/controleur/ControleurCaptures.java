package controleur;

import java.util.List;

import com.sun.media.jfxmedia.logging.Logger;

import donnee.HumiditeDAO;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modele.HumiditeJour;
import modele.HumiditeMois;
import modele.HumiditeAnnee;
import outil.Journal;
import vue.Navigateur;
import vue.VueJournee;
import vue.VueMois;
import vue.VueAnnee;

public class ControleurCaptures extends Controleur{

	public ControleurCaptures()
	{
		Logger.logMsg(Logger.INFO, "new ControleurCaptures()");
	}
	
	public void clicJournee()
	{
		Navigateur.getInstance().afficherVue(VueMois.getInstance());
			
		HumiditeDAO HDAO = new HumiditeDAO();
		HDAO.recupHumidite("mois");
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
	
	public void clicAnnee()
	{
		Navigateur.getInstance().afficherVue(VueAnnee.getInstance());
		
		HumiditeDAO HDAO = new HumiditeDAO();
		HDAO.recupHumidite("annee");
		List<HumiditeAnnee> HAnnee = HDAO.DecoderXMLAnnee();
		VueAnnee.getInstance().afficherAnnee(HAnnee);
	}
}
