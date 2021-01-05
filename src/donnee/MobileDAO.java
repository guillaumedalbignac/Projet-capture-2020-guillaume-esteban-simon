package donnee;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import modele.AlerteMobile;
import modele.ApercuMobile;
import outil.Journal;
import outil.JournalDesactivable;

public class MobileDAO {
	protected String xml = "";
	
	
	public String recupMobile(String quelMobile) {
		//lecture
		String URL_MOBILE = "";
		switch (quelMobile) {
        case "alerte":  URL_MOBILE = "http://devoircapture.ddns.net/humidite/mobile/alerte";
                 break;
        case "apercu":  URL_MOBILE = "http://devoircapture.ddns.net/humidite/mobile/apercu";


    }
		
		String derniereBalise = "</humidite>";
		

		try {
			xml ="<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
			URL urlListePensees = new URL(URL_MOBILE);
			InputStream flux = urlListePensees.openConnection().getInputStream();
			Scanner lecteur = new Scanner(flux);
			lecteur.useDelimiter(derniereBalise);
			this.xml = lecteur.next() + derniereBalise;
			lecteur.close();
			this.xml = new String(this.xml.getBytes("UTF-8"), "ISO-8859-1");
			
		}
		catch (IOException e1) {
			e1.printStackTrace();
		}
		System.out.println(this.xml);
		

		return this.xml;
	}
	
	public List<ApercuMobile> DecoderXMLApercuMobile() {
		// Parsing
		//File fichierXML = new File("C:\\Users\\Simon\\git\\devoir-capture-2020-guillaume-esteban-simon\\src\\donnee\\EchafaudXmlApercuMobile.xml"); //echafaud fichier xml pour tester
		JournalDesactivable.ecrire("decoderListe()");
		List<ApercuMobile> listeApercuMobile = new ArrayList<ApercuMobile>();

		try 
		{
			DocumentBuilder parseur = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			@SuppressWarnings("deprecation")
			Document document = parseur.parse(new StringBufferInputStream(this.xml)); //mettre à la place du fichier xml
			String racine = document.getDocumentElement().getNodeName();
			Journal.ecrire(3, "Racine=" + racine);
					
			NodeList listeNoeudsApercuMobile = document.getElementsByTagName("apercu");
			for(int position = 0; position < listeNoeudsApercuMobile.getLength(); position++)
			{
				Element noeudApercuMobile = (Element)listeNoeudsApercuMobile.item(position);
				String mesureActuelle = noeudApercuMobile.getElementsByTagName("mesureactuelle").item(0).getTextContent();
				String moyJour = noeudApercuMobile.getElementsByTagName("journee").item(0).getTextContent();
				String moyAnnee = noeudApercuMobile.getElementsByTagName("annee").item(0).getTextContent();

				Journal.ecrire(3,"Mesure actuelle : " + mesureActuelle);
				Journal.ecrire(3,"moyenne du jour : " + moyJour);
				Journal.ecrire(3,"Moyenne de l'année : " + moyAnnee);
				ApercuMobile apercuMobile = new ApercuMobile(mesureActuelle,moyJour,moyAnnee);
				listeApercuMobile.add(apercuMobile);
			}
		} 
		catch (ParserConfigurationException e) 
		{	
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return listeApercuMobile;
	}
	
	
	public List<AlerteMobile> DecoderXMLAlerteMobile() {
		// Parsing
		//File fichierXML = new File("C:\\Users\\Simon\\git\\devoir-capture-2020-guillaume-esteban-simon\\src\\donnee\\EchafaudXmlAlerteMobile.xml"); //echafaud fichier xml pour tester
		JournalDesactivable.ecrire("decoderListe()");
		List<AlerteMobile> listeAlerteMobile = new ArrayList<AlerteMobile>();

		try 
		{
			DocumentBuilder parseur = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			@SuppressWarnings("deprecation")
			Document document = parseur.parse(new StringBufferInputStream(this.xml)); //mettre à la place du fichier xml
			String racine = document.getDocumentElement().getNodeName();
			Journal.ecrire(3, "Racine=" + racine);
					
			NodeList listeNoeudsAlerteMobile = document.getElementsByTagName("mobile");
			for(int position = 0; position < listeNoeudsAlerteMobile.getLength(); position++)
			{
				Element noeudAlerteMobile = (Element)listeNoeudsAlerteMobile.item(position);
				String alerte = noeudAlerteMobile.getElementsByTagName("alerte").item(0).getTextContent();
				String seuil = noeudAlerteMobile.getElementsByTagName("seuil").item(0).getTextContent();
				

				Journal.ecrire(3,"Alerte : " + alerte);
				Journal.ecrire(3,"Seuil : " + seuil);
				AlerteMobile alerteMobile = new AlerteMobile(alerte,seuil);
				listeAlerteMobile.add(alerteMobile);
			}
		} 
		catch (ParserConfigurationException e) 
		{	
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return listeAlerteMobile;
	}

<<<<<<< HEAD
}
=======
}
>>>>>>> origin/master
