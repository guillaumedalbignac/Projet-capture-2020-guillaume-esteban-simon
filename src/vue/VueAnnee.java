package vue;
import com.sun.media.jfxmedia.logging.Logger;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import controleur.ControleurAnnee;

import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import modele.HumiditeAnnee;
import outil.Journal;

public class VueAnnee extends Vue {

	protected static VueAnnee instance = null; 
	public static VueAnnee getInstance() {if(null==instance)instance = new VueAnnee();return VueAnnee.instance;}; 

	protected ControleurAnnee controleur = null;
	public ControleurAnnee getControleur() {return this.controleur;}
	
	private VueAnnee() 
	{
		super("annee.fxml"); 
		super.controleur = this.controleur = new ControleurAnnee();
		Logger.logMsg(Logger.INFO, "new VueAnnee()");
	}
		
	public void activerControles()
	{
		super.activerControles();
		Journal.ecrire(3, "Etape controles1");

		Button actionAccueil = (Button)lookup("#action-menu-accueil");
		
		actionAccueil.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
			controleur.clicAccueil();
			}
		});

		Button actionJournee = (Button)lookup("#action-menu-journee");
		
		actionJournee.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
			controleur.clicJournee();
			}
		});

		Button actionMois = (Button)lookup("#action-menu-mois");
		
		actionMois.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
			controleur.clicMois();
			}
		});
	}

	public void afficherAnnee(List<HumiditeAnnee> LHA)
	{		
		Journal.ecrire(3, "Etape void afficher");
		/*
		for(HumiditeAnnee ha : LHA) {
			System.out.println(ha.getId());
			Journal.ecrire(3, "Etape humidite for 2");
			HBox vueHAnneeMin = (HBox)lookup("#hb-min");
			vueHAnneeMin.getStyleClass().add(ha.getMin()+"");
			vueHAnneeMin.getChildren().add(new Label ("Min"));
			vueHAnnees.getChildren().add(vueHAnneeMin);
		}*/
		VBox vueHAnneeM = (VBox)lookup("#vb-mois");
		VBox vueHAnneeMin = (VBox)lookup("#vb-min");
		VBox vueHAnneeMax = (VBox)lookup("#vb-max");
		VBox vueHAnneeMoy = (VBox)lookup("#vb-moy");
		vueHAnneeM.getChildren().clear();
		vueHAnneeMin.getChildren().clear();
		vueHAnneeMax.getChildren().clear();
		vueHAnneeMoy.getChildren().clear();
		for(HumiditeAnnee ha: LHA) {

			Logger.logMsg(Logger.INFO, "HumiditeAnnee : " + ha.getId());
			System.out.println(ha.getId());
			Journal.ecrire(3, "Etape humidite for 2");
			vueHAnneeM.getChildren().add(new Label (Integer.toString(ha.getId())));
			vueHAnneeMin.getChildren().add(new Label (Integer.toString(ha.getMin())));
			vueHAnneeMax.getChildren().add(new Label (Integer.toString(ha.getMax())));
			vueHAnneeMoy.getChildren().add(new Label (Integer.toString(ha.getMoy())));
		}
	}
}
