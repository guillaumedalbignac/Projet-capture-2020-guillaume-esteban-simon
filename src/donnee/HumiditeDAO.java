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

import modele.HumiditeAnnee;
import modele.HumiditeJour;
import modele.HumiditeMois;
import outil.Journal;
import outil.JournalDesactivable;



public class HumiditeDAO {
	protected String xml = "";
	
	
	public String recupHumidite(String quelHumidite) {
		//lecture
		String URL_HUMIDITE = "";
		switch (quelHumidite) {
        case "jour":  URL_HUMIDITE = "http://devoircapture.ddns.net/humidite/bureau/jour";
                 break;
        case "mois":  URL_HUMIDITE = "http://devoircapture.ddns.net/humidite/bureau/mois";
                 break;
        case "annee":  URL_HUMIDITE = "http://devoircapture.ddns.net/humidite/bureau/annee";

    }
		
		String derniereBalise = "</humidite>";
		

		try {
			xml ="<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
			URL urlListePensees = new URL(URL_HUMIDITE);
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
	
	public List<HumiditeJour> DecoderXMLJour() {
		// Parsing
		//File fichierXML = new File("C:\\Users\\Simon\\git\\devoir-capture-2020-guillaume-esteban-simon\\src\\donnee\\EchafaudXmlJour.xml"); //echafaud fichier xml pour tester
		JournalDesactivable.ecrire("decoderListe()");
		List<HumiditeJour> listeHumiditeJour = new ArrayList<HumiditeJour>();

		try 
		{
			DocumentBuilder parseur = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			@SuppressWarnings("deprecation")
			Document document = parseur.parse(new StringBufferInputStream(this.xml)); //mettre à la place du fichier xml
			String racine = document.getDocumentElement().getNodeName();
			Journal.ecrire(3, "Racine=" + racine);
					
			NodeList listeNoeudsHumiditeJour = document.getElementsByTagName("heure");
			for(int position = 0; position < listeNoeudsHumiditeJour.getLength(); position++)
			{
				//Node noeudPensee = listePensees.item(position);
				Element noeudHumiditeJour = (Element)listeNoeudsHumiditeJour.item(position);
				String id = noeudHumiditeJour.getElementsByTagName("valeur").item(0).getTextContent();
				String min = noeudHumiditeJour.getElementsByTagName("min").item(0).getTextContent();
				String moyenne = noeudHumiditeJour.getElementsByTagName("moyenne").item(0).getTextContent();
				String max = noeudHumiditeJour.getElementsByTagName("max").item(0).getTextContent();
				
				Journal.ecrire(3,"Id : " + id);
				Journal.ecrire(3,"min : " + min);
				Journal.ecrire(3,"max : " + max);
				Journal.ecrire(3,"moyenne : " + moyenne);
				HumiditeJour humiditeJour = new HumiditeJour(id,min,max,moyenne);
				listeHumiditeJour.add(humiditeJour);
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
		return listeHumiditeJour;
	}
	
	
	
	public List<HumiditeAnnee> DecoderXMLAnnee() {
		// Parsing
		//File fichierXML = new File("C:\\Users\\Simon\\git\\devoir-capture-2020-guillaume-esteban-simon\\src\\donnee\\EchafaudXmlAnnee.xml"); //echafaud fichier xml pour tester
		JournalDesactivable.ecrire("decoderListe()");
		List<HumiditeAnnee> listeHumiditeAnnee = new ArrayList<HumiditeAnnee>();

		try 
		{
			DocumentBuilder parseur = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			@SuppressWarnings("deprecation")
			Document document = parseur.parse(new StringBufferInputStream(this.xml)); //mettre à la place du fichier xml
			String racine = document.getDocumentElement().getNodeName();
			Journal.ecrire(3, "Racine=" + racine);
					
			NodeList listeNoeudsHumiditeAnnee = document.getElementsByTagName("mois");
			for(int position = 0; position < listeNoeudsHumiditeAnnee.getLength(); position++)
			{
				//Node noeudPensee = listePensees.item(position);
				Element noeudHumiditeAnnee = (Element)listeNoeudsHumiditeAnnee.item(position);
				String id = noeudHumiditeAnnee.getElementsByTagName("valeur").item(0).getTextContent();
				String min = noeudHumiditeAnnee.getElementsByTagName("min").item(0).getTextContent();
				String moyenne = noeudHumiditeAnnee.getElementsByTagName("moyenne").item(0).getTextContent();
				String max = noeudHumiditeAnnee.getElementsByTagName("max").item(0).getTextContent();
				
				Journal.ecrire(3,"Id : " + id);
				Journal.ecrire(3,"min : " + min);
				Journal.ecrire(3,"max : " + max);
				Journal.ecrire(3,"moyenne : " + moyenne);
				HumiditeAnnee humiditeAnnee = new HumiditeAnnee(id,min,max,moyenne);
				listeHumiditeAnnee.add(humiditeAnnee);
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
		return listeHumiditeAnnee;
	}
	
	
	public List<HumiditeMois> DecoderXMLMois() {
		// Parsing
		//File fichierXML = new File("C:\\Users\\Simon\\git\\devoir-capture-2020-guillaume-esteban-simon\\src\\donnee\\EchafaudXmlMois.xml"); //echafaud fichier xml pour tester
		JournalDesactivable.ecrire("decoderListe()");
		List<HumiditeMois> listeHumiditeMois = new ArrayList<HumiditeMois>();

		try 
		{
			DocumentBuilder parseur = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			@SuppressWarnings("deprecation")
			Document document = parseur.parse(new StringBufferInputStream(this.xml)); //mettre à la place du fichier xml
			String racine = document.getDocumentElement().getNodeName();
			Journal.ecrire(3, "Racine=" + racine);
					
			NodeList listeNoeudsHumiditeMois = document.getElementsByTagName("jour");
			for(int position = 0; position < listeNoeudsHumiditeMois.getLength(); position++)
			{
				//Node noeudPensee = listePensees.item(position);
				Element noeudHumiditeMois = (Element)listeNoeudsHumiditeMois.item(position);
				String id = noeudHumiditeMois.getElementsByTagName("valeur").item(0).getTextContent();
				String min = noeudHumiditeMois.getElementsByTagName("min").item(0).getTextContent();
				String moyenne = noeudHumiditeMois.getElementsByTagName("moyenne").item(0).getTextContent();
				String max = noeudHumiditeMois.getElementsByTagName("max").item(0).getTextContent();
				
				Journal.ecrire(3,"Id : " + id);
				Journal.ecrire(3,"min : " + min);
				Journal.ecrire(3,"max : " + max);
				Journal.ecrire(3,"moyenne : " + moyenne);
				HumiditeMois humiditeMois = new HumiditeMois(id,min,max,moyenne);
				listeHumiditeMois.add(humiditeMois);
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
		return listeHumiditeMois;
	}
	
	

}
