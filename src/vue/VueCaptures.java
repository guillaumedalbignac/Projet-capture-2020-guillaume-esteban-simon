package vue;
import com.sun.media.jfxmedia.logging.Logger;

import controleur.ControleurCaptures;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.Glow;
import javafx.scene.effect.Reflection;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class VueCaptures extends Vue {

	protected ControleurCaptures controleur;
	protected static VueCaptures instance = null; 
	public static VueCaptures getInstance() {if(null==instance)instance = new VueCaptures();return VueCaptures.instance;}; 
	
	private VueCaptures() 
	{
		super("captures.fxml"); 
		super.controleur = this.controleur = new ControleurCaptures();
		Logger.logMsg(Logger.INFO, "new VueCaptures()");
		
	}
		
	public void activerControles()
	{
		super.activerControles();
		
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

		Button actionAnnee = (Button)lookup("#action-menu-annee");
		
		actionAnnee.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
			controleur.clicAnnee();
			}
		});
		
		//Ajout d'un titre avec effet de brillance
		Label labelTitre = (Label)lookup("#label-titre");
		
		//Création de la brillance
		Glow glow = new Glow();
		glow.setLevel(50);	
		
		//Ajout d'un reflet (façon mirroir)
		Reflection reflection = new Reflection();
		reflection.setTopOffset(0);
        reflection.setTopOpacity(0.75);
        reflection.setBottomOpacity(0.10);
        
        labelTitre.setEffect(reflection);
				
		EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() { 
	         @Override 
	         public void handle(MouseEvent e) { 
	            System.out.println("Mise en brillance du titre"); 
	            labelTitre.setEffect(glow);
	            
	         } 
	      };  
	    labelTitre.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler); 						
	}
}
